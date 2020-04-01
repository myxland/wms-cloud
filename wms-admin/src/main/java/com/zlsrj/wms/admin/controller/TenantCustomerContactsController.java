package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantCustomerContactsClientService;
import com.zlsrj.wms.api.dto.TenantCustomerContactsAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerContactsVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户联系方式", tags = { "用户联系方式操作接口" })
@Controller
@RequestMapping("/tenantCustomerContacts")
@Slf4j
public class TenantCustomerContactsController {

	@Autowired
	private TenantCustomerContactsClientService tenantCustomerContactsClientService;

	@ApiOperation(value = "新增用户联系方式")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantCustomerContactsAddParam tenantCustomerContactsAddParam) {
		String id = tenantCustomerContactsClientService.save(tenantCustomerContactsAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID删除用户联系方式")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCustomerContactsClientService.removeById(id);

		return commonResult;
	}

	@ApiOperation(value = "根据ID查询用户联系方式")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerContactsVo> getById(@PathVariable("id") String id) {
		TenantCustomerContactsVo tenantCustomerContactsVo = tenantCustomerContactsClientService.getById(id);

		return CommonResult.success(tenantCustomerContactsVo);
	}
	
	@ApiOperation(value = "根据参数查询用户联系方式列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantCustomerContactsVo>> list(TenantCustomerContactsQueryParam tenantCustomerContactsQueryParam) {
		List<TenantCustomerContactsVo> tenantCustomerContactsVoList = tenantCustomerContactsClientService.list(tenantCustomerContactsQueryParam);

		return CommonResult.success(tenantCustomerContactsVoList);
	}
	
	@ApiOperation(value = "根据参数查询用户联系方式数量")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> count(TenantCustomerContactsQueryParam tenantCustomerContactsQueryParam) {
		int count = tenantCustomerContactsClientService.count(tenantCustomerContactsQueryParam);

		return CommonResult.success(count);
	}
	
	@ApiOperation(value = "根据参数分页查询用户联系方式列表")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustomerContactsVo>> page(TenantCustomerContactsQueryParam tenantCustomerContactsQueryParam,
	                @RequestParam(value = "page", defaultValue = "1") int page, //
	                @RequestParam(value = "rows", defaultValue = "10") int rows, //
	                @RequestParam(value = "sort", required = false) String sort, // 排序列字段名
	                @RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
	        Page<TenantCustomerContactsVo> tenantCustomerContactsVoPage = tenantCustomerContactsClientService.page(tenantCustomerContactsQueryParam, page, rows, sort, order);
	        CommonPage<TenantCustomerContactsVo> tenantCustomerContactsCommonPage = CommonPage.restPage(tenantCustomerContactsVoPage);

	        return CommonResult.success(tenantCustomerContactsCommonPage);
	}
	
	@ApiOperation(value = "根据参数更新用户联系方式信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantCustomerContactsUpdateParam tenantCustomerContactsUpdateParam) {
		boolean success = tenantCustomerContactsClientService.updateById(id, tenantCustomerContactsUpdateParam);

		return CommonResult.success(success);
	}

}
