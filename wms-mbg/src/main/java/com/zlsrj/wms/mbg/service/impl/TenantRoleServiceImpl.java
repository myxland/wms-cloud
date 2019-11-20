package com.zlsrj.wms.mbg.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.mbg.mapper.TenantRoleMapper;
import com.zlsrj.wms.mbg.service.ITenantRoleService;

@Service
public class TenantRoleServiceImpl extends ServiceImpl<TenantRoleMapper, TenantRole> implements ITenantRoleService {

}
