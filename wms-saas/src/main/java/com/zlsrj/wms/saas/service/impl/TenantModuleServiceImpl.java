package com.zlsrj.wms.saas.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantModule;
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

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		return false;
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
