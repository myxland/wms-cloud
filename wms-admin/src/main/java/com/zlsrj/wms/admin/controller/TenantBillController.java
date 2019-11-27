package com.zlsrj.wms.admin.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantBillClientService;
import com.zlsrj.wms.api.dto.TenantBillQueryParam;
import com.zlsrj.wms.api.entity.TenantBill;
import com.zlsrj.wms.api.vo.TenantBillVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户账单配置", tags = { "租户账单配置操作接口" })
@Controller
@RequestMapping("/tenantBill")
@Slf4j
public class TenantBillController {

	@Autowired
	private TenantBillClientService tenantBillClientService;

	@ApiOperation(value = "根据参数查询租户账单配置列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantBillVo>> list(TenantBillQueryParam tenantBillQueryParam, int pageNum,
			int pageSize) {
		Page<TenantBillVo> tenantBillVoPage = tenantBillClientService.page(tenantBillQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantBillVo> tenantBillCommonPage = CommonPage.restPage(tenantBillVoPage);

		return CommonResult.success(tenantBillCommonPage);
	}

	@ApiOperation(value = "新增租户账单配置")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantBillVo> create(@RequestBody TenantBill tenantBill) {
		TenantBillVo tenantBillVo = tenantBillClientService.save(tenantBill);

		return CommonResult.success(tenantBillVo);
	}

	@ApiOperation(value = "根据ID查询租户账单配置")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantBillVo> getById(@PathVariable("id") Long id) {
		TenantBillVo tenantBillVo = tenantBillClientService.getById(id);

		return CommonResult.success(tenantBillVo);
	}

	@ApiOperation(value = "根据租户ID查询租户账单配置")
	@RequestMapping(value = "/tenantId/{tenantId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantBillVo> getByTenantId(@PathVariable("tenantId") Long tenantId) {
		TenantBillVo tenantBillVo = tenantBillClientService.getByTenantId(tenantId);

		return CommonResult.success(tenantBillVo);
	}

	@ApiOperation(value = "根据参数更新租户账单配置信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantBillVo> getById(@RequestBody TenantBill tenantBill) {
		Long id = tenantBill.getId();
		TenantBillVo tenantBillVo = tenantBillClientService.updatePatchById(id, tenantBill);

		return CommonResult.success(tenantBillVo);
	}
	
	@ApiOperation(value = "根据ID删除租户账单配置")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantBillClientService.removeById(id);

		return commonResult;
	}

}
