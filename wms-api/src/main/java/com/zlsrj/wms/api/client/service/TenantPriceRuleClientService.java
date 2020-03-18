package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantPriceRuleAddParam;
import com.zlsrj.wms.api.dto.TenantPriceRuleQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceRuleUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceRule;
import com.zlsrj.wms.api.vo.TenantPriceRuleVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantPriceRule")
public interface TenantPriceRuleClientService {
	@RequestMapping(value = "/tenant-price-rules/{id}", method = RequestMethod.GET)
	public TenantPriceRuleVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-price-rules/list", method = RequestMethod.GET)
	public List<TenantPriceRuleVo> list(@RequestBody TenantPriceRuleQueryParam tenantPriceRuleQueryParam);
	
	@RequestMapping(value = "/tenant-price-rules", method = RequestMethod.GET)
	public Page<TenantPriceRuleVo> page(@RequestBody TenantPriceRuleQueryParam tenantPriceRuleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-price-rules", method = RequestMethod.POST)
	public String save(@RequestBody TenantPriceRuleAddParam tenantPriceRuleAddParam);

	@RequestMapping(value = "/tenant-price-rules/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantPriceRuleUpdateParam tenantPriceRuleUpdateParam);

	@RequestMapping(value = "/tenant-price-rules/{id}", method = RequestMethod.PATCH)
	public TenantPriceRuleVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPriceRule tenantPriceRule);

	@RequestMapping(value = "/tenant-price-rules/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

