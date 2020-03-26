package com.zlsrj.wms.saas.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantPriceItemAddParam;
import com.zlsrj.wms.api.dto.TenantPriceItemUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantPriceItemMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantPriceItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantPriceItemServiceImpl extends ServiceImpl<TenantPriceItemMapper, TenantPriceItem> implements ITenantPriceItemService {
	@Resource
	private IIdService idService;
	
	private final static int [] TENANT_PRICE_ITEM_CODE = new int[] {1,2,3};
	private final static String [] TENANT_PRICE_ITEM_NAME = new String[] {"水费","水资源费","污水费"};
	private final static BigDecimal [] TENANT_PRICE_ITEM_TAX_RATE = new BigDecimal[] {new BigDecimal(0.03),BigDecimal.ZERO,BigDecimal.ZERO};
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantPriceItem> queryWrapperTenantPriceItem = new QueryWrapper<TenantPriceItem>();
		queryWrapperTenantPriceItem.lambda()//
				.eq(TenantPriceItem::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantPriceItem);
		if (count > 0) {
			log.error("根据租户信息初始化收费项目失败，收费项目已存在。");
			return false;
		}

		List<TenantPriceItem> tenantPriceItemList = new ArrayList<TenantPriceItem>();

		for (int i = 0; i < TENANT_PRICE_ITEM_CODE.length; i++) {
			TenantPriceItem tenantPriceItem = TenantPriceItem.builder()//
					.id(idService.selectId())//
					.tenantId(tenantInfo.getId())// 租户ID
					.priceItemCode(TENANT_PRICE_ITEM_CODE[i])// 费用项目编码
					.priceItemName(TENANT_PRICE_ITEM_NAME[i])// 费用项目名称
					.priceItemTaxRate(TENANT_PRICE_ITEM_TAX_RATE[i])// 税率
					.priceItemTaxCode(null)// 税收分类编码
					.build();
			tenantPriceItemList.add(tenantPriceItem);
		}

		boolean success = this.saveBatch(tenantPriceItemList);

		return success;
	}
	
	@Override
	public boolean removeBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		QueryWrapper<TenantPriceItem> queryWrapperTenantPriceItem = new QueryWrapper<TenantPriceItem>();
		queryWrapperTenantPriceItem.lambda()//
				.eq(TenantPriceItem::getTenantId, tenantInfo.getId())//
		;
		success = this.remove(queryWrapperTenantPriceItem);
		
		return success;
	}

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
