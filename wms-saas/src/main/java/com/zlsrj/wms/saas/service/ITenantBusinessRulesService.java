package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantBusinessRulesAddParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesUpdateParam;
import com.zlsrj.wms.api.entity.TenantBusinessRules;

public interface ITenantBusinessRulesService extends IService<TenantBusinessRules> {
	String save(TenantBusinessRulesAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantBusinessRulesUpdateParam tenantCustomerTypeUpdateParam);

}