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
import com.zlsrj.wms.api.client.service.TenantCustomerStatementClientService;
import com.zlsrj.wms.api.dto.TenantCustomerStatementAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerStatementVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户结算信息", tags = { "用户结算信息操作接口" })
@Controller
@RequestMapping("/tenantCustomerStatement")
@Slf4j
public class TenantCustomerStatementController {

	@Autowired
	private TenantCustomerStatementClientService tenantCustomerStatementClientService;

	@ApiOperation(value = "新增用户结算信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantCustomerStatementAddParam tenantCustomerStatementAddParam) {
		String id = tenantCustomerStatementClientService.save(tenantCustomerStatementAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID删除用户结算信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCustomerStatementClientService.removeById(id);

		return commonResult;
	}

	@ApiOperation(value = "根据ID查询用户结算信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerStatementVo> getById(@PathVariable("id") String id) {
		TenantCustomerStatementVo tenantCustomerStatementVo = tenantCustomerStatementClientService.getById(id);

		return CommonResult.success(tenantCustomerStatementVo);
	}
	
	@ApiOperation(value = "根据参数查询用户结算信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantCustomerStatementVo>> list(TenantCustomerStatementQueryParam tenantCustomerStatementQueryParam) {
		List<TenantCustomerStatementVo> tenantCustomerStatementVoList = tenantCustomerStatementClientService.list(tenantCustomerStatementQueryParam);

		return CommonResult.success(tenantCustomerStatementVoList);
	}
	
	@ApiOperation(value = "根据参数查询用户结算信息数量")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> count(TenantCustomerStatementQueryParam tenantCustomerStatementQueryParam) {
		int count = tenantCustomerStatementClientService.count(tenantCustomerStatementQueryParam);

		return CommonResult.success(count);
	}
	
	@ApiOperation(value = "根据参数分页查询用户结算信息列表")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustomerStatementVo>> page(TenantCustomerStatementQueryParam tenantCustomerStatementQueryParam,
	                @RequestParam(value = "page", defaultValue = "1") int page, //
	                @RequestParam(value = "rows", defaultValue = "10") int rows, //
	                @RequestParam(value = "sort", required = false) String sort, // 排序列字段名
	                @RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
	        Page<TenantCustomerStatementVo> tenantCustomerStatementVoPage = tenantCustomerStatementClientService.page(tenantCustomerStatementQueryParam, page, rows, sort, order);
	        CommonPage<TenantCustomerStatementVo> tenantCustomerStatementCommonPage = CommonPage.restPage(tenantCustomerStatementVoPage);

	        return CommonResult.success(tenantCustomerStatementCommonPage);
	}
	
	@ApiOperation(value = "根据参数更新用户结算信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantCustomerStatementUpdateParam tenantCustomerStatementUpdateParam) {
		boolean success = tenantCustomerStatementClientService.updateById(id, tenantCustomerStatementUpdateParam);

		return CommonResult.success(success);
	}

}
