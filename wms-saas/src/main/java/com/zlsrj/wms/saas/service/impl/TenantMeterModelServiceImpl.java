package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterModelAddParam;
import com.zlsrj.wms.api.dto.TenantMeterModelUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterModel;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterModelMapper;
import com.zlsrj.wms.saas.service.ITenantMeterModelService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterModelServiceImpl extends ServiceImpl<TenantMeterModelMapper, TenantMeterModel> implements ITenantMeterModelService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantMeterModelAddParam tenantMeterModelAddParam) {
		TenantMeterModel tenantMeterModel = TranslateUtil.translate(tenantMeterModelAddParam,
				TenantMeterModel.class);
		if (tenantMeterModel != null && StringUtils.isBlank(tenantMeterModel.getId())) {
			tenantMeterModel.setId(idService.selectId());
		}
		this.save(tenantMeterModel);

		return tenantMeterModel.getId();
	}

	@Override
	public boolean updateById(TenantMeterModelUpdateParam tenantMeterModelUpdateParam) {
		TenantMeterModel tenantMeterModel = TranslateUtil.translate(tenantMeterModelUpdateParam,
				TenantMeterModel.class);

		return this.updateById(tenantMeterModel);
	}
	
}
