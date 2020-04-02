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
import com.zlsrj.wms.api.client.service.TenantMeterPriceClientService;
import com.zlsrj.wms.api.dto.TenantMeterPriceAddParam;
import com.zlsrj.wms.api.dto.TenantMeterPriceQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterPriceUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterPriceVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表计费", tags = { "水表计费操作接口" })
@Controller
@RequestMapping("/tenantMeterPrice")
@Slf4j
public class TenantMeterPriceController {

	@Autowired
	private TenantMeterPriceClientService tenantMeterPriceClientService;

	@ApiOperation(value = "新增水表计费")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterPriceAddParam tenantMeterPriceAddParam) {
		String id = tenantMeterPriceClientService.save(tenantMeterPriceAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID删除水表计费")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterPriceClientService.removeById(id);

		return commonResult;
	}

	@ApiOperation(value = "根据ID查询水表计费")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterPriceVo> getById(@PathVariable("id") String id) {
		TenantMeterPriceVo tenantMeterPriceVo = tenantMeterPriceClientService.getById(id);

		return CommonResult.success(tenantMeterPriceVo);
	}
	
	@ApiOperation(value = "根据参数查询水表计费列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterPriceVo>> list(TenantMeterPriceQueryParam tenantMeterPriceQueryParam) {
		List<TenantMeterPriceVo> tenantMeterPriceVoList = tenantMeterPriceClientService.list(tenantMeterPriceQueryParam);

		return CommonResult.success(tenantMeterPriceVoList);
	}
	
	@ApiOperation(value = "根据参数查询水表计费数量")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> count(TenantMeterPriceQueryParam tenantMeterPriceQueryParam) {
		int count = tenantMeterPriceClientService.count(tenantMeterPriceQueryParam);

		return CommonResult.success(count);
	}
	
	@ApiOperation(value = "根据参数分页查询水表计费列表")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantMeterPriceVo>> page(TenantMeterPriceQueryParam tenantMeterPriceQueryParam,
	                @RequestParam(value = "page", defaultValue = "1") int page, //
	                @RequestParam(value = "rows", defaultValue = "10") int rows, //
	                @RequestParam(value = "sort", required = false) String sort, // 排序列字段名
	                @RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
	        Page<TenantMeterPriceVo> tenantMeterPriceVoPage = tenantMeterPriceClientService.page(tenantMeterPriceQueryParam, page, rows, sort, order);
	        CommonPage<TenantMeterPriceVo> tenantMeterPriceCommonPage = CommonPage.restPage(tenantMeterPriceVoPage);

	        return CommonResult.success(tenantMeterPriceCommonPage);
	}
	
	@ApiOperation(value = "根据参数更新水表计费信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterPriceUpdateParam tenantMeterPriceUpdateParam) {
		boolean success = tenantMeterPriceClientService.updateById(id, tenantMeterPriceUpdateParam);

		return CommonResult.success(success);
	}

}
