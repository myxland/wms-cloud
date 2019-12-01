package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.ModuleMenuQueryParam;
import com.zlsrj.wms.api.entity.ModuleMenu;
import com.zlsrj.wms.api.vo.ModuleMenuVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-MODULE", contextId = "ModuleMenu")
public interface ModuleMenuClientService {
	@RequestMapping(value = "/module-menus/{id}", method = RequestMethod.GET)
	public ModuleMenuVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/module-menus", method = RequestMethod.GET)
	public Page<ModuleMenuVo> page(@RequestBody ModuleMenuQueryParam moduleMenuQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/module-menus", method = RequestMethod.POST)
	public ModuleMenuVo save(@RequestBody ModuleMenu moduleMenu);

	@RequestMapping(value = "/module-menus/{id}", method = RequestMethod.PUT)
	public ModuleMenuVo updateById(@PathVariable("id") Long id, @RequestBody ModuleMenu moduleMenu);

	@RequestMapping(value = "/module-menus/{id}", method = RequestMethod.PATCH)
	public ModuleMenuVo updatePatchById(@PathVariable("id") Long id, @RequestBody ModuleMenu moduleMenu);

	@RequestMapping(value = "/module-menus/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

