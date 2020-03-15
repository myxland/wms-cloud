package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRole;

public interface ITenantRoleService extends IService<TenantRole> {
	/**
	 * 根据新建租户信息创建默认用户类型
	 * @param tenantInfo
	 * @return
	 */
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
	
	String save(TenantRoleAddParam tenantRoleAddParam);
}
