package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantMeterClientService;
import com.zlsrj.wms.api.dto.TenantMeterAddParam;
import com.zlsrj.wms.api.dto.TenantMeterQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表信息", tags = { "水表信息操作接口" })
@Controller
@RequestMapping("/tenantMeter")
@Slf4j
public class TenantMeterController {

	@Autowired
	private TenantMeterClientService tenantMeterClientService;

	@ApiOperation(value = "新增水表信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterAddParam tenantMeterAddParam) {
		String id = tenantMeterClientService.save(tenantMeterAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID删除水表信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterClientService.removeById(id);

		return commonResult;
	}

	@ApiOperation(value = "根据ID查询水表信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterVo> getById(@PathVariable("id") String id) {
		TenantMeterVo tenantMeterVo = tenantMeterClientService.getById(id);

		return CommonResult.success(tenantMeterVo);
	}
	
	@ApiOperation(value = "根据参数查询水表信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterVo>> list(TenantMeterQueryParam tenantMeterQueryParam) {
		List<TenantMeterVo> tenantMeterVoList = tenantMeterClientService.list(tenantMeterQueryParam);

		return CommonResult.success(tenantMeterVoList);
	}
	
	@ApiOperation(value = "根据参数查询水表信息数量")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> count(TenantMeterQueryParam tenantMeterQueryParam) {
		int count = tenantMeterClientService.count(tenantMeterQueryParam);

		return CommonResult.success(count);
	}
	
	@ApiOperation(value = "根据参数分页查询水表信息列表")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantMeterVo>> page(TenantMeterQueryParam tenantMeterQueryParam,
	                @RequestParam(value = "page", defaultValue = "1") int page, //
	                @RequestParam(value = "rows", defaultValue = "10") int rows, //
	                @RequestParam(value = "sort", required = false) String sort, // 排序列字段名
	                @RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
	        Page<TenantMeterVo> tenantMeterVoPage = tenantMeterClientService.page(tenantMeterQueryParam, page, rows, sort, order);
	        CommonPage<TenantMeterVo> tenantMeterCommonPage = CommonPage.restPage(tenantMeterVoPage);

	        return CommonResult.success(tenantMeterCommonPage);
	}
	
	@ApiOperation(value = "根据参数更新水表信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterUpdateParam tenantMeterUpdateParam) {
		boolean success = tenantMeterClientService.updateById(id, tenantMeterUpdateParam);

		return CommonResult.success(success);
	}

}
