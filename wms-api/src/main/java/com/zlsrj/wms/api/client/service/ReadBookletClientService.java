package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.ReadBookletQueryParam;
import com.zlsrj.wms.api.entity.ReadBooklet;
import com.zlsrj.wms.api.vo.ReadBookletVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-CUST", contextId = "ReadBooklet")
public interface ReadBookletClientService {
	@RequestMapping(value = "/read-booklets/{id}", method = RequestMethod.GET)
	public ReadBookletVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/read-booklets", method = RequestMethod.GET)
	public Page<ReadBookletVo> page(@RequestBody ReadBookletQueryParam readBookletQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/read-booklets", method = RequestMethod.POST)
	public ReadBookletVo save(@RequestBody ReadBooklet readBooklet);

	@RequestMapping(value = "/read-booklets/{id}", method = RequestMethod.PUT)
	public ReadBookletVo updateById(@PathVariable("id") Long id, @RequestBody ReadBooklet readBooklet);

	@RequestMapping(value = "/read-booklets/{id}", method = RequestMethod.PATCH)
	public ReadBookletVo updatePatchById(@PathVariable("id") Long id, @RequestBody ReadBooklet readBooklet);

	@RequestMapping(value = "/read-booklets/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

