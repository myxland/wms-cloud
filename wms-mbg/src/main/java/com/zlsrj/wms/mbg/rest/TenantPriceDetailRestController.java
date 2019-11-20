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
import com.zlsrj.wms.api.dto.TenantPriceDetailQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.vo.TenantPriceDetailVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantPriceDetailService;
import com.zlsrj.wms.mbg.service.ITenantPriceItemService;
import com.zlsrj.wms.mbg.service.ITenantPriceTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "价格明细", tags = { "价格明细操作接口" })
@RestController
@Slf4j
public class TenantPriceDetailRestController {

	@Autowired
	private ITenantPriceDetailService tenantPriceDetailService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private ITenantPriceTypeService tenantPriceTypeService;
	@Autowired
	private ITenantPriceItemService tenantPriceItemService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询价格明细")
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.GET)
	public TenantPriceDetailVo getById(@PathVariable("id") Long id) {
		TenantPriceDetail tenantPriceDetail = tenantPriceDetailService.getById(id);

		return entity2vo(tenantPriceDetail);
	}

	@ApiOperation(value = "根据参数查询价格明细列表")
	@RequestMapping(value = "/tenant-price-details", method = RequestMethod.GET)
	public Page<TenantPriceDetailVo> page(@RequestBody TenantPriceDetailQueryParam tenantPriceDetailQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPriceDetail> pageTenantPriceDetail = new Page<TenantPriceDetail>(page, rows);
		QueryWrapper<TenantPriceDetail> queryWrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		queryWrapperTenantPriceDetail.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantPriceDetail.lambda()
						.eq(tenantPriceDetailQueryParam.getId() != null, TenantPriceDetail::getId, tenantPriceDetailQueryParam.getId())
						.eq(tenantPriceDetailQueryParam.getTenantId() != null, TenantPriceDetail::getTenantId, tenantPriceDetailQueryParam.getTenantId())
						.eq(tenantPriceDetailQueryParam.getPriceTypeId() != null, TenantPriceDetail::getPriceTypeId, tenantPriceDetailQueryParam.getPriceTypeId())
						.eq(tenantPriceDetailQueryParam.getPriceItemId() != null, TenantPriceDetail::getPriceItemId, tenantPriceDetailQueryParam.getPriceItemId())
						.eq(tenantPriceDetailQueryParam.getCalcType() != null, TenantPriceDetail::getCalcType, tenantPriceDetailQueryParam.getCalcType())
						.eq(tenantPriceDetailQueryParam.getPrice() != null, TenantPriceDetail::getPrice, tenantPriceDetailQueryParam.getPrice())
				;

		IPage<TenantPriceDetail> tenantPriceDetailPage = tenantPriceDetailService.page(pageTenantPriceDetail, queryWrapperTenantPriceDetail);

		Page<TenantPriceDetailVo> tenantPriceDetailVoPage = new Page<TenantPriceDetailVo>(page, rows);
		tenantPriceDetailVoPage.setCurrent(tenantPriceDetailPage.getCurrent());
		tenantPriceDetailVoPage.setPages(tenantPriceDetailPage.getPages());
		tenantPriceDetailVoPage.setSize(tenantPriceDetailPage.getSize());
		tenantPriceDetailVoPage.setTotal(tenantPriceDetailPage.getTotal());
		tenantPriceDetailVoPage.setRecords(tenantPriceDetailPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantPriceDetailVoPage;
	}

	@ApiOperation(value = "新增价格明细")
	@RequestMapping(value = "/tenant-price-details", method = RequestMethod.POST)
	public TenantPriceDetailVo save(@RequestBody TenantPriceDetail tenantPriceDetail) {
		if (tenantPriceDetail.getId() == null || tenantPriceDetail.getId().compareTo(0L) <= 0) {
			tenantPriceDetail.setId(idService.selectId());
		}
		boolean success = tenantPriceDetailService.save(tenantPriceDetail);
		if (success) {
			TenantPriceDetail tenantPriceDetailDatabase = tenantPriceDetailService.getById(tenantPriceDetail.getId());
			return entity2vo(tenantPriceDetailDatabase);
		}
		log.info("save TenantPriceDetail fail，{}", ToStringBuilder.reflectionToString(tenantPriceDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新价格明细全部信息")
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.PUT)
	public TenantPriceDetailVo updateById(@PathVariable("id") Long id, @RequestBody TenantPriceDetail tenantPriceDetail) {
		tenantPriceDetail.setId(id);
		boolean success = tenantPriceDetailService.updateById(tenantPriceDetail);
		if (success) {
			TenantPriceDetail tenantPriceDetailDatabase = tenantPriceDetailService.getById(id);
			return entity2vo(tenantPriceDetailDatabase);
		}
		log.info("update TenantPriceDetail fail，{}", ToStringBuilder.reflectionToString(tenantPriceDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新价格明细信息")
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.PATCH)
	public TenantPriceDetailVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantPriceDetail tenantPriceDetail) {
		UpdateWrapper<TenantPriceDetail> updateWrapperTenantPriceDetail = new UpdateWrapper<TenantPriceDetail>();
		updateWrapperTenantPriceDetail.lambda()//
				.eq(TenantPriceDetail::getId, id)
				// .set(tenantPriceDetail.getId() != null, TenantPriceDetail::getId, tenantPriceDetail.getId())
				.set(tenantPriceDetail.getTenantId() != null, TenantPriceDetail::getTenantId, tenantPriceDetail.getTenantId())
				.set(tenantPriceDetail.getPriceTypeId() != null, TenantPriceDetail::getPriceTypeId, tenantPriceDetail.getPriceTypeId())
				.set(tenantPriceDetail.getPriceItemId() != null, TenantPriceDetail::getPriceItemId, tenantPriceDetail.getPriceItemId())
				.set(tenantPriceDetail.getCalcType() != null, TenantPriceDetail::getCalcType, tenantPriceDetail.getCalcType())
				.set(tenantPriceDetail.getPrice() != null, TenantPriceDetail::getPrice, tenantPriceDetail.getPrice())
				;

		boolean success = tenantPriceDetailService.update(updateWrapperTenantPriceDetail);
		if (success) {
			TenantPriceDetail tenantPriceDetailDatabase = tenantPriceDetailService.getById(id);
			return entity2vo(tenantPriceDetailDatabase);
		}
		log.info("partial update TenantPriceDetail fail，{}",
				ToStringBuilder.reflectionToString(tenantPriceDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除价格明细")
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantPriceDetailService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPriceDetailVo entity2vo(TenantPriceDetail tenantPriceDetail) {
		String jsonString = JSON.toJSONString(tenantPriceDetail);
		TenantPriceDetailVo tenantPriceDetailVo = JSON.parseObject(jsonString, TenantPriceDetailVo.class);
		if (StringUtils.isEmpty(tenantPriceDetailVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantPriceDetail.getTenantId());
			if (tenantInfo != null) {
				tenantPriceDetailVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		if(StringUtils.isEmpty(tenantPriceDetailVo.getPriceTypeName())) {
			TenantPriceType tenantPriceType = tenantPriceTypeService.getById(tenantPriceDetail.getPriceTypeId());
			if(tenantPriceType!=null) {
				tenantPriceDetailVo.setPriceTypeName(tenantPriceType.getPriceTypeName());
			}
		}
		if(StringUtils.isEmpty(tenantPriceDetailVo.getPriceItemName())) {
			TenantPriceItem tenantPriceItem = tenantPriceItemService.getById(tenantPriceDetail.getPriceItemId());
			if(tenantPriceItem!=null) {
				tenantPriceDetailVo.setPriceItemName(tenantPriceItem.getPriceItemName());
			}
		}
		return tenantPriceDetailVo;
	}

}
