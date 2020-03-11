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
import com.zlsrj.wms.api.client.service.TenantDeptClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantDeptQueryParam;
import com.zlsrj.wms.api.entity.TenantDept;
import com.zlsrj.wms.api.vo.TenantDeptVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户部门", tags = { "租户部门操作接口" })
@Controller
@RequestMapping("/tenantDept")
@Slf4j
public class TenantDeptController {

	@Autowired
	private TenantDeptClientService tenantDeptClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询租户部门列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantDeptVo>> list(TenantDeptQueryParam tenantDeptQueryParam, int pageNum,
			int pageSize) {
		Page<TenantDeptVo> tenantDeptVoPage = tenantDeptClientService.page(tenantDeptQueryParam, pageNum, pageSize, "id", "desc");
		tenantDeptVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantDeptVo> tenantDeptCommonPage = CommonPage.restPage(tenantDeptVoPage);

		return CommonResult.success(tenantDeptCommonPage);
	}

	@ApiOperation(value = "新增租户部门")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantDeptVo> create(@RequestBody TenantDept tenantDept) {
		TenantDeptVo tenantDeptVo = tenantDeptClientService.save(tenantDept);

		return CommonResult.success(tenantDeptVo);
	}

	@ApiOperation(value = "根据ID查询租户部门")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantDeptVo> getById(@PathVariable("id") String id) {
		TenantDeptVo tenantDeptVo = tenantDeptClientService.getById(id);
		wrappperVo(tenantDeptVo);

		return CommonResult.success(tenantDeptVo);
	}

	@ApiOperation(value = "根据参数更新租户部门信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantDeptVo> getById(@RequestBody TenantDept tenantDept) {
		String id = tenantDept.getId();
		TenantDeptVo tenantDeptVo = tenantDeptClientService.updatePatchById(id, tenantDept);
		wrappperVo(tenantDeptVo);

		return CommonResult.success(tenantDeptVo);
	}
	
	@ApiOperation(value = "根据ID删除租户部门")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantDeptClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantDeptVo tenantDeptVo) {
		if (StringUtils.isEmpty(tenantDeptVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantDeptVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantDeptVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
