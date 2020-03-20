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
import com.zlsrj.wms.api.dto.TenantPriceStepAddParam;
import com.zlsrj.wms.api.dto.TenantPriceStepQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceStepUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.vo.TenantPriceStepVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantPriceStepService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "阶梯明细", tags = { "阶梯明细操作接口" })
@RestController
@Slf4j
public class TenantPriceStepRestController {

	@Autowired
	private ITenantPriceStepService tenantPriceStepService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询阶梯明细")
	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.GET)
	public TenantPriceStepVo getById(@PathVariable("id") String id) {
		TenantPriceStep tenantPriceStep = tenantPriceStepService.getById(id);

		return entity2vo(tenantPriceStep);
	}

	@ApiOperation(value = "根据参数查询阶梯明细列表")
	@RequestMapping(value = "/tenant-price-steps/list", method = RequestMethod.GET)
	public List<TenantPriceStepVo> list(@RequestBody TenantPriceStepQueryParam tenantPriceStepQueryParam) {
		QueryWrapper<TenantPriceStep> queryWrapperTenantPriceStep = new QueryWrapper<TenantPriceStep>();
		queryWrapperTenantPriceStep.lambda()
				.eq(tenantPriceStepQueryParam.getId() != null, TenantPriceStep::getId, tenantPriceStepQueryParam.getId())
				.eq(tenantPriceStepQueryParam.getTenantId() != null, TenantPriceStep::getTenantId, tenantPriceStepQueryParam.getTenantId())
				.eq(tenantPriceStepQueryParam.getPriceDetailId() != null, TenantPriceStep::getPriceDetailId, tenantPriceStepQueryParam.getPriceDetailId())
				.eq(tenantPriceStepQueryParam.getStepClass() != null, TenantPriceStep::getStepClass, tenantPriceStepQueryParam.getStepClass())
				.eq(tenantPriceStepQueryParam.getStartCode() != null, TenantPriceStep::getStartCode, tenantPriceStepQueryParam.getStartCode())
				.eq(tenantPriceStepQueryParam.getEndCode() != null, TenantPriceStep::getEndCode, tenantPriceStepQueryParam.getEndCode())
				.eq(tenantPriceStepQueryParam.getStepPrice() != null, TenantPriceStep::getStepPrice, tenantPriceStepQueryParam.getStepPrice())
				.eq(tenantPriceStepQueryParam.getStepUsers() != null, TenantPriceStep::getStepUsers, tenantPriceStepQueryParam.getStepUsers())
				.eq(tenantPriceStepQueryParam.getStepUsersAdd() != null, TenantPriceStep::getStepUsersAdd, tenantPriceStepQueryParam.getStepUsersAdd())
				.eq(tenantPriceStepQueryParam.getAddTime() != null, TenantPriceStep::getAddTime, tenantPriceStepQueryParam.getAddTime())
				.ge(tenantPriceStepQueryParam.getAddTimeStart() != null, TenantPriceStep::getAddTime,tenantPriceStepQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceStepQueryParam.getAddTimeStart()))
				.le(tenantPriceStepQueryParam.getAddTimeEnd() != null, TenantPriceStep::getAddTime,tenantPriceStepQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceStepQueryParam.getAddTimeEnd()))
				.eq(tenantPriceStepQueryParam.getUpdateTime() != null, TenantPriceStep::getUpdateTime, tenantPriceStepQueryParam.getUpdateTime())
				.ge(tenantPriceStepQueryParam.getUpdateTimeStart() != null, TenantPriceStep::getUpdateTime,tenantPriceStepQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceStepQueryParam.getUpdateTimeStart()))
				.le(tenantPriceStepQueryParam.getUpdateTimeEnd() != null, TenantPriceStep::getUpdateTime,tenantPriceStepQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceStepQueryParam.getUpdateTimeEnd()))
				;

		List<TenantPriceStep> tenantPriceStepList = tenantPriceStepService.list(queryWrapperTenantPriceStep);

		List<TenantPriceStepVo> tenantPriceStepVoList = TranslateUtil.translateList(tenantPriceStepList, TenantPriceStepVo.class);

		return tenantPriceStepVoList;
	}
	
	@ApiOperation(value = "根据参数查询阶梯明细列表")
	@RequestMapping(value = "/tenant-price-steps", method = RequestMethod.GET)
	public Page<TenantPriceStepVo> page(@RequestBody TenantPriceStepQueryParam tenantPriceStepQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPriceStep> pageTenantPriceStep = new Page<TenantPriceStep>(page, rows);
		QueryWrapper<TenantPriceStep> queryWrapperTenantPriceStep = new QueryWrapper<TenantPriceStep>();
		queryWrapperTenantPriceStep.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantPriceStep.lambda()
				.eq(tenantPriceStepQueryParam.getId() != null, TenantPriceStep::getId, tenantPriceStepQueryParam.getId())
				.eq(tenantPriceStepQueryParam.getTenantId() != null, TenantPriceStep::getTenantId, tenantPriceStepQueryParam.getTenantId())
				.eq(tenantPriceStepQueryParam.getPriceDetailId() != null, TenantPriceStep::getPriceDetailId, tenantPriceStepQueryParam.getPriceDetailId())
				.eq(tenantPriceStepQueryParam.getStepClass() != null, TenantPriceStep::getStepClass, tenantPriceStepQueryParam.getStepClass())
				.eq(tenantPriceStepQueryParam.getStartCode() != null, TenantPriceStep::getStartCode, tenantPriceStepQueryParam.getStartCode())
				.eq(tenantPriceStepQueryParam.getEndCode() != null, TenantPriceStep::getEndCode, tenantPriceStepQueryParam.getEndCode())
				.eq(tenantPriceStepQueryParam.getStepPrice() != null, TenantPriceStep::getStepPrice, tenantPriceStepQueryParam.getStepPrice())
				.eq(tenantPriceStepQueryParam.getStepUsers() != null, TenantPriceStep::getStepUsers, tenantPriceStepQueryParam.getStepUsers())
				.eq(tenantPriceStepQueryParam.getStepUsersAdd() != null, TenantPriceStep::getStepUsersAdd, tenantPriceStepQueryParam.getStepUsersAdd())
				.eq(tenantPriceStepQueryParam.getAddTime() != null, TenantPriceStep::getAddTime, tenantPriceStepQueryParam.getAddTime())
				.ge(tenantPriceStepQueryParam.getAddTimeStart() != null, TenantPriceStep::getAddTime,tenantPriceStepQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceStepQueryParam.getAddTimeStart()))
				.le(tenantPriceStepQueryParam.getAddTimeEnd() != null, TenantPriceStep::getAddTime,tenantPriceStepQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceStepQueryParam.getAddTimeEnd()))
				.eq(tenantPriceStepQueryParam.getUpdateTime() != null, TenantPriceStep::getUpdateTime, tenantPriceStepQueryParam.getUpdateTime())
				.ge(tenantPriceStepQueryParam.getUpdateTimeStart() != null, TenantPriceStep::getUpdateTime,tenantPriceStepQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceStepQueryParam.getUpdateTimeStart()))
				.le(tenantPriceStepQueryParam.getUpdateTimeEnd() != null, TenantPriceStep::getUpdateTime,tenantPriceStepQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceStepQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantPriceStep> tenantPriceStepPage = tenantPriceStepService.page(pageTenantPriceStep, queryWrapperTenantPriceStep);

		Page<TenantPriceStepVo> tenantPriceStepVoPage = new Page<TenantPriceStepVo>(page, rows);
		tenantPriceStepVoPage.setCurrent(tenantPriceStepPage.getCurrent());
		tenantPriceStepVoPage.setPages(tenantPriceStepPage.getPages());
		tenantPriceStepVoPage.setSize(tenantPriceStepPage.getSize());
		tenantPriceStepVoPage.setTotal(tenantPriceStepPage.getTotal());
		tenantPriceStepVoPage.setRecords(tenantPriceStepPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantPriceStepVoPage;
	}
	
	@ApiOperation(value = "根据参数查询阶梯明细统计")
	@RequestMapping(value = "/tenant-price-steps/aggregation", method = RequestMethod.GET)
	public TenantPriceStepVo aggregation(@RequestBody TenantPriceStepQueryParam tenantPriceStepQueryParam) {
		QueryWrapper<TenantPriceStep> queryWrapperTenantPriceStep = new QueryWrapper<TenantPriceStep>();
		queryWrapperTenantPriceStep.lambda()
				.eq(tenantPriceStepQueryParam.getId() != null, TenantPriceStep::getId, tenantPriceStepQueryParam.getId())
				.eq(tenantPriceStepQueryParam.getTenantId() != null, TenantPriceStep::getTenantId, tenantPriceStepQueryParam.getTenantId())
				.eq(tenantPriceStepQueryParam.getPriceDetailId() != null, TenantPriceStep::getPriceDetailId, tenantPriceStepQueryParam.getPriceDetailId())
				.eq(tenantPriceStepQueryParam.getStepClass() != null, TenantPriceStep::getStepClass, tenantPriceStepQueryParam.getStepClass())
				.eq(tenantPriceStepQueryParam.getStartCode() != null, TenantPriceStep::getStartCode, tenantPriceStepQueryParam.getStartCode())
				.eq(tenantPriceStepQueryParam.getEndCode() != null, TenantPriceStep::getEndCode, tenantPriceStepQueryParam.getEndCode())
				.eq(tenantPriceStepQueryParam.getStepPrice() != null, TenantPriceStep::getStepPrice, tenantPriceStepQueryParam.getStepPrice())
				.eq(tenantPriceStepQueryParam.getStepUsers() != null, TenantPriceStep::getStepUsers, tenantPriceStepQueryParam.getStepUsers())
				.eq(tenantPriceStepQueryParam.getStepUsersAdd() != null, TenantPriceStep::getStepUsersAdd, tenantPriceStepQueryParam.getStepUsersAdd())
				.eq(tenantPriceStepQueryParam.getAddTime() != null, TenantPriceStep::getAddTime, tenantPriceStepQueryParam.getAddTime())
				.ge(tenantPriceStepQueryParam.getAddTimeStart() != null, TenantPriceStep::getAddTime,tenantPriceStepQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceStepQueryParam.getAddTimeStart()))
				.le(tenantPriceStepQueryParam.getAddTimeEnd() != null, TenantPriceStep::getAddTime,tenantPriceStepQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceStepQueryParam.getAddTimeEnd()))
				.eq(tenantPriceStepQueryParam.getUpdateTime() != null, TenantPriceStep::getUpdateTime, tenantPriceStepQueryParam.getUpdateTime())
				.ge(tenantPriceStepQueryParam.getUpdateTimeStart() != null, TenantPriceStep::getUpdateTime,tenantPriceStepQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceStepQueryParam.getUpdateTimeStart()))
				.le(tenantPriceStepQueryParam.getUpdateTimeEnd() != null, TenantPriceStep::getUpdateTime,tenantPriceStepQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceStepQueryParam.getUpdateTimeEnd()))
				;

		TenantPriceStep tenantPriceStep = tenantPriceStepService.getAggregation(queryWrapperTenantPriceStep);
		
		return entity2vo(tenantPriceStep);
	}

	@ApiOperation(value = "新增阶梯明细")
	@RequestMapping(value = "/tenant-price-steps", method = RequestMethod.POST)
	public String save(@RequestBody TenantPriceStepAddParam tenantPriceStepAddParam) {
		return tenantPriceStepService.save(tenantPriceStepAddParam);
	}

	@ApiOperation(value = "更新阶梯明细全部信息")
	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantPriceStepUpdateParam tenantPriceStepUpdateParam) {
		tenantPriceStepUpdateParam.setId(id);
		return tenantPriceStepService.updateById(tenantPriceStepUpdateParam);
	}

	@ApiOperation(value = "根据参数更新阶梯明细信息")
	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.PATCH)
	public TenantPriceStepVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPriceStep tenantPriceStep) {
        TenantPriceStep tenantPriceStepWhere = TenantPriceStep.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantPriceStep> updateWrapperTenantPriceStep = new UpdateWrapper<TenantPriceStep>();
		updateWrapperTenantPriceStep.setEntity(tenantPriceStepWhere);
		updateWrapperTenantPriceStep.lambda()//
				//.eq(TenantPriceStep::getId, id)
				// .set(tenantPriceStep.getId() != null, TenantPriceStep::getId, tenantPriceStep.getId())
				.set(tenantPriceStep.getTenantId() != null, TenantPriceStep::getTenantId, tenantPriceStep.getTenantId())
				.set(tenantPriceStep.getPriceDetailId() != null, TenantPriceStep::getPriceDetailId, tenantPriceStep.getPriceDetailId())
				.set(tenantPriceStep.getStepClass() != null, TenantPriceStep::getStepClass, tenantPriceStep.getStepClass())
				.set(tenantPriceStep.getStartCode() != null, TenantPriceStep::getStartCode, tenantPriceStep.getStartCode())
				.set(tenantPriceStep.getEndCode() != null, TenantPriceStep::getEndCode, tenantPriceStep.getEndCode())
				.set(tenantPriceStep.getStepPrice() != null, TenantPriceStep::getStepPrice, tenantPriceStep.getStepPrice())
				.set(tenantPriceStep.getStepUsers() != null, TenantPriceStep::getStepUsers, tenantPriceStep.getStepUsers())
				.set(tenantPriceStep.getStepUsersAdd() != null, TenantPriceStep::getStepUsersAdd, tenantPriceStep.getStepUsersAdd())
				.set(tenantPriceStep.getAddTime() != null, TenantPriceStep::getAddTime, tenantPriceStep.getAddTime())
				.set(tenantPriceStep.getUpdateTime() != null, TenantPriceStep::getUpdateTime, tenantPriceStep.getUpdateTime())
				;

		boolean success = tenantPriceStepService.update(updateWrapperTenantPriceStep);
		if (success) {
			TenantPriceStep tenantPriceStepDatabase = tenantPriceStepService.getById(id);
			return entity2vo(tenantPriceStepDatabase);
		}
		log.info("partial update TenantPriceStep fail，{}",
				ToStringBuilder.reflectionToString(tenantPriceStep, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除阶梯明细")
	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantPriceStepService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPriceStepVo entity2vo(TenantPriceStep tenantPriceStep) {
		if (tenantPriceStep == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantPriceStep);
		TenantPriceStepVo tenantPriceStepVo = JSON.parseObject(jsonString, TenantPriceStepVo.class);
		if (StringUtils.isEmpty(tenantPriceStepVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantPriceStep.getTenantId());
			if (tenantInfo != null) {
				tenantPriceStepVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantPriceStepVo;
	}

}
