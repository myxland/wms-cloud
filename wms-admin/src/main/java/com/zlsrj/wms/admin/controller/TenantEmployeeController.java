package com.zlsrj.wms.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantDepartmentClientService;
import com.zlsrj.wms.api.client.service.TenantEmployeeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantEmployeeQueryParam;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.vo.TenantDepartmentVo;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户员工", tags = { "租户员工操作接口" })
@Controller
@RequestMapping("/tenantEmployee")
@Slf4j
public class TenantEmployeeController {

	@Autowired
	private TenantEmployeeClientService tenantEmployeeClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private TenantDepartmentClientService tenantDepartmentClientService;

	@ApiOperation(value = "根据参数查询租户员工列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantEmployeeVo>> list(TenantEmployeeQueryParam tenantEmployeeQueryParam, int pageNum,
			int pageSize) {
		Page<TenantEmployeeVo> tenantEmployeeVoPage = tenantEmployeeClientService.page(tenantEmployeeQueryParam, pageNum, pageSize, "id", "desc");
		tenantEmployeeVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantEmployeeVo> tenantEmployeeCommonPage = CommonPage.restPage(tenantEmployeeVoPage);

		return CommonResult.success(tenantEmployeeCommonPage);
	}

	@ApiOperation(value = "新增租户员工")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantEmployeeVo> create(@RequestBody TenantEmployee tenantEmployee) {
		TenantEmployeeVo tenantEmployeeVo = tenantEmployeeClientService.save(tenantEmployee);

		return CommonResult.success(tenantEmployeeVo);
	}

	@ApiOperation(value = "根据ID查询租户员工")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantEmployeeVo> getById(@PathVariable("id") Long id) {
		TenantEmployeeVo tenantEmployeeVo = tenantEmployeeClientService.getById(id);
		wrappperVo(tenantEmployeeVo);

		return CommonResult.success(tenantEmployeeVo);
	}

	@ApiOperation(value = "根据参数更新租户员工信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantEmployeeVo> getById(@RequestBody TenantEmployee tenantEmployee) {
		Long id = tenantEmployee.getId();
		TenantEmployeeVo tenantEmployeeVo = tenantEmployeeClientService.updatePatchById(id, tenantEmployee);
		wrappperVo(tenantEmployeeVo);

		return CommonResult.success(tenantEmployeeVo);
	}
	
	@ApiOperation(value = "根据ID删除租户员工")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantEmployeeClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantEmployeeVo tenantEmployeeVo) {
		if (StringUtils.isEmpty(tenantEmployeeVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantEmployeeVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantEmployeeVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		if (StringUtils.isEmpty(tenantEmployeeVo.getEmployeeDepartmentName())) {
			TenantDepartmentVo tenantDepartmentVo = tenantDepartmentClientService.getById(tenantEmployeeVo.getEmployeeDepartmentId());
			if (tenantDepartmentVo != null) {
				tenantEmployeeVo.setEmployeeDepartmentName(tenantDepartmentVo.getDepartmentName());
			}
		}
	}

}
