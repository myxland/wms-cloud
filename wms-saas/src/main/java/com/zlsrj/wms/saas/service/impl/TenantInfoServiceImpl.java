package com.zlsrj.wms.saas.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;
import com.zlsrj.wms.saas.service.ITenantInfoService;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

@Service
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements ITenantInfoService {
	@Override
	public boolean save(TenantInfo tenantInfo) {
		//账户余额
		if(tenantInfo.getTenantBalance() == null) {
			tenantInfo.setTenantBalance(BigDecimal.ZERO);
		}
		//注册时间
		if(tenantInfo.getTenantRegisterTime() == null) {
			tenantInfo.setTenantRegisterTime(new DateTime());
		}
		
		return super.save(tenantInfo);
	}
}
