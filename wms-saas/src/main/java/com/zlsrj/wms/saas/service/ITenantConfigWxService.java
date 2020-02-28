package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantConfigWx;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantConfigWxService extends IService<TenantConfigWx> {
	TenantConfigWx getAggregation(Wrapper<TenantConfigWx> wrapper);
}