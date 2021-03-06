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
import com.zlsrj.wms.api.dto.TenantRoleQueryParam;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.vo.TenantRoleVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户角色", tags = { "租户角色操作接口" })
@RestController
@Slf4j
public class TenantRoleRestController {

	@Autowired
	private ITenantRoleService tenantRoleService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户角色")
	@RequestMapping(value = "/tenant-roles/{id}", method = RequestMethod.GET)
	public TenantRoleVo getById(@PathVariable("id") Long id) {
		TenantRole tenantRole = tenantRoleService.getById(id);

		return entity2vo(tenantRole);
	}

	@ApiOperation(value = "根据参数查询租户角色列表")
	@RequestMapping(value = "/tenant-roles", method = RequestMethod.GET)
	public Page<TenantRoleVo> page(@RequestBody TenantRoleQueryParam tenantRoleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantRole> pageTenantRole = new Page<TenantRole>(page, rows);
		QueryWrapper<TenantRole> queryWrapperTenantRole = new QueryWrapper<TenantRole>();
		queryWrapperTenantRole.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantRole.lambda()
				.eq(tenantRoleQueryParam.getId() != null, TenantRole::getId, tenantRoleQueryParam.getId())
				.eq(tenantRoleQueryParam.getTenantId() != null, TenantRole::getTenantId, tenantRoleQueryParam.getTenantId())
				.eq(tenantRoleQueryParam.getRoleName() != null, TenantRole::getRoleName, tenantRoleQueryParam.getRoleName())
				.eq(tenantRoleQueryParam.getRoleRemark() != null, TenantRole::getRoleRemark, tenantRoleQueryParam.getRoleRemark())
				;

		IPage<TenantRole> tenantRolePage = tenantRoleService.page(pageTenantRole, queryWrapperTenantRole);

		Page<TenantRoleVo> tenantRoleVoPage = new Page<TenantRoleVo>(page, rows);
		tenantRoleVoPage.setCurrent(tenantRolePage.getCurrent());
		tenantRoleVoPage.setPages(tenantRolePage.getPages());
		tenantRoleVoPage.setSize(tenantRolePage.getSize());
		tenantRoleVoPage.setTotal(tenantRolePage.getTotal());
		tenantRoleVoPage.setRecords(tenantRolePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantRoleVoPage;
	}

	@ApiOperation(value = "新增租户角色")
	@RequestMapping(value = "/tenant-roles", method = RequestMethod.POST)
	public TenantRoleVo save(@RequestBody TenantRole tenantRole) {
		if (tenantRole.getId() == null || tenantRole.getId().compareTo(0L) <= 0) {
			tenantRole.setId(idService.selectId());
		}
		boolean success = tenantRoleService.save(tenantRole);
		if (success) {
			TenantRole tenantRoleDatabase = tenantRoleService.getById(tenantRole.getId());
			return entity2vo(tenantRoleDatabase);
		}
		log.info("save TenantRole fail，{}", ToStringBuilder.reflectionToString(tenantRole, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户角色全部信息")
	@RequestMapping(value = "/tenant-roles/{id}", method = RequestMethod.PUT)
	public TenantRoleVo updateById(@PathVariable("id") Long id, @RequestBody TenantRole tenantRole) {
		tenantRole.setId(id);
		boolean success = tenantRoleService.updateById(tenantRole);
		if (success) {
			TenantRole tenantRoleDatabase = tenantRoleService.getById(id);
			return entity2vo(tenantRoleDatabase);
		}
		log.info("update TenantRole fail，{}", ToStringBuilder.reflectionToString(tenantRole, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户角色信息")
	@RequestMapping(value = "/tenant-roles/{id}", method = RequestMethod.PATCH)
	public TenantRoleVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantRole tenantRole) {
        TenantRole tenantRoleWhere = TenantRole.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantRole> updateWrapperTenantRole = new UpdateWrapper<TenantRole>();
		updateWrapperTenantRole.setEntity(tenantRoleWhere);
		updateWrapperTenantRole.lambda()//
				//.eq(TenantRole::getId, id)
				// .set(tenantRole.getId() != null, TenantRole::getId, tenantRole.getId())
				.set(tenantRole.getTenantId() != null, TenantRole::getTenantId, tenantRole.getTenantId())
				.set(tenantRole.getRoleName() != null, TenantRole::getRoleName, tenantRole.getRoleName())
				.set(tenantRole.getRoleRemark() != null, TenantRole::getRoleRemark, tenantRole.getRoleRemark())
				;

		boolean success = tenantRoleService.update(updateWrapperTenantRole);
		if (success) {
			TenantRole tenantRoleDatabase = tenantRoleService.getById(id);
			return entity2vo(tenantRoleDatabase);
		}
		log.info("partial update TenantRole fail，{}",
				ToStringBuilder.reflectionToString(tenantRole, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户角色")
	@RequestMapping(value = "/tenant-roles/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantRoleService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantRoleVo entity2vo(TenantRole tenantRole) {
		if (tenantRole == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantRole);
		TenantRoleVo tenantRoleVo = JSON.parseObject(jsonString, TenantRoleVo.class);
		return tenantRoleVo;
	}

}
