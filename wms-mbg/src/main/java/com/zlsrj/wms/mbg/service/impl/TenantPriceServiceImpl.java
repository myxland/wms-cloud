package com.zlsrj.wms.mbg.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.mbg.enums.PriceCalcTypeEnum;
import com.zlsrj.wms.mbg.enums.PriceItemEnum;
import com.zlsrj.wms.mbg.enums.PriceTypeEnum;
import com.zlsrj.wms.mbg.service.ITenantPriceDetailService;
import com.zlsrj.wms.mbg.service.ITenantPriceItemService;
import com.zlsrj.wms.mbg.service.ITenantPriceService;
import com.zlsrj.wms.mbg.service.ITenantPriceStepService;
import com.zlsrj.wms.mbg.service.ITenantPriceTypeService;

import cn.hutool.core.util.IdUtil;

@Service
public class TenantPriceServiceImpl implements ITenantPriceService {

	@Resource
	private ITenantPriceItemService tenantPriceItemService;

	@Resource
	private ITenantPriceTypeService tenantPriceTypeService;

	@Resource
	private ITenantPriceStepService tenantPriceStepService;

	@Resource
	private ITenantPriceDetailService tenantPriceDetailService;

	public boolean initByTenant(TenantInfo tenantInfo) {
		boolean success = false;

		Long waterPriceItemId = null;/* 水费 */
		Long denizenPriceTypeId = null;/* 居民 */

		for (PriceItemEnum i : PriceItemEnum.values()) {
			TenantPriceItem tenantPriceItem = TenantPriceItem.builder()//
					.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
					.tenantId(tenantInfo.getId())// 租户编号
					.priceItemName(i.getText())// 费用项目名称
					.taxRate(new BigDecimal(i.getRate()))// 税率
					.taxId(null)// 对应税控项目编号
					.build();

			tenantPriceItemService.getBaseMapper().insert(tenantPriceItem);

			if (tenantPriceItem.getPriceItemName().equals(PriceItemEnum.WATER.getText())) {
				waterPriceItemId = tenantPriceItem.getId();
			}
		}

		for (PriceTypeEnum t : PriceTypeEnum.values()) {
			TenantPriceType tenantPriceType = TenantPriceType.builder()//
					.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
					.tenantId(tenantInfo.getId())// 租户编号
					.priceTypeName(t.getText())// 价格类别名称
					.bottomOn(0)// 启用保底水量（启用/不启用）
					.bottomNum(null)// 保底水量
					.topOn(0)// 启用封顶水量（启用/不启用）
					.topNum(null)// 封顶水量
					.reduceOn(0)// 启用固定减免（启用/不启用）
					.reduceNum(null)// 固定减免水量
					.reduceLowerLimit(null)// 减免起始水量（当月多少吨以上才可以减免）
					.fixedOn(0)// 启用固定水量征收（启用/不启用）
					.fixedNum(null)// 固定征收水量
					.build();

			tenantPriceTypeService.getBaseMapper().insert(tenantPriceType);

			if (tenantPriceType.getPriceTypeName().equals(PriceTypeEnum.DENIZEN.getText())) {
				denizenPriceTypeId = tenantPriceType.getId();
			}
		}

		int[] range = new int[] { 0, 100, 200, 10000000 };
		for (int i = 0; i < range.length - 1; i++) {
			int min = range[i] + 1;
			int max = range[i + 1];

			TenantPriceStep tenantPriceStep = TenantPriceStep.builder()//
					.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
					.tenantId(tenantInfo.getId())// 租户编号
					.priceTypeId(denizenPriceTypeId)// 价格类别
					.priceItemId(waterPriceItemId)// 费用项目
					.stepId(new Long(i + 1))// 阶梯号
					.startNum(min)// 起始量
					.endNum(max)// 终止量
					.price(new BigDecimal(0))// 价格
					.build();

			tenantPriceStepService.getBaseMapper().insert(tenantPriceStep);

		}

		QueryWrapper<TenantPriceItem> queryWrapperTenantPriceItem = new QueryWrapper<TenantPriceItem>();
		queryWrapperTenantPriceItem.lambda()//
				.eq(TenantPriceItem::getTenantId, tenantInfo.getId());

		List<TenantPriceItem> tenantPriceItemList = tenantPriceItemService.list(queryWrapperTenantPriceItem);

		QueryWrapper<TenantPriceType> queryWrapperTenantPriceType = new QueryWrapper<TenantPriceType>();
		queryWrapperTenantPriceType.lambda()//
				.eq(TenantPriceType::getTenantId, tenantInfo.getId());

		List<TenantPriceType> tenantPriceTypeList = tenantPriceTypeService.list(queryWrapperTenantPriceType);

		if (tenantPriceItemList != null && tenantPriceItemList.size() > 0) {
			for (TenantPriceItem i : tenantPriceItemList) {
				if (tenantPriceTypeList != null && tenantPriceTypeList.size() > 0) {
					for (TenantPriceType t : tenantPriceTypeList) {
						TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
								.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
								.tenantId(tenantInfo.getId())// 租户编号
								.priceTypeId(t.getId())// 价格类别
								.priceItemId(i.getId())// 费用项目
								.calcType(PriceCalcTypeEnum.FIXED_RATE.getCode())// 计算方法（固定单价/固定金额/阶梯价格）
								.price(new BigDecimal(0))// 指定价格（金额）
								.build();

						tenantPriceDetailService.getBaseMapper().insert(tenantPriceDetail);
					}
				}
			}
		}

		success = true;
		return success;
	}
}
