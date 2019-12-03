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
import com.zlsrj.wms.api.dto.TenantRoleModuleQueryParam;
import com.zlsrj.wms.api.entity.TenantRoleModule;
import com.zlsrj.wms.api.vo.TenantRoleModuleVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantRoleModuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "角色模块", tags = { "角色模块操作接口" })
@RestController
@Slf4j
public class TenantRoleModuleRestController {

	@Autowired
	private ITenantRoleModuleService tenantRoleModuleService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询角色模块")
	@RequestMapping(value = "/tenant-role-modules/{id}", method = RequestMethod.GET)
	public TenantRoleModuleVo getById(@PathVariable("id") Long id) {
		TenantRoleModule tenantRoleModule = tenantRoleModuleService.getById(id);

		return entity2vo(tenantRoleModule);
	}

	@ApiOperation(value = "根据参数查询角色模块列表")
	@RequestMapping(value = "/tenant-role-modules", method = RequestMethod.GET)
	public Page<TenantRoleModuleVo> page(@RequestBody TenantRoleModuleQueryParam tenantRoleModuleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantRoleModule> pageTenantRoleModule = new Page<TenantRoleModule>(page, rows);
		QueryWrapper<TenantRoleModule> queryWrapperTenantRoleModule = new QueryWrapper<TenantRoleModule>();
		queryWrapperTenantRoleModule.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantRoleModule.lambda()
				.eq(tenantRoleModuleQueryParam.getId() != null, TenantRoleModule::getId, tenantRoleModuleQueryParam.getId())
				.eq(tenantRoleModuleQueryParam.getTenantId() != null, TenantRoleModule::getTenantId, tenantRoleModuleQueryParam.getTenantId())
				.eq(tenantRoleModuleQueryParam.getRoleId() != null, TenantRoleModule::getRoleId, tenantRoleModuleQueryParam.getRoleId())
				.eq(tenantRoleModuleQueryParam.getModuleId() != null, TenantRoleModule::getModuleId, tenantRoleModuleQueryParam.getModuleId())
				.eq(tenantRoleModuleQueryParam.getRoleModuleOn() != null, TenantRoleModule::getRoleModuleOn, tenantRoleModuleQueryParam.getRoleModuleOn())
				;

		IPage<TenantRoleModule> tenantRoleModulePage = tenantRoleModuleService.page(pageTenantRoleModule, queryWrapperTenantRoleModule);

		Page<TenantRoleModuleVo> tenantRoleModuleVoPage = new Page<TenantRoleModuleVo>(page, rows);
		tenantRoleModuleVoPage.setCurrent(tenantRoleModulePage.getCurrent());
		tenantRoleModuleVoPage.setPages(tenantRoleModulePage.getPages());
		tenantRoleModuleVoPage.setSize(tenantRoleModulePage.getSize());
		tenantRoleModuleVoPage.setTotal(tenantRoleModulePage.getTotal());
		tenantRoleModuleVoPage.setRecords(tenantRoleModulePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantRoleModuleVoPage;
	}

	@ApiOperation(value = "新增角色模块")
	@RequestMapping(value = "/tenant-role-modules", method = RequestMethod.POST)
	public TenantRoleModuleVo save(@RequestBody TenantRoleModule tenantRoleModule) {
		if (tenantRoleModule.getId() == null || tenantRoleModule.getId().compareTo(0L) <= 0) {
			tenantRoleModule.setId(idService.selectId());
		}
		boolean success = tenantRoleModuleService.save(tenantRoleModule);
		if (success) {
			TenantRoleModule tenantRoleModuleDatabase = tenantRoleModuleService.getById(tenantRoleModule.getId());
			return entity2vo(tenantRoleModuleDatabase);
		}
		log.info("save TenantRoleModule fail，{}", ToStringBuilder.reflectionToString(tenantRoleModule, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新角色模块全部信息")
	@RequestMapping(value = "/tenant-role-modules/{id}", method = RequestMethod.PUT)
	public TenantRoleModuleVo updateById(@PathVariable("id") Long id, @RequestBody TenantRoleModule tenantRoleModule) {
		tenantRoleModule.setId(id);
		boolean success = tenantRoleModuleService.updateById(tenantRoleModule);
		if (success) {
			TenantRoleModule tenantRoleModuleDatabase = tenantRoleModuleService.getById(id);
			return entity2vo(tenantRoleModuleDatabase);
		}
		log.info("update TenantRoleModule fail，{}", ToStringBuilder.reflectionToString(tenantRoleModule, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新角色模块信息")
	@RequestMapping(value = "/tenant-role-modules/{id}", method = RequestMethod.PATCH)
	public TenantRoleModuleVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantRoleModule tenantRoleModule) {
        TenantRoleModule tenantRoleModuleWhere = TenantRoleModule.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantRoleModule> updateWrapperTenantRoleModule = new UpdateWrapper<TenantRoleModule>();
		updateWrapperTenantRoleModule.setEntity(tenantRoleModuleWhere);
		updateWrapperTenantRoleModule.lambda()//
				//.eq(TenantRoleModule::getId, id)
				// .set(tenantRoleModule.getId() != null, TenantRoleModule::getId, tenantRoleModule.getId())
				.set(tenantRoleModule.getTenantId() != null, TenantRoleModule::getTenantId, tenantRoleModule.getTenantId())
				.set(tenantRoleModule.getRoleId() != null, TenantRoleModule::getRoleId, tenantRoleModule.getRoleId())
				.set(tenantRoleModule.getModuleId() != null, TenantRoleModule::getModuleId, tenantRoleModule.getModuleId())
				.set(tenantRoleModule.getRoleModuleOn() != null, TenantRoleModule::getRoleModuleOn, tenantRoleModule.getRoleModuleOn())
				;

		boolean success = tenantRoleModuleService.update(updateWrapperTenantRoleModule);
		if (success) {
			TenantRoleModule tenantRoleModuleDatabase = tenantRoleModuleService.getById(id);
			return entity2vo(tenantRoleModuleDatabase);
		}
		log.info("partial update TenantRoleModule fail，{}",
				ToStringBuilder.reflectionToString(tenantRoleModule, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除角色模块")
	@RequestMapping(value = "/tenant-role-modules/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantRoleModuleService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantRoleModuleVo entity2vo(TenantRoleModule tenantRoleModule) {
		if (tenantRoleModule == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantRoleModule);
		TenantRoleModuleVo tenantRoleModuleVo = JSON.parseObject(jsonString, TenantRoleModuleVo.class);
		return tenantRoleModuleVo;
	}

}
