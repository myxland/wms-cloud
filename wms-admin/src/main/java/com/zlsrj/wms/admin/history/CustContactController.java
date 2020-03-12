package com.zlsrj.wms.admin.history;

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
import com.zlsrj.wms.api.client.service.CustContactClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.CustContactQueryParam;
import com.zlsrj.wms.api.entity.CustContact;
import com.zlsrj.wms.api.vo.CustContactVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户联系人", tags = { "用户联系人操作接口" })
@Controller
@RequestMapping("/custContact")
@Slf4j
public class CustContactController {

	@Autowired
	private CustContactClientService custContactClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询用户联系人列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<CustContactVo>> list(CustContactQueryParam custContactQueryParam, int pageNum,
			int pageSize) {
		Page<CustContactVo> custContactVoPage = custContactClientService.page(custContactQueryParam, pageNum, pageSize, "id", "desc");
		custContactVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<CustContactVo> custContactCommonPage = CommonPage.restPage(custContactVoPage);

		return CommonResult.success(custContactCommonPage);
	}

	@ApiOperation(value = "新增用户联系人")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<CustContactVo> create(@RequestBody CustContact custContact) {
		CustContactVo custContactVo = custContactClientService.save(custContact);

		return CommonResult.success(custContactVo);
	}

	@ApiOperation(value = "根据ID查询用户联系人")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CustContactVo> getById(@PathVariable("id") String id) {
		CustContactVo custContactVo = custContactClientService.getById(id);
		wrappperVo(custContactVo);
		
		return CommonResult.success(custContactVo);
	}

	@ApiOperation(value = "根据参数更新用户联系人信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<CustContactVo> getById(@RequestBody CustContact custContact) {
		String id = custContact.getId();
		CustContactVo custContactVo = custContactClientService.updatePatchById(id, custContact);

		return CommonResult.success(custContactVo);
	}
	
	@ApiOperation(value = "根据ID删除用户联系人")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = custContactClientService.removeById(id);

		return commonResult;
	}
	
	private void wrappperVo(CustContactVo custContactVo) {
		if (StringUtils.isEmpty(custContactVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(custContactVo.getTenantId());
			if (tenantInfoVo != null) {
				custContactVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
