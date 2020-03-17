package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterCaliberAddParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterCaliber;
import com.zlsrj.wms.api.vo.TenantMeterCaliberVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterCaliber")
public interface TenantMeterCaliberClientService {
	@RequestMapping(value = "/tenant-meter-calibers/{id}", method = RequestMethod.GET)
	public TenantMeterCaliberVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-calibers/list", method = RequestMethod.GET)
	public List<TenantMeterCaliberVo> list(@RequestBody TenantMeterCaliberQueryParam tenantMeterCaliberQueryParam);
	
	@RequestMapping(value = "/tenant-meter-calibers", method = RequestMethod.GET)
	public Page<TenantMeterCaliberVo> page(@RequestBody TenantMeterCaliberQueryParam tenantMeterCaliberQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-meter-calibers", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterCaliberAddParam tenantMeterCaliberAddParam);

	@RequestMapping(value = "/tenant-meter-calibers/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterCaliberUpdateParam tenantMeterCaliberUpdateParam);

	@RequestMapping(value = "/tenant-meter-calibers/{id}", method = RequestMethod.PATCH)
	public TenantMeterCaliberVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterCaliber tenantMeterCaliber);

	@RequestMapping(value = "/tenant-meter-calibers/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

