package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterIndustryAddParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterIndustry;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterIndustryMapper;
import com.zlsrj.wms.saas.service.ITenantMeterIndustryService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterIndustryServiceImpl extends ServiceImpl<TenantMeterIndustryMapper, TenantMeterIndustry> implements ITenantMeterIndustryService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantMeterIndustryAddParam tenantMeterIndustryAddParam) {
		TenantMeterIndustry tenantMeterIndustry = TranslateUtil.translate(tenantMeterIndustryAddParam,
				TenantMeterIndustry.class);
		if (tenantMeterIndustry != null && StringUtils.isBlank(tenantMeterIndustry.getId())) {
			tenantMeterIndustry.setId(idService.selectId());
		}
		this.save(tenantMeterIndustry);

		return tenantMeterIndustry.getId();
	}

	@Override
	public boolean updateById(TenantMeterIndustryUpdateParam tenantMeterIndustryUpdateParam) {
		TenantMeterIndustry tenantMeterIndustry = TranslateUtil.translate(tenantMeterIndustryUpdateParam,
				TenantMeterIndustry.class);

		return this.updateById(tenantMeterIndustry);
	}
	
}
