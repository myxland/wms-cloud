package com.zlsrj.wms.admin.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.IotDeviceDesignClientService;
import com.zlsrj.wms.api.dto.IotDeviceDesignQueryParam;
import com.zlsrj.wms.api.entity.IotDeviceDesign;
import com.zlsrj.wms.api.vo.IotDeviceDesignVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "设备信息", tags = { "设备信息操作接口" })
@Controller
@RequestMapping("/iotDeviceDesign")
@Slf4j
public class IotDeviceDesignController {

	@Autowired
	private IotDeviceDesignClientService iotDeviceDesignClientService;

	@ApiOperation(value = "根据参数查询设备信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<IotDeviceDesignVo>> list(IotDeviceDesignQueryParam iotDeviceDesignQueryParam, int pageNum,
			int pageSize) {
		Page<IotDeviceDesignVo> iotDeviceDesignVoPage = iotDeviceDesignClientService.page(iotDeviceDesignQueryParam, pageNum, pageSize, "id", "desc");
		iotDeviceDesignVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<IotDeviceDesignVo> iotDeviceDesignCommonPage = CommonPage.restPage(iotDeviceDesignVoPage);

		return CommonResult.success(iotDeviceDesignCommonPage);
	}

	@ApiOperation(value = "新增设备信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<IotDeviceDesignVo> create(@RequestBody IotDeviceDesign iotDeviceDesign) {
		IotDeviceDesignVo iotDeviceDesignVo = iotDeviceDesignClientService.save(iotDeviceDesign);

		return CommonResult.success(iotDeviceDesignVo);
	}

	@ApiOperation(value = "根据ID查询设备信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<IotDeviceDesignVo> getById(@PathVariable("id") String id) {
		IotDeviceDesignVo iotDeviceDesignVo = iotDeviceDesignClientService.getById(id);
		wrappperVo(iotDeviceDesignVo);
		
		return CommonResult.success(iotDeviceDesignVo);
	}

	@ApiOperation(value = "根据参数更新设备信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<IotDeviceDesignVo> getById(@RequestBody IotDeviceDesign iotDeviceDesign) {
		String id = iotDeviceDesign.getId();
		IotDeviceDesignVo iotDeviceDesignVo = iotDeviceDesignClientService.updatePatchById(id, iotDeviceDesign);

		return CommonResult.success(iotDeviceDesignVo);
	}
	
	@ApiOperation(value = "根据ID删除设备信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = iotDeviceDesignClientService.removeById(id);

		return commonResult;
	}
	
	private void wrappperVo(IotDeviceDesignVo iotDeviceDesignVo) {
	}

}
