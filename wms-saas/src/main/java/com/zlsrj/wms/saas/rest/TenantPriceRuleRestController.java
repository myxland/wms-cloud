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
import com.zlsrj.wms.api.dto.TenantPriceRuleAddParam;
import com.zlsrj.wms.api.dto.TenantPriceRuleQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceRuleUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceRule;
import com.zlsrj.wms.api.vo.TenantPriceRuleVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantPriceRuleService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "计费规则", tags = { "计费规则操作接口" })
@RestController
@Slf4j
public class TenantPriceRuleRestController {

	@Autowired
	private ITenantPriceRuleService tenantPriceRuleService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询计费规则")
	@RequestMapping(value = "/tenant-price-rules/{id}", method = RequestMethod.GET)
	public TenantPriceRuleVo getById(@PathVariable("id") String id) {
		TenantPriceRule tenantPriceRule = tenantPriceRuleService.getById(id);

		return entity2vo(tenantPriceRule);
	}

	@ApiOperation(value = "根据参数查询计费规则列表")
	@RequestMapping(value = "/tenant-price-rules/list", method = RequestMethod.GET)
	public List<TenantPriceRuleVo> list(@RequestBody TenantPriceRuleQueryParam tenantPriceRuleQueryParam) {
		QueryWrapper<TenantPriceRule> queryWrapperTenantPriceRule = new QueryWrapper<TenantPriceRule>();
		queryWrapperTenantPriceRule.lambda()
				.eq(tenantPriceRuleQueryParam.getId() != null, TenantPriceRule::getId, tenantPriceRuleQueryParam.getId())
				.eq(tenantPriceRuleQueryParam.getTenantId() != null, TenantPriceRule::getTenantId, tenantPriceRuleQueryParam.getTenantId())
				.eq(tenantPriceRuleQueryParam.getRuleName() != null, TenantPriceRule::getRuleName, tenantPriceRuleQueryParam.getRuleName())
				.eq(tenantPriceRuleQueryParam.getRuleExplain() != null, TenantPriceRule::getRuleExplain, tenantPriceRuleQueryParam.getRuleExplain())
				.eq(tenantPriceRuleQueryParam.getAddTime() != null, TenantPriceRule::getAddTime, tenantPriceRuleQueryParam.getAddTime())
				.ge(tenantPriceRuleQueryParam.getAddTimeStart() != null, TenantPriceRule::getAddTime,tenantPriceRuleQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceRuleQueryParam.getAddTimeStart()))
				.le(tenantPriceRuleQueryParam.getAddTimeEnd() != null, TenantPriceRule::getAddTime,tenantPriceRuleQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceRuleQueryParam.getAddTimeEnd()))
				.eq(tenantPriceRuleQueryParam.getUpdateTime() != null, TenantPriceRule::getUpdateTime, tenantPriceRuleQueryParam.getUpdateTime())
				.ge(tenantPriceRuleQueryParam.getUpdateTimeStart() != null, TenantPriceRule::getUpdateTime,tenantPriceRuleQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceRuleQueryParam.getUpdateTimeStart()))
				.le(tenantPriceRuleQueryParam.getUpdateTimeEnd() != null, TenantPriceRule::getUpdateTime,tenantPriceRuleQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceRuleQueryParam.getUpdateTimeEnd()))
				;

		List<TenantPriceRule> tenantPriceRuleList = tenantPriceRuleService.list(queryWrapperTenantPriceRule);

		List<TenantPriceRuleVo> tenantPriceRuleVoList = TranslateUtil.translateList(tenantPriceRuleList, TenantPriceRuleVo.class);

		return tenantPriceRuleVoList;
	}
	
	@ApiOperation(value = "根据参数查询计费规则列表")
	@RequestMapping(value = "/tenant-price-rules", method = RequestMethod.GET)
	public Page<TenantPriceRuleVo> page(@RequestBody TenantPriceRuleQueryParam tenantPriceRuleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPriceRule> pageTenantPriceRule = new Page<TenantPriceRule>(page, rows);
		QueryWrapper<TenantPriceRule> queryWrapperTenantPriceRule = new QueryWrapper<TenantPriceRule>();
		queryWrapperTenantPriceRule.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantPriceRule.lambda()
				.eq(tenantPriceRuleQueryParam.getId() != null, TenantPriceRule::getId, tenantPriceRuleQueryParam.getId())
				.eq(tenantPriceRuleQueryParam.getTenantId() != null, TenantPriceRule::getTenantId, tenantPriceRuleQueryParam.getTenantId())
				.eq(tenantPriceRuleQueryParam.getRuleName() != null, TenantPriceRule::getRuleName, tenantPriceRuleQueryParam.getRuleName())
				.eq(tenantPriceRuleQueryParam.getRuleExplain() != null, TenantPriceRule::getRuleExplain, tenantPriceRuleQueryParam.getRuleExplain())
				.eq(tenantPriceRuleQueryParam.getAddTime() != null, TenantPriceRule::getAddTime, tenantPriceRuleQueryParam.getAddTime())
				.ge(tenantPriceRuleQueryParam.getAddTimeStart() != null, TenantPriceRule::getAddTime,tenantPriceRuleQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceRuleQueryParam.getAddTimeStart()))
				.le(tenantPriceRuleQueryParam.getAddTimeEnd() != null, TenantPriceRule::getAddTime,tenantPriceRuleQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceRuleQueryParam.getAddTimeEnd()))
				.eq(tenantPriceRuleQueryParam.getUpdateTime() != null, TenantPriceRule::getUpdateTime, tenantPriceRuleQueryParam.getUpdateTime())
				.ge(tenantPriceRuleQueryParam.getUpdateTimeStart() != null, TenantPriceRule::getUpdateTime,tenantPriceRuleQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceRuleQueryParam.getUpdateTimeStart()))
				.le(tenantPriceRuleQueryParam.getUpdateTimeEnd() != null, TenantPriceRule::getUpdateTime,tenantPriceRuleQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceRuleQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantPriceRule> tenantPriceRulePage = tenantPriceRuleService.page(pageTenantPriceRule, queryWrapperTenantPriceRule);

		Page<TenantPriceRuleVo> tenantPriceRuleVoPage = new Page<TenantPriceRuleVo>(page, rows);
		tenantPriceRuleVoPage.setCurrent(tenantPriceRulePage.getCurrent());
		tenantPriceRuleVoPage.setPages(tenantPriceRulePage.getPages());
		tenantPriceRuleVoPage.setSize(tenantPriceRulePage.getSize());
		tenantPriceRuleVoPage.setTotal(tenantPriceRulePage.getTotal());
		tenantPriceRuleVoPage.setRecords(tenantPriceRulePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantPriceRuleVoPage;
	}
	
	@ApiOperation(value = "新增计费规则")
	@RequestMapping(value = "/tenant-price-rules", method = RequestMethod.POST)
	public String save(@RequestBody TenantPriceRuleAddParam tenantPriceRuleAddParam) {
		return tenantPriceRuleService.save(tenantPriceRuleAddParam);
	}

	@ApiOperation(value = "更新计费规则全部信息")
	@RequestMapping(value = "/tenant-price-rules/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantPriceRuleUpdateParam tenantPriceRuleUpdateParam) {
		tenantPriceRuleUpdateParam.setId(id);
		return tenantPriceRuleService.updateById(tenantPriceRuleUpdateParam);
	}

	@ApiOperation(value = "根据参数更新计费规则信息")
	@RequestMapping(value = "/tenant-price-rules/{id}", method = RequestMethod.PATCH)
	public TenantPriceRuleVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPriceRule tenantPriceRule) {
        TenantPriceRule tenantPriceRuleWhere = TenantPriceRule.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantPriceRule> updateWrapperTenantPriceRule = new UpdateWrapper<TenantPriceRule>();
		updateWrapperTenantPriceRule.setEntity(tenantPriceRuleWhere);
		updateWrapperTenantPriceRule.lambda()//
				//.eq(TenantPriceRule::getId, id)
				// .set(tenantPriceRule.getId() != null, TenantPriceRule::getId, tenantPriceRule.getId())
				.set(tenantPriceRule.getTenantId() != null, TenantPriceRule::getTenantId, tenantPriceRule.getTenantId())
				.set(tenantPriceRule.getRuleName() != null, TenantPriceRule::getRuleName, tenantPriceRule.getRuleName())
				.set(tenantPriceRule.getRuleExplain() != null, TenantPriceRule::getRuleExplain, tenantPriceRule.getRuleExplain())
				.set(tenantPriceRule.getAddTime() != null, TenantPriceRule::getAddTime, tenantPriceRule.getAddTime())
				.set(tenantPriceRule.getUpdateTime() != null, TenantPriceRule::getUpdateTime, tenantPriceRule.getUpdateTime())
				;

		boolean success = tenantPriceRuleService.update(updateWrapperTenantPriceRule);
		if (success) {
			TenantPriceRule tenantPriceRuleDatabase = tenantPriceRuleService.getById(id);
			return entity2vo(tenantPriceRuleDatabase);
		}
		log.info("partial update TenantPriceRule fail，{}",
				ToStringBuilder.reflectionToString(tenantPriceRule, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除计费规则")
	@RequestMapping(value = "/tenant-price-rules/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantPriceRuleService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPriceRuleVo entity2vo(TenantPriceRule tenantPriceRule) {
		if (tenantPriceRule == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantPriceRule);
		TenantPriceRuleVo tenantPriceRuleVo = JSON.parseObject(jsonString, TenantPriceRuleVo.class);
		if (StringUtils.isEmpty(tenantPriceRuleVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantPriceRule.getTenantId());
			if (tenantInfo != null) {
				tenantPriceRuleVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantPriceRuleVo;
	}

}
