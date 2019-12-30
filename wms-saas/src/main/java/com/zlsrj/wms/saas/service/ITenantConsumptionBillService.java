package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantConsumptionBill;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantConsumptionBillService extends IService<TenantConsumptionBill> {
	/**
	 * 根据新建租户信息创建默认用户类型
	 * @param tenantInfo
	 * @return
	 */
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
}
