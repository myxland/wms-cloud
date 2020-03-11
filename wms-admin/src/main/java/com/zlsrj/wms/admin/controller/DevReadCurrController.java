package com.zlsrj.wms.admin.controller;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.DevReadCurrClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.DevReadCurrQueryParam;
import com.zlsrj.wms.api.entity.DevReadCurr;
import com.zlsrj.wms.api.vo.DevReadCurrVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "抄表信息", tags = { "抄表信息操作接口" })
@Controller
@RequestMapping("/devReadCurr")
@Slf4j
public class DevReadCurrController {

	@Autowired
	private DevReadCurrClientService devReadCurrClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询抄表信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<DevReadCurrVo>> list(DevReadCurrQueryParam devReadCurrQueryParam, int pageNum,
			int pageSize) {
		Page<DevReadCurrVo> devReadCurrVoPage = devReadCurrClientService.page(devReadCurrQueryParam, pageNum, pageSize, "id", "desc");
		devReadCurrVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<DevReadCurrVo> devReadCurrCommonPage = CommonPage.restPage(devReadCurrVoPage);

		return CommonResult.success(devReadCurrCommonPage);
	}

	@ApiOperation(value = "新增抄表信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<DevReadCurrVo> create(@RequestBody DevReadCurr devReadCurr) {
		DevReadCurrVo devReadCurrVo = devReadCurrClientService.save(devReadCurr);

		return CommonResult.success(devReadCurrVo);
	}

	@ApiOperation(value = "根据ID查询抄表信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<DevReadCurrVo> getById(@PathVariable("id") String id) {
		DevReadCurrVo devReadCurrVo = devReadCurrClientService.getById(id);
		wrappperVo(devReadCurrVo);
		
		return CommonResult.success(devReadCurrVo);
	}

	@ApiOperation(value = "根据参数更新抄表信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<DevReadCurrVo> getById(@RequestBody DevReadCurr devReadCurr) {
		String id = devReadCurr.getId();
		DevReadCurrVo devReadCurrVo = devReadCurrClientService.updatePatchById(id, devReadCurr);

		return CommonResult.success(devReadCurrVo);
	}
	
	@ApiOperation(value = "根据ID删除抄表信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = devReadCurrClientService.removeById(id);

		return commonResult;
	}
	
	private void wrappperVo(DevReadCurrVo devReadCurrVo) {
		if (StringUtils.isEmpty(devReadCurrVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(devReadCurrVo.getTenantId());
			if (tenantInfoVo != null) {
				devReadCurrVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
