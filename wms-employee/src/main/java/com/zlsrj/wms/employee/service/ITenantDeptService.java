package com.zlsrj.wms.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantDept;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantDeptService extends IService<TenantDept> {
	/**
	 * 根据新建租户信息创建默认用户类型
	 * @param tenantInfo
	 * @return
	 */
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
}
