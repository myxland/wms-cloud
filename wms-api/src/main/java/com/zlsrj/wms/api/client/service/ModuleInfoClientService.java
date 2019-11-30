package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.ModuleInfoQueryParam;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-MODULE", contextId = "ModuleInfo")
public interface ModuleInfoClientService {
	@RequestMapping(value = "/module-infos/{id}", method = RequestMethod.GET)
	public ModuleInfoVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/module-infos", method = RequestMethod.GET)
	public Page<ModuleInfoVo> page(@RequestBody ModuleInfoQueryParam moduleInfoQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/module-infos", method = RequestMethod.POST)
	public ModuleInfoVo save(@RequestBody ModuleInfo moduleInfo);

	@RequestMapping(value = "/module-infos/{id}", method = RequestMethod.PUT)
	public ModuleInfoVo updateById(@PathVariable("id") Long id, @RequestBody ModuleInfo moduleInfo);

	@RequestMapping(value = "/module-infos/{id}", method = RequestMethod.PATCH)
	public ModuleInfoVo updatePatchById(@PathVariable("id") Long id, @RequestBody ModuleInfo moduleInfo);

	@RequestMapping(value = "/module-infos/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

