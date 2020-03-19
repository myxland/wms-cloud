package com.zlsrj.wms.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.zlsrj.wms.api.client.service.TenantDepartmentClientService;
import com.zlsrj.wms.api.client.service.TenantEmployeeClientService;
import com.zlsrj.wms.api.client.service.TenantEmployeeRoleClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.client.service.TenantRoleClientService;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantEmployeeBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantEmployeeQueryParam;
import com.zlsrj.wms.api.dto.TenantEmployeeRoleQueryParam;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.dto.TenantRoleQueryParam;
import com.zlsrj.wms.api.vo.TenantDepartmentVo;
import com.zlsrj.wms.api.vo.TenantEmployeeAndTenantRoleVo;
import com.zlsrj.wms.api.vo.TenantEmployeeRoleVo;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.api.vo.TenantRoleDataVo;
import com.zlsrj.wms.api.vo.TenantRoleVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户员工", tags = { "租户员工操作接口" })
@Controller
@RequestMapping("/tenantEmployee")
@Slf4j
public class TenantEmployeeController {

	@Autowired
	private TenantEmployeeClientService tenantEmployeeClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private TenantDepartmentClientService tenantDepartmentClientService;
	@Autowired
	private TenantRoleClientService tenantRoleClientService;
	@Autowired
	private TenantEmployeeRoleClientService tenantEmployeeRoleClientService;
	
