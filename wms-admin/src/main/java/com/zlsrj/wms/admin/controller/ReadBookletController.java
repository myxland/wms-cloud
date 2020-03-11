package com.zlsrj.wms.admin.controller;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.ReadBookletClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.ReadBookletQueryParam;
import com.zlsrj.wms.api.entity.ReadBooklet;
import com.zlsrj.wms.api.vo.ReadBookletVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "表册信息", tags = { "表册信息操作接口" })
@Controller
@RequestMapping("/readBooklet")
@Slf4j
public class ReadBookletController {

	@Autowired
	private ReadBookletClientService readBookletClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询表册信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<ReadBookletVo>> list(ReadBookletQueryParam readBookletQueryParam, int pageNum,
			int pageSize) {
		Page<ReadBookletVo> readBookletVoPage = readBookletClientService.page(readBookletQueryParam, pageNum, pageSize, "id", "desc");
		readBookletVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<ReadBookletVo> readBookletCommonPage = CommonPage.restPage(readBookletVoPage);

		return CommonResult.success(readBookletCommonPage);
	}

	@ApiOperation(value = "新增表册信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ReadBookletVo> create(@RequestBody ReadBooklet readBooklet) {
		ReadBookletVo readBookletVo = readBookletClientService.save(readBooklet);

		return CommonResult.success(readBookletVo);
	}

	@ApiOperation(value = "根据ID查询表册信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<ReadBookletVo> getById(@PathVariable("id") String id) {
		ReadBookletVo readBookletVo = readBookletClientService.getById(id);
		wrappperVo(readBookletVo);
		
		return CommonResult.success(readBookletVo);
	}

	@ApiOperation(value = "根据参数更新表册信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ReadBookletVo> getById(@RequestBody ReadBooklet readBooklet) {
		String id = readBooklet.getId();
		ReadBookletVo readBookletVo = readBookletClientService.updatePatchById(id, readBooklet);

		return CommonResult.success(readBookletVo);
	}
	
	@ApiOperation(value = "根据ID删除表册信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = readBookletClientService.removeById(id);

		return commonResult;
	}
	
	private void wrappperVo(ReadBookletVo readBookletVo) {
		if (StringUtils.isEmpty(readBookletVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(readBookletVo.getTenantId());
			if (tenantInfoVo != null) {
				readBookletVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
