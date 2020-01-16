package com.zlsrj.wms.employee.rest;

import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantEmployeeRoleQueryParam;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantEmployeeRoleBatch;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.vo.TenantEmployeeRoleVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantEmployeeRoleService;
import com.zlsrj.wms.employee.service.ITenantEmployeeService;
import com.zlsrj.wms.employee.service.ITenantRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "员工角色", tags = { "员工角色操作接口" })
@RestController
@Slf4j
public class TenantEmployeeRoleRestController {

	@Autowired
	private ITenantEmployeeRoleService tenantEmployeeRoleService;
	@Autowired
	private ITenantEmployeeService tenantEmployeeService;
	@Autowired
	private ITenantRoleService tenantRoleService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询员工角色")
	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.GET)
	public TenantEmployeeRoleVo getById(@PathVariable("id") Long id) {
		TenantEmployeeRole tenantEmployeeRole = tenantEmployeeRoleService.getById(id);

		return entity2vo(tenantEmployeeRole);
	}

	@ApiOperation(value = "根据参数查询员工角色列表")
	@RequestMapping(value = "/tenant-employee-roles", method = RequestMethod.GET)
	public Page<TenantEmployeeRoleVo> page(@RequestBody TenantEmployeeRoleQueryParam tenantEmployeeRoleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantEmployeeRole> pageTenantEmployeeRole = new Page<TenantEmployeeRole>(page, rows);
		QueryWrapper<TenantEmployeeRole> queryWrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		queryWrapperTenantEmployeeRole.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantEmployeeRole.lambda()
				.eq(tenantEmployeeRoleQueryParam.getId() != null, TenantEmployeeRole::getId,
						tenantEmployeeRoleQueryParam.getId())
				.eq(tenantEmployeeRoleQueryParam.getTenantId() != null, TenantEmployeeRole::getTenantId,
						tenantEmployeeRoleQueryParam.getTenantId())
				.eq(tenantEmployeeRoleQueryParam.getEmpId() != null, TenantEmployeeRole::getEmpId,
						tenantEmployeeRoleQueryParam.getEmpId())
				.eq(tenantEmployeeRoleQueryParam.getRoleId() != null, TenantEmployeeRole::getRoleId,
						tenantEmployeeRoleQueryParam.getRoleId())
				.eq(tenantEmployeeRoleQueryParam.getEmpRoleOn() != null, TenantEmployeeRole::getEmpRoleOn,
						tenantEmployeeRoleQueryParam.getEmpRoleOn());

		IPage<TenantEmployeeRole> tenantEmployeeRolePage = tenantEmployeeRoleService.page(pageTenantEmployeeRole,
				queryWrapperTenantEmployeeRole);

		Page<TenantEmployeeRoleVo> tenantEmployeeRoleVoPage = new Page<TenantEmployeeRoleVo>(page, rows);
		tenantEmployeeRoleVoPage.setCurrent(tenantEmployeeRolePage.getCurrent());
		tenantEmployeeRoleVoPage.setPages(tenantEmployeeRolePage.getPages());
		tenantEmployeeRoleVoPage.setSize(tenantEmployeeRolePage.getSize());
		tenantEmployeeRoleVoPage.setTotal(tenantEmployeeRolePage.getTotal());
		tenantEmployeeRoleVoPage.setRecords(tenantEmployeeRolePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantEmployeeRoleVoPage;
	}

	@ApiOperation(value = "新增员工角色")
	@RequestMapping(value = "/tenant-employee-roles", method = RequestMethod.POST)
	public TenantEmployeeRoleVo save(@RequestBody TenantEmployeeRole tenantEmployeeRole) {
		if (tenantEmployeeRole.getId() == null || tenantEmployeeRole.getId().compareTo(0L) <= 0) {
			tenantEmployeeRole.setId(idService.selectId());
		}
		boolean success = tenantEmployeeRoleService.save(tenantEmployeeRole);
		if (success) {
			TenantEmployeeRole tenantEmployeeRoleDatabase = tenantEmployeeRoleService
					.getById(tenantEmployeeRole.getId());
			return entity2vo(tenantEmployeeRoleDatabase);
		}
		log.info("save TenantEmployeeRole fail，{}",
				ToStringBuilder.reflectionToString(tenantEmployeeRole, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "批量新增员工角色")
	@RequestMapping(value = "/tenant-employee-roles/batch", method = RequestMethod.POST)
	public CommonResult<Object> saveBatch(@RequestBody TenantEmployeeRoleBatch tenantEmployeeRoleBatch) {
		boolean success = tenantEmployeeRoleService.saveBatchTenantEmployeeRole(tenantEmployeeRoleBatch);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
	@ApiOperation(value = "根据员工批量新增员工角色")
	@RequestMapping(value = "/tenant-employee-roles/batch/employee/{empId}", method = RequestMethod.POST)
	public CommonResult<Object> saveBatchByEmpId(@PathVariable("empId") Long empId,@RequestBody TenantEmployeeRoleBatch tenantEmployeeRoleBatch) {
		boolean success = tenantEmployeeRoleService.saveBatchTenantEmployeeRoleByEmpId(empId,tenantEmployeeRoleBatch);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
	@ApiOperation(value = "根据角色批量新增员工角色")
	@RequestMapping(value = "/tenant-employee-roles/batch/role/{roleId}", method = RequestMethod.POST)
	public CommonResult<Object> saveBatchByRoleId(@PathVariable("roleId") Long roleId,@RequestBody TenantEmployeeRoleBatch tenantEmployeeRoleBatch) {
		boolean success = tenantEmployeeRoleService.saveBatchTenantEmployeeRoleByRoleId(roleId,tenantEmployeeRoleBatch);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@ApiOperation(value = "更新员工角色全部信息")
	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.PUT)
	public TenantEmployeeRoleVo updateById(@PathVariable("id") Long id,
			@RequestBody TenantEmployeeRole tenantEmployeeRole) {
		tenantEmployeeRole.setId(id);
		boolean success = tenantEmployeeRoleService.updateById(tenantEmployeeRole);
		if (success) {
			TenantEmployeeRole tenantEmployeeRoleDatabase = tenantEmployeeRoleService.getById(id);
			return entity2vo(tenantEmployeeRoleDatabase);
		}
		log.info("update TenantEmployeeRole fail，{}",
				ToStringBuilder.reflectionToString(tenantEmployeeRole, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新员工角色信息")
	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.PATCH)
	public TenantEmployeeRoleVo updatePatchById(@PathVariable("id") Long id,
			@RequestBody TenantEmployeeRole tenantEmployeeRole) {
		TenantEmployeeRole tenantEmployeeRoleWhere = TenantEmployeeRole.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantEmployeeRole> updateWrapperTenantEmployeeRole = new UpdateWrapper<TenantEmployeeRole>();
		updateWrapperTenantEmployeeRole.setEntity(tenantEmployeeRoleWhere);
		updateWrapperTenantEmployeeRole.lambda()//
				// .eq(TenantEmployeeRole::getId, id)
				// .set(tenantEmployeeRole.getId() != null, TenantEmployeeRole::getId,
				// tenantEmployeeRole.getId())
				.set(tenantEmployeeRole.getTenantId() != null, TenantEmployeeRole::getTenantId,
						tenantEmployeeRole.getTenantId())
				.set(tenantEmployeeRole.getEmpId() != null, TenantEmployeeRole::getEmpId, tenantEmployeeRole.getEmpId())
				.set(tenantEmployeeRole.getRoleId() != null, TenantEmployeeRole::getRoleId,
						tenantEmployeeRole.getRoleId())
				.set(tenantEmployeeRole.getEmpRoleOn() != null, TenantEmployeeRole::getEmpRoleOn,
						tenantEmployeeRole.getEmpRoleOn());

		boolean success = tenantEmployeeRoleService.update(updateWrapperTenantEmployeeRole);
		if (success) {
			TenantEmployeeRole tenantEmployeeRoleDatabase = tenantEmployeeRoleService.getById(id);
			return entity2vo(tenantEmployeeRoleDatabase);
		}
		log.info("partial update TenantEmployeeRole fail，{}",
				ToStringBuilder.reflectionToString(tenantEmployeeRole, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除员工角色")
	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantEmployeeRoleService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantEmployeeRoleVo entity2vo(TenantEmployeeRole tenantEmployeeRole) {
		if (tenantEmployeeRole == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantEmployeeRole);
		TenantEmployeeRoleVo tenantEmployeeRoleVo = JSON.parseObject(jsonString, TenantEmployeeRoleVo.class);
		if (StringUtils.isEmpty(tenantEmployeeRoleVo.getRoleName())) {
			TenantRole tenantRole = tenantRoleService.getById(tenantEmployeeRoleVo.getRoleId());
			if (tenantRole != null) {
				tenantEmployeeRoleVo.setRoleName(tenantRole.getRoleName());
			}
		}
		return tenantEmployeeRoleVo;
	}

}
