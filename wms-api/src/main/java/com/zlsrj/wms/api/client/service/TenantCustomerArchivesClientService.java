package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerArchivesVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantCustomerArchives")
public interface TenantCustomerArchivesClientService {
	@RequestMapping(value = "/tenant-customer-archivess/{id}", method = RequestMethod.GET)
	public TenantCustomerArchivesVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-customer-archivess/list", method = RequestMethod.GET)
	public List<TenantCustomerArchivesVo> list(@RequestBody TenantCustomerArchivesQueryParam tenantCustomerArchivesQueryParam);

	@RequestMapping(value = "/tenant-customer-archivess/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerArchivesQueryParam tenantCustomerArchivesQueryParam);

	@RequestMapping(value = "/tenant-customer-archivess", method = RequestMethod.GET)
	public Page<TenantCustomerArchivesVo> page(@RequestBody TenantCustomerArchivesQueryParam tenantCustomerArchivesQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-customer-archivess", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerArchivesAddParam tenantCustomerArchivesAddParam);

	@RequestMapping(value = "/tenant-customer-archivess/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerArchivesUpdateParam tenantCustomerArchivesUpdateParam);

	@RequestMapping(value = "/tenant-customer-archivess/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}