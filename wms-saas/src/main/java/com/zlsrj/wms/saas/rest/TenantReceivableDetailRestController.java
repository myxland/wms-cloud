package com.zlsrj.wms.saas.rest;

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
import com.zlsrj.wms.api.dto.TenantReceivableDetailQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantReceivableDetail;
import com.zlsrj.wms.api.vo.TenantReceivableDetailVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantReceivableDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "应收明细账", tags = { "应收明细账操作接口" })
@RestController
@Slf4j
public class TenantReceivableDetailRestController {

	@Autowired
	private ITenantReceivableDetailService tenantReceivableDetailService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询应收明细账")
	@RequestMapping(value = "/tenant-receivable-details/{id}", method = RequestMethod.GET)
	public TenantReceivableDetailVo getById(@PathVariable("id") Long id) {
		TenantReceivableDetail tenantReceivableDetail = tenantReceivableDetailService.getById(id);

		return entity2vo(tenantReceivableDetail);
	}

	@ApiOperation(value = "根据参数查询应收明细账列表")
	@RequestMapping(value = "/tenant-receivable-details", method = RequestMethod.GET)
	public Page<TenantReceivableDetailVo> page(@RequestBody TenantReceivableDetailQueryParam tenantReceivableDetailQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantReceivableDetail> pageTenantReceivableDetail = new Page<TenantReceivableDetail>(page, rows);
		QueryWrapper<TenantReceivableDetail> queryWrapperTenantReceivableDetail = new QueryWrapper<TenantReceivableDetail>();
		queryWrapperTenantReceivableDetail.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantReceivableDetail.lambda()
				.eq(tenantReceivableDetailQueryParam.getId() != null, TenantReceivableDetail::getId, tenantReceivableDetailQueryParam.getId())
				.eq(tenantReceivableDetailQueryParam.getTenantId() != null, TenantReceivableDetail::getTenantId, tenantReceivableDetailQueryParam.getTenantId())
				.eq(tenantReceivableDetailQueryParam.getReceivableId() != null, TenantReceivableDetail::getReceivableId, tenantReceivableDetailQueryParam.getReceivableId())
				.eq(tenantReceivableDetailQueryParam.getPriceStepId() != null, TenantReceivableDetail::getPriceStepId, tenantReceivableDetailQueryParam.getPriceStepId())
				.eq(tenantReceivableDetailQueryParam.getReceivableWaters() != null, TenantReceivableDetail::getReceivableWaters, tenantReceivableDetailQueryParam.getReceivableWaters())
				.eq(tenantReceivableDetailQueryParam.getArrearsWaters() != null, TenantReceivableDetail::getArrearsWaters, tenantReceivableDetailQueryParam.getArrearsWaters())
				.eq(tenantReceivableDetailQueryParam.getPriceItemId() != null, TenantReceivableDetail::getPriceItemId, tenantReceivableDetailQueryParam.getPriceItemId())
				.eq(tenantReceivableDetailQueryParam.getDetailPrice() != null, TenantReceivableDetail::getDetailPrice, tenantReceivableDetailQueryParam.getDetailPrice())
				.eq(tenantReceivableDetailQueryParam.getReceivableMoney() != null, TenantReceivableDetail::getReceivableMoney, tenantReceivableDetailQueryParam.getReceivableMoney())
				.eq(tenantReceivableDetailQueryParam.getArrearsMoney() != null, TenantReceivableDetail::getArrearsMoney, tenantReceivableDetailQueryParam.getArrearsMoney())
				;

		IPage<TenantReceivableDetail> tenantReceivableDetailPage = tenantReceivableDetailService.page(pageTenantReceivableDetail, queryWrapperTenantReceivableDetail);

		Page<TenantReceivableDetailVo> tenantReceivableDetailVoPage = new Page<TenantReceivableDetailVo>(page, rows);
		tenantReceivableDetailVoPage.setCurrent(tenantReceivableDetailPage.getCurrent());
		tenantReceivableDetailVoPage.setPages(tenantReceivableDetailPage.getPages());
		tenantReceivableDetailVoPage.setSize(tenantReceivableDetailPage.getSize());
		tenantReceivableDetailVoPage.setTotal(tenantReceivableDetailPage.getTotal());
		tenantReceivableDetailVoPage.setRecords(tenantReceivableDetailPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantReceivableDetailVoPage;
	}

	@ApiOperation(value = "新增应收明细账")
	@RequestMapping(value = "/tenant-receivable-details", method = RequestMethod.POST)
	public TenantReceivableDetailVo save(@RequestBody TenantReceivableDetail tenantReceivableDetail) {
		if (tenantReceivableDetail.getId() == null || tenantReceivableDetail.getId().compareTo(0L) <= 0) {
			tenantReceivableDetail.setId(idService.selectId());
		}
		boolean success = tenantReceivableDetailService.save(tenantReceivableDetail);
		if (success) {
			TenantReceivableDetail tenantReceivableDetailDatabase = tenantReceivableDetailService.getById(tenantReceivableDetail.getId());
			return entity2vo(tenantReceivableDetailDatabase);
		}
		log.info("save TenantReceivableDetail fail，{}", ToStringBuilder.reflectionToString(tenantReceivableDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新应收明细账全部信息")
	@RequestMapping(value = "/tenant-receivable-details/{id}", method = RequestMethod.PUT)
	public TenantReceivableDetailVo updateById(@PathVariable("id") Long id, @RequestBody TenantReceivableDetail tenantReceivableDetail) {
		tenantReceivableDetail.setId(id);
		boolean success = tenantReceivableDetailService.updateById(tenantReceivableDetail);
		if (success) {
			TenantReceivableDetail tenantReceivableDetailDatabase = tenantReceivableDetailService.getById(id);
			return entity2vo(tenantReceivableDetailDatabase);
		}
		log.info("update TenantReceivableDetail fail，{}", ToStringBuilder.reflectionToString(tenantReceivableDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新应收明细账信息")
	@RequestMapping(value = "/tenant-receivable-details/{id}", method = RequestMethod.PATCH)
	public TenantReceivableDetailVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantReceivableDetail tenantReceivableDetail) {
        TenantReceivableDetail tenantReceivableDetailWhere = TenantReceivableDetail.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantReceivableDetail> updateWrapperTenantReceivableDetail = new UpdateWrapper<TenantReceivableDetail>();
		updateWrapperTenantReceivableDetail.setEntity(tenantReceivableDetailWhere);
		updateWrapperTenantReceivableDetail.lambda()//
				//.eq(TenantReceivableDetail::getId, id)
				// .set(tenantReceivableDetail.getId() != null, TenantReceivableDetail::getId, tenantReceivableDetail.getId())
				.set(tenantReceivableDetail.getTenantId() != null, TenantReceivableDetail::getTenantId, tenantReceivableDetail.getTenantId())
				.set(tenantReceivableDetail.getReceivableId() != null, TenantReceivableDetail::getReceivableId, tenantReceivableDetail.getReceivableId())
				.set(tenantReceivableDetail.getPriceStepId() != null, TenantReceivableDetail::getPriceStepId, tenantReceivableDetail.getPriceStepId())
				.set(tenantReceivableDetail.getReceivableWaters() != null, TenantReceivableDetail::getReceivableWaters, tenantReceivableDetail.getReceivableWaters())
				.set(tenantReceivableDetail.getArrearsWaters() != null, TenantReceivableDetail::getArrearsWaters, tenantReceivableDetail.getArrearsWaters())
				.set(tenantReceivableDetail.getPriceItemId() != null, TenantReceivableDetail::getPriceItemId, tenantReceivableDetail.getPriceItemId())
				.set(tenantReceivableDetail.getDetailPrice() != null, TenantReceivableDetail::getDetailPrice, tenantReceivableDetail.getDetailPrice())
				.set(tenantReceivableDetail.getReceivableMoney() != null, TenantReceivableDetail::getReceivableMoney, tenantReceivableDetail.getReceivableMoney())
				.set(tenantReceivableDetail.getArrearsMoney() != null, TenantReceivableDetail::getArrearsMoney, tenantReceivableDetail.getArrearsMoney())
				;

		boolean success = tenantReceivableDetailService.update(updateWrapperTenantReceivableDetail);
		if (success) {
			TenantReceivableDetail tenantReceivableDetailDatabase = tenantReceivableDetailService.getById(id);
			return entity2vo(tenantReceivableDetailDatabase);
		}
		log.info("partial update TenantReceivableDetail fail，{}",
				ToStringBuilder.reflectionToString(tenantReceivableDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除应收明细账")
	@RequestMapping(value = "/tenant-receivable-details/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantReceivableDetailService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantReceivableDetailVo entity2vo(TenantReceivableDetail tenantReceivableDetail) {
		if (tenantReceivableDetail == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantReceivableDetail);
		TenantReceivableDetailVo tenantReceivableDetailVo = JSON.parseObject(jsonString, TenantReceivableDetailVo.class);
		if (StringUtils.isEmpty(tenantReceivableDetailVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantReceivableDetail.getTenantId());
			if (tenantInfo != null) {
				tenantReceivableDetailVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantReceivableDetailVo;
	}

}
