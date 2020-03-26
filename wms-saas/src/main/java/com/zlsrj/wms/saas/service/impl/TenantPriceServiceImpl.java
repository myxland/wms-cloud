package com.zlsrj.wms.saas.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantPriceAddParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailAddParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailUpdateParam;
import com.zlsrj.wms.api.dto.TenantPriceStepAddParam;
import com.zlsrj.wms.api.dto.TenantPriceStepUpdateParam;
import com.zlsrj.wms.api.dto.TenantPriceUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPrice;
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantPriceDetailMapper;
import com.zlsrj.wms.saas.mapper.TenantPriceItemMapper;
import com.zlsrj.wms.saas.mapper.TenantPriceMapper;
import com.zlsrj.wms.saas.mapper.TenantPriceStepMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantPriceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantPriceServiceImpl extends ServiceImpl<TenantPriceMapper, TenantPrice> implements ITenantPriceService {
	@Resource
	private IIdService idService;

	@Resource
	private TenantPriceDetailMapper tenantPriceDetailMapper;
	
	@Resource
	private TenantPriceItemMapper tenantPriceItemMapper;

	@Resource
	private TenantPriceStepMapper tenantPriceStepMapper;

	private final static String[] PRICE_NAME = new String[] { "居民价格", "居民阶梯价格", "非居民价格", "特种行业价格" };
	
	private final static int[] PRICE_STEP_CLASS = new int[] { 1,2,3 };
	private final static BigDecimal[] PRICE_STEP_START_CODE = new BigDecimal[] { new BigDecimal(1), new BigDecimal(201), new BigDecimal(401)};
	private final static BigDecimal[] PRICE_STEP_END_CODE = new BigDecimal[] { new BigDecimal(200), new BigDecimal(400), new BigDecimal(600)};

	@Override
	public TenantPrice getAggregation(Wrapper<TenantPrice> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}

	@Override
	@Transactional
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		QueryWrapper<TenantPrice> queryWrapperTenantPrice = new QueryWrapper<TenantPrice>();
		queryWrapperTenantPrice.lambda()//
				.eq(TenantPrice::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantPrice);
		if (count > 0) {
			log.error("根据租户信息初始化价格信息失败，价格信息已存在。");
			return false;
		}

		TenantPrice tenantPrice = TenantPrice.builder()//
				.id(idService.selectId())//
				.tenantId(tenantInfo.getId())// 租户ID
				.priceOrder(1)// 排序
				.priceName(tenantInfo.getTenantName())// 水价名称
				.priceParentId("")// 父级ID
				.priceVersion(null)// 水价版本
				.priceVersionMemo(null)// 版本说明
				.marketingAreaId(null)// 营销区域
				.priceMemo(null)// 备注
				.build();
		this.save(tenantPrice);

		String parentId = tenantPrice.getId();
		
		QueryWrapper<TenantPriceItem> queryWrapperTenantPriceItem = new QueryWrapper<TenantPriceItem>();
		queryWrapperTenantPriceItem.lambda()//
				.eq(TenantPriceItem::getTenantId, tenantInfo.getId())//
		;
		List<TenantPriceItem> tenantPriceItemList = tenantPriceItemMapper.selectList(queryWrapperTenantPriceItem);

		// 默认增加四种水价：居民价格、居民阶梯价格、非居民价格、特种行业价格
		for (int i = 0; i < PRICE_NAME.length; i++) {
			tenantPrice = TenantPrice.builder()//
					.id(idService.selectId())//
					.tenantId(tenantInfo.getId())// 租户ID
					.priceOrder(1)// 排序
					.priceName(PRICE_NAME[i])// 水价名称
					.priceParentId(parentId)// 父级ID
					.priceVersion(null)// 水价版本
					.priceVersionMemo(null)// 版本说明
					.marketingAreaId(null)// 营销区域
					.priceMemo(null)// 备注
					.build();
			this.save(tenantPrice);
			
			//tenantPriceDetail
			if(tenantPriceItemList!=null && tenantPriceItemList.size()>0) {
				for(TenantPriceItem tenantPriceItem: tenantPriceItemList) {
					TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
							.id(idService.selectId())// 水价明细ID
							.tenantId(tenantInfo.getId())// 租户ID
							.priceId(tenantPrice.getId())// 水表列表ID
							.priceItemId(tenantPriceItem.getId())// 费用项目
							.priceRule(1)// 计费规则
							.detailPrice(BigDecimal.ZERO)// 单价
							.build();
						
					tenantPriceDetailMapper.insert(tenantPriceDetail);
				}
			}
			
		}
		
		
		//阶梯水价
		Integer priceStepOn = tenantInfo.getPriceStepOn();
		if(1== priceStepOn) {
			tenantPrice = TenantPrice.builder()//
					.id(idService.selectId())//
					.tenantId(tenantInfo.getId())// 租户ID
					.priceOrder(1)// 排序
					.priceName("居民阶梯价格")// 水价名称
					.priceParentId(parentId)// 父级ID
					.priceVersion(null)// 水价版本
					.priceVersionMemo(null)// 版本说明
					.marketingAreaId(null)// 营销区域
					.priceMemo(null)// 备注
					.build();
			this.save(tenantPrice);
			
			if(tenantPriceItemList!=null && tenantPriceItemList.size()>0) {
				for(TenantPriceItem tenantPriceItem: tenantPriceItemList) {
					TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
							.id(idService.selectId())// 水价明细ID
							.tenantId(tenantInfo.getId())// 租户ID
							.priceId(tenantPrice.getId())// 水表列表ID
							.priceItemId(tenantPriceItem.getId())// 费用项目
							.priceRule(4)// 计费规则
							.detailPrice(BigDecimal.ZERO)// 单价
							.build();
						
					tenantPriceDetailMapper.insert(tenantPriceDetail);
					
					for(int i=0;i<PRICE_STEP_CLASS.length;i++) {
						TenantPriceStep tenantPriceStep = TenantPriceStep.builder()//
								.id(idService.selectId())// 阶梯明细ID
								.tenantId(tenantInfo.getId())// 租户ID
								.priceDetailId(tenantPriceDetail.getId())// 水价明细ID
								.stepClass(PRICE_STEP_CLASS[i])// 阶梯级次
								.startCode(PRICE_STEP_START_CODE[i])// 阶梯起始量
								.endCode(PRICE_STEP_END_CODE[i])// 阶梯终止量
								.stepPrice(BigDecimal.ZERO)// 单价
								.stepUsers(4)// 标准用水人数
								.stepUsersAdd(BigDecimal.ZERO)// 超人数增补量
								.build();
							
						tenantPriceStepMapper.insert(tenantPriceStep);
					}
				}
			}
		}

		success = true;

		return success;
	}
	
	@Override
	@Transactional
	public boolean removeBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		
		QueryWrapper<TenantPriceStep> queryWrapperTenantPriceStep = new QueryWrapper<TenantPriceStep>();
		queryWrapperTenantPriceStep.lambda()//
				.eq(TenantPriceStep::getTenantId, tenantInfo.getId())//
		;
		
		tenantPriceStepMapper.delete(queryWrapperTenantPriceStep);
		
		QueryWrapper<TenantPriceDetail> queryWrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		queryWrapperTenantPriceDetail.lambda()//
				.eq(TenantPriceDetail::getTenantId, tenantInfo.getId())//
		;
		
		tenantPriceDetailMapper.delete(queryWrapperTenantPriceDetail);
		
		QueryWrapper<TenantPrice> queryWrapperTenantPrice = new QueryWrapper<TenantPrice>();
		queryWrapperTenantPrice.lambda()//
				.eq(TenantPrice::getTenantId, tenantInfo.getId())//
		;
		success = this.remove(queryWrapperTenantPrice);
		
		return success;
	}

	@Override
	@Transactional
	public String save(TenantPriceAddParam tenantPriceAddParam) {
		// tenant_price
		// tenant_price_detail
		// tenant_price_step
		TenantPrice tenantPrice = TranslateUtil.translate(tenantPriceAddParam, TenantPrice.class);
		if (tenantPrice != null && StringUtils.isBlank(tenantPrice.getId())) {
			tenantPrice.setId(idService.selectId());
		}
		this.save(tenantPrice);
		String id = tenantPrice.getId();
		String tenantId = tenantPrice.getTenantId();

		tenantPriceStepMapper.deleteByPriceId(id.toString());

		QueryWrapper<TenantPriceDetail> wrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		wrapperTenantPriceDetail.lambda().eq(TenantPriceDetail::getPriceId, id);
		tenantPriceDetailMapper.delete(wrapperTenantPriceDetail);

		List<TenantPriceDetailAddParam> tenantPriceDetailAddParamList = tenantPriceAddParam.getTenantPriceDetailList();
		if (tenantPriceDetailAddParamList != null && tenantPriceDetailAddParamList.size() > 0) {
			for (TenantPriceDetailAddParam tenantPriceDetailAddParam : tenantPriceDetailAddParamList) {
				TenantPriceDetail tenantPriceDetail = TranslateUtil.translate(tenantPriceDetailAddParam,
						TenantPriceDetail.class);
				if (tenantPriceDetail != null && StringUtils.isBlank(tenantPriceDetail.getId())) {
					tenantPriceDetail.setId(idService.selectId());
				}
				if (tenantPriceDetail != null && StringUtils.isBlank(tenantPriceDetail.getTenantId())) {
					tenantPriceDetail.setTenantId(tenantId);
				}
				tenantPriceDetail.setPriceId(id);
				tenantPriceDetailMapper.insert(tenantPriceDetail);
				String priceDetailId = tenantPriceDetail.getId();

				List<TenantPriceStepAddParam> tenantPriceStepAddParamList = tenantPriceDetailAddParam
						.getTenantPriceStepList();
				if (tenantPriceStepAddParamList != null && tenantPriceStepAddParamList.size() > 0) {
					for (TenantPriceStepAddParam tenantPriceStepAddParam : tenantPriceStepAddParamList) {
						TenantPriceStep tenantPriceStep = TranslateUtil.translate(tenantPriceStepAddParam,
								TenantPriceStep.class);
						if (tenantPriceStep != null && StringUtils.isBlank(tenantPriceStep.getId())) {
							tenantPriceStep.setId(idService.selectId());
						}
						if (tenantPriceStep != null && StringUtils.isBlank(tenantPriceStep.getTenantId())) {
							tenantPriceStep.setTenantId(tenantId);
						}
						tenantPriceStep.setPriceDetailId(priceDetailId);
						tenantPriceStepMapper.insert(tenantPriceStep);

					}
				}
			}
		}

		return tenantPrice.getId();
	}

	@Override
	@Transactional
	public boolean updateById(TenantPriceUpdateParam tenantPriceUpdateParam) {
		boolean success = false;
		TenantPrice tenantPrice = TranslateUtil.translate(tenantPriceUpdateParam, TenantPrice.class);

		this.updateById(tenantPrice);
		String id = tenantPrice.getId();
		String tenantId = tenantPrice.getTenantId();

		tenantPriceStepMapper.deleteByPriceId(id.toString());

		QueryWrapper<TenantPriceDetail> wrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		wrapperTenantPriceDetail.lambda().eq(TenantPriceDetail::getPriceId, id);
		tenantPriceDetailMapper.delete(wrapperTenantPriceDetail);

		List<TenantPriceDetailUpdateParam> tenantPriceDetailUpdateParamList = tenantPriceUpdateParam
				.getTenantPriceDetailList();
		if (tenantPriceDetailUpdateParamList != null && tenantPriceDetailUpdateParamList.size() > 0) {
			for (TenantPriceDetailUpdateParam tenantPriceDetailUpdateParam : tenantPriceDetailUpdateParamList) {
				TenantPriceDetail tenantPriceDetail = TranslateUtil.translate(tenantPriceDetailUpdateParam,
						TenantPriceDetail.class);
				if (tenantPriceDetail != null && StringUtils.isBlank(tenantPriceDetail.getId())) {
					tenantPriceDetail.setId(idService.selectId());
				}
				if (tenantPriceDetail != null && StringUtils.isBlank(tenantPriceDetail.getTenantId())) {
					tenantPriceDetail.setTenantId(tenantId);
				}
				tenantPriceDetail.setPriceId(id);
				tenantPriceDetailMapper.insert(tenantPriceDetail);
				String priceDetailId = tenantPriceDetail.getId();

				List<TenantPriceStepUpdateParam> tenantPriceStepUpdateParamList = tenantPriceDetailUpdateParam
						.getTenantPriceStepList();
				if (tenantPriceStepUpdateParamList != null && tenantPriceStepUpdateParamList.size() > 0) {
					for (TenantPriceStepUpdateParam tenantPriceStepUpdateParam : tenantPriceStepUpdateParamList) {
						TenantPriceStep tenantPriceStep = TranslateUtil.translate(tenantPriceStepUpdateParam,
								TenantPriceStep.class);
						if (tenantPriceStep != null && StringUtils.isBlank(tenantPriceStep.getId())) {
							tenantPriceStep.setId(idService.selectId());
						}
						if (tenantPriceStep != null && StringUtils.isBlank(tenantPriceStep.getTenantId())) {
							tenantPriceStep.setTenantId(tenantId);
						}
						tenantPriceStep.setPriceDetailId(priceDetailId);
						tenantPriceStepMapper.insert(tenantPriceStep);

					}
				}
			}
		}

		success = true;
		return success;
	}

	@Override
	@Transactional
	public boolean removeById(Serializable id) {
		boolean success = false;
		// 删除tenantPriceStep
		// 删除tenantPriceDetail
		// 删除tenantPrice

		tenantPriceStepMapper.deleteByPriceId(id.toString());

		QueryWrapper<TenantPriceDetail> wrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		wrapperTenantPriceDetail.lambda().eq(TenantPriceDetail::getPriceId, id);
		tenantPriceDetailMapper.delete(wrapperTenantPriceDetail);

		super.removeById(id);

		success = true;
		return success;
	}
}
