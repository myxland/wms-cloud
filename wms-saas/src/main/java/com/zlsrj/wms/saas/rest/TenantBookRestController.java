package com.zlsrj.wms.saas.rest;

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
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantBookQueryParam;
import com.zlsrj.wms.api.dto.TenantBookUpdateParam;
import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.vo.TenantBookVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantBookService;
import com.zlsrj.wms.saas.service.ITenantInfoService;

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
				.apply("=".equals(tenantBookQueryParam.getQueryType()) && StringUtils.isNotBlank(tenantBookQueryParam.getQueryCol()), tenantBookQueryParam.getQueryCol()+"={0}", tenantBookQueryParam.getQueryValue())
				.apply("like".equals(tenantBookQueryParam.getQueryType()) && StringUtils.isNotBlank(tenantBookQueryParam.getQueryCol()), tenantBookQueryParam.getQueryCol()+" like CONCAT('%',{0},'%')", tenantBookQueryParam.getQueryValue())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getId()), TenantBook::getId, tenantBookQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getTenantId()), TenantBook::getTenantId, tenantBookQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookCode()), TenantBook::getBookCode, tenantBookQueryParam.getBookCode())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookName()), TenantBook::getBookName, tenantBookQueryParam.getBookName())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookReaderEmployeeId()), TenantBook::getBookReaderEmployeeId, tenantBookQueryParam.getBookReaderEmployeeId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookChargeEmployeeId()), TenantBook::getBookChargeEmployeeId, tenantBookQueryParam.getBookChargeEmployeeId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookMarketingAreaId()), TenantBook::getBookMarketingAreaId, tenantBookQueryParam.getBookMarketingAreaId())
				.eq(tenantBookQueryParam.getBookReadCycle() != null, TenantBook::getBookReadCycle, tenantBookQueryParam.getBookReadCycle())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookLastMonth()), TenantBook::getBookLastMonth, tenantBookQueryParam.getBookLastMonth())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookReadMonth()), TenantBook::getBookReadMonth, tenantBookQueryParam.getBookReadMonth())
				.eq(tenantBookQueryParam.getBookSettleCycle() != null, TenantBook::getBookSettleCycle, tenantBookQueryParam.getBookSettleCycle())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookSettleLastMonth()), TenantBook::getBookSettleLastMonth, tenantBookQueryParam.getBookSettleLastMonth())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookSettleMonth()), TenantBook::getBookSettleMonth, tenantBookQueryParam.getBookSettleMonth())
				.eq(tenantBookQueryParam.getBookStatus() != null, TenantBook::getBookStatus, tenantBookQueryParam.getBookStatus())
				.eq(tenantBookQueryParam.getBookReadStatus() != null, TenantBook::getBookReadStatus, tenantBookQueryParam.getBookReadStatus())
				.eq(tenantBookQueryParam.getPriceClass() != null, TenantBook::getPriceClass, tenantBookQueryParam.getPriceClass())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getPriceMemo()), TenantBook::getPriceMemo, tenantBookQueryParam.getPriceMemo())
				.eq(tenantBookQueryParam.getAddTime() != null, TenantBook::getAddTime, tenantBookQueryParam.getAddTime())
				.ge(tenantBookQueryParam.getAddTimeStart() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getAddTimeStart()))
				.le(tenantBookQueryParam.getAddTimeEnd() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getAddTimeEnd()))
				.eq(tenantBookQueryParam.getUpdateTime() != null, TenantBook::getUpdateTime, tenantBookQueryParam.getUpdateTime())
				.ge(tenantBookQueryParam.getUpdateTimeStart() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getUpdateTimeStart()))
				.le(tenantBookQueryParam.getUpdateTimeEnd() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getUpdateTimeEnd()))
				;

		List<TenantBook> tenantBookList = tenantBookService.list(queryWrapperTenantBook);

		List<TenantBookVo> tenantBookVoList = tenantBookList.stream()//
				 .map(e -> entity2vo(e))//
				 .collect(Collectors.toList());

		return tenantBookVoList;
	}
	
	@ApiOperation(value = "根据参数查询表册信息数量")
	@RequestMapping(value = "/tenant-books/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantBookQueryParam tenantBookQueryParam) {
		QueryWrapper<TenantBook> queryWrapperTenantBook = new QueryWrapper<TenantBook>();
		queryWrapperTenantBook.lambda()
				.apply("=".equals(tenantBookQueryParam.getQueryType()) && StringUtils.isNotBlank(tenantBookQueryParam.getQueryCol()), tenantBookQueryParam.getQueryCol()+"={0}", tenantBookQueryParam.getQueryValue())
				.apply("like".equals(tenantBookQueryParam.getQueryType()) && StringUtils.isNotBlank(tenantBookQueryParam.getQueryCol()), tenantBookQueryParam.getQueryCol()+" like CONCAT('%',{0},'%')", tenantBookQueryParam.getQueryValue())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getId()), TenantBook::getId, tenantBookQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getTenantId()), TenantBook::getTenantId, tenantBookQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookCode()), TenantBook::getBookCode, tenantBookQueryParam.getBookCode())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookName()), TenantBook::getBookName, tenantBookQueryParam.getBookName())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookReaderEmployeeId()), TenantBook::getBookReaderEmployeeId, tenantBookQueryParam.getBookReaderEmployeeId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookChargeEmployeeId()), TenantBook::getBookChargeEmployeeId, tenantBookQueryParam.getBookChargeEmployeeId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookMarketingAreaId()), TenantBook::getBookMarketingAreaId, tenantBookQueryParam.getBookMarketingAreaId())
				.eq(tenantBookQueryParam.getBookReadCycle() != null, TenantBook::getBookReadCycle, tenantBookQueryParam.getBookReadCycle())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookLastMonth()), TenantBook::getBookLastMonth, tenantBookQueryParam.getBookLastMonth())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookReadMonth()), TenantBook::getBookReadMonth, tenantBookQueryParam.getBookReadMonth())
				.eq(tenantBookQueryParam.getBookSettleCycle() != null, TenantBook::getBookSettleCycle, tenantBookQueryParam.getBookSettleCycle())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookSettleLastMonth()), TenantBook::getBookSettleLastMonth, tenantBookQueryParam.getBookSettleLastMonth())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookSettleMonth()), TenantBook::getBookSettleMonth, tenantBookQueryParam.getBookSettleMonth())
				.eq(tenantBookQueryParam.getBookStatus() != null, TenantBook::getBookStatus, tenantBookQueryParam.getBookStatus())
				.eq(tenantBookQueryParam.getBookReadStatus() != null, TenantBook::getBookReadStatus, tenantBookQueryParam.getBookReadStatus())
				.eq(tenantBookQueryParam.getPriceClass() != null, TenantBook::getPriceClass, tenantBookQueryParam.getPriceClass())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getPriceMemo()), TenantBook::getPriceMemo, tenantBookQueryParam.getPriceMemo())
				.eq(tenantBookQueryParam.getAddTime() != null, TenantBook::getAddTime, tenantBookQueryParam.getAddTime())
				.ge(tenantBookQueryParam.getAddTimeStart() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getAddTimeStart()))
				.le(tenantBookQueryParam.getAddTimeEnd() != null, TenantBook::getAddTime,tenantBookQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getAddTimeEnd()))
				.eq(tenantBookQueryParam.getUpdateTime() != null, TenantBook::getUpdateTime, tenantBookQueryParam.getUpdateTime())
				.ge(tenantBookQueryParam.getUpdateTimeStart() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantBookQueryParam.getUpdateTimeStart()))
				.le(tenantBookQueryParam.getUpdateTimeEnd() != null, TenantBook::getUpdateTime,tenantBookQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantBookQueryParam.getUpdateTimeEnd()))
				;

		int count = tenantBookService.count(queryWrapperTenantBook);

		return count;
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
		queryWrapperTenantBook.orderBy(StringUtils.isNotBlank(sort), "asc".equals(order), sort);
		queryWrapperTenantBook.lambda()
				.apply("=".equals(tenantBookQueryParam.getQueryType()) && StringUtils.isNotBlank(tenantBookQueryParam.getQueryCol()), tenantBookQueryParam.getQueryCol()+"={0}", tenantBookQueryParam.getQueryValue())
				.apply("like".equals(tenantBookQueryParam.getQueryType()) && StringUtils.isNotBlank(tenantBookQueryParam.getQueryCol()), tenantBookQueryParam.getQueryCol()+" like CONCAT('%',{0},'%')", tenantBookQueryParam.getQueryValue())		
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getId()), TenantBook::getId, tenantBookQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getTenantId()), TenantBook::getTenantId, tenantBookQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookCode()), TenantBook::getBookCode, tenantBookQueryParam.getBookCode())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookName()), TenantBook::getBookName, tenantBookQueryParam.getBookName())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookReaderEmployeeId()), TenantBook::getBookReaderEmployeeId, tenantBookQueryParam.getBookReaderEmployeeId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookChargeEmployeeId()), TenantBook::getBookChargeEmployeeId, tenantBookQueryParam.getBookChargeEmployeeId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookMarketingAreaId()), TenantBook::getBookMarketingAreaId, tenantBookQueryParam.getBookMarketingAreaId())
				.eq(tenantBookQueryParam.getBookReadCycle() != null, TenantBook::getBookReadCycle, tenantBookQueryParam.getBookReadCycle())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookLastMonth()), TenantBook::getBookLastMonth, tenantBookQueryParam.getBookLastMonth())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookReadMonth()), TenantBook::getBookReadMonth, tenantBookQueryParam.getBookReadMonth())
				.eq(tenantBookQueryParam.getBookSettleCycle() != null, TenantBook::getBookSettleCycle, tenantBookQueryParam.getBookSettleCycle())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookSettleLastMonth()), TenantBook::getBookSettleLastMonth, tenantBookQueryParam.getBookSettleLastMonth())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookSettleMonth()), TenantBook::getBookSettleMonth, tenantBookQueryParam.getBookSettleMonth())
				.eq(tenantBookQueryParam.getBookStatus() != null, TenantBook::getBookStatus, tenantBookQueryParam.getBookStatus())
				.eq(tenantBookQueryParam.getBookReadStatus() != null, TenantBook::getBookReadStatus, tenantBookQueryParam.getBookReadStatus())
				.eq(tenantBookQueryParam.getPriceClass() != null, TenantBook::getPriceClass, tenantBookQueryParam.getPriceClass())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getPriceMemo()), TenantBook::getPriceMemo, tenantBookQueryParam.getPriceMemo())
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
				.apply("=".equals(tenantBookQueryParam.getQueryType()) && StringUtils.isNotBlank(tenantBookQueryParam.getQueryCol()), tenantBookQueryParam.getQueryCol()+"={0}", tenantBookQueryParam.getQueryValue())
				.apply("like".equals(tenantBookQueryParam.getQueryType()) && StringUtils.isNotBlank(tenantBookQueryParam.getQueryCol()), tenantBookQueryParam.getQueryCol()+" like CONCAT('%',{0},'%')", tenantBookQueryParam.getQueryValue())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getId()), TenantBook::getId, tenantBookQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getTenantId()), TenantBook::getTenantId, tenantBookQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookCode()), TenantBook::getBookCode, tenantBookQueryParam.getBookCode())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookName()), TenantBook::getBookName, tenantBookQueryParam.getBookName())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookReaderEmployeeId()), TenantBook::getBookReaderEmployeeId, tenantBookQueryParam.getBookReaderEmployeeId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookChargeEmployeeId()), TenantBook::getBookChargeEmployeeId, tenantBookQueryParam.getBookChargeEmployeeId())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookMarketingAreaId()), TenantBook::getBookMarketingAreaId, tenantBookQueryParam.getBookMarketingAreaId())
				.eq(tenantBookQueryParam.getBookReadCycle() != null, TenantBook::getBookReadCycle, tenantBookQueryParam.getBookReadCycle())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookLastMonth()), TenantBook::getBookLastMonth, tenantBookQueryParam.getBookLastMonth())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookReadMonth()), TenantBook::getBookReadMonth, tenantBookQueryParam.getBookReadMonth())
				.eq(tenantBookQueryParam.getBookSettleCycle() != null, TenantBook::getBookSettleCycle, tenantBookQueryParam.getBookSettleCycle())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookSettleLastMonth()), TenantBook::getBookSettleLastMonth, tenantBookQueryParam.getBookSettleLastMonth())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getBookSettleMonth()), TenantBook::getBookSettleMonth, tenantBookQueryParam.getBookSettleMonth())
				.eq(tenantBookQueryParam.getBookStatus() != null, TenantBook::getBookStatus, tenantBookQueryParam.getBookStatus())
				.eq(tenantBookQueryParam.getBookReadStatus() != null, TenantBook::getBookReadStatus, tenantBookQueryParam.getBookReadStatus())
				.eq(tenantBookQueryParam.getPriceClass() != null, TenantBook::getPriceClass, tenantBookQueryParam.getPriceClass())
				.eq(StringUtils.isNotEmpty(tenantBookQueryParam.getPriceMemo()), TenantBook::getPriceMemo, tenantBookQueryParam.getPriceMemo())
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
	
	@ApiOperation(value = "批量更新表册信息营销区域")
	@RequestMapping(value = "/tenant-books/marketingArea/{ids}", method = RequestMethod.PUT)
	public boolean updateByIds(@PathVariable("ids") String ids, @RequestBody TenantBookBatchUpdateParam tenantBookBatchUpdateParam) {
		tenantBookBatchUpdateParam.setIds(ids);
		return tenantBookService.updateByIds(tenantBookBatchUpdateParam);
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

		TenantBookVo tenantBookVo = TranslateUtil.translate(tenantBook, TenantBookVo.class);
		if (StringUtils.isEmpty(tenantBookVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantBook.getTenantId());
			if (tenantInfo != null) {
				tenantBookVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantBookVo;
	}

}
