package com.zlsrj.wms.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantWaterType;

public interface ITenantWaterTypeService extends IService<TenantWaterType> {
	boolean initByTenant(Long tenantId);
}
