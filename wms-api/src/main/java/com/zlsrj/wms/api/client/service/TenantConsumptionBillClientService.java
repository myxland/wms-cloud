package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantConsumptionBillQueryParam;
import com.zlsrj.wms.api.entity.TenantConsumptionBill;
import com.zlsrj.wms.api.vo.TenantConsumptionBillVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantConsumptionBill")
public interface TenantConsumptionBillClientService {
	@RequestMapping(value = "/tenant-consumption-bills/{id}", method = RequestMethod.GET)
	public TenantConsumptionBillVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-consumption-bills", method = RequestMethod.GET)
	public Page<TenantConsumptionBillVo> page(@RequestBody TenantConsumptionBillQueryParam tenantConsumptionBillQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-consumption-bills", method = RequestMethod.POST)
	public TenantConsumptionBillVo save(@RequestBody TenantConsumptionBill tenantConsumptionBill);

	@RequestMapping(value = "/tenant-consumption-bills/{id}", method = RequestMethod.PUT)
	public TenantConsumptionBillVo updateById(@PathVariable("id") Long id, @RequestBody TenantConsumptionBill tenantConsumptionBill);

	@RequestMapping(value = "/tenant-consumption-bills/{id}", method = RequestMethod.PATCH)
	public TenantConsumptionBillVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantConsumptionBill tenantConsumptionBill);

	@RequestMapping(value = "/tenant-consumption-bills/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

