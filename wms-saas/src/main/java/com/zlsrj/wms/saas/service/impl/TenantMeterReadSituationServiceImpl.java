package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationAddParam;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterReadSituation;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterReadSituationMapper;
import com.zlsrj.wms.saas.service.ITenantMeterReadSituationService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterReadSituationServiceImpl extends ServiceImpl<TenantMeterReadSituationMapper, TenantMeterReadSituation> implements ITenantMeterReadSituationService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantMeterReadSituationAddParam tenantMeterReadSituationAddParam) {
		TenantMeterReadSituation tenantMeterReadSituation = TranslateUtil.translate(tenantMeterReadSituationAddParam,
				TenantMeterReadSituation.class);
		if (tenantMeterReadSituation != null && StringUtils.isBlank(tenantMeterReadSituation.getId())) {
			tenantMeterReadSituation.setId(idService.selectId());
		}
		this.save(tenantMeterReadSituation);

		return tenantMeterReadSituation.getId();
	}

	@Override
	public boolean updateById(TenantMeterReadSituationUpdateParam tenantMeterReadSituationUpdateParam) {
		TenantMeterReadSituation tenantMeterReadSituation = TranslateUtil.translate(tenantMeterReadSituationUpdateParam,
				TenantMeterReadSituation.class);

		return this.updateById(tenantMeterReadSituation);
	}
	
}