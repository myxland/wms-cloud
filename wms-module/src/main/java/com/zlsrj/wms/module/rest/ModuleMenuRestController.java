package com.zlsrj.wms.module.rest;

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
import com.zlsrj.wms.module.service.IIdService;
import com.zlsrj.wms.module.service.IModuleMenuService;

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
	public ModuleMenuVo getById(@PathVariable("id") Long id) {
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
				.eq(moduleMenuQueryParam.getParentMenuId() != null, ModuleMenu::getParentMenuId, moduleMenuQueryParam.getParentMenuId())
				.eq(moduleMenuQueryParam.getModuleId() != null, ModuleMenu::getModuleId, moduleMenuQueryParam.getModuleId())
				.eq(moduleMenuQueryParam.getMenuName() != null, ModuleMenu::getMenuName, moduleMenuQueryParam.getMenuName())
				.like(moduleMenuQueryParam.getMenuNameLike() != null, ModuleMenu::getMenuName,moduleMenuQueryParam.getMenuNameLike())
				.eq(moduleMenuQueryParam.getMenuOrder() != null, ModuleMenu::getMenuOrder, moduleMenuQueryParam.getMenuOrder())
				.eq(moduleMenuQueryParam.getMenuIcon() != null, ModuleMenu::getMenuIcon, moduleMenuQueryParam.getMenuIcon())
				.eq(moduleMenuQueryParam.getBasicOn() != null, ModuleMenu::getBasicOn, moduleMenuQueryParam.getBasicOn())
				.eq(moduleMenuQueryParam.getAdvanceOn() != null, ModuleMenu::getAdvanceOn, moduleMenuQueryParam.getAdvanceOn())
				.eq(moduleMenuQueryParam.getUltimateOn() != null, ModuleMenu::getUltimateOn, moduleMenuQueryParam.getUltimateOn())
				.eq(moduleMenuQueryParam.getBasicUrl() != null, ModuleMenu::getBasicUrl, moduleMenuQueryParam.getBasicUrl())
				.eq(moduleMenuQueryParam.getAdvanceUrl() != null, ModuleMenu::getAdvanceUrl, moduleMenuQueryParam.getAdvanceUrl())
				.eq(moduleMenuQueryParam.getUltimateUrl() != null, ModuleMenu::getUltimateUrl, moduleMenuQueryParam.getUltimateUrl())
				.eq(moduleMenuQueryParam.getPerms() != null, ModuleMenu::getPerms, moduleMenuQueryParam.getPerms())
				.eq(moduleMenuQueryParam.getMenuType() != null, ModuleMenu::getMenuType, moduleMenuQueryParam.getMenuType())
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
		if (moduleMenu.getId() == null || moduleMenu.getId().compareTo(0L) <= 0) {
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
	public ModuleMenuVo updateById(@PathVariable("id") Long id, @RequestBody ModuleMenu moduleMenu) {
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
	public ModuleMenuVo updatePatchById(@PathVariable("id") Long id, @RequestBody ModuleMenu moduleMenu) {
        ModuleMenu moduleMenuWhere = ModuleMenu.builder()//
				.id(id)//
				.build();
		UpdateWrapper<ModuleMenu> updateWrapperModuleMenu = new UpdateWrapper<ModuleMenu>();
		updateWrapperModuleMenu.setEntity(moduleMenuWhere);
		updateWrapperModuleMenu.lambda()//
				//.eq(ModuleMenu::getId, id)
				// .set(moduleMenu.getId() != null, ModuleMenu::getId, moduleMenu.getId())
				.set(moduleMenu.getParentMenuId() != null, ModuleMenu::getParentMenuId, moduleMenu.getParentMenuId())
				.set(moduleMenu.getModuleId() != null, ModuleMenu::getModuleId, moduleMenu.getModuleId())
				.set(moduleMenu.getMenuName() != null, ModuleMenu::getMenuName, moduleMenu.getMenuName())
				.set(moduleMenu.getMenuOrder() != null, ModuleMenu::getMenuOrder, moduleMenu.getMenuOrder())
				.set(moduleMenu.getMenuIcon() != null, ModuleMenu::getMenuIcon, moduleMenu.getMenuIcon())
				.set(moduleMenu.getBasicOn() != null, ModuleMenu::getBasicOn, moduleMenu.getBasicOn())
				.set(moduleMenu.getAdvanceOn() != null, ModuleMenu::getAdvanceOn, moduleMenu.getAdvanceOn())
				.set(moduleMenu.getUltimateOn() != null, ModuleMenu::getUltimateOn, moduleMenu.getUltimateOn())
				.set(moduleMenu.getBasicUrl() != null, ModuleMenu::getBasicUrl, moduleMenu.getBasicUrl())
				.set(moduleMenu.getAdvanceUrl() != null, ModuleMenu::getAdvanceUrl, moduleMenu.getAdvanceUrl())
				.set(moduleMenu.getUltimateUrl() != null, ModuleMenu::getUltimateUrl, moduleMenu.getUltimateUrl())
				.set(moduleMenu.getPerms() != null, ModuleMenu::getPerms, moduleMenu.getPerms())
				.set(moduleMenu.getMenuType() != null, ModuleMenu::getMenuType, moduleMenu.getMenuType())
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
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
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

}
