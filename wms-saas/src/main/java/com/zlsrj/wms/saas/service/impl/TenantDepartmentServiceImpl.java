package com.zlsrj.wms.saas.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantDepartment;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.mapper.TenantDepartmentMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantDepartmentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantDepartmentServiceImpl extends ServiceImpl<TenantDepartmentMapper, TenantDepartment> implements ITenantDepartmentService {
	@Resource
	private IIdService idService;

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		return false;
	}

}
