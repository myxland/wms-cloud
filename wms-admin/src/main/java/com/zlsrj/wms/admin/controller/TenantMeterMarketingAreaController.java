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

import com.zlsrj.wms.api.client.service.TenantMeterMarketingAreaClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterMarketingAreaVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "营销机构", tags = { "营销机构操作接口" })
@Controller
@RequestMapping("/tenantMeterMarketingArea")
@Slf4j
public class TenantMeterMarketingAreaController {

	@Autowired
	private TenantMeterMarketingAreaClientService tenantMeterMarketingAreaClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询营销机构列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterMarketingAreaVo>> list(TenantMeterMarketingAreaQueryParam tenantMeterMarketingAreaQueryParam) {
		List<TenantMeterMarketingAreaVo> tenantMeterMarketingAreaVoList = tenantMeterMarketingAreaClientService.list(tenantMeterMarketingAreaQueryParam);
		tenantMeterMarketingAreaVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantMeterMarketingAreaVoList);
	}

	@ApiOperation(value = "新增营销机构")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterMarketingAreaAddParam tenantMeterMarketingAreaAddParam) {
		String id = tenantMeterMarketingAreaClientService.save(tenantMeterMarketingAreaAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询营销机构")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterMarketingAreaVo> getById(@PathVariable("id") String id) {
		TenantMeterMarketingAreaVo tenantMeterMarketingAreaVo = tenantMeterMarketingAreaClientService.getById(id);
		wrappperVo(tenantMeterMarketingAreaVo);

		return CommonResult.success(tenantMeterMarketingAreaVo);
	}

	@ApiOperation(value = "根据参数更新营销机构信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterMarketingAreaUpdateParam tenantMeterMarketingAreaUpdateParam) {
		boolean success = tenantMeterMarketingAreaClientService.updateById(id, tenantMeterMarketingAreaUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除营销机构")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterMarketingAreaClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterMarketingAreaVo tenantMeterMarketingAreaVo) {
		if (StringUtils.isEmpty(tenantMeterMarketingAreaVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantMeterMarketingAreaVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterMarketingAreaVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		boolean hasChildren = tenantMeterMarketingAreaVo.isHasChildren();
		if(hasChildren == false) {
			String parentId = tenantMeterMarketingAreaVo.getId();
			TenantMeterMarketingAreaQueryParam tenantMeterMarketingAreaQueryParam = new TenantMeterMarketingAreaQueryParam();
			tenantMeterMarketingAreaQueryParam.setParentId(parentId);
			List<TenantMeterMarketingAreaVo> tenantMeterMarketingAreaVoList = tenantMeterMarketingAreaClientService.list(tenantMeterMarketingAreaQueryParam);
			if(tenantMeterMarketingAreaVoList!=null && tenantMeterMarketingAreaVoList.size()>0) {
				tenantMeterMarketingAreaVo.setHasChildren(true);
			}
		}
	}

}
