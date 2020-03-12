package com.zlsrj.wms.admin.history;

import org.apache.commons.lang3.StringUtils;
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
import com.zlsrj.wms.api.client.service.TenantCustomerLinkmanClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantCustomerLinkmanQueryParam;
import com.zlsrj.wms.api.entity.TenantCustomerLinkman;
import com.zlsrj.wms.api.vo.TenantCustomerLinkmanVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户联系人", tags = { "用户联系人操作接口" })
@Controller
@RequestMapping("/tenantCustomerLinkman")
@Slf4j
public class TenantCustomerLinkmanController {

	@Autowired
	private TenantCustomerLinkmanClientService tenantCustomerLinkmanClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询用户联系人列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustomerLinkmanVo>> list(TenantCustomerLinkmanQueryParam tenantCustomerLinkmanQueryParam, int pageNum,
			int pageSize) {
		Page<TenantCustomerLinkmanVo> tenantCustomerLinkmanVoPage = tenantCustomerLinkmanClientService.page(tenantCustomerLinkmanQueryParam, pageNum, pageSize, "id", "desc");
		tenantCustomerLinkmanVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantCustomerLinkmanVo> tenantCustomerLinkmanCommonPage = CommonPage.restPage(tenantCustomerLinkmanVoPage);

		return CommonResult.success(tenantCustomerLinkmanCommonPage);
	}

	@ApiOperation(value = "新增用户联系人")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerLinkmanVo> create(@RequestBody TenantCustomerLinkman tenantCustomerLinkman) {
		TenantCustomerLinkmanVo tenantCustomerLinkmanVo = tenantCustomerLinkmanClientService.save(tenantCustomerLinkman);

		return CommonResult.success(tenantCustomerLinkmanVo);
	}

	@ApiOperation(value = "根据ID查询用户联系人")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerLinkmanVo> getById(@PathVariable("id") String id) {
		TenantCustomerLinkmanVo tenantCustomerLinkmanVo = tenantCustomerLinkmanClientService.getById(id);
		wrappperVo(tenantCustomerLinkmanVo);

		return CommonResult.success(tenantCustomerLinkmanVo);
	}

	@ApiOperation(value = "根据参数更新用户联系人信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerLinkmanVo> getById(@RequestBody TenantCustomerLinkman tenantCustomerLinkman) {
		String id = tenantCustomerLinkman.getId();
		TenantCustomerLinkmanVo tenantCustomerLinkmanVo = tenantCustomerLinkmanClientService.updatePatchById(id, tenantCustomerLinkman);
		wrappperVo(tenantCustomerLinkmanVo);

		return CommonResult.success(tenantCustomerLinkmanVo);
	}
	
	@ApiOperation(value = "根据ID删除用户联系人")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCustomerLinkmanClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantCustomerLinkmanVo tenantCustomerLinkmanVo) {
		if (StringUtils.isEmpty(tenantCustomerLinkmanVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantCustomerLinkmanVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantCustomerLinkmanVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
