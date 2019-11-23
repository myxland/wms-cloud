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
import com.zlsrj.wms.api.client.service.DevRecListClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.DevRecListQueryParam;
import com.zlsrj.wms.api.entity.DevRecList;
import com.zlsrj.wms.api.vo.DevRecListVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "应收流水", tags = { "应收流水操作接口" })
@Controller
@RequestMapping("/devRecList")
@Slf4j
public class DevRecListController {

	@Autowired
	private DevRecListClientService devRecListClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询应收流水列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<DevRecListVo>> list(DevRecListQueryParam devRecListQueryParam, int pageNum,
			int pageSize) {
		Page<DevRecListVo> devRecListVoPage = devRecListClientService.page(devRecListQueryParam, pageNum, pageSize, "id", "desc");
		devRecListVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<DevRecListVo> devRecListCommonPage = CommonPage.restPage(devRecListVoPage);

		return CommonResult.success(devRecListCommonPage);
	}

	@ApiOperation(value = "新增应收流水")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<DevRecListVo> create(@RequestBody DevRecList devRecList) {
		DevRecListVo devRecListVo = devRecListClientService.save(devRecList);

		return CommonResult.success(devRecListVo);
	}

	@ApiOperation(value = "根据ID查询应收流水")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<DevRecListVo> getById(@PathVariable("id") Long id) {
		DevRecListVo devRecListVo = devRecListClientService.getById(id);
		wrappperVo(devRecListVo);
		
		return CommonResult.success(devRecListVo);
	}

	@ApiOperation(value = "根据参数更新应收流水信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<DevRecListVo> getById(@RequestBody DevRecList devRecList) {
		Long id = devRecList.getId();
		DevRecListVo devRecListVo = devRecListClientService.updatePatchById(id, devRecList);

		return CommonResult.success(devRecListVo);
	}
	
	@ApiOperation(value = "根据ID删除应收流水")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = devRecListClientService.removeById(id);

		return commonResult;
	}
	
	private void wrappperVo(DevRecListVo devRecListVo) {
		if (StringUtils.isEmpty(devRecListVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(devRecListVo.getTenantId());
			if (tenantInfoVo != null) {
				devRecListVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
