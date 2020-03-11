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
import com.zlsrj.wms.api.dto.TenantCaliberQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCaliber;
import com.zlsrj.wms.api.vo.TenantCaliberVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCaliberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表口径", tags = { "水表口径操作接口" })
@RestController
@Slf4j
public class TenantCaliberRestController {

	@Autowired
	private ITenantCaliberService tenantCaliberService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询水表口径")
	@RequestMapping(value = "/tenant-calibers/{id}", method = RequestMethod.GET)
	public TenantCaliberVo getById(@PathVariable("id") String id) {
		TenantCaliber tenantCaliber = tenantCaliberService.getById(id);

		return entity2vo(tenantCaliber);
	}

	@ApiOperation(value = "根据参数查询水表口径列表")
	@RequestMapping(value = "/tenant-calibers", method = RequestMethod.GET)
	public Page<TenantCaliberVo> page(@RequestBody TenantCaliberQueryParam tenantCaliberQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCaliber> pageTenantCaliber = new Page<TenantCaliber>(page, rows);
		QueryWrapper<TenantCaliber> queryWrapperTenantCaliber = new QueryWrapper<TenantCaliber>();
		queryWrapperTenantCaliber.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantCaliber.lambda()
				.eq(tenantCaliberQueryParam.getId() != null, TenantCaliber::getId, tenantCaliberQueryParam.getId())
				.eq(tenantCaliberQueryParam.getTenantId() != null, TenantCaliber::getTenantId, tenantCaliberQueryParam.getTenantId())
				.eq(tenantCaliberQueryParam.getCaliberName() != null, TenantCaliber::getCaliberName, tenantCaliberQueryParam.getCaliberName())
				;

		IPage<TenantCaliber> tenantCaliberPage = tenantCaliberService.page(pageTenantCaliber, queryWrapperTenantCaliber);

		Page<TenantCaliberVo> tenantCaliberVoPage = new Page<TenantCaliberVo>(page, rows);
		tenantCaliberVoPage.setCurrent(tenantCaliberPage.getCurrent());
		tenantCaliberVoPage.setPages(tenantCaliberPage.getPages());
		tenantCaliberVoPage.setSize(tenantCaliberPage.getSize());
		tenantCaliberVoPage.setTotal(tenantCaliberPage.getTotal());
		tenantCaliberVoPage.setRecords(tenantCaliberPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCaliberVoPage;
	}

	@ApiOperation(value = "新增水表口径")
	@RequestMapping(value = "/tenant-calibers", method = RequestMethod.POST)
	public TenantCaliberVo save(@RequestBody TenantCaliber tenantCaliber) {
		if (tenantCaliber.getId() == null || tenantCaliber.getId().trim().length() <= 0) {
			tenantCaliber.setId(idService.selectId());
		}
		boolean success = tenantCaliberService.save(tenantCaliber);
		if (success) {
			TenantCaliber tenantCaliberDatabase = tenantCaliberService.getById(tenantCaliber.getId());
			return entity2vo(tenantCaliberDatabase);
		}
		log.info("save TenantCaliber fail，{}", ToStringBuilder.reflectionToString(tenantCaliber, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新水表口径全部信息")
	@RequestMapping(value = "/tenant-calibers/{id}", method = RequestMethod.PUT)
	public TenantCaliberVo updateById(@PathVariable("id") String id, @RequestBody TenantCaliber tenantCaliber) {
		tenantCaliber.setId(id);
		boolean success = tenantCaliberService.updateById(tenantCaliber);
		if (success) {
			TenantCaliber tenantCaliberDatabase = tenantCaliberService.getById(id);
			return entity2vo(tenantCaliberDatabase);
		}
		log.info("update TenantCaliber fail，{}", ToStringBuilder.reflectionToString(tenantCaliber, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新水表口径信息")
	@RequestMapping(value = "/tenant-calibers/{id}", method = RequestMethod.PATCH)
	public TenantCaliberVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantCaliber tenantCaliber) {
        TenantCaliber tenantCaliberWhere = TenantCaliber.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantCaliber> updateWrapperTenantCaliber = new UpdateWrapper<TenantCaliber>();
		updateWrapperTenantCaliber.setEntity(tenantCaliberWhere);
		updateWrapperTenantCaliber.lambda()//
				//.eq(TenantCaliber::getId, id)
				// .set(tenantCaliber.getId() != null, TenantCaliber::getId, tenantCaliber.getId())
				.set(tenantCaliber.getTenantId() != null, TenantCaliber::getTenantId, tenantCaliber.getTenantId())
				.set(tenantCaliber.getCaliberName() != null, TenantCaliber::getCaliberName, tenantCaliber.getCaliberName())
				;

		boolean success = tenantCaliberService.update(updateWrapperTenantCaliber);
		if (success) {
			TenantCaliber tenantCaliberDatabase = tenantCaliberService.getById(id);
			return entity2vo(tenantCaliberDatabase);
		}
		log.info("partial update TenantCaliber fail，{}",
				ToStringBuilder.reflectionToString(tenantCaliber, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水表口径")
	@RequestMapping(value = "/tenant-calibers/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantCaliberService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCaliberVo entity2vo(TenantCaliber tenantCaliber) {
		if (tenantCaliber == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantCaliber);
		TenantCaliberVo tenantCaliberVo = JSON.parseObject(jsonString, TenantCaliberVo.class);
		if (StringUtils.isEmpty(tenantCaliberVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantCaliber.getTenantId());
			if (tenantInfo != null) {
				tenantCaliberVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCaliberVo;
	}

}
