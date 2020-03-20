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
import com.zlsrj.wms.api.dto.TenantMeterCaliberAddParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterCaliber;
import com.zlsrj.wms.api.vo.TenantMeterCaliberVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterCaliberService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表口径字典表", tags = { "水表口径字典表操作接口" })
@RestController
@Slf4j
public class TenantMeterCaliberRestController {

	@Autowired
	private ITenantMeterCaliberService tenantMeterCaliberService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询水表口径字典表")
	@RequestMapping(value = "/tenant-meter-calibers/{id}", method = RequestMethod.GET)
	public TenantMeterCaliberVo getById(@PathVariable("id") String id) {
		TenantMeterCaliber tenantMeterCaliber = tenantMeterCaliberService.getById(id);

		return entity2vo(tenantMeterCaliber);
	}

	@ApiOperation(value = "根据参数查询水表口径字典表列表")
	@RequestMapping(value = "/tenant-meter-calibers/list", method = RequestMethod.GET)
	public List<TenantMeterCaliberVo> list(@RequestBody TenantMeterCaliberQueryParam tenantMeterCaliberQueryParam) {
		QueryWrapper<TenantMeterCaliber> queryWrapperTenantMeterCaliber = new QueryWrapper<TenantMeterCaliber>();
		queryWrapperTenantMeterCaliber.lambda()
				.eq(tenantMeterCaliberQueryParam.getId() != null, TenantMeterCaliber::getId, tenantMeterCaliberQueryParam.getId())
				.eq(tenantMeterCaliberQueryParam.getTenantId() != null, TenantMeterCaliber::getTenantId, tenantMeterCaliberQueryParam.getTenantId())
				.eq(tenantMeterCaliberQueryParam.getMeterCaliberName() != null, TenantMeterCaliber::getMeterCaliberName, tenantMeterCaliberQueryParam.getMeterCaliberName())
				.eq(tenantMeterCaliberQueryParam.getMeterCaliberData() != null, TenantMeterCaliber::getMeterCaliberData, tenantMeterCaliberQueryParam.getMeterCaliberData())
				.eq(tenantMeterCaliberQueryParam.getAddTime() != null, TenantMeterCaliber::getAddTime, tenantMeterCaliberQueryParam.getAddTime())
				.ge(tenantMeterCaliberQueryParam.getAddTimeStart() != null, TenantMeterCaliber::getAddTime,tenantMeterCaliberQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterCaliberQueryParam.getAddTimeStart()))
				.le(tenantMeterCaliberQueryParam.getAddTimeEnd() != null, TenantMeterCaliber::getAddTime,tenantMeterCaliberQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterCaliberQueryParam.getAddTimeEnd()))
				.eq(tenantMeterCaliberQueryParam.getUpdateTime() != null, TenantMeterCaliber::getUpdateTime, tenantMeterCaliberQueryParam.getUpdateTime())
				.ge(tenantMeterCaliberQueryParam.getUpdateTimeStart() != null, TenantMeterCaliber::getUpdateTime,tenantMeterCaliberQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterCaliberQueryParam.getUpdateTimeStart()))
				.le(tenantMeterCaliberQueryParam.getUpdateTimeEnd() != null, TenantMeterCaliber::getUpdateTime,tenantMeterCaliberQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterCaliberQueryParam.getUpdateTimeEnd()))
				;

		List<TenantMeterCaliber> tenantMeterCaliberList = tenantMeterCaliberService.list(queryWrapperTenantMeterCaliber);

		List<TenantMeterCaliberVo> tenantMeterCaliberVoList = TranslateUtil.translateList(tenantMeterCaliberList, TenantMeterCaliberVo.class);

		return tenantMeterCaliberVoList;
	}
	
	@ApiOperation(value = "根据参数查询水表口径字典表列表")
	@RequestMapping(value = "/tenant-meter-calibers", method = RequestMethod.GET)
	public Page<TenantMeterCaliberVo> page(@RequestBody TenantMeterCaliberQueryParam tenantMeterCaliberQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterCaliber> pageTenantMeterCaliber = new Page<TenantMeterCaliber>(page, rows);
		QueryWrapper<TenantMeterCaliber> queryWrapperTenantMeterCaliber = new QueryWrapper<TenantMeterCaliber>();
		queryWrapperTenantMeterCaliber.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterCaliber.lambda()
				.eq(tenantMeterCaliberQueryParam.getId() != null, TenantMeterCaliber::getId, tenantMeterCaliberQueryParam.getId())
				.eq(tenantMeterCaliberQueryParam.getTenantId() != null, TenantMeterCaliber::getTenantId, tenantMeterCaliberQueryParam.getTenantId())
				.eq(tenantMeterCaliberQueryParam.getMeterCaliberName() != null, TenantMeterCaliber::getMeterCaliberName, tenantMeterCaliberQueryParam.getMeterCaliberName())
				.eq(tenantMeterCaliberQueryParam.getMeterCaliberData() != null, TenantMeterCaliber::getMeterCaliberData, tenantMeterCaliberQueryParam.getMeterCaliberData())
				.eq(tenantMeterCaliberQueryParam.getAddTime() != null, TenantMeterCaliber::getAddTime, tenantMeterCaliberQueryParam.getAddTime())
				.ge(tenantMeterCaliberQueryParam.getAddTimeStart() != null, TenantMeterCaliber::getAddTime,tenantMeterCaliberQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterCaliberQueryParam.getAddTimeStart()))
				.le(tenantMeterCaliberQueryParam.getAddTimeEnd() != null, TenantMeterCaliber::getAddTime,tenantMeterCaliberQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterCaliberQueryParam.getAddTimeEnd()))
				.eq(tenantMeterCaliberQueryParam.getUpdateTime() != null, TenantMeterCaliber::getUpdateTime, tenantMeterCaliberQueryParam.getUpdateTime())
				.ge(tenantMeterCaliberQueryParam.getUpdateTimeStart() != null, TenantMeterCaliber::getUpdateTime,tenantMeterCaliberQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterCaliberQueryParam.getUpdateTimeStart()))
				.le(tenantMeterCaliberQueryParam.getUpdateTimeEnd() != null, TenantMeterCaliber::getUpdateTime,tenantMeterCaliberQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterCaliberQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantMeterCaliber> tenantMeterCaliberPage = tenantMeterCaliberService.page(pageTenantMeterCaliber, queryWrapperTenantMeterCaliber);

		Page<TenantMeterCaliberVo> tenantMeterCaliberVoPage = new Page<TenantMeterCaliberVo>(page, rows);
		tenantMeterCaliberVoPage.setCurrent(tenantMeterCaliberPage.getCurrent());
		tenantMeterCaliberVoPage.setPages(tenantMeterCaliberPage.getPages());
		tenantMeterCaliberVoPage.setSize(tenantMeterCaliberPage.getSize());
		tenantMeterCaliberVoPage.setTotal(tenantMeterCaliberPage.getTotal());
		tenantMeterCaliberVoPage.setRecords(tenantMeterCaliberPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterCaliberVoPage;
	}
	
	@ApiOperation(value = "新增水表口径字典表")
	@RequestMapping(value = "/tenant-meter-calibers", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterCaliberAddParam tenantMeterCaliberAddParam) {
		return tenantMeterCaliberService.save(tenantMeterCaliberAddParam);
	}

	@ApiOperation(value = "更新水表口径字典表全部信息")
	@RequestMapping(value = "/tenant-meter-calibers/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterCaliberUpdateParam tenantMeterCaliberUpdateParam) {
		tenantMeterCaliberUpdateParam.setId(id);
		return tenantMeterCaliberService.updateById(tenantMeterCaliberUpdateParam);
	}

	@ApiOperation(value = "根据参数更新水表口径字典表信息")
	@RequestMapping(value = "/tenant-meter-calibers/{id}", method = RequestMethod.PATCH)
	public TenantMeterCaliberVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterCaliber tenantMeterCaliber) {
        TenantMeterCaliber tenantMeterCaliberWhere = TenantMeterCaliber.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterCaliber> updateWrapperTenantMeterCaliber = new UpdateWrapper<TenantMeterCaliber>();
		updateWrapperTenantMeterCaliber.setEntity(tenantMeterCaliberWhere);
		updateWrapperTenantMeterCaliber.lambda()//
				//.eq(TenantMeterCaliber::getId, id)
				// .set(tenantMeterCaliber.getId() != null, TenantMeterCaliber::getId, tenantMeterCaliber.getId())
				.set(tenantMeterCaliber.getTenantId() != null, TenantMeterCaliber::getTenantId, tenantMeterCaliber.getTenantId())
				.set(tenantMeterCaliber.getMeterCaliberName() != null, TenantMeterCaliber::getMeterCaliberName, tenantMeterCaliber.getMeterCaliberName())
				.set(tenantMeterCaliber.getMeterCaliberData() != null, TenantMeterCaliber::getMeterCaliberData, tenantMeterCaliber.getMeterCaliberData())
				.set(tenantMeterCaliber.getAddTime() != null, TenantMeterCaliber::getAddTime, tenantMeterCaliber.getAddTime())
				.set(tenantMeterCaliber.getUpdateTime() != null, TenantMeterCaliber::getUpdateTime, tenantMeterCaliber.getUpdateTime())
				;

		boolean success = tenantMeterCaliberService.update(updateWrapperTenantMeterCaliber);
		if (success) {
			TenantMeterCaliber tenantMeterCaliberDatabase = tenantMeterCaliberService.getById(id);
			return entity2vo(tenantMeterCaliberDatabase);
		}
		log.info("partial update TenantMeterCaliber fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterCaliber, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水表口径字典表")
	@RequestMapping(value = "/tenant-meter-calibers/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterCaliberService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterCaliberVo entity2vo(TenantMeterCaliber tenantMeterCaliber) {
		if (tenantMeterCaliber == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterCaliber);
		TenantMeterCaliberVo tenantMeterCaliberVo = JSON.parseObject(jsonString, TenantMeterCaliberVo.class);
		if (StringUtils.isEmpty(tenantMeterCaliberVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantMeterCaliber.getTenantId());
			if (tenantInfo != null) {
				tenantMeterCaliberVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterCaliberVo;
	}

}
