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
import com.zlsrj.wms.api.client.service.TenantSystemPriceClientService;
import com.zlsrj.wms.api.dto.TenantSystemPriceQueryParam;
import com.zlsrj.wms.api.entity.TenantSystemPrice;
import com.zlsrj.wms.api.vo.TenantSystemPriceVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户模块价格", tags = { "租户模块价格操作接口" })
@Controller
@RequestMapping("/tenantSystemPrice")
@Slf4j
public class TenantSystemPriceController {

	@Autowired
	private TenantSystemPriceClientService tenantSystemPriceClientService;

	@ApiOperation(value = "根据参数查询租户模块价格列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantSystemPriceVo>> list(TenantSystemPriceQueryParam tenantSystemPriceQueryParam, int pageNum,
			int pageSize) {
		Page<TenantSystemPriceVo> tenantSystemPriceVoPage = tenantSystemPriceClientService.page(tenantSystemPriceQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantSystemPriceVo> tenantSystemPriceCommonPage = CommonPage.restPage(tenantSystemPriceVoPage);

		return CommonResult.success(tenantSystemPriceCommonPage);
	}

	@ApiOperation(value = "新增租户模块价格")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantSystemPriceVo> create(@RequestBody TenantSystemPrice tenantSystemPrice) {
		TenantSystemPriceVo tenantSystemPriceVo = tenantSystemPriceClientService.save(tenantSystemPrice);

		return CommonResult.success(tenantSystemPriceVo);
	}

	@ApiOperation(value = "根据ID查询租户模块价格")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantSystemPriceVo> getById(@PathVariable("id") String id) {
		TenantSystemPriceVo tenantSystemPriceVo = tenantSystemPriceClientService.getById(id);

		return CommonResult.success(tenantSystemPriceVo);
	}

	@ApiOperation(value = "根据参数更新租户模块价格信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantSystemPriceVo> getById(@RequestBody TenantSystemPrice tenantSystemPrice) {
		String id = tenantSystemPrice.getId();
		TenantSystemPriceVo tenantSystemPriceVo = tenantSystemPriceClientService.updatePatchById(id, tenantSystemPrice);

		return CommonResult.success(tenantSystemPriceVo);
	}
	
	@ApiOperation(value = "根据ID删除租户模块价格")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantSystemPriceClientService.removeById(id);

		return commonResult;
	}

}
