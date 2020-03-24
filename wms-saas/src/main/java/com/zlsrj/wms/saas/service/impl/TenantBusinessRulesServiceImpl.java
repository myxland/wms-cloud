package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantBusinessRulesAddParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesUpdateParam;
import com.zlsrj.wms.api.entity.TenantBusinessRules;
import com.zlsrj.wms.api.entity.TenantBusinessRulesDefault;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantBusinessRulesDefaultMapper;
import com.zlsrj.wms.saas.mapper.TenantBusinessRulesMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantBusinessRulesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantBusinessRulesServiceImpl extends ServiceImpl<TenantBusinessRulesMapper, TenantBusinessRules> implements ITenantBusinessRulesService {
	@Resource
	private IIdService idService;
	@Resource
	private TenantBusinessRulesDefaultMapper tenantBusinessRulesDefaultMapper;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		
		QueryWrapper<TenantBusinessRules> queryWrapperTenantBusinessRules = new QueryWrapper<TenantBusinessRules>();
		queryWrapperTenantBusinessRules.lambda()//
				.eq(TenantBusinessRules::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantBusinessRules);
		if (count > 0) {
			log.error("根据租户信息初始化水表口径失败，水表口径已存在。");
			return false;
		}
		
		QueryWrapper<TenantBusinessRulesDefault> queryWrapperTenantBusinessRulesDefault = new QueryWrapper<TenantBusinessRulesDefault>();
		queryWrapperTenantBusinessRulesDefault.lambda()//
				.eq(TenantBusinessRulesDefault::getTenantType, tenantInfo.getTenantType())//
		;
		List<TenantBusinessRulesDefault> tenantBusinessRulesDefaultList = tenantBusinessRulesDefaultMapper.selectList(queryWrapperTenantBusinessRulesDefault);
		
		if(tenantBusinessRulesDefaultList!=null && tenantBusinessRulesDefaultList.size()>0) {
			
			List<TenantBusinessRules> tenantBusinessRulesList = new ArrayList<TenantBusinessRules>();
			
			for(TenantBusinessRulesDefault tenantBusinessRulesDefault: tenantBusinessRulesDefaultList) {
				TenantBusinessRules tenantBusinessRules = TenantBusinessRules.builder()//
						.id(idService.selectId())// 
						.tenantId(tenantInfo.getId())// 租户ID
						.rulesType(tenantBusinessRulesDefault.getRulesType())// 业务规则类型
						.rulesData(tenantBusinessRulesDefault.getRulesData())// 结构化数据
						.build();
				
				tenantBusinessRulesList.add(tenantBusinessRules);
			}
			
			success = this.saveBatch(tenantBusinessRulesList);
		}
		
		return success;
	}

	@Override
	public String save(TenantBusinessRulesAddParam tenantBusinessRulesAddParam) {
		TenantBusinessRules tenantBusinessRules = TranslateUtil.translate(tenantBusinessRulesAddParam,
				TenantBusinessRules.class);
		if (tenantBusinessRules != null && StringUtils.isBlank(tenantBusinessRules.getId())) {
			tenantBusinessRules.setId(idService.selectId());
		}
		this.save(tenantBusinessRules);

		return tenantBusinessRules.getId();
	}

	@Override
	public boolean updateById(TenantBusinessRulesUpdateParam tenantBusinessRulesUpdateParam) {
		TenantBusinessRules tenantBusinessRules = TranslateUtil.translate(tenantBusinessRulesUpdateParam,
				TenantBusinessRules.class);

		return this.updateById(tenantBusinessRules);
	}
	
}
