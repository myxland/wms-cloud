package com.zlsrj.wms.admin.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantCustomerArchivesClientService;
import com.zlsrj.wms.api.client.service.TenantCustomerClientService;
import com.zlsrj.wms.api.client.service.TenantCustomerContactsClientService;
import com.zlsrj.wms.api.client.service.TenantCustomerInvoiceClientService;
import com.zlsrj.wms.api.client.service.TenantCustomerStatementClientService;
import com.zlsrj.wms.api.client.service.TenantMeterClientService;
import com.zlsrj.wms.api.dto.TenantCustomerAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerUpdateParam;
import com.zlsrj.wms.api.dto.TenantMeterQueryParam;
import com.zlsrj.wms.api.vo.TenantCustomerArchivesVo;
import com.zlsrj.wms.api.vo.TenantCustomerContactsVo;
import com.zlsrj.wms.api.vo.TenantCustomerDataVo;
import com.zlsrj.wms.api.vo.TenantCustomerInvoiceVo;
import com.zlsrj.wms.api.vo.TenantCustomerStatementVo;
import com.zlsrj.wms.api.vo.TenantCustomerVo;
import com.zlsrj.wms.api.vo.TenantMeterVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户信息", tags = { "用户信息操作接口" })
@Controller
@RequestMapping("/tenantCustomer")
@Slf4j
public class TenantCustomerController {

	@Autowired
	private TenantCustomerClientService tenantCustomerClientService;
	@Autowired
	private TenantCustomerStatementClientService tenantCustomerStatementClientService;
	@Autowired
	private TenantCustomerInvoiceClientService tenantCustomerInvoiceClientService;
	@Autowired
	private TenantCustomerContactsClientService tenantCustomerContactsClientService;
	@Autowired
	private TenantCustomerArchivesClientService tenantCustomerArchivesClientService;
	@Autowired
	private TenantMeterClientService tenantMeterClientService;
	
	@ApiOperation(value = "新增用户信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantCustomerAddParam tenantCustomerAddParam) {
		String id = tenantCustomerClientService.save(tenantCustomerAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID删除用户信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCustomerClientService.removeById(id);

		return commonResult;
	}

	@ApiOperation(value = "根据ID查询用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerVo> getById(@PathVariable("id") String id) {
		TenantCustomerVo tenantCustomerVo = tenantCustomerClientService.getById(id);

		return CommonResult.success(tenantCustomerVo);
	}
	
	@ApiOperation(value = "根据参数查询用户信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantCustomerDataVo>> list(TenantCustomerQueryParam tenantCustomerQueryParam) {
		List<TenantCustomerVo> tenantCustomerVoList = tenantCustomerClientService.list(tenantCustomerQueryParam);
		List<TenantCustomerStatementVo> tenantCustomerStatementVoList = tenantCustomerStatementClientService.list(TranslateUtil.translate(tenantCustomerQueryParam, TenantCustomerStatementQueryParam.class));
		List<TenantCustomerInvoiceVo> tenantCustomerInvoiceVoList = tenantCustomerInvoiceClientService.list(TranslateUtil.translate(tenantCustomerQueryParam, TenantCustomerInvoiceQueryParam.class));
		List<TenantCustomerContactsVo> tenantCustomerContactsVoList = tenantCustomerContactsClientService.list(TranslateUtil.translate(tenantCustomerQueryParam, TenantCustomerContactsQueryParam.class));
		List<TenantCustomerArchivesVo> tenantCustomerArchivesVoList = tenantCustomerArchivesClientService.list(TranslateUtil.translate(tenantCustomerQueryParam, TenantCustomerArchivesQueryParam.class));
		List<TenantMeterVo> tenantMeterVoList = tenantMeterClientService.list(TranslateUtil.translate(tenantCustomerQueryParam, TenantMeterQueryParam.class));
		
		List<TenantCustomerDataVo> tenantCustomerDataVoList = TranslateUtil.translateList(tenantCustomerVoList, TenantCustomerDataVo.class);
		if (tenantCustomerDataVoList != null && tenantCustomerDataVoList.size() > 0) {
			for (TenantCustomerDataVo tenantCustomerDataVo : tenantCustomerDataVoList) {
				Optional<TenantCustomerStatementVo> tenantCustomerStatementVoOptional = tenantCustomerStatementVoList.stream().filter(e -> tenantCustomerDataVo.getId().equals(e.getCustomerId())).findFirst();
				if(tenantCustomerStatementVoOptional.isPresent()) {
					tenantCustomerDataVo.setTenantCustomerStatement(tenantCustomerStatementVoOptional.get());
				}
				
				Optional<TenantCustomerInvoiceVo> tenantCustomerInvoiceVoOptional = tenantCustomerInvoiceVoList.stream().filter(e -> tenantCustomerDataVo.getId().equals(e.getCustomerId())).findFirst();
				if(tenantCustomerInvoiceVoOptional.isPresent()) {
					tenantCustomerDataVo.setTenantCustomerInvoice(tenantCustomerInvoiceVoOptional.get());
				}
				
				tenantCustomerDataVo.setTenantCustomerContactsList(tenantCustomerContactsVoList.stream().filter(e -> tenantCustomerDataVo.getId().equals(e.getCustomerId())).collect(Collectors.toList()));
				tenantCustomerDataVo.setTenantCustomerArchivesList(tenantCustomerArchivesVoList.stream().filter(e -> tenantCustomerDataVo.getId().equals(e.getCustomerId())).collect(Collectors.toList()));
				tenantCustomerDataVo.setTenantMeterList(tenantMeterVoList.stream().filter(e -> tenantCustomerDataVo.getId().equals(e.getCustomerId())).collect(Collectors.toList()));
			}
		}
		
		return CommonResult.success(tenantCustomerDataVoList);
	}
	
	@ApiOperation(value = "根据参数查询用户信息数量")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> count(TenantCustomerQueryParam tenantCustomerQueryParam) {
		int count = tenantCustomerClientService.count(tenantCustomerQueryParam);

		return CommonResult.success(count);
	}
	
	@ApiOperation(value = "根据参数分页查询用户信息列表")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustomerVo>> page(TenantCustomerQueryParam tenantCustomerQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		Page<TenantCustomerVo> tenantCustomerVoPage = tenantCustomerClientService.page(tenantCustomerQueryParam, page,
				rows, sort, order);

		CommonPage<TenantCustomerVo> tenantCustomerCommonPage = CommonPage.restPage(tenantCustomerVoPage);

		return CommonResult.success(tenantCustomerCommonPage);
	}
	
	@ApiOperation(value = "根据参数更新用户信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantCustomerUpdateParam tenantCustomerUpdateParam) {
		boolean success = tenantCustomerClientService.updateById(id, tenantCustomerUpdateParam);

		return CommonResult.success(success);
	}

}
