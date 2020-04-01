package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantCustomerContactsAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerContacts;

public interface ITenantCustomerContactsService extends IService<TenantCustomerContacts> {
	String save(TenantCustomerContactsAddParam tenantCustomerContactsAddParam);

	boolean updateById(TenantCustomerContactsUpdateParam tenantCustomerContactsUpdateParam);

}