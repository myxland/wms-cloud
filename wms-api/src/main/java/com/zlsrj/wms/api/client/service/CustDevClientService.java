package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.CustDevQueryParam;
import com.zlsrj.wms.api.entity.CustDev;
import com.zlsrj.wms.api.vo.CustDevVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-CUST", contextId = "CustDev")
public interface CustDevClientService {
	@RequestMapping(value = "/cust-devs/{id}", method = RequestMethod.GET)
	public CustDevVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/cust-devs", method = RequestMethod.GET)
	public Page<CustDevVo> page(@RequestBody CustDevQueryParam custDevQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/cust-devs", method = RequestMethod.POST)
	public CustDevVo save(@RequestBody CustDev custDev);

	@RequestMapping(value = "/cust-devs/{id}", method = RequestMethod.PUT)
	public CustDevVo updateById(@PathVariable("id") String id, @RequestBody CustDev custDev);

	@RequestMapping(value = "/cust-devs/{id}", method = RequestMethod.PATCH)
	public CustDevVo updatePatchById(@PathVariable("id") String id, @RequestBody CustDev custDev);

	@RequestMapping(value = "/cust-devs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

