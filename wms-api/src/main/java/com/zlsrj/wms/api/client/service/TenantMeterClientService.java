package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantMeterQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeter")
public interface TenantMeterClientService {
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.GET)
	public TenantMeterVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meters/list", method = RequestMethod.GET)
	public List<TenantMeterVo> list(@RequestBody TenantMeterQueryParam tenantMeterQueryParam);

	@RequestMapping(value = "/tenant-meters/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantMeterQueryParam tenantMeterQueryParam);

	@RequestMapping(value = "/tenant-meters", method = RequestMethod.GET)
	public Page<TenantMeterVo> page(@RequestBody TenantMeterQueryParam tenantMeterQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-meters/aggregation", method = RequestMethod.GET)
	public TenantMeterVo aggregation(@RequestBody TenantMeterQueryParam tenantMeterQueryParam);

	@RequestMapping(value = "/tenant-meters", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterAddParam tenantMeterAddParam);

	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterUpdateParam tenantMeterUpdateParam);

	@RequestMapping(value = "/tenant-meters/batch/{ids}", method = RequestMethod.PUT)
	public boolean updateByIds(@PathVariable("ids") String ids, @RequestBody TenantMeterBatchUpdateParam tenantMeterBatchUpdateParam);
	
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}