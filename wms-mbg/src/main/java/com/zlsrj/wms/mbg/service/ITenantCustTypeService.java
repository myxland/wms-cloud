package com.zlsrj.wms.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.mbg.entity.TenantCustType;

public interface ITenantCustTypeService extends IService<TenantCustType> {
	boolean initByTenant(Long tenantId);
}
