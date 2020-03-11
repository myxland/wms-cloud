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
import com.zlsrj.wms.api.client.service.TenantCustTypeClientService;
import com.zlsrj.wms.api.dto.TenantCustTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantCustType;
import com.zlsrj.wms.api.vo.TenantCustTypeVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户类型", tags = { "用户类型操作接口" })
@Controller
@RequestMapping("/tenantCustType")
@Slf4j
public class TenantCustTypeController {

	@Autowired
	private TenantCustTypeClientService tenantCustTypeClientService;

	@ApiOperation(value = "根据参数查询用户类型列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustTypeVo>> list(TenantCustTypeQueryParam tenantCustTypeQueryParam, int pageNum,
			int pageSize) {
		Page<TenantCustTypeVo> tenantCustTypeVoPage = tenantCustTypeClientService.page(tenantCustTypeQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantCustTypeVo> tenantCustTypeCommonPage = CommonPage.restPage(tenantCustTypeVoPage);

		return CommonResult.success(tenantCustTypeCommonPage);
	}

	@ApiOperation(value = "新增用户类型")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustTypeVo> create(@RequestBody TenantCustType tenantCustType) {
		TenantCustTypeVo tenantCustTypeVo = tenantCustTypeClientService.save(tenantCustType);

		return CommonResult.success(tenantCustTypeVo);
	}

	@ApiOperation(value = "根据ID查询用户类型")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustTypeVo> getById(@PathVariable("id") String id) {
		TenantCustTypeVo tenantCustTypeVo = tenantCustTypeClientService.getById(id);

		return CommonResult.success(tenantCustTypeVo);
	}

	@ApiOperation(value = "根据参数更新用户类型信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustTypeVo> getById(@RequestBody TenantCustType tenantCustType) {
		String id = tenantCustType.getId();
		TenantCustTypeVo tenantCustTypeVo = tenantCustTypeClientService.updatePatchById(id, tenantCustType);

		return CommonResult.success(tenantCustTypeVo);
	}
	
	@ApiOperation(value = "根据ID删除用户类型")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCustTypeClientService.removeById(id);

		return commonResult;
	}

}
