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
import com.zlsrj.wms.api.dto.SystemDictionariesAddParam;
import com.zlsrj.wms.api.dto.SystemDictionariesQueryParam;
import com.zlsrj.wms.api.dto.SystemDictionariesUpdateParam;
import com.zlsrj.wms.api.entity.SystemDictionaries;
import com.zlsrj.wms.api.vo.SystemDictionariesVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ISystemDictionariesService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "基础字典表", tags = { "基础字典表操作接口" })
@RestController
@Slf4j
public class SystemDictionariesRestController {

	@Autowired
	private ISystemDictionariesService systemDictionariesService;

	@ApiOperation(value = "根据ID查询基础字典表")
	@RequestMapping(value = "/system-dictionariess/{id}", method = RequestMethod.GET)
	public SystemDictionariesVo getById(@PathVariable("id") String id) {
		SystemDictionaries systemDictionaries = systemDictionariesService.getById(id);

		return entity2vo(systemDictionaries);
	}

	@ApiOperation(value = "根据参数查询基础字典表列表")
	@RequestMapping(value = "/system-dictionariess/list", method = RequestMethod.GET)
	public List<SystemDictionariesVo> list(@RequestBody SystemDictionariesQueryParam systemDictionariesQueryParam) {
		QueryWrapper<SystemDictionaries> queryWrapperSystemDictionaries = new QueryWrapper<SystemDictionaries>();
		queryWrapperSystemDictionaries.lambda()
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getId()), SystemDictionaries::getId, systemDictionariesQueryParam.getId())
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getDictCode()), SystemDictionaries::getDictCode, systemDictionariesQueryParam.getDictCode())
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getDictType()), SystemDictionaries::getDictType, systemDictionariesQueryParam.getDictType())
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getDictName()), SystemDictionaries::getDictName, systemDictionariesQueryParam.getDictName())
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getDictData()), SystemDictionaries::getDictData, systemDictionariesQueryParam.getDictData())
				.eq(systemDictionariesQueryParam.getAddTime() != null, SystemDictionaries::getAddTime, systemDictionariesQueryParam.getAddTime())
				.ge(systemDictionariesQueryParam.getAddTimeStart() != null, SystemDictionaries::getAddTime,systemDictionariesQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(systemDictionariesQueryParam.getAddTimeStart()))
				.le(systemDictionariesQueryParam.getAddTimeEnd() != null, SystemDictionaries::getAddTime,systemDictionariesQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(systemDictionariesQueryParam.getAddTimeEnd()))
				.eq(systemDictionariesQueryParam.getUpdateTime() != null, SystemDictionaries::getUpdateTime, systemDictionariesQueryParam.getUpdateTime())
				.ge(systemDictionariesQueryParam.getUpdateTimeStart() != null, SystemDictionaries::getUpdateTime,systemDictionariesQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(systemDictionariesQueryParam.getUpdateTimeStart()))
				.le(systemDictionariesQueryParam.getUpdateTimeEnd() != null, SystemDictionaries::getUpdateTime,systemDictionariesQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(systemDictionariesQueryParam.getUpdateTimeEnd()))
				;

		List<SystemDictionaries> systemDictionariesList = systemDictionariesService.list(queryWrapperSystemDictionaries);

		List<SystemDictionariesVo> systemDictionariesVoList = TranslateUtil.translateList(systemDictionariesList, SystemDictionariesVo.class);

		return systemDictionariesVoList;
	}
	
	@ApiOperation(value = "根据参数查询基础字典表列表")
	@RequestMapping(value = "/system-dictionariess", method = RequestMethod.GET)
	public Page<SystemDictionariesVo> page(@RequestBody SystemDictionariesQueryParam systemDictionariesQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<SystemDictionaries> pageSystemDictionaries = new Page<SystemDictionaries>(page, rows);
		QueryWrapper<SystemDictionaries> queryWrapperSystemDictionaries = new QueryWrapper<SystemDictionaries>();
		queryWrapperSystemDictionaries.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperSystemDictionaries.lambda()
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getId()), SystemDictionaries::getId, systemDictionariesQueryParam.getId())
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getDictCode()), SystemDictionaries::getDictCode, systemDictionariesQueryParam.getDictCode())
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getDictType()), SystemDictionaries::getDictType, systemDictionariesQueryParam.getDictType())
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getDictName()), SystemDictionaries::getDictName, systemDictionariesQueryParam.getDictName())
				.eq(StringUtils.isNotEmpty(systemDictionariesQueryParam.getDictData()), SystemDictionaries::getDictData, systemDictionariesQueryParam.getDictData())
				.eq(systemDictionariesQueryParam.getAddTime() != null, SystemDictionaries::getAddTime, systemDictionariesQueryParam.getAddTime())
				.ge(systemDictionariesQueryParam.getAddTimeStart() != null, SystemDictionaries::getAddTime,systemDictionariesQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(systemDictionariesQueryParam.getAddTimeStart()))
				.le(systemDictionariesQueryParam.getAddTimeEnd() != null, SystemDictionaries::getAddTime,systemDictionariesQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(systemDictionariesQueryParam.getAddTimeEnd()))
				.eq(systemDictionariesQueryParam.getUpdateTime() != null, SystemDictionaries::getUpdateTime, systemDictionariesQueryParam.getUpdateTime())
				.ge(systemDictionariesQueryParam.getUpdateTimeStart() != null, SystemDictionaries::getUpdateTime,systemDictionariesQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(systemDictionariesQueryParam.getUpdateTimeStart()))
				.le(systemDictionariesQueryParam.getUpdateTimeEnd() != null, SystemDictionaries::getUpdateTime,systemDictionariesQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(systemDictionariesQueryParam.getUpdateTimeEnd()))
				;

		IPage<SystemDictionaries> systemDictionariesPage = systemDictionariesService.page(pageSystemDictionaries, queryWrapperSystemDictionaries);

		Page<SystemDictionariesVo> systemDictionariesVoPage = new Page<SystemDictionariesVo>(page, rows);
		systemDictionariesVoPage.setCurrent(systemDictionariesPage.getCurrent());
		systemDictionariesVoPage.setPages(systemDictionariesPage.getPages());
		systemDictionariesVoPage.setSize(systemDictionariesPage.getSize());
		systemDictionariesVoPage.setTotal(systemDictionariesPage.getTotal());
		systemDictionariesVoPage.setRecords(systemDictionariesPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return systemDictionariesVoPage;
	}
	
	@ApiOperation(value = "新增基础字典表")
	@RequestMapping(value = "/system-dictionariess", method = RequestMethod.POST)
	public String save(@RequestBody SystemDictionariesAddParam systemDictionariesAddParam) {
		return systemDictionariesService.save(systemDictionariesAddParam);
	}

	@ApiOperation(value = "更新基础字典表全部信息")
	@RequestMapping(value = "/system-dictionariess/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody SystemDictionariesUpdateParam systemDictionariesUpdateParam) {
		systemDictionariesUpdateParam.setId(id);
		return systemDictionariesService.updateById(systemDictionariesUpdateParam);
	}

	@ApiOperation(value = "根据参数更新基础字典表信息")
	@RequestMapping(value = "/system-dictionariess/{id}", method = RequestMethod.PATCH)
	public SystemDictionariesVo updatePatchById(@PathVariable("id") String id, @RequestBody SystemDictionaries systemDictionaries) {
        SystemDictionaries systemDictionariesWhere = SystemDictionaries.builder()//
				.id(id)//
				.build();
		UpdateWrapper<SystemDictionaries> updateWrapperSystemDictionaries = new UpdateWrapper<SystemDictionaries>();
		updateWrapperSystemDictionaries.setEntity(systemDictionariesWhere);
		updateWrapperSystemDictionaries.lambda()//
				//.eq(SystemDictionaries::getId, id)
				// .set(systemDictionaries.getId() != null, SystemDictionaries::getId, systemDictionaries.getId())
				.set(systemDictionaries.getDictCode() != null, SystemDictionaries::getDictCode, systemDictionaries.getDictCode())
				.set(systemDictionaries.getDictType() != null, SystemDictionaries::getDictType, systemDictionaries.getDictType())
				.set(systemDictionaries.getDictName() != null, SystemDictionaries::getDictName, systemDictionaries.getDictName())
				.set(systemDictionaries.getDictData() != null, SystemDictionaries::getDictData, systemDictionaries.getDictData())
				.set(systemDictionaries.getAddTime() != null, SystemDictionaries::getAddTime, systemDictionaries.getAddTime())
				.set(systemDictionaries.getUpdateTime() != null, SystemDictionaries::getUpdateTime, systemDictionaries.getUpdateTime())
				;

		boolean success = systemDictionariesService.update(updateWrapperSystemDictionaries);
		if (success) {
			SystemDictionaries systemDictionariesDatabase = systemDictionariesService.getById(id);
			return entity2vo(systemDictionariesDatabase);
		}
		log.info("partial update SystemDictionaries fail，{}",
				ToStringBuilder.reflectionToString(systemDictionaries, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除基础字典表")
	@RequestMapping(value = "/system-dictionariess/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = systemDictionariesService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private SystemDictionariesVo entity2vo(SystemDictionaries systemDictionaries) {
		if (systemDictionaries == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(systemDictionaries);
		SystemDictionariesVo systemDictionariesVo = JSON.parseObject(jsonString, SystemDictionariesVo.class);
		return systemDictionariesVo;
	}

}
