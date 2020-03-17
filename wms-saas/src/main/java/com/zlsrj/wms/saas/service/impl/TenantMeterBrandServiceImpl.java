package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterBrandAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBrandUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterBrand;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterBrandMapper;
import com.zlsrj.wms.saas.service.ITenantMeterBrandService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterBrandServiceImpl extends ServiceImpl<TenantMeterBrandMapper, TenantMeterBrand> implements ITenantMeterBrandService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantMeterBrandAddParam tenantMeterBrandAddParam) {
		TenantMeterBrand tenantMeterBrand = TranslateUtil.translate(tenantMeterBrandAddParam,
				TenantMeterBrand.class);
		if (tenantMeterBrand != null && StringUtils.isBlank(tenantMeterBrand.getId())) {
			tenantMeterBrand.setId(idService.selectId());
		}
		this.save(tenantMeterBrand);

		return tenantMeterBrand.getId();
	}

	@Override
	public boolean updateById(TenantMeterBrandUpdateParam tenantMeterBrandUpdateParam) {
		TenantMeterBrand tenantMeterBrand = TranslateUtil.translate(tenantMeterBrandUpdateParam,
				TenantMeterBrand.class);

		return this.updateById(tenantMeterBrand);
	}
	
}
