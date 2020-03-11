package com.zlsrj.wms.admin.controller;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.CustInfoChangeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.CustInfoChangeQueryParam;
import com.zlsrj.wms.api.entity.CustInfoChange;
import com.zlsrj.wms.api.vo.CustInfoChangeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户变更", tags = { "用户变更操作接口" })
@Controller
@RequestMapping("/custInfoChange")
@Slf4j
public class CustInfoChangeController {

	@Autowired
	private CustInfoChangeClientService custInfoChangeClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询用户变更列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<CustInfoChangeVo>> list(CustInfoChangeQueryParam custInfoChangeQueryParam, int pageNum,
			int pageSize) {
		Page<CustInfoChangeVo> custInfoChangeVoPage = custInfoChangeClientService.page(custInfoChangeQueryParam, pageNum, pageSize, "id", "desc");
		custInfoChangeVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<CustInfoChangeVo> custInfoChangeCommonPage = CommonPage.restPage(custInfoChangeVoPage);

		return CommonResult.success(custInfoChangeCommonPage);
	}

	@ApiOperation(value = "新增用户变更")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<CustInfoChangeVo> create(@RequestBody CustInfoChange custInfoChange) {
		CustInfoChangeVo custInfoChangeVo = custInfoChangeClientService.save(custInfoChange);

		return CommonResult.success(custInfoChangeVo);
	}

	@ApiOperation(value = "根据ID查询用户变更")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CustInfoChangeVo> getById(@PathVariable("id") String id) {
		CustInfoChangeVo custInfoChangeVo = custInfoChangeClientService.getById(id);
		wrappperVo(custInfoChangeVo);
		
		return CommonResult.success(custInfoChangeVo);
	}

	@ApiOperation(value = "根据参数更新用户变更信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<CustInfoChangeVo> getById(@RequestBody CustInfoChange custInfoChange) {
		String id = custInfoChange.getId();
		CustInfoChangeVo custInfoChangeVo = custInfoChangeClientService.updatePatchById(id, custInfoChange);

		return CommonResult.success(custInfoChangeVo);
	}
	
	@ApiOperation(value = "根据ID删除用户变更")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = custInfoChangeClientService.removeById(id);

		return commonResult;
	}
	
	private void wrappperVo(CustInfoChangeVo custInfoChangeVo) {
		if (StringUtils.isEmpty(custInfoChangeVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(custInfoChangeVo.getTenantId());
			if (tenantInfoVo != null) {
				custInfoChangeVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
