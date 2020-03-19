package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantChargeAgencyAddParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyQueryParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyUpdateParam;
import com.zlsrj.wms.api.entity.TenantChargeAgency;
import com.zlsrj.wms.api.vo.TenantChargeAgencyVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantChargeAgency")
public interface TenantChargeAgencyClientService {
	@RequestMapping(value = "/tenant-charge-agencys/{id}", method = RequestMethod.GET)
	public TenantChargeAgencyVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-charge-agencys/list", method = RequestMethod.GET)
	public List<TenantChargeAgencyVo> list(@RequestBody TenantChargeAgencyQueryParam tenantChargeAgencyQueryParam);
	
	@RequestMapping(value = "/tenant-charge-agencys", method = RequestMethod.GET)
	public Page<TenantChargeAgencyVo> page(@RequestBody TenantChargeAgencyQueryParam tenantChargeAgencyQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-charge-agencys", method = RequestMethod.POST)
	public String save(@RequestBody TenantChargeAgencyAddParam tenantChargeAgencyAddParam);

	@RequestMapping(value = "/tenant-charge-agencys/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantChargeAgencyUpdateParam tenantChargeAgencyUpdateParam);

	@RequestMapping(value = "/tenant-charge-agencys/{id}", method = RequestMethod.PATCH)
	public TenantChargeAgencyVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantChargeAgency tenantChargeAgency);

	@RequestMapping(value = "/tenant-charge-agencys/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

