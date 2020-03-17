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

import com.zlsrj.wms.api.client.service.TenantMeterSupplyAreaClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterSupplyAreaVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "供水区域", tags = { "供水区域操作接口" })
@Controller
@RequestMapping("/tenantMeterSupplyArea")
@Slf4j
public class TenantMeterSupplyAreaController {

	@Autowired
	private TenantMeterSupplyAreaClientService tenantMeterSupplyAreaClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询供水区域列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterSupplyAreaVo>> list(TenantMeterSupplyAreaQueryParam tenantMeterSupplyAreaQueryParam) {
		List<TenantMeterSupplyAreaVo> tenantMeterSupplyAreaVoList = tenantMeterSupplyAreaClientService.list(tenantMeterSupplyAreaQueryParam);
		tenantMeterSupplyAreaVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantMeterSupplyAreaVoList);
	}

	@ApiOperation(value = "新增供水区域")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterSupplyAreaAddParam tenantMeterSupplyAreaAddParam) {
		String id = tenantMeterSupplyAreaClientService.save(tenantMeterSupplyAreaAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询供水区域")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterSupplyAreaVo> getById(@PathVariable("id") String id) {
		TenantMeterSupplyAreaVo tenantMeterSupplyAreaVo = tenantMeterSupplyAreaClientService.getById(id);
		wrappperVo(tenantMeterSupplyAreaVo);

		return CommonResult.success(tenantMeterSupplyAreaVo);
	}

	@ApiOperation(value = "根据参数更新供水区域信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterSupplyAreaUpdateParam tenantMeterSupplyAreaUpdateParam) {
		boolean success = tenantMeterSupplyAreaClientService.updateById(id, tenantMeterSupplyAreaUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除供水区域")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterSupplyAreaClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterSupplyAreaVo tenantMeterSupplyAreaVo) {
		if (StringUtils.isEmpty(tenantMeterSupplyAreaVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantMeterSupplyAreaVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterSupplyAreaVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		boolean hasChildren = tenantMeterSupplyAreaVo.isHasChildren();
		if(hasChildren == false) {
			String parentId = tenantMeterSupplyAreaVo.getId();
			TenantMeterSupplyAreaQueryParam tenantMeterSupplyAreaQueryParam = new TenantMeterSupplyAreaQueryParam();
			tenantMeterSupplyAreaQueryParam.setParentId(parentId);
			List<TenantMeterSupplyAreaVo> tenantMeterSupplyAreaVoList = tenantMeterSupplyAreaClientService.list(tenantMeterSupplyAreaQueryParam);
			if(tenantMeterSupplyAreaVoList!=null && tenantMeterSupplyAreaVoList.size()>0) {
				tenantMeterSupplyAreaVo.setHasChildren(true);
			}
		}
	}

}
