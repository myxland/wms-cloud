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
import com.zlsrj.wms.api.dto.TenantCustTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustType;
import com.zlsrj.wms.api.vo.TenantCustTypeVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantCustTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "", tags = { "操作接口" })
@RestController
@Slf4j
public class TenantCustTypeRestController {

	@Autowired
	private ITenantCustTypeService tenantCustTypeService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询")
	@RequestMapping(value = "/tenant-cust-types/{id}", method = RequestMethod.GET)
	public TenantCustTypeVo getById(@PathVariable("id") Long id) {
		TenantCustType tenantCustType = tenantCustTypeService.getById(id);

		return entity2vo(tenantCustType);
	}

	@ApiOperation(value = "根据参数查询列表")
	@RequestMapping(value = "/tenant-cust-types", method = RequestMethod.GET)
	public Page<TenantCustTypeVo> page(@RequestBody TenantCustTypeQueryParam tenantCustTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustType> pageTenantCustType = new Page<TenantCustType>(page, rows);
		QueryWrapper<TenantCustType> queryWrapperTenantCustType = new QueryWrapper<TenantCustType>();
		queryWrapperTenantCustType.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantCustType.lambda()
						.eq(tenantCustTypeQueryParam.getId() != null, TenantCustType::getId, tenantCustTypeQueryParam.getId())
						.eq(tenantCustTypeQueryParam.getTenantId() != null, TenantCustType::getTenantId, tenantCustTypeQueryParam.getTenantId())
						.eq(tenantCustTypeQueryParam.getCustTypeName() != null, TenantCustType::getCustTypeName, tenantCustTypeQueryParam.getCustTypeName())
				;

		IPage<TenantCustType> tenantCustTypePage = tenantCustTypeService.page(pageTenantCustType, queryWrapperTenantCustType);

		Page<TenantCustTypeVo> tenantCustTypeVoPage = new Page<TenantCustTypeVo>(page, rows);
		tenantCustTypeVoPage.setCurrent(tenantCustTypePage.getCurrent());
		tenantCustTypeVoPage.setPages(tenantCustTypePage.getPages());
		tenantCustTypeVoPage.setSize(tenantCustTypePage.getSize());
		tenantCustTypeVoPage.setTotal(tenantCustTypePage.getTotal());
		tenantCustTypeVoPage.setRecords(tenantCustTypePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustTypeVoPage;
	}

	@ApiOperation(value = "新增")
	@RequestMapping(value = "/tenant-cust-types", method = RequestMethod.POST)
	public TenantCustTypeVo save(@RequestBody TenantCustType tenantCustType) {
		if (tenantCustType.getId() == null || tenantCustType.getId().compareTo(0L) <= 0) {
			tenantCustType.setId(idService.selectId());
		}
		boolean success = tenantCustTypeService.save(tenantCustType);
		if (success) {
			TenantCustType tenantCustTypeDatabase = tenantCustTypeService.getById(tenantCustType.getId());
			return entity2vo(tenantCustTypeDatabase);
		}
		log.info("save TenantCustType fail，{}", ToStringBuilder.reflectionToString(tenantCustType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新全部信息")
	@RequestMapping(value = "/tenant-cust-types/{id}", method = RequestMethod.PUT)
	public TenantCustTypeVo updateById(@PathVariable("id") Long id, @RequestBody TenantCustType tenantCustType) {
		tenantCustType.setId(id);
		boolean success = tenantCustTypeService.updateById(tenantCustType);
		if (success) {
			TenantCustType tenantCustTypeDatabase = tenantCustTypeService.getById(id);
			return entity2vo(tenantCustTypeDatabase);
		}
		log.info("update TenantCustType fail，{}", ToStringBuilder.reflectionToString(tenantCustType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新信息")
	@RequestMapping(value = "/tenant-cust-types/{id}", method = RequestMethod.PATCH)
	public TenantCustTypeVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantCustType tenantCustType) {
		UpdateWrapper<TenantCustType> updateWrapperTenantCustType = new UpdateWrapper<TenantCustType>();
		updateWrapperTenantCustType.lambda()//
				.eq(TenantCustType::getId, id)
				// .set(tenantCustType.getId() != null, TenantCustType::getId, tenantCustType.getId())
				.set(tenantCustType.getTenantId() != null, TenantCustType::getTenantId, tenantCustType.getTenantId())
				.set(tenantCustType.getCustTypeName() != null, TenantCustType::getCustTypeName, tenantCustType.getCustTypeName())
				;

		boolean success = tenantCustTypeService.update(updateWrapperTenantCustType);
		if (success) {
			TenantCustType tenantCustTypeDatabase = tenantCustTypeService.getById(id);
			return entity2vo(tenantCustTypeDatabase);
		}
		log.info("partial update TenantCustType fail，{}",
				ToStringBuilder.reflectionToString(tenantCustType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除")
	@RequestMapping(value = "/tenant-cust-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantCustTypeService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustTypeVo entity2vo(TenantCustType tenantCustType) {
		String jsonString = JSON.toJSONString(tenantCustType);
		TenantCustTypeVo tenantCustTypeVo = JSON.parseObject(jsonString, TenantCustTypeVo.class);
		if (StringUtils.isEmpty(tenantCustTypeVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantCustType.getTenantId());
			if (tenantInfo != null) {
				tenantCustTypeVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustTypeVo;
	}

}
