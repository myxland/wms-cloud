package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationAddParam;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterReadSituation;
import com.zlsrj.wms.api.vo.TenantMeterReadSituationVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterReadSituation")
public interface TenantMeterReadSituationClientService {
	@RequestMapping(value = "/tenant-meter-read-situations/{id}", method = RequestMethod.GET)
	public TenantMeterReadSituationVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-read-situations/list", method = RequestMethod.GET)
	public List<TenantMeterReadSituationVo> list(@RequestBody TenantMeterReadSituationQueryParam tenantMeterReadSituationQueryParam);
	
	@RequestMapping(value = "/tenant-meter-read-situations", method = RequestMethod.GET)
	public Page<TenantMeterReadSituationVo> page(@RequestBody TenantMeterReadSituationQueryParam tenantMeterReadSituationQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-meter-read-situations", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterReadSituationAddParam tenantMeterReadSituationAddParam);

	@RequestMapping(value = "/tenant-meter-read-situations/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterReadSituationUpdateParam tenantMeterReadSituationUpdateParam);

	@RequestMapping(value = "/tenant-meter-read-situations/{id}", method = RequestMethod.PATCH)
	public TenantMeterReadSituationVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterReadSituation tenantMeterReadSituation);

	@RequestMapping(value = "/tenant-meter-read-situations/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

