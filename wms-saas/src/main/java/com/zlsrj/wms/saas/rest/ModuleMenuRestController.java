package com.zlsrj.wms.saas.rest;

import java.util.List;
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
import com.zlsrj.wms.api.dto.ModuleMenuQueryParam;
import com.zlsrj.wms.api.entity.ModuleMenu;
import com.zlsrj.wms.api.vo.ModuleMenuVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.IModuleMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块菜单", tags = { "模块菜单操作接口" })
@RestController
@Slf4j
public class ModuleMenuRestController {

	@Autowired
	private IModuleMenuService moduleMenuService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询模块菜单")
	@RequestMapping(value = "/module-menus/{id}", method = RequestMethod.GET)
	public ModuleMenuVo getById(@PathVariable("id") String id) {
		ModuleMenu moduleMenu = moduleMenuService.getById(id);

		return entity2vo(moduleMenu);
	}

	@ApiOperation(value = "根据参数查询模块菜单列表")
	@RequestMapping(value = "/module-menus", method = RequestMethod.GET)
	public Page<ModuleMenuVo> page(@RequestBody ModuleMenuQueryParam moduleMenuQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<ModuleMenu> pageModuleMenu = new Page<ModuleMenu>(page, rows);
		QueryWrapper<ModuleMenu> queryWrapperModuleMenu = new QueryWrapper<ModuleMenu>();
		queryWrapperModuleMenu.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperModuleMenu.lambda()
				.eq(moduleMenuQueryParam.getId() != null, ModuleMenu::getId, moduleMenuQueryParam.getId())
				.eq(moduleMenuQueryParam.getModuleId() != null, ModuleMenu::getModuleId, moduleMenuQueryParam.getModuleId())
				.eq(moduleMenuQueryParam.getMenuName() != null, ModuleMenu::getMenuName, moduleMenuQueryParam.getMenuName())
				.eq(moduleMenuQueryParam.getMenuOrder() != null, ModuleMenu::getMenuOrder, moduleMenuQueryParam.getMenuOrder())
				.eq(moduleMenuQueryParam.getMenuIcon() != null, ModuleMenu::getMenuIcon, moduleMenuQueryParam.getMenuIcon())
				.eq(moduleMenuQueryParam.getMenuParentId() != null, ModuleMenu::getMenuParentId, moduleMenuQueryParam.getMenuParentId())
				.eq(moduleMenuQueryParam.getBasicEditionOn() != null, ModuleMenu::getBasicEditionOn, moduleMenuQueryParam.getBasicEditionOn())
				.eq(moduleMenuQueryParam.getAdvanceEditionOn() != null, ModuleMenu::getAdvanceEditionOn, moduleMenuQueryParam.getAdvanceEditionOn())
				.eq(moduleMenuQueryParam.getUltimateEditionOn() != null, ModuleMenu::getUltimateEditionOn, moduleMenuQueryParam.getUltimateEditionOn())
				.eq(moduleMenuQueryParam.getBasicUrl() != null, ModuleMenu::getBasicUrl, moduleMenuQueryParam.getBasicUrl())
				.eq(moduleMenuQueryParam.getAdvanceUrl() != null, ModuleMenu::getAdvanceUrl, moduleMenuQueryParam.getAdvanceUrl())
				.eq(moduleMenuQueryParam.getUltimateUrl() != null, ModuleMenu::getUltimateUrl, moduleMenuQueryParam.getUltimateUrl())
				.eq(moduleMenuQueryParam.getParentId()!=null,ModuleMenu::getMenuParentId, moduleMenuQueryParam.getParentId())
				.isNull(moduleMenuQueryParam.getParentId()==null, ModuleMenu::getMenuParentId)
				;

		IPage<ModuleMenu> moduleMenuPage = moduleMenuService.page(pageModuleMenu, queryWrapperModuleMenu);

		Page<ModuleMenuVo> moduleMenuVoPage = new Page<ModuleMenuVo>(page, rows);
		moduleMenuVoPage.setCurrent(moduleMenuPage.getCurrent());
		moduleMenuVoPage.setPages(moduleMenuPage.getPages());
		moduleMenuVoPage.setSize(moduleMenuPage.getSize());
		moduleMenuVoPage.setTotal(moduleMenuPage.getTotal());
		moduleMenuVoPage.setRecords(moduleMenuPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return moduleMenuVoPage;
	}

	@ApiOperation(value = "新增模块菜单")
	@RequestMapping(value = "/module-menus", method = RequestMethod.POST)
	public ModuleMenuVo save(@RequestBody ModuleMenu moduleMenu) {
		if (moduleMenu.getId() == null || moduleMenu.getId().trim().length() <= 0) {
			moduleMenu.setId(idService.selectId());
		}
		boolean success = moduleMenuService.save(moduleMenu);
		if (success) {
			ModuleMenu moduleMenuDatabase = moduleMenuService.getById(moduleMenu.getId());
			return entity2vo(moduleMenuDatabase);
		}
		log.info("save ModuleMenu fail，{}", ToStringBuilder.reflectionToString(moduleMenu, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新模块菜单全部信息")
	@RequestMapping(value = "/module-menus/{id}", method = RequestMethod.PUT)
	public ModuleMenuVo updateById(@PathVariable("id") String id, @RequestBody ModuleMenu moduleMenu) {
		moduleMenu.setId(id);
		boolean success = moduleMenuService.updateById(moduleMenu);
		if (success) {
			ModuleMenu moduleMenuDatabase = moduleMenuService.getById(id);
			return entity2vo(moduleMenuDatabase);
		}
		log.info("update ModuleMenu fail，{}", ToStringBuilder.reflectionToString(moduleMenu, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新模块菜单信息")
	@RequestMapping(value = "/module-menus/{id}", method = RequestMethod.PATCH)
	public ModuleMenuVo updatePatchById(@PathVariable("id") String id, @RequestBody ModuleMenu moduleMenu) {
        ModuleMenu moduleMenuWhere = ModuleMenu.builder()//
				.id(id)//
				.build();
		UpdateWrapper<ModuleMenu> updateWrapperModuleMenu = new UpdateWrapper<ModuleMenu>();
		updateWrapperModuleMenu.setEntity(moduleMenuWhere);
		updateWrapperModuleMenu.lambda()//
				//.eq(ModuleMenu::getId, id)
				// .set(moduleMenu.getId() != null, ModuleMenu::getId, moduleMenu.getId())
				.set(moduleMenu.getModuleId() != null, ModuleMenu::getModuleId, moduleMenu.getModuleId())
				.set(moduleMenu.getMenuName() != null, ModuleMenu::getMenuName, moduleMenu.getMenuName())
				.set(moduleMenu.getMenuOrder() != null, ModuleMenu::getMenuOrder, moduleMenu.getMenuOrder())
				.set(moduleMenu.getMenuIcon() != null, ModuleMenu::getMenuIcon, moduleMenu.getMenuIcon())
				.set(moduleMenu.getMenuParentId() != null, ModuleMenu::getMenuParentId, moduleMenu.getMenuParentId())
				.set(moduleMenu.getBasicEditionOn() != null, ModuleMenu::getBasicEditionOn, moduleMenu.getBasicEditionOn())
				.set(moduleMenu.getAdvanceEditionOn() != null, ModuleMenu::getAdvanceEditionOn, moduleMenu.getAdvanceEditionOn())
				.set(moduleMenu.getUltimateEditionOn() != null, ModuleMenu::getUltimateEditionOn, moduleMenu.getUltimateEditionOn())
				.set(moduleMenu.getBasicUrl() != null, ModuleMenu::getBasicUrl, moduleMenu.getBasicUrl())
				.set(moduleMenu.getAdvanceUrl() != null, ModuleMenu::getAdvanceUrl, moduleMenu.getAdvanceUrl())
				.set(moduleMenu.getUltimateUrl() != null, ModuleMenu::getUltimateUrl, moduleMenu.getUltimateUrl())
				;

		boolean success = moduleMenuService.update(updateWrapperModuleMenu);
		if (success) {
			ModuleMenu moduleMenuDatabase = moduleMenuService.getById(id);
			return entity2vo(moduleMenuDatabase);
		}
		log.info("partial update ModuleMenu fail，{}",
				ToStringBuilder.reflectionToString(moduleMenu, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除模块菜单")
	@RequestMapping(value = "/module-menus/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = moduleMenuService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private ModuleMenuVo entity2vo(ModuleMenu moduleMenu) {
		if (moduleMenu == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(moduleMenu);
		ModuleMenuVo moduleMenuVo = JSON.parseObject(jsonString, ModuleMenuVo.class);
		return moduleMenuVo;
	}
	
	@ApiOperation(value = "根据员工信息查询模块菜单列表")
	@RequestMapping(value = "/module-menus/employee", method = RequestMethod.GET)
	public List<ModuleMenuVo> selectByEmployee(@RequestParam(value = "tenantId") String tenantId, //
			@RequestParam(value = "employeeId") String employeeId//
	) {

		List<ModuleMenu> moduleMenuList = moduleMenuService.selectModuleMenuByEmployee(tenantId, employeeId);

		List<ModuleMenuVo> moduleMenuVoList = moduleMenuList.stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList());

		return moduleMenuVoList;
	}
	
	@ApiOperation(value = "根据租户信息查询模块菜单列表")
	@RequestMapping(value = "/module-menus/tenant", method = RequestMethod.GET)
	public List<ModuleMenuVo> selectByTenant(@RequestParam(value = "tenantId") String tenantId) {

		List<ModuleMenu> moduleMenuList = moduleMenuService.selectModuleMenuByTenant(tenantId);

		List<ModuleMenuVo> moduleMenuVoList = moduleMenuList.stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList());

		return moduleMenuVoList;
	}
	
	@ApiOperation(value = "根据角色信息查询模块菜单列表")
	@RequestMapping(value = "/module-menus/role", method = RequestMethod.GET)
	public List<ModuleMenuVo> selectByRole(@RequestParam(value = "tenantId") String tenantId,@RequestParam(value = "roleId") String roleId) {

		List<ModuleMenu> moduleMenuList = moduleMenuService.selectModuleMenuByRole(tenantId,roleId);

		List<ModuleMenuVo> moduleMenuVoList = moduleMenuList.stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList());

		return moduleMenuVoList;
	}

}
