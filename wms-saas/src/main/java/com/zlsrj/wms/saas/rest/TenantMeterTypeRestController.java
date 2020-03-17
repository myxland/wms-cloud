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
import com.zlsrj.wms.api.dto.TenantMeterTypeAddParam;
import com.zlsrj.wms.api.dto.TenantMeterTypeQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterTypeUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterType;
import com.zlsrj.wms.api.vo.TenantMeterTypeVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterTypeService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表类型", tags = { "水表类型操作接口" })
@RestController
@Slf4j
public class TenantMeterTypeRestController {

	@Autowired
	private ITenantMeterTypeService tenantMeterTypeService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询水表类型")
	@RequestMapping(value = "/tenant-meter-types/{id}", method = RequestMethod.GET)
	public TenantMeterTypeVo getById(@PathVariable("id") String id) {
		TenantMeterType tenantMeterType = tenantMeterTypeService.getById(id);

		return entity2vo(tenantMeterType);
	}

	@ApiOperation(value = "根据参数查询水表类型列表")
	@RequestMapping(value = "/tenant-meter-types/list", method = RequestMethod.GET)
	public List<TenantMeterTypeVo> list(@RequestBody TenantMeterTypeQueryParam tenantMeterTypeQueryParam) {
		QueryWrapper<TenantMeterType> queryWrapperTenantMeterType = new QueryWrapper<TenantMeterType>();
		queryWrapperTenantMeterType.lambda()
				.eq(tenantMeterTypeQueryParam.getId() != null, TenantMeterType::getId, tenantMeterTypeQueryParam.getId())
				.eq(tenantMeterTypeQueryParam.getTenantId() != null, TenantMeterType::getTenantId, tenantMeterTypeQueryParam.getTenantId())
				.eq(tenantMeterTypeQueryParam.getMeterTypeName() != null, TenantMeterType::getMeterTypeName, tenantMeterTypeQueryParam.getMeterTypeName())
				.eq(tenantMeterTypeQueryParam.getMeterTypeData() != null, TenantMeterType::getMeterTypeData, tenantMeterTypeQueryParam.getMeterTypeData())
				.eq(tenantMeterTypeQueryParam.getAddTime() != null, TenantMeterType::getAddTime, tenantMeterTypeQueryParam.getAddTime())
				.ge(tenantMeterTypeQueryParam.getAddTimeStart() != null, TenantMeterType::getAddTime,tenantMeterTypeQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterTypeQueryParam.getAddTimeStart()))
				.le(tenantMeterTypeQueryParam.getAddTimeEnd() != null, TenantMeterType::getAddTime,tenantMeterTypeQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterTypeQueryParam.getAddTimeEnd()))
				.eq(tenantMeterTypeQueryParam.getUpdateTime() != null, TenantMeterType::getUpdateTime, tenantMeterTypeQueryParam.getUpdateTime())
				.ge(tenantMeterTypeQueryParam.getUpdateTimeStart() != null, TenantMeterType::getUpdateTime,tenantMeterTypeQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterTypeQueryParam.getUpdateTimeStart()))
				.le(tenantMeterTypeQueryParam.getUpdateTimeEnd() != null, TenantMeterType::getUpdateTime,tenantMeterTypeQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterTypeQueryParam.getUpdateTimeEnd()))
				;

		List<TenantMeterType> tenantMeterTypeList = tenantMeterTypeService.list(queryWrapperTenantMeterType);

		List<TenantMeterTypeVo> tenantMeterTypeVoList = TranslateUtil.translateList(tenantMeterTypeList, TenantMeterTypeVo.class);

		return tenantMeterTypeVoList;
	}
	
	@ApiOperation(value = "根据参数查询水表类型列表")
	@RequestMapping(value = "/tenant-meter-types", method = RequestMethod.GET)
	public Page<TenantMeterTypeVo> page(@RequestBody TenantMeterTypeQueryParam tenantMeterTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterType> pageTenantMeterType = new Page<TenantMeterType>(page, rows);
		QueryWrapper<TenantMeterType> queryWrapperTenantMeterType = new QueryWrapper<TenantMeterType>();
		queryWrapperTenantMeterType.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterType.lambda()
				.eq(tenantMeterTypeQueryParam.getId() != null, TenantMeterType::getId, tenantMeterTypeQueryParam.getId())
				.eq(tenantMeterTypeQueryParam.getTenantId() != null, TenantMeterType::getTenantId, tenantMeterTypeQueryParam.getTenantId())
				.eq(tenantMeterTypeQueryParam.getMeterTypeName() != null, TenantMeterType::getMeterTypeName, tenantMeterTypeQueryParam.getMeterTypeName())
				.eq(tenantMeterTypeQueryParam.getMeterTypeData() != null, TenantMeterType::getMeterTypeData, tenantMeterTypeQueryParam.getMeterTypeData())
				.eq(tenantMeterTypeQueryParam.getAddTime() != null, TenantMeterType::getAddTime, tenantMeterTypeQueryParam.getAddTime())
				.ge(tenantMeterTypeQueryParam.getAddTimeStart() != null, TenantMeterType::getAddTime,tenantMeterTypeQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterTypeQueryParam.getAddTimeStart()))
				.le(tenantMeterTypeQueryParam.getAddTimeEnd() != null, TenantMeterType::getAddTime,tenantMeterTypeQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterTypeQueryParam.getAddTimeEnd()))
				.eq(tenantMeterTypeQueryParam.getUpdateTime() != null, TenantMeterType::getUpdateTime, tenantMeterTypeQueryParam.getUpdateTime())
				.ge(tenantMeterTypeQueryParam.getUpdateTimeStart() != null, TenantMeterType::getUpdateTime,tenantMeterTypeQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterTypeQueryParam.getUpdateTimeStart()))
				.le(tenantMeterTypeQueryParam.getUpdateTimeEnd() != null, TenantMeterType::getUpdateTime,tenantMeterTypeQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterTypeQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantMeterType> tenantMeterTypePage = tenantMeterTypeService.page(pageTenantMeterType, queryWrapperTenantMeterType);

		Page<TenantMeterTypeVo> tenantMeterTypeVoPage = new Page<TenantMeterTypeVo>(page, rows);
		tenantMeterTypeVoPage.setCurrent(tenantMeterTypePage.getCurrent());
		tenantMeterTypeVoPage.setPages(tenantMeterTypePage.getPages());
		tenantMeterTypeVoPage.setSize(tenantMeterTypePage.getSize());
		tenantMeterTypeVoPage.setTotal(tenantMeterTypePage.getTotal());
		tenantMeterTypeVoPage.setRecords(tenantMeterTypePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterTypeVoPage;
	}
	
	@ApiOperation(value = "新增水表类型")
	@RequestMapping(value = "/tenant-meter-types", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterTypeAddParam tenantMeterTypeAddParam) {
		return tenantMeterTypeService.save(tenantMeterTypeAddParam);
	}

	@ApiOperation(value = "更新水表类型全部信息")
	@RequestMapping(value = "/tenant-meter-types/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterTypeUpdateParam tenantMeterTypeUpdateParam) {
		tenantMeterTypeUpdateParam.setId(id);
		return tenantMeterTypeService.updateById(tenantMeterTypeUpdateParam);
	}

	@ApiOperation(value = "根据参数更新水表类型信息")
	@RequestMapping(value = "/tenant-meter-types/{id}", method = RequestMethod.PATCH)
	public TenantMeterTypeVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterType tenantMeterType) {
        TenantMeterType tenantMeterTypeWhere = TenantMeterType.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterType> updateWrapperTenantMeterType = new UpdateWrapper<TenantMeterType>();
		updateWrapperTenantMeterType.setEntity(tenantMeterTypeWhere);
		updateWrapperTenantMeterType.lambda()//
				//.eq(TenantMeterType::getId, id)
				// .set(tenantMeterType.getId() != null, TenantMeterType::getId, tenantMeterType.getId())
				.set(tenantMeterType.getTenantId() != null, TenantMeterType::getTenantId, tenantMeterType.getTenantId())
				.set(tenantMeterType.getMeterTypeName() != null, TenantMeterType::getMeterTypeName, tenantMeterType.getMeterTypeName())
				.set(tenantMeterType.getMeterTypeData() != null, TenantMeterType::getMeterTypeData, tenantMeterType.getMeterTypeData())
				.set(tenantMeterType.getAddTime() != null, TenantMeterType::getAddTime, tenantMeterType.getAddTime())
				.set(tenantMeterType.getUpdateTime() != null, TenantMeterType::getUpdateTime, tenantMeterType.getUpdateTime())
				;

		boolean success = tenantMeterTypeService.update(updateWrapperTenantMeterType);
		if (success) {
			TenantMeterType tenantMeterTypeDatabase = tenantMeterTypeService.getById(id);
			return entity2vo(tenantMeterTypeDatabase);
		}
		log.info("partial update TenantMeterType fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterType, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水表类型")
	@RequestMapping(value = "/tenant-meter-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterTypeService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterTypeVo entity2vo(TenantMeterType tenantMeterType) {
		if (tenantMeterType == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterType);
		TenantMeterTypeVo tenantMeterTypeVo = JSON.parseObject(jsonString, TenantMeterTypeVo.class);
		if (StringUtils.isEmpty(tenantMeterTypeVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantMeterType.getTenantId());
			if (tenantInfo != null) {
				tenantMeterTypeVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterTypeVo;
	}

}
