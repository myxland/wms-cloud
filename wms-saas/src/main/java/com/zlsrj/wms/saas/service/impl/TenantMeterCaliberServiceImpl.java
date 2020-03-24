package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterCaliberAddParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterCaliber;
import com.zlsrj.wms.api.entity.TenantMeterCaliberDefault;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterCaliberDefaultMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterCaliberMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterCaliberService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterCaliberServiceImpl extends ServiceImpl<TenantMeterCaliberMapper, TenantMeterCaliber> implements ITenantMeterCaliberService {
	@Resource
	private IIdService idService;
	@Resource
	private TenantMeterCaliberDefaultMapper tenantMeterCaliberDefaultMapper;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		
		QueryWrapper<TenantMeterCaliber> queryWrapperTenantMeterCaliber = new QueryWrapper<TenantMeterCaliber>();
		queryWrapperTenantMeterCaliber.lambda()//
				.eq(TenantMeterCaliber::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantMeterCaliber);
		if (count > 0) {
			log.error("根据租户信息初始化水表口径失败，水表口径已存在。");
			return false;
		}
		
		List<TenantMeterCaliberDefault> tenantMeterCaliberDefaultList = tenantMeterCaliberDefaultMapper.selectList(null);
		
		if(tenantMeterCaliberDefaultList!=null && tenantMeterCaliberDefaultList.size()>0) {
			
			List<TenantMeterCaliber> tenantMeterCaliberList = new ArrayList<TenantMeterCaliber>();
			
			for(TenantMeterCaliberDefault tenantMeterCaliberDefault: tenantMeterCaliberDefaultList) {
				TenantMeterCaliber tenantMeterCaliber = TenantMeterCaliber.builder()//
						.id(idService.selectId())// 
						.tenantId(tenantInfo.getId())// 租户ID
						.meterCaliberName(tenantMeterCaliberDefault.getMeterCaliberName())// 名称
						.meterCaliberData(tenantMeterCaliberDefault.getMeterCaliberData())// 结构化数据
						.build();
				
				tenantMeterCaliberList.add(tenantMeterCaliber);
			}
			
			success = this.saveBatch(tenantMeterCaliberList);
		}
		
		return success;
	}

	@Override
	public String save(TenantMeterCaliberAddParam tenantMeterCaliberAddParam) {
		TenantMeterCaliber tenantMeterCaliber = TranslateUtil.translate(tenantMeterCaliberAddParam,
				TenantMeterCaliber.class);
		if (tenantMeterCaliber != null && StringUtils.isBlank(tenantMeterCaliber.getId())) {
			tenantMeterCaliber.setId(idService.selectId());
		}
		this.save(tenantMeterCaliber);

		return tenantMeterCaliber.getId();
	}

	@Override
	public boolean updateById(TenantMeterCaliberUpdateParam tenantMeterCaliberUpdateParam) {
		TenantMeterCaliber tenantMeterCaliber = TranslateUtil.translate(tenantMeterCaliberUpdateParam,
				TenantMeterCaliber.class);

		return this.updateById(tenantMeterCaliber);
	}
	
}
