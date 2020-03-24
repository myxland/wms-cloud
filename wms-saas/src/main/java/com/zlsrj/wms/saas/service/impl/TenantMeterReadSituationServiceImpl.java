package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationAddParam;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterReadSituation;
import com.zlsrj.wms.api.entity.TenantMeterReadSituationDefault;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterReadSituationDefaultMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterReadSituationMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterReadSituationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterReadSituationServiceImpl extends ServiceImpl<TenantMeterReadSituationMapper, TenantMeterReadSituation> implements ITenantMeterReadSituationService {
	@Resource
	private IIdService idService;
	
	@Resource
	private TenantMeterReadSituationDefaultMapper tenantMeterReadSituationDefaultMapper;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		
		QueryWrapper<TenantMeterReadSituation> queryWrapperTenantMeterReadSituation = new QueryWrapper<TenantMeterReadSituation>();
		queryWrapperTenantMeterReadSituation.lambda()//
				.eq(TenantMeterReadSituation::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantMeterReadSituation);
		if (count > 0) {
			log.error("根据租户信息初始化抄表表况失败，抄表表况已存在。");
			return false;
		}
		
		List<TenantMeterReadSituationDefault> tenantMeterReadSituationDefaultList = tenantMeterReadSituationDefaultMapper.selectList(null);
		
		if(tenantMeterReadSituationDefaultList!=null && tenantMeterReadSituationDefaultList.size()>0) {
			
			List<TenantMeterReadSituation> tenantMeterReadSituationList = new ArrayList<TenantMeterReadSituation>();
			
			for(TenantMeterReadSituationDefault tenantMeterReadSituationDefault: tenantMeterReadSituationDefaultList) {
				TenantMeterReadSituation tenantMeterReadSituation = TenantMeterReadSituation.builder()//
						.id(idService.selectId())// 
						.tenantId(tenantInfo.getId())// 租户ID
						.readSituationName(tenantMeterReadSituationDefault.getReadSituationName())// 名称
						.readSituationData(tenantMeterReadSituationDefault.getReadSituationData())// 结构化数据
						.build();
				
				tenantMeterReadSituationList.add(tenantMeterReadSituation);
			}
			
			success = this.saveBatch(tenantMeterReadSituationList);
		}
		
		return success;
	}

	@Override
	public String save(TenantMeterReadSituationAddParam tenantMeterReadSituationAddParam) {
		TenantMeterReadSituation tenantMeterReadSituation = TranslateUtil.translate(tenantMeterReadSituationAddParam,
				TenantMeterReadSituation.class);
		if (tenantMeterReadSituation != null && StringUtils.isBlank(tenantMeterReadSituation.getId())) {
			tenantMeterReadSituation.setId(idService.selectId());
		}
		this.save(tenantMeterReadSituation);

		return tenantMeterReadSituation.getId();
	}

	@Override
	public boolean updateById(TenantMeterReadSituationUpdateParam tenantMeterReadSituationUpdateParam) {
		TenantMeterReadSituation tenantMeterReadSituation = TranslateUtil.translate(tenantMeterReadSituationUpdateParam,
				TenantMeterReadSituation.class);

		return this.updateById(tenantMeterReadSituation);
	}
	
}
