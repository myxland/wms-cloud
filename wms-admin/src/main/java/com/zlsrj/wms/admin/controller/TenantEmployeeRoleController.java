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
import com.zlsrj.wms.api.client.service.TenantEmployeeRoleClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantEmployeeRoleQueryParam;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantEmployeeRoleBatch;
import com.zlsrj.wms.api.vo.TenantEmployeeRoleVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "员工角色", tags = { "员工角色操作接口" })
@Controller
@RequestMapping("/tenantEmployeeRole")
@Slf4j
public class TenantEmployeeRoleController {

	@Autowired
	private TenantEmployeeRoleClientService tenantEmployeeRoleClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询员工角色列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantEmployeeRoleVo>> list(TenantEmployeeRoleQueryParam tenantEmployeeRoleQueryParam, int pageNum,
			int pageSize) {
		Page<TenantEmployeeRoleVo> tenantEmployeeRoleVoPage = tenantEmployeeRoleClientService.page(tenantEmployeeRoleQueryParam, pageNum, pageSize, "id", "desc");
		tenantEmployeeRoleVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantEmployeeRoleVo> tenantEmployeeRoleCommonPage = CommonPage.restPage(tenantEmployeeRoleVoPage);

		return CommonResult.success(tenantEmployeeRoleCommonPage);
	}

	@ApiOperation(value = "新增员工角色")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantEmployeeRoleVo> create(@RequestBody TenantEmployeeRole tenantEmployeeRole) {
		TenantEmployeeRoleVo tenantEmployeeRoleVo = tenantEmployeeRoleClientService.save(tenantEmployeeRole);

		return CommonResult.success(tenantEmployeeRoleVo);
	}
	
	@ApiOperation(value = "批量新增员工角色")
	@RequestMapping(value = "/createBatch", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> createBatch(@RequestBody TenantEmployeeRoleBatch tenantEmployeeRoleBatch) {
		CommonResult<Object> CommonResult = tenantEmployeeRoleClientService.saveBatch(tenantEmployeeRoleBatch);

		return CommonResult;
	}
	
	@ApiOperation(value = "根据员工编号批量新增员工角色")
	@RequestMapping(value = "/createBatch/employee/{empId}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> createBatchByEmpId(@PathVariable("empId") Long empId,@RequestBody TenantEmployeeRoleBatch tenantEmployeeRoleBatch) {
		CommonResult<Object> CommonResult = tenantEmployeeRoleClientService.saveBatchByEmpId(empId,tenantEmployeeRoleBatch);

		return CommonResult;
	}
	
	@ApiOperation(value = "根据角色编号批量新增员工角色")
	@RequestMapping(value = "/createBatch/role/{roleId}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> createBatchByRoleId(@PathVariable("roleId") Long roleId,@RequestBody TenantEmployeeRoleBatch tenantEmployeeRoleBatch) {
		CommonResult<Object> CommonResult = tenantEmployeeRoleClientService.saveBatchByRoleId(roleId,tenantEmployeeRoleBatch);

		return CommonResult;
	}

	@ApiOperation(value = "根据ID查询员工角色")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantEmployeeRoleVo> getById(@PathVariable("id") Long id) {
		TenantEmployeeRoleVo tenantEmployeeRoleVo = tenantEmployeeRoleClientService.getById(id);
		wrappperVo(tenantEmployeeRoleVo);

		return CommonResult.success(tenantEmployeeRoleVo);
	}

	@ApiOperation(value = "根据参数更新员工角色信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantEmployeeRoleVo> getById(@RequestBody TenantEmployeeRole tenantEmployeeRole) {
		Long id = tenantEmployeeRole.getId();
		TenantEmployeeRoleVo tenantEmployeeRoleVo = tenantEmployeeRoleClientService.updatePatchById(id, tenantEmployeeRole);
		wrappperVo(tenantEmployeeRoleVo);

		return CommonResult.success(tenantEmployeeRoleVo);
	}
	
	@ApiOperation(value = "根据ID删除员工角色")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantEmployeeRoleClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantEmployeeRoleVo tenantEmployeeRoleVo) {
		if (StringUtils.isEmpty(tenantEmployeeRoleVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantEmployeeRoleVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantEmployeeRoleVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
