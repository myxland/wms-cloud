package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.CustInfoChangeQueryParam;
import com.zlsrj.wms.api.entity.CustInfoChange;
import com.zlsrj.wms.api.vo.CustInfoChangeVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-CUST", contextId = "CustInfoChange")
public interface CustInfoChangeClientService {
	@RequestMapping(value = "/cust-info-changes/{id}", method = RequestMethod.GET)
	public CustInfoChangeVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/cust-info-changes", method = RequestMethod.GET)
	public Page<CustInfoChangeVo> page(@RequestBody CustInfoChangeQueryParam custInfoChangeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/cust-info-changes", method = RequestMethod.POST)
	public CustInfoChangeVo save(@RequestBody CustInfoChange custInfoChange);

	@RequestMapping(value = "/cust-info-changes/{id}", method = RequestMethod.PUT)
	public CustInfoChangeVo updateById(@PathVariable("id") Long id, @RequestBody CustInfoChange custInfoChange);

	@RequestMapping(value = "/cust-info-changes/{id}", method = RequestMethod.PATCH)
	public CustInfoChangeVo updatePatchById(@PathVariable("id") Long id, @RequestBody CustInfoChange custInfoChange);

	@RequestMapping(value = "/cust-info-changes/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

