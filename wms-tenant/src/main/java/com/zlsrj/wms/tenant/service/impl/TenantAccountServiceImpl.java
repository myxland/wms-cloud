package com.zlsrj.wms.tenant.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantAccount;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.tenant.mapper.TenantAccountMapper;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantAccountService;

@Service
public class TenantAccountServiceImpl extends ServiceImpl<TenantAccountMapper, TenantAccount>
		implements ITenantAccountService {
	@Resource
	private IIdService idService;

	public boolean saveByTenantInfo(TenantInfo tenantInfo) {
		TenantAccount tenantAccount = TenantAccount.builder()//
				.id(idService.selectId())//
				.tenantId(tenantInfo.getId())//
				.accountBalance(BigDecimal.ZERO)//
				.build();

		return super.save(tenantAccount);
	}
}
