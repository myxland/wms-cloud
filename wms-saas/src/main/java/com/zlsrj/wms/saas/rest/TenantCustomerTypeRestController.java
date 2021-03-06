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
import com.zlsrj.wms.api.dto.TenantCustomerTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomerType;
import com.zlsrj.wms.api.vo.TenantCustomerTypeVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCustomerTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户分类", tags = { "用户分类操作接口" })
@RestController
@Slf4j
public class TenantCustomerTypeRestController {

	@Autowired
	private ITenantCustomerTypeService tenantCustomerTypeService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用户分类")
	@RequestMapping(value = "/tenant-customer-types/{id}", method = RequestMethod.GET)
	public TenantCustomerTypeVo getById(@PathVariable("id") Long id) {
		TenantCustomerType tenantCustomerType = tenantCustomerTypeService.getById(id);

		return entity2vo(tenantCustomerType);
	}

	@ApiOperation(value = "根据参数查询用户分类列表")
	@RequestMapping(value = "/tenant-customer-types", method = RequestMethod.GET)
	public Page<TenantCustomerTypeVo> page(@RequestBody TenantCustomerTypeQueryParam tenantCustomerTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustomerType> pageTenantCustomerType = new Page<TenantCustomerType>(page, rows);
		QueryWrapper<TenantCustomerType> queryWrapperTenantCustomerType = new QueryWrapper<TenantCustomerType>();
		queryWrapperTenantCustomerType.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomerType.lambda()
				.eq(tenantCustomerTypeQueryParam.getId() != null, TenantCustomerType::getId, tenantCustomerTypeQueryParam.getId())
				.eq(tenantCustomerTypeQueryParam.getTenantId() != null, TenantCustomerType::getTenantId, tenantCustomerTypeQueryParam.getTenantId())
				.eq(tenantCustomerTypeQueryParam.getCustomerTypeName() != null, TenantCustomerType::getCustomerTypeName, tenantCustomerTypeQueryParam.getCustomerTypeName())
				.eq(tenantCustomerTypeQueryParam.getCustomerTypeParentId() != null, TenantCustomerType::getCustomerTypeParentId, tenantCustomerTypeQueryParam.getCustomerTypeParentId())
				.eq(tenantCustomerTypeQueryParam.getParentId()!=null,TenantCustomerType::getCustomerTypeParentId, tenantCustomerTypeQueryParam.getParentId())
				.isNull(tenantCustomerTypeQueryParam.getParentId()==null, TenantCustomerType::getCustomerTypeParentId)
				;

		IPage<TenantCustomerType> tenantCustomerTypePage = tenantCustomerTypeService.page(pageTenantCustomerType, queryWrapperTenantCustomerType);

		Page<TenantCustomerTypeVo> tenantCustomerTypeVoPage = new Page<TenantCustomerTypeVo>(page, rows);
		tenantCustomerTypeVoPage.setCurrent(tenantCustomerTypePage.getCurrent());
		tenantCustomerTypeVoPage.setPages(tenantCustomerTypePage.getPages());
		tenantCustomerTypeVoPage.setSize(tenantCustomerTypePage.getSize());
		tenantCustomerTypeVoPage.setTotal(tenantCustomerTypePage.getTotal());
		tenantCustomerTypeVoPage.setRecords(tenantCustomerTypePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustomerTypeVoPage;
	}

	@ApiOperation(value = "新增用户分类")
	@RequestMapping(value = "/tenant-customer-types", method = RequestMethod.POST)
	public TenantCustomerTypeVo save(@RequestBody TenantCustomerType tenantCustomerType) {
		if (tenantCustomerType.getId() == null || tenantCustomerType.getId().compareTo(0L) <= 0) {
			tenantCustomerType.setId(idService.selectId());
		}
		boolean success = tenantCustomerTypeService.save(tenantCustomerType);
		if (success) {
			TenantCustomerType tenantCustomerTypeDatabase = tenantCustomerTypeService.getById(tenantCustomerType.getId());
			return entity2vo(tenantCustomerTypeDatabase);
		}
		log.info("save TenantCustomerType fail，{}", ToStringBuilder.reflectionToString(tenantCustomerType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新用户分类全部信息")
	@RequestMapping(value = "/tenant-customer-types/{id}", method = RequestMethod.PUT)
	public TenantCustomerTypeVo updateById(@PathVariable("id") Long id, @RequestBody TenantCustomerType tenantCustomerType) {
		tenantCustomerType.setId(id);
		boolean success = tenantCustomerTypeService.updateById(tenantCustomerType);
		if (success) {
			TenantCustomerType tenantCustomerTypeDatabase = tenantCustomerTypeService.getById(id);
			return entity2vo(tenantCustomerTypeDatabase);
		}
		log.info("update TenantCustomerType fail，{}", ToStringBuilder.reflectionToString(tenantCustomerType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用户分类信息")
	@RequestMapping(value = "/tenant-customer-types/{id}", method = RequestMethod.PATCH)
	public TenantCustomerTypeVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantCustomerType tenantCustomerType) {
        TenantCustomerType tenantCustomerTypeWhere = TenantCustomerType.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantCustomerType> updateWrapperTenantCustomerType = new UpdateWrapper<TenantCustomerType>();
		updateWrapperTenantCustomerType.setEntity(tenantCustomerTypeWhere);
		updateWrapperTenantCustomerType.lambda()//
				//.eq(TenantCustomerType::getId, id)
				// .set(tenantCustomerType.getId() != null, TenantCustomerType::getId, tenantCustomerType.getId())
				.set(tenantCustomerType.getTenantId() != null, TenantCustomerType::getTenantId, tenantCustomerType.getTenantId())
				.set(tenantCustomerType.getCustomerTypeName() != null, TenantCustomerType::getCustomerTypeName, tenantCustomerType.getCustomerTypeName())
				.set(tenantCustomerType.getCustomerTypeParentId() != null, TenantCustomerType::getCustomerTypeParentId, tenantCustomerType.getCustomerTypeParentId())
				;

		boolean success = tenantCustomerTypeService.update(updateWrapperTenantCustomerType);
		if (success) {
			TenantCustomerType tenantCustomerTypeDatabase = tenantCustomerTypeService.getById(id);
			return entity2vo(tenantCustomerTypeDatabase);
		}
		log.info("partial update TenantCustomerType fail，{}",
				ToStringBuilder.reflectionToString(tenantCustomerType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除用户分类")
	@RequestMapping(value = "/tenant-customer-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantCustomerTypeService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerTypeVo entity2vo(TenantCustomerType tenantCustomerType) {
		if (tenantCustomerType == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantCustomerType);
		TenantCustomerTypeVo tenantCustomerTypeVo = JSON.parseObject(jsonString, TenantCustomerTypeVo.class);
		if (StringUtils.isEmpty(tenantCustomerTypeVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantCustomerType.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerTypeVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerTypeVo;
	}

}
