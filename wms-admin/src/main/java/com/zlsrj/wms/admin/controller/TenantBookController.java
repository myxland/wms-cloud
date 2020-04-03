package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.TenantBookClientService;
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantBookQueryParam;
import com.zlsrj.wms.api.dto.TenantBookUpdateParam;
import com.zlsrj.wms.api.vo.TenantBookReaderVo;
import com.zlsrj.wms.api.vo.TenantBookVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "表册信息", tags = { "表册信息操作接口" })
@Controller
@RequestMapping("/tenantBook")
@Slf4j
public class TenantBookController {

	@Autowired
	private TenantBookClientService tenantBookClientService;

	@ApiOperation(value = "新增表册信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantBookAddParam tenantBookAddParam) {
		String id = tenantBookClientService.save(tenantBookAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID删除表册信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantBookClientService.removeById(id);

		return commonResult;
	}

	@ApiOperation(value = "根据ID查询表册信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantBookVo> getById(@PathVariable("id") String id) {
		TenantBookVo tenantBookVo = tenantBookClientService.getById(id);

		return CommonResult.success(tenantBookVo);
	}
	
	@ApiOperation(value = "根据参数查询表册信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantBookVo>> list(TenantBookQueryParam tenantBookQueryParam) {
		List<TenantBookVo> tenantBookVoList = tenantBookClientService.list(tenantBookQueryParam);

		return CommonResult.success(tenantBookVoList);
	}
	
	@ApiOperation(value = "根据参数查询表册信息数量")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> count(TenantBookQueryParam tenantBookQueryParam) {
		int count = tenantBookClientService.count(tenantBookQueryParam);

		return CommonResult.success(count);
	}
	
	@ApiOperation(value = "根据参数查询表册信息列表")
	@RequestMapping(value = "/list/reader", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantBookReaderVo>> reader(TenantBookQueryParam tenantBookQueryParam) {
		List<TenantBookReaderVo> tenantBookReaderVoList = tenantBookClientService.reader(tenantBookQueryParam);

		return CommonResult.success(tenantBookReaderVoList);
	}
	
	@ApiOperation(value = "根据参数更新表册信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantBookUpdateParam tenantBookUpdateParam) {
		boolean success = tenantBookClientService.updateById(id, tenantBookUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "批量更新表册信息营销区域信息")
	@RequestMapping(value = "/update/marketingArea/{ids}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("ids") String ids,@RequestBody TenantBookBatchUpdateParam tenantBookBatchUpdateParam) {
		boolean success = tenantBookClientService.updateByIds(ids, tenantBookBatchUpdateParam);

		return CommonResult.success(success);
	}

}
