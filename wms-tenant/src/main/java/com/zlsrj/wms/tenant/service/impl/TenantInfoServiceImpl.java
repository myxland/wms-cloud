package com.zlsrj.wms.tenant.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.tenant.mapper.TenantInfoMapper;
import com.zlsrj.wms.tenant.service.ITenantInfoService;

@Service
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements ITenantInfoService {

}
