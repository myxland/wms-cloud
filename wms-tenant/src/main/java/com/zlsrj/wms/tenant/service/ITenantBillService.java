package com.zlsrj.wms.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantBill;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantBillService extends IService<TenantBill> {
	/**
	 * 根据新建租户信息创建默认租户账单配置
	 * @param tenantInfo
	 * @return
	 */
	boolean saveByTenantInfo(TenantInfo tenantInfo);
}
