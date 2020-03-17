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

import com.zlsrj.wms.api.client.service.TenantMeterCaliberClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterCaliberAddParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterCaliberVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表口径字典表", tags = { "水表口径字典表操作接口" })
@Controller
@RequestMapping("/tenantMeterCaliber")
@Slf4j
public class TenantMeterCaliberController {

	@Autowired
	private TenantMeterCaliberClientService tenantMeterCaliberClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水表口径字典表列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterCaliberVo>> list(TenantMeterCaliberQueryParam tenantMeterCaliberQueryParam) {
		List<TenantMeterCaliberVo> tenantMeterCaliberVoList = tenantMeterCaliberClientService.list(tenantMeterCaliberQueryParam);
		tenantMeterCaliberVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantMeterCaliberVoList);
	}

	@ApiOperation(value = "新增水表口径字典表")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterCaliberAddParam tenantMeterCaliberAddParam) {
		String id = tenantMeterCaliberClientService.save(tenantMeterCaliberAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询水表口径字典表")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterCaliberVo> getById(@PathVariable("id") String id) {
		TenantMeterCaliberVo tenantMeterCaliberVo = tenantMeterCaliberClientService.getById(id);
		wrappperVo(tenantMeterCaliberVo);

		return CommonResult.success(tenantMeterCaliberVo);
	}

	@ApiOperation(value = "根据参数更新水表口径字典表信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterCaliberUpdateParam tenantMeterCaliberUpdateParam) {
		boolean success = tenantMeterCaliberClientService.updateById(id, tenantMeterCaliberUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除水表口径字典表")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterCaliberClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterCaliberVo tenantMeterCaliberVo) {
		if (StringUtils.isEmpty(tenantMeterCaliberVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantMeterCaliberVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterCaliberVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
