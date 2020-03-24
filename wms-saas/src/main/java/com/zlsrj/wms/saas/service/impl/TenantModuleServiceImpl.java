package com.zlsrj.wms.saas.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.saas.mapper.ModuleInfoMapper;
import com.zlsrj.wms.saas.mapper.TenantModuleMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantModuleService;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantModuleServiceImpl extends ServiceImpl<TenantModuleMapper, TenantModule>
		implements ITenantModuleService {
	@Resource
	private IIdService idService;
	
	@Resource
	private ModuleInfoMapper moduleInfoMapper;

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		QueryWrapper<TenantModule> queryWrapperTenantModule = new QueryWrapper<TenantModule>();
		queryWrapperTenantModule.lambda()//
				.eq(TenantModule::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantModule);
		if (count > 0) {
			log.error("根据租户信息初始化租户模块失败，租户模块已存在。");
			return false;
		}

		QueryWrapper<ModuleInfo> queryWrapperModuleInfo = new QueryWrapper<ModuleInfo>();
		queryWrapperModuleInfo.lambda()//
				.eq(ModuleInfo::getOpenTarget, tenantInfo.getTenantType())//
				.eq(ModuleInfo::getBillingMode, 1)//
				.eq(ModuleInfo::getModuleOn, 1)//
		;
		List<ModuleInfo> moduleInfoList = moduleInfoMapper.selectList(queryWrapperModuleInfo);
		if (moduleInfoList != null && moduleInfoList.size() > 0) {
			List<TenantModule> tenantModuleList = new ArrayList<TenantModule>();
			for (ModuleInfo moduleInfo : moduleInfoList) {
				int moduleEdition = 1;
				if (moduleInfo.getBasicEditionOn() == 1) {
					moduleEdition = 1;
				} else if (moduleInfo.getAdvanceEditionOn() == 1) {
					moduleEdition = 2;
				} else if (moduleInfo.getUltimateEditionOn() == 1) {
					moduleEdition = 3;
				}
				TenantModule tenantModule = TenantModule.builder()//
						.id(idService.selectId())// 租户模块ID
						.tenantId(tenantInfo.getId())// 租户ID
						.moduleId(moduleInfo.getId())// 模块ID
						.moduleEdition(moduleEdition)// 开通版本（1：基础版；2：高级版；3：旗舰版）
						.moduleOpenTime(new Date())// 开通时间
						.build();
				tenantModuleList.add(tenantModule);
			}
			success = this.saveBatch(tenantModuleList);
		}

		return success;
	}

	@Override
	public boolean saveBatchByModuleInfo(ModuleInfo moduleInfo) {
		return false;
	}

	/**
	 * 根据租户批量保存租户模块
	 * 
	 * @param tenantInfo
	 * @return
	 */
	@Override
	@Transactional
	public boolean saveBatchByTenantInfo(List<TenantModule> tenantModuleList) {
		boolean success = false;
		if (tenantModuleList != null && tenantModuleList.size() > 0) {
			TenantModule tenantModule = tenantModuleList.get(0);
			String tenantId = tenantModule.getTenantId();
			QueryWrapper<TenantModule> queryWrapperTenantModule = new QueryWrapper<TenantModule>();
			queryWrapperTenantModule.lambda().eq(TenantModule::getTenantId, tenantId);
			super.remove(queryWrapperTenantModule);

			for (TenantModule tm : tenantModuleList) {
				if (tm.getId() == null) {
					tm.setId(idService.selectId());
				}
				if (tm.getModuleOpenTime() == null) {
					tm.setModuleOpenTime(DateUtil.date());
				}
				super.save(tm);
			}
			success = true;
		}
		return success;
	}

}
