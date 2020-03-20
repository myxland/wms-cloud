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

import com.zlsrj.wms.api.client.service.TenantChargeAgencyClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantChargeAgencyAddParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyQueryParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyUpdateParam;
import com.zlsrj.wms.api.vo.TenantChargeAgencyVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "代收机构", tags = { "代收机构操作接口" })
@Controller
@RequestMapping("/tenantChargeAgency")
@Slf4j
public class TenantChargeAgencyController {

	@Autowired
	private TenantChargeAgencyClientService tenantChargeAgencyClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询代收机构列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantChargeAgencyVo>> list(TenantChargeAgencyQueryParam tenantChargeAgencyQueryParam) {
		List<TenantChargeAgencyVo> tenantChargeAgencyVoList = tenantChargeAgencyClientService.list(tenantChargeAgencyQueryParam);
		tenantChargeAgencyVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantChargeAgencyVoList);
	}

	@ApiOperation(value = "新增代收机构")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantChargeAgencyAddParam tenantChargeAgencyAddParam) {
		String id = tenantChargeAgencyClientService.save(tenantChargeAgencyAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询代收机构")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantChargeAgencyVo> getById(@PathVariable("id") String id) {
		TenantChargeAgencyVo tenantChargeAgencyVo = tenantChargeAgencyClientService.getById(id);
		wrappperVo(tenantChargeAgencyVo);

		return CommonResult.success(tenantChargeAgencyVo);
	}

	@ApiOperation(value = "根据参数更新代收机构信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantChargeAgencyUpdateParam tenantChargeAgencyUpdateParam) {
		boolean success = tenantChargeAgencyClientService.updateById(id, tenantChargeAgencyUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除代收机构")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantChargeAgencyClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantChargeAgencyVo tenantChargeAgencyVo) {
		if (StringUtils.isEmpty(tenantChargeAgencyVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(tenantChargeAgencyVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantChargeAgencyVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
