package com.zlsrj.wms.saas.rest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantEmployeeBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantEmployeeQueryParam;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.ITenantEmployeeService;
import com.zlsrj.wms.saas.service.ITenantInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户员工", tags = { "租户员工操作接口" })
@RestController
@Slf4j
public class TenantEmployeeRestController {

	@Autowired
	private ITenantEmployeeService tenantEmployeeService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询租户员工")
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.GET)
	public TenantEmployeeVo getById(@PathVariable("id") String id) {
		TenantEmployee tenantEmployee = tenantEmployeeService.getById(id);

		return entity2vo(tenantEmployee);
	}
	
	@ApiOperation(value = "根据参数查询租户员工列表")
	@RequestMapping(value = "/tenant-employees/list", method = RequestMethod.GET)
	public List<TenantEmployeeVo> list(@RequestBody TenantEmployeeQueryParam tenantEmployeeQueryParam) {
		QueryWrapper<TenantEmployee> queryWrapperTenantEmployee = new QueryWrapper<TenantEmployee>();
		queryWrapperTenantEmployee.lambda()
				.eq(tenantEmployeeQueryParam.getId() != null, TenantEmployee::getId, tenantEmployeeQueryParam.getId())
				.eq(tenantEmployeeQueryParam.getTenantId() != null, TenantEmployee::getTenantId, tenantEmployeeQueryParam.getTenantId())
				.eq(tenantEmployeeQueryParam.getEmployeeName() != null, TenantEmployee::getEmployeeName, tenantEmployeeQueryParam.getEmployeeName())
				.eq(tenantEmployeeQueryParam.getEmployeePassword() != null, TenantEmployee::getEmployeePassword, tenantEmployeeQueryParam.getEmployeePassword())
				.eq(tenantEmployeeQueryParam.getEmployeeDepartmentId() != null, TenantEmployee::getEmployeeDepartmentId, tenantEmployeeQueryParam.getEmployeeDepartmentId())
				.eq(tenantEmployeeQueryParam.getEmployeeLoginOn() != null, TenantEmployee::getEmployeeLoginOn, tenantEmployeeQueryParam.getEmployeeLoginOn())
				.eq(tenantEmployeeQueryParam.getEmployeeStatus() != null, TenantEmployee::getEmployeeStatus, tenantEmployeeQueryParam.getEmployeeStatus())
				.eq(tenantEmployeeQueryParam.getEmployeeMobile() != null, TenantEmployee::getEmployeeMobile, tenantEmployeeQueryParam.getEmployeeMobile())
				.eq(tenantEmployeeQueryParam.getEmployeeEmail() != null, TenantEmployee::getEmployeeEmail, tenantEmployeeQueryParam.getEmployeeEmail())
				.eq(tenantEmployeeQueryParam.getEmployeePersonalWx() != null, TenantEmployee::getEmployeePersonalWx, tenantEmployeeQueryParam.getEmployeePersonalWx())
				.eq(tenantEmployeeQueryParam.getEmployeeEnterpriceWx() != null, TenantEmployee::getEmployeeEnterpriceWx, tenantEmployeeQueryParam.getEmployeeEnterpriceWx())
				.eq(tenantEmployeeQueryParam.getEmployeeDingding() != null, TenantEmployee::getEmployeeDingding, tenantEmployeeQueryParam.getEmployeeDingding())
				.eq(tenantEmployeeQueryParam.getEmployeeCreateType() != null, TenantEmployee::getEmployeeCreateType, tenantEmployeeQueryParam.getEmployeeCreateType())
				;

		List<TenantEmployee> tenantEmployeeList = tenantEmployeeService.list(queryWrapperTenantEmployee);

		List<TenantEmployeeVo> tenantEmployeeVoList = tenantEmployeeList.stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList());

		return tenantEmployeeVoList;
	}

	@ApiOperation(value = "根据参数查询租户员工列表")
	@RequestMapping(value = "/tenant-employees", method = RequestMethod.GET)
	public Page<TenantEmployeeVo> page(@RequestBody TenantEmployeeQueryParam tenantEmployeeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantEmployee> pageTenantEmployee = new Page<TenantEmployee>(page, rows);
		QueryWrapper<TenantEmployee> queryWrapperTenantEmployee = new QueryWrapper<TenantEmployee>();
		queryWrapperTenantEmployee.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantEmployee.lambda()
				.eq(tenantEmployeeQueryParam.getId() != null, TenantEmployee::getId, tenantEmployeeQueryParam.getId())
				.eq(tenantEmployeeQueryParam.getTenantId() != null, TenantEmployee::getTenantId, tenantEmployeeQueryParam.getTenantId())
				.eq(tenantEmployeeQueryParam.getEmployeeName() != null, TenantEmployee::getEmployeeName, tenantEmployeeQueryParam.getEmployeeName())
				.eq(tenantEmployeeQueryParam.getEmployeePassword() != null, TenantEmployee::getEmployeePassword, tenantEmployeeQueryParam.getEmployeePassword())
				.eq(tenantEmployeeQueryParam.getEmployeeDepartmentId() != null, TenantEmployee::getEmployeeDepartmentId, tenantEmployeeQueryParam.getEmployeeDepartmentId())
				.eq(tenantEmployeeQueryParam.getEmployeeLoginOn() != null, TenantEmployee::getEmployeeLoginOn, tenantEmployeeQueryParam.getEmployeeLoginOn())
				.eq(tenantEmployeeQueryParam.getEmployeeStatus() != null, TenantEmployee::getEmployeeStatus, tenantEmployeeQueryParam.getEmployeeStatus())
				.eq(tenantEmployeeQueryParam.getEmployeeMobile() != null, TenantEmployee::getEmployeeMobile, tenantEmployeeQueryParam.getEmployeeMobile())
				.eq(tenantEmployeeQueryParam.getEmployeeEmail() != null, TenantEmployee::getEmployeeEmail, tenantEmployeeQueryParam.getEmployeeEmail())
				.eq(tenantEmployeeQueryParam.getEmployeePersonalWx() != null, TenantEmployee::getEmployeePersonalWx, tenantEmployeeQueryParam.getEmployeePersonalWx())
				.eq(tenantEmployeeQueryParam.getEmployeeEnterpriceWx() != null, TenantEmployee::getEmployeeEnterpriceWx, tenantEmployeeQueryParam.getEmployeeEnterpriceWx())
				.eq(tenantEmployeeQueryParam.getEmployeeDingding() != null, TenantEmployee::getEmployeeDingding, tenantEmployeeQueryParam.getEmployeeDingding())
				.eq(tenantEmployeeQueryParam.getEmployeeCreateType() != null, TenantEmployee::getEmployeeCreateType, tenantEmployeeQueryParam.getEmployeeCreateType())
				;

		IPage<TenantEmployee> tenantEmployeePage = tenantEmployeeService.page(pageTenantEmployee, queryWrapperTenantEmployee);

		Page<TenantEmployeeVo> tenantEmployeeVoPage = new Page<TenantEmployeeVo>(page, rows);
		tenantEmployeeVoPage.setCurrent(tenantEmployeePage.getCurrent());
		tenantEmployeeVoPage.setPages(tenantEmployeePage.getPages());
		tenantEmployeeVoPage.setSize(tenantEmployeePage.getSize());
		tenantEmployeeVoPage.setTotal(tenantEmployeePage.getTotal());
		tenantEmployeeVoPage.setRecords(tenantEmployeePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantEmployeeVoPage;
	}

	@ApiOperation(value = "新增租户员工")
	@RequestMapping(value = "/tenant-employees", method = RequestMethod.POST)
	public String save(@RequestBody TenantEmployeeAddParam tenantEmployeeAddParam) {
		return tenantEmployeeService.save(tenantEmployeeAddParam);
	}

	@ApiOperation(value = "更新租户员工全部信息")
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantEmployeeUpdateParam tenantEmployeeUpdateParam) {
		return  tenantEmployeeService.updateById(id,tenantEmployeeUpdateParam);
	}

	@ApiOperation(value = "根据参数更新租户员工信息")
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.PATCH)
	public boolean updatePatchById(@PathVariable("id") String id, @RequestBody TenantEmployeeUpdateParam tenantEmployeeUpdateParam) {
		return tenantEmployeeService.update(id,tenantEmployeeUpdateParam);
	}
	
	@ApiOperation(value = "根据参数批量更新租户员工信息")
	@RequestMapping(value = "/tenant-employees/ids/{ids}", method = RequestMethod.PUT)
	public boolean updateByIds(@PathVariable("ids") String[] ids, @RequestBody TenantEmployeeBatchUpdateParam tenantEmployeeBatchUpdateParam) {
		return tenantEmployeeService.updateByIds(ids,tenantEmployeeBatchUpdateParam);
	}

	@ApiOperation(value = "根据ID删除租户员工")
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantEmployeeService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
	@ApiOperation(value = "根据IDS批量删除租户员工")
	@RequestMapping(value = "/tenant-employees/ids/{ids}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeByIds(@PathVariable("ids") String[] ids) {
		if(ids==null) {
			return CommonResult.failed("ids参数为空");
		}
		boolean success = tenantEmployeeService.removeByIds(Arrays.asList(ids));
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantEmployeeVo entity2vo(TenantEmployee tenantEmployee) {
		if (tenantEmployee == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantEmployee);
		TenantEmployeeVo tenantEmployeeVo = JSON.parseObject(jsonString, TenantEmployeeVo.class);
		if (StringUtils.isEmpty(tenantEmployeeVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantEmployee.getTenantId());
			if (tenantInfo != null) {
				tenantEmployeeVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantEmployeeVo;
	}

}
