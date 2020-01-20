package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantReceivable;

public interface ITenantReceivableService extends IService<TenantReceivable> {
	TenantReceivable getAggregation(Wrapper<TenantReceivable> wrapper);
}