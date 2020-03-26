package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.ModuleInfoClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.client.service.TenantModuleClientService;
import com.zlsrj.wms.api.dto.ModuleInfoQueryParam;
import com.zlsrj.wms.api.dto.TenantInfoAddParam;
import com.zlsrj.wms.api.dto.TenantInfoUpdateParam;
import com.zlsrj.wms.api.dto.TenantModuleQueryParam;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.api.vo.TenantModuleVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户信息", tags = { "租户信息操作接口" })
@Controller
@RequestMapping("/tenantInfo")
@Slf4j
public class TenantInfoController {

	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private ModuleInfoClientService moduleInfoClientService;
	@Autowired
	private TenantModuleClientService tenantModuleClientService;
	
	@ApiOperation(value = "新增租户信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantInfoAddParam tenantInfoAddParam) {
		String id = tenantInfoClientService.save(tenantInfoAddParam);
		return CommonResult.success(id);
	}
	
	@ApiOperation(value = "根据参数更新租户信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantInfoUpdateParam tenantInfoUpdateParam) {
		boolean success = tenantInfoClientService.updateById(id, tenantInfoUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除租户信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		//判断该租户是否开通了除默认开通以外的功能，如果已经开通了，则返回失败，
		//如果没有开通则直接删除租户记录和新增租户时初始化的资料记录
		
		TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(id);
		
		ModuleInfoQueryParam moduleInfoQueryParam = new ModuleInfoQueryParam();
		moduleInfoQueryParam.setOpenTarget(tenantInfoVo.getTenantType());
		moduleInfoQueryParam.setBillingMode(1);
		moduleInfoQueryParam.setModuleOn(1);
		List<ModuleInfoVo> moduleInfoVoList = moduleInfoClientService.list(moduleInfoQueryParam);
		
		TenantModuleQueryParam tenantModuleQueryParam = new TenantModuleQueryParam();
		tenantModuleQueryParam.setTenantId(tenantInfoVo.getId());
		List<TenantModuleVo> tenantModuleVoList = tenantModuleClientService.list(tenantModuleQueryParam);
		
		for(TenantModuleVo tenantModuleVo :tenantModuleVoList) {
			boolean isDefaultModue = false;//是否是默认功能
			for(ModuleInfoVo moduleInfoVo :moduleInfoVoList) {
				if(moduleInfoVo.getId().equals(tenantModuleVo.getModuleId())) {
					switch (tenantModuleVo.getModuleEdition()) {
					case 1:
						isDefaultModue = moduleInfoVo.getBasicEditionOn() == 1;
						break;
					case 2:
						isDefaultModue = moduleInfoVo.getAdvanceEditionOn() == 1;
						break;
					case 3:
						isDefaultModue = moduleInfoVo.getUltimateEditionOn() == 1;
						break;
					}
				}
			}
			if(isDefaultModue==false) {
				return CommonResult.failed("删除失败，租户开通了非默认功能");
			}
		}
		
		
		CommonResult<Object> commonResult = tenantInfoClientService.removeById(id);

		return commonResult;
	}
	

	private void wrappperVo(TenantInfoVo tenantInfoVo) {
	}

}
