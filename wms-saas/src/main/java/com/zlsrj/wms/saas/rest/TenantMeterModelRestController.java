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
import com.zlsrj.wms.api.dto.TenantMeterModelAddParam;
import com.zlsrj.wms.api.dto.TenantMeterModelQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterModelUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterModel;
import com.zlsrj.wms.api.vo.TenantMeterModelVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterModelService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表型号", tags = { "水表型号操作接口" })
@RestController
@Slf4j
public class TenantMeterModelRestController {

	@Autowired
	private ITenantMeterModelService tenantMeterModelService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询水表型号")
	@RequestMapping(value = "/tenant-meter-models/{id}", method = RequestMethod.GET)
	public TenantMeterModelVo getById(@PathVariable("id") String id) {
		TenantMeterModel tenantMeterModel = tenantMeterModelService.getById(id);

		return entity2vo(tenantMeterModel);
	}

	@ApiOperation(value = "根据参数查询水表型号列表")
	@RequestMapping(value = "/tenant-meter-models/list", method = RequestMethod.GET)
	public List<TenantMeterModelVo> list(@RequestBody TenantMeterModelQueryParam tenantMeterModelQueryParam) {
		QueryWrapper<TenantMeterModel> queryWrapperTenantMeterModel = new QueryWrapper<TenantMeterModel>();
		queryWrapperTenantMeterModel.lambda()
				.eq(tenantMeterModelQueryParam.getId() != null, TenantMeterModel::getId, tenantMeterModelQueryParam.getId())
				.eq(tenantMeterModelQueryParam.getTenantId() != null, TenantMeterModel::getTenantId, tenantMeterModelQueryParam.getTenantId())
				.eq(tenantMeterModelQueryParam.getMeterModelName() != null, TenantMeterModel::getMeterModelName, tenantMeterModelQueryParam.getMeterModelName())
				.eq(tenantMeterModelQueryParam.getMeterModelData() != null, TenantMeterModel::getMeterModelData, tenantMeterModelQueryParam.getMeterModelData())
				.eq(tenantMeterModelQueryParam.getAddTime() != null, TenantMeterModel::getAddTime, tenantMeterModelQueryParam.getAddTime())
				.ge(tenantMeterModelQueryParam.getAddTimeStart() != null, TenantMeterModel::getAddTime,tenantMeterModelQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterModelQueryParam.getAddTimeStart()))
				.le(tenantMeterModelQueryParam.getAddTimeEnd() != null, TenantMeterModel::getAddTime,tenantMeterModelQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterModelQueryParam.getAddTimeEnd()))
				.eq(tenantMeterModelQueryParam.getUpdateTime() != null, TenantMeterModel::getUpdateTime, tenantMeterModelQueryParam.getUpdateTime())
				.ge(tenantMeterModelQueryParam.getUpdateTimeStart() != null, TenantMeterModel::getUpdateTime,tenantMeterModelQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterModelQueryParam.getUpdateTimeStart()))
				.le(tenantMeterModelQueryParam.getUpdateTimeEnd() != null, TenantMeterModel::getUpdateTime,tenantMeterModelQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterModelQueryParam.getUpdateTimeEnd()))
				;

		List<TenantMeterModel> tenantMeterModelList = tenantMeterModelService.list(queryWrapperTenantMeterModel);

		List<TenantMeterModelVo> tenantMeterModelVoList = TranslateUtil.translateList(tenantMeterModelList, TenantMeterModelVo.class);

		return tenantMeterModelVoList;
	}
	
	@ApiOperation(value = "根据参数查询水表型号列表")
	@RequestMapping(value = "/tenant-meter-models", method = RequestMethod.GET)
	public Page<TenantMeterModelVo> page(@RequestBody TenantMeterModelQueryParam tenantMeterModelQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterModel> pageTenantMeterModel = new Page<TenantMeterModel>(page, rows);
		QueryWrapper<TenantMeterModel> queryWrapperTenantMeterModel = new QueryWrapper<TenantMeterModel>();
		queryWrapperTenantMeterModel.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterModel.lambda()
				.eq(tenantMeterModelQueryParam.getId() != null, TenantMeterModel::getId, tenantMeterModelQueryParam.getId())
				.eq(tenantMeterModelQueryParam.getTenantId() != null, TenantMeterModel::getTenantId, tenantMeterModelQueryParam.getTenantId())
				.eq(tenantMeterModelQueryParam.getMeterModelName() != null, TenantMeterModel::getMeterModelName, tenantMeterModelQueryParam.getMeterModelName())
				.eq(tenantMeterModelQueryParam.getMeterModelData() != null, TenantMeterModel::getMeterModelData, tenantMeterModelQueryParam.getMeterModelData())
				.eq(tenantMeterModelQueryParam.getAddTime() != null, TenantMeterModel::getAddTime, tenantMeterModelQueryParam.getAddTime())
				.ge(tenantMeterModelQueryParam.getAddTimeStart() != null, TenantMeterModel::getAddTime,tenantMeterModelQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterModelQueryParam.getAddTimeStart()))
				.le(tenantMeterModelQueryParam.getAddTimeEnd() != null, TenantMeterModel::getAddTime,tenantMeterModelQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterModelQueryParam.getAddTimeEnd()))
				.eq(tenantMeterModelQueryParam.getUpdateTime() != null, TenantMeterModel::getUpdateTime, tenantMeterModelQueryParam.getUpdateTime())
				.ge(tenantMeterModelQueryParam.getUpdateTimeStart() != null, TenantMeterModel::getUpdateTime,tenantMeterModelQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterModelQueryParam.getUpdateTimeStart()))
				.le(tenantMeterModelQueryParam.getUpdateTimeEnd() != null, TenantMeterModel::getUpdateTime,tenantMeterModelQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterModelQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantMeterModel> tenantMeterModelPage = tenantMeterModelService.page(pageTenantMeterModel, queryWrapperTenantMeterModel);

		Page<TenantMeterModelVo> tenantMeterModelVoPage = new Page<TenantMeterModelVo>(page, rows);
		tenantMeterModelVoPage.setCurrent(tenantMeterModelPage.getCurrent());
		tenantMeterModelVoPage.setPages(tenantMeterModelPage.getPages());
		tenantMeterModelVoPage.setSize(tenantMeterModelPage.getSize());
		tenantMeterModelVoPage.setTotal(tenantMeterModelPage.getTotal());
		tenantMeterModelVoPage.setRecords(tenantMeterModelPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterModelVoPage;
	}
	
	@ApiOperation(value = "新增水表型号")
	@RequestMapping(value = "/tenant-meter-models", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterModelAddParam tenantMeterModelAddParam) {
		return tenantMeterModelService.save(tenantMeterModelAddParam);
	}

	@ApiOperation(value = "更新水表型号全部信息")
	@RequestMapping(value = "/tenant-meter-models/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterModelUpdateParam tenantMeterModelUpdateParam) {
		tenantMeterModelUpdateParam.setId(id);
		return tenantMeterModelService.updateById(tenantMeterModelUpdateParam);
	}

	@ApiOperation(value = "根据参数更新水表型号信息")
	@RequestMapping(value = "/tenant-meter-models/{id}", method = RequestMethod.PATCH)
	public TenantMeterModelVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterModel tenantMeterModel) {
        TenantMeterModel tenantMeterModelWhere = TenantMeterModel.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterModel> updateWrapperTenantMeterModel = new UpdateWrapper<TenantMeterModel>();
		updateWrapperTenantMeterModel.setEntity(tenantMeterModelWhere);
		updateWrapperTenantMeterModel.lambda()//
				//.eq(TenantMeterModel::getId, id)
				// .set(tenantMeterModel.getId() != null, TenantMeterModel::getId, tenantMeterModel.getId())
				.set(tenantMeterModel.getTenantId() != null, TenantMeterModel::getTenantId, tenantMeterModel.getTenantId())
				.set(tenantMeterModel.getMeterModelName() != null, TenantMeterModel::getMeterModelName, tenantMeterModel.getMeterModelName())
				.set(tenantMeterModel.getMeterModelData() != null, TenantMeterModel::getMeterModelData, tenantMeterModel.getMeterModelData())
				.set(tenantMeterModel.getAddTime() != null, TenantMeterModel::getAddTime, tenantMeterModel.getAddTime())
				.set(tenantMeterModel.getUpdateTime() != null, TenantMeterModel::getUpdateTime, tenantMeterModel.getUpdateTime())
				;

		boolean success = tenantMeterModelService.update(updateWrapperTenantMeterModel);
		if (success) {
			TenantMeterModel tenantMeterModelDatabase = tenantMeterModelService.getById(id);
			return entity2vo(tenantMeterModelDatabase);
		}
		log.info("partial update TenantMeterModel fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterModel, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水表型号")
	@RequestMapping(value = "/tenant-meter-models/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterModelService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterModelVo entity2vo(TenantMeterModel tenantMeterModel) {
		if (tenantMeterModel == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterModel);
		TenantMeterModelVo tenantMeterModelVo = JSON.parseObject(jsonString, TenantMeterModelVo.class);
		if (StringUtils.isEmpty(tenantMeterModelVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantMeterModel.getTenantId());
			if (tenantInfo != null) {
				tenantMeterModelVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterModelVo;
	}

}
