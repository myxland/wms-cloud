package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantPayment;

import com.zlsrj.wms.saas.mapper.TenantPaymentMapper;
import com.zlsrj.wms.saas.service.ITenantPaymentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantPaymentServiceImpl extends ServiceImpl<TenantPaymentMapper, TenantPayment> implements ITenantPaymentService {

	@Override
	public TenantPayment getAggregation(Wrapper<TenantPayment> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
}
