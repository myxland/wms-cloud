package com.zlsrj.wms.mbg.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantAccount;
import com.zlsrj.wms.mbg.mapper.TenantAccountMapper;
import com.zlsrj.wms.mbg.service.ITenantAccountService;

import cn.hutool.core.util.IdUtil;

@Service
public class TenantAccountServiceImpl extends ServiceImpl<TenantAccountMapper, TenantAccount> implements ITenantAccountService {
	/**
	 * 初始化租户账户
	 * 
	 * @param tenantId
	 * @return
	 */
	public boolean initByTenant(Long tenantId) {
		TenantAccount tenantAccount = TenantAccount.builder()//
				.id(IdUtil.createSnowflake(1L, 1L).nextId())// 编号ID
				.tenantId(tenantId)// 租房编号
				.accountBalance(new BigDecimal(0))// 账户余额
				.build();
		boolean success = retBool(baseMapper.insert(tenantAccount));

		return success;
	}
}
