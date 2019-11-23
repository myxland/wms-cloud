package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.CustContactQueryParam;
import com.zlsrj.wms.api.entity.CustContact;
import com.zlsrj.wms.api.vo.CustContactVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-CUST", contextId = "CustContact")
public interface CustContactClientService {
	@RequestMapping(value = "/cust-contacts/{id}", method = RequestMethod.GET)
	public CustContactVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/cust-contacts", method = RequestMethod.GET)
	public Page<CustContactVo> page(@RequestBody CustContactQueryParam custContactQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/cust-contacts", method = RequestMethod.POST)
	public CustContactVo save(@RequestBody CustContact custContact);

	@RequestMapping(value = "/cust-contacts/{id}", method = RequestMethod.PUT)
	public CustContactVo updateById(@PathVariable("id") Long id, @RequestBody CustContact custContact);

	@RequestMapping(value = "/cust-contacts/{id}", method = RequestMethod.PATCH)
	public CustContactVo updatePatchById(@PathVariable("id") Long id, @RequestBody CustContact custContact);

	@RequestMapping(value = "/cust-contacts/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

