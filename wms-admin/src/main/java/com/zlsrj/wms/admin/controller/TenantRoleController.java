package com.zlsrj.wms.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.ModuleMenuClientService;
import com.zlsrj.wms.api.client.service.TenantEmployeeClientService;
import com.zlsrj.wms.api.client.service.TenantEmployeeRoleClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.client.service.TenantRoleClientService;
import com.zlsrj.wms.api.dto.TenantEmployeeQueryParam;
import com.zlsrj.wms.api.dto.TenantEmployeeRoleQueryParam;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
import com.zlsrj.wms.api.dto.TenantRoleQueryParam;
import com.zlsrj.wms.api.dto.TenantRoleUpdateParam;
import com.zlsrj.wms.api.vo.ModuleMenuVo;
import com.zlsrj.wms.api.vo.TenantEmployeeDataVo;
import com.zlsrj.wms.api.vo.TenantEmployeeRoleVo;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.api.vo.TenantRoleVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "角色信息", tags = { "角色信息操作接口" })
@Controller
@RequestMapping("/tenantRole")
@Slf4j
public class TenantRoleController {

	@Autowired
	private TenantRoleClientService tenantRoleClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private TenantEmployeeClientService tenantEmployeeClientService;
	@Autowired
	private TenantEmployeeRoleClientService tenantEmployeeRoleClientService;
	@Autowired
	private ModuleMenuClientService moduleMenuClientService;
	
	@ApiOperation(value = "新增角色信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantRoleAddParam tenantRoleAddParam) {
		String id = tenantRoleClientService.save(tenantRoleAddParam);

		return CommonResult.success(id);
	}
	
	@ApiOperation(value = "根据ID删除角色信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		TenantRoleVo tenantRoleVo = tenantRoleClientService.getById(id);
		if(tenantRoleVo!=null && 1==tenantRoleVo.getCreateType()) {
			return CommonResult.failed("系统默认建立的角色，不能删除");
		}
		
		TenantEmployeeRoleQueryParam tenantEmployeeRoleQueryParam = new TenantEmployeeRoleQueryParam();
		tenantEmployeeRoleQueryParam.setRoleId(id);
		Page<TenantEmployeeRoleVo> tenantEmployeeRoleVoPage = tenantEmployeeRoleClientService.page(tenantEmployeeRoleQueryParam, 1, 500, "id", "asc");
		if(tenantEmployeeRoleVoPage!=null && tenantEmployeeRoleVoPage.getTotal()>0) {
			tenantEmployeeRoleVoPage.getRecords().stream().map(e->JSON.toJSONString(e)).forEach(log::info);
			return CommonResult.failed("角色已分配给员工，不能删除");
		}
		
		CommonResult<Object> commonResult = tenantRoleClientService.removeById(id);
		return commonResult;
	}

	@ApiOperation(value = "根据参数查询角色信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantRoleVo>> list(TenantRoleQueryParam tenantRoleQueryParam) {
		List<TenantRoleVo> tenantRoleVoList = tenantRoleClientService.list(tenantRoleQueryParam);
		tenantRoleVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantRoleVoList);
	}
	
	@ApiOperation(value = "页面初始化")
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Map<String,Object>> init(TenantRoleQueryParam tenantRoleQueryParam) {
		String tenantId = tenantRoleQueryParam.getTenantId();
		
		TenantEmployeeQueryParam tenantEmployeeQueryParam = new TenantEmployeeQueryParam();
		tenantEmployeeQueryParam.setTenantId(tenantId);
		List<TenantEmployeeVo> tenantEmployeeVoList = tenantEmployeeClientService.list(tenantEmployeeQueryParam);
		List<TenantEmployeeDataVo> tenantEmployeeDataVoList = TranslateUtil.translateList(tenantEmployeeVoList, TenantEmployeeDataVo.class);
		
		List<ModuleMenuVo> moduleMenuList = moduleMenuClientService.selectByTenant(tenantId);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tenantEmployeeList", tenantEmployeeDataVoList);
		map.put("moduleMenuList", moduleMenuList);

		return CommonResult.success(map);
	}

	

	@ApiOperation(value = "根据ID查询角色信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Map<String,Object>> getById(@PathVariable("id") String id) {
		TenantRoleVo tenantRoleVo = tenantRoleClientService.getById(id);
		wrappperVo(tenantRoleVo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tenantRole", tenantRoleVo);
		
		
		String tenantId = tenantRoleVo.getTenantId();
		
		//菜单及菜单分配信息
		List<ModuleMenuVo> moduleMenuVoList = moduleMenuClientService.selectByRole(tenantId, id);
		map.put("moduleMenuList", moduleMenuVoList);
		
		//员工信息
		TenantEmployeeQueryParam tenantEmployeeQueryParam = new TenantEmployeeQueryParam();
		tenantEmployeeQueryParam.setTenantId(tenantId);
		Page<TenantEmployeeVo> tenantEmployeeVoPage = tenantEmployeeClientService.page(tenantEmployeeQueryParam, 1, 500, "id", "asc");
		//分配员工信息
		TenantEmployeeRoleQueryParam tenantEmployeeRoleQueryParam = new TenantEmployeeRoleQueryParam();
		tenantEmployeeRoleQueryParam.setTenantId(tenantId);
		tenantEmployeeRoleQueryParam.setRoleId(id);
		Page<TenantEmployeeRoleVo> tenantEmployeeRoleVoPage = tenantEmployeeRoleClientService.page(tenantEmployeeRoleQueryParam, 1, 500, "id", "asc");
		
		List<TenantEmployeeDataVo> tenantEmployeeDataVoList = new ArrayList<TenantEmployeeDataVo>();
		
		if(tenantEmployeeVoPage!=null && tenantEmployeeVoPage.getRecords() !=null && tenantEmployeeVoPage.getRecords().size()>0) {
			for(TenantEmployeeVo tenantEmployeeVo:tenantEmployeeVoPage.getRecords()) {
				
				TenantEmployeeDataVo tenantEmployeeDataVo = TranslateUtil.translate(tenantEmployeeVo, TenantEmployeeDataVo.class);
				
				tenantEmployeeDataVoList.add(tenantEmployeeDataVo);
				tenantEmployeeDataVo.setIssel(0);
				
				if(tenantEmployeeRoleVoPage!=null && tenantEmployeeRoleVoPage.getRecords()!=null && tenantEmployeeRoleVoPage.getSize()>0) {
					for(TenantEmployeeRoleVo tenantEmployeeRoleVo:tenantEmployeeRoleVoPage.getRecords()) {
						if(tenantEmployeeVo.getId().equals(tenantEmployeeRoleVo.getEmployeeId())) {
							tenantEmployeeDataVo.setIssel(1);
						}
					}
				}
				
				
			}
			
			map.put("tenantEmployeeList", tenantEmployeeDataVoList);
		}

		return CommonResult.success(map);
	}

	@ApiOperation(value = "根据参数更新角色信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> getById(@PathVariable("id") String id,@RequestBody TenantRoleUpdateParam tenantRoleUpdateParam) {
		boolean success = tenantRoleClientService.updateById(id, tenantRoleUpdateParam);
		return CommonResult.success(success);
	}
	
	

	private void wrappperVo(TenantRoleVo tenantRoleVo) {
		if (StringUtils.isEmpty(tenantRoleVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(tenantRoleVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantRoleVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
