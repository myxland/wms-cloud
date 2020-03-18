package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantPriceRuleAddParam;
import com.zlsrj.wms.api.dto.TenantPriceRuleUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceRule;

public interface ITenantPriceRuleService extends IService<TenantPriceRule> {
	String save(TenantPriceRuleAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantPriceRuleUpdateParam tenantCustomerTypeUpdateParam);

}