package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantPayment;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantPaymentService extends IService<TenantPayment> {
	TenantPayment getAggregation(Wrapper<TenantPayment> wrapper);
}