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
import com.zlsrj.wms.api.dto.TenantWaterAreaQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantWaterArea;
import com.zlsrj.wms.api.vo.TenantWaterAreaVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantWaterAreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "供水区域", tags = { "供水区域操作接口" })
@RestController
@Slf4j
public class TenantWaterAreaRestController {

	@Autowired
	private ITenantWaterAreaService tenantWaterAreaService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询供水区域")
	@RequestMapping(value = "/tenant-water-areas/{id}", method = RequestMethod.GET)
	public TenantWaterAreaVo getById(@PathVariable("id") String id) {
		TenantWaterArea tenantWaterArea = tenantWaterAreaService.getById(id);

		return entity2vo(tenantWaterArea);
	}

	@ApiOperation(value = "根据参数查询供水区域列表")
	@RequestMapping(value = "/tenant-water-areas", method = RequestMethod.GET)
	public Page<TenantWaterAreaVo> page(@RequestBody TenantWaterAreaQueryParam tenantWaterAreaQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantWaterArea> pageTenantWaterArea = new Page<TenantWaterArea>(page, rows);
		QueryWrapper<TenantWaterArea> queryWrapperTenantWaterArea = new QueryWrapper<TenantWaterArea>();
		queryWrapperTenantWaterArea.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantWaterArea.lambda()
				.eq(tenantWaterAreaQueryParam.getId() != null, TenantWaterArea::getId, tenantWaterAreaQueryParam.getId())
				.eq(tenantWaterAreaQueryParam.getTenantId() != null, TenantWaterArea::getTenantId, tenantWaterAreaQueryParam.getTenantId())
				.eq(tenantWaterAreaQueryParam.getWaterAreaName() != null, TenantWaterArea::getWaterAreaName, tenantWaterAreaQueryParam.getWaterAreaName())
				.eq(tenantWaterAreaQueryParam.getWaterAreaParentId() != null, TenantWaterArea::getWaterAreaParentId, tenantWaterAreaQueryParam.getWaterAreaParentId())
				.eq(tenantWaterAreaQueryParam.getParentId()!=null,TenantWaterArea::getWaterAreaParentId, tenantWaterAreaQueryParam.getParentId())
				.isNull(tenantWaterAreaQueryParam.getParentId()==null, TenantWaterArea::getWaterAreaParentId)
				;

		IPage<TenantWaterArea> tenantWaterAreaPage = tenantWaterAreaService.page(pageTenantWaterArea, queryWrapperTenantWaterArea);

		Page<TenantWaterAreaVo> tenantWaterAreaVoPage = new Page<TenantWaterAreaVo>(page, rows);
		tenantWaterAreaVoPage.setCurrent(tenantWaterAreaPage.getCurrent());
		tenantWaterAreaVoPage.setPages(tenantWaterAreaPage.getPages());
		tenantWaterAreaVoPage.setSize(tenantWaterAreaPage.getSize());
		tenantWaterAreaVoPage.setTotal(tenantWaterAreaPage.getTotal());
		tenantWaterAreaVoPage.setRecords(tenantWaterAreaPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantWaterAreaVoPage;
	}

	@ApiOperation(value = "新增供水区域")
	@RequestMapping(value = "/tenant-water-areas", method = RequestMethod.POST)
	public TenantWaterAreaVo save(@RequestBody TenantWaterArea tenantWaterArea) {
		if (tenantWaterArea.getId() == null || tenantWaterArea.getId().trim().length() <= 0) {
			tenantWaterArea.setId(idService.selectId());
		}
		boolean success = tenantWaterAreaService.save(tenantWaterArea);
		if (success) {
			TenantWaterArea tenantWaterAreaDatabase = tenantWaterAreaService.getById(tenantWaterArea.getId());
			return entity2vo(tenantWaterAreaDatabase);
		}
		log.info("save TenantWaterArea fail，{}", ToStringBuilder.reflectionToString(tenantWaterArea, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新供水区域全部信息")
	@RequestMapping(value = "/tenant-water-areas/{id}", method = RequestMethod.PUT)
	public TenantWaterAreaVo updateById(@PathVariable("id") String id, @RequestBody TenantWaterArea tenantWaterArea) {
		tenantWaterArea.setId(id);
		boolean success = tenantWaterAreaService.updateById(tenantWaterArea);
		if (success) {
			TenantWaterArea tenantWaterAreaDatabase = tenantWaterAreaService.getById(id);
			return entity2vo(tenantWaterAreaDatabase);
		}
		log.info("update TenantWaterArea fail，{}", ToStringBuilder.reflectionToString(tenantWaterArea, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新供水区域信息")
	@RequestMapping(value = "/tenant-water-areas/{id}", method = RequestMethod.PATCH)
	public TenantWaterAreaVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantWaterArea tenantWaterArea) {
        TenantWaterArea tenantWaterAreaWhere = TenantWaterArea.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantWaterArea> updateWrapperTenantWaterArea = new UpdateWrapper<TenantWaterArea>();
		updateWrapperTenantWaterArea.setEntity(tenantWaterAreaWhere);
		updateWrapperTenantWaterArea.lambda()//
				//.eq(TenantWaterArea::getId, id)
				// .set(tenantWaterArea.getId() != null, TenantWaterArea::getId, tenantWaterArea.getId())
				.set(tenantWaterArea.getTenantId() != null, TenantWaterArea::getTenantId, tenantWaterArea.getTenantId())
				.set(tenantWaterArea.getWaterAreaName() != null, TenantWaterArea::getWaterAreaName, tenantWaterArea.getWaterAreaName())
				.set(tenantWaterArea.getWaterAreaParentId() != null, TenantWaterArea::getWaterAreaParentId, tenantWaterArea.getWaterAreaParentId())
				;

		boolean success = tenantWaterAreaService.update(updateWrapperTenantWaterArea);
		if (success) {
			TenantWaterArea tenantWaterAreaDatabase = tenantWaterAreaService.getById(id);
			return entity2vo(tenantWaterAreaDatabase);
		}
		log.info("partial update TenantWaterArea fail，{}",
				ToStringBuilder.reflectionToString(tenantWaterArea, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除供水区域")
	@RequestMapping(value = "/tenant-water-areas/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantWaterAreaService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantWaterAreaVo entity2vo(TenantWaterArea tenantWaterArea) {
		if (tenantWaterArea == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantWaterArea);
		TenantWaterAreaVo tenantWaterAreaVo = JSON.parseObject(jsonString, TenantWaterAreaVo.class);
		if (StringUtils.isEmpty(tenantWaterAreaVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantWaterArea.getTenantId());
			if (tenantInfo != null) {
				tenantWaterAreaVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantWaterAreaVo;
	}

}
