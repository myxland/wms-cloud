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
import com.zlsrj.wms.api.client.service.TenantWaterTypeClientService;
import com.zlsrj.wms.api.dto.TenantWaterTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.api.vo.TenantWaterTypeVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用水类型", tags = { "用水类型操作接口" })
@Controller
@RequestMapping("/tenantWaterType")
@Slf4j
public class TenantWaterTypeController {

	@Autowired
	private TenantWaterTypeClientService tenantWaterTypeClientService;

	@ApiOperation(value = "根据参数查询用水类型列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantWaterTypeVo>> list(TenantWaterTypeQueryParam tenantWaterTypeQueryParam, int pageNum,
			int pageSize) {
		Page<TenantWaterTypeVo> tenantWaterTypeVoPage = tenantWaterTypeClientService.page(tenantWaterTypeQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantWaterTypeVo> tenantWaterTypeCommonPage = CommonPage.restPage(tenantWaterTypeVoPage);

		return CommonResult.success(tenantWaterTypeCommonPage);
	}

	@ApiOperation(value = "新增用水类型")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantWaterTypeVo> create(@RequestBody TenantWaterType tenantWaterType) {
		TenantWaterTypeVo tenantWaterTypeVo = tenantWaterTypeClientService.save(tenantWaterType);

		return CommonResult.success(tenantWaterTypeVo);
	}

	@ApiOperation(value = "根据ID查询用水类型")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantWaterTypeVo> getById(@PathVariable("id") Long id) {
		TenantWaterTypeVo tenantWaterTypeVo = tenantWaterTypeClientService.getById(id);

		return CommonResult.success(tenantWaterTypeVo);
	}

	@ApiOperation(value = "根据参数更新用水类型信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantWaterTypeVo> getById(@RequestBody TenantWaterType tenantWaterType) {
		Long id = tenantWaterType.getId();
		TenantWaterTypeVo tenantWaterTypeVo = tenantWaterTypeClientService.updatePatchById(id, tenantWaterType);

		return CommonResult.success(tenantWaterTypeVo);
	}
	
	@ApiOperation(value = "根据ID删除用水类型")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantWaterTypeClientService.removeById(id);

		return commonResult;
	}

}
