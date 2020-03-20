package com.zlsrj.wms.saas.rest;

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
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.vo.TenantEmployeeRoleVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantEmployeeRoleService;
import com.zlsrj.wms.saas.service.ITenantInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "角色信息", tags = { "角色信息操作接口" })
@RestController
@Slf4j
public class TenantEmployeeRoleRestController {

	@Autowired
	private ITenantEmployeeRoleService tenantEmployeeRoleService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询角色信息")
	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.GET)
	public TenantEmployeeRoleVo getById(@PathVariable("id") String id) {
		TenantEmployeeRole tenantEmployeeRole = tenantEmployeeRoleService.getById(id);

		return entity2vo(tenantEmployeeRole);
	}

	@ApiOperation(value = "根据参数查询角色信息列表")
	@RequestMapping(value = "/tenant-employee-roles", method = RequestMethod.GET)
	public Page<TenantEmployeeRoleVo> page(@RequestBody TenantEmployeeRoleQueryParam tenantEmployeeRoleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantEmployeeRole> pageTenantEmployeeRole = new Page<TenantEmployeeRole>(page, rows);
		QueryWrapper<TenantEmployeeRole> queryWrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		queryWrapperTenantEmployeeRole.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantEmployeeRole.lambda()
				.eq(tenantEmployeeRoleQueryParam.getId() != null, TenantEmployeeRole::getId, tenantEmployeeRoleQueryParam.getId())
				.eq(tenantEmployeeRoleQueryParam.getTenantId() != null, TenantEmployeeRole::getTenantId, tenantEmployeeRoleQueryParam.getTenantId())
				.eq(tenantEmployeeRoleQueryParam.getEmployeeId() != null, TenantEmployeeRole::getEmployeeId, tenantEmployeeRoleQueryParam.getEmployeeId())
				.eq(tenantEmployeeRoleQueryParam.getRoleId() != null, TenantEmployeeRole::getRoleId, tenantEmployeeRoleQueryParam.getRoleId())
				;

		IPage<TenantEmployeeRole> tenantEmployeeRolePage = tenantEmployeeRoleService.page(pageTenantEmployeeRole, queryWrapperTenantEmployeeRole);

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

	@ApiOperation(value = "新增角色信息")
	@RequestMapping(value = "/tenant-employee-roles", method = RequestMethod.POST)
	public TenantEmployeeRoleVo save(@RequestBody TenantEmployeeRole tenantEmployeeRole) {
		if (tenantEmployeeRole.getId() == null || tenantEmployeeRole.getId().trim().length() <= 0) {
			tenantEmployeeRole.setId(idService.selectId());
		}
		boolean success = tenantEmployeeRoleService.save(tenantEmployeeRole);
		if (success) {
			TenantEmployeeRole tenantEmployeeRoleDatabase = tenantEmployeeRoleService.getById(tenantEmployeeRole.getId());
			return entity2vo(tenantEmployeeRoleDatabase);
		}
		log.info("save TenantEmployeeRole fail，{}", ToStringBuilder.reflectionToString(tenantEmployeeRole, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新角色信息全部信息")
	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.PUT)
	public TenantEmployeeRoleVo updateById(@PathVariable("id") String id, @RequestBody TenantEmployeeRole tenantEmployeeRole) {
		tenantEmployeeRole.setId(id);
		boolean success = tenantEmployeeRoleService.updateById(tenantEmployeeRole);
		if (success) {
			TenantEmployeeRole tenantEmployeeRoleDatabase = tenantEmployeeRoleService.getById(id);
			return entity2vo(tenantEmployeeRoleDatabase);
		}
		log.info("update TenantEmployeeRole fail，{}", ToStringBuilder.reflectionToString(tenantEmployeeRole, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新角色信息信息")
	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.PATCH)
	public TenantEmployeeRoleVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantEmployeeRole tenantEmployeeRole) {
        TenantEmployeeRole tenantEmployeeRoleWhere = TenantEmployeeRole.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantEmployeeRole> updateWrapperTenantEmployeeRole = new UpdateWrapper<TenantEmployeeRole>();
		updateWrapperTenantEmployeeRole.setEntity(tenantEmployeeRoleWhere);
		updateWrapperTenantEmployeeRole.lambda()//
				//.eq(TenantEmployeeRole::getId, id)
				// .set(tenantEmployeeRole.getId() != null, TenantEmployeeRole::getId, tenantEmployeeRole.getId())
				.set(tenantEmployeeRole.getTenantId() != null, TenantEmployeeRole::getTenantId, tenantEmployeeRole.getTenantId())
				.set(tenantEmployeeRole.getEmployeeId() != null, TenantEmployeeRole::getEmployeeId, tenantEmployeeRole.getEmployeeId())
				.set(tenantEmployeeRole.getRoleId() != null, TenantEmployeeRole::getRoleId, tenantEmployeeRole.getRoleId())
				;

		boolean success = tenantEmployeeRoleService.update(updateWrapperTenantEmployeeRole);
		if (success) {
			TenantEmployeeRole tenantEmployeeRoleDatabase = tenantEmployeeRoleService.getById(id);
			return entity2vo(tenantEmployeeRoleDatabase);
		}
		log.info("partial update TenantEmployeeRole fail，{}",
				ToStringBuilder.reflectionToString(tenantEmployeeRole, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除角色信息")
	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantEmployeeRoleService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantEmployeeRoleVo entity2vo(TenantEmployeeRole tenantEmployeeRole) {
		if (tenantEmployeeRole == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantEmployeeRole);
		TenantEmployeeRoleVo tenantEmployeeRoleVo = JSON.parseObject(jsonString, TenantEmployeeRoleVo.class);
		if (StringUtils.isEmpty(tenantEmployeeRoleVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantEmployeeRole.getTenantId());
			if (tenantInfo != null) {
				tenantEmployeeRoleVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantEmployeeRoleVo;
	}

}
