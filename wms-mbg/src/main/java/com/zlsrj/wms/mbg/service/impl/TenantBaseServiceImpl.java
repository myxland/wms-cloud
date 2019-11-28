package com.zlsrj.wms.mbg.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zlsrj.wms.api.entity.TenantCustType;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.api.enums.CustTypeEnum;
import com.zlsrj.wms.api.enums.WaterTypeEnum;
import com.zlsrj.wms.mbg.service.ITenantBaseService;
import com.zlsrj.wms.mbg.service.ITenantCustTypeService;
import com.zlsrj.wms.mbg.service.ITenantWaterTypeService;

import cn.hutool.core.util.IdUtil;

@Service
public class TenantBaseServiceImpl implements ITenantBaseService {

	@Resource
	private ITenantWaterTypeService tenantWaterTypeService;

	@Resource
	private ITenantCustTypeService tenantCustTypeService;

	public boolean initByTenant(TenantInfo tenantInfo) {
		boolean success = false;

		Arrays.asList(WaterTypeEnum.values()).forEach(c -> {
			TenantWaterType tenantWaterType = TenantWaterType.builder()//
					.id(IdUtil.createSnowflake(1L, 1L).nextId())// 用水类别编号
					.tenantId(tenantInfo.getId())// 租户编号
					.waterTypeName(c.getText())// 用水类别名称
					.build();
			tenantWaterTypeService.save(tenantWaterType);
		});

		Arrays.asList(CustTypeEnum.values()).forEach(c -> {
			TenantCustType tenantCustType = TenantCustType.builder()//
					.id(IdUtil.createSnowflake(1L, 1L).nextId())// 用户类别编号
					.tenantId(tenantInfo.getId())// 租户编号
					.custTypeName(c.getText())// 用户类别名称
					.build();
			tenantCustTypeService.save(tenantCustType);
		});

		success = true;
		return success;
	}
}
