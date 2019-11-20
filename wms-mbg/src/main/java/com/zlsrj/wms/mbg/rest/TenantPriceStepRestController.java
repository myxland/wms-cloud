package com.zlsrj.wms.mbg.rest;

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
import com.zlsrj.wms.api.dto.TenantPriceStepQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.vo.TenantPriceStepVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantPriceItemService;
import com.zlsrj.wms.mbg.service.ITenantPriceStepService;
import com.zlsrj.wms.mbg.service.ITenantPriceTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "价格阶梯", tags = { "价格阶梯操作接口" })
@RestController
@Slf4j
public class TenantPriceStepRestController {

	@Autowired
	private ITenantPriceStepService tenantPriceStepService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private ITenantPriceTypeService tenantPriceTypeService;
	@Autowired
	private ITenantPriceItemService tenantPriceItemService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询价格阶梯")
	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.GET)
	public TenantPriceStepVo getById(@PathVariable("id") Long id) {
		TenantPriceStep tenantPriceStep = tenantPriceStepService.getById(id);

		return entity2vo(tenantPriceStep);
	}

	@ApiOperation(value = "根据参数查询价格阶梯列表")
	@RequestMapping(value = "/tenant-price-steps", method = RequestMethod.GET)
	public Page<TenantPriceStepVo> page(@RequestBody TenantPriceStepQueryParam tenantPriceStepQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPriceStep> pageTenantPriceStep = new Page<TenantPriceStep>(page, rows);
		QueryWrapper<TenantPriceStep> queryWrapperTenantPriceStep = new QueryWrapper<TenantPriceStep>();
		queryWrapperTenantPriceStep.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantPriceStep.lambda()
						.eq(tenantPriceStepQueryParam.getId() != null, TenantPriceStep::getId, tenantPriceStepQueryParam.getId())
						.eq(tenantPriceStepQueryParam.getTenantId() != null, TenantPriceStep::getTenantId, tenantPriceStepQueryParam.getTenantId())
						.eq(tenantPriceStepQueryParam.getPriceTypeId() != null, TenantPriceStep::getPriceTypeId, tenantPriceStepQueryParam.getPriceTypeId())
						.eq(tenantPriceStepQueryParam.getPriceItemId() != null, TenantPriceStep::getPriceItemId, tenantPriceStepQueryParam.getPriceItemId())
						.eq(tenantPriceStepQueryParam.getStepId() != null, TenantPriceStep::getStepId, tenantPriceStepQueryParam.getStepId())
						.eq(tenantPriceStepQueryParam.getStartNum() != null, TenantPriceStep::getStartNum, tenantPriceStepQueryParam.getStartNum())
						.eq(tenantPriceStepQueryParam.getEndNum() != null, TenantPriceStep::getEndNum, tenantPriceStepQueryParam.getEndNum())
						.eq(tenantPriceStepQueryParam.getPrice() != null, TenantPriceStep::getPrice, tenantPriceStepQueryParam.getPrice())
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

	@ApiOperation(value = "新增价格阶梯")
	@RequestMapping(value = "/tenant-price-steps", method = RequestMethod.POST)
	public TenantPriceStepVo save(@RequestBody TenantPriceStep tenantPriceStep) {
		if (tenantPriceStep.getId() == null || tenantPriceStep.getId().compareTo(0L) <= 0) {
			tenantPriceStep.setId(idService.selectId());
		}
		boolean success = tenantPriceStepService.save(tenantPriceStep);
		if (success) {
			TenantPriceStep tenantPriceStepDatabase = tenantPriceStepService.getById(tenantPriceStep.getId());
			return entity2vo(tenantPriceStepDatabase);
		}
		log.info("save TenantPriceStep fail，{}", ToStringBuilder.reflectionToString(tenantPriceStep, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新价格阶梯全部信息")
	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.PUT)
	public TenantPriceStepVo updateById(@PathVariable("id") Long id, @RequestBody TenantPriceStep tenantPriceStep) {
		tenantPriceStep.setId(id);
		boolean success = tenantPriceStepService.updateById(tenantPriceStep);
		if (success) {
			TenantPriceStep tenantPriceStepDatabase = tenantPriceStepService.getById(id);
			return entity2vo(tenantPriceStepDatabase);
		}
		log.info("update TenantPriceStep fail，{}", ToStringBuilder.reflectionToString(tenantPriceStep, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新价格阶梯信息")
	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.PATCH)
	public TenantPriceStepVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantPriceStep tenantPriceStep) {
		UpdateWrapper<TenantPriceStep> updateWrapperTenantPriceStep = new UpdateWrapper<TenantPriceStep>();
		updateWrapperTenantPriceStep.lambda()//
				.eq(TenantPriceStep::getId, id)
				// .set(tenantPriceStep.getId() != null, TenantPriceStep::getId, tenantPriceStep.getId())
				.set(tenantPriceStep.getTenantId() != null, TenantPriceStep::getTenantId, tenantPriceStep.getTenantId())
				.set(tenantPriceStep.getPriceTypeId() != null, TenantPriceStep::getPriceTypeId, tenantPriceStep.getPriceTypeId())
				.set(tenantPriceStep.getPriceItemId() != null, TenantPriceStep::getPriceItemId, tenantPriceStep.getPriceItemId())
				.set(tenantPriceStep.getStepId() != null, TenantPriceStep::getStepId, tenantPriceStep.getStepId())
				.set(tenantPriceStep.getStartNum() != null, TenantPriceStep::getStartNum, tenantPriceStep.getStartNum())
				.set(tenantPriceStep.getEndNum() != null, TenantPriceStep::getEndNum, tenantPriceStep.getEndNum())
				.set(tenantPriceStep.getPrice() != null, TenantPriceStep::getPrice, tenantPriceStep.getPrice())
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

	@ApiOperation(value = "根据ID删除价格阶梯")
	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantPriceStepService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPriceStepVo entity2vo(TenantPriceStep tenantPriceStep) {
		String jsonString = JSON.toJSONString(tenantPriceStep);
		TenantPriceStepVo tenantPriceStepVo = JSON.parseObject(jsonString, TenantPriceStepVo.class);
		if (StringUtils.isEmpty(tenantPriceStepVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantPriceStep.getTenantId());
			if (tenantInfo != null) {
				tenantPriceStepVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		if(StringUtils.isEmpty(tenantPriceStepVo.getPriceTypeName())) {
			TenantPriceType tenantPriceType = tenantPriceTypeService.getById(tenantPriceStep.getPriceTypeId());
			if(tenantPriceType!=null) {
				tenantPriceStepVo.setPriceTypeName(tenantPriceType.getPriceTypeName());
			}
		}
		if(StringUtils.isEmpty(tenantPriceStepVo.getPriceItemName())) {
			TenantPriceItem tenantPriceItem = tenantPriceItemService.getById(tenantPriceStep.getPriceItemId());
			if(tenantPriceItem!=null) {
				tenantPriceStepVo.setPriceItemName(tenantPriceItem.getPriceItemName());
			}
		}
		return tenantPriceStepVo;
	}

}
