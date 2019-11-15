package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.SystemDesignQueryParam;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.api.vo.SystemDesignVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SYSTEM", contextId = "SystemDesign")
public interface SystemDesignClientService {
	@RequestMapping(value = "/system-designs/{id}", method = RequestMethod.GET)
	public SystemDesignVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/system-designs", method = RequestMethod.GET)
	public Page<SystemDesignVo> page(@RequestBody SystemDesignQueryParam systemDesignQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/system-designs", method = RequestMethod.POST)
	public SystemDesignVo save(@RequestBody SystemDesign systemDesign);

	@RequestMapping(value = "/system-designs/{id}", method = RequestMethod.PUT)
	public SystemDesignVo updateById(@PathVariable("id") Long id, @RequestBody SystemDesign systemDesign);

	@RequestMapping(value = "/system-designs/{id}", method = RequestMethod.PATCH)
	public SystemDesignVo updatePatchById(@PathVariable("id") Long id, @RequestBody SystemDesign systemDesign);

	@RequestMapping(value = "/system-designs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

