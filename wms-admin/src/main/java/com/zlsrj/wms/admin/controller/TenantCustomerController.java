package com.zlsrj.wms.admin.controller;

import org.apache.commons.lang3.StringUtils;
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
import com.zlsrj.wms.api.client.service.TenantCustomerClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantCustomerQueryParam;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.vo.TenantCustomerVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户信息", tags = { "用户信息操作接口" })
@Controller
@RequestMapping("/tenantCustomer")
@Slf4j
public class TenantCustomerController {

	@Autowired
	private TenantCustomerClientService tenantCustomerClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询用户信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustomerVo>> list(TenantCustomerQueryParam tenantCustomerQueryParam, int pageNum,
			int pageSize) {
		Page<TenantCustomerVo> tenantCustomerVoPage = tenantCustomerClientService.page(tenantCustomerQueryParam, pageNum, pageSize, "id", "desc");
		tenantCustomerVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantCustomerVo> tenantCustomerCommonPage = CommonPage.restPage(tenantCustomerVoPage);

		return CommonResult.success(tenantCustomerCommonPage);
	}

	@ApiOperation(value = "新增用户信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerVo> create(@RequestBody TenantCustomer tenantCustomer) {
		TenantCustomerVo tenantCustomerVo = tenantCustomerClientService.save(tenantCustomer);

		return CommonResult.success(tenantCustomerVo);
	}

	@ApiOperation(value = "根据ID查询用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerVo> getById(@PathVariable("id") Long id) {
		TenantCustomerVo tenantCustomerVo = tenantCustomerClientService.getById(id);
		wrappperVo(tenantCustomerVo);

		return CommonResult.success(tenantCustomerVo);
	}

	@ApiOperation(value = "根据参数更新用户信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerVo> getById(@RequestBody TenantCustomer tenantCustomer) {
		Long id = tenantCustomer.getId();
		TenantCustomerVo tenantCustomerVo = tenantCustomerClientService.updatePatchById(id, tenantCustomer);
		wrappperVo(tenantCustomerVo);

		return CommonResult.success(tenantCustomerVo);
	}
	
	@ApiOperation(value = "根据ID删除用户信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantCustomerClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantCustomerVo tenantCustomerVo) {
		if (StringUtils.isEmpty(tenantCustomerVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantCustomerVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantCustomerVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
