package com.zlsrj.wms.saas.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterPriceAddParam;
import com.zlsrj.wms.api.dto.TenantMeterPriceQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterPriceUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterPrice;
import com.zlsrj.wms.api.vo.TenantMeterPriceVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterPriceService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表计费", tags = { "水表计费操作接口" })
@RestController
@Slf4j
public class TenantMeterPriceRestController {

	@Autowired
	private ITenantMeterPriceService tenantMeterPriceService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询水表计费")
	@RequestMapping(value = "/tenant-meter-prices/{id}", method = RequestMethod.GET)
	public TenantMeterPriceVo getById(@PathVariable("id") String id) {
		TenantMeterPrice tenantMeterPrice = tenantMeterPriceService.getById(id);

		return entity2vo(tenantMeterPrice);
	}

	@ApiOperation(value = "根据参数查询水表计费列表")
	@RequestMapping(value = "/tenant-meter-prices/list", method = RequestMethod.GET)
	public List<TenantMeterPriceVo> list(@RequestBody TenantMeterPriceQueryParam tenantMeterPriceQueryParam) {
		QueryWrapper<TenantMeterPrice> queryWrapperTenantMeterPrice = new QueryWrapper<TenantMeterPrice>();
		queryWrapperTenantMeterPrice.lambda()
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getId()), TenantMeterPrice::getId, tenantMeterPriceQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getTenantId()), TenantMeterPrice::getTenantId, tenantMeterPriceQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getMeterId()), TenantMeterPrice::getMeterId, tenantMeterPriceQueryParam.getMeterId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getMeterCode()), TenantMeterPrice::getMeterCode, tenantMeterPriceQueryParam.getMeterCode())
				.eq(tenantMeterPriceQueryParam.getPriceOrder() != null, TenantMeterPrice::getPriceOrder, tenantMeterPriceQueryParam.getPriceOrder())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getPriceId()), TenantMeterPrice::getPriceId, tenantMeterPriceQueryParam.getPriceId())
				.eq(tenantMeterPriceQueryParam.getPriceType() != null, TenantMeterPrice::getPriceType, tenantMeterPriceQueryParam.getPriceType())
				.eq(tenantMeterPriceQueryParam.getPriceScale() != null, TenantMeterPrice::getPriceScale, tenantMeterPriceQueryParam.getPriceScale())
				.eq(tenantMeterPriceQueryParam.getAddTime() != null, TenantMeterPrice::getAddTime, tenantMeterPriceQueryParam.getAddTime())
				.ge(tenantMeterPriceQueryParam.getAddTimeStart() != null, TenantMeterPrice::getAddTime,tenantMeterPriceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterPriceQueryParam.getAddTimeStart()))
				.le(tenantMeterPriceQueryParam.getAddTimeEnd() != null, TenantMeterPrice::getAddTime,tenantMeterPriceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterPriceQueryParam.getAddTimeEnd()))
				.eq(tenantMeterPriceQueryParam.getUpdateTime() != null, TenantMeterPrice::getUpdateTime, tenantMeterPriceQueryParam.getUpdateTime())
				.ge(tenantMeterPriceQueryParam.getUpdateTimeStart() != null, TenantMeterPrice::getUpdateTime,tenantMeterPriceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterPriceQueryParam.getUpdateTimeStart()))
				.le(tenantMeterPriceQueryParam.getUpdateTimeEnd() != null, TenantMeterPrice::getUpdateTime,tenantMeterPriceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterPriceQueryParam.getUpdateTimeEnd()))
				;

		List<TenantMeterPrice> tenantMeterPriceList = tenantMeterPriceService.list(queryWrapperTenantMeterPrice);

		List<TenantMeterPriceVo> tenantMeterPriceVoList = tenantMeterPriceList.stream()//
				 .map(e -> entity2vo(e))//
				 .collect(Collectors.toList());

		return tenantMeterPriceVoList;
	}
	
	@ApiOperation(value = "根据参数查询水表计费数量")
	@RequestMapping(value = "/tenant-meter-prices/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantMeterPriceQueryParam tenantMeterPriceQueryParam) {
		QueryWrapper<TenantMeterPrice> queryWrapperTenantMeterPrice = new QueryWrapper<TenantMeterPrice>();
		queryWrapperTenantMeterPrice.lambda()
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getId()), TenantMeterPrice::getId, tenantMeterPriceQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getTenantId()), TenantMeterPrice::getTenantId, tenantMeterPriceQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getMeterId()), TenantMeterPrice::getMeterId, tenantMeterPriceQueryParam.getMeterId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getMeterCode()), TenantMeterPrice::getMeterCode, tenantMeterPriceQueryParam.getMeterCode())
				.eq(tenantMeterPriceQueryParam.getPriceOrder() != null, TenantMeterPrice::getPriceOrder, tenantMeterPriceQueryParam.getPriceOrder())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getPriceId()), TenantMeterPrice::getPriceId, tenantMeterPriceQueryParam.getPriceId())
				.eq(tenantMeterPriceQueryParam.getPriceType() != null, TenantMeterPrice::getPriceType, tenantMeterPriceQueryParam.getPriceType())
				.eq(tenantMeterPriceQueryParam.getPriceScale() != null, TenantMeterPrice::getPriceScale, tenantMeterPriceQueryParam.getPriceScale())
				.eq(tenantMeterPriceQueryParam.getAddTime() != null, TenantMeterPrice::getAddTime, tenantMeterPriceQueryParam.getAddTime())
				.ge(tenantMeterPriceQueryParam.getAddTimeStart() != null, TenantMeterPrice::getAddTime,tenantMeterPriceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterPriceQueryParam.getAddTimeStart()))
				.le(tenantMeterPriceQueryParam.getAddTimeEnd() != null, TenantMeterPrice::getAddTime,tenantMeterPriceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterPriceQueryParam.getAddTimeEnd()))
				.eq(tenantMeterPriceQueryParam.getUpdateTime() != null, TenantMeterPrice::getUpdateTime, tenantMeterPriceQueryParam.getUpdateTime())
				.ge(tenantMeterPriceQueryParam.getUpdateTimeStart() != null, TenantMeterPrice::getUpdateTime,tenantMeterPriceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterPriceQueryParam.getUpdateTimeStart()))
				.le(tenantMeterPriceQueryParam.getUpdateTimeEnd() != null, TenantMeterPrice::getUpdateTime,tenantMeterPriceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterPriceQueryParam.getUpdateTimeEnd()))
				;

		int count = tenantMeterPriceService.count(queryWrapperTenantMeterPrice);

		return count;
	}
	
	@ApiOperation(value = "根据参数查询水表计费列表")
	@RequestMapping(value = "/tenant-meter-prices", method = RequestMethod.GET)
	public Page<TenantMeterPriceVo> page(@RequestBody TenantMeterPriceQueryParam tenantMeterPriceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterPrice> pageTenantMeterPrice = new Page<TenantMeterPrice>(page, rows);
		QueryWrapper<TenantMeterPrice> queryWrapperTenantMeterPrice = new QueryWrapper<TenantMeterPrice>();
		queryWrapperTenantMeterPrice.orderBy(StringUtils.isNotBlank(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterPrice.lambda()
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getId()), TenantMeterPrice::getId, tenantMeterPriceQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getTenantId()), TenantMeterPrice::getTenantId, tenantMeterPriceQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getMeterId()), TenantMeterPrice::getMeterId, tenantMeterPriceQueryParam.getMeterId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getMeterCode()), TenantMeterPrice::getMeterCode, tenantMeterPriceQueryParam.getMeterCode())
				.eq(tenantMeterPriceQueryParam.getPriceOrder() != null, TenantMeterPrice::getPriceOrder, tenantMeterPriceQueryParam.getPriceOrder())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getPriceId()), TenantMeterPrice::getPriceId, tenantMeterPriceQueryParam.getPriceId())
				.eq(tenantMeterPriceQueryParam.getPriceType() != null, TenantMeterPrice::getPriceType, tenantMeterPriceQueryParam.getPriceType())
				.eq(tenantMeterPriceQueryParam.getPriceScale() != null, TenantMeterPrice::getPriceScale, tenantMeterPriceQueryParam.getPriceScale())
				.eq(tenantMeterPriceQueryParam.getAddTime() != null, TenantMeterPrice::getAddTime, tenantMeterPriceQueryParam.getAddTime())
				.ge(tenantMeterPriceQueryParam.getAddTimeStart() != null, TenantMeterPrice::getAddTime,tenantMeterPriceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterPriceQueryParam.getAddTimeStart()))
				.le(tenantMeterPriceQueryParam.getAddTimeEnd() != null, TenantMeterPrice::getAddTime,tenantMeterPriceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterPriceQueryParam.getAddTimeEnd()))
				.eq(tenantMeterPriceQueryParam.getUpdateTime() != null, TenantMeterPrice::getUpdateTime, tenantMeterPriceQueryParam.getUpdateTime())
				.ge(tenantMeterPriceQueryParam.getUpdateTimeStart() != null, TenantMeterPrice::getUpdateTime,tenantMeterPriceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterPriceQueryParam.getUpdateTimeStart()))
				.le(tenantMeterPriceQueryParam.getUpdateTimeEnd() != null, TenantMeterPrice::getUpdateTime,tenantMeterPriceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterPriceQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantMeterPrice> tenantMeterPricePage = tenantMeterPriceService.page(pageTenantMeterPrice, queryWrapperTenantMeterPrice);

		Page<TenantMeterPriceVo> tenantMeterPriceVoPage = new Page<TenantMeterPriceVo>(page, rows);
		tenantMeterPriceVoPage.setCurrent(tenantMeterPricePage.getCurrent());
		tenantMeterPriceVoPage.setPages(tenantMeterPricePage.getPages());
		tenantMeterPriceVoPage.setSize(tenantMeterPricePage.getSize());
		tenantMeterPriceVoPage.setTotal(tenantMeterPricePage.getTotal());
		tenantMeterPriceVoPage.setRecords(tenantMeterPricePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterPriceVoPage;
	}
	
	@ApiOperation(value = "根据参数查询水表计费统计")
	@RequestMapping(value = "/tenant-meter-prices/aggregation", method = RequestMethod.GET)
	public TenantMeterPriceVo aggregation(@RequestBody TenantMeterPriceQueryParam tenantMeterPriceQueryParam) {
		QueryWrapper<TenantMeterPrice> queryWrapperTenantMeterPrice = new QueryWrapper<TenantMeterPrice>();
		queryWrapperTenantMeterPrice.lambda()
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getId()), TenantMeterPrice::getId, tenantMeterPriceQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getTenantId()), TenantMeterPrice::getTenantId, tenantMeterPriceQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getMeterId()), TenantMeterPrice::getMeterId, tenantMeterPriceQueryParam.getMeterId())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getMeterCode()), TenantMeterPrice::getMeterCode, tenantMeterPriceQueryParam.getMeterCode())
				.eq(tenantMeterPriceQueryParam.getPriceOrder() != null, TenantMeterPrice::getPriceOrder, tenantMeterPriceQueryParam.getPriceOrder())
				.eq(StringUtils.isNotEmpty(tenantMeterPriceQueryParam.getPriceId()), TenantMeterPrice::getPriceId, tenantMeterPriceQueryParam.getPriceId())
				.eq(tenantMeterPriceQueryParam.getPriceType() != null, TenantMeterPrice::getPriceType, tenantMeterPriceQueryParam.getPriceType())
				.eq(tenantMeterPriceQueryParam.getPriceScale() != null, TenantMeterPrice::getPriceScale, tenantMeterPriceQueryParam.getPriceScale())
				.eq(tenantMeterPriceQueryParam.getAddTime() != null, TenantMeterPrice::getAddTime, tenantMeterPriceQueryParam.getAddTime())
				.ge(tenantMeterPriceQueryParam.getAddTimeStart() != null, TenantMeterPrice::getAddTime,tenantMeterPriceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterPriceQueryParam.getAddTimeStart()))
				.le(tenantMeterPriceQueryParam.getAddTimeEnd() != null, TenantMeterPrice::getAddTime,tenantMeterPriceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterPriceQueryParam.getAddTimeEnd()))
				.eq(tenantMeterPriceQueryParam.getUpdateTime() != null, TenantMeterPrice::getUpdateTime, tenantMeterPriceQueryParam.getUpdateTime())
				.ge(tenantMeterPriceQueryParam.getUpdateTimeStart() != null, TenantMeterPrice::getUpdateTime,tenantMeterPriceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterPriceQueryParam.getUpdateTimeStart()))
				.le(tenantMeterPriceQueryParam.getUpdateTimeEnd() != null, TenantMeterPrice::getUpdateTime,tenantMeterPriceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterPriceQueryParam.getUpdateTimeEnd()))
				;

		TenantMeterPrice tenantMeterPrice = tenantMeterPriceService.getAggregation(queryWrapperTenantMeterPrice);
		
		return entity2vo(tenantMeterPrice);
	}

	@ApiOperation(value = "新增水表计费")
	@RequestMapping(value = "/tenant-meter-prices", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterPriceAddParam tenantMeterPriceAddParam) {
		return tenantMeterPriceService.save(tenantMeterPriceAddParam);
	}

	@ApiOperation(value = "更新水表计费全部信息")
	@RequestMapping(value = "/tenant-meter-prices/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterPriceUpdateParam tenantMeterPriceUpdateParam) {
		tenantMeterPriceUpdateParam.setId(id);
		return tenantMeterPriceService.updateById(tenantMeterPriceUpdateParam);
	}

	@ApiOperation(value = "根据ID删除水表计费")
	@RequestMapping(value = "/tenant-meter-prices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterPriceService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterPriceVo entity2vo(TenantMeterPrice tenantMeterPrice) {
		if (tenantMeterPrice == null) {
			return null;
		}

		TenantMeterPriceVo tenantMeterPriceVo = TranslateUtil.translate(tenantMeterPrice, TenantMeterPriceVo.class);
		if (StringUtils.isEmpty(tenantMeterPriceVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantMeterPrice.getTenantId());
			if (tenantInfo != null) {
				tenantMeterPriceVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterPriceVo;
	}

}
