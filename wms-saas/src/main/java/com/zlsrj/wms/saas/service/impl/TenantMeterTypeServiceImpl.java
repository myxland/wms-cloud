package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterTypeAddParam;
import com.zlsrj.wms.api.dto.TenantMeterTypeUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterType;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterTypeMapper;
import com.zlsrj.wms.saas.service.ITenantMeterTypeService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterTypeServiceImpl extends ServiceImpl<TenantMeterTypeMapper, TenantMeterType> implements ITenantMeterTypeService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantMeterTypeAddParam tenantMeterTypeAddParam) {
		TenantMeterType tenantMeterType = TranslateUtil.translate(tenantMeterTypeAddParam,
				TenantMeterType.class);
		if (tenantMeterType != null && StringUtils.isBlank(tenantMeterType.getId())) {
			tenantMeterType.setId(idService.selectId());
		}
		this.save(tenantMeterType);

		return tenantMeterType.getId();
	}

	@Override
	public boolean updateById(TenantMeterTypeUpdateParam tenantMeterTypeUpdateParam) {
		TenantMeterType tenantMeterType = TranslateUtil.translate(tenantMeterTypeUpdateParam,
				TenantMeterType.class);

		return this.updateById(tenantMeterType);
	}
	
}