	@ApiOperation(value = "新增租户员工")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantEmployeeAddParam tenantEmployee) {
		String id = tenantEmployeeClientService.save(tenantEmployee);
		return CommonResult.success(id);
	}
	
	@ApiOperation(value = "根据ID删除租户员工")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		//如果员工的create_type = 1 则提示：系统默认建立的员工不能删除
		TenantEmployeeVo tenantEmployeeVo  = tenantEmployeeClientService.getById(id);
		if(1==tenantEmployeeVo.getEmployeeCreateType()) {
			return CommonResult.failed("系统默认建立的员工不能删除");
		}
		
		CommonResult<Object> commonResult = tenantEmployeeClientService.removeById(id);
		return commonResult;
	}
	
	@ApiOperation(value = "根据ID删除租户员工")
	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeByIds(@PathVariable("ids") String[] ids) {
		//如果员工的create_type = 1 则提示：系统默认建立的员工不能删除
		if(ids==null || ids.length==0) {
			CommonResult.failed("参数ids为空，不能删除");
		}
		if(ids!=null && ids.length>0) {
			for(String id: ids) {
				TenantEmployeeVo tenantEmployeeVo  = tenantEmployeeClientService.getById(id);
				if(1==tenantEmployeeVo.getEmployeeCreateType()) {
					return CommonResult.failed("系统默认建立的员工"+id+"不能删除");
				}
			}
		}
		
		CommonResult<Object> commonResult = tenantEmployeeClientService.removeByIds(ids);
		return commonResult;
	}
	
	@ApiOperation(value = "根据参数更新租户员工信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantEmployeeUpdateParam tenantEmployeeUpdateParam) {
		boolean success = tenantEmployeeClientService.updateById(id, tenantEmployeeUpdateParam);
		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据参数批量更新租户员工信息")
	@RequestMapping(value = "/update/ids/{ids}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("ids") String[] ids,@RequestBody TenantEmployeeBatchUpdateParam tenantEmployeeBatchUpdateParam) {
		boolean success = tenantEmployeeClientService.updateByIds(ids, tenantEmployeeBatchUpdateParam);
		return CommonResult.success(success);
	}

	@ApiOperation(value = "根据ID查询租户员工")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantEmployeeAndTenantRoleVo> getById(@PathVariable("id") String id) {
		TenantEmployeeVo tenantEmployeeVo = tenantEmployeeClientService.getById(id);
		wrappperVo(tenantEmployeeVo);
		String jsonString = JSON.toJSONString(tenantEmployeeVo);
		TenantEmployeeAndTenantRoleVo tenantEmployeeAndTenantRoleVo = JSON.parseObject(jsonString, TenantEmployeeAndTenantRoleVo.class);
		
		String tenantId = tenantEmployeeVo.getTenantId();
		//角色信息
		TenantRoleQueryParam tenantRoleQueryParam = new TenantRoleQueryParam();
		tenantRoleQueryParam.setTenantId(tenantId);
		Page<TenantRoleVo> tenantRoleVoPage = tenantRoleClientService.page(tenantRoleQueryParam, 1, 500, "id", "asc");
		//分配角色信息
		TenantEmployeeRoleQueryParam tenantEmployeeRoleQueryParam = new TenantEmployeeRoleQueryParam();
		tenantEmployeeRoleQueryParam.setTenantId(tenantId);
		tenantEmployeeRoleQueryParam.setEmployeeId(id);
		Page<TenantEmployeeRoleVo> tenantEmployeeRoleVoPage = tenantEmployeeRoleClientService.page(tenantEmployeeRoleQueryParam, 1, 500, "id", "asc");
		
		List<TenantRoleDataVo> tenantRoleDataVoList = new ArrayList<TenantRoleDataVo>();
		
		if(tenantRoleVoPage!=null && tenantRoleVoPage.getRecords() !=null && tenantRoleVoPage.getRecords().size()>0) {
			for(TenantRoleVo tenantRoleVo:tenantRoleVoPage.getRecords()) {
				jsonString = JSON.toJSONString(tenantRoleVo);
				TenantRoleDataVo tenantRoleDataVo = JSON.parseObject(jsonString, TenantRoleDataVo.class);
				tenantRoleDataVoList.add(tenantRoleDataVo);
				tenantRoleDataVo.setIssel(0);
				
				if(tenantEmployeeRoleVoPage!=null && tenantEmployeeRoleVoPage.getRecords()!=null && tenantEmployeeRoleVoPage.getSize()>0) {
					for(TenantEmployeeRoleVo tenantEmployeeRoleVo:tenantEmployeeRoleVoPage.getRecords()) {
						if(tenantRoleVo.getId().equals(tenantEmployeeRoleVo.getRoleId())) {
							tenantRoleDataVo.setIssel(1);
						}
					}
				}
				
				
			}
			tenantEmployeeAndTenantRoleVo.setTenantRoleList(tenantRoleDataVoList);
		}
		
		

		return CommonResult.success(tenantEmployeeAndTenantRoleVo);
	}
	
	@ApiOperation(value = "根据部门查询租户员工列表")
	@RequestMapping(value = "/list/department/{departmentId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantEmployeeAndTenantRoleVo>> listByDepartment(@PathVariable("departmentId") String departmentId) {
		TenantEmployeeQueryParam tenantEmployeeQueryParam = new TenantEmployeeQueryParam();
		tenantEmployeeQueryParam.setEmployeeDepartmentId(departmentId);
		
		List<TenantEmployeeVo> tenantEmployeeVoList = tenantEmployeeClientService.list(tenantEmployeeQueryParam);
		tenantEmployeeVoList.stream().forEach(v->wrappperVo(v));
		
		List<TenantEmployeeAndTenantRoleVo> tenantEmployeeAndTenantRoleList = tenantEmployeeVoList.stream()//
				.map(e -> JSON.parseObject(JSON.toJSONString(e), TenantEmployeeAndTenantRoleVo.class))//
				.collect(Collectors.toList());
		
		return CommonResult.success(tenantEmployeeAndTenantRoleList);
	}
	
	@ApiOperation(value = "根据参数查询租户员工列表",hidden=true)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantEmployeeVo>> list(TenantEmployeeQueryParam tenantEmployeeQueryParam, int pageNum,
			int pageSize) {
		Page<TenantEmployeeVo> tenantEmployeeVoPage = tenantEmployeeClientService.page(tenantEmployeeQueryParam, pageNum, pageSize, "id", "desc");
		tenantEmployeeVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantEmployeeVo> tenantEmployeeCommonPage = CommonPage.restPage(tenantEmployeeVoPage);

		return CommonResult.success(tenantEmployeeCommonPage);
	}

	private void wrappperVo(TenantEmployeeVo tenantEmployeeVo) {
		if (StringUtils.isEmpty(tenantEmployeeVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantEmployeeVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantEmployeeVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		if (StringUtils.isEmpty(tenantEmployeeVo.getEmployeeDepartmentName())) {
			TenantDepartmentVo tenantDepartmentVo = tenantDepartmentClientService.getById(tenantEmployeeVo.getEmployeeDepartmentId());
			if (tenantDepartmentVo != null) {
				tenantEmployeeVo.setEmployeeDepartmentName(tenantDepartmentVo.getDepartmentName());
			}
		}
	}

}
