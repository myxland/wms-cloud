package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterMarketingAreaMapper;
import com.zlsrj.wms.saas.service.ITenantMeterMarketingAreaService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterMarketingAreaServiceImpl extends ServiceImpl<TenantMeterMarketingAreaMapper, TenantMeterMarketingArea> implements ITenantMeterMarketingAreaService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantMeterMarketingAreaAddParam tenantMeterMarketingAreaAddParam) {
		TenantMeterMarketingArea tenantMeterMarketingArea = TranslateUtil.translate(tenantMeterMarketingAreaAddParam,
				TenantMeterMarketingArea.class);
		if (tenantMeterMarketingArea != null && StringUtils.isBlank(tenantMeterMarketingArea.getId())) {
			tenantMeterMarketingArea.setId(idService.selectId());
		}
		this.save(tenantMeterMarketingArea);

		return tenantMeterMarketingArea.getId();
	}

	@Override
	public boolean updateById(TenantMeterMarketingAreaUpdateParam tenantMeterMarketingAreaUpdateParam) {
		TenantMeterMarketingArea tenantMeterMarketingArea = TranslateUtil.translate(tenantMeterMarketingAreaUpdateParam,
				TenantMeterMarketingArea.class);

		return this.updateById(tenantMeterMarketingArea);
	}
	
}
