package com.zlsrj.wms.admin.history;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.CustDevClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.client.service.TenantPriceTypeClientService;
import com.zlsrj.wms.api.client.service.TenantWaterTypeClientService;
import com.zlsrj.wms.api.dto.CustDevQueryParam;
import com.zlsrj.wms.api.entity.CustDev;
import com.zlsrj.wms.api.vo.CustDevVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.api.vo.TenantPriceTypeVo;
import com.zlsrj.wms.api.vo.TenantWaterTypeVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户表具", tags = { "用户表具操作接口" })
@Controller
@RequestMapping("/custDev")
@Slf4j
public class CustDevController {

	@Autowired
	private CustDevClientService custDevClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private TenantPriceTypeClientService tenantPriceTypeClientService;
	@Autowired
	private TenantWaterTypeClientService tenantWaterTypeClientService;

	@ApiOperation(value = "根据参数查询用户表具列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<CustDevVo>> list(CustDevQueryParam custDevQueryParam, int pageNum,
			int pageSize) {
		Page<CustDevVo> custDevVoPage = custDevClientService.page(custDevQueryParam, pageNum, pageSize, "id", "desc");
		custDevVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<CustDevVo> custDevCommonPage = CommonPage.restPage(custDevVoPage);

		return CommonResult.success(custDevCommonPage);
	}

	@ApiOperation(value = "新增用户表具")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<CustDevVo> create(@RequestBody CustDev custDev) {
		CustDevVo custDevVo = custDevClientService.save(custDev);

		return CommonResult.success(custDevVo);
	}

	@ApiOperation(value = "根据ID查询用户表具")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CustDevVo> getById(@PathVariable("id") String id) {
		CustDevVo custDevVo = custDevClientService.getById(id);
		wrappperVo(custDevVo);
		
		return CommonResult.success(custDevVo);
	}

	@ApiOperation(value = "根据参数更新用户表具信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<CustDevVo> getById(@RequestBody CustDev custDev) {
		String id = custDev.getId();
		CustDevVo custDevVo = custDevClientService.updatePatchById(id, custDev);
		wrappperVo(custDevVo);
		
		return CommonResult.success(custDevVo);
	}
	
	@ApiOperation(value = "根据ID删除用户表具")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = custDevClientService.removeById(id);

		return commonResult;
	}
	
	private void wrappperVo(CustDevVo custDevVo) {
		if (StringUtils.isEmpty(custDevVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(custDevVo.getTenantId());
			if (tenantInfoVo != null) {
				custDevVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		if (StringUtils.isEmpty(custDevVo.getPriceTypeName())) {
			TenantPriceTypeVo tenantPriceTypeVo = tenantPriceTypeClientService.getById(custDevVo.getPriceTypeId());
			if (tenantPriceTypeVo != null) {
				custDevVo.setPriceTypeName(tenantPriceTypeVo.getPriceTypeName());
			}
		}
		if (StringUtils.isEmpty(custDevVo.getWaterTypeName())) {
			TenantWaterTypeVo tenantWaterTypeVo = tenantWaterTypeClientService.getById(custDevVo.getWaterTypeId());
			if (tenantWaterTypeVo != null) {
				custDevVo.setWaterTypeName(tenantWaterTypeVo.getWaterTypeName());
			}
		}
	}

}
