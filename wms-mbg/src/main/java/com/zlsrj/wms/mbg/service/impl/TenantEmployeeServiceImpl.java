package com.zlsrj.wms.mbg.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.mbg.mapper.TenantEmployeeMapper;
import com.zlsrj.wms.mbg.service.ITenantEmployeeService;

@Service
public class TenantEmployeeServiceImpl extends ServiceImpl<TenantEmployeeMapper, TenantEmployee> implements ITenantEmployeeService {

}
