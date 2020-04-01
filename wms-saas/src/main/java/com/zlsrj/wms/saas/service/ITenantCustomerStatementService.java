package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantCustomerStatementAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerStatement;

public interface ITenantCustomerStatementService extends IService<TenantCustomerStatement> {
	String save(TenantCustomerStatementAddParam tenantCustomerStatementAddParam);

	boolean updateById(TenantCustomerStatementUpdateParam tenantCustomerStatementUpdateParam);

}