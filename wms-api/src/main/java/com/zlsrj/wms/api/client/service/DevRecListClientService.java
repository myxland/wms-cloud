package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.DevRecListQueryParam;
import com.zlsrj.wms.api.entity.DevRecList;
import com.zlsrj.wms.api.vo.DevRecListVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-ACCOUNT", contextId = "DevRecList")
public interface DevRecListClientService {
	@RequestMapping(value = "/dev-rec-lists/{id}", method = RequestMethod.GET)
	public DevRecListVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/dev-rec-lists", method = RequestMethod.GET)
	public Page<DevRecListVo> page(@RequestBody DevRecListQueryParam devRecListQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/dev-rec-lists", method = RequestMethod.POST)
	public DevRecListVo save(@RequestBody DevRecList devRecList);

	@RequestMapping(value = "/dev-rec-lists/{id}", method = RequestMethod.PUT)
	public DevRecListVo updateById(@PathVariable("id") Long id, @RequestBody DevRecList devRecList);

	@RequestMapping(value = "/dev-rec-lists/{id}", method = RequestMethod.PATCH)
	public DevRecListVo updatePatchById(@PathVariable("id") Long id, @RequestBody DevRecList devRecList);

	@RequestMapping(value = "/dev-rec-lists/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

