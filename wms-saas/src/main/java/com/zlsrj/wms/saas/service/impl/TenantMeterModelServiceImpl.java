package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterModelAddParam;
import com.zlsrj.wms.api.dto.TenantMeterModelUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterModel;
import com.zlsrj.wms.api.entity.TenantMeterModelDefault;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterModelDefaultMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterModelMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterModelService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterModelServiceImpl extends ServiceImpl<TenantMeterModelMapper, TenantMeterModel> implements ITenantMeterModelService {
	@Resource
	private IIdService idService;
	@Resource
	private TenantMeterModelDefaultMapper tenantMeterModelDefaultMapper;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		
		QueryWrapper<TenantMeterModel> queryWrapperTenantMeterModel = new QueryWrapper<TenantMeterModel>();
		queryWrapperTenantMeterModel.lambda()//
				.eq(TenantMeterModel::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantMeterModel);
		if (count > 0) {
			log.error("根据租户信息初始化水表型号失败，水表型号已存在。");
			return false;
		}
		
		List<TenantMeterModelDefault> tenantMeterModelDefaultList = tenantMeterModelDefaultMapper.selectList(null);
		
		if(tenantMeterModelDefaultList!=null && tenantMeterModelDefaultList.size()>0) {
			
			List<TenantMeterModel> tenantMeterModelList = new ArrayList<TenantMeterModel>();
			
			for(TenantMeterModelDefault tenantMeterModelDefault: tenantMeterModelDefaultList) {
				TenantMeterModel tenantMeterModel = TenantMeterModel.builder()//
						.id(idService.selectId())// 
						.tenantId(tenantInfo.getId())// 租户ID
						.meterModelName(tenantMeterModelDefault.getMeterModelName())// 名称
						.meterModelData(tenantMeterModelDefault.getMeterModelData())// 结构化数据
						.build();
				
				tenantMeterModelList.add(tenantMeterModel);
			}
			
			success = this.saveBatch(tenantMeterModelList);
		}
		
		return success;
	}

	@Override
	public String save(TenantMeterModelAddParam tenantMeterModelAddParam) {
		TenantMeterModel tenantMeterModel = TranslateUtil.translate(tenantMeterModelAddParam,
				TenantMeterModel.class);
		if (tenantMeterModel != null && StringUtils.isBlank(tenantMeterModel.getId())) {
			tenantMeterModel.setId(idService.selectId());
		}
		this.save(tenantMeterModel);

		return tenantMeterModel.getId();
	}

	@Override
	public boolean updateById(TenantMeterModelUpdateParam tenantMeterModelUpdateParam) {
		TenantMeterModel tenantMeterModel = TranslateUtil.translate(tenantMeterModelUpdateParam,
				TenantMeterModel.class);

		return this.updateById(tenantMeterModel);
	}
	
}
