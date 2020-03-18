package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantPriceRuleAddParam;
import com.zlsrj.wms.api.dto.TenantPriceRuleUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceRule;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantPriceRuleMapper;
import com.zlsrj.wms.saas.service.ITenantPriceRuleService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantPriceRuleServiceImpl extends ServiceImpl<TenantPriceRuleMapper, TenantPriceRule> implements ITenantPriceRuleService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantPriceRuleAddParam tenantPriceRuleAddParam) {
		TenantPriceRule tenantPriceRule = TranslateUtil.translate(tenantPriceRuleAddParam,
				TenantPriceRule.class);
		if (tenantPriceRule != null && StringUtils.isBlank(tenantPriceRule.getId())) {
			tenantPriceRule.setId(idService.selectId());
		}
		this.save(tenantPriceRule);

		return tenantPriceRule.getId();
	}

	@Override
	public boolean updateById(TenantPriceRuleUpdateParam tenantPriceRuleUpdateParam) {
		TenantPriceRule tenantPriceRule = TranslateUtil.translate(tenantPriceRuleUpdateParam,
				TenantPriceRule.class);

		return this.updateById(tenantPriceRule);
	}
	
}
