package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.TenantCustomerClientService;
import com.zlsrj.wms.api.dto.TenantCustomerAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户信息", tags = { "用户信息操作接口" })
@Controller
@RequestMapping("/tenantCustomer")
@Slf4j
public class TenantCustomerController {

	@Autowired
	private TenantCustomerClientService tenantCustomerClientService;

	@ApiOperation(value = "新增用户信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantCustomerAddParam tenantCustomerAddParam) {
		String id = tenantCustomerClientService.save(tenantCustomerAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID删除用户信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCustomerClientService.removeById(id);

		return commonResult;
	}

	@ApiOperation(value = "根据ID查询用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerVo> getById(@PathVariable("id") String id) {
		TenantCustomerVo tenantCustomerVo = tenantCustomerClientService.getById(id);

		return CommonResult.success(tenantCustomerVo);
	}
	
	@ApiOperation(value = "根据参数查询用户信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantCustomerVo>> list(TenantCustomerQueryParam tenantCustomerQueryParam) {
		List<TenantCustomerVo> tenantCustomerVoList = tenantCustomerClientService.list(tenantCustomerQueryParam);

		return CommonResult.success(tenantCustomerVoList);
	}
	
	@ApiOperation(value = "根据参数查询用户信息数量")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> count(TenantCustomerQueryParam tenantCustomerQueryParam) {
		int count = tenantCustomerClientService.count(tenantCustomerQueryParam);

		return CommonResult.success(count);
	}
	
	@ApiOperation(value = "根据参数更新用户信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantCustomerUpdateParam tenantCustomerUpdateParam) {
		boolean success = tenantCustomerClientService.updateById(id, tenantCustomerUpdateParam);

		return CommonResult.success(success);
	}

}
