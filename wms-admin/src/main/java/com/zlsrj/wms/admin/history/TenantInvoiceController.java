package com.zlsrj.wms.admin.history;

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
import com.zlsrj.wms.api.client.service.TenantInvoiceClientService;
import com.zlsrj.wms.api.dto.TenantInvoiceQueryParam;
import com.zlsrj.wms.api.entity.TenantInvoice;
import com.zlsrj.wms.api.vo.TenantInvoiceVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户发票配置", tags = { "租户发票配置操作接口" })
@Controller
@RequestMapping("/tenantInvoice")
@Slf4j
public class TenantInvoiceController {

	@Autowired
	private TenantInvoiceClientService tenantInvoiceClientService;

	@ApiOperation(value = "根据参数查询租户发票配置列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantInvoiceVo>> list(TenantInvoiceQueryParam tenantInvoiceQueryParam, int pageNum,
			int pageSize) {
		Page<TenantInvoiceVo> tenantInvoiceVoPage = tenantInvoiceClientService.page(tenantInvoiceQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantInvoiceVo> tenantInvoiceCommonPage = CommonPage.restPage(tenantInvoiceVoPage);

		return CommonResult.success(tenantInvoiceCommonPage);
	}

	@ApiOperation(value = "新增租户发票配置")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantInvoiceVo> create(@RequestBody TenantInvoice tenantInvoice) {
		TenantInvoiceVo tenantInvoiceVo = tenantInvoiceClientService.save(tenantInvoice);

		return CommonResult.success(tenantInvoiceVo);
	}

	@ApiOperation(value = "根据ID查询租户发票配置")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantInvoiceVo> getById(@PathVariable("id") String id) {
		TenantInvoiceVo tenantInvoiceVo = tenantInvoiceClientService.getById(id);

		return CommonResult.success(tenantInvoiceVo);
	}

	@ApiOperation(value = "根据租户ID查询租户发票配置")
	@RequestMapping(value = "/tenantId/{tenantId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantInvoiceVo> getByTenantId(@PathVariable("tenantId") String tenantId) {
		TenantInvoiceVo tenantInvoiceVo = tenantInvoiceClientService.getByTenantId(tenantId);

		return CommonResult.success(tenantInvoiceVo);
	}

	@ApiOperation(value = "根据参数更新租户发票配置信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantInvoiceVo> getById(@RequestBody TenantInvoice tenantInvoice) {
		String id = tenantInvoice.getId();
		TenantInvoiceVo tenantInvoiceVo = tenantInvoiceClientService.updatePatchById(id, tenantInvoice);

		return CommonResult.success(tenantInvoiceVo);
	}
	
	@ApiOperation(value = "根据ID删除租户发票配置")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantInvoiceClientService.removeById(id);

		return commonResult;
	}

}
