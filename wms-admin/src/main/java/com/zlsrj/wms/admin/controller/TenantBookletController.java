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
import com.zlsrj.wms.api.client.service.TenantBookletClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantBookletQueryParam;
import com.zlsrj.wms.api.entity.TenantBooklet;
import com.zlsrj.wms.api.vo.TenantBookletVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户表册", tags = { "租户表册操作接口" })
@Controller
@RequestMapping("/tenantBooklet")
@Slf4j
public class TenantBookletController {

	@Autowired
	private TenantBookletClientService tenantBookletClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询租户表册列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantBookletVo>> list(TenantBookletQueryParam tenantBookletQueryParam, int pageNum,
			int pageSize) {
		Page<TenantBookletVo> tenantBookletVoPage = tenantBookletClientService.page(tenantBookletQueryParam, pageNum, pageSize, "id", "desc");
		tenantBookletVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantBookletVo> tenantBookletCommonPage = CommonPage.restPage(tenantBookletVoPage);

		return CommonResult.success(tenantBookletCommonPage);
	}

	@ApiOperation(value = "新增租户表册")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantBookletVo> create(@RequestBody TenantBooklet tenantBooklet) {
		TenantBookletVo tenantBookletVo = tenantBookletClientService.save(tenantBooklet);

		return CommonResult.success(tenantBookletVo);
	}

	@ApiOperation(value = "根据ID查询租户表册")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantBookletVo> getById(@PathVariable("id") String id) {
		TenantBookletVo tenantBookletVo = tenantBookletClientService.getById(id);
		wrappperVo(tenantBookletVo);

		return CommonResult.success(tenantBookletVo);
	}

	@ApiOperation(value = "根据参数更新租户表册信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantBookletVo> getById(@RequestBody TenantBooklet tenantBooklet) {
		String id = tenantBooklet.getId();
		TenantBookletVo tenantBookletVo = tenantBookletClientService.updatePatchById(id, tenantBooklet);
		wrappperVo(tenantBookletVo);

		return CommonResult.success(tenantBookletVo);
	}
	
	@ApiOperation(value = "根据ID删除租户表册")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantBookletClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantBookletVo tenantBookletVo) {
		if (StringUtils.isEmpty(tenantBookletVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantBookletVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantBookletVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
