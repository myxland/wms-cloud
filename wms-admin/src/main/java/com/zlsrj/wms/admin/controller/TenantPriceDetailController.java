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
import com.zlsrj.wms.api.client.service.TenantPriceDetailClientService;
import com.zlsrj.wms.api.dto.TenantPriceDetailQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.api.vo.TenantPriceDetailVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "价格明细", tags = { "价格明细操作接口" })
@Controller
@RequestMapping("/tenantPriceDetail")
@Slf4j
public class TenantPriceDetailController {

	@Autowired
	private TenantPriceDetailClientService tenantPriceDetailClientService;

	@ApiOperation(value = "根据参数查询价格明细列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantPriceDetailVo>> list(TenantPriceDetailQueryParam tenantPriceDetailQueryParam, int pageNum,
			int pageSize) {
		Page<TenantPriceDetailVo> tenantPriceDetailVoPage = tenantPriceDetailClientService.page(tenantPriceDetailQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantPriceDetailVo> tenantPriceDetailCommonPage = CommonPage.restPage(tenantPriceDetailVoPage);

		return CommonResult.success(tenantPriceDetailCommonPage);
	}

	@ApiOperation(value = "新增价格明细")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceDetailVo> create(@RequestBody TenantPriceDetail tenantPriceDetail) {
		TenantPriceDetailVo tenantPriceDetailVo = tenantPriceDetailClientService.save(tenantPriceDetail);

		return CommonResult.success(tenantPriceDetailVo);
	}

	@ApiOperation(value = "根据ID查询价格明细")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceDetailVo> getById(@PathVariable("id") Long id) {
		TenantPriceDetailVo tenantPriceDetailVo = tenantPriceDetailClientService.getById(id);

		return CommonResult.success(tenantPriceDetailVo);
	}

	@ApiOperation(value = "根据参数更新价格明细信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceDetailVo> getById(@RequestBody TenantPriceDetail tenantPriceDetail) {
		Long id = tenantPriceDetail.getId();
		TenantPriceDetailVo tenantPriceDetailVo = tenantPriceDetailClientService.updatePatchById(id, tenantPriceDetail);

		return CommonResult.success(tenantPriceDetailVo);
	}
	
	@ApiOperation(value = "根据ID删除价格明细")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantPriceDetailClientService.removeById(id);

		return commonResult;
	}

}
