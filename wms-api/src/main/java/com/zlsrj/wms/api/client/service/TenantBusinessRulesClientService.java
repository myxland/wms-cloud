package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantBusinessRulesAddParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesQueryParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesUpdateParam;
import com.zlsrj.wms.api.entity.TenantBusinessRules;
import com.zlsrj.wms.api.vo.TenantBusinessRulesVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantBusinessRules")
public interface TenantBusinessRulesClientService {
	@RequestMapping(value = "/tenant-business-ruless/{id}", method = RequestMethod.GET)
	public TenantBusinessRulesVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-business-ruless/list", method = RequestMethod.GET)
	public List<TenantBusinessRulesVo> list(@RequestBody TenantBusinessRulesQueryParam tenantBusinessRulesQueryParam);
	
	@RequestMapping(value = "/tenant-business-ruless", method = RequestMethod.GET)
	public Page<TenantBusinessRulesVo> page(@RequestBody TenantBusinessRulesQueryParam tenantBusinessRulesQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-business-ruless", method = RequestMethod.POST)
	public String save(@RequestBody TenantBusinessRulesAddParam tenantBusinessRulesAddParam);

	@RequestMapping(value = "/tenant-business-ruless/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantBusinessRulesUpdateParam tenantBusinessRulesUpdateParam);

	@RequestMapping(value = "/tenant-business-ruless/{id}", method = RequestMethod.PATCH)
	public TenantBusinessRulesVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantBusinessRules tenantBusinessRules);

	@RequestMapping(value = "/tenant-business-ruless/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

