package com.zlsrj.wms.saas.service.impl;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterMarketingAreaMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterMarketingAreaService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterMarketingAreaServiceImpl extends ServiceImpl<TenantMeterMarketingAreaMapper, TenantMeterMarketingArea> implements ITenantMeterMarketingAreaService {
	@Resource
	private IIdService idService;

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantMeterMarketingArea> queryWrapperTenantMeterMarketingArea = new QueryWrapper<TenantMeterMarketingArea>();
		queryWrapperTenantMeterMarketingArea.lambda()//
				.eq(TenantMeterMarketingArea::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantMeterMarketingArea);
		if (count > 0) {
			log.error("根据租户信息初始化营销区域失败，营销区域已存在。");
			return false;
		}
		
		TenantMeterMarketingArea tenantMeterMarketingArea = TenantMeterMarketingArea.builder()//
				.id(idService.selectId())// 营销机构ID
				.tenantId(tenantInfo.getId())// 租户ID
				.marketingAreaType(0)// 区域类型（0：内部机构；1：代收机构）
				.marketingAreaName(tenantInfo.getTenantName())// 名称
				.marketingAreaParentId("")// 父级ID
				.marketingAreaData(null)// 结构化数据
				.build();

		boolean success = this.save(tenantMeterMarketingArea);

		return success;
	}
	
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
