package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterIndustryAddParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryUpdateParam;
import com.zlsrj.wms.api.entity.TenantDepartment;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterIndustry;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterIndustryMapper;
import com.zlsrj.wms.saas.service.ITenantMeterIndustryService;

import cn.hutool.core.util.RandomUtil;

import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterIndustryServiceImpl extends ServiceImpl<TenantMeterIndustryMapper, TenantMeterIndustry> implements ITenantMeterIndustryService {
	@Resource
	private IIdService idService;
	
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantMeterIndustry> queryWrapperTenantMeterIndustry = new QueryWrapper<TenantMeterIndustry>();
		queryWrapperTenantMeterIndustry.lambda()//
				.eq(TenantMeterIndustry::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantMeterIndustry);
		if (count > 0) {
			log.error("根据租户信息初始化行业分类失败，行业分类已存在。");
			return false;
		}
		
//		TenantMeterIndustry tenantMeterIndustry = TenantMeterIndustry.builder()//
//				.id(idService.selectId())// 行业分类ID
//				.tenantId(tenantInfo.getId())// 租户ID
//				.meterIndustryName()// 名称
//				.meterIndustryParentId()// 父级ID
//				.meterIndustryData()// 结构化数据
//				.build();
//		
//		boolean success = this.save(tenantMeterIndustry);

		return false;
	}

	@Override
	public String save(TenantMeterIndustryAddParam tenantMeterIndustryAddParam) {
		TenantMeterIndustry tenantMeterIndustry = TranslateUtil.translate(tenantMeterIndustryAddParam,
				TenantMeterIndustry.class);
		if (tenantMeterIndustry != null && StringUtils.isBlank(tenantMeterIndustry.getId())) {
			tenantMeterIndustry.setId(idService.selectId());
		}
		this.save(tenantMeterIndustry);

		return tenantMeterIndustry.getId();
	}

	@Override
	public boolean updateById(TenantMeterIndustryUpdateParam tenantMeterIndustryUpdateParam) {
		TenantMeterIndustry tenantMeterIndustry = TranslateUtil.translate(tenantMeterIndustryUpdateParam,
				TenantMeterIndustry.class);

		return this.updateById(tenantMeterIndustry);
	}
	
}
