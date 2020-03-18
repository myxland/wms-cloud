package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantPriceItemAddParam;
import com.zlsrj.wms.api.dto.TenantPriceItemUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantPriceItemMapper;
import com.zlsrj.wms.saas.service.ITenantPriceItemService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantPriceItemServiceImpl extends ServiceImpl<TenantPriceItemMapper, TenantPriceItem> implements ITenantPriceItemService {
	@Resource
	private IIdService idService;

	@Override
	public TenantPriceItem getAggregation(Wrapper<TenantPriceItem> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	public String save(TenantPriceItemAddParam tenantPriceItemAddParam) {
		TenantPriceItem tenantPriceItem = TranslateUtil.translate(tenantPriceItemAddParam,
				TenantPriceItem.class);
		if (tenantPriceItem != null && StringUtils.isBlank(tenantPriceItem.getId())) {
			tenantPriceItem.setId(idService.selectId());
		}
		this.save(tenantPriceItem);

		return tenantPriceItem.getId();
	}

	@Override
	public boolean updateById(TenantPriceItemUpdateParam tenantPriceItemUpdateParam) {
		TenantPriceItem tenantPriceItem = TranslateUtil.translate(tenantPriceItemUpdateParam,
				TenantPriceItem.class);

		return this.updateById(tenantPriceItem);
	}
	
}
