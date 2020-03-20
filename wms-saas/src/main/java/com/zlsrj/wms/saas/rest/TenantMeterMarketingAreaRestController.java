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
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;
import com.zlsrj.wms.api.vo.TenantMeterMarketingAreaVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterMarketingAreaService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "营销机构", tags = { "营销机构操作接口" })
@RestController
@Slf4j
public class TenantMeterMarketingAreaRestController {

	@Autowired
	private ITenantMeterMarketingAreaService tenantMeterMarketingAreaService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询营销机构")
	@RequestMapping(value = "/tenant-meter-marketing-areas/{id}", method = RequestMethod.GET)
	public TenantMeterMarketingAreaVo getById(@PathVariable("id") String id) {
		TenantMeterMarketingArea tenantMeterMarketingArea = tenantMeterMarketingAreaService.getById(id);

		return entity2vo(tenantMeterMarketingArea);
	}

	@ApiOperation(value = "根据参数查询营销机构列表")
	@RequestMapping(value = "/tenant-meter-marketing-areas/list", method = RequestMethod.GET)
	public List<TenantMeterMarketingAreaVo> list(@RequestBody TenantMeterMarketingAreaQueryParam tenantMeterMarketingAreaQueryParam) {
		QueryWrapper<TenantMeterMarketingArea> queryWrapperTenantMeterMarketingArea = new QueryWrapper<TenantMeterMarketingArea>();
		queryWrapperTenantMeterMarketingArea.lambda()
				.eq(tenantMeterMarketingAreaQueryParam.getId() != null, TenantMeterMarketingArea::getId, tenantMeterMarketingAreaQueryParam.getId())
				.eq(tenantMeterMarketingAreaQueryParam.getTenantId() != null, TenantMeterMarketingArea::getTenantId, tenantMeterMarketingAreaQueryParam.getTenantId())
				.eq(tenantMeterMarketingAreaQueryParam.getMarketingAreaType() != null, TenantMeterMarketingArea::getMarketingAreaType, tenantMeterMarketingAreaQueryParam.getMarketingAreaType())
				.eq(tenantMeterMarketingAreaQueryParam.getMarketingAreaName() != null, TenantMeterMarketingArea::getMarketingAreaName, tenantMeterMarketingAreaQueryParam.getMarketingAreaName())
				.eq(tenantMeterMarketingAreaQueryParam.getMarketingAreaParentId() != null, TenantMeterMarketingArea::getMarketingAreaParentId, tenantMeterMarketingAreaQueryParam.getMarketingAreaParentId())
				.eq(tenantMeterMarketingAreaQueryParam.getMarketingAreaData() != null, TenantMeterMarketingArea::getMarketingAreaData, tenantMeterMarketingAreaQueryParam.getMarketingAreaData())
				.eq(tenantMeterMarketingAreaQueryParam.getAddTime() != null, TenantMeterMarketingArea::getAddTime, tenantMeterMarketingAreaQueryParam.getAddTime())
				.ge(tenantMeterMarketingAreaQueryParam.getAddTimeStart() != null, TenantMeterMarketingArea::getAddTime,tenantMeterMarketingAreaQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterMarketingAreaQueryParam.getAddTimeStart()))
				.le(tenantMeterMarketingAreaQueryParam.getAddTimeEnd() != null, TenantMeterMarketingArea::getAddTime,tenantMeterMarketingAreaQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterMarketingAreaQueryParam.getAddTimeEnd()))
				.eq(tenantMeterMarketingAreaQueryParam.getUpdateTime() != null, TenantMeterMarketingArea::getUpdateTime, tenantMeterMarketingAreaQueryParam.getUpdateTime())
				.ge(tenantMeterMarketingAreaQueryParam.getUpdateTimeStart() != null, TenantMeterMarketingArea::getUpdateTime,tenantMeterMarketingAreaQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterMarketingAreaQueryParam.getUpdateTimeStart()))
				.le(tenantMeterMarketingAreaQueryParam.getUpdateTimeEnd() != null, TenantMeterMarketingArea::getUpdateTime,tenantMeterMarketingAreaQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterMarketingAreaQueryParam.getUpdateTimeEnd()))
				.eq(tenantMeterMarketingAreaQueryParam.getParentId()!=null,TenantMeterMarketingArea::getMarketingAreaParentId, tenantMeterMarketingAreaQueryParam.getParentId())
				;

		List<TenantMeterMarketingArea> tenantMeterMarketingAreaList = tenantMeterMarketingAreaService.list(queryWrapperTenantMeterMarketingArea);

		List<TenantMeterMarketingAreaVo> tenantMeterMarketingAreaVoList = TranslateUtil.translateList(tenantMeterMarketingAreaList, TenantMeterMarketingAreaVo.class);

		return tenantMeterMarketingAreaVoList;
	}
	
	@ApiOperation(value = "根据参数查询营销机构列表")
	@RequestMapping(value = "/tenant-meter-marketing-areas", method = RequestMethod.GET)
	public Page<TenantMeterMarketingAreaVo> page(@RequestBody TenantMeterMarketingAreaQueryParam tenantMeterMarketingAreaQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterMarketingArea> pageTenantMeterMarketingArea = new Page<TenantMeterMarketingArea>(page, rows);
		QueryWrapper<TenantMeterMarketingArea> queryWrapperTenantMeterMarketingArea = new QueryWrapper<TenantMeterMarketingArea>();
		queryWrapperTenantMeterMarketingArea.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterMarketingArea.lambda()
				.eq(tenantMeterMarketingAreaQueryParam.getId() != null, TenantMeterMarketingArea::getId, tenantMeterMarketingAreaQueryParam.getId())
				.eq(tenantMeterMarketingAreaQueryParam.getTenantId() != null, TenantMeterMarketingArea::getTenantId, tenantMeterMarketingAreaQueryParam.getTenantId())
				.eq(tenantMeterMarketingAreaQueryParam.getMarketingAreaType() != null, TenantMeterMarketingArea::getMarketingAreaType, tenantMeterMarketingAreaQueryParam.getMarketingAreaType())
				.eq(tenantMeterMarketingAreaQueryParam.getMarketingAreaName() != null, TenantMeterMarketingArea::getMarketingAreaName, tenantMeterMarketingAreaQueryParam.getMarketingAreaName())
				.eq(tenantMeterMarketingAreaQueryParam.getMarketingAreaParentId() != null, TenantMeterMarketingArea::getMarketingAreaParentId, tenantMeterMarketingAreaQueryParam.getMarketingAreaParentId())
				.eq(tenantMeterMarketingAreaQueryParam.getMarketingAreaData() != null, TenantMeterMarketingArea::getMarketingAreaData, tenantMeterMarketingAreaQueryParam.getMarketingAreaData())
				.eq(tenantMeterMarketingAreaQueryParam.getAddTime() != null, TenantMeterMarketingArea::getAddTime, tenantMeterMarketingAreaQueryParam.getAddTime())
				.ge(tenantMeterMarketingAreaQueryParam.getAddTimeStart() != null, TenantMeterMarketingArea::getAddTime,tenantMeterMarketingAreaQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterMarketingAreaQueryParam.getAddTimeStart()))
				.le(tenantMeterMarketingAreaQueryParam.getAddTimeEnd() != null, TenantMeterMarketingArea::getAddTime,tenantMeterMarketingAreaQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterMarketingAreaQueryParam.getAddTimeEnd()))
				.eq(tenantMeterMarketingAreaQueryParam.getUpdateTime() != null, TenantMeterMarketingArea::getUpdateTime, tenantMeterMarketingAreaQueryParam.getUpdateTime())
				.ge(tenantMeterMarketingAreaQueryParam.getUpdateTimeStart() != null, TenantMeterMarketingArea::getUpdateTime,tenantMeterMarketingAreaQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterMarketingAreaQueryParam.getUpdateTimeStart()))
				.le(tenantMeterMarketingAreaQueryParam.getUpdateTimeEnd() != null, TenantMeterMarketingArea::getUpdateTime,tenantMeterMarketingAreaQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterMarketingAreaQueryParam.getUpdateTimeEnd()))
				.eq(tenantMeterMarketingAreaQueryParam.getParentId()!=null,TenantMeterMarketingArea::getMarketingAreaParentId, tenantMeterMarketingAreaQueryParam.getParentId())
				.isNull(tenantMeterMarketingAreaQueryParam.getParentId()==null, TenantMeterMarketingArea::getMarketingAreaParentId)
				;

		IPage<TenantMeterMarketingArea> tenantMeterMarketingAreaPage = tenantMeterMarketingAreaService.page(pageTenantMeterMarketingArea, queryWrapperTenantMeterMarketingArea);

		Page<TenantMeterMarketingAreaVo> tenantMeterMarketingAreaVoPage = new Page<TenantMeterMarketingAreaVo>(page, rows);
		tenantMeterMarketingAreaVoPage.setCurrent(tenantMeterMarketingAreaPage.getCurrent());
		tenantMeterMarketingAreaVoPage.setPages(tenantMeterMarketingAreaPage.getPages());
		tenantMeterMarketingAreaVoPage.setSize(tenantMeterMarketingAreaPage.getSize());
		tenantMeterMarketingAreaVoPage.setTotal(tenantMeterMarketingAreaPage.getTotal());
		tenantMeterMarketingAreaVoPage.setRecords(tenantMeterMarketingAreaPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterMarketingAreaVoPage;
	}
	
	@ApiOperation(value = "新增营销机构")
	@RequestMapping(value = "/tenant-meter-marketing-areas", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterMarketingAreaAddParam tenantMeterMarketingAreaAddParam) {
		return tenantMeterMarketingAreaService.save(tenantMeterMarketingAreaAddParam);
	}

	@ApiOperation(value = "更新营销机构全部信息")
	@RequestMapping(value = "/tenant-meter-marketing-areas/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterMarketingAreaUpdateParam tenantMeterMarketingAreaUpdateParam) {
		tenantMeterMarketingAreaUpdateParam.setId(id);
		return tenantMeterMarketingAreaService.updateById(tenantMeterMarketingAreaUpdateParam);
	}

	@ApiOperation(value = "根据参数更新营销机构信息")
	@RequestMapping(value = "/tenant-meter-marketing-areas/{id}", method = RequestMethod.PATCH)
	public TenantMeterMarketingAreaVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterMarketingArea tenantMeterMarketingArea) {
        TenantMeterMarketingArea tenantMeterMarketingAreaWhere = TenantMeterMarketingArea.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterMarketingArea> updateWrapperTenantMeterMarketingArea = new UpdateWrapper<TenantMeterMarketingArea>();
		updateWrapperTenantMeterMarketingArea.setEntity(tenantMeterMarketingAreaWhere);
		updateWrapperTenantMeterMarketingArea.lambda()//
				//.eq(TenantMeterMarketingArea::getId, id)
				// .set(tenantMeterMarketingArea.getId() != null, TenantMeterMarketingArea::getId, tenantMeterMarketingArea.getId())
				.set(tenantMeterMarketingArea.getTenantId() != null, TenantMeterMarketingArea::getTenantId, tenantMeterMarketingArea.getTenantId())
				.set(tenantMeterMarketingArea.getMarketingAreaType() != null, TenantMeterMarketingArea::getMarketingAreaType, tenantMeterMarketingArea.getMarketingAreaType())
				.set(tenantMeterMarketingArea.getMarketingAreaName() != null, TenantMeterMarketingArea::getMarketingAreaName, tenantMeterMarketingArea.getMarketingAreaName())
				.set(tenantMeterMarketingArea.getMarketingAreaParentId() != null, TenantMeterMarketingArea::getMarketingAreaParentId, tenantMeterMarketingArea.getMarketingAreaParentId())
				.set(tenantMeterMarketingArea.getMarketingAreaData() != null, TenantMeterMarketingArea::getMarketingAreaData, tenantMeterMarketingArea.getMarketingAreaData())
				.set(tenantMeterMarketingArea.getAddTime() != null, TenantMeterMarketingArea::getAddTime, tenantMeterMarketingArea.getAddTime())
				.set(tenantMeterMarketingArea.getUpdateTime() != null, TenantMeterMarketingArea::getUpdateTime, tenantMeterMarketingArea.getUpdateTime())
				;

		boolean success = tenantMeterMarketingAreaService.update(updateWrapperTenantMeterMarketingArea);
		if (success) {
			TenantMeterMarketingArea tenantMeterMarketingAreaDatabase = tenantMeterMarketingAreaService.getById(id);
			return entity2vo(tenantMeterMarketingAreaDatabase);
		}
		log.info("partial update TenantMeterMarketingArea fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterMarketingArea, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除营销机构")
	@RequestMapping(value = "/tenant-meter-marketing-areas/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterMarketingAreaService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterMarketingAreaVo entity2vo(TenantMeterMarketingArea tenantMeterMarketingArea) {
		if (tenantMeterMarketingArea == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterMarketingArea);
		TenantMeterMarketingAreaVo tenantMeterMarketingAreaVo = JSON.parseObject(jsonString, TenantMeterMarketingAreaVo.class);
		if (StringUtils.isEmpty(tenantMeterMarketingAreaVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantMeterMarketingArea.getTenantId());
			if (tenantInfo != null) {
				tenantMeterMarketingAreaVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterMarketingAreaVo;
	}

}
