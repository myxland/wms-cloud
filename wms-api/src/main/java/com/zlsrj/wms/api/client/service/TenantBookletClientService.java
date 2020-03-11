package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantBookletQueryParam;
import com.zlsrj.wms.api.entity.TenantBooklet;
import com.zlsrj.wms.api.vo.TenantBookletVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantBooklet")
public interface TenantBookletClientService {
	@RequestMapping(value = "/tenant-booklets/{id}", method = RequestMethod.GET)
	public TenantBookletVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-booklets", method = RequestMethod.GET)
	public Page<TenantBookletVo> page(@RequestBody TenantBookletQueryParam tenantBookletQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-booklets", method = RequestMethod.POST)
	public TenantBookletVo save(@RequestBody TenantBooklet tenantBooklet);

	@RequestMapping(value = "/tenant-booklets/{id}", method = RequestMethod.PUT)
	public TenantBookletVo updateById(@PathVariable("id") String id, @RequestBody TenantBooklet tenantBooklet);

	@RequestMapping(value = "/tenant-booklets/{id}", method = RequestMethod.PATCH)
	public TenantBookletVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantBooklet tenantBooklet);

	@RequestMapping(value = "/tenant-booklets/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

