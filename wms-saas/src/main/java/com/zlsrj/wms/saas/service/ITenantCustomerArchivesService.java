package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerArchives;

public interface ITenantCustomerArchivesService extends IService<TenantCustomerArchives> {
	String save(TenantCustomerArchivesAddParam tenantCustomerArchivesAddParam);

	boolean updateById(TenantCustomerArchivesUpdateParam tenantCustomerArchivesUpdateParam);

}