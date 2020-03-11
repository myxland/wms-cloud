package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.DevReadCurrQueryParam;
import com.zlsrj.wms.api.entity.DevReadCurr;
import com.zlsrj.wms.api.vo.DevReadCurrVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-CUST", contextId = "DevReadCurr")
public interface DevReadCurrClientService {
	@RequestMapping(value = "/dev-read-currs/{id}", method = RequestMethod.GET)
	public DevReadCurrVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/dev-read-currs", method = RequestMethod.GET)
	public Page<DevReadCurrVo> page(@RequestBody DevReadCurrQueryParam devReadCurrQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/dev-read-currs", method = RequestMethod.POST)
	public DevReadCurrVo save(@RequestBody DevReadCurr devReadCurr);

	@RequestMapping(value = "/dev-read-currs/{id}", method = RequestMethod.PUT)
	public DevReadCurrVo updateById(@PathVariable("id") String id, @RequestBody DevReadCurr devReadCurr);

	@RequestMapping(value = "/dev-read-currs/{id}", method = RequestMethod.PATCH)
	public DevReadCurrVo updatePatchById(@PathVariable("id") String id, @RequestBody DevReadCurr devReadCurr);

	@RequestMapping(value = "/dev-read-currs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

