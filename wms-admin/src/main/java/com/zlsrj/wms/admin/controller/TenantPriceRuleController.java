package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.TenantPriceRuleClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantPriceRuleAddParam;
import com.zlsrj.wms.api.dto.TenantPriceRuleQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceRuleUpdateParam;
import com.zlsrj.wms.api.vo.TenantPriceRuleVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "计费规则", tags = { "计费规则操作接口" })
@Controller
@RequestMapping("/tenantPriceRule")
@Slf4j
public class TenantPriceRuleController {

	@Autowired
	private TenantPriceRuleClientService tenantPriceRuleClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询计费规则列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantPriceRuleVo>> list(TenantPriceRuleQueryParam tenantPriceRuleQueryParam) {
		List<TenantPriceRuleVo> tenantPriceRuleVoList = tenantPriceRuleClientService.list(tenantPriceRuleQueryParam);
		tenantPriceRuleVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantPriceRuleVoList);
	}

	@ApiOperation(value = "新增计费规则")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantPriceRuleAddParam tenantPriceRuleAddParam) {
		String id = tenantPriceRuleClientService.save(tenantPriceRuleAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询计费规则")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceRuleVo> getById(@PathVariable("id") String id) {
		TenantPriceRuleVo tenantPriceRuleVo = tenantPriceRuleClientService.getById(id);
		wrappperVo(tenantPriceRuleVo);

		return CommonResult.success(tenantPriceRuleVo);
	}

	@ApiOperation(value = "根据参数更新计费规则信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantPriceRuleUpdateParam tenantPriceRuleUpdateParam) {
		boolean success = tenantPriceRuleClientService.updateById(id, tenantPriceRuleUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除计费规则")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantPriceRuleClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPriceRuleVo tenantPriceRuleVo) {
		if (StringUtils.isEmpty(tenantPriceRuleVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantPriceRuleVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPriceRuleVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
