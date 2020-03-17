package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterCaliberAddParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterCaliber;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterCaliberMapper;
import com.zlsrj.wms.saas.service.ITenantMeterCaliberService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterCaliberServiceImpl extends ServiceImpl<TenantMeterCaliberMapper, TenantMeterCaliber> implements ITenantMeterCaliberService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantMeterCaliberAddParam tenantMeterCaliberAddParam) {
		TenantMeterCaliber tenantMeterCaliber = TranslateUtil.translate(tenantMeterCaliberAddParam,
				TenantMeterCaliber.class);
		if (tenantMeterCaliber != null && StringUtils.isBlank(tenantMeterCaliber.getId())) {
			tenantMeterCaliber.setId(idService.selectId());
		}
		this.save(tenantMeterCaliber);

		return tenantMeterCaliber.getId();
	}

	@Override
	public boolean updateById(TenantMeterCaliberUpdateParam tenantMeterCaliberUpdateParam) {
		TenantMeterCaliber tenantMeterCaliber = TranslateUtil.translate(tenantMeterCaliberUpdateParam,
				TenantMeterCaliber.class);

		return this.updateById(tenantMeterCaliber);
	}
	
}
