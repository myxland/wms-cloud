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
import com.zlsrj.wms.api.dto.TenantWaterTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.api.vo.TenantWaterTypeVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantWaterTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用水类型", tags = { "用水类型操作接口" })
@RestController
@Slf4j
public class TenantWaterTypeRestController {

	@Autowired
	private ITenantWaterTypeService tenantWaterTypeService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用水类型")
	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.GET)
	public TenantWaterTypeVo getById(@PathVariable("id") Long id) {
		TenantWaterType tenantWaterType = tenantWaterTypeService.getById(id);

		return entity2vo(tenantWaterType);
	}

	@ApiOperation(value = "根据参数查询用水类型列表")
	@RequestMapping(value = "/tenant-water-types", method = RequestMethod.GET)
	public Page<TenantWaterTypeVo> page(@RequestBody TenantWaterTypeQueryParam tenantWaterTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantWaterType> pageTenantWaterType = new Page<TenantWaterType>(page, rows);
		QueryWrapper<TenantWaterType> queryWrapperTenantWaterType = new QueryWrapper<TenantWaterType>();
		queryWrapperTenantWaterType.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantWaterType.lambda()
						.eq(tenantWaterTypeQueryParam.getId() != null, TenantWaterType::getId, tenantWaterTypeQueryParam.getId())
						.eq(tenantWaterTypeQueryParam.getTenantId() != null, TenantWaterType::getTenantId, tenantWaterTypeQueryParam.getTenantId())
						.eq(tenantWaterTypeQueryParam.getWaterTypeName() != null, TenantWaterType::getWaterTypeName, tenantWaterTypeQueryParam.getWaterTypeName())
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

	@ApiOperation(value = "新增用水类型")
	@RequestMapping(value = "/tenant-water-types", method = RequestMethod.POST)
	public TenantWaterTypeVo save(@RequestBody TenantWaterType tenantWaterType) {
		if (tenantWaterType.getId() == null || tenantWaterType.getId().compareTo(0L) <= 0) {
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

	@ApiOperation(value = "更新用水类型全部信息")
	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.PUT)
	public TenantWaterTypeVo updateById(@PathVariable("id") Long id, @RequestBody TenantWaterType tenantWaterType) {
		tenantWaterType.setId(id);
		boolean success = tenantWaterTypeService.updateById(tenantWaterType);
		if (success) {
			TenantWaterType tenantWaterTypeDatabase = tenantWaterTypeService.getById(id);
			return entity2vo(tenantWaterTypeDatabase);
		}
		log.info("update TenantWaterType fail，{}", ToStringBuilder.reflectionToString(tenantWaterType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用水类型信息")
	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.PATCH)
	public TenantWaterTypeVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantWaterType tenantWaterType) {
		UpdateWrapper<TenantWaterType> updateWrapperTenantWaterType = new UpdateWrapper<TenantWaterType>();
		updateWrapperTenantWaterType.lambda()//
				.eq(TenantWaterType::getId, id)
				// .set(tenantWaterType.getId() != null, TenantWaterType::getId, tenantWaterType.getId())
				.set(tenantWaterType.getTenantId() != null, TenantWaterType::getTenantId, tenantWaterType.getTenantId())
				.set(tenantWaterType.getWaterTypeName() != null, TenantWaterType::getWaterTypeName, tenantWaterType.getWaterTypeName())
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

	@ApiOperation(value = "根据ID删除用水类型")
	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantWaterTypeService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantWaterTypeVo entity2vo(TenantWaterType tenantWaterType) {
		String jsonString = JSON.toJSONString(tenantWaterType);
		TenantWaterTypeVo tenantWaterTypeVo = JSON.parseObject(jsonString, TenantWaterTypeVo.class);
		if (StringUtils.isEmpty(tenantWaterTypeVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantWaterType.getTenantId());
			if (tenantInfo != null) {
				tenantWaterTypeVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantWaterTypeVo;
	}

}
