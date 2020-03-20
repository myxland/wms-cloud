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
import com.zlsrj.wms.api.dto.TenantMeterReadSituationAddParam;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterReadSituation;
import com.zlsrj.wms.api.vo.TenantMeterReadSituationVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterReadSituationService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "抄表表况", tags = { "抄表表况操作接口" })
@RestController
@Slf4j
public class TenantMeterReadSituationRestController {

	@Autowired
	private ITenantMeterReadSituationService tenantMeterReadSituationService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询抄表表况")
	@RequestMapping(value = "/tenant-meter-read-situations/{id}", method = RequestMethod.GET)
	public TenantMeterReadSituationVo getById(@PathVariable("id") String id) {
		TenantMeterReadSituation tenantMeterReadSituation = tenantMeterReadSituationService.getById(id);

		return entity2vo(tenantMeterReadSituation);
	}

	@ApiOperation(value = "根据参数查询抄表表况列表")
	@RequestMapping(value = "/tenant-meter-read-situations/list", method = RequestMethod.GET)
	public List<TenantMeterReadSituationVo> list(@RequestBody TenantMeterReadSituationQueryParam tenantMeterReadSituationQueryParam) {
		QueryWrapper<TenantMeterReadSituation> queryWrapperTenantMeterReadSituation = new QueryWrapper<TenantMeterReadSituation>();
		queryWrapperTenantMeterReadSituation.lambda()
				.eq(tenantMeterReadSituationQueryParam.getId() != null, TenantMeterReadSituation::getId, tenantMeterReadSituationQueryParam.getId())
				.eq(tenantMeterReadSituationQueryParam.getTenantId() != null, TenantMeterReadSituation::getTenantId, tenantMeterReadSituationQueryParam.getTenantId())
				.eq(tenantMeterReadSituationQueryParam.getReadSituationName() != null, TenantMeterReadSituation::getReadSituationName, tenantMeterReadSituationQueryParam.getReadSituationName())
				.eq(tenantMeterReadSituationQueryParam.getReadSituationData() != null, TenantMeterReadSituation::getReadSituationData, tenantMeterReadSituationQueryParam.getReadSituationData())
				.eq(tenantMeterReadSituationQueryParam.getAddTime() != null, TenantMeterReadSituation::getAddTime, tenantMeterReadSituationQueryParam.getAddTime())
				.ge(tenantMeterReadSituationQueryParam.getAddTimeStart() != null, TenantMeterReadSituation::getAddTime,tenantMeterReadSituationQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterReadSituationQueryParam.getAddTimeStart()))
				.le(tenantMeterReadSituationQueryParam.getAddTimeEnd() != null, TenantMeterReadSituation::getAddTime,tenantMeterReadSituationQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterReadSituationQueryParam.getAddTimeEnd()))
				.eq(tenantMeterReadSituationQueryParam.getUpdateTime() != null, TenantMeterReadSituation::getUpdateTime, tenantMeterReadSituationQueryParam.getUpdateTime())
				.ge(tenantMeterReadSituationQueryParam.getUpdateTimeStart() != null, TenantMeterReadSituation::getUpdateTime,tenantMeterReadSituationQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterReadSituationQueryParam.getUpdateTimeStart()))
				.le(tenantMeterReadSituationQueryParam.getUpdateTimeEnd() != null, TenantMeterReadSituation::getUpdateTime,tenantMeterReadSituationQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterReadSituationQueryParam.getUpdateTimeEnd()))
				;

		List<TenantMeterReadSituation> tenantMeterReadSituationList = tenantMeterReadSituationService.list(queryWrapperTenantMeterReadSituation);

		List<TenantMeterReadSituationVo> tenantMeterReadSituationVoList = TranslateUtil.translateList(tenantMeterReadSituationList, TenantMeterReadSituationVo.class);

		return tenantMeterReadSituationVoList;
	}
	
	@ApiOperation(value = "根据参数查询抄表表况列表")
	@RequestMapping(value = "/tenant-meter-read-situations", method = RequestMethod.GET)
	public Page<TenantMeterReadSituationVo> page(@RequestBody TenantMeterReadSituationQueryParam tenantMeterReadSituationQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterReadSituation> pageTenantMeterReadSituation = new Page<TenantMeterReadSituation>(page, rows);
		QueryWrapper<TenantMeterReadSituation> queryWrapperTenantMeterReadSituation = new QueryWrapper<TenantMeterReadSituation>();
		queryWrapperTenantMeterReadSituation.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterReadSituation.lambda()
				.eq(tenantMeterReadSituationQueryParam.getId() != null, TenantMeterReadSituation::getId, tenantMeterReadSituationQueryParam.getId())
				.eq(tenantMeterReadSituationQueryParam.getTenantId() != null, TenantMeterReadSituation::getTenantId, tenantMeterReadSituationQueryParam.getTenantId())
				.eq(tenantMeterReadSituationQueryParam.getReadSituationName() != null, TenantMeterReadSituation::getReadSituationName, tenantMeterReadSituationQueryParam.getReadSituationName())
				.eq(tenantMeterReadSituationQueryParam.getReadSituationData() != null, TenantMeterReadSituation::getReadSituationData, tenantMeterReadSituationQueryParam.getReadSituationData())
				.eq(tenantMeterReadSituationQueryParam.getAddTime() != null, TenantMeterReadSituation::getAddTime, tenantMeterReadSituationQueryParam.getAddTime())
				.ge(tenantMeterReadSituationQueryParam.getAddTimeStart() != null, TenantMeterReadSituation::getAddTime,tenantMeterReadSituationQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterReadSituationQueryParam.getAddTimeStart()))
				.le(tenantMeterReadSituationQueryParam.getAddTimeEnd() != null, TenantMeterReadSituation::getAddTime,tenantMeterReadSituationQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterReadSituationQueryParam.getAddTimeEnd()))
				.eq(tenantMeterReadSituationQueryParam.getUpdateTime() != null, TenantMeterReadSituation::getUpdateTime, tenantMeterReadSituationQueryParam.getUpdateTime())
				.ge(tenantMeterReadSituationQueryParam.getUpdateTimeStart() != null, TenantMeterReadSituation::getUpdateTime,tenantMeterReadSituationQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterReadSituationQueryParam.getUpdateTimeStart()))
				.le(tenantMeterReadSituationQueryParam.getUpdateTimeEnd() != null, TenantMeterReadSituation::getUpdateTime,tenantMeterReadSituationQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterReadSituationQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantMeterReadSituation> tenantMeterReadSituationPage = tenantMeterReadSituationService.page(pageTenantMeterReadSituation, queryWrapperTenantMeterReadSituation);

		Page<TenantMeterReadSituationVo> tenantMeterReadSituationVoPage = new Page<TenantMeterReadSituationVo>(page, rows);
		tenantMeterReadSituationVoPage.setCurrent(tenantMeterReadSituationPage.getCurrent());
		tenantMeterReadSituationVoPage.setPages(tenantMeterReadSituationPage.getPages());
		tenantMeterReadSituationVoPage.setSize(tenantMeterReadSituationPage.getSize());
		tenantMeterReadSituationVoPage.setTotal(tenantMeterReadSituationPage.getTotal());
		tenantMeterReadSituationVoPage.setRecords(tenantMeterReadSituationPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterReadSituationVoPage;
	}
	
	@ApiOperation(value = "新增抄表表况")
	@RequestMapping(value = "/tenant-meter-read-situations", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterReadSituationAddParam tenantMeterReadSituationAddParam) {
		return tenantMeterReadSituationService.save(tenantMeterReadSituationAddParam);
	}

	@ApiOperation(value = "更新抄表表况全部信息")
	@RequestMapping(value = "/tenant-meter-read-situations/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterReadSituationUpdateParam tenantMeterReadSituationUpdateParam) {
		tenantMeterReadSituationUpdateParam.setId(id);
		return tenantMeterReadSituationService.updateById(tenantMeterReadSituationUpdateParam);
	}

	@ApiOperation(value = "根据参数更新抄表表况信息")
	@RequestMapping(value = "/tenant-meter-read-situations/{id}", method = RequestMethod.PATCH)
	public TenantMeterReadSituationVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterReadSituation tenantMeterReadSituation) {
        TenantMeterReadSituation tenantMeterReadSituationWhere = TenantMeterReadSituation.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterReadSituation> updateWrapperTenantMeterReadSituation = new UpdateWrapper<TenantMeterReadSituation>();
		updateWrapperTenantMeterReadSituation.setEntity(tenantMeterReadSituationWhere);
		updateWrapperTenantMeterReadSituation.lambda()//
				//.eq(TenantMeterReadSituation::getId, id)
				// .set(tenantMeterReadSituation.getId() != null, TenantMeterReadSituation::getId, tenantMeterReadSituation.getId())
				.set(tenantMeterReadSituation.getTenantId() != null, TenantMeterReadSituation::getTenantId, tenantMeterReadSituation.getTenantId())
				.set(tenantMeterReadSituation.getReadSituationName() != null, TenantMeterReadSituation::getReadSituationName, tenantMeterReadSituation.getReadSituationName())
				.set(tenantMeterReadSituation.getReadSituationData() != null, TenantMeterReadSituation::getReadSituationData, tenantMeterReadSituation.getReadSituationData())
				.set(tenantMeterReadSituation.getAddTime() != null, TenantMeterReadSituation::getAddTime, tenantMeterReadSituation.getAddTime())
				.set(tenantMeterReadSituation.getUpdateTime() != null, TenantMeterReadSituation::getUpdateTime, tenantMeterReadSituation.getUpdateTime())
				;

		boolean success = tenantMeterReadSituationService.update(updateWrapperTenantMeterReadSituation);
		if (success) {
			TenantMeterReadSituation tenantMeterReadSituationDatabase = tenantMeterReadSituationService.getById(id);
			return entity2vo(tenantMeterReadSituationDatabase);
		}
		log.info("partial update TenantMeterReadSituation fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterReadSituation, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除抄表表况")
	@RequestMapping(value = "/tenant-meter-read-situations/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterReadSituationService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterReadSituationVo entity2vo(TenantMeterReadSituation tenantMeterReadSituation) {
		if (tenantMeterReadSituation == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterReadSituation);
		TenantMeterReadSituationVo tenantMeterReadSituationVo = JSON.parseObject(jsonString, TenantMeterReadSituationVo.class);
		if (StringUtils.isEmpty(tenantMeterReadSituationVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantMeterReadSituation.getTenantId());
			if (tenantInfo != null) {
				tenantMeterReadSituationVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterReadSituationVo;
	}

}
