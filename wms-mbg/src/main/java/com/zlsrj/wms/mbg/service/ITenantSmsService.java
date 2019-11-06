package com.zlsrj.wms.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantSms;

public interface ITenantSmsService extends IService<TenantSms> {
	boolean initByTenant(Long tenantId);
}
