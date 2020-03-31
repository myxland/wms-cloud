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
import com.zlsrj.wms.api.client.service.TenantCustomerArchivesClientService;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerArchivesVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户档案", tags = { "用户档案操作接口" })
@Controller
@RequestMapping("/tenantCustomerArchives")
@Slf4j
public class TenantCustomerArchivesController {

	@Autowired
	private TenantCustomerArchivesClientService tenantCustomerArchivesClientService;

	@ApiOperation(value = "新增用户档案")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantCustomerArchivesAddParam tenantCustomerArchivesAddParam) {
		String id = tenantCustomerArchivesClientService.save(tenantCustomerArchivesAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID删除用户档案")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCustomerArchivesClientService.removeById(id);

		return commonResult;
	}

	@ApiOperation(value = "根据ID查询用户档案")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerArchivesVo> getById(@PathVariable("id") String id) {
		TenantCustomerArchivesVo tenantCustomerArchivesVo = tenantCustomerArchivesClientService.getById(id);

		return CommonResult.success(tenantCustomerArchivesVo);
	}
	
	@ApiOperation(value = "根据参数查询用户档案列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantCustomerArchivesVo>> list(TenantCustomerArchivesQueryParam tenantCustomerArchivesQueryParam) {
		List<TenantCustomerArchivesVo> tenantCustomerArchivesVoList = tenantCustomerArchivesClientService.list(tenantCustomerArchivesQueryParam);

		return CommonResult.success(tenantCustomerArchivesVoList);
	}
	
	@ApiOperation(value = "根据参数查询用户档案数量")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> count(TenantCustomerArchivesQueryParam tenantCustomerArchivesQueryParam) {
		int count = tenantCustomerArchivesClientService.count(tenantCustomerArchivesQueryParam);

		return CommonResult.success(count);
	}
	
	@ApiOperation(value = "根据参数分页查询用户档案列表")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustomerArchivesVo>> page(TenantCustomerArchivesQueryParam tenantCustomerArchivesQueryParam,
	                @RequestParam(value = "page", defaultValue = "1") int page, //
	                @RequestParam(value = "rows", defaultValue = "10") int rows, //
	                @RequestParam(value = "sort", required = false) String sort, // 排序列字段名
	                @RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
	        Page<TenantCustomerArchivesVo> tenantCustomerArchivesVoPage = tenantCustomerArchivesClientService.page(tenantCustomerArchivesQueryParam, page, rows, sort, order);
	        CommonPage<TenantCustomerArchivesVo> tenantCustomerArchivesCommonPage = CommonPage.restPage(tenantCustomerArchivesVoPage);

	        return CommonResult.success(tenantCustomerArchivesCommonPage);
	}
	
	@ApiOperation(value = "根据参数更新用户档案信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantCustomerArchivesUpdateParam tenantCustomerArchivesUpdateParam) {
		boolean success = tenantCustomerArchivesClientService.updateById(id, tenantCustomerArchivesUpdateParam);

		return CommonResult.success(success);
	}

}
