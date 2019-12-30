package com.zlsrj.wms.saas.service.impl;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantConsumptionBill;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.mapper.TenantConsumptionBillMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantConsumptionBillService;
import com.zlsrj.wms.saas.service.ITenantInfoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantConsumptionBillServiceImpl extends ServiceImpl<TenantConsumptionBillMapper, TenantConsumptionBill> implements ITenantConsumptionBillService {
	@Resource
	private IIdService idService;
	
	@Resource
	private ITenantInfoService tenantInfoService; 

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		return false;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(TenantConsumptionBill tenantConsumptionBill) {
		boolean success = false;
		TenantInfo tenantInfo = tenantInfoService.getById(tenantConsumptionBill.getTenantId());
		
		BigDecimal tenantBalance = tenantInfo.getTenantBalance();
		
		if(tenantConsumptionBill.getConsumptionBillType() == 1) {
			//充值
			tenantBalance = tenantBalance.add(tenantConsumptionBill.getConsumptionBillMoney());
		} else if(tenantConsumptionBill.getConsumptionBillType() == 2) {
			//消费
			tenantBalance = tenantBalance.subtract(tenantConsumptionBill.getConsumptionBillMoney());
			
			if(tenantBalance.compareTo(BigDecimal.ZERO)<0) {
				throw new RuntimeException("余额为负数");
			}
		}
		
		TenantInfo tenantInfoWhere = TenantInfo.builder()//
				.id(tenantInfo.getId())//
				.build();
		UpdateWrapper<TenantInfo> updateWrapperTenantInfo = new UpdateWrapper<TenantInfo>();
		updateWrapperTenantInfo.setEntity(tenantInfoWhere);
		updateWrapperTenantInfo.lambda()//
				.set(TenantInfo::getTenantBalance, tenantBalance)
				;

		success = tenantInfoService.update(updateWrapperTenantInfo);
		
		if(success== false) {
			throw new RuntimeException("更新租户余额失败");
		}
		
		tenantConsumptionBill.setTenantBalance(tenantBalance);
		success = super.save(tenantConsumptionBill);
		return success;
	}

}
