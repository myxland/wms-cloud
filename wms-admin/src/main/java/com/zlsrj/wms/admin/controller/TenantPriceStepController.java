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
import com.zlsrj.wms.api.client.service.TenantPriceStepClientService;
import com.zlsrj.wms.api.dto.TenantPriceStepQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.vo.TenantPriceStepVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "价格阶梯", tags = { "价格阶梯操作接口" })
@Controller
@RequestMapping("/tenantPriceStep")
@Slf4j
public class TenantPriceStepController {

	@Autowired
	private TenantPriceStepClientService tenantPriceStepClientService;

	@ApiOperation(value = "根据参数查询价格阶梯列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantPriceStepVo>> list(TenantPriceStepQueryParam tenantPriceStepQueryParam, int pageNum,
			int pageSize) {
		Page<TenantPriceStepVo> tenantPriceStepVoPage = tenantPriceStepClientService.page(tenantPriceStepQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantPriceStepVo> tenantPriceStepCommonPage = CommonPage.restPage(tenantPriceStepVoPage);

		return CommonResult.success(tenantPriceStepCommonPage);
	}

	@ApiOperation(value = "新增价格阶梯")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceStepVo> create(@RequestBody TenantPriceStep tenantPriceStep) {
		TenantPriceStepVo tenantPriceStepVo = tenantPriceStepClientService.save(tenantPriceStep);

		return CommonResult.success(tenantPriceStepVo);
	}

	@ApiOperation(value = "根据ID查询价格阶梯")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceStepVo> getById(@PathVariable("id") Long id) {
		TenantPriceStepVo tenantPriceStepVo = tenantPriceStepClientService.getById(id);

		return CommonResult.success(tenantPriceStepVo);
	}

	@ApiOperation(value = "根据参数更新价格阶梯信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceStepVo> getById(@RequestBody TenantPriceStep tenantPriceStep) {
		Long id = tenantPriceStep.getId();
		TenantPriceStepVo tenantPriceStepVo = tenantPriceStepClientService.updatePatchById(id, tenantPriceStep);

		return CommonResult.success(tenantPriceStepVo);
	}
	
	@ApiOperation(value = "根据ID删除价格阶梯")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantPriceStepClientService.removeById(id);

		return commonResult;
	}

}
