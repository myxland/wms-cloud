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
import com.zlsrj.wms.api.dto.TenantConsumptionBillAddParam;
import com.zlsrj.wms.api.dto.TenantConsumptionBillUpdateParam;
import com.zlsrj.wms.api.entity.TenantConsumptionBill;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantConsumptionBillMapper;
import com.zlsrj.wms.saas.service.ITenantConsumptionBillService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantConsumptionBillServiceImpl extends ServiceImpl<TenantConsumptionBillMapper, TenantConsumptionBill> implements ITenantConsumptionBillService {
	@Resource
	private IIdService idService;

	@Override
	public TenantConsumptionBill getAggregation(Wrapper<TenantConsumptionBill> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	public String save(TenantConsumptionBillAddParam tenantConsumptionBillAddParam) {
		TenantConsumptionBill tenantConsumptionBill = TranslateUtil.translate(tenantConsumptionBillAddParam,
				TenantConsumptionBill.class);
		if (tenantConsumptionBill != null && StringUtils.isBlank(tenantConsumptionBill.getId())) {
			tenantConsumptionBill.setId(idService.selectId());
		}
		this.save(tenantConsumptionBill);

		return tenantConsumptionBill.getId();
	}

	@Override
	public boolean updateById(TenantConsumptionBillUpdateParam tenantConsumptionBillUpdateParam) {
		TenantConsumptionBill tenantConsumptionBill = TranslateUtil.translate(tenantConsumptionBillUpdateParam,
				TenantConsumptionBill.class);

		return this.updateById(tenantConsumptionBill);
	}
	
}
