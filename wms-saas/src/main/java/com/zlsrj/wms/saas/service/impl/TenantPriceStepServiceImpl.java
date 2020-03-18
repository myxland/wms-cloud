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
import com.zlsrj.wms.api.dto.TenantPriceStepAddParam;
import com.zlsrj.wms.api.dto.TenantPriceStepUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantPriceStepMapper;
import com.zlsrj.wms.saas.service.ITenantPriceStepService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantPriceStepServiceImpl extends ServiceImpl<TenantPriceStepMapper, TenantPriceStep> implements ITenantPriceStepService {
	@Resource
	private IIdService idService;

	@Override
	public TenantPriceStep getAggregation(Wrapper<TenantPriceStep> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	public String save(TenantPriceStepAddParam tenantPriceStepAddParam) {
		TenantPriceStep tenantPriceStep = TranslateUtil.translate(tenantPriceStepAddParam,
				TenantPriceStep.class);
		if (tenantPriceStep != null && StringUtils.isBlank(tenantPriceStep.getId())) {
			tenantPriceStep.setId(idService.selectId());
		}
		this.save(tenantPriceStep);

		return tenantPriceStep.getId();
	}

	@Override
	public boolean updateById(TenantPriceStepUpdateParam tenantPriceStepUpdateParam) {
		TenantPriceStep tenantPriceStep = TranslateUtil.translate(tenantPriceStepUpdateParam,
				TenantPriceStep.class);

		return this.updateById(tenantPriceStep);
	}
	
}
