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
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookQueryParam;
import com.zlsrj.wms.api.dto.TenantBookUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.api.vo.TenantBookVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantBookService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "表册信息", tags = { "表册信息操作接口" })
@RestController
@Slf4j
public class TenantBookRestController {

	@Autowired
	private ITenantBookService tenantBookService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询表册信息")
	@RequestMapping(value = "/tenant-books/{id}", method = RequestMethod.GET)
	public TenantBookVo getById(@PathVariable("id") String id) {
		TenantBook tenantBook = tenantBookService.getById(id);

		return entity2vo(tenantBook);
	}

	@ApiOperation(value = "根据参数查询表册信息列表")
	@RequestMapping(value = "/tenant-books/list", method = RequestMethod.GET)
	public List<TenantBookVo> list(@RequestBody TenantBookQueryParam tenantBookQueryParam) {
		QueryWrapper<TenantBook> queryWrapperTenantBook = new QueryWrapper<TenantBook>();
		queryWrapperTenantBook.lambda()
				.eq(tenantBookQueryParam.getId() != null, TenantBook::getId, tenantBookQueryParam.getId())
				.eq(tenantBookQueryParam.getTenantId() != null, TenantBook::getTenantId, tenantBookQueryParam.getTenantId())
				.eq(tenantBookQueryParam.getBookCode() != null, TenantBook::getBookCode, tenantBookQueryParam.getBookCode())
				.eq(tenantBookQueryParam.getBookName() != null, TenantBook::getBookName, tenantBookQueryParam.getBookName())
				.eq(tenantBookQueryParam.getBookReaderEmployeeId() != null, TenantBook::getBookReaderEmployeeId, tenantBookQueryParam.getBookReaderEmployeeId())
				.eq(tenantBookQueryParam.getBookChargeEmployeeId() != null, TenantBook::getBookChargeEmployeeId, tenantBookQueryParam.getBookChargeEmployeeId())
				.eq(tenantBookQueryParam.getBookMarketingAreaId() != null, TenantBook::getBookMarketingAreaId, tenantBookQueryParam.getBookMarketingAreaId())
				.eq(tenantBookQueryParam.getBookReadCycle() != null, TenantBook::getBookReadCycle, tenantBookQueryParam.getBookReadCycle())
				.eq(tenantBookQueryParam.getBookLastMonth() != null, TenantBook::getBookLastMonth, tenantBookQueryParam.getBookLastMonth())
				.eq(tenantBookQueryParam.getBookReadMonth() != null, TenantBook::getBookReadMonth, tenantBookQueryParam.getBookReadMonth())
				.eq(tenantBookQueryParam.getBookSettleCycle() != null, TenantBook::getBookSettleCycle, tenantBookQueryParam.getBookSettleCycle())
				.eq(tenantBookQueryParam.getBookSettleLastMonth() != null, TenantBook::getBookSettleLastMonth, tenantBookQueryParam.getBookSettleLastMonth())
				.eq(tenantBookQueryParam.getBookSettleMonth() != null, TenantBook::getBookSettleMonth, tenantBookQueryParam.getBookSettleMonth())
				.eq(tenantBookQueryParam.getBookStatus() != null, TenantBook::getBookStatus, tenantBookQueryParam.getBookStatus())
				.eq(tenantBookQueryParam.getBookReadStatus() != null, TenantBook::getBookReadStatus, tenantBookQueryParam.getBookReadStatus())
				.eq(tenantBookQueryParam.getPriceCalss() != null, TenantBook::getPriceCalss, tenantBookQueryParam.getPriceCalss())
				.eq(tenantBookQueryParam.getPriceMemo() != null, TenantBook::getPriceMemo, tenantBookQueryParam.getPriceMemo())
				.eq(tenantBookQueryParam.getAddTime() != null, TenantBook::getAddTime, tenantBookQueryParam.getAddTime())
				.ge(tenantBookQueryParam.getAddTimeStart() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getAddTimeStart()))
				.le(tenantBookQueryParam.getAddTimeEnd() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getAddTimeEnd()))
				.eq(tenantBookQueryParam.getUpdateTime() != null, TenantBook::getUpdateTime, tenantBookQueryParam.getUpdateTime())
				.ge(tenantBookQueryParam.getUpdateTimeStart() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getUpdateTimeStart()))
				.le(tenantBookQueryParam.getUpdateTimeEnd() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getUpdateTimeEnd()))
				;

		List<TenantBook> tenantBookList = tenantBookService.list(queryWrapperTenantBook);

		List<TenantBookVo> tenantBookVoList = TranslateUtil.translateList(tenantBookList, TenantBookVo.class);

		return tenantBookVoList;
	}
	
	@ApiOperation(value = "根据参数查询表册信息列表")
	@RequestMapping(value = "/tenant-books", method = RequestMethod.GET)
	public Page<TenantBookVo> page(@RequestBody TenantBookQueryParam tenantBookQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantBook> pageTenantBook = new Page<TenantBook>(page, rows);
		QueryWrapper<TenantBook> queryWrapperTenantBook = new QueryWrapper<TenantBook>();
		queryWrapperTenantBook.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantBook.lambda()
				.eq(tenantBookQueryParam.getId() != null, TenantBook::getId, tenantBookQueryParam.getId())
				.eq(tenantBookQueryParam.getTenantId() != null, TenantBook::getTenantId, tenantBookQueryParam.getTenantId())
				.eq(tenantBookQueryParam.getBookCode() != null, TenantBook::getBookCode, tenantBookQueryParam.getBookCode())
				.eq(tenantBookQueryParam.getBookName() != null, TenantBook::getBookName, tenantBookQueryParam.getBookName())
				.eq(tenantBookQueryParam.getBookReaderEmployeeId() != null, TenantBook::getBookReaderEmployeeId, tenantBookQueryParam.getBookReaderEmployeeId())
				.eq(tenantBookQueryParam.getBookChargeEmployeeId() != null, TenantBook::getBookChargeEmployeeId, tenantBookQueryParam.getBookChargeEmployeeId())
				.eq(tenantBookQueryParam.getBookMarketingAreaId() != null, TenantBook::getBookMarketingAreaId, tenantBookQueryParam.getBookMarketingAreaId())
				.eq(tenantBookQueryParam.getBookReadCycle() != null, TenantBook::getBookReadCycle, tenantBookQueryParam.getBookReadCycle())
				.eq(tenantBookQueryParam.getBookLastMonth() != null, TenantBook::getBookLastMonth, tenantBookQueryParam.getBookLastMonth())
				.eq(tenantBookQueryParam.getBookReadMonth() != null, TenantBook::getBookReadMonth, tenantBookQueryParam.getBookReadMonth())
				.eq(tenantBookQueryParam.getBookSettleCycle() != null, TenantBook::getBookSettleCycle, tenantBookQueryParam.getBookSettleCycle())
				.eq(tenantBookQueryParam.getBookSettleLastMonth() != null, TenantBook::getBookSettleLastMonth, tenantBookQueryParam.getBookSettleLastMonth())
				.eq(tenantBookQueryParam.getBookSettleMonth() != null, TenantBook::getBookSettleMonth, tenantBookQueryParam.getBookSettleMonth())
				.eq(tenantBookQueryParam.getBookStatus() != null, TenantBook::getBookStatus, tenantBookQueryParam.getBookStatus())
				.eq(tenantBookQueryParam.getBookReadStatus() != null, TenantBook::getBookReadStatus, tenantBookQueryParam.getBookReadStatus())
				.eq(tenantBookQueryParam.getPriceCalss() != null, TenantBook::getPriceCalss, tenantBookQueryParam.getPriceCalss())
				.eq(tenantBookQueryParam.getPriceMemo() != null, TenantBook::getPriceMemo, tenantBookQueryParam.getPriceMemo())
				.eq(tenantBookQueryParam.getAddTime() != null, TenantBook::getAddTime, tenantBookQueryParam.getAddTime())
				.ge(tenantBookQueryParam.getAddTimeStart() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getAddTimeStart()))
				.le(tenantBookQueryParam.getAddTimeEnd() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getAddTimeEnd()))
				.eq(tenantBookQueryParam.getUpdateTime() != null, TenantBook::getUpdateTime, tenantBookQueryParam.getUpdateTime())
				.ge(tenantBookQueryParam.getUpdateTimeStart() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getUpdateTimeStart()))
				.le(tenantBookQueryParam.getUpdateTimeEnd() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantBook> tenantBookPage = tenantBookService.page(pageTenantBook, queryWrapperTenantBook);

		Page<TenantBookVo> tenantBookVoPage = new Page<TenantBookVo>(page, rows);
		tenantBookVoPage.setCurrent(tenantBookPage.getCurrent());
		tenantBookVoPage.setPages(tenantBookPage.getPages());
		tenantBookVoPage.setSize(tenantBookPage.getSize());
		tenantBookVoPage.setTotal(tenantBookPage.getTotal());
		tenantBookVoPage.setRecords(tenantBookPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantBookVoPage;
	}
	
	@ApiOperation(value = "根据参数查询表册信息统计")
	@RequestMapping(value = "/tenant-books/aggregation", method = RequestMethod.GET)
	public TenantBookVo aggregation(@RequestBody TenantBookQueryParam tenantBookQueryParam) {
		QueryWrapper<TenantBook> queryWrapperTenantBook = new QueryWrapper<TenantBook>();
		queryWrapperTenantBook.lambda()
				.eq(tenantBookQueryParam.getId() != null, TenantBook::getId, tenantBookQueryParam.getId())
				.eq(tenantBookQueryParam.getTenantId() != null, TenantBook::getTenantId, tenantBookQueryParam.getTenantId())
				.eq(tenantBookQueryParam.getBookCode() != null, TenantBook::getBookCode, tenantBookQueryParam.getBookCode())
				.eq(tenantBookQueryParam.getBookName() != null, TenantBook::getBookName, tenantBookQueryParam.getBookName())
				.eq(tenantBookQueryParam.getBookReaderEmployeeId() != null, TenantBook::getBookReaderEmployeeId, tenantBookQueryParam.getBookReaderEmployeeId())
				.eq(tenantBookQueryParam.getBookChargeEmployeeId() != null, TenantBook::getBookChargeEmployeeId, tenantBookQueryParam.getBookChargeEmployeeId())
				.eq(tenantBookQueryParam.getBookMarketingAreaId() != null, TenantBook::getBookMarketingAreaId, tenantBookQueryParam.getBookMarketingAreaId())
				.eq(tenantBookQueryParam.getBookReadCycle() != null, TenantBook::getBookReadCycle, tenantBookQueryParam.getBookReadCycle())
				.eq(tenantBookQueryParam.getBookLastMonth() != null, TenantBook::getBookLastMonth, tenantBookQueryParam.getBookLastMonth())
				.eq(tenantBookQueryParam.getBookReadMonth() != null, TenantBook::getBookReadMonth, tenantBookQueryParam.getBookReadMonth())
				.eq(tenantBookQueryParam.getBookSettleCycle() != null, TenantBook::getBookSettleCycle, tenantBookQueryParam.getBookSettleCycle())
				.eq(tenantBookQueryParam.getBookSettleLastMonth() != null, TenantBook::getBookSettleLastMonth, tenantBookQueryParam.getBookSettleLastMonth())
				.eq(tenantBookQueryParam.getBookSettleMonth() != null, TenantBook::getBookSettleMonth, tenantBookQueryParam.getBookSettleMonth())
				.eq(tenantBookQueryParam.getBookStatus() != null, TenantBook::getBookStatus, tenantBookQueryParam.getBookStatus())
				.eq(tenantBookQueryParam.getBookReadStatus() != null, TenantBook::getBookReadStatus, tenantBookQueryParam.getBookReadStatus())
				.eq(tenantBookQueryParam.getPriceCalss() != null, TenantBook::getPriceCalss, tenantBookQueryParam.getPriceCalss())
				.eq(tenantBookQueryParam.getPriceMemo() != null, TenantBook::getPriceMemo, tenantBookQueryParam.getPriceMemo())
				.eq(tenantBookQueryParam.getAddTime() != null, TenantBook::getAddTime, tenantBookQueryParam.getAddTime())
				.ge(tenantBookQueryParam.getAddTimeStart() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getAddTimeStart()))
				.le(tenantBookQueryParam.getAddTimeEnd() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getAddTimeEnd()))
				.eq(tenantBookQueryParam.getUpdateTime() != null, TenantBook::getUpdateTime, tenantBookQueryParam.getUpdateTime())
				.ge(tenantBookQueryParam.getUpdateTimeStart() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getUpdateTimeStart()))
				.le(tenantBookQueryParam.getUpdateTimeEnd() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getUpdateTimeEnd()))
				;

		TenantBook tenantBook = tenantBookService.getAggregation(queryWrapperTenantBook);
		
		return entity2vo(tenantBook);
	}

	@ApiOperation(value = "新增表册信息")
	@RequestMapping(value = "/tenant-books", method = RequestMethod.POST)
	public String save(@RequestBody TenantBookAddParam tenantBookAddParam) {
		return tenantBookService.save(tenantBookAddParam);
	}

	@ApiOperation(value = "更新表册信息全部信息")
	@RequestMapping(value = "/tenant-books/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantBookUpdateParam tenantBookUpdateParam) {
		tenantBookUpdateParam.setId(id);
		return tenantBookService.updateById(tenantBookUpdateParam);
	}

	@ApiOperation(value = "根据参数更新表册信息信息")
	@RequestMapping(value = "/tenant-books/{id}", method = RequestMethod.PATCH)
	public TenantBookVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantBook tenantBook) {
        TenantBook tenantBookWhere = TenantBook.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantBook> updateWrapperTenantBook = new UpdateWrapper<TenantBook>();
		updateWrapperTenantBook.setEntity(tenantBookWhere);
		updateWrapperTenantBook.lambda()//
				//.eq(TenantBook::getId, id)
				// .set(tenantBook.getId() != null, TenantBook::getId, tenantBook.getId())
				.set(tenantBook.getTenantId() != null, TenantBook::getTenantId, tenantBook.getTenantId())
				.set(tenantBook.getBookCode() != null, TenantBook::getBookCode, tenantBook.getBookCode())
				.set(tenantBook.getBookName() != null, TenantBook::getBookName, tenantBook.getBookName())
				.set(tenantBook.getBookReaderEmployeeId() != null, TenantBook::getBookReaderEmployeeId, tenantBook.getBookReaderEmployeeId())
				.set(tenantBook.getBookChargeEmployeeId() != null, TenantBook::getBookChargeEmployeeId, tenantBook.getBookChargeEmployeeId())
				.set(tenantBook.getBookMarketingAreaId() != null, TenantBook::getBookMarketingAreaId, tenantBook.getBookMarketingAreaId())
				.set(tenantBook.getBookReadCycle() != null, TenantBook::getBookReadCycle, tenantBook.getBookReadCycle())
				.set(tenantBook.getBookLastMonth() != null, TenantBook::getBookLastMonth, tenantBook.getBookLastMonth())
				.set(tenantBook.getBookReadMonth() != null, TenantBook::getBookReadMonth, tenantBook.getBookReadMonth())
				.set(tenantBook.getBookSettleCycle() != null, TenantBook::getBookSettleCycle, tenantBook.getBookSettleCycle())
				.set(tenantBook.getBookSettleLastMonth() != null, TenantBook::getBookSettleLastMonth, tenantBook.getBookSettleLastMonth())
				.set(tenantBook.getBookSettleMonth() != null, TenantBook::getBookSettleMonth, tenantBook.getBookSettleMonth())
				.set(tenantBook.getBookStatus() != null, TenantBook::getBookStatus, tenantBook.getBookStatus())
				.set(tenantBook.getBookReadStatus() != null, TenantBook::getBookReadStatus, tenantBook.getBookReadStatus())
				.set(tenantBook.getPriceCalss() != null, TenantBook::getPriceCalss, tenantBook.getPriceCalss())
				.set(tenantBook.getPriceMemo() != null, TenantBook::getPriceMemo, tenantBook.getPriceMemo())
				.set(tenantBook.getAddTime() != null, TenantBook::getAddTime, tenantBook.getAddTime())
				.set(tenantBook.getUpdateTime() != null, TenantBook::getUpdateTime, tenantBook.getUpdateTime())
				;

		boolean success = tenantBookService.update(updateWrapperTenantBook);
		if (success) {
			TenantBook tenantBookDatabase = tenantBookService.getById(id);
			return entity2vo(tenantBookDatabase);
		}
		log.info("partial update TenantBook fail，{}",
				ToStringBuilder.reflectionToString(tenantBook, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除表册信息")
	@RequestMapping(value = "/tenant-books/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantBookService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantBookVo entity2vo(TenantBook tenantBook) {
		if (tenantBook == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantBook);
		TenantBookVo tenantBookVo = JSON.parseObject(jsonString, TenantBookVo.class);
		if (StringUtils.isEmpty(tenantBookVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantBook.getTenantId());
			if (tenantInfo != null) {
				tenantBookVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantBookVo;
	}

}
