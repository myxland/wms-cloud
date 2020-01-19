package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantPaymentDetailQueryParam;
import com.zlsrj.wms.api.entity.TenantPaymentDetail;
import com.zlsrj.wms.api.vo.TenantPaymentDetailVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantPaymentDetail")
public interface TenantPaymentDetailClientService {
	@RequestMapping(value = "/tenant-payment-details/{id}", method = RequestMethod.GET)
	public TenantPaymentDetailVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-payment-details", method = RequestMethod.GET)
	public Page<TenantPaymentDetailVo> page(@RequestBody TenantPaymentDetailQueryParam tenantPaymentDetailQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-payment-details", method = RequestMethod.POST)
	public TenantPaymentDetailVo save(@RequestBody TenantPaymentDetail tenantPaymentDetail);

	@RequestMapping(value = "/tenant-payment-details/{id}", method = RequestMethod.PUT)
	public TenantPaymentDetailVo updateById(@PathVariable("id") Long id, @RequestBody TenantPaymentDetail tenantPaymentDetail);

	@RequestMapping(value = "/tenant-payment-details/{id}", method = RequestMethod.PATCH)
	public TenantPaymentDetailVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantPaymentDetail tenantPaymentDetail);

	@RequestMapping(value = "/tenant-payment-details/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

