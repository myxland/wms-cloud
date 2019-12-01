package com.zlsrj.wms.tenant.service.impl;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.api.entity.TenantModuleBatch;
import com.zlsrj.wms.tenant.mapper.TenantModuleMapper;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantModuleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantModuleServiceImpl extends ServiceImpl<TenantModuleMapper, TenantModule> implements ITenantModuleService {
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
	
	@Transactional
	public boolean saveBatchTenantModuleByTenantId(Long tenantId,TenantModuleBatch tenantModuleBatch) {
		// 删除全部租户与模块关联关系
		QueryWrapper<TenantModule> queryWrapperTenantModule = new QueryWrapper<TenantModule>();
		queryWrapperTenantModule.lambda()//
				.eq(TenantModule::getTenantId, tenantId)//
				.eq(TenantModule::getModuleStatus,0)//删除未开通模块,已开通状态不变，模块状态（1：开通；0：未开通）
		;
		boolean success = super.remove(queryWrapperTenantModule);
		// 批量新建租户与模块关系
		
		String moduleIds = tenantModuleBatch.getModuleIds();
		if(StringUtils.isNoneEmpty(moduleIds)) {
			String[] moduleIdArray = moduleIds.split(",");
			for(int n=0;n<moduleIdArray.length;n++) {
				Long moduleId = Long.parseLong(moduleIdArray[n]);
				
				TenantModule tenantModule = TenantModule.builder()//
						.id(idService.selectId())// 系统ID
						.tenantId(tenantId)// 租户编号
						.moduleId(moduleId)// 模块编号
						.moduleStatus(0) //未开通状态
						.build();
				try {
					success = super.save(tenantModule);//插入异常不抛出
				}catch(Exception e) {
					e.printStackTrace();
					log.info("租户模块关系已存在，不需要重复增加，tenantModule=[{}]",tenantModule.toString());
				}
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		success = true;
		
		return success;
	}
	
	@Transactional
	public boolean saveBatchTenantModuleByModuleId(Long moduleId,TenantModuleBatch tenantModuleBatch) {

		// 删除全部租户与模块关联关系
		QueryWrapper<TenantModule> queryWrapperTenantModule = new QueryWrapper<TenantModule>();
		queryWrapperTenantModule.lambda()//
				.eq(TenantModule::getModuleId,moduleId)//
				.eq(TenantModule::getModuleStatus,0)//删除未开通模块,已开通状态不变，模块状态（1：开通；0：未开通）
		;
		boolean success = super.remove(queryWrapperTenantModule);
		// 批量新建租户与模块关系
		
		String tenantIds = tenantModuleBatch.getTenantIds();
		if(StringUtils.isNoneEmpty(tenantIds)) {
			String[] tenantIdArray = tenantIds.split(",");
			for(int m=0;m<tenantIdArray.length;m++) {
				Long tenantId = Long.parseLong(tenantIdArray[m]);
				
				TenantModule tenantModule = TenantModule.builder()//
						.id(idService.selectId())// 系统ID
						.tenantId(tenantId)// 租户编号
						.moduleId(moduleId)// 模块编号
						.moduleStatus(0) //未开通状态
						.build();
				try {
					success = super.save(tenantModule);//插入异常不抛出
				}catch(Exception e) {
					e.printStackTrace();
					log.info("租户模块关系已存在，不需要重复增加，tenantModule=[{}]",tenantModule.toString());
				}
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		success = true;
		
		return success;
	}

}
