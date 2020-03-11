package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.CustInfoQueryParam;
import com.zlsrj.wms.api.entity.CustInfo;
import com.zlsrj.wms.api.vo.CustInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-CUST", contextId = "CustInfo")
public interface CustInfoClientService {
	@RequestMapping(value = "/cust-infos/{id}", method = RequestMethod.GET)
	public CustInfoVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/cust-infos", method = RequestMethod.GET)
	public Page<CustInfoVo> page(@RequestBody CustInfoQueryParam custInfoQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/cust-infos", method = RequestMethod.POST)
	public CustInfoVo save(@RequestBody CustInfo custInfo);

	@RequestMapping(value = "/cust-infos/{id}", method = RequestMethod.PUT)
	public CustInfoVo updateById(@PathVariable("id") String id, @RequestBody CustInfo custInfo);

	@RequestMapping(value = "/cust-infos/{id}", method = RequestMethod.PATCH)
	public CustInfoVo updatePatchById(@PathVariable("id") String id, @RequestBody CustInfo custInfo);

	@RequestMapping(value = "/cust-infos/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

