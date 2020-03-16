package com.zlsrj.wms.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantDepartmentClientService;
import com.zlsrj.wms.api.client.service.TenantEmployeeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantDepartmentAddParam;
import com.zlsrj.wms.api.dto.TenantDepartmentQueryParam;
import com.zlsrj.wms.api.dto.TenantDepartmentUpdateParam;
import com.zlsrj.wms.api.dto.TenantEmployeeQueryParam;
import com.zlsrj.wms.api.vo.TenantDepartmentDataVo;
import com.zlsrj.wms.api.vo.TenantDepartmentVo;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户部门", tags = { "租户部门操作接口" })
@Controller
@RequestMapping("/tenantDepartment")
@Slf4j
public class TenantDepartmentController {

	@Autowired
	private TenantDepartmentClientService tenantDepartmentClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private TenantEmployeeClientService tenantEmployeeClientService;
	
	@ApiOperation(value = "根据ID查询租户部门")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantDepartmentDataVo> getById(@PathVariable("id") String id) {
		TenantDepartmentVo tenantDepartmentVo = tenantDepartmentClientService.getById(id);
		wrappperVo(tenantDepartmentVo);
		
		String jsonString = JSON.toJSONString(tenantDepartmentVo);
		TenantDepartmentDataVo tenantDepartmentDataVo = JSON.parseObject(jsonString, TenantDepartmentDataVo.class);

		return CommonResult.success(tenantDepartmentDataVo);
	}

	@ApiOperation(value = "根据参数查询租户部门列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantDepartmentDataVo>> list(TenantDepartmentQueryParam tenantDepartmentQueryParam) {
		List<TenantDepartmentVo> tenantDepartmentVoList = tenantDepartmentClientService
				.list(tenantDepartmentQueryParam);
		tenantDepartmentVoList.stream().forEach(v -> wrappperVo(v));

		List<TenantDepartmentDataVo> tenantDepartmentDataVoList = tenantDepartmentVoList.stream()//
				.map(e -> JSON.parseObject(JSON.toJSONString(e), TenantDepartmentDataVo.class))//
				.collect(Collectors.toList());

		return CommonResult.success(tenantDepartmentDataVoList);
	}
	
	@ApiOperation(value = "根据ID删除租户部门")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		//如果该部门没有下级部门及没有员工，则直接删除
		TenantDepartmentVo tenantDepartmentVo  = tenantDepartmentClientService.getById(id);
		wrappperVo(tenantDepartmentVo);
		if(tenantDepartmentVo.isHasChildren()) {
			return CommonResult.failed("部门包含下级部门，不能删除");
		}
		
		TenantEmployeeQueryParam tenantEmployeeQueryParam = new TenantEmployeeQueryParam();
		tenantEmployeeQueryParam.setEmployeeDepartmentId(id);
		
		List<TenantEmployeeVo> tenantEmployeeVoList = tenantEmployeeClientService.list(tenantEmployeeQueryParam);
		if(tenantEmployeeVoList!=null && tenantEmployeeVoList.size()>0) {
			return CommonResult.failed("部门包含员工，不能删除");
		}
		
		CommonResult<Object> commonResult = tenantDepartmentClientService.removeById(id);

		return commonResult;
	}

	@ApiOperation(value = "新增租户部门")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantDepartmentAddParam tenantDepartmentAddParam) {
		String id = tenantDepartmentClientService.save(tenantDepartmentAddParam);

		return CommonResult.success(id);
	}

	

	@ApiOperation(value = "根据参数更新租户部门信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> getById(@PathVariable("id") String id,@RequestBody TenantDepartmentUpdateParam tenantDepartmentUpdateParam) {
		boolean success = tenantDepartmentClientService.updateById(id, tenantDepartmentUpdateParam);

		return CommonResult.success(success);
	}
	


	private void wrappperVo(TenantDepartmentVo tenantDepartmentVo) {
		if (StringUtils.isEmpty(tenantDepartmentVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantDepartmentVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantDepartmentVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		boolean hasChildren = tenantDepartmentVo.isHasChildren();
		if(hasChildren == false) {
			String parentId = tenantDepartmentVo.getId();
			TenantDepartmentQueryParam tenantDepartmentQueryParam = new TenantDepartmentQueryParam();
			tenantDepartmentQueryParam.setParentId(parentId);
			Page<TenantDepartmentVo> tenantDepartmentVoPage = tenantDepartmentClientService.page(tenantDepartmentQueryParam, 1, 500, "id", "desc");
			if(tenantDepartmentVoPage!=null && tenantDepartmentVoPage.getTotal()>0) {
				tenantDepartmentVo.setHasChildren(true);
			}
		}
	}

}
