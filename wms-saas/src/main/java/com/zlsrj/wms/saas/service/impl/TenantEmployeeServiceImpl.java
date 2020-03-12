package com.zlsrj.wms.saas.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.saas.mapper.TenantEmployeeMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantEmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantEmployeeServiceImpl extends ServiceImpl<TenantEmployeeMapper, TenantEmployee> implements ITenantEmployeeService {
	@Resource
	private IIdService idService;

	@Override
	public boolean save(TenantEmployeeAddParam tenantEmployeeAddParam) {
		boolean success = false;
		String jsonString = JSON.toJSONString(tenantEmployeeAddParam);
		TenantEmployee tenantEmployee = JSON.parseObject(jsonString, TenantEmployee.class);
		if(tenantEmployee.getId()==null || tenantEmployee.getId().trim().length()==0) {
			tenantEmployee.setId(idService.selectId());
		}
		tenantEmployee.setEmployeePassword("123456");
		success = this.save(tenantEmployee);
		
		return success;
	}
}
