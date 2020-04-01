package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerInvoice;

public interface ITenantCustomerInvoiceService extends IService<TenantCustomerInvoice> {
	String save(TenantCustomerInvoiceAddParam tenantCustomerInvoiceAddParam);

	boolean updateById(TenantCustomerInvoiceUpdateParam tenantCustomerInvoiceUpdateParam);

}