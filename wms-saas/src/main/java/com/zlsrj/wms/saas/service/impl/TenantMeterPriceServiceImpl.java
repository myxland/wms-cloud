package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterPriceAddParam;
import com.zlsrj.wms.api.dto.TenantMeterPriceUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterPrice;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterPriceMapper;
import com.zlsrj.wms.saas.service.ITenantMeterPriceService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterPriceServiceImpl extends ServiceImpl<TenantMeterPriceMapper, TenantMeterPrice> implements ITenantMeterPriceService {
	@Resource
	private IIdService idService;

	@Override
	public TenantMeterPrice getAggregation(Wrapper<TenantMeterPrice> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	public String save(TenantMeterPriceAddParam tenantMeterPriceAddParam) {
		TenantMeterPrice tenantMeterPrice = TranslateUtil.translate(tenantMeterPriceAddParam,
				TenantMeterPrice.class);
		if (tenantMeterPrice != null && StringUtils.isBlank(tenantMeterPrice.getId())) {
			tenantMeterPrice.setId(idService.selectId());
		}
		this.save(tenantMeterPrice);

		return tenantMeterPrice.getId();
	}

	@Override
	public boolean updateById(TenantMeterPriceUpdateParam tenantMeterPriceUpdateParam) {
		TenantMeterPrice tenantMeterPrice = TranslateUtil.translate(tenantMeterPriceUpdateParam,
				TenantMeterPrice.class);

		return this.updateById(tenantMeterPrice);
	}
	
}
