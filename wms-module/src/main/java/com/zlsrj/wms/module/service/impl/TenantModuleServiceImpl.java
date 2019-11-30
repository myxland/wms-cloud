package com.zlsrj.wms.module.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.enums.ModuleEnum;
import com.zlsrj.wms.module.mapper.TenantModuleMapper;
import com.zlsrj.wms.module.service.IIdService;
import com.zlsrj.wms.module.service.ITenantModuleService;
import com.zlsrj.wms.module.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantModuleServiceImpl extends ServiceImpl<TenantModuleMapper, TenantModule> implements ITenantModuleService {
	@Resource
	private RedisService<String, String> redisService;
	@Resource
	private IIdService idService;

	@Override
	public TenantModule getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				TenantModule tenantModule = JSON.parseObject(jsonString, TenantModule.class);
				return tenantModule;
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		TenantModule tenantModule = baseMapper.selectById(id);
		if (tenantModule != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(tenantModule));
		}

		return tenantModule;
	}

	@Override
	public boolean update(TenantModule entity, Wrapper<TenantModule> updateWrapper) {
		boolean success = super.update(entity, updateWrapper);
		if (success) {
			try {
				Long id = updateWrapper.getEntity().getId();
				redisService.remove(Long.toString(id));
			} catch(Exception e) {
				//ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}

	@Override
	public boolean removeById(Serializable id) {
		boolean success = super.removeById(id);
		if (success) {
			try {
				redisService.remove(id.toString());
			} catch(Exception e) {
				//ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantModule> queryWrapperTenantModule = new QueryWrapper<TenantModule>();
		queryWrapperTenantModule.lambda()//
				.eq(TenantModule::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantModule);
		if (count > 0) {
			log.error("根据租户信息初始化租户模块失败，租户模块已存在。");
			return false;
		}

		List<TenantModule> tenantModuleList = new ArrayList<TenantModule>();
		for (ModuleEnum moduleEnum : ModuleEnum.values()) {
			TenantModule tenantModule = TenantModule.builder()//
					.id(idService.selectId())// 系统ID
					.tenantId(tenantInfo.getId())// 租户编号
					.moduleDisplayName(moduleEnum.getText())// 模块显示名称
					.moduleOrder(null)// 模块排序
					.moduleEdition(null)// 开通版本（1：基础版；2：高级版；3：旗舰版）
					.moduleStatus(null)// 模块状态（1：开通；0：未开通）
					.modulePriceType(null)// 价格体系（1：标准价格；2：指定价格）
					.moduleOpenDate(null)// 开通时间
					.moduleEndDate(null)// 到期时间
					.moduleStartChargeDate(null)// 开始计费日期
					.moduleArrears(BigDecimal.ZERO)// 欠费金额
					.moduleOverdraft(BigDecimal.ZERO)// 透支额度
					.build();
			log.info(tenantModule.toString());
			tenantModuleList.add(tenantModule);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info(tenantModuleList.toString());
		return super.saveBatch(tenantModuleList);
	}

}
