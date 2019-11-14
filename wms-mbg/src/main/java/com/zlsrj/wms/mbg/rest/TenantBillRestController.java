package com.zlsrj.wms.mbg.rest;

import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantBillQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantBill;
import com.zlsrj.wms.api.vo.TenantBillVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantBillService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户账单配置", tags = { "租户账单配置操作接口" })
@RestController
@Slf4j
public class TenantBillRestController {

	@Autowired
	private ITenantBillService tenantBillService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户账单配置")
	@RequestMapping(value = "/tenant-bills/{id}", method = RequestMethod.GET)
	public TenantBillVo getById(@PathVariable("id") Long id) {
		TenantBill tenantBill = tenantBillService.getById(id);

		return entity2vo(tenantBill);
	}

	@ApiOperation(value = "根据参数查询租户账单配置列表")
	@RequestMapping(value = "/tenant-bills", method = RequestMethod.GET)
	public Page<TenantBillVo> page(@RequestBody TenantBillQueryParam tenantBillQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantBill> pageTenantBill = new Page<TenantBill>(page, rows);
		QueryWrapper<TenantBill> queryWrapperTenantBill = new QueryWrapper<TenantBill>();
		queryWrapperTenantBill.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantBill.lambda()
				.eq(tenantBillQueryParam.getId() != null, TenantBill::getId, tenantBillQueryParam.getId())
				.eq(tenantBillQueryParam.getTenantId() != null, TenantBill::getTenantId, tenantBillQueryParam.getTenantId())
				.eq(tenantBillQueryParam.getBillPrintType() != null, TenantBill::getBillPrintType, tenantBillQueryParam.getBillPrintType())
				.eq(tenantBillQueryParam.getBillRemark() != null, TenantBill::getBillRemark, tenantBillQueryParam.getBillRemark())
				.eq(tenantBillQueryParam.getBillService() != null, TenantBill::getBillService, tenantBillQueryParam.getBillService())
				.eq(tenantBillQueryParam.getBillJrdm() != null, TenantBill::getBillJrdm, tenantBillQueryParam.getBillJrdm())
				.eq(tenantBillQueryParam.getBillQmcs() != null, TenantBill::getBillQmcs, tenantBillQueryParam.getBillQmcs())
				.eq(tenantBillQueryParam.getBillSkpbh() != null, TenantBill::getBillSkpbh, tenantBillQueryParam.getBillSkpbh())
				.eq(tenantBillQueryParam.getBillSkpkl() != null, TenantBill::getBillSkpkl, tenantBillQueryParam.getBillSkpkl())
				.eq(tenantBillQueryParam.getBillKeypwd() != null, TenantBill::getBillKeypwd, tenantBillQueryParam.getBillKeypwd())
				;

		IPage<TenantBill> tenantBillPage = tenantBillService.page(pageTenantBill, queryWrapperTenantBill);

		Page<TenantBillVo> tenantBillVoPage = new Page<TenantBillVo>(page, rows);
		tenantBillVoPage.setCurrent(tenantBillPage.getCurrent());
		tenantBillVoPage.setPages(tenantBillPage.getPages());
		tenantBillVoPage.setSize(tenantBillPage.getSize());
		tenantBillVoPage.setTotal(tenantBillPage.getTotal());
		tenantBillVoPage.setRecords(tenantBillPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantBillVoPage;
	}

	@ApiOperation(value = "新增租户账单配置")
	@RequestMapping(value = "/tenant-bills", method = RequestMethod.POST)
	public TenantBillVo save(@RequestBody TenantBill tenantBill) {
		if (tenantBill.getId() == null || tenantBill.getId().compareTo(0L) <= 0) {
			tenantBill.setId(idService.selectId());
		}
		boolean success = tenantBillService.save(tenantBill);
		if (success) {
			TenantBill tenantBillDatabase = tenantBillService.getById(tenantBill.getId());
			return entity2vo(tenantBillDatabase);
		}
		log.info("save TenantBill fail，{}", ToStringBuilder.reflectionToString(tenantBill, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户账单配置全部信息")
	@RequestMapping(value = "/tenant-bills/{id}", method = RequestMethod.PUT)
	public TenantBillVo updateById(@PathVariable("id") Long id, @RequestBody TenantBill tenantBill) {
		tenantBill.setId(id);
		boolean success = tenantBillService.updateById(tenantBill);
		if (success) {
			TenantBill tenantBillDatabase = tenantBillService.getById(id);
			return entity2vo(tenantBillDatabase);
		}
		log.info("update TenantBill fail，{}", ToStringBuilder.reflectionToString(tenantBill, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户账单配置信息")
	@RequestMapping(value = "/tenant-bills/{id}", method = RequestMethod.PATCH)
	public TenantBillVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantBill tenantBill) {
		UpdateWrapper<TenantBill> updateWrapperTenantBill = new UpdateWrapper<TenantBill>();
		updateWrapperTenantBill.lambda()//
				.eq(TenantBill::getId, id)
				// .set(tenantBill.getId() != null, TenantBill::getId, tenantBill.getId())
				.set(tenantBill.getTenantId() != null, TenantBill::getTenantId, tenantBill.getTenantId())
				.set(tenantBill.getBillPrintType() != null, TenantBill::getBillPrintType, tenantBill.getBillPrintType())
				.set(tenantBill.getBillRemark() != null, TenantBill::getBillRemark, tenantBill.getBillRemark())
				.set(tenantBill.getBillService() != null, TenantBill::getBillService, tenantBill.getBillService())
				.set(tenantBill.getBillJrdm() != null, TenantBill::getBillJrdm, tenantBill.getBillJrdm())
				.set(tenantBill.getBillQmcs() != null, TenantBill::getBillQmcs, tenantBill.getBillQmcs())
				.set(tenantBill.getBillSkpbh() != null, TenantBill::getBillSkpbh, tenantBill.getBillSkpbh())
				.set(tenantBill.getBillSkpkl() != null, TenantBill::getBillSkpkl, tenantBill.getBillSkpkl())
				.set(tenantBill.getBillKeypwd() != null, TenantBill::getBillKeypwd, tenantBill.getBillKeypwd())
				;

		boolean success = tenantBillService.update(updateWrapperTenantBill);
		if (success) {
			TenantBill tenantBillDatabase = tenantBillService.getById(id);
			return entity2vo(tenantBillDatabase);
		}
		log.info("partial update TenantBill fail，{}",
				ToStringBuilder.reflectionToString(tenantBill, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户账单配置")
	@RequestMapping(value = "/tenant-bills/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantBillService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantBillVo entity2vo(TenantBill tenantBill) {
		String jsonString = JSON.toJSONString(tenantBill);
		TenantBillVo tenantBillVo = JSON.parseObject(jsonString, TenantBillVo.class);
		if (StringUtils.isEmpty(tenantBillVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantBill.getTenantId());
			if (tenantInfo != null) {
				tenantBillVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantBillVo;
	}

}
