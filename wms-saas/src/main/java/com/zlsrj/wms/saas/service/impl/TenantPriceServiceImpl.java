package com.zlsrj.wms.saas.service.impl;
import java.io.Serializable;
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
import com.zlsrj.wms.api.entity.TenantPrice;
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantPriceDetailMapper;
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
	private TenantPriceStepMapper tenantPriceStepMapper;

	@Override
	public TenantPrice getAggregation(Wrapper<TenantPrice> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	@Transactional
	public String save(TenantPriceAddParam tenantPriceAddParam) {
		//tenant_price
		//tenant_price_detail
		//tenant_price_step
		TenantPrice tenantPrice = TranslateUtil.translate(tenantPriceAddParam,
				TenantPrice.class);
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
		if(tenantPriceDetailAddParamList!=null && tenantPriceDetailAddParamList.size()>0) {
			for(TenantPriceDetailAddParam tenantPriceDetailAddParam : tenantPriceDetailAddParamList) {
				TenantPriceDetail tenantPriceDetail = TranslateUtil.translate(tenantPriceDetailAddParam,TenantPriceDetail.class);
				if (tenantPriceDetail != null && StringUtils.isBlank(tenantPriceDetail.getId())) {
					tenantPriceDetail.setId(idService.selectId());
				}
				if (tenantPriceDetail != null && StringUtils.isBlank(tenantPriceDetail.getTenantId())) {
					tenantPriceDetail.setTenantId(tenantId);
				}
				tenantPriceDetail.setPriceId(id);
				tenantPriceDetailMapper.insert(tenantPriceDetail);
				String priceDetailId = tenantPriceDetail.getId();
				
				List<TenantPriceStepAddParam> tenantPriceStepAddParamList = tenantPriceDetailAddParam.getTenantPriceStepList();
				if(tenantPriceStepAddParamList!=null && tenantPriceStepAddParamList.size()>0) {
					for(TenantPriceStepAddParam tenantPriceStepAddParam : tenantPriceStepAddParamList) {
						TenantPriceStep tenantPriceStep = TranslateUtil.translate(tenantPriceStepAddParam,TenantPriceStep.class);
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
		TenantPrice tenantPrice = TranslateUtil.translate(tenantPriceUpdateParam,
				TenantPrice.class);

		this.updateById(tenantPrice);
		String id = tenantPrice.getId();
		String tenantId = tenantPrice.getTenantId();
		
		tenantPriceStepMapper.deleteByPriceId(id.toString());
		
		QueryWrapper<TenantPriceDetail> wrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		wrapperTenantPriceDetail.lambda().eq(TenantPriceDetail::getPriceId, id);
		tenantPriceDetailMapper.delete(wrapperTenantPriceDetail);
		
		List<TenantPriceDetailUpdateParam> tenantPriceDetailUpdateParamList = tenantPriceUpdateParam.getTenantPriceDetailList();
		if(tenantPriceDetailUpdateParamList!=null && tenantPriceDetailUpdateParamList.size()>0) {
			for(TenantPriceDetailUpdateParam tenantPriceDetailUpdateParam : tenantPriceDetailUpdateParamList) {
				TenantPriceDetail tenantPriceDetail = TranslateUtil.translate(tenantPriceDetailUpdateParam,TenantPriceDetail.class);
				if (tenantPriceDetail != null && StringUtils.isBlank(tenantPriceDetail.getId())) {
					tenantPriceDetail.setId(idService.selectId());
				}
				if (tenantPriceDetail != null && StringUtils.isBlank(tenantPriceDetail.getTenantId())) {
					tenantPriceDetail.setTenantId(tenantId);
				}
				tenantPriceDetail.setPriceId(id);
				tenantPriceDetailMapper.insert(tenantPriceDetail);
				String priceDetailId = tenantPriceDetail.getId();
				
				List<TenantPriceStepUpdateParam> tenantPriceStepUpdateParamList = tenantPriceDetailUpdateParam.getTenantPriceStepList();
				if(tenantPriceStepUpdateParamList!=null && tenantPriceStepUpdateParamList.size()>0) {
					for(TenantPriceStepUpdateParam tenantPriceStepUpdateParam : tenantPriceStepUpdateParamList) {
						TenantPriceStep tenantPriceStep = TranslateUtil.translate(tenantPriceStepUpdateParam,TenantPriceStep.class);
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
		//删除tenantPriceStep
		//删除tenantPriceDetail
		//删除tenantPrice
		
		tenantPriceStepMapper.deleteByPriceId(id.toString());
		
		QueryWrapper<TenantPriceDetail> wrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		wrapperTenantPriceDetail.lambda().eq(TenantPriceDetail::getPriceId, id);
		tenantPriceDetailMapper.delete(wrapperTenantPriceDetail);
		
		super.removeById(id);
		
		success = true;
		return success;
	}
}
