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
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantInfoQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户表", tags = { "租户表操作接口" })
@Controller
@RequestMapping("/tenantInfo")
@Slf4j
public class TenantInfoController {

	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询租户表列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantInfoVo>> list(TenantInfoQueryParam tenantInfoQueryParam, int pageNum,
			int pageSize) {
		Page<TenantInfoVo> tenantInfoVoPage = tenantInfoClientService.page(tenantInfoQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantInfoVo> tenantInfoCommonPage = CommonPage.restPage(tenantInfoVoPage);

		return CommonResult.success(tenantInfoCommonPage);
	}

	@ApiOperation(value = "新增租户表")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantInfoVo> create(@RequestBody TenantInfo tenantInfo) {
		TenantInfoVo tenantInfoVo = tenantInfoClientService.save(tenantInfo);

		return CommonResult.success(tenantInfoVo);
	}

	@ApiOperation(value = "根据ID查询租户表")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantInfoVo> getById(@PathVariable("id") Long id) {
		TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(id);

		return CommonResult.success(tenantInfoVo);
	}

	@ApiOperation(value = "根据参数更新租户表信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantInfoVo> getById(@RequestBody TenantInfo tenantInfo) {
		Long id = tenantInfo.getId();
		TenantInfoVo tenantInfoVo = tenantInfoClientService.updatePatchById(id, tenantInfo);

		return CommonResult.success(tenantInfoVo);
	}
	
	@ApiOperation(value = "根据ID删除租户表")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantInfoClientService.removeById(id);

		return commonResult;
	}

}
