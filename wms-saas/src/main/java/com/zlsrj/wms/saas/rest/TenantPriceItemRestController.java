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
import com.zlsrj.wms.api.dto.TenantPriceItemAddParam;
import com.zlsrj.wms.api.dto.TenantPriceItemQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceItemUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.api.vo.TenantPriceItemVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantPriceItemService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "费用项目", tags = { "费用项目操作接口" })
@RestController
@Slf4j
public class TenantPriceItemRestController {

	@Autowired
	private ITenantPriceItemService tenantPriceItemService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询费用项目")
	@RequestMapping(value = "/tenant-price-items/{id}", method = RequestMethod.GET)
	public TenantPriceItemVo getById(@PathVariable("id") String id) {
		TenantPriceItem tenantPriceItem = tenantPriceItemService.getById(id);

		return entity2vo(tenantPriceItem);
	}

	@ApiOperation(value = "根据参数查询费用项目列表")
	@RequestMapping(value = "/tenant-price-items/list", method = RequestMethod.GET)
	public List<TenantPriceItemVo> list(@RequestBody TenantPriceItemQueryParam tenantPriceItemQueryParam) {
		QueryWrapper<TenantPriceItem> queryWrapperTenantPriceItem = new QueryWrapper<TenantPriceItem>();
		queryWrapperTenantPriceItem.lambda()
				.eq(tenantPriceItemQueryParam.getId() != null, TenantPriceItem::getId, tenantPriceItemQueryParam.getId())
				.eq(tenantPriceItemQueryParam.getTenantId() != null, TenantPriceItem::getTenantId, tenantPriceItemQueryParam.getTenantId())
				.eq(tenantPriceItemQueryParam.getPriceItemCode() != null, TenantPriceItem::getPriceItemCode, tenantPriceItemQueryParam.getPriceItemCode())
				.eq(tenantPriceItemQueryParam.getPriceItemName() != null, TenantPriceItem::getPriceItemName, tenantPriceItemQueryParam.getPriceItemName())
				.eq(tenantPriceItemQueryParam.getPriceItemTaxRate() != null, TenantPriceItem::getPriceItemTaxRate, tenantPriceItemQueryParam.getPriceItemTaxRate())
				.eq(tenantPriceItemQueryParam.getPriceItemTaxCode() != null, TenantPriceItem::getPriceItemTaxCode, tenantPriceItemQueryParam.getPriceItemTaxCode())
				.eq(tenantPriceItemQueryParam.getAddTime() != null, TenantPriceItem::getAddTime, tenantPriceItemQueryParam.getAddTime())
				.ge(tenantPriceItemQueryParam.getAddTimeStart() != null, TenantPriceItem::getAddTime,tenantPriceItemQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceItemQueryParam.getAddTimeStart()))
				.le(tenantPriceItemQueryParam.getAddTimeEnd() != null, TenantPriceItem::getAddTime,tenantPriceItemQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceItemQueryParam.getAddTimeEnd()))
				.eq(tenantPriceItemQueryParam.getUpdateTime() != null, TenantPriceItem::getUpdateTime, tenantPriceItemQueryParam.getUpdateTime())
				.ge(tenantPriceItemQueryParam.getUpdateTimeStart() != null, TenantPriceItem::getUpdateTime,tenantPriceItemQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceItemQueryParam.getUpdateTimeStart()))
				.le(tenantPriceItemQueryParam.getUpdateTimeEnd() != null, TenantPriceItem::getUpdateTime,tenantPriceItemQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceItemQueryParam.getUpdateTimeEnd()))
				;

		List<TenantPriceItem> tenantPriceItemList = tenantPriceItemService.list(queryWrapperTenantPriceItem);

		List<TenantPriceItemVo> tenantPriceItemVoList = TranslateUtil.translateList(tenantPriceItemList, TenantPriceItemVo.class);

		return tenantPriceItemVoList;
	}
	
	@ApiOperation(value = "根据参数查询费用项目列表")
	@RequestMapping(value = "/tenant-price-items", method = RequestMethod.GET)
	public Page<TenantPriceItemVo> page(@RequestBody TenantPriceItemQueryParam tenantPriceItemQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPriceItem> pageTenantPriceItem = new Page<TenantPriceItem>(page, rows);
		QueryWrapper<TenantPriceItem> queryWrapperTenantPriceItem = new QueryWrapper<TenantPriceItem>();
		queryWrapperTenantPriceItem.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantPriceItem.lambda()
				.eq(tenantPriceItemQueryParam.getId() != null, TenantPriceItem::getId, tenantPriceItemQueryParam.getId())
				.eq(tenantPriceItemQueryParam.getTenantId() != null, TenantPriceItem::getTenantId, tenantPriceItemQueryParam.getTenantId())
				.eq(tenantPriceItemQueryParam.getPriceItemCode() != null, TenantPriceItem::getPriceItemCode, tenantPriceItemQueryParam.getPriceItemCode())
				.eq(tenantPriceItemQueryParam.getPriceItemName() != null, TenantPriceItem::getPriceItemName, tenantPriceItemQueryParam.getPriceItemName())
				.eq(tenantPriceItemQueryParam.getPriceItemTaxRate() != null, TenantPriceItem::getPriceItemTaxRate, tenantPriceItemQueryParam.getPriceItemTaxRate())
				.eq(tenantPriceItemQueryParam.getPriceItemTaxCode() != null, TenantPriceItem::getPriceItemTaxCode, tenantPriceItemQueryParam.getPriceItemTaxCode())
				.eq(tenantPriceItemQueryParam.getAddTime() != null, TenantPriceItem::getAddTime, tenantPriceItemQueryParam.getAddTime())
				.ge(tenantPriceItemQueryParam.getAddTimeStart() != null, TenantPriceItem::getAddTime,tenantPriceItemQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceItemQueryParam.getAddTimeStart()))
				.le(tenantPriceItemQueryParam.getAddTimeEnd() != null, TenantPriceItem::getAddTime,tenantPriceItemQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceItemQueryParam.getAddTimeEnd()))
				.eq(tenantPriceItemQueryParam.getUpdateTime() != null, TenantPriceItem::getUpdateTime, tenantPriceItemQueryParam.getUpdateTime())
				.ge(tenantPriceItemQueryParam.getUpdateTimeStart() != null, TenantPriceItem::getUpdateTime,tenantPriceItemQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceItemQueryParam.getUpdateTimeStart()))
				.le(tenantPriceItemQueryParam.getUpdateTimeEnd() != null, TenantPriceItem::getUpdateTime,tenantPriceItemQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceItemQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantPriceItem> tenantPriceItemPage = tenantPriceItemService.page(pageTenantPriceItem, queryWrapperTenantPriceItem);

		Page<TenantPriceItemVo> tenantPriceItemVoPage = new Page<TenantPriceItemVo>(page, rows);
		tenantPriceItemVoPage.setCurrent(tenantPriceItemPage.getCurrent());
		tenantPriceItemVoPage.setPages(tenantPriceItemPage.getPages());
		tenantPriceItemVoPage.setSize(tenantPriceItemPage.getSize());
		tenantPriceItemVoPage.setTotal(tenantPriceItemPage.getTotal());
		tenantPriceItemVoPage.setRecords(tenantPriceItemPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantPriceItemVoPage;
	}
	
	@ApiOperation(value = "根据参数查询费用项目统计")
	@RequestMapping(value = "/tenant-price-items/aggregation", method = RequestMethod.GET)
	public TenantPriceItemVo aggregation(@RequestBody TenantPriceItemQueryParam tenantPriceItemQueryParam) {
		QueryWrapper<TenantPriceItem> queryWrapperTenantPriceItem = new QueryWrapper<TenantPriceItem>();
		queryWrapperTenantPriceItem.lambda()
				.eq(tenantPriceItemQueryParam.getId() != null, TenantPriceItem::getId, tenantPriceItemQueryParam.getId())
				.eq(tenantPriceItemQueryParam.getTenantId() != null, TenantPriceItem::getTenantId, tenantPriceItemQueryParam.getTenantId())
				.eq(tenantPriceItemQueryParam.getPriceItemCode() != null, TenantPriceItem::getPriceItemCode, tenantPriceItemQueryParam.getPriceItemCode())
				.eq(tenantPriceItemQueryParam.getPriceItemName() != null, TenantPriceItem::getPriceItemName, tenantPriceItemQueryParam.getPriceItemName())
				.eq(tenantPriceItemQueryParam.getPriceItemTaxRate() != null, TenantPriceItem::getPriceItemTaxRate, tenantPriceItemQueryParam.getPriceItemTaxRate())
				.eq(tenantPriceItemQueryParam.getPriceItemTaxCode() != null, TenantPriceItem::getPriceItemTaxCode, tenantPriceItemQueryParam.getPriceItemTaxCode())
				.eq(tenantPriceItemQueryParam.getAddTime() != null, TenantPriceItem::getAddTime, tenantPriceItemQueryParam.getAddTime())
				.ge(tenantPriceItemQueryParam.getAddTimeStart() != null, TenantPriceItem::getAddTime,tenantPriceItemQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceItemQueryParam.getAddTimeStart()))
				.le(tenantPriceItemQueryParam.getAddTimeEnd() != null, TenantPriceItem::getAddTime,tenantPriceItemQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceItemQueryParam.getAddTimeEnd()))
				.eq(tenantPriceItemQueryParam.getUpdateTime() != null, TenantPriceItem::getUpdateTime, tenantPriceItemQueryParam.getUpdateTime())
				.ge(tenantPriceItemQueryParam.getUpdateTimeStart() != null, TenantPriceItem::getUpdateTime,tenantPriceItemQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceItemQueryParam.getUpdateTimeStart()))
				.le(tenantPriceItemQueryParam.getUpdateTimeEnd() != null, TenantPriceItem::getUpdateTime,tenantPriceItemQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceItemQueryParam.getUpdateTimeEnd()))
				;

		TenantPriceItem tenantPriceItem = tenantPriceItemService.getAggregation(queryWrapperTenantPriceItem);
		
		return entity2vo(tenantPriceItem);
	}

	@ApiOperation(value = "新增费用项目")
	@RequestMapping(value = "/tenant-price-items", method = RequestMethod.POST)
	public String save(@RequestBody TenantPriceItemAddParam tenantPriceItemAddParam) {
		return tenantPriceItemService.save(tenantPriceItemAddParam);
	}

	@ApiOperation(value = "更新费用项目全部信息")
	@RequestMapping(value = "/tenant-price-items/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantPriceItemUpdateParam tenantPriceItemUpdateParam) {
		tenantPriceItemUpdateParam.setId(id);
		return tenantPriceItemService.updateById(tenantPriceItemUpdateParam);
	}

	@ApiOperation(value = "根据参数更新费用项目信息")
	@RequestMapping(value = "/tenant-price-items/{id}", method = RequestMethod.PATCH)
	public TenantPriceItemVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPriceItem tenantPriceItem) {
        TenantPriceItem tenantPriceItemWhere = TenantPriceItem.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantPriceItem> updateWrapperTenantPriceItem = new UpdateWrapper<TenantPriceItem>();
		updateWrapperTenantPriceItem.setEntity(tenantPriceItemWhere);
		updateWrapperTenantPriceItem.lambda()//
				//.eq(TenantPriceItem::getId, id)
				// .set(tenantPriceItem.getId() != null, TenantPriceItem::getId, tenantPriceItem.getId())
				.set(tenantPriceItem.getTenantId() != null, TenantPriceItem::getTenantId, tenantPriceItem.getTenantId())
				.set(tenantPriceItem.getPriceItemCode() != null, TenantPriceItem::getPriceItemCode, tenantPriceItem.getPriceItemCode())
				.set(tenantPriceItem.getPriceItemName() != null, TenantPriceItem::getPriceItemName, tenantPriceItem.getPriceItemName())
				.set(tenantPriceItem.getPriceItemTaxRate() != null, TenantPriceItem::getPriceItemTaxRate, tenantPriceItem.getPriceItemTaxRate())
				.set(tenantPriceItem.getPriceItemTaxCode() != null, TenantPriceItem::getPriceItemTaxCode, tenantPriceItem.getPriceItemTaxCode())
				.set(tenantPriceItem.getAddTime() != null, TenantPriceItem::getAddTime, tenantPriceItem.getAddTime())
				.set(tenantPriceItem.getUpdateTime() != null, TenantPriceItem::getUpdateTime, tenantPriceItem.getUpdateTime())
				;

		boolean success = tenantPriceItemService.update(updateWrapperTenantPriceItem);
		if (success) {
			TenantPriceItem tenantPriceItemDatabase = tenantPriceItemService.getById(id);
			return entity2vo(tenantPriceItemDatabase);
		}
		log.info("partial update TenantPriceItem fail，{}",
				ToStringBuilder.reflectionToString(tenantPriceItem, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除费用项目")
	@RequestMapping(value = "/tenant-price-items/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantPriceItemService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPriceItemVo entity2vo(TenantPriceItem tenantPriceItem) {
		if (tenantPriceItem == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantPriceItem);
		TenantPriceItemVo tenantPriceItemVo = JSON.parseObject(jsonString, TenantPriceItemVo.class);
		if (StringUtils.isEmpty(tenantPriceItemVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantPriceItem.getTenantId());
			if (tenantInfo != null) {
				tenantPriceItemVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantPriceItemVo;
	}

}
