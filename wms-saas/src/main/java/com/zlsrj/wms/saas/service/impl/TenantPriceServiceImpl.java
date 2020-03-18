package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantPriceAddParam;
import com.zlsrj.wms.api.dto.TenantPriceUpdateParam;
import com.zlsrj.wms.api.entity.TenantPrice;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantPriceMapper;
import com.zlsrj.wms.saas.service.ITenantPriceService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantPriceServiceImpl extends ServiceImpl<TenantPriceMapper, TenantPrice> implements ITenantPriceService {
	@Resource
	private IIdService idService;

	@Override
	public TenantPrice getAggregation(Wrapper<TenantPrice> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	public String save(TenantPriceAddParam tenantPriceAddParam) {
		TenantPrice tenantPrice = TranslateUtil.translate(tenantPriceAddParam,
				TenantPrice.class);
		if (tenantPrice != null && StringUtils.isBlank(tenantPrice.getId())) {
			tenantPrice.setId(idService.selectId());
		}
		this.save(tenantPrice);

		return tenantPrice.getId();
	}

	@Override
	public boolean updateById(TenantPriceUpdateParam tenantPriceUpdateParam) {
		TenantPrice tenantPrice = TranslateUtil.translate(tenantPriceUpdateParam,
				TenantPrice.class);

		return this.updateById(tenantPrice);
	}
	
}
