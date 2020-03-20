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

import com.zlsrj.wms.api.client.service.TenantMeterIndustryClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterIndustryAddParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterIndustryVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "行业分类", tags = { "行业分类操作接口" })
@Controller
@RequestMapping("/tenantMeterIndustry")
@Slf4j
public class TenantMeterIndustryController {

	@Autowired
	private TenantMeterIndustryClientService tenantMeterIndustryClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询行业分类列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterIndustryVo>> list(TenantMeterIndustryQueryParam tenantMeterIndustryQueryParam) {
		List<TenantMeterIndustryVo> tenantMeterIndustryVoList = tenantMeterIndustryClientService.list(tenantMeterIndustryQueryParam);
		tenantMeterIndustryVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantMeterIndustryVoList);
	}

	@ApiOperation(value = "新增行业分类")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterIndustryAddParam tenantMeterIndustryAddParam) {
		String id = tenantMeterIndustryClientService.save(tenantMeterIndustryAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询行业分类")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterIndustryVo> getById(@PathVariable("id") String id) {
		TenantMeterIndustryVo tenantMeterIndustryVo = tenantMeterIndustryClientService.getById(id);
		wrappperVo(tenantMeterIndustryVo);

		return CommonResult.success(tenantMeterIndustryVo);
	}

	@ApiOperation(value = "根据参数更新行业分类信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterIndustryUpdateParam tenantMeterIndustryUpdateParam) {
		boolean success = tenantMeterIndustryClientService.updateById(id, tenantMeterIndustryUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除行业分类")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterIndustryClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterIndustryVo tenantMeterIndustryVo) {
		if (StringUtils.isEmpty(tenantMeterIndustryVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(tenantMeterIndustryVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterIndustryVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		boolean hasChildren = tenantMeterIndustryVo.isHasChildren();
		if(hasChildren == false) {
			String parentId = tenantMeterIndustryVo.getId();
			TenantMeterIndustryQueryParam tenantMeterIndustryQueryParam = new TenantMeterIndustryQueryParam();
			tenantMeterIndustryQueryParam.setParentId(parentId);
			List<TenantMeterIndustryVo> tenantMeterIndustryVoList = tenantMeterIndustryClientService.list(tenantMeterIndustryQueryParam);
			if(tenantMeterIndustryVoList!=null && tenantMeterIndustryVoList.size()>0) {
				tenantMeterIndustryVo.setHasChildren(true);
			}
		}
	}

}
