package com.zlsrj.wms.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantInvoice;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantInvoiceService extends IService<TenantInvoice> {
	/**
	 * 根据新建租户信息创建默认租户发票配置
	 * @param tenantInfo
	 * @return
	 */
	boolean saveByTenantInfo(TenantInfo tenantInfo);
}
