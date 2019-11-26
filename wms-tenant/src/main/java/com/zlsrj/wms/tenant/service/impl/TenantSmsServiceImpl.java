package com.zlsrj.wms.tenant.service.impl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantSms;
import com.zlsrj.wms.tenant.mapper.TenantSmsMapper;
import com.zlsrj.wms.tenant.service.ITenantSmsService;

@Service
public class TenantSmsServiceImpl extends ServiceImpl<TenantSmsMapper, TenantSms> implements ITenantSmsService {
}
