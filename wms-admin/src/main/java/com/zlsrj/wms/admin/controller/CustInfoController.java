package com.zlsrj.wms.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.CustInfoClientService;
import com.zlsrj.wms.api.client.service.TenantCustTypeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.CustInfoQueryParam;
import com.zlsrj.wms.api.entity.CustInfo;
import com.zlsrj.wms.api.vo.CustInfoVo;
import com.zlsrj.wms.api.vo.TenantCustTypeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户信息", tags = { "用户信息操作接口" })
@Controller
@RequestMapping("/custInfo")
@Slf4j
public class CustInfoController {

	@Autowired
	private CustInfoClientService custInfoClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private TenantCustTypeClientService tenantCustTypeClientService;

	@ApiOperation(value = "根据参数查询用户信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<CustInfoVo>> list(CustInfoQueryParam custInfoQueryParam, int pageNum, int pageSize) {
		Page<CustInfoVo> custInfoVoPage = custInfoClientService.page(custInfoQueryParam, pageNum, pageSize, "id",
				"desc");
		custInfoVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<CustInfoVo> custInfoCommonPage = CommonPage.restPage(custInfoVoPage);

		return CommonResult.success(custInfoCommonPage);
	}

	@ApiOperation(value = "新增用户信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<CustInfoVo> create(@RequestBody CustInfo custInfo) {
		CustInfoVo custInfoVo = custInfoClientService.save(custInfo);

		return CommonResult.success(custInfoVo);
	}

	@ApiOperation(value = "根据ID查询用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CustInfoVo> getById(@PathVariable("id") Long id) {
		CustInfoVo custInfoVo = custInfoClientService.getById(id);
		wrappperVo(custInfoVo);

		return CommonResult.success(custInfoVo);
	}

	@ApiOperation(value = "根据参数更新用户信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<CustInfoVo> getById(@RequestBody CustInfo custInfo) {
		Long id = custInfo.getId();
		CustInfoVo custInfoVo = custInfoClientService.updatePatchById(id, custInfo);
		wrappperVo(custInfoVo);

		return CommonResult.success(custInfoVo);
	}

	@ApiOperation(value = "根据ID删除用户信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = custInfoClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(CustInfoVo custInfoVo) {
		if (StringUtils.isEmpty(custInfoVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(custInfoVo.getTenantId());
			if (tenantInfoVo != null) {
				custInfoVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		if (StringUtils.isEmpty(custInfoVo.getCustTypeName())) {
			TenantCustTypeVo tenantCustTypeVo = tenantCustTypeClientService.getById(custInfoVo.getCustTypeId());
			if (tenantCustTypeVo != null) {
				custInfoVo.setCustTypeName(tenantCustTypeVo.getCustTypeName());
			}
		}
	}

}
