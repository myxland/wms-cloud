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

import com.zlsrj.wms.api.client.service.TenantPriceClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantPriceAddParam;
import com.zlsrj.wms.api.dto.TenantPriceQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceUpdateParam;
import com.zlsrj.wms.api.vo.TenantPriceVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水价列表", tags = { "水价列表操作接口" })
@Controller
@RequestMapping("/tenantPrice")
@Slf4j
public class TenantPriceController {

	@Autowired
	private TenantPriceClientService tenantPriceClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水价列表列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantPriceVo>> list(TenantPriceQueryParam tenantPriceQueryParam) {
		List<TenantPriceVo> tenantPriceVoList = tenantPriceClientService.list(tenantPriceQueryParam);
		tenantPriceVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantPriceVoList);
	}

	@ApiOperation(value = "新增水价列表")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantPriceAddParam tenantPriceAddParam) {
		String id = tenantPriceClientService.save(tenantPriceAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询水价列表")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceVo> getById(@PathVariable("id") String id) {
		TenantPriceVo tenantPriceVo = tenantPriceClientService.getById(id);
		wrappperVo(tenantPriceVo);

		return CommonResult.success(tenantPriceVo);
	}

	@ApiOperation(value = "根据参数更新水价列表信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantPriceUpdateParam tenantPriceUpdateParam) {
		boolean success = tenantPriceClientService.updateById(id, tenantPriceUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除水价列表")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantPriceClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPriceVo tenantPriceVo) {
		if (StringUtils.isEmpty(tenantPriceVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantPriceVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPriceVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		boolean hasChildren = tenantPriceVo.isHasChildren();
		if(hasChildren == false) {
			String parentId = tenantPriceVo.getId();
			TenantPriceQueryParam tenantPriceQueryParam = new TenantPriceQueryParam();
			tenantPriceQueryParam.setParentId(parentId);
			List<TenantPriceVo> tenantPriceVoList = tenantPriceClientService.list(tenantPriceQueryParam);
			if(tenantPriceVoList!=null && tenantPriceVoList.size()>0) {
				tenantPriceVo.setHasChildren(true);
			}
		}
	}

}
