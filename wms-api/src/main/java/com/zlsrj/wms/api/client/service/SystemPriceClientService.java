package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.SystemPriceQueryParam;
import com.zlsrj.wms.api.entity.SystemPrice;
import com.zlsrj.wms.api.vo.SystemPriceVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SYSTEM", contextId = "SystemPrice")
public interface SystemPriceClientService {
	@RequestMapping(value = "/system-prices/{id}", method = RequestMethod.GET)
	public SystemPriceVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/system-prices", method = RequestMethod.GET)
	public Page<SystemPriceVo> page(@RequestBody SystemPriceQueryParam systemPriceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/system-prices", method = RequestMethod.POST)
	public SystemPriceVo save(@RequestBody SystemPrice systemPrice);

	@RequestMapping(value = "/system-prices/{id}", method = RequestMethod.PUT)
	public SystemPriceVo updateById(@PathVariable("id") Long id, @RequestBody SystemPrice systemPrice);

	@RequestMapping(value = "/system-prices/{id}", method = RequestMethod.PATCH)
	public SystemPriceVo updatePatchById(@PathVariable("id") Long id, @RequestBody SystemPrice systemPrice);

	@RequestMapping(value = "/system-prices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

