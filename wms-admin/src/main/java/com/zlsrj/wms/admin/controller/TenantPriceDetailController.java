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

import com.zlsrj.wms.api.client.service.TenantPriceDetailClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantPriceDetailAddParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailUpdateParam;
import com.zlsrj.wms.api.vo.TenantPriceDetailVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水价明细", tags = { "水价明细操作接口" })
@Controller
@RequestMapping("/tenantPriceDetail")
@Slf4j
public class TenantPriceDetailController {

	@Autowired
	private TenantPriceDetailClientService tenantPriceDetailClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水价明细列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantPriceDetailVo>> list(TenantPriceDetailQueryParam tenantPriceDetailQueryParam) {
		List<TenantPriceDetailVo> tenantPriceDetailVoList = tenantPriceDetailClientService.list(tenantPriceDetailQueryParam);
		tenantPriceDetailVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantPriceDetailVoList);
	}

	@ApiOperation(value = "新增水价明细")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantPriceDetailAddParam tenantPriceDetailAddParam) {
		String id = tenantPriceDetailClientService.save(tenantPriceDetailAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询水价明细")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceDetailVo> getById(@PathVariable("id") String id) {
		TenantPriceDetailVo tenantPriceDetailVo = tenantPriceDetailClientService.getById(id);
		wrappperVo(tenantPriceDetailVo);

		return CommonResult.success(tenantPriceDetailVo);
	}

	@ApiOperation(value = "根据参数更新水价明细信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantPriceDetailUpdateParam tenantPriceDetailUpdateParam) {
		boolean success = tenantPriceDetailClientService.updateById(id, tenantPriceDetailUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除水价明细")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantPriceDetailClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPriceDetailVo tenantPriceDetailVo) {
		if (StringUtils.isEmpty(tenantPriceDetailVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantPriceDetailVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPriceDetailVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
