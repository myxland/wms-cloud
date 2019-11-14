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
import com.zlsrj.wms.api.client.service.TenantAccountClientService;
import com.zlsrj.wms.api.dto.TenantAccountQueryParam;
import com.zlsrj.wms.api.entity.TenantAccount;
import com.zlsrj.wms.api.vo.TenantAccountVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户账户", tags = { "租户账户操作接口" })
@Controller
@RequestMapping("/tenantAccount")
@Slf4j
public class TenantAccountController {

	@Autowired
	private TenantAccountClientService tenantAccountClientService;

	@ApiOperation(value = "根据参数查询租户账户列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantAccountVo>> list(TenantAccountQueryParam tenantAccountQueryParam, int pageNum,
			int pageSize) {
		Page<TenantAccountVo> tenantAccountVoPage = tenantAccountClientService.page(tenantAccountQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantAccountVo> tenantAccountCommonPage = CommonPage.restPage(tenantAccountVoPage);

		return CommonResult.success(tenantAccountCommonPage);
	}

	@ApiOperation(value = "新增租户账户")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantAccountVo> create(@RequestBody TenantAccount tenantAccount) {
		TenantAccountVo tenantAccountVo = tenantAccountClientService.save(tenantAccount);

		return CommonResult.success(tenantAccountVo);
	}

	@ApiOperation(value = "根据ID查询租户账户")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantAccountVo> getById(@PathVariable("id") Long id) {
		TenantAccountVo tenantAccountVo = tenantAccountClientService.getById(id);

		return CommonResult.success(tenantAccountVo);
	}

	@ApiOperation(value = "根据参数更新租户账户信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantAccountVo> getById(@RequestBody TenantAccount tenantAccount) {
		Long id = tenantAccount.getId();
		TenantAccountVo tenantAccountVo = tenantAccountClientService.updatePatchById(id, tenantAccount);

		return CommonResult.success(tenantAccountVo);
	}
	
	@ApiOperation(value = "根据ID删除租户账户")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantAccountClientService.removeById(id);

		return commonResult;
	}

}
