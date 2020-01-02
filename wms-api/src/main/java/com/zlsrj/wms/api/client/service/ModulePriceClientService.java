package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.ModulePriceQueryParam;
import com.zlsrj.wms.api.entity.ModulePrice;
import com.zlsrj.wms.api.vo.ModulePriceVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "ModulePrice")
public interface ModulePriceClientService {
	@RequestMapping(value = "/module-prices/{id}", method = RequestMethod.GET)
	public ModulePriceVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/module-prices", method = RequestMethod.GET)
	public Page<ModulePriceVo> page(@RequestBody ModulePriceQueryParam modulePriceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/module-prices", method = RequestMethod.POST)
	public ModulePriceVo save(@RequestBody ModulePrice modulePrice);

	@RequestMapping(value = "/module-prices/{id}", method = RequestMethod.PUT)
	public ModulePriceVo updateById(@PathVariable("id") Long id, @RequestBody ModulePrice modulePrice);

	@RequestMapping(value = "/module-prices/{id}", method = RequestMethod.PATCH)
	public ModulePriceVo updatePatchById(@PathVariable("id") Long id, @RequestBody ModulePrice modulePrice);

	@RequestMapping(value = "/module-prices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

