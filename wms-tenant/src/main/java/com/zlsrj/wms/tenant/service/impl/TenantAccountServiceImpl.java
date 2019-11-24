package com.zlsrj.wms.tenant.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantAccount;
import com.zlsrj.wms.tenant.mapper.TenantAccountMapper;
import com.zlsrj.wms.tenant.service.ITenantAccountService;

@Service
public class TenantAccountServiceImpl extends ServiceImpl<TenantAccountMapper, TenantAccount> implements ITenantAccountService {

}
