package com.zlsrj.wms.admin.history;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.ModuleInfoClientService;
import com.zlsrj.wms.api.client.service.ModuleMenuClientService;
import com.zlsrj.wms.api.dto.ModuleMenuQueryParam;
import com.zlsrj.wms.api.entity.ModuleMenu;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.api.vo.ModuleMenuVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块菜单", tags = { "模块菜单操作接口" })
@Controller
@RequestMapping("/moduleMenu")
@Slf4j
public class ModuleMenuController {

	@Autowired
	private ModuleMenuClientService moduleMenuClientService;
	@Autowired
	private ModuleInfoClientService moduleInfoClientService;

	@ApiOperation(value = "根据参数查询模块菜单列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<ModuleMenuVo>> list(ModuleMenuQueryParam moduleMenuQueryParam, int pageNum,
			int pageSize) {
		Page<ModuleMenuVo> moduleMenuVoPage = moduleMenuClientService.page(moduleMenuQueryParam, pageNum, pageSize, "id", "desc");
		moduleMenuVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<ModuleMenuVo> moduleMenuCommonPage = CommonPage.restPage(moduleMenuVoPage);

		return CommonResult.success(moduleMenuCommonPage);
	}

	@ApiOperation(value = "更新模块菜单开放基础版")
	@RequestMapping(value = "/update/basicEditionOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateBasicEditionOn(@RequestParam("ids") String ids,
			@RequestParam("basicEditionOn") Integer basicEditionOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			String id = n;
			ModuleMenu moduleMenu = new ModuleMenu();
			moduleMenu.setBasicEditionOn(basicEditionOn);
			moduleMenuClientService.updatePatchById(id, moduleMenu);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新模块菜单开放高级版")
	@RequestMapping(value = "/update/advanceEditionOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateAdvanceEditionOn(@RequestParam("ids") String ids,
			@RequestParam("advanceEditionOn") Integer advanceEditionOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			String id = n;
			ModuleMenu moduleMenu = new ModuleMenu();
			moduleMenu.setAdvanceEditionOn(advanceEditionOn);
			moduleMenuClientService.updatePatchById(id, moduleMenu);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新模块菜单开放旗舰版")
	@RequestMapping(value = "/update/ultimateEditionOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateUltimateEditionOn(@RequestParam("ids") String ids,
			@RequestParam("ultimateEditionOn") Integer ultimateEditionOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			String id = n;
			ModuleMenu moduleMenu = new ModuleMenu();
			moduleMenu.setUltimateEditionOn(ultimateEditionOn);
			moduleMenuClientService.updatePatchById(id, moduleMenu);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "新增模块菜单")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ModuleMenuVo> create(@RequestBody ModuleMenu moduleMenu) {
		ModuleMenuVo moduleMenuVo = moduleMenuClientService.save(moduleMenu);

		return CommonResult.success(moduleMenuVo);
	}

	@ApiOperation(value = "根据ID查询模块菜单")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<ModuleMenuVo> getById(@PathVariable("id") String id) {
		ModuleMenuVo moduleMenuVo = moduleMenuClientService.getById(id);
		wrappperVo(moduleMenuVo);

		return CommonResult.success(moduleMenuVo);
	}

	@ApiOperation(value = "根据参数更新模块菜单信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ModuleMenuVo> getById(@RequestBody ModuleMenu moduleMenu) {
		String id = moduleMenu.getId();
		ModuleMenuVo moduleMenuVo = moduleMenuClientService.updatePatchById(id, moduleMenu);
		wrappperVo(moduleMenuVo);

		return CommonResult.success(moduleMenuVo);
	}
	
	@ApiOperation(value = "根据ID删除模块菜单")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = moduleMenuClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(ModuleMenuVo moduleMenuVo) {
		if (StringUtils.isEmpty(moduleMenuVo.getModuleName())) {
			ModuleInfoVo moduleInfoVo = moduleInfoClientService.getById(moduleMenuVo.getModuleId());
			if (moduleInfoVo != null) {
				moduleMenuVo.setModuleName(moduleInfoVo.getModuleName());
			}
		}
		
//		List<ModuleMenuVo> children = new ArrayList<ModuleMenuVo>();
//		ModuleMenuVo child = new ModuleMenuVo();
//		child.setId(100L);
//		child.setMenuName("XXX");
//		children.add(child);
//		
//		moduleMenuVo.setChildren(children);
		
//		if(moduleMenuVo.getId()%2 ==0) {
//			moduleMenuVo.setHasChildren(true);
//		}
		
		boolean hasChildren = moduleMenuVo.isHasChildren();
		if(hasChildren == false) {
			String parentId = moduleMenuVo.getId();
			ModuleMenuQueryParam moduleMenuQueryParam = new ModuleMenuQueryParam();
			moduleMenuQueryParam.setParentId(parentId);
			Page<ModuleMenuVo> moduleMenuVoPage = moduleMenuClientService.page(moduleMenuQueryParam, 1, 500, "id", "desc");
			if(moduleMenuVoPage!=null && moduleMenuVoPage.getTotal()>0) {
				moduleMenuVo.setHasChildren(true);
			}
		}
	}

}
