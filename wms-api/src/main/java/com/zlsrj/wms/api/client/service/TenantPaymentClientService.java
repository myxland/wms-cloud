package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantPaymentQueryParam;
import com.zlsrj.wms.api.entity.TenantPayment;
import com.zlsrj.wms.api.vo.TenantPaymentVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantPayment")
public interface TenantPaymentClientService {
	@RequestMapping(value = "/tenant-payments/{id}", method = RequestMethod.GET)
	public TenantPaymentVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-payments", method = RequestMethod.GET)
	public Page<TenantPaymentVo> page(@RequestBody TenantPaymentQueryParam tenantPaymentQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-payments", method = RequestMethod.POST)
	public TenantPaymentVo save(@RequestBody TenantPayment tenantPayment);

	@RequestMapping(value = "/tenant-payments/{id}", method = RequestMethod.PUT)
	public TenantPaymentVo updateById(@PathVariable("id") Long id, @RequestBody TenantPayment tenantPayment);

	@RequestMapping(value = "/tenant-payments/{id}", method = RequestMethod.PATCH)
	public TenantPaymentVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantPayment tenantPayment);

	@RequestMapping(value = "/tenant-payments/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

