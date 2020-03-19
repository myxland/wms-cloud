package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantBusinessRulesAddParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesUpdateParam;
import com.zlsrj.wms.api.entity.TenantBusinessRules;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantBusinessRulesMapper;
import com.zlsrj.wms.saas.service.ITenantBusinessRulesService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantBusinessRulesServiceImpl extends ServiceImpl<TenantBusinessRulesMapper, TenantBusinessRules> implements ITenantBusinessRulesService {
	@Resource
	private IIdService idService;

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
