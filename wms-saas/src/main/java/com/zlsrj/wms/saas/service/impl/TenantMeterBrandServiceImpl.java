package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterBrandAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBrandUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterBrand;
import com.zlsrj.wms.api.entity.TenantMeterBrandDefault;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterBrandDefaultMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterBrandMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterBrandService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterBrandServiceImpl extends ServiceImpl<TenantMeterBrandMapper, TenantMeterBrand> implements ITenantMeterBrandService {
	@Resource
	private IIdService idService;
	@Resource
	private TenantMeterBrandDefaultMapper tenantMeterBrandDefaultMapper;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		
		QueryWrapper<TenantMeterBrand> queryWrapperTenantMeterBrand = new QueryWrapper<TenantMeterBrand>();
		queryWrapperTenantMeterBrand.lambda()//
				.eq(TenantMeterBrand::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantMeterBrand);
		if (count > 0) {
			log.error("根据租户信息初始化水表口径失败，水表口径已存在。");
			return false;
		}
		
		List<TenantMeterBrandDefault> tenantMeterBrandDefaultList = tenantMeterBrandDefaultMapper.selectList(null);
		
		if(tenantMeterBrandDefaultList!=null && tenantMeterBrandDefaultList.size()>0) {
			
			List<TenantMeterBrand> tenantMeterBrandList = new ArrayList<TenantMeterBrand>();
			
			for(TenantMeterBrandDefault tenantMeterBrandDefault: tenantMeterBrandDefaultList) {
				TenantMeterBrand tenantMeterBrand = TenantMeterBrand.builder()//
						.id(idService.selectId())// 
						.tenantId(tenantInfo.getId())// 租户ID
						.meterBrandName(tenantMeterBrandDefault.getMeterBrandName())// 名称
						.meterBrandData(tenantMeterBrandDefault.getMeterBrandData())// 结构化数据
						.build();
				
				tenantMeterBrandList.add(tenantMeterBrand);
			}
			
			success = this.saveBatch(tenantMeterBrandList);
		}
		
		return success;
	}

	@Override
	public String save(TenantMeterBrandAddParam tenantMeterBrandAddParam) {
		TenantMeterBrand tenantMeterBrand = TranslateUtil.translate(tenantMeterBrandAddParam,
				TenantMeterBrand.class);
		if (tenantMeterBrand != null && StringUtils.isBlank(tenantMeterBrand.getId())) {
			tenantMeterBrand.setId(idService.selectId());
		}
		this.save(tenantMeterBrand);

		return tenantMeterBrand.getId();
	}

	@Override
	public boolean updateById(TenantMeterBrandUpdateParam tenantMeterBrandUpdateParam) {
		TenantMeterBrand tenantMeterBrand = TranslateUtil.translate(tenantMeterBrandUpdateParam,
				TenantMeterBrand.class);

		return this.updateById(tenantMeterBrand);
	}
	
}
