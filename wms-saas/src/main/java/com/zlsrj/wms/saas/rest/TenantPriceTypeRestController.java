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
import com.zlsrj.wms.api.dto.TenantPriceTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.vo.TenantPriceTypeVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantPriceTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水价分类", tags = { "水价分类操作接口" })
@RestController
@Slf4j
public class TenantPriceTypeRestController {

	@Autowired
	private ITenantPriceTypeService tenantPriceTypeService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询水价分类")
	@RequestMapping(value = "/tenant-price-types/{id}", method = RequestMethod.GET)
	public TenantPriceTypeVo getById(@PathVariable("id") Long id) {
		TenantPriceType tenantPriceType = tenantPriceTypeService.getById(id);

		return entity2vo(tenantPriceType);
	}

	@ApiOperation(value = "根据参数查询水价分类列表")
	@RequestMapping(value = "/tenant-price-types", method = RequestMethod.GET)
	public Page<TenantPriceTypeVo> page(@RequestBody TenantPriceTypeQueryParam tenantPriceTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPriceType> pageTenantPriceType = new Page<TenantPriceType>(page, rows);
		QueryWrapper<TenantPriceType> queryWrapperTenantPriceType = new QueryWrapper<TenantPriceType>();
		queryWrapperTenantPriceType.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantPriceType.lambda()
				.eq(tenantPriceTypeQueryParam.getId() != null, TenantPriceType::getId, tenantPriceTypeQueryParam.getId())
				.eq(tenantPriceTypeQueryParam.getTenantId() != null, TenantPriceType::getTenantId, tenantPriceTypeQueryParam.getTenantId())
				.eq(tenantPriceTypeQueryParam.getPriceTypeName() != null, TenantPriceType::getPriceTypeName, tenantPriceTypeQueryParam.getPriceTypeName())
				.eq(tenantPriceTypeQueryParam.getBottomOn() != null, TenantPriceType::getBottomOn, tenantPriceTypeQueryParam.getBottomOn())
				.eq(tenantPriceTypeQueryParam.getBottomWaters() != null, TenantPriceType::getBottomWaters, tenantPriceTypeQueryParam.getBottomWaters())
				.eq(tenantPriceTypeQueryParam.getTopOn() != null, TenantPriceType::getTopOn, tenantPriceTypeQueryParam.getTopOn())
				.eq(tenantPriceTypeQueryParam.getTopWaters() != null, TenantPriceType::getTopWaters, tenantPriceTypeQueryParam.getTopWaters())
				.eq(tenantPriceTypeQueryParam.getReduceOn() != null, TenantPriceType::getReduceOn, tenantPriceTypeQueryParam.getReduceOn())
				.eq(tenantPriceTypeQueryParam.getReduceWaters() != null, TenantPriceType::getReduceWaters, tenantPriceTypeQueryParam.getReduceWaters())
				.eq(tenantPriceTypeQueryParam.getReduceLowerlimit() != null, TenantPriceType::getReduceLowerlimit, tenantPriceTypeQueryParam.getReduceLowerlimit())
				.eq(tenantPriceTypeQueryParam.getFixedOn() != null, TenantPriceType::getFixedOn, tenantPriceTypeQueryParam.getFixedOn())
				.eq(tenantPriceTypeQueryParam.getFixedWaters() != null, TenantPriceType::getFixedWaters, tenantPriceTypeQueryParam.getFixedWaters())
				;

		IPage<TenantPriceType> tenantPriceTypePage = tenantPriceTypeService.page(pageTenantPriceType, queryWrapperTenantPriceType);

		Page<TenantPriceTypeVo> tenantPriceTypeVoPage = new Page<TenantPriceTypeVo>(page, rows);
		tenantPriceTypeVoPage.setCurrent(tenantPriceTypePage.getCurrent());
		tenantPriceTypeVoPage.setPages(tenantPriceTypePage.getPages());
		tenantPriceTypeVoPage.setSize(tenantPriceTypePage.getSize());
		tenantPriceTypeVoPage.setTotal(tenantPriceTypePage.getTotal());
		tenantPriceTypeVoPage.setRecords(tenantPriceTypePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantPriceTypeVoPage;
	}

	@ApiOperation(value = "新增水价分类")
	@RequestMapping(value = "/tenant-price-types", method = RequestMethod.POST)
	public TenantPriceTypeVo save(@RequestBody TenantPriceType tenantPriceType) {
		if (tenantPriceType.getId() == null || tenantPriceType.getId().compareTo(0L) <= 0) {
			tenantPriceType.setId(idService.selectId());
		}
		boolean success = tenantPriceTypeService.save(tenantPriceType);
		if (success) {
			TenantPriceType tenantPriceTypeDatabase = tenantPriceTypeService.getById(tenantPriceType.getId());
			return entity2vo(tenantPriceTypeDatabase);
		}
		log.info("save TenantPriceType fail，{}", ToStringBuilder.reflectionToString(tenantPriceType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新水价分类全部信息")
	@RequestMapping(value = "/tenant-price-types/{id}", method = RequestMethod.PUT)
	public TenantPriceTypeVo updateById(@PathVariable("id") Long id, @RequestBody TenantPriceType tenantPriceType) {
		tenantPriceType.setId(id);
		boolean success = tenantPriceTypeService.updateById(tenantPriceType);
		if (success) {
			TenantPriceType tenantPriceTypeDatabase = tenantPriceTypeService.getById(id);
			return entity2vo(tenantPriceTypeDatabase);
		}
		log.info("update TenantPriceType fail，{}", ToStringBuilder.reflectionToString(tenantPriceType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新水价分类信息")
	@RequestMapping(value = "/tenant-price-types/{id}", method = RequestMethod.PATCH)
	public TenantPriceTypeVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantPriceType tenantPriceType) {
        TenantPriceType tenantPriceTypeWhere = TenantPriceType.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantPriceType> updateWrapperTenantPriceType = new UpdateWrapper<TenantPriceType>();
		updateWrapperTenantPriceType.setEntity(tenantPriceTypeWhere);
		updateWrapperTenantPriceType.lambda()//
				//.eq(TenantPriceType::getId, id)
				// .set(tenantPriceType.getId() != null, TenantPriceType::getId, tenantPriceType.getId())
				.set(tenantPriceType.getTenantId() != null, TenantPriceType::getTenantId, tenantPriceType.getTenantId())
				.set(tenantPriceType.getPriceTypeName() != null, TenantPriceType::getPriceTypeName, tenantPriceType.getPriceTypeName())
				.set(tenantPriceType.getBottomOn() != null, TenantPriceType::getBottomOn, tenantPriceType.getBottomOn())
				.set(tenantPriceType.getBottomWaters() != null, TenantPriceType::getBottomWaters, tenantPriceType.getBottomWaters())
				.set(tenantPriceType.getTopOn() != null, TenantPriceType::getTopOn, tenantPriceType.getTopOn())
				.set(tenantPriceType.getTopWaters() != null, TenantPriceType::getTopWaters, tenantPriceType.getTopWaters())
				.set(tenantPriceType.getReduceOn() != null, TenantPriceType::getReduceOn, tenantPriceType.getReduceOn())
				.set(tenantPriceType.getReduceWaters() != null, TenantPriceType::getReduceWaters, tenantPriceType.getReduceWaters())
				.set(tenantPriceType.getReduceLowerlimit() != null, TenantPriceType::getReduceLowerlimit, tenantPriceType.getReduceLowerlimit())
				.set(tenantPriceType.getFixedOn() != null, TenantPriceType::getFixedOn, tenantPriceType.getFixedOn())
				.set(tenantPriceType.getFixedWaters() != null, TenantPriceType::getFixedWaters, tenantPriceType.getFixedWaters())
				;

		boolean success = tenantPriceTypeService.update(updateWrapperTenantPriceType);
		if (success) {
			TenantPriceType tenantPriceTypeDatabase = tenantPriceTypeService.getById(id);
			return entity2vo(tenantPriceTypeDatabase);
		}
		log.info("partial update TenantPriceType fail，{}",
				ToStringBuilder.reflectionToString(tenantPriceType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水价分类")
	@RequestMapping(value = "/tenant-price-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantPriceTypeService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPriceTypeVo entity2vo(TenantPriceType tenantPriceType) {
		if (tenantPriceType == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantPriceType);
		TenantPriceTypeVo tenantPriceTypeVo = JSON.parseObject(jsonString, TenantPriceTypeVo.class);
		if (StringUtils.isEmpty(tenantPriceTypeVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantPriceType.getTenantId());
			if (tenantInfo != null) {
				tenantPriceTypeVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantPriceTypeVo;
	}

}
