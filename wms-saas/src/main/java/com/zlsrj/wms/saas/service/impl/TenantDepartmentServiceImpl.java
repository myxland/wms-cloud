package com.zlsrj.wms.saas.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantDepartmentAddParam;
import com.zlsrj.wms.api.dto.TenantDepartmentUpdateParam;
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
	
	@Override
	public boolean updateById(TenantDepartmentUpdateParam tenantDepartmentUpdateParam) {
		String jsonString = JSON.toJSONString(tenantDepartmentUpdateParam);
		TenantDepartment tenantDepartment = JSON.parseObject(jsonString, TenantDepartment.class);
		
		return this.updateById(tenantDepartment);
	}
	
	@Override
	public String save(TenantDepartmentAddParam tenantDepartmentAddParam) {
		String jsonString = JSON.toJSONString(tenantDepartmentAddParam);
		TenantDepartment tenantDepartment = JSON.parseObject(jsonString, TenantDepartment.class);
		
		if (tenantDepartment.getId() == null || tenantDepartment.getId().trim().length() == 0) {
			tenantDepartment.setId(idService.selectId());
		}
		
		this.save(tenantDepartment);
		
		return tenantDepartment.getId();
	}

}
