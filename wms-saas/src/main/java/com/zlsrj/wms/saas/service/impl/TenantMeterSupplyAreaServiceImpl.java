package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterSupplyArea;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterSupplyAreaMapper;
import com.zlsrj.wms.saas.service.ITenantMeterSupplyAreaService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterSupplyAreaServiceImpl extends ServiceImpl<TenantMeterSupplyAreaMapper, TenantMeterSupplyArea> implements ITenantMeterSupplyAreaService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantMeterSupplyAreaAddParam tenantMeterSupplyAreaAddParam) {
		TenantMeterSupplyArea tenantMeterSupplyArea = TranslateUtil.translate(tenantMeterSupplyAreaAddParam,
				TenantMeterSupplyArea.class);
		if (tenantMeterSupplyArea != null && StringUtils.isBlank(tenantMeterSupplyArea.getId())) {
			tenantMeterSupplyArea.setId(idService.selectId());
		}
		this.save(tenantMeterSupplyArea);

		return tenantMeterSupplyArea.getId();
	}

	@Override
	public boolean updateById(TenantMeterSupplyAreaUpdateParam tenantMeterSupplyAreaUpdateParam) {
		TenantMeterSupplyArea tenantMeterSupplyArea = TranslateUtil.translate(tenantMeterSupplyAreaUpdateParam,
				TenantMeterSupplyArea.class);

		return this.updateById(tenantMeterSupplyArea);
	}
	
}
