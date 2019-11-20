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
import com.zlsrj.wms.api.dto.TenantPriceItemQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.api.vo.TenantPriceItemVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantPriceItemService;

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
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询费用项目")
	@RequestMapping(value = "/tenant-price-items/{id}", method = RequestMethod.GET)
	public TenantPriceItemVo getById(@PathVariable("id") Long id) {
		TenantPriceItem tenantPriceItem = tenantPriceItemService.getById(id);

		return entity2vo(tenantPriceItem);
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
		queryWrapperTenantPriceItem.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantPriceItem.lambda()
						.eq(tenantPriceItemQueryParam.getId() != null, TenantPriceItem::getId, tenantPriceItemQueryParam.getId())
						.eq(tenantPriceItemQueryParam.getTenantId() != null, TenantPriceItem::getTenantId, tenantPriceItemQueryParam.getTenantId())
						.eq(tenantPriceItemQueryParam.getPriceItemName() != null, TenantPriceItem::getPriceItemName, tenantPriceItemQueryParam.getPriceItemName())
						.eq(tenantPriceItemQueryParam.getTaxRate() != null, TenantPriceItem::getTaxRate, tenantPriceItemQueryParam.getTaxRate())
						.eq(tenantPriceItemQueryParam.getTaxId() != null, TenantPriceItem::getTaxId, tenantPriceItemQueryParam.getTaxId())
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

	@ApiOperation(value = "新增费用项目")
	@RequestMapping(value = "/tenant-price-items", method = RequestMethod.POST)
	public TenantPriceItemVo save(@RequestBody TenantPriceItem tenantPriceItem) {
		if (tenantPriceItem.getId() == null || tenantPriceItem.getId().compareTo(0L) <= 0) {
			tenantPriceItem.setId(idService.selectId());
		}
		boolean success = tenantPriceItemService.save(tenantPriceItem);
		if (success) {
			TenantPriceItem tenantPriceItemDatabase = tenantPriceItemService.getById(tenantPriceItem.getId());
			return entity2vo(tenantPriceItemDatabase);
		}
		log.info("save TenantPriceItem fail，{}", ToStringBuilder.reflectionToString(tenantPriceItem, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新费用项目全部信息")
	@RequestMapping(value = "/tenant-price-items/{id}", method = RequestMethod.PUT)
	public TenantPriceItemVo updateById(@PathVariable("id") Long id, @RequestBody TenantPriceItem tenantPriceItem) {
		tenantPriceItem.setId(id);
		boolean success = tenantPriceItemService.updateById(tenantPriceItem);
		if (success) {
			TenantPriceItem tenantPriceItemDatabase = tenantPriceItemService.getById(id);
			return entity2vo(tenantPriceItemDatabase);
		}
		log.info("update TenantPriceItem fail，{}", ToStringBuilder.reflectionToString(tenantPriceItem, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新费用项目信息")
	@RequestMapping(value = "/tenant-price-items/{id}", method = RequestMethod.PATCH)
	public TenantPriceItemVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantPriceItem tenantPriceItem) {
		UpdateWrapper<TenantPriceItem> updateWrapperTenantPriceItem = new UpdateWrapper<TenantPriceItem>();
		updateWrapperTenantPriceItem.lambda()//
				.eq(TenantPriceItem::getId, id)
				// .set(tenantPriceItem.getId() != null, TenantPriceItem::getId, tenantPriceItem.getId())
				.set(tenantPriceItem.getTenantId() != null, TenantPriceItem::getTenantId, tenantPriceItem.getTenantId())
				.set(tenantPriceItem.getPriceItemName() != null, TenantPriceItem::getPriceItemName, tenantPriceItem.getPriceItemName())
				.set(tenantPriceItem.getTaxRate() != null, TenantPriceItem::getTaxRate, tenantPriceItem.getTaxRate())
				.set(tenantPriceItem.getTaxId() != null, TenantPriceItem::getTaxId, tenantPriceItem.getTaxId())
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
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantPriceItemService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPriceItemVo entity2vo(TenantPriceItem tenantPriceItem) {
		String jsonString = JSON.toJSONString(tenantPriceItem);
		TenantPriceItemVo tenantPriceItemVo = JSON.parseObject(jsonString, TenantPriceItemVo.class);
		if (StringUtils.isEmpty(tenantPriceItemVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantPriceItem.getTenantId());
			if (tenantInfo != null) {
				tenantPriceItemVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantPriceItemVo;
	}

}
