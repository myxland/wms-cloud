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
import com.zlsrj.wms.api.dto.TenantManufactorQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantManufactor;
import com.zlsrj.wms.api.vo.TenantManufactorVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantManufactorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表厂商", tags = { "水表厂商操作接口" })
@RestController
@Slf4j
public class TenantManufactorRestController {

	@Autowired
	private ITenantManufactorService tenantManufactorService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询水表厂商")
	@RequestMapping(value = "/tenant-manufactors/{id}", method = RequestMethod.GET)
	public TenantManufactorVo getById(@PathVariable("id") String id) {
		TenantManufactor tenantManufactor = tenantManufactorService.getById(id);

		return entity2vo(tenantManufactor);
	}

	@ApiOperation(value = "根据参数查询水表厂商列表")
	@RequestMapping(value = "/tenant-manufactors", method = RequestMethod.GET)
	public Page<TenantManufactorVo> page(@RequestBody TenantManufactorQueryParam tenantManufactorQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantManufactor> pageTenantManufactor = new Page<TenantManufactor>(page, rows);
		QueryWrapper<TenantManufactor> queryWrapperTenantManufactor = new QueryWrapper<TenantManufactor>();
		queryWrapperTenantManufactor.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantManufactor.lambda()
				.eq(tenantManufactorQueryParam.getId() != null, TenantManufactor::getId, tenantManufactorQueryParam.getId())
				.eq(tenantManufactorQueryParam.getTenantId() != null, TenantManufactor::getTenantId, tenantManufactorQueryParam.getTenantId())
				.eq(tenantManufactorQueryParam.getManufactorName() != null, TenantManufactor::getManufactorName, tenantManufactorQueryParam.getManufactorName())
				.eq(tenantManufactorQueryParam.getManufactorApikey() != null, TenantManufactor::getManufactorApikey, tenantManufactorQueryParam.getManufactorApikey())
				;

		IPage<TenantManufactor> tenantManufactorPage = tenantManufactorService.page(pageTenantManufactor, queryWrapperTenantManufactor);

		Page<TenantManufactorVo> tenantManufactorVoPage = new Page<TenantManufactorVo>(page, rows);
		tenantManufactorVoPage.setCurrent(tenantManufactorPage.getCurrent());
		tenantManufactorVoPage.setPages(tenantManufactorPage.getPages());
		tenantManufactorVoPage.setSize(tenantManufactorPage.getSize());
		tenantManufactorVoPage.setTotal(tenantManufactorPage.getTotal());
		tenantManufactorVoPage.setRecords(tenantManufactorPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantManufactorVoPage;
	}

	@ApiOperation(value = "新增水表厂商")
	@RequestMapping(value = "/tenant-manufactors", method = RequestMethod.POST)
	public TenantManufactorVo save(@RequestBody TenantManufactor tenantManufactor) {
		if (tenantManufactor.getId() == null || tenantManufactor.getId().trim().length() <= 0) {
			tenantManufactor.setId(idService.selectId());
		}
		boolean success = tenantManufactorService.save(tenantManufactor);
		if (success) {
			TenantManufactor tenantManufactorDatabase = tenantManufactorService.getById(tenantManufactor.getId());
			return entity2vo(tenantManufactorDatabase);
		}
		log.info("save TenantManufactor fail，{}", ToStringBuilder.reflectionToString(tenantManufactor, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新水表厂商全部信息")
	@RequestMapping(value = "/tenant-manufactors/{id}", method = RequestMethod.PUT)
	public TenantManufactorVo updateById(@PathVariable("id") String id, @RequestBody TenantManufactor tenantManufactor) {
		tenantManufactor.setId(id);
		boolean success = tenantManufactorService.updateById(tenantManufactor);
		if (success) {
			TenantManufactor tenantManufactorDatabase = tenantManufactorService.getById(id);
			return entity2vo(tenantManufactorDatabase);
		}
		log.info("update TenantManufactor fail，{}", ToStringBuilder.reflectionToString(tenantManufactor, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新水表厂商信息")
	@RequestMapping(value = "/tenant-manufactors/{id}", method = RequestMethod.PATCH)
	public TenantManufactorVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantManufactor tenantManufactor) {
        TenantManufactor tenantManufactorWhere = TenantManufactor.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantManufactor> updateWrapperTenantManufactor = new UpdateWrapper<TenantManufactor>();
		updateWrapperTenantManufactor.setEntity(tenantManufactorWhere);
		updateWrapperTenantManufactor.lambda()//
				//.eq(TenantManufactor::getId, id)
				// .set(tenantManufactor.getId() != null, TenantManufactor::getId, tenantManufactor.getId())
				.set(tenantManufactor.getTenantId() != null, TenantManufactor::getTenantId, tenantManufactor.getTenantId())
				.set(tenantManufactor.getManufactorName() != null, TenantManufactor::getManufactorName, tenantManufactor.getManufactorName())
				.set(tenantManufactor.getManufactorApikey() != null, TenantManufactor::getManufactorApikey, tenantManufactor.getManufactorApikey())
				;

		boolean success = tenantManufactorService.update(updateWrapperTenantManufactor);
		if (success) {
			TenantManufactor tenantManufactorDatabase = tenantManufactorService.getById(id);
			return entity2vo(tenantManufactorDatabase);
		}
		log.info("partial update TenantManufactor fail，{}",
				ToStringBuilder.reflectionToString(tenantManufactor, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水表厂商")
	@RequestMapping(value = "/tenant-manufactors/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantManufactorService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantManufactorVo entity2vo(TenantManufactor tenantManufactor) {
		if (tenantManufactor == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantManufactor);
		TenantManufactorVo tenantManufactorVo = JSON.parseObject(jsonString, TenantManufactorVo.class);
		if (StringUtils.isEmpty(tenantManufactorVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantManufactor.getTenantId());
			if (tenantInfo != null) {
				tenantManufactorVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantManufactorVo;
	}

}
