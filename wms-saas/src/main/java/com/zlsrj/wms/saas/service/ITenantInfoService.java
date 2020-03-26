package com.zlsrj.wms.saas.service;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantInfoAddParam;
import com.zlsrj.wms.api.dto.TenantInfoModuleInfoUpdateParam;
import com.zlsrj.wms.api.dto.TenantInfoRechargeParam;
import com.zlsrj.wms.api.dto.TenantInfoUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantInfoService extends IService<TenantInfo> {
	
	TenantInfo getDictionaryById(Serializable id);
	
	String save(TenantInfoAddParam tenantInfoAddParam);
	
	boolean updateById(TenantInfoUpdateParam tenantInfoUpdateParam);
	
	boolean recharge(TenantInfoRechargeParam tenantInfoRechargeParam);
	
	boolean updateModule(TenantInfoModuleInfoUpdateParam tenantInfoModuleInfoUpdateParam);
}
