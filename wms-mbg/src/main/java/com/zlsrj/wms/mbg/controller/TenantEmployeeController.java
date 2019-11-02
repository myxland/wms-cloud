package com.zlsrj.wms.mbg.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.dto.TenantEmployeeQueryParam;
import com.zlsrj.wms.mbg.entity.TenantEmployee;
import com.zlsrj.wms.mbg.service.ITenantEmployeeService;

@RestController
@RequestMapping("/tenantEmployee")
public class TenantEmployeeController {

	@Autowired
	private ITenantEmployeeService tenantEmployeeService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantEmployee tenantEmployee = tenantEmployeeService.getById(id);

		return CommonResult.success(tenantEmployee);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantEmployee> tenantEmployeeList = tenantEmployeeService.list();

		return tenantEmployeeList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantEmployeeQueryParam tenantEmployeeQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantEmployee> page = new Page<TenantEmployee>(pageNum, pageSize);
		QueryWrapper<TenantEmployee> queryWrapperTenantEmployee = new QueryWrapper<TenantEmployee>();
		queryWrapperTenantEmployee.lambda()
				.eq(tenantEmployeeQueryParam.getId() != null, TenantEmployee::getId, tenantEmployeeQueryParam.getId())
				.eq(tenantEmployeeQueryParam.getTenantId() != null, TenantEmployee::getTenantId, tenantEmployeeQueryParam.getTenantId())
				.eq(tenantEmployeeQueryParam.getEmpName() != null, TenantEmployee::getEmpName, tenantEmployeeQueryParam.getEmpName())
				.eq(tenantEmployeeQueryParam.getEmpPassword() != null, TenantEmployee::getEmpPassword, tenantEmployeeQueryParam.getEmpPassword())
				.eq(tenantEmployeeQueryParam.getDeptId() != null, TenantEmployee::getDeptId, tenantEmployeeQueryParam.getDeptId())
				.eq(tenantEmployeeQueryParam.getLoginOn() != null, TenantEmployee::getLoginOn, tenantEmployeeQueryParam.getLoginOn())
				.eq(tenantEmployeeQueryParam.getEmpStatus() != null, TenantEmployee::getEmpStatus, tenantEmployeeQueryParam.getEmpStatus())
				.eq(tenantEmployeeQueryParam.getEmpMobile() != null, TenantEmployee::getEmpMobile, tenantEmployeeQueryParam.getEmpMobile())
				.eq(tenantEmployeeQueryParam.getEmpEmail() != null, TenantEmployee::getEmpEmail, tenantEmployeeQueryParam.getEmpEmail())
				.eq(tenantEmployeeQueryParam.getEmpPersonalWx() != null, TenantEmployee::getEmpPersonalWx, tenantEmployeeQueryParam.getEmpPersonalWx())
				.eq(tenantEmployeeQueryParam.getEmpEnterpriceWx() != null, TenantEmployee::getEmpEnterpriceWx, tenantEmployeeQueryParam.getEmpEnterpriceWx())
				;

		IPage<TenantEmployee> tenantEmployeePage = tenantEmployeeService.page(page, queryWrapperTenantEmployee);

		return CommonPage.restPage(tenantEmployeePage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantEmployee tenantEmployee) {
		boolean success = tenantEmployeeService.save(tenantEmployee);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantEmployee tenantEmployee) {
		boolean success = tenantEmployeeService.updateById(tenantEmployee);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update/{id}/status/{status}", method = RequestMethod.PUT)
	public Object updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
		UpdateWrapper<TenantEmployee> updateWrapperTenantEmployee = new UpdateWrapper<TenantEmployee>();
		updateWrapperTenantEmployee.lambda().set(TenantEmployee::getEmpStatus, status).eq(TenantEmployee::getId, id);
		boolean success = tenantEmployeeService.update(updateWrapperTenantEmployee);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantEmployeeService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantEmployeeService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
