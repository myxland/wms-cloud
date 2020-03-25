package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantChargeAgencyAddParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyUpdateParam;
import com.zlsrj.wms.api.entity.TenantChargeAgency;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantChargeAgencyMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantChargeAgencyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantChargeAgencyServiceImpl extends ServiceImpl<TenantChargeAgencyMapper, TenantChargeAgency> implements ITenantChargeAgencyService {
	@Resource
	private IIdService idService;
	
	private final static String [] CHARGE_AGENCY_NAME = new String[] {"微信","支付宝"};
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		
		QueryWrapper<TenantChargeAgency> queryWrapperTenantChargeAgency = new QueryWrapper<TenantChargeAgency>();
		queryWrapperTenantChargeAgency.lambda()//
				.eq(TenantChargeAgency::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantChargeAgency);
		if (count > 0) {
			log.error("根据租户信息初始化水表口径失败，水表口径已存在。");
			return false;
		}
		
		List<TenantChargeAgency> tenantChargeAgencyList = new ArrayList<TenantChargeAgency>();
		
		for(String name: CHARGE_AGENCY_NAME) {
			
			TenantChargeAgency tenantChargeAgency = TenantChargeAgency.builder()//
					.id(idService.selectId()) // 代收机构ID
					.tenantId(tenantInfo.getId())// 租户ID
					.chargeAgencyName(name)// 代收机构名称
					.chargeAgencyData(null)// 结构化数据
					.createType(1)// 创建类型（1：平台默认创建；2：租户自建）
					.apiOn(0)// 是否开放API（1：开放；0：不开放）
					.build();
			
			tenantChargeAgencyList.add(tenantChargeAgency);
		}
		
		success = this.saveBatch(tenantChargeAgencyList);
		
		return success;
	}
	
	@Override
	public boolean removeBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		QueryWrapper<TenantChargeAgency> queryWrapperTenantChargeAgency = new QueryWrapper<TenantChargeAgency>();
		queryWrapperTenantChargeAgency.lambda()//
				.eq(TenantChargeAgency::getTenantId, tenantInfo.getId())//
		;
		success = this.remove(queryWrapperTenantChargeAgency);
		
		return success;
	}

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
