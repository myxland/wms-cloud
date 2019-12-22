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
import com.zlsrj.wms.api.dto.TenantRoleMenuQueryParam;
import com.zlsrj.wms.api.entity.TenantRoleMenu;
import com.zlsrj.wms.api.vo.TenantRoleMenuVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantRoleMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "角色菜单", tags = { "角色菜单操作接口" })
@RestController
@Slf4j
public class TenantRoleMenuRestController {

	@Autowired
	private ITenantRoleMenuService tenantRoleMenuService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询角色菜单")
	@RequestMapping(value = "/tenant-role-menus/{id}", method = RequestMethod.GET)
	public TenantRoleMenuVo getById(@PathVariable("id") Long id) {
		TenantRoleMenu tenantRoleMenu = tenantRoleMenuService.getById(id);

		return entity2vo(tenantRoleMenu);
	}

	@ApiOperation(value = "根据参数查询角色菜单列表")
	@RequestMapping(value = "/tenant-role-menus", method = RequestMethod.GET)
	public Page<TenantRoleMenuVo> page(@RequestBody TenantRoleMenuQueryParam tenantRoleMenuQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantRoleMenu> pageTenantRoleMenu = new Page<TenantRoleMenu>(page, rows);
		QueryWrapper<TenantRoleMenu> queryWrapperTenantRoleMenu = new QueryWrapper<TenantRoleMenu>();
		queryWrapperTenantRoleMenu.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantRoleMenu.lambda()
				.eq(tenantRoleMenuQueryParam.getId() != null, TenantRoleMenu::getId, tenantRoleMenuQueryParam.getId())
				.eq(tenantRoleMenuQueryParam.getTenantId() != null, TenantRoleMenu::getTenantId, tenantRoleMenuQueryParam.getTenantId())
				.eq(tenantRoleMenuQueryParam.getRoleId() != null, TenantRoleMenu::getRoleId, tenantRoleMenuQueryParam.getRoleId())
				.eq(tenantRoleMenuQueryParam.getModuleId() != null, TenantRoleMenu::getModuleId, tenantRoleMenuQueryParam.getModuleId())
				.eq(tenantRoleMenuQueryParam.getMenuId() != null, TenantRoleMenu::getMenuId, tenantRoleMenuQueryParam.getMenuId())
				.eq(tenantRoleMenuQueryParam.getRoleMenuOn() != null, TenantRoleMenu::getRoleMenuOn, tenantRoleMenuQueryParam.getRoleMenuOn())
				;

		IPage<TenantRoleMenu> tenantRoleMenuPage = tenantRoleMenuService.page(pageTenantRoleMenu, queryWrapperTenantRoleMenu);

		Page<TenantRoleMenuVo> tenantRoleMenuVoPage = new Page<TenantRoleMenuVo>(page, rows);
		tenantRoleMenuVoPage.setCurrent(tenantRoleMenuPage.getCurrent());
		tenantRoleMenuVoPage.setPages(tenantRoleMenuPage.getPages());
		tenantRoleMenuVoPage.setSize(tenantRoleMenuPage.getSize());
		tenantRoleMenuVoPage.setTotal(tenantRoleMenuPage.getTotal());
		tenantRoleMenuVoPage.setRecords(tenantRoleMenuPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantRoleMenuVoPage;
	}

	@ApiOperation(value = "新增角色菜单")
	@RequestMapping(value = "/tenant-role-menus", method = RequestMethod.POST)
	public TenantRoleMenuVo save(@RequestBody TenantRoleMenu tenantRoleMenu) {
		if (tenantRoleMenu.getId() == null || tenantRoleMenu.getId().compareTo(0L) <= 0) {
			tenantRoleMenu.setId(idService.selectId());
		}
		boolean success = tenantRoleMenuService.save(tenantRoleMenu);
		if (success) {
			TenantRoleMenu tenantRoleMenuDatabase = tenantRoleMenuService.getById(tenantRoleMenu.getId());
			return entity2vo(tenantRoleMenuDatabase);
		}
		log.info("save TenantRoleMenu fail，{}", ToStringBuilder.reflectionToString(tenantRoleMenu, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新角色菜单全部信息")
	@RequestMapping(value = "/tenant-role-menus/{id}", method = RequestMethod.PUT)
	public TenantRoleMenuVo updateById(@PathVariable("id") Long id, @RequestBody TenantRoleMenu tenantRoleMenu) {
		tenantRoleMenu.setId(id);
		boolean success = tenantRoleMenuService.updateById(tenantRoleMenu);
		if (success) {
			TenantRoleMenu tenantRoleMenuDatabase = tenantRoleMenuService.getById(id);
			return entity2vo(tenantRoleMenuDatabase);
		}
		log.info("update TenantRoleMenu fail，{}", ToStringBuilder.reflectionToString(tenantRoleMenu, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新角色菜单信息")
	@RequestMapping(value = "/tenant-role-menus/{id}", method = RequestMethod.PATCH)
	public TenantRoleMenuVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantRoleMenu tenantRoleMenu) {
        TenantRoleMenu tenantRoleMenuWhere = TenantRoleMenu.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantRoleMenu> updateWrapperTenantRoleMenu = new UpdateWrapper<TenantRoleMenu>();
		updateWrapperTenantRoleMenu.setEntity(tenantRoleMenuWhere);
		updateWrapperTenantRoleMenu.lambda()//
				//.eq(TenantRoleMenu::getId, id)
				// .set(tenantRoleMenu.getId() != null, TenantRoleMenu::getId, tenantRoleMenu.getId())
				.set(tenantRoleMenu.getTenantId() != null, TenantRoleMenu::getTenantId, tenantRoleMenu.getTenantId())
				.set(tenantRoleMenu.getRoleId() != null, TenantRoleMenu::getRoleId, tenantRoleMenu.getRoleId())
				.set(tenantRoleMenu.getModuleId() != null, TenantRoleMenu::getModuleId, tenantRoleMenu.getModuleId())
				.set(tenantRoleMenu.getMenuId() != null, TenantRoleMenu::getMenuId, tenantRoleMenu.getMenuId())
				.set(tenantRoleMenu.getRoleMenuOn() != null, TenantRoleMenu::getRoleMenuOn, tenantRoleMenu.getRoleMenuOn())
				;

		boolean success = tenantRoleMenuService.update(updateWrapperTenantRoleMenu);
		if (success) {
			TenantRoleMenu tenantRoleMenuDatabase = tenantRoleMenuService.getById(id);
			return entity2vo(tenantRoleMenuDatabase);
		}
		log.info("partial update TenantRoleMenu fail，{}",
				ToStringBuilder.reflectionToString(tenantRoleMenu, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除角色菜单")
	@RequestMapping(value = "/tenant-role-menus/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantRoleMenuService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantRoleMenuVo entity2vo(TenantRoleMenu tenantRoleMenu) {
		if (tenantRoleMenu == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantRoleMenu);
		TenantRoleMenuVo tenantRoleMenuVo = JSON.parseObject(jsonString, TenantRoleMenuVo.class);
		return tenantRoleMenuVo;
	}

}
