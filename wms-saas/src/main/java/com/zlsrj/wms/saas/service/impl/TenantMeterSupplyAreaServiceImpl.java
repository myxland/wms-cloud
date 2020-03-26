package com.zlsrj.wms.saas.service.impl;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterSupplyArea;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterSupplyAreaMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterSupplyAreaService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterSupplyAreaServiceImpl extends ServiceImpl<TenantMeterSupplyAreaMapper, TenantMeterSupplyArea> implements ITenantMeterSupplyAreaService {
	@Resource
	private IIdService idService;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantMeterSupplyArea> queryWrapperTenantMeterSupplyArea = new QueryWrapper<TenantMeterSupplyArea>();
		queryWrapperTenantMeterSupplyArea.lambda()//
				.eq(TenantMeterSupplyArea::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantMeterSupplyArea);
		if (count > 0) {
			log.error("根据租户信息初始化供水区域失败，供水区域已存在。");
			return false;
		}

		TenantMeterSupplyArea tenantMeterSupplyArea = TenantMeterSupplyArea.builder()//
				.id(idService.selectId())// 供水区域ID
				.tenantId(tenantInfo.getId())// 租户ID
				.supplyAreaName(tenantInfo.getTenantName())// 名称
				.supplyAreaParentId("")// 父级ID
				.supplyAreaData(null)// 结构化数据
				.build();

		boolean success = this.save(tenantMeterSupplyArea);

		return success;
	}
	
	@Override
	public boolean removeBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		QueryWrapper<TenantMeterSupplyArea> queryWrapperTenantMeterSupplyArea = new QueryWrapper<TenantMeterSupplyArea>();
		queryWrapperTenantMeterSupplyArea.lambda()//
				.eq(TenantMeterSupplyArea::getTenantId, tenantInfo.getId())//
		;
		success = this.remove(queryWrapperTenantMeterSupplyArea);
		
		return success;
	}

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
