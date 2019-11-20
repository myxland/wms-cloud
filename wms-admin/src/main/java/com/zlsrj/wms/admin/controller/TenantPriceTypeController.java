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
import com.zlsrj.wms.api.client.service.TenantPriceTypeClientService;
import com.zlsrj.wms.api.dto.TenantPriceTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.vo.TenantPriceTypeVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "价格类别", tags = { "价格类别操作接口" })
@Controller
@RequestMapping("/tenantPriceType")
@Slf4j
public class TenantPriceTypeController {

	@Autowired
	private TenantPriceTypeClientService tenantPriceTypeClientService;

	@ApiOperation(value = "根据参数查询价格类别列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantPriceTypeVo>> list(TenantPriceTypeQueryParam tenantPriceTypeQueryParam, int pageNum,
			int pageSize) {
		Page<TenantPriceTypeVo> tenantPriceTypeVoPage = tenantPriceTypeClientService.page(tenantPriceTypeQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantPriceTypeVo> tenantPriceTypeCommonPage = CommonPage.restPage(tenantPriceTypeVoPage);

		return CommonResult.success(tenantPriceTypeCommonPage);
	}

	@ApiOperation(value = "新增价格类别")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceTypeVo> create(@RequestBody TenantPriceType tenantPriceType) {
		TenantPriceTypeVo tenantPriceTypeVo = tenantPriceTypeClientService.save(tenantPriceType);

		return CommonResult.success(tenantPriceTypeVo);
	}

	@ApiOperation(value = "根据ID查询价格类别")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceTypeVo> getById(@PathVariable("id") Long id) {
		TenantPriceTypeVo tenantPriceTypeVo = tenantPriceTypeClientService.getById(id);

		return CommonResult.success(tenantPriceTypeVo);
	}

	@ApiOperation(value = "根据参数更新价格类别信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceTypeVo> getById(@RequestBody TenantPriceType tenantPriceType) {
		Long id = tenantPriceType.getId();
		TenantPriceTypeVo tenantPriceTypeVo = tenantPriceTypeClientService.updatePatchById(id, tenantPriceType);

		return CommonResult.success(tenantPriceTypeVo);
	}
	
	@ApiOperation(value = "根据ID删除价格类别")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantPriceTypeClientService.removeById(id);

		return commonResult;
	}

}
