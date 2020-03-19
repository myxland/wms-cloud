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
import com.zlsrj.wms.api.dto.TenantBusinessRulesAddParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesQueryParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantBusinessRules;
import com.zlsrj.wms.api.vo.TenantBusinessRulesVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantBusinessRulesService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "业务规则", tags = { "业务规则操作接口" })
@RestController
@Slf4j
public class TenantBusinessRulesRestController {

	@Autowired
	private ITenantBusinessRulesService tenantBusinessRulesService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询业务规则")
	@RequestMapping(value = "/tenant-business-ruless/{id}", method = RequestMethod.GET)
	public TenantBusinessRulesVo getById(@PathVariable("id") String id) {
		TenantBusinessRules tenantBusinessRules = tenantBusinessRulesService.getById(id);

		return entity2vo(tenantBusinessRules);
	}

	@ApiOperation(value = "根据参数查询业务规则列表")
	@RequestMapping(value = "/tenant-business-ruless/list", method = RequestMethod.GET)
	public List<TenantBusinessRulesVo> list(@RequestBody TenantBusinessRulesQueryParam tenantBusinessRulesQueryParam) {
		QueryWrapper<TenantBusinessRules> queryWrapperTenantBusinessRules = new QueryWrapper<TenantBusinessRules>();
		queryWrapperTenantBusinessRules.lambda()
				.eq(tenantBusinessRulesQueryParam.getId() != null, TenantBusinessRules::getId, tenantBusinessRulesQueryParam.getId())
				.eq(tenantBusinessRulesQueryParam.getTenantId() != null, TenantBusinessRules::getTenantId, tenantBusinessRulesQueryParam.getTenantId())
				.eq(tenantBusinessRulesQueryParam.getRulesType() != null, TenantBusinessRules::getRulesType, tenantBusinessRulesQueryParam.getRulesType())
				.eq(tenantBusinessRulesQueryParam.getRulesData() != null, TenantBusinessRules::getRulesData, tenantBusinessRulesQueryParam.getRulesData())
				.eq(tenantBusinessRulesQueryParam.getAddTime() != null, TenantBusinessRules::getAddTime, tenantBusinessRulesQueryParam.getAddTime())
				.ge(tenantBusinessRulesQueryParam.getAddTimeStart() != null, TenantBusinessRules::getAddTime,tenantBusinessRulesQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantBusinessRulesQueryParam.getAddTimeStart()))
				.le(tenantBusinessRulesQueryParam.getAddTimeEnd() != null, TenantBusinessRules::getAddTime,tenantBusinessRulesQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantBusinessRulesQueryParam.getAddTimeEnd()))
				.eq(tenantBusinessRulesQueryParam.getUpdateTime() != null, TenantBusinessRules::getUpdateTime, tenantBusinessRulesQueryParam.getUpdateTime())
				.ge(tenantBusinessRulesQueryParam.getUpdateTimeStart() != null, TenantBusinessRules::getUpdateTime,tenantBusinessRulesQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantBusinessRulesQueryParam.getUpdateTimeStart()))
				.le(tenantBusinessRulesQueryParam.getUpdateTimeEnd() != null, TenantBusinessRules::getUpdateTime,tenantBusinessRulesQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantBusinessRulesQueryParam.getUpdateTimeEnd()))
				;

		List<TenantBusinessRules> tenantBusinessRulesList = tenantBusinessRulesService.list(queryWrapperTenantBusinessRules);

		List<TenantBusinessRulesVo> tenantBusinessRulesVoList = TranslateUtil.translateList(tenantBusinessRulesList, TenantBusinessRulesVo.class);

		return tenantBusinessRulesVoList;
	}
	
	@ApiOperation(value = "根据参数查询业务规则列表")
	@RequestMapping(value = "/tenant-business-ruless", method = RequestMethod.GET)
	public Page<TenantBusinessRulesVo> page(@RequestBody TenantBusinessRulesQueryParam tenantBusinessRulesQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantBusinessRules> pageTenantBusinessRules = new Page<TenantBusinessRules>(page, rows);
		QueryWrapper<TenantBusinessRules> queryWrapperTenantBusinessRules = new QueryWrapper<TenantBusinessRules>();
		queryWrapperTenantBusinessRules.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantBusinessRules.lambda()
				.eq(tenantBusinessRulesQueryParam.getId() != null, TenantBusinessRules::getId, tenantBusinessRulesQueryParam.getId())
				.eq(tenantBusinessRulesQueryParam.getTenantId() != null, TenantBusinessRules::getTenantId, tenantBusinessRulesQueryParam.getTenantId())
				.eq(tenantBusinessRulesQueryParam.getRulesType() != null, TenantBusinessRules::getRulesType, tenantBusinessRulesQueryParam.getRulesType())
				.eq(tenantBusinessRulesQueryParam.getRulesData() != null, TenantBusinessRules::getRulesData, tenantBusinessRulesQueryParam.getRulesData())
				.eq(tenantBusinessRulesQueryParam.getAddTime() != null, TenantBusinessRules::getAddTime, tenantBusinessRulesQueryParam.getAddTime())
				.ge(tenantBusinessRulesQueryParam.getAddTimeStart() != null, TenantBusinessRules::getAddTime,tenantBusinessRulesQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantBusinessRulesQueryParam.getAddTimeStart()))
				.le(tenantBusinessRulesQueryParam.getAddTimeEnd() != null, TenantBusinessRules::getAddTime,tenantBusinessRulesQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantBusinessRulesQueryParam.getAddTimeEnd()))
				.eq(tenantBusinessRulesQueryParam.getUpdateTime() != null, TenantBusinessRules::getUpdateTime, tenantBusinessRulesQueryParam.getUpdateTime())
				.ge(tenantBusinessRulesQueryParam.getUpdateTimeStart() != null, TenantBusinessRules::getUpdateTime,tenantBusinessRulesQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantBusinessRulesQueryParam.getUpdateTimeStart()))
				.le(tenantBusinessRulesQueryParam.getUpdateTimeEnd() != null, TenantBusinessRules::getUpdateTime,tenantBusinessRulesQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantBusinessRulesQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantBusinessRules> tenantBusinessRulesPage = tenantBusinessRulesService.page(pageTenantBusinessRules, queryWrapperTenantBusinessRules);

		Page<TenantBusinessRulesVo> tenantBusinessRulesVoPage = new Page<TenantBusinessRulesVo>(page, rows);
		tenantBusinessRulesVoPage.setCurrent(tenantBusinessRulesPage.getCurrent());
		tenantBusinessRulesVoPage.setPages(tenantBusinessRulesPage.getPages());
		tenantBusinessRulesVoPage.setSize(tenantBusinessRulesPage.getSize());
		tenantBusinessRulesVoPage.setTotal(tenantBusinessRulesPage.getTotal());
		tenantBusinessRulesVoPage.setRecords(tenantBusinessRulesPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantBusinessRulesVoPage;
	}
	
	@ApiOperation(value = "新增业务规则")
	@RequestMapping(value = "/tenant-business-ruless", method = RequestMethod.POST)
	public String save(@RequestBody TenantBusinessRulesAddParam tenantBusinessRulesAddParam) {
		return tenantBusinessRulesService.save(tenantBusinessRulesAddParam);
	}

	@ApiOperation(value = "更新业务规则全部信息")
	@RequestMapping(value = "/tenant-business-ruless/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantBusinessRulesUpdateParam tenantBusinessRulesUpdateParam) {
		tenantBusinessRulesUpdateParam.setId(id);
		return tenantBusinessRulesService.updateById(tenantBusinessRulesUpdateParam);
	}

	@ApiOperation(value = "根据参数更新业务规则信息")
	@RequestMapping(value = "/tenant-business-ruless/{id}", method = RequestMethod.PATCH)
	public TenantBusinessRulesVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantBusinessRules tenantBusinessRules) {
        TenantBusinessRules tenantBusinessRulesWhere = TenantBusinessRules.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantBusinessRules> updateWrapperTenantBusinessRules = new UpdateWrapper<TenantBusinessRules>();
		updateWrapperTenantBusinessRules.setEntity(tenantBusinessRulesWhere);
		updateWrapperTenantBusinessRules.lambda()//
				//.eq(TenantBusinessRules::getId, id)
				// .set(tenantBusinessRules.getId() != null, TenantBusinessRules::getId, tenantBusinessRules.getId())
				.set(tenantBusinessRules.getTenantId() != null, TenantBusinessRules::getTenantId, tenantBusinessRules.getTenantId())
				.set(tenantBusinessRules.getRulesType() != null, TenantBusinessRules::getRulesType, tenantBusinessRules.getRulesType())
				.set(tenantBusinessRules.getRulesData() != null, TenantBusinessRules::getRulesData, tenantBusinessRules.getRulesData())
				.set(tenantBusinessRules.getAddTime() != null, TenantBusinessRules::getAddTime, tenantBusinessRules.getAddTime())
				.set(tenantBusinessRules.getUpdateTime() != null, TenantBusinessRules::getUpdateTime, tenantBusinessRules.getUpdateTime())
				;

		boolean success = tenantBusinessRulesService.update(updateWrapperTenantBusinessRules);
		if (success) {
			TenantBusinessRules tenantBusinessRulesDatabase = tenantBusinessRulesService.getById(id);
			return entity2vo(tenantBusinessRulesDatabase);
		}
		log.info("partial update TenantBusinessRules fail，{}",
				ToStringBuilder.reflectionToString(tenantBusinessRules, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除业务规则")
	@RequestMapping(value = "/tenant-business-ruless/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantBusinessRulesService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantBusinessRulesVo entity2vo(TenantBusinessRules tenantBusinessRules) {
		if (tenantBusinessRules == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantBusinessRules);
		TenantBusinessRulesVo tenantBusinessRulesVo = JSON.parseObject(jsonString, TenantBusinessRulesVo.class);
		if (StringUtils.isEmpty(tenantBusinessRulesVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantBusinessRules.getTenantId());
			if (tenantInfo != null) {
				tenantBusinessRulesVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantBusinessRulesVo;
	}

}
