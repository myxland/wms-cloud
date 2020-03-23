package com.zlsrj.wms.saas.rest;

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
import com.zlsrj.wms.api.dto.TenantPriceDetailAddParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.api.vo.TenantPriceDetailVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantPriceDetailService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水价明细", tags = { "水价明细操作接口" })
@RestController
@Slf4j
public class TenantPriceDetailRestController {

	@Autowired
	private ITenantPriceDetailService tenantPriceDetailService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询水价明细")
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.GET)
	public TenantPriceDetailVo getById(@PathVariable("id") String id) {
		TenantPriceDetail tenantPriceDetail = tenantPriceDetailService.getById(id);

		return entity2vo(tenantPriceDetail);
	}

	@ApiOperation(value = "根据参数查询水价明细列表")
	@RequestMapping(value = "/tenant-price-details/list", method = RequestMethod.GET)
	public List<TenantPriceDetailVo> list(@RequestBody TenantPriceDetailQueryParam tenantPriceDetailQueryParam) {
		QueryWrapper<TenantPriceDetail> queryWrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		queryWrapperTenantPriceDetail.lambda()
				.eq(tenantPriceDetailQueryParam.getId() != null, TenantPriceDetail::getId, tenantPriceDetailQueryParam.getId())
				.eq(tenantPriceDetailQueryParam.getTenantId() != null, TenantPriceDetail::getTenantId, tenantPriceDetailQueryParam.getTenantId())
				.eq(tenantPriceDetailQueryParam.getPriceId() != null, TenantPriceDetail::getPriceId, tenantPriceDetailQueryParam.getPriceId())
				.eq(tenantPriceDetailQueryParam.getPriceItemId() != null, TenantPriceDetail::getPriceItemId, tenantPriceDetailQueryParam.getPriceItemId())
				.eq(tenantPriceDetailQueryParam.getPriceRule() != null, TenantPriceDetail::getPriceRule, tenantPriceDetailQueryParam.getPriceRule())
				.eq(tenantPriceDetailQueryParam.getDetailPrice() != null, TenantPriceDetail::getDetailPrice, tenantPriceDetailQueryParam.getDetailPrice())
				.eq(tenantPriceDetailQueryParam.getAddTime() != null, TenantPriceDetail::getAddTime, tenantPriceDetailQueryParam.getAddTime())
				.ge(tenantPriceDetailQueryParam.getAddTimeStart() != null, TenantPriceDetail::getAddTime,tenantPriceDetailQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceDetailQueryParam.getAddTimeStart()))
				.le(tenantPriceDetailQueryParam.getAddTimeEnd() != null, TenantPriceDetail::getAddTime,tenantPriceDetailQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceDetailQueryParam.getAddTimeEnd()))
				.eq(tenantPriceDetailQueryParam.getUpdateTime() != null, TenantPriceDetail::getUpdateTime, tenantPriceDetailQueryParam.getUpdateTime())
				.ge(tenantPriceDetailQueryParam.getUpdateTimeStart() != null, TenantPriceDetail::getUpdateTime,tenantPriceDetailQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceDetailQueryParam.getUpdateTimeStart()))
				.le(tenantPriceDetailQueryParam.getUpdateTimeEnd() != null, TenantPriceDetail::getUpdateTime,tenantPriceDetailQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceDetailQueryParam.getUpdateTimeEnd()))
				;

		List<TenantPriceDetail> tenantPriceDetailList = tenantPriceDetailService.list(queryWrapperTenantPriceDetail);

		List<TenantPriceDetailVo> tenantPriceDetailVoList = TranslateUtil.translateList(tenantPriceDetailList, TenantPriceDetailVo.class);

		return tenantPriceDetailVoList;
	}
	
	@ApiOperation(value = "根据参数查询水价明细列表")
	@RequestMapping(value = "/tenant-price-details", method = RequestMethod.GET)
	public Page<TenantPriceDetailVo> page(@RequestBody TenantPriceDetailQueryParam tenantPriceDetailQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPriceDetail> pageTenantPriceDetail = new Page<TenantPriceDetail>(page, rows);
		QueryWrapper<TenantPriceDetail> queryWrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		queryWrapperTenantPriceDetail.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantPriceDetail.lambda()
				.eq(tenantPriceDetailQueryParam.getId() != null, TenantPriceDetail::getId, tenantPriceDetailQueryParam.getId())
				.eq(tenantPriceDetailQueryParam.getTenantId() != null, TenantPriceDetail::getTenantId, tenantPriceDetailQueryParam.getTenantId())
				.eq(tenantPriceDetailQueryParam.getPriceId() != null, TenantPriceDetail::getPriceId, tenantPriceDetailQueryParam.getPriceId())
				.eq(tenantPriceDetailQueryParam.getPriceItemId() != null, TenantPriceDetail::getPriceItemId, tenantPriceDetailQueryParam.getPriceItemId())
				.eq(tenantPriceDetailQueryParam.getPriceRule() != null, TenantPriceDetail::getPriceRule, tenantPriceDetailQueryParam.getPriceRule())
				.eq(tenantPriceDetailQueryParam.getDetailPrice() != null, TenantPriceDetail::getDetailPrice, tenantPriceDetailQueryParam.getDetailPrice())
				.eq(tenantPriceDetailQueryParam.getAddTime() != null, TenantPriceDetail::getAddTime, tenantPriceDetailQueryParam.getAddTime())
				.ge(tenantPriceDetailQueryParam.getAddTimeStart() != null, TenantPriceDetail::getAddTime,tenantPriceDetailQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceDetailQueryParam.getAddTimeStart()))
				.le(tenantPriceDetailQueryParam.getAddTimeEnd() != null, TenantPriceDetail::getAddTime,tenantPriceDetailQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceDetailQueryParam.getAddTimeEnd()))
				.eq(tenantPriceDetailQueryParam.getUpdateTime() != null, TenantPriceDetail::getUpdateTime, tenantPriceDetailQueryParam.getUpdateTime())
				.ge(tenantPriceDetailQueryParam.getUpdateTimeStart() != null, TenantPriceDetail::getUpdateTime,tenantPriceDetailQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceDetailQueryParam.getUpdateTimeStart()))
				.le(tenantPriceDetailQueryParam.getUpdateTimeEnd() != null, TenantPriceDetail::getUpdateTime,tenantPriceDetailQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceDetailQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantPriceDetail> tenantPriceDetailPage = tenantPriceDetailService.page(pageTenantPriceDetail, queryWrapperTenantPriceDetail);

		Page<TenantPriceDetailVo> tenantPriceDetailVoPage = new Page<TenantPriceDetailVo>(page, rows);
		tenantPriceDetailVoPage.setCurrent(tenantPriceDetailPage.getCurrent());
		tenantPriceDetailVoPage.setPages(tenantPriceDetailPage.getPages());
		tenantPriceDetailVoPage.setSize(tenantPriceDetailPage.getSize());
		tenantPriceDetailVoPage.setTotal(tenantPriceDetailPage.getTotal());
		tenantPriceDetailVoPage.setRecords(tenantPriceDetailPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantPriceDetailVoPage;
	}
	
	@ApiOperation(value = "根据参数查询水价明细统计")
	@RequestMapping(value = "/tenant-price-details/aggregation", method = RequestMethod.GET)
	public TenantPriceDetailVo aggregation(@RequestBody TenantPriceDetailQueryParam tenantPriceDetailQueryParam) {
		QueryWrapper<TenantPriceDetail> queryWrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		queryWrapperTenantPriceDetail.lambda()
				.eq(tenantPriceDetailQueryParam.getId() != null, TenantPriceDetail::getId, tenantPriceDetailQueryParam.getId())
				.eq(tenantPriceDetailQueryParam.getTenantId() != null, TenantPriceDetail::getTenantId, tenantPriceDetailQueryParam.getTenantId())
				.eq(tenantPriceDetailQueryParam.getPriceId() != null, TenantPriceDetail::getPriceId, tenantPriceDetailQueryParam.getPriceId())
				.eq(tenantPriceDetailQueryParam.getPriceItemId() != null, TenantPriceDetail::getPriceItemId, tenantPriceDetailQueryParam.getPriceItemId())
				.eq(tenantPriceDetailQueryParam.getPriceRule() != null, TenantPriceDetail::getPriceRule, tenantPriceDetailQueryParam.getPriceRule())
				.eq(tenantPriceDetailQueryParam.getDetailPrice() != null, TenantPriceDetail::getDetailPrice, tenantPriceDetailQueryParam.getDetailPrice())
				.eq(tenantPriceDetailQueryParam.getAddTime() != null, TenantPriceDetail::getAddTime, tenantPriceDetailQueryParam.getAddTime())
				.ge(tenantPriceDetailQueryParam.getAddTimeStart() != null, TenantPriceDetail::getAddTime,tenantPriceDetailQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceDetailQueryParam.getAddTimeStart()))
				.le(tenantPriceDetailQueryParam.getAddTimeEnd() != null, TenantPriceDetail::getAddTime,tenantPriceDetailQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceDetailQueryParam.getAddTimeEnd()))
				.eq(tenantPriceDetailQueryParam.getUpdateTime() != null, TenantPriceDetail::getUpdateTime, tenantPriceDetailQueryParam.getUpdateTime())
				.ge(tenantPriceDetailQueryParam.getUpdateTimeStart() != null, TenantPriceDetail::getUpdateTime,tenantPriceDetailQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantPriceDetailQueryParam.getUpdateTimeStart()))
				.le(tenantPriceDetailQueryParam.getUpdateTimeEnd() != null, TenantPriceDetail::getUpdateTime,tenantPriceDetailQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantPriceDetailQueryParam.getUpdateTimeEnd()))
				;

		TenantPriceDetail tenantPriceDetail = tenantPriceDetailService.getAggregation(queryWrapperTenantPriceDetail);
		
		return entity2vo(tenantPriceDetail);
	}

	@ApiOperation(value = "新增水价明细")
	@RequestMapping(value = "/tenant-price-details", method = RequestMethod.POST)
	public String save(@RequestBody TenantPriceDetailAddParam tenantPriceDetailAddParam) {
		return tenantPriceDetailService.save(tenantPriceDetailAddParam);
	}

	@ApiOperation(value = "更新水价明细全部信息")
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantPriceDetailUpdateParam tenantPriceDetailUpdateParam) {
		tenantPriceDetailUpdateParam.setId(id);
		return tenantPriceDetailService.updateById(tenantPriceDetailUpdateParam);
	}

	@ApiOperation(value = "根据参数更新水价明细信息")
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.PATCH)
	public TenantPriceDetailVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPriceDetail tenantPriceDetail) {
        TenantPriceDetail tenantPriceDetailWhere = TenantPriceDetail.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantPriceDetail> updateWrapperTenantPriceDetail = new UpdateWrapper<TenantPriceDetail>();
		updateWrapperTenantPriceDetail.setEntity(tenantPriceDetailWhere);
		updateWrapperTenantPriceDetail.lambda()//
				//.eq(TenantPriceDetail::getId, id)
				// .set(tenantPriceDetail.getId() != null, TenantPriceDetail::getId, tenantPriceDetail.getId())
				.set(tenantPriceDetail.getTenantId() != null, TenantPriceDetail::getTenantId, tenantPriceDetail.getTenantId())
				.set(tenantPriceDetail.getPriceId() != null, TenantPriceDetail::getPriceId, tenantPriceDetail.getPriceId())
				.set(tenantPriceDetail.getPriceItemId() != null, TenantPriceDetail::getPriceItemId, tenantPriceDetail.getPriceItemId())
				.set(tenantPriceDetail.getPriceRule() != null, TenantPriceDetail::getPriceRule, tenantPriceDetail.getPriceRule())
				.set(tenantPriceDetail.getDetailPrice() != null, TenantPriceDetail::getDetailPrice, tenantPriceDetail.getDetailPrice())
				.set(tenantPriceDetail.getAddTime() != null, TenantPriceDetail::getAddTime, tenantPriceDetail.getAddTime())
				.set(tenantPriceDetail.getUpdateTime() != null, TenantPriceDetail::getUpdateTime, tenantPriceDetail.getUpdateTime())
				;

		boolean success = tenantPriceDetailService.update(updateWrapperTenantPriceDetail);
		if (success) {
			TenantPriceDetail tenantPriceDetailDatabase = tenantPriceDetailService.getById(id);
			return entity2vo(tenantPriceDetailDatabase);
		}
		log.info("partial update TenantPriceDetail fail，{}",
				ToStringBuilder.reflectionToString(tenantPriceDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水价明细")
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantPriceDetailService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPriceDetailVo entity2vo(TenantPriceDetail tenantPriceDetail) {
		if (tenantPriceDetail == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantPriceDetail);
		TenantPriceDetailVo tenantPriceDetailVo = JSON.parseObject(jsonString, TenantPriceDetailVo.class);
		if (StringUtils.isEmpty(tenantPriceDetailVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantPriceDetail.getTenantId());
			if (tenantInfo != null) {
				tenantPriceDetailVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantPriceDetailVo;
	}

}
