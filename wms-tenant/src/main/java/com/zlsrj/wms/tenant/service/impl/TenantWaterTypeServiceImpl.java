package com.zlsrj.wms.tenant.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.enums.WaterTypeEnum;
import com.zlsrj.wms.tenant.mapper.TenantWaterTypeMapper;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantWaterTypeService;
import com.zlsrj.wms.tenant.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantWaterTypeServiceImpl extends ServiceImpl<TenantWaterTypeMapper, TenantWaterType> implements ITenantWaterTypeService {
	@Resource
	private RedisService<String, String> redisService;
	@Resource
	private IIdService idService;

	@Override
	public TenantWaterType getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				TenantWaterType tenantWaterType = JSON.parseObject(jsonString, TenantWaterType.class);
				return tenantWaterType;
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		TenantWaterType tenantWaterType = baseMapper.selectById(id);
		if (tenantWaterType != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(tenantWaterType));
		}

		return tenantWaterType;
	}

	@Override
	public boolean update(TenantWaterType entity, Wrapper<TenantWaterType> updateWrapper) {
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
		QueryWrapper<TenantWaterType> queryWrapperTenantWaterType = new QueryWrapper<TenantWaterType>();
		queryWrapperTenantWaterType.lambda()//
				.eq(TenantWaterType::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantWaterType);
		if (count > 0) {
			log.error("根据租户信息初始化用水类型失败，租户类型已存在。");
			return false;
		}

		List<TenantWaterType> tenantWaterTypeList = new ArrayList<TenantWaterType>();
		for (WaterTypeEnum waterTypeEnum : WaterTypeEnum.values()) {
			TenantWaterType tenantWaterType = TenantWaterType.builder()//
					.id(idService.selectId())// 系统ID
					.tenantId(tenantInfo.getId())// 租户编号
					.waterTypeName(waterTypeEnum.getText())// 用水类别名称
					.build();
			log.info(tenantWaterType.toString());
			tenantWaterTypeList.add(tenantWaterType);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info(tenantWaterTypeList.toString());
		return super.saveBatch(tenantWaterTypeList);
	}

}
