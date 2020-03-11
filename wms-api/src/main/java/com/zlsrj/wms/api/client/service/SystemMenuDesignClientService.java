package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.SystemMenuDesignQueryParam;
import com.zlsrj.wms.api.entity.SystemMenuDesign;
import com.zlsrj.wms.api.vo.SystemMenuDesignVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SYSTEM", contextId = "SystemMenuDesign")
public interface SystemMenuDesignClientService {
	@RequestMapping(value = "/system-menu-designs/{id}", method = RequestMethod.GET)
	public SystemMenuDesignVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/system-menu-designs", method = RequestMethod.GET)
	public Page<SystemMenuDesignVo> page(@RequestBody SystemMenuDesignQueryParam systemMenuDesignQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/system-menu-designs", method = RequestMethod.POST)
	public SystemMenuDesignVo save(@RequestBody SystemMenuDesign systemMenuDesign);

	@RequestMapping(value = "/system-menu-designs/{id}", method = RequestMethod.PUT)
	public SystemMenuDesignVo updateById(@PathVariable("id") String id, @RequestBody SystemMenuDesign systemMenuDesign);

	@RequestMapping(value = "/system-menu-designs/{id}", method = RequestMethod.PATCH)
	public SystemMenuDesignVo updatePatchById(@PathVariable("id") String id, @RequestBody SystemMenuDesign systemMenuDesign);

	@RequestMapping(value = "/system-menu-designs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

