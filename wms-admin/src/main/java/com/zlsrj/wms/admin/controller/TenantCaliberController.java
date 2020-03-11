package com.zlsrj.wms.admin.controller;

import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantCaliberClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantCaliberQueryParam;
import com.zlsrj.wms.api.entity.TenantCaliber;
import com.zlsrj.wms.api.vo.TenantCaliberVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表口径", tags = { "水表口径操作接口" })
@Controller
@RequestMapping("/tenantCaliber")
@Slf4j
public class TenantCaliberController {

	@Autowired
	private TenantCaliberClientService tenantCaliberClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水表口径列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCaliberVo>> list(TenantCaliberQueryParam tenantCaliberQueryParam, int pageNum,
			int pageSize) {
		Page<TenantCaliberVo> tenantCaliberVoPage = tenantCaliberClientService.page(tenantCaliberQueryParam, pageNum, pageSize, "id", "desc");
		tenantCaliberVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantCaliberVo> tenantCaliberCommonPage = CommonPage.restPage(tenantCaliberVoPage);

		return CommonResult.success(tenantCaliberCommonPage);
	}

	@ApiOperation(value = "新增水表口径")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCaliberVo> create(@RequestBody TenantCaliber tenantCaliber) {
		TenantCaliberVo tenantCaliberVo = tenantCaliberClientService.save(tenantCaliber);

		return CommonResult.success(tenantCaliberVo);
	}

	@ApiOperation(value = "根据ID查询水表口径")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCaliberVo> getById(@PathVariable("id") String id) {
		TenantCaliberVo tenantCaliberVo = tenantCaliberClientService.getById(id);
		wrappperVo(tenantCaliberVo);

		return CommonResult.success(tenantCaliberVo);
	}

	@ApiOperation(value = "根据参数更新水表口径信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCaliberVo> getById(@RequestBody TenantCaliber tenantCaliber) {
		String id = tenantCaliber.getId();
		TenantCaliberVo tenantCaliberVo = tenantCaliberClientService.updatePatchById(id, tenantCaliber);
		wrappperVo(tenantCaliberVo);

		return CommonResult.success(tenantCaliberVo);
	}
	
	@ApiOperation(value = "根据ID删除水表口径")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCaliberClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantCaliberVo tenantCaliberVo) {
		if (StringUtils.isEmpty(tenantCaliberVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantCaliberVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantCaliberVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
