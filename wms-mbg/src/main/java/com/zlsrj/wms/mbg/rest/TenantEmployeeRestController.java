package com.zlsrj.wms.mbg.rest;

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
import com.zlsrj.wms.api.dto.TenantEmployeeQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantEmployeeService;

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
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户员工")
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.GET)
	public TenantEmployeeVo getById(@PathVariable("id") Long id) {
		TenantEmployee tenantEmployee = tenantEmployeeService.getById(id);

		return entity2vo(tenantEmployee);
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
		queryWrapperTenantEmployee.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantEmployee.lambda()
						.eq(tenantEmployeeQueryParam.getId() != null, TenantEmployee::getId, tenantEmployeeQueryParam.getId())
						.eq(tenantEmployeeQueryParam.getTenantId() != null, TenantEmployee::getTenantId, tenantEmployeeQueryParam.getTenantId())
						.eq(tenantEmployeeQueryParam.getEmpName() != null, TenantEmployee::getEmpName, tenantEmployeeQueryParam.getEmpName())
						.eq(tenantEmployeeQueryParam.getEmpPassword() != null, TenantEmployee::getEmpPassword, tenantEmployeeQueryParam.getEmpPassword())
						.eq(tenantEmployeeQueryParam.getDeptId() != null, TenantEmployee::getDeptId, tenantEmployeeQueryParam.getDeptId())
						.eq(tenantEmployeeQueryParam.getLoginOn() != null, TenantEmployee::getLoginOn, tenantEmployeeQueryParam.getLoginOn())
						.eq(tenantEmployeeQueryParam.getEmpStatus() != null, TenantEmployee::getEmpStatus, tenantEmployeeQueryParam.getEmpStatus())
						.eq(tenantEmployeeQueryParam.getEmpMobile() != null, TenantEmployee::getEmpMobile, tenantEmployeeQueryParam.getEmpMobile())
						.eq(tenantEmployeeQueryParam.getEmpEmail() != null, TenantEmployee::getEmpEmail, tenantEmployeeQueryParam.getEmpEmail())
						.eq(tenantEmployeeQueryParam.getEmpPersonalWx() != null, TenantEmployee::getEmpPersonalWx, tenantEmployeeQueryParam.getEmpPersonalWx())
						.eq(tenantEmployeeQueryParam.getEmpEnterpriceWx() != null, TenantEmployee::getEmpEnterpriceWx, tenantEmployeeQueryParam.getEmpEnterpriceWx())
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
	public TenantEmployeeVo save(@RequestBody TenantEmployee tenantEmployee) {
		if (tenantEmployee.getId() == null || tenantEmployee.getId().compareTo(0L) <= 0) {
			tenantEmployee.setId(idService.selectId());
		}
		boolean success = tenantEmployeeService.save(tenantEmployee);
		if (success) {
			TenantEmployee tenantEmployeeDatabase = tenantEmployeeService.getById(tenantEmployee.getId());
			return entity2vo(tenantEmployeeDatabase);
		}
		log.info("save TenantEmployee fail，{}", ToStringBuilder.reflectionToString(tenantEmployee, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户员工全部信息")
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.PUT)
	public TenantEmployeeVo updateById(@PathVariable("id") Long id, @RequestBody TenantEmployee tenantEmployee) {
		tenantEmployee.setId(id);
		boolean success = tenantEmployeeService.updateById(tenantEmployee);
		if (success) {
			TenantEmployee tenantEmployeeDatabase = tenantEmployeeService.getById(id);
			return entity2vo(tenantEmployeeDatabase);
		}
		log.info("update TenantEmployee fail，{}", ToStringBuilder.reflectionToString(tenantEmployee, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户员工信息")
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.PATCH)
	public TenantEmployeeVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantEmployee tenantEmployee) {
		UpdateWrapper<TenantEmployee> updateWrapperTenantEmployee = new UpdateWrapper<TenantEmployee>();
		updateWrapperTenantEmployee.lambda()//
				.eq(TenantEmployee::getId, id)
				// .set(tenantEmployee.getId() != null, TenantEmployee::getId, tenantEmployee.getId())
				.set(tenantEmployee.getTenantId() != null, TenantEmployee::getTenantId, tenantEmployee.getTenantId())
				.set(tenantEmployee.getEmpName() != null, TenantEmployee::getEmpName, tenantEmployee.getEmpName())
				.set(tenantEmployee.getEmpPassword() != null, TenantEmployee::getEmpPassword, tenantEmployee.getEmpPassword())
				.set(tenantEmployee.getDeptId() != null, TenantEmployee::getDeptId, tenantEmployee.getDeptId())
				.set(tenantEmployee.getLoginOn() != null, TenantEmployee::getLoginOn, tenantEmployee.getLoginOn())
				.set(tenantEmployee.getEmpStatus() != null, TenantEmployee::getEmpStatus, tenantEmployee.getEmpStatus())
				.set(tenantEmployee.getEmpMobile() != null, TenantEmployee::getEmpMobile, tenantEmployee.getEmpMobile())
				.set(tenantEmployee.getEmpEmail() != null, TenantEmployee::getEmpEmail, tenantEmployee.getEmpEmail())
				.set(tenantEmployee.getEmpPersonalWx() != null, TenantEmployee::getEmpPersonalWx, tenantEmployee.getEmpPersonalWx())
				.set(tenantEmployee.getEmpEnterpriceWx() != null, TenantEmployee::getEmpEnterpriceWx, tenantEmployee.getEmpEnterpriceWx())
				;

		boolean success = tenantEmployeeService.update(updateWrapperTenantEmployee);
		if (success) {
			TenantEmployee tenantEmployeeDatabase = tenantEmployeeService.getById(id);
			return entity2vo(tenantEmployeeDatabase);
		}
		log.info("partial update TenantEmployee fail，{}",
				ToStringBuilder.reflectionToString(tenantEmployee, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户员工")
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantEmployeeService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantEmployeeVo entity2vo(TenantEmployee tenantEmployee) {
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
