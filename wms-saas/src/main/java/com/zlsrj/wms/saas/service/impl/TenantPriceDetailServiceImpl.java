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
import com.zlsrj.wms.api.dto.TenantPriceDetailAddParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantPriceDetailMapper;
import com.zlsrj.wms.saas.service.ITenantPriceDetailService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantPriceDetailServiceImpl extends ServiceImpl<TenantPriceDetailMapper, TenantPriceDetail> implements ITenantPriceDetailService {
	@Resource
	private IIdService idService;

	@Override
	public TenantPriceDetail getAggregation(Wrapper<TenantPriceDetail> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	public String save(TenantPriceDetailAddParam tenantPriceDetailAddParam) {
		TenantPriceDetail tenantPriceDetail = TranslateUtil.translate(tenantPriceDetailAddParam,
				TenantPriceDetail.class);
		if (tenantPriceDetail != null && StringUtils.isBlank(tenantPriceDetail.getId())) {
			tenantPriceDetail.setId(idService.selectId());
		}
		this.save(tenantPriceDetail);

		return tenantPriceDetail.getId();
	}

	@Override
	public boolean updateById(TenantPriceDetailUpdateParam tenantPriceDetailUpdateParam) {
		TenantPriceDetail tenantPriceDetail = TranslateUtil.translate(tenantPriceDetailUpdateParam,
				TenantPriceDetail.class);

		return this.updateById(tenantPriceDetail);
	}
	
}
