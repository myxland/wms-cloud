package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantEmployeeBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantEmployeeQueryParam;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantEmployee")
public interface TenantEmployeeClientService {
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.GET)
	public TenantEmployeeVo getById(@PathVariable("id") String id);
	
	@RequestMapping(value = "/tenant-employees/dictionary/{id}", method = RequestMethod.GET)
	public TenantEmployeeVo getDictionaryById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-employees/list", method = RequestMethod.GET)
	public List<TenantEmployeeVo> list(@RequestBody TenantEmployeeQueryParam tenantEmployeeQueryParam);
	
	@RequestMapping(value = "/tenant-employees", method = RequestMethod.GET)
	public Page<TenantEmployeeVo> page(@RequestBody TenantEmployeeQueryParam tenantEmployeeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-employees", method = RequestMethod.POST)
	public String save(@RequestBody TenantEmployeeAddParam tenantEmployeeAddParam);

	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantEmployeeUpdateParam tenantEmployeeUpdateParam);

	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.PATCH)
	public boolean updatePatchById(@PathVariable("id") String id, @RequestBody TenantEmployeeUpdateParam tenantEmployeeUpdateParam);
	
	@RequestMapping(value = "/tenant-employees/ids/{ids}", method = RequestMethod.PUT)
	public boolean updateByIds(@PathVariable("ids") String[] ids, @RequestBody TenantEmployeeBatchUpdateParam tenantEmployeeBatchUpdateParam);
	
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
	
	@RequestMapping(value = "/tenant-employees/ids/{ids}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeByIds(@PathVariable("ids") String[] ids);
	
	@RequestMapping(value = "/tenant-employees/update/password/{id}", method = RequestMethod.PUT)
	public boolean updatePassword(@PathVariable("id") String id, @RequestBody String plainPassword);
	
	@RequestMapping(value = "/tenant-employees/reset/password/{id}", method = RequestMethod.PUT)
	public boolean resetPassword(@PathVariable("id") String id);
}

