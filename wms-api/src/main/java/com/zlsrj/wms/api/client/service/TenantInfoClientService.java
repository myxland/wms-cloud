package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantInfoAddParam;
import com.zlsrj.wms.api.dto.TenantInfoModuleInfoUpdateParam;
import com.zlsrj.wms.api.dto.TenantInfoQueryParam;
import com.zlsrj.wms.api.dto.TenantInfoRechargeParam;
import com.zlsrj.wms.api.dto.TenantInfoUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantInfo")
public interface TenantInfoClientService {
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.GET)
	public TenantInfoVo getById(@PathVariable("id") String id);
	
	@RequestMapping(value = "/tenant-infos/dictionary/{id}", method = RequestMethod.GET)
	public TenantInfoVo getDictionaryById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-infos/list", method = RequestMethod.GET)
	public List<TenantInfoVo> list(@RequestBody TenantInfoQueryParam tenantInfoQueryParam);
	
	@RequestMapping(value = "/tenant-infos", method = RequestMethod.GET)
	public Page<TenantInfoVo> page(@RequestBody TenantInfoQueryParam tenantInfoQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-infos", method = RequestMethod.POST)
	public String save(@RequestBody TenantInfoAddParam tenantInfoAddParam);

	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantInfoUpdateParam tenantInfoUpdateParam);

	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.PATCH)
	public TenantInfoVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantInfo tenantInfo);

	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
	
	@RequestMapping(value = "/tenant-infos/recharge/{id}", method = RequestMethod.PUT)
	public boolean recharge(@PathVariable("id") String id, @RequestBody TenantInfoRechargeParam tenantInfoRechargeParam);
	
	@RequestMapping(value = "/tenant-infos/update/module", method = RequestMethod.PUT)
	public boolean updateModule(@RequestBody TenantInfoModuleInfoUpdateParam tenantInfoModuleInfoUpdateParam);
}

