package com.zlsrj.wms.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.mbg.entity.TenantInfo;
import com.zlsrj.wms.mbg.entity.TenantRole;

public interface ITenantRoleService extends IService<TenantRole> {
	boolean initByTenant(TenantInfo tenantInfo);
}
