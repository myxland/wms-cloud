package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.IotDeviceDesignQueryParam;
import com.zlsrj.wms.api.entity.IotDeviceDesign;
import com.zlsrj.wms.api.vo.IotDeviceDesignVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-DEV", contextId = "IotDeviceDesign")
public interface IotDeviceDesignClientService {
	@RequestMapping(value = "/iot-device-designs/{id}", method = RequestMethod.GET)
	public IotDeviceDesignVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/iot-device-designs", method = RequestMethod.GET)
	public Page<IotDeviceDesignVo> page(@RequestBody IotDeviceDesignQueryParam iotDeviceDesignQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/iot-device-designs", method = RequestMethod.POST)
	public IotDeviceDesignVo save(@RequestBody IotDeviceDesign iotDeviceDesign);

	@RequestMapping(value = "/iot-device-designs/{id}", method = RequestMethod.PUT)
	public IotDeviceDesignVo updateById(@PathVariable("id") Long id, @RequestBody IotDeviceDesign iotDeviceDesign);

	@RequestMapping(value = "/iot-device-designs/{id}", method = RequestMethod.PATCH)
	public IotDeviceDesignVo updatePatchById(@PathVariable("id") Long id, @RequestBody IotDeviceDesign iotDeviceDesign);

	@RequestMapping(value = "/iot-device-designs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

