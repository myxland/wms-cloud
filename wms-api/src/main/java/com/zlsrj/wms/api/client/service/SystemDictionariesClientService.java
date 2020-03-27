package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.SystemDictionariesAddParam;
import com.zlsrj.wms.api.dto.SystemDictionariesQueryParam;
import com.zlsrj.wms.api.dto.SystemDictionariesUpdateParam;
import com.zlsrj.wms.api.entity.SystemDictionaries;
import com.zlsrj.wms.api.vo.SystemDictionariesVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "SystemDictionaries")
public interface SystemDictionariesClientService {
	@RequestMapping(value = "/system-dictionariess/{id}", method = RequestMethod.GET)
	public SystemDictionariesVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/system-dictionariess/list", method = RequestMethod.GET)
	public List<SystemDictionariesVo> list(@RequestBody SystemDictionariesQueryParam systemDictionariesQueryParam);
	
	@RequestMapping(value = "/system-dictionariess", method = RequestMethod.GET)
	public Page<SystemDictionariesVo> page(@RequestBody SystemDictionariesQueryParam systemDictionariesQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/system-dictionariess", method = RequestMethod.POST)
	public String save(@RequestBody SystemDictionariesAddParam systemDictionariesAddParam);

	@RequestMapping(value = "/system-dictionariess/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody SystemDictionariesUpdateParam systemDictionariesUpdateParam);

	@RequestMapping(value = "/system-dictionariess/{id}", method = RequestMethod.PATCH)
	public SystemDictionariesVo updatePatchById(@PathVariable("id") String id, @RequestBody SystemDictionaries systemDictionaries);

	@RequestMapping(value = "/system-dictionariess/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

