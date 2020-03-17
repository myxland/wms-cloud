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
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterSupplyArea;
import com.zlsrj.wms.api.vo.TenantMeterSupplyAreaVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterSupplyAreaService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "供水区域", tags = { "供水区域操作接口" })
@RestController
@Slf4j
public class TenantMeterSupplyAreaRestController {

	@Autowired
	private ITenantMeterSupplyAreaService tenantMeterSupplyAreaService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询供水区域")
	@RequestMapping(value = "/tenant-meter-supply-areas/{id}", method = RequestMethod.GET)
	public TenantMeterSupplyAreaVo getById(@PathVariable("id") String id) {
		TenantMeterSupplyArea tenantMeterSupplyArea = tenantMeterSupplyAreaService.getById(id);

		return entity2vo(tenantMeterSupplyArea);
	}

	@ApiOperation(value = "根据参数查询供水区域列表")
	@RequestMapping(value = "/tenant-meter-supply-areas/list", method = RequestMethod.GET)
	public List<TenantMeterSupplyAreaVo> list(@RequestBody TenantMeterSupplyAreaQueryParam tenantMeterSupplyAreaQueryParam) {
		QueryWrapper<TenantMeterSupplyArea> queryWrapperTenantMeterSupplyArea = new QueryWrapper<TenantMeterSupplyArea>();
		queryWrapperTenantMeterSupplyArea.lambda()
				.eq(tenantMeterSupplyAreaQueryParam.getId() != null, TenantMeterSupplyArea::getId, tenantMeterSupplyAreaQueryParam.getId())
				.eq(tenantMeterSupplyAreaQueryParam.getTenantId() != null, TenantMeterSupplyArea::getTenantId, tenantMeterSupplyAreaQueryParam.getTenantId())
				.eq(tenantMeterSupplyAreaQueryParam.getSupplyAreaName() != null, TenantMeterSupplyArea::getSupplyAreaName, tenantMeterSupplyAreaQueryParam.getSupplyAreaName())
				.eq(tenantMeterSupplyAreaQueryParam.getSupplyAreaParentId() != null, TenantMeterSupplyArea::getSupplyAreaParentId, tenantMeterSupplyAreaQueryParam.getSupplyAreaParentId())
				.eq(tenantMeterSupplyAreaQueryParam.getSupplyAreaData() != null, TenantMeterSupplyArea::getSupplyAreaData, tenantMeterSupplyAreaQueryParam.getSupplyAreaData())
				.eq(tenantMeterSupplyAreaQueryParam.getAddTime() != null, TenantMeterSupplyArea::getAddTime, tenantMeterSupplyAreaQueryParam.getAddTime())
				.ge(tenantMeterSupplyAreaQueryParam.getAddTimeStart() != null, TenantMeterSupplyArea::getAddTime,tenantMeterSupplyAreaQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterSupplyAreaQueryParam.getAddTimeStart()))
				.le(tenantMeterSupplyAreaQueryParam.getAddTimeEnd() != null, TenantMeterSupplyArea::getAddTime,tenantMeterSupplyAreaQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterSupplyAreaQueryParam.getAddTimeEnd()))
				.eq(tenantMeterSupplyAreaQueryParam.getUpdateTime() != null, TenantMeterSupplyArea::getUpdateTime, tenantMeterSupplyAreaQueryParam.getUpdateTime())
				.ge(tenantMeterSupplyAreaQueryParam.getUpdateTimeStart() != null, TenantMeterSupplyArea::getUpdateTime,tenantMeterSupplyAreaQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterSupplyAreaQueryParam.getUpdateTimeStart()))
				.le(tenantMeterSupplyAreaQueryParam.getUpdateTimeEnd() != null, TenantMeterSupplyArea::getUpdateTime,tenantMeterSupplyAreaQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterSupplyAreaQueryParam.getUpdateTimeEnd()))
				.eq(tenantMeterSupplyAreaQueryParam.getParentId()!=null,TenantMeterSupplyArea::getSupplyAreaParentId, tenantMeterSupplyAreaQueryParam.getParentId())
				;

		List<TenantMeterSupplyArea> tenantMeterSupplyAreaList = tenantMeterSupplyAreaService.list(queryWrapperTenantMeterSupplyArea);

		List<TenantMeterSupplyAreaVo> tenantMeterSupplyAreaVoList = TranslateUtil.translateList(tenantMeterSupplyAreaList, TenantMeterSupplyAreaVo.class);

		return tenantMeterSupplyAreaVoList;
	}
	
	@ApiOperation(value = "根据参数查询供水区域列表")
	@RequestMapping(value = "/tenant-meter-supply-areas", method = RequestMethod.GET)
	public Page<TenantMeterSupplyAreaVo> page(@RequestBody TenantMeterSupplyAreaQueryParam tenantMeterSupplyAreaQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterSupplyArea> pageTenantMeterSupplyArea = new Page<TenantMeterSupplyArea>(page, rows);
		QueryWrapper<TenantMeterSupplyArea> queryWrapperTenantMeterSupplyArea = new QueryWrapper<TenantMeterSupplyArea>();
		queryWrapperTenantMeterSupplyArea.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterSupplyArea.lambda()
				.eq(tenantMeterSupplyAreaQueryParam.getId() != null, TenantMeterSupplyArea::getId, tenantMeterSupplyAreaQueryParam.getId())
				.eq(tenantMeterSupplyAreaQueryParam.getTenantId() != null, TenantMeterSupplyArea::getTenantId, tenantMeterSupplyAreaQueryParam.getTenantId())
				.eq(tenantMeterSupplyAreaQueryParam.getSupplyAreaName() != null, TenantMeterSupplyArea::getSupplyAreaName, tenantMeterSupplyAreaQueryParam.getSupplyAreaName())
				.eq(tenantMeterSupplyAreaQueryParam.getSupplyAreaParentId() != null, TenantMeterSupplyArea::getSupplyAreaParentId, tenantMeterSupplyAreaQueryParam.getSupplyAreaParentId())
				.eq(tenantMeterSupplyAreaQueryParam.getSupplyAreaData() != null, TenantMeterSupplyArea::getSupplyAreaData, tenantMeterSupplyAreaQueryParam.getSupplyAreaData())
				.eq(tenantMeterSupplyAreaQueryParam.getAddTime() != null, TenantMeterSupplyArea::getAddTime, tenantMeterSupplyAreaQueryParam.getAddTime())
				.ge(tenantMeterSupplyAreaQueryParam.getAddTimeStart() != null, TenantMeterSupplyArea::getAddTime,tenantMeterSupplyAreaQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterSupplyAreaQueryParam.getAddTimeStart()))
				.le(tenantMeterSupplyAreaQueryParam.getAddTimeEnd() != null, TenantMeterSupplyArea::getAddTime,tenantMeterSupplyAreaQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterSupplyAreaQueryParam.getAddTimeEnd()))
				.eq(tenantMeterSupplyAreaQueryParam.getUpdateTime() != null, TenantMeterSupplyArea::getUpdateTime, tenantMeterSupplyAreaQueryParam.getUpdateTime())
				.ge(tenantMeterSupplyAreaQueryParam.getUpdateTimeStart() != null, TenantMeterSupplyArea::getUpdateTime,tenantMeterSupplyAreaQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterSupplyAreaQueryParam.getUpdateTimeStart()))
				.le(tenantMeterSupplyAreaQueryParam.getUpdateTimeEnd() != null, TenantMeterSupplyArea::getUpdateTime,tenantMeterSupplyAreaQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterSupplyAreaQueryParam.getUpdateTimeEnd()))
				.eq(tenantMeterSupplyAreaQueryParam.getParentId()!=null,TenantMeterSupplyArea::getSupplyAreaParentId, tenantMeterSupplyAreaQueryParam.getParentId())
				.isNull(tenantMeterSupplyAreaQueryParam.getParentId()==null, TenantMeterSupplyArea::getSupplyAreaParentId)
				;

		IPage<TenantMeterSupplyArea> tenantMeterSupplyAreaPage = tenantMeterSupplyAreaService.page(pageTenantMeterSupplyArea, queryWrapperTenantMeterSupplyArea);

		Page<TenantMeterSupplyAreaVo> tenantMeterSupplyAreaVoPage = new Page<TenantMeterSupplyAreaVo>(page, rows);
		tenantMeterSupplyAreaVoPage.setCurrent(tenantMeterSupplyAreaPage.getCurrent());
		tenantMeterSupplyAreaVoPage.setPages(tenantMeterSupplyAreaPage.getPages());
		tenantMeterSupplyAreaVoPage.setSize(tenantMeterSupplyAreaPage.getSize());
		tenantMeterSupplyAreaVoPage.setTotal(tenantMeterSupplyAreaPage.getTotal());
		tenantMeterSupplyAreaVoPage.setRecords(tenantMeterSupplyAreaPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterSupplyAreaVoPage;
	}
	
	@ApiOperation(value = "新增供水区域")
	@RequestMapping(value = "/tenant-meter-supply-areas", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterSupplyAreaAddParam tenantMeterSupplyAreaAddParam) {
		return tenantMeterSupplyAreaService.save(tenantMeterSupplyAreaAddParam);
	}

	@ApiOperation(value = "更新供水区域全部信息")
	@RequestMapping(value = "/tenant-meter-supply-areas/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterSupplyAreaUpdateParam tenantMeterSupplyAreaUpdateParam) {
		tenantMeterSupplyAreaUpdateParam.setId(id);
		return tenantMeterSupplyAreaService.updateById(tenantMeterSupplyAreaUpdateParam);
	}

	@ApiOperation(value = "根据参数更新供水区域信息")
	@RequestMapping(value = "/tenant-meter-supply-areas/{id}", method = RequestMethod.PATCH)
	public TenantMeterSupplyAreaVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterSupplyArea tenantMeterSupplyArea) {
        TenantMeterSupplyArea tenantMeterSupplyAreaWhere = TenantMeterSupplyArea.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterSupplyArea> updateWrapperTenantMeterSupplyArea = new UpdateWrapper<TenantMeterSupplyArea>();
		updateWrapperTenantMeterSupplyArea.setEntity(tenantMeterSupplyAreaWhere);
		updateWrapperTenantMeterSupplyArea.lambda()//
				//.eq(TenantMeterSupplyArea::getId, id)
				// .set(tenantMeterSupplyArea.getId() != null, TenantMeterSupplyArea::getId, tenantMeterSupplyArea.getId())
				.set(tenantMeterSupplyArea.getTenantId() != null, TenantMeterSupplyArea::getTenantId, tenantMeterSupplyArea.getTenantId())
				.set(tenantMeterSupplyArea.getSupplyAreaName() != null, TenantMeterSupplyArea::getSupplyAreaName, tenantMeterSupplyArea.getSupplyAreaName())
				.set(tenantMeterSupplyArea.getSupplyAreaParentId() != null, TenantMeterSupplyArea::getSupplyAreaParentId, tenantMeterSupplyArea.getSupplyAreaParentId())
				.set(tenantMeterSupplyArea.getSupplyAreaData() != null, TenantMeterSupplyArea::getSupplyAreaData, tenantMeterSupplyArea.getSupplyAreaData())
				.set(tenantMeterSupplyArea.getAddTime() != null, TenantMeterSupplyArea::getAddTime, tenantMeterSupplyArea.getAddTime())
				.set(tenantMeterSupplyArea.getUpdateTime() != null, TenantMeterSupplyArea::getUpdateTime, tenantMeterSupplyArea.getUpdateTime())
				;

		boolean success = tenantMeterSupplyAreaService.update(updateWrapperTenantMeterSupplyArea);
		if (success) {
			TenantMeterSupplyArea tenantMeterSupplyAreaDatabase = tenantMeterSupplyAreaService.getById(id);
			return entity2vo(tenantMeterSupplyAreaDatabase);
		}
		log.info("partial update TenantMeterSupplyArea fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterSupplyArea, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除供水区域")
	@RequestMapping(value = "/tenant-meter-supply-areas/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterSupplyAreaService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterSupplyAreaVo entity2vo(TenantMeterSupplyArea tenantMeterSupplyArea) {
		if (tenantMeterSupplyArea == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterSupplyArea);
		TenantMeterSupplyAreaVo tenantMeterSupplyAreaVo = JSON.parseObject(jsonString, TenantMeterSupplyAreaVo.class);
		if (StringUtils.isEmpty(tenantMeterSupplyAreaVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantMeterSupplyArea.getTenantId());
			if (tenantInfo != null) {
				tenantMeterSupplyAreaVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterSupplyAreaVo;
	}

}
