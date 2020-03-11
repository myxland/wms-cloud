package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerMeterInstallQueryParam;
import com.zlsrj.wms.api.entity.TenantCustomerMeterInstall;
import com.zlsrj.wms.api.vo.TenantCustomerMeterInstallVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantCustomerMeterInstall")
public interface TenantCustomerMeterInstallClientService {
	@RequestMapping(value = "/tenant-customer-meter-installs/{id}", method = RequestMethod.GET)
	public TenantCustomerMeterInstallVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-customer-meter-installs", method = RequestMethod.GET)
	public Page<TenantCustomerMeterInstallVo> page(@RequestBody TenantCustomerMeterInstallQueryParam tenantCustomerMeterInstallQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-customer-meter-installs", method = RequestMethod.POST)
	public TenantCustomerMeterInstallVo save(@RequestBody TenantCustomerMeterInstall tenantCustomerMeterInstall);

	@RequestMapping(value = "/tenant-customer-meter-installs/{id}", method = RequestMethod.PUT)
	public TenantCustomerMeterInstallVo updateById(@PathVariable("id") String id, @RequestBody TenantCustomerMeterInstall tenantCustomerMeterInstall);

	@RequestMapping(value = "/tenant-customer-meter-installs/{id}", method = RequestMethod.PATCH)
	public TenantCustomerMeterInstallVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantCustomerMeterInstall tenantCustomerMeterInstall);

	@RequestMapping(value = "/tenant-customer-meter-installs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

