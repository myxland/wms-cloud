package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantChargeAgencyAddParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyUpdateParam;
import com.zlsrj.wms.api.entity.TenantChargeAgency;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantChargeAgencyMapper;
import com.zlsrj.wms.saas.service.ITenantChargeAgencyService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantChargeAgencyServiceImpl extends ServiceImpl<TenantChargeAgencyMapper, TenantChargeAgency> implements ITenantChargeAgencyService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantChargeAgencyAddParam tenantChargeAgencyAddParam) {
		TenantChargeAgency tenantChargeAgency = TranslateUtil.translate(tenantChargeAgencyAddParam,
				TenantChargeAgency.class);
		if (tenantChargeAgency != null && StringUtils.isBlank(tenantChargeAgency.getId())) {
			tenantChargeAgency.setId(idService.selectId());
		}
		this.save(tenantChargeAgency);

		return tenantChargeAgency.getId();
	}

	@Override
	public boolean updateById(TenantChargeAgencyUpdateParam tenantChargeAgencyUpdateParam) {
		TenantChargeAgency tenantChargeAgency = TranslateUtil.translate(tenantChargeAgencyUpdateParam,
				TenantChargeAgency.class);

		return this.updateById(tenantChargeAgency);
	}
	
}
