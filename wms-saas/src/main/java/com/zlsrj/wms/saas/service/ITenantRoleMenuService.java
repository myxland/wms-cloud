package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRoleMenu;

public interface ITenantRoleMenuService extends IService<TenantRoleMenu> {
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
}
