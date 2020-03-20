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
import com.zlsrj.wms.api.dto.TenantWaterTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.api.vo.TenantWaterTypeVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantWaterTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用水分类", tags = { "用水分类操作接口" })
@RestController
@Slf4j
public class TenantWaterTypeRestController {

	@Autowired
	private ITenantWaterTypeService tenantWaterTypeService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用水分类")
	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.GET)
	public TenantWaterTypeVo getById(@PathVariable("id") String id) {
		TenantWaterType tenantWaterType = tenantWaterTypeService.getById(id);

		return entity2vo(tenantWaterType);
	}

	@ApiOperation(value = "根据参数查询用水分类列表")
	@RequestMapping(value = "/tenant-water-types", method = RequestMethod.GET)
	public Page<TenantWaterTypeVo> page(@RequestBody TenantWaterTypeQueryParam tenantWaterTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantWaterType> pageTenantWaterType = new Page<TenantWaterType>(page, rows);
		QueryWrapper<TenantWaterType> queryWrapperTenantWaterType = new QueryWrapper<TenantWaterType>();
		queryWrapperTenantWaterType.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantWaterType.lambda()
				.eq(tenantWaterTypeQueryParam.getId() != null, TenantWaterType::getId, tenantWaterTypeQueryParam.getId())
				.eq(tenantWaterTypeQueryParam.getTenantId() != null, TenantWaterType::getTenantId, tenantWaterTypeQueryParam.getTenantId())
				.eq(tenantWaterTypeQueryParam.getWaterTypeName() != null, TenantWaterType::getWaterTypeName, tenantWaterTypeQueryParam.getWaterTypeName())
				.eq(tenantWaterTypeQueryParam.getWaterTypeParentId() != null, TenantWaterType::getWaterTypeParentId, tenantWaterTypeQueryParam.getWaterTypeParentId())
				.eq(tenantWaterTypeQueryParam.getDefaultPriceTypeId() != null, TenantWaterType::getDefaultPriceTypeId, tenantWaterTypeQueryParam.getDefaultPriceTypeId())
				.eq(tenantWaterTypeQueryParam.getParentId()!=null,TenantWaterType::getWaterTypeParentId, tenantWaterTypeQueryParam.getParentId())
				.isNull(tenantWaterTypeQueryParam.getParentId()==null, TenantWaterType::getWaterTypeParentId)
				;

		IPage<TenantWaterType> tenantWaterTypePage = tenantWaterTypeService.page(pageTenantWaterType, queryWrapperTenantWaterType);

		Page<TenantWaterTypeVo> tenantWaterTypeVoPage = new Page<TenantWaterTypeVo>(page, rows);
		tenantWaterTypeVoPage.setCurrent(tenantWaterTypePage.getCurrent());
		tenantWaterTypeVoPage.setPages(tenantWaterTypePage.getPages());
		tenantWaterTypeVoPage.setSize(tenantWaterTypePage.getSize());
		tenantWaterTypeVoPage.setTotal(tenantWaterTypePage.getTotal());
		tenantWaterTypeVoPage.setRecords(tenantWaterTypePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantWaterTypeVoPage;
	}

	@ApiOperation(value = "新增用水分类")
	@RequestMapping(value = "/tenant-water-types", method = RequestMethod.POST)
	public TenantWaterTypeVo save(@RequestBody TenantWaterType tenantWaterType) {
		if (tenantWaterType.getId() == null || tenantWaterType.getId().trim().length() <= 0) {
			tenantWaterType.setId(idService.selectId());
		}
		boolean success = tenantWaterTypeService.save(tenantWaterType);
		if (success) {
			TenantWaterType tenantWaterTypeDatabase = tenantWaterTypeService.getById(tenantWaterType.getId());
			return entity2vo(tenantWaterTypeDatabase);
		}
		log.info("save TenantWaterType fail，{}", ToStringBuilder.reflectionToString(tenantWaterType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新用水分类全部信息")
	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.PUT)
	public TenantWaterTypeVo updateById(@PathVariable("id") String id, @RequestBody TenantWaterType tenantWaterType) {
		tenantWaterType.setId(id);
		boolean success = tenantWaterTypeService.updateById(tenantWaterType);
		if (success) {
			TenantWaterType tenantWaterTypeDatabase = tenantWaterTypeService.getById(id);
			return entity2vo(tenantWaterTypeDatabase);
		}
		log.info("update TenantWaterType fail，{}", ToStringBuilder.reflectionToString(tenantWaterType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用水分类信息")
	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.PATCH)
	public TenantWaterTypeVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantWaterType tenantWaterType) {
        TenantWaterType tenantWaterTypeWhere = TenantWaterType.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantWaterType> updateWrapperTenantWaterType = new UpdateWrapper<TenantWaterType>();
		updateWrapperTenantWaterType.setEntity(tenantWaterTypeWhere);
		updateWrapperTenantWaterType.lambda()//
				//.eq(TenantWaterType::getId, id)
				// .set(tenantWaterType.getId() != null, TenantWaterType::getId, tenantWaterType.getId())
				.set(tenantWaterType.getTenantId() != null, TenantWaterType::getTenantId, tenantWaterType.getTenantId())
				.set(tenantWaterType.getWaterTypeName() != null, TenantWaterType::getWaterTypeName, tenantWaterType.getWaterTypeName())
				.set(tenantWaterType.getWaterTypeParentId() != null, TenantWaterType::getWaterTypeParentId, tenantWaterType.getWaterTypeParentId())
				.set(tenantWaterType.getDefaultPriceTypeId() != null, TenantWaterType::getDefaultPriceTypeId, tenantWaterType.getDefaultPriceTypeId())
				;

		boolean success = tenantWaterTypeService.update(updateWrapperTenantWaterType);
		if (success) {
			TenantWaterType tenantWaterTypeDatabase = tenantWaterTypeService.getById(id);
			return entity2vo(tenantWaterTypeDatabase);
		}
		log.info("partial update TenantWaterType fail，{}",
				ToStringBuilder.reflectionToString(tenantWaterType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除用水分类")
	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantWaterTypeService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantWaterTypeVo entity2vo(TenantWaterType tenantWaterType) {
		if (tenantWaterType == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantWaterType);
		TenantWaterTypeVo tenantWaterTypeVo = JSON.parseObject(jsonString, TenantWaterTypeVo.class);
		if (StringUtils.isEmpty(tenantWaterTypeVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantWaterType.getTenantId());
			if (tenantInfo != null) {
				tenantWaterTypeVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantWaterTypeVo;
	}

}
