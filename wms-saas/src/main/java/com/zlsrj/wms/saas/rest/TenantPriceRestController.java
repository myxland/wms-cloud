package com.zlsrj.wms.saas.rest;

import java.util.Date;
import java.util.List;
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
import com.zlsrj.wms.api.dto.TenantPriceAddParam;
import com.zlsrj.wms.api.dto.TenantPriceQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPrice;
import com.zlsrj.wms.api.vo.TenantPriceVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantPriceService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水价列表", tags = { "水价列表操作接口" })
@RestController
@Slf4j
public class TenantPriceRestController {

	@Autowired
	private ITenantPriceService tenantPriceService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询水价列表")
	@RequestMapping(value = "/tenant-prices/{id}", method = RequestMethod.GET)
	public TenantPriceVo getById(@PathVariable("id") String id) {
		TenantPrice tenantPrice = tenantPriceService.getById(id);

		return entity2vo(tenantPrice);
	}

	@ApiOperation(value = "根据参数查询水价列表列表")
	@RequestMapping(value = "/tenant-prices/list", method = RequestMethod.GET)
	public List<TenantPriceVo> list(@RequestBody TenantPriceQueryParam tenantPriceQueryParam) {
		QueryWrapper<TenantPrice> queryWrapperTenantPrice = new QueryWrapper<TenantPrice>();
		queryWrapperTenantPrice.lambda()
				.eq(tenantPriceQueryParam.getId() != null, TenantPrice::getId, tenantPriceQueryParam.getId())
				.eq(tenantPriceQueryParam.getTenantId() != null, TenantPrice::getTenantId, tenantPriceQueryParam.getTenantId())
				.eq(tenantPriceQueryParam.getPriceOrder() != null, TenantPrice::getPriceOrder, tenantPriceQueryParam.getPriceOrder())
				.eq(tenantPriceQueryParam.getPriceName() != null, TenantPrice::getPriceName, tenantPriceQueryParam.getPriceName())
				.eq(tenantPriceQueryParam.getPriceParentId() != null, TenantPrice::getPriceParentId, tenantPriceQueryParam.getPriceParentId())
				.eq(tenantPriceQueryParam.getPriceVersion() != null, TenantPrice::getPriceVersion, tenantPriceQueryParam.getPriceVersion())
				.eq(tenantPriceQueryParam.getPriceVersionMemo() != null, TenantPrice::getPriceVersionMemo, tenantPriceQueryParam.getPriceVersionMemo())
				.eq(tenantPriceQueryParam.getMarketingAreaId() != null, TenantPrice::getMarketingAreaId, tenantPriceQueryParam.getMarketingAreaId())
				.eq(tenantPriceQueryParam.getPriceMemo() != null, TenantPrice::getPriceMemo, tenantPriceQueryParam.getPriceMemo())
				.eq(tenantPriceQueryParam.getAddTime() != null, TenantPrice::getAddTime, tenantPriceQueryParam.getAddTime())
				.ge(tenantPriceQueryParam.getAddTimeStart() != null, TenantPrice::getAddTime,tenantPriceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceQueryParam.getAddTimeStart()))
				.le(tenantPriceQueryParam.getAddTimeEnd() != null, TenantPrice::getAddTime,tenantPriceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceQueryParam.getAddTimeEnd()))
				.eq(tenantPriceQueryParam.getUpdateTime() != null, TenantPrice::getUpdateTime, tenantPriceQueryParam.getUpdateTime())
				.ge(tenantPriceQueryParam.getUpdateTimeStart() != null, TenantPrice::getUpdateTime,tenantPriceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceQueryParam.getUpdateTimeStart()))
				.le(tenantPriceQueryParam.getUpdateTimeEnd() != null, TenantPrice::getUpdateTime,tenantPriceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceQueryParam.getUpdateTimeEnd()))
				.eq(tenantPriceQueryParam.getParentId()!=null,TenantPrice::getPriceParentId, tenantPriceQueryParam.getParentId())
				;

		List<TenantPrice> tenantPriceList = tenantPriceService.list(queryWrapperTenantPrice);

		List<TenantPriceVo> tenantPriceVoList = TranslateUtil.translateList(tenantPriceList, TenantPriceVo.class);

		return tenantPriceVoList;
	}
	
	@ApiOperation(value = "根据参数查询水价列表列表")
	@RequestMapping(value = "/tenant-prices", method = RequestMethod.GET)
	public Page<TenantPriceVo> page(@RequestBody TenantPriceQueryParam tenantPriceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPrice> pageTenantPrice = new Page<TenantPrice>(page, rows);
		QueryWrapper<TenantPrice> queryWrapperTenantPrice = new QueryWrapper<TenantPrice>();
		queryWrapperTenantPrice.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantPrice.lambda()
				.eq(tenantPriceQueryParam.getId() != null, TenantPrice::getId, tenantPriceQueryParam.getId())
				.eq(tenantPriceQueryParam.getTenantId() != null, TenantPrice::getTenantId, tenantPriceQueryParam.getTenantId())
				.eq(tenantPriceQueryParam.getPriceOrder() != null, TenantPrice::getPriceOrder, tenantPriceQueryParam.getPriceOrder())
				.eq(tenantPriceQueryParam.getPriceName() != null, TenantPrice::getPriceName, tenantPriceQueryParam.getPriceName())
				.eq(tenantPriceQueryParam.getPriceParentId() != null, TenantPrice::getPriceParentId, tenantPriceQueryParam.getPriceParentId())
				.eq(tenantPriceQueryParam.getPriceVersion() != null, TenantPrice::getPriceVersion, tenantPriceQueryParam.getPriceVersion())
				.eq(tenantPriceQueryParam.getPriceVersionMemo() != null, TenantPrice::getPriceVersionMemo, tenantPriceQueryParam.getPriceVersionMemo())
				.eq(tenantPriceQueryParam.getMarketingAreaId() != null, TenantPrice::getMarketingAreaId, tenantPriceQueryParam.getMarketingAreaId())
				.eq(tenantPriceQueryParam.getPriceMemo() != null, TenantPrice::getPriceMemo, tenantPriceQueryParam.getPriceMemo())
				.eq(tenantPriceQueryParam.getAddTime() != null, TenantPrice::getAddTime, tenantPriceQueryParam.getAddTime())
				.ge(tenantPriceQueryParam.getAddTimeStart() != null, TenantPrice::getAddTime,tenantPriceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceQueryParam.getAddTimeStart()))
				.le(tenantPriceQueryParam.getAddTimeEnd() != null, TenantPrice::getAddTime,tenantPriceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceQueryParam.getAddTimeEnd()))
				.eq(tenantPriceQueryParam.getUpdateTime() != null, TenantPrice::getUpdateTime, tenantPriceQueryParam.getUpdateTime())
				.ge(tenantPriceQueryParam.getUpdateTimeStart() != null, TenantPrice::getUpdateTime,tenantPriceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceQueryParam.getUpdateTimeStart()))
				.le(tenantPriceQueryParam.getUpdateTimeEnd() != null, TenantPrice::getUpdateTime,tenantPriceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceQueryParam.getUpdateTimeEnd()))
				.eq(tenantPriceQueryParam.getParentId()!=null,TenantPrice::getPriceParentId, tenantPriceQueryParam.getParentId())
				.isNull(tenantPriceQueryParam.getParentId()==null, TenantPrice::getPriceParentId)
				;

		IPage<TenantPrice> tenantPricePage = tenantPriceService.page(pageTenantPrice, queryWrapperTenantPrice);

		Page<TenantPriceVo> tenantPriceVoPage = new Page<TenantPriceVo>(page, rows);
		tenantPriceVoPage.setCurrent(tenantPricePage.getCurrent());
		tenantPriceVoPage.setPages(tenantPricePage.getPages());
		tenantPriceVoPage.setSize(tenantPricePage.getSize());
		tenantPriceVoPage.setTotal(tenantPricePage.getTotal());
		tenantPriceVoPage.setRecords(tenantPricePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantPriceVoPage;
	}
	
	@ApiOperation(value = "根据参数查询水价列表统计")
	@RequestMapping(value = "/tenant-prices/aggregation", method = RequestMethod.GET)
	public TenantPriceVo aggregation(@RequestBody TenantPriceQueryParam tenantPriceQueryParam) {
		QueryWrapper<TenantPrice> queryWrapperTenantPrice = new QueryWrapper<TenantPrice>();
		queryWrapperTenantPrice.lambda()
				.eq(tenantPriceQueryParam.getId() != null, TenantPrice::getId, tenantPriceQueryParam.getId())
				.eq(tenantPriceQueryParam.getTenantId() != null, TenantPrice::getTenantId, tenantPriceQueryParam.getTenantId())
				.eq(tenantPriceQueryParam.getPriceOrder() != null, TenantPrice::getPriceOrder, tenantPriceQueryParam.getPriceOrder())
				.eq(tenantPriceQueryParam.getPriceName() != null, TenantPrice::getPriceName, tenantPriceQueryParam.getPriceName())
				.eq(tenantPriceQueryParam.getPriceParentId() != null, TenantPrice::getPriceParentId, tenantPriceQueryParam.getPriceParentId())
				.eq(tenantPriceQueryParam.getPriceVersion() != null, TenantPrice::getPriceVersion, tenantPriceQueryParam.getPriceVersion())
				.eq(tenantPriceQueryParam.getPriceVersionMemo() != null, TenantPrice::getPriceVersionMemo, tenantPriceQueryParam.getPriceVersionMemo())
				.eq(tenantPriceQueryParam.getMarketingAreaId() != null, TenantPrice::getMarketingAreaId, tenantPriceQueryParam.getMarketingAreaId())
				.eq(tenantPriceQueryParam.getPriceMemo() != null, TenantPrice::getPriceMemo, tenantPriceQueryParam.getPriceMemo())
				.eq(tenantPriceQueryParam.getAddTime() != null, TenantPrice::getAddTime, tenantPriceQueryParam.getAddTime())
				.ge(tenantPriceQueryParam.getAddTimeStart() != null, TenantPrice::getAddTime,tenantPriceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceQueryParam.getAddTimeStart()))
				.le(tenantPriceQueryParam.getAddTimeEnd() != null, TenantPrice::getAddTime,tenantPriceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceQueryParam.getAddTimeEnd()))
				.eq(tenantPriceQueryParam.getUpdateTime() != null, TenantPrice::getUpdateTime, tenantPriceQueryParam.getUpdateTime())
				.ge(tenantPriceQueryParam.getUpdateTimeStart() != null, TenantPrice::getUpdateTime,tenantPriceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceQueryParam.getUpdateTimeStart()))
				.le(tenantPriceQueryParam.getUpdateTimeEnd() != null, TenantPrice::getUpdateTime,tenantPriceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceQueryParam.getUpdateTimeEnd()))
				.eq(tenantPriceQueryParam.getParentId()!=null,TenantPrice::getPriceParentId, tenantPriceQueryParam.getParentId())
				.isNull(tenantPriceQueryParam.getParentId()==null, TenantPrice::getPriceParentId)
				;

		TenantPrice tenantPrice = tenantPriceService.getAggregation(queryWrapperTenantPrice);
		
		return entity2vo(tenantPrice);
	}

	@ApiOperation(value = "新增水价列表")
	@RequestMapping(value = "/tenant-prices", method = RequestMethod.POST)
	public String save(@RequestBody TenantPriceAddParam tenantPriceAddParam) {
		return tenantPriceService.save(tenantPriceAddParam);
	}

	@ApiOperation(value = "更新水价列表全部信息")
	@RequestMapping(value = "/tenant-prices/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantPriceUpdateParam tenantPriceUpdateParam) {
		tenantPriceUpdateParam.setId(id);
		return tenantPriceService.updateById(tenantPriceUpdateParam);
	}

	@ApiOperation(value = "根据参数更新水价列表信息")
	@RequestMapping(value = "/tenant-prices/{id}", method = RequestMethod.PATCH)
	public TenantPriceVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPrice tenantPrice) {
        TenantPrice tenantPriceWhere = TenantPrice.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantPrice> updateWrapperTenantPrice = new UpdateWrapper<TenantPrice>();
		updateWrapperTenantPrice.setEntity(tenantPriceWhere);
		updateWrapperTenantPrice.lambda()//
				//.eq(TenantPrice::getId, id)
				// .set(tenantPrice.getId() != null, TenantPrice::getId, tenantPrice.getId())
				.set(tenantPrice.getTenantId() != null, TenantPrice::getTenantId, tenantPrice.getTenantId())
				.set(tenantPrice.getPriceOrder() != null, TenantPrice::getPriceOrder, tenantPrice.getPriceOrder())
				.set(tenantPrice.getPriceName() != null, TenantPrice::getPriceName, tenantPrice.getPriceName())
				.set(tenantPrice.getPriceParentId() != null, TenantPrice::getPriceParentId, tenantPrice.getPriceParentId())
				.set(tenantPrice.getPriceVersion() != null, TenantPrice::getPriceVersion, tenantPrice.getPriceVersion())
				.set(tenantPrice.getPriceVersionMemo() != null, TenantPrice::getPriceVersionMemo, tenantPrice.getPriceVersionMemo())
				.set(tenantPrice.getMarketingAreaId() != null, TenantPrice::getMarketingAreaId, tenantPrice.getMarketingAreaId())
				.set(tenantPrice.getPriceMemo() != null, TenantPrice::getPriceMemo, tenantPrice.getPriceMemo())
				.set(tenantPrice.getAddTime() != null, TenantPrice::getAddTime, tenantPrice.getAddTime())
				.set(tenantPrice.getUpdateTime() != null, TenantPrice::getUpdateTime, tenantPrice.getUpdateTime())
				;

		boolean success = tenantPriceService.update(updateWrapperTenantPrice);
		if (success) {
			TenantPrice tenantPriceDatabase = tenantPriceService.getById(id);
			return entity2vo(tenantPriceDatabase);
		}
		log.info("partial update TenantPrice fail，{}",
				ToStringBuilder.reflectionToString(tenantPrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水价列表")
	@RequestMapping(value = "/tenant-prices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantPriceService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPriceVo entity2vo(TenantPrice tenantPrice) {
		if (tenantPrice == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantPrice);
		TenantPriceVo tenantPriceVo = JSON.parseObject(jsonString, TenantPriceVo.class);
		if (StringUtils.isEmpty(tenantPriceVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantPrice.getTenantId());
			if (tenantInfo != null) {
				tenantPriceVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantPriceVo;
	}

}
