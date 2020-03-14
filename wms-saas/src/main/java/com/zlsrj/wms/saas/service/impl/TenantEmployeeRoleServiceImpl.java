package com.zlsrj.wms.saas.service.impl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.saas.mapper.TenantEmployeeRoleMapper;
import com.zlsrj.wms.saas.service.ITenantEmployeeRoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantEmployeeRoleServiceImpl extends ServiceImpl<TenantEmployeeRoleMapper, TenantEmployeeRole> implements ITenantEmployeeRoleService {

}
