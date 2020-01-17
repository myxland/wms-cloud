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
import com.zlsrj.wms.api.client.service.TenantCustomerMeterInstallClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantCustomerMeterInstallQueryParam;
import com.zlsrj.wms.api.entity.TenantCustomerMeterInstall;
import com.zlsrj.wms.api.vo.TenantCustomerMeterInstallVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户水表立户", tags = { "用户水表立户操作接口" })
@Controller
@RequestMapping("/tenantCustomerMeterInstall")
@Slf4j
public class TenantCustomerMeterInstallController {

	@Autowired
	private TenantCustomerMeterInstallClientService tenantCustomerMeterInstallClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询用户水表立户列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustomerMeterInstallVo>> list(TenantCustomerMeterInstallQueryParam tenantCustomerMeterInstallQueryParam, int pageNum,
			int pageSize) {
		Page<TenantCustomerMeterInstallVo> tenantCustomerMeterInstallVoPage = tenantCustomerMeterInstallClientService.page(tenantCustomerMeterInstallQueryParam, pageNum, pageSize, "id", "desc");
		tenantCustomerMeterInstallVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantCustomerMeterInstallVo> tenantCustomerMeterInstallCommonPage = CommonPage.restPage(tenantCustomerMeterInstallVoPage);

		return CommonResult.success(tenantCustomerMeterInstallCommonPage);
	}

	@ApiOperation(value = "新增用户水表立户")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerMeterInstallVo> create(@RequestBody TenantCustomerMeterInstall tenantCustomerMeterInstall) {
		TenantCustomerMeterInstallVo tenantCustomerMeterInstallVo = tenantCustomerMeterInstallClientService.save(tenantCustomerMeterInstall);

		return CommonResult.success(tenantCustomerMeterInstallVo);
	}

	@ApiOperation(value = "根据ID查询用户水表立户")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerMeterInstallVo> getById(@PathVariable("id") Long id) {
		TenantCustomerMeterInstallVo tenantCustomerMeterInstallVo = tenantCustomerMeterInstallClientService.getById(id);
		wrappperVo(tenantCustomerMeterInstallVo);

		return CommonResult.success(tenantCustomerMeterInstallVo);
	}

	@ApiOperation(value = "根据参数更新用户水表立户信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerMeterInstallVo> getById(@RequestBody TenantCustomerMeterInstall tenantCustomerMeterInstall) {
		Long id = tenantCustomerMeterInstall.getId();
		TenantCustomerMeterInstallVo tenantCustomerMeterInstallVo = tenantCustomerMeterInstallClientService.updatePatchById(id, tenantCustomerMeterInstall);
		wrappperVo(tenantCustomerMeterInstallVo);

		return CommonResult.success(tenantCustomerMeterInstallVo);
	}
	
	@ApiOperation(value = "根据ID删除用户水表立户")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantCustomerMeterInstallClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantCustomerMeterInstallVo tenantCustomerMeterInstallVo) {
		if (StringUtils.isEmpty(tenantCustomerMeterInstallVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantCustomerMeterInstallVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantCustomerMeterInstallVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
