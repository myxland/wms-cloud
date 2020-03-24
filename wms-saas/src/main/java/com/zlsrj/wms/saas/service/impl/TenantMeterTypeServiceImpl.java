package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterTypeAddParam;
import com.zlsrj.wms.api.dto.TenantMeterTypeUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterType;
import com.zlsrj.wms.api.entity.TenantMeterTypeDefault;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterTypeDefaultMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterTypeMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterTypeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterTypeServiceImpl extends ServiceImpl<TenantMeterTypeMapper, TenantMeterType> implements ITenantMeterTypeService {
	@Resource
	private IIdService idService;
	@Resource
	private TenantMeterTypeDefaultMapper tenantMeterTypeDefaultMapper;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		
		QueryWrapper<TenantMeterType> queryWrapperTenantMeterType = new QueryWrapper<TenantMeterType>();
		queryWrapperTenantMeterType.lambda()//
				.eq(TenantMeterType::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantMeterType);
		if (count > 0) {
			log.error("根据租户信息初始化水表类型失败，水表类型已存在。");
			return false;
		}
		
		List<TenantMeterTypeDefault> tenantMeterTypeDefaultList = tenantMeterTypeDefaultMapper.selectList(null);
		
		if(tenantMeterTypeDefaultList!=null && tenantMeterTypeDefaultList.size()>0) {
			
			List<TenantMeterType> tenantMeterTypeList = new ArrayList<TenantMeterType>();
			
			for(TenantMeterTypeDefault tenantMeterTypeDefault: tenantMeterTypeDefaultList) {
				TenantMeterType tenantMeterType = TenantMeterType.builder()//
						.id(idService.selectId())// 
						.tenantId(tenantInfo.getId())// 租户ID
						.meterTypeName(tenantMeterTypeDefault.getMeterTypeName())// 名称
						.meterTypeData(tenantMeterTypeDefault.getMeterTypeData())// 结构化数据
						.build();
				
				tenantMeterTypeList.add(tenantMeterType);
			}
			
			success = this.saveBatch(tenantMeterTypeList);
		}
		
		return success;
	}

	@Override
	public String save(TenantMeterTypeAddParam tenantMeterTypeAddParam) {
		TenantMeterType tenantMeterType = TranslateUtil.translate(tenantMeterTypeAddParam,
				TenantMeterType.class);
		if (tenantMeterType != null && StringUtils.isBlank(tenantMeterType.getId())) {
			tenantMeterType.setId(idService.selectId());
		}
		this.save(tenantMeterType);

		return tenantMeterType.getId();
	}

	@Override
	public boolean updateById(TenantMeterTypeUpdateParam tenantMeterTypeUpdateParam) {
		TenantMeterType tenantMeterType = TranslateUtil.translate(tenantMeterTypeUpdateParam,
				TenantMeterType.class);

		return this.updateById(tenantMeterType);
	}
	
}
