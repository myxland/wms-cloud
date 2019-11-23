package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.DevReadCurrHisQueryParam;
import com.zlsrj.wms.api.entity.DevReadCurrHis;
import com.zlsrj.wms.api.vo.DevReadCurrHisVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-CUST", contextId = "DevReadCurrHis")
public interface DevReadCurrHisClientService {
	@RequestMapping(value = "/dev-read-curr-hiss/{id}", method = RequestMethod.GET)
	public DevReadCurrHisVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/dev-read-curr-hiss", method = RequestMethod.GET)
	public Page<DevReadCurrHisVo> page(@RequestBody DevReadCurrHisQueryParam devReadCurrHisQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/dev-read-curr-hiss", method = RequestMethod.POST)
	public DevReadCurrHisVo save(@RequestBody DevReadCurrHis devReadCurrHis);

	@RequestMapping(value = "/dev-read-curr-hiss/{id}", method = RequestMethod.PUT)
	public DevReadCurrHisVo updateById(@PathVariable("id") Long id, @RequestBody DevReadCurrHis devReadCurrHis);

	@RequestMapping(value = "/dev-read-curr-hiss/{id}", method = RequestMethod.PATCH)
	public DevReadCurrHisVo updatePatchById(@PathVariable("id") Long id, @RequestBody DevReadCurrHis devReadCurrHis);

	@RequestMapping(value = "/dev-read-curr-hiss/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

