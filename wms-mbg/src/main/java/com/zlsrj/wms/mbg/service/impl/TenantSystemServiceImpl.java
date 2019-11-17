package com.zlsrj.wms.mbg.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantSystem;
import com.zlsrj.wms.mbg.mapper.TenantSystemMapper;
import com.zlsrj.wms.mbg.service.ITenantSystemService;

@Service
public class TenantSystemServiceImpl extends ServiceImpl<TenantSystemMapper, TenantSystem> implements ITenantSystemService {

}
