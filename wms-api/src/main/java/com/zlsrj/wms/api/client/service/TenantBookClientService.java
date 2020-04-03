package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantBookQueryParam;
import com.zlsrj.wms.api.dto.TenantBookUpdateParam;
import com.zlsrj.wms.api.vo.TenantBookReaderVo;
import com.zlsrj.wms.api.vo.TenantBookVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantBook")
public interface TenantBookClientService {
	@RequestMapping(value = "/tenant-books/{id}", method = RequestMethod.GET)
	public TenantBookVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-books/list", method = RequestMethod.GET)
	public List<TenantBookVo> list(@RequestBody TenantBookQueryParam tenantBookQueryParam);

	@RequestMapping(value = "/tenant-books/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantBookQueryParam tenantBookQueryParam);

	@RequestMapping(value = "/tenant-books", method = RequestMethod.GET)
	public Page<TenantBookVo> page(@RequestBody TenantBookQueryParam tenantBookQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-books/aggregation", method = RequestMethod.GET)
	public TenantBookVo aggregation(@RequestBody TenantBookQueryParam tenantBookQueryParam);
	
	@RequestMapping(value = "/tenant-books/reader", method = RequestMethod.GET)
	public List<TenantBookReaderVo> reader(@RequestBody TenantBookQueryParam tenantBookQueryParam);

	@RequestMapping(value = "/tenant-books", method = RequestMethod.POST)
	public String save(@RequestBody TenantBookAddParam tenantBookAddParam);

	@RequestMapping(value = "/tenant-books/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantBookUpdateParam tenantBookUpdateParam);

	@RequestMapping(value = "/tenant-books/marketingArea/{ids}", method = RequestMethod.PUT)
	public boolean updateByIds(@PathVariable("ids") String ids, @RequestBody TenantBookBatchUpdateParam tenantBookBatchUpdateParam);
	
	@RequestMapping(value = "/tenant-books/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}