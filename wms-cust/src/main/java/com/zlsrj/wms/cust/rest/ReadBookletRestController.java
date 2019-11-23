package com.zlsrj.wms.cust.rest;

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
import com.zlsrj.wms.api.dto.ReadBookletQueryParam;
import com.zlsrj.wms.api.entity.ReadBooklet;
import com.zlsrj.wms.api.vo.ReadBookletVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.cust.service.IIdService;
import com.zlsrj.wms.cust.service.IReadBookletService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "表册信息", tags = { "表册信息操作接口" })
@RestController
@Slf4j
public class ReadBookletRestController {

	@Autowired
	private IReadBookletService readBookletService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询表册信息")
	@RequestMapping(value = "/read-booklets/{id}", method = RequestMethod.GET)
	public ReadBookletVo getById(@PathVariable("id") Long id) {
		ReadBooklet readBooklet = readBookletService.getById(id);

		return entity2vo(readBooklet);
	}

	@ApiOperation(value = "根据参数查询表册信息列表")
	@RequestMapping(value = "/read-booklets", method = RequestMethod.GET)
	public Page<ReadBookletVo> page(@RequestBody ReadBookletQueryParam readBookletQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<ReadBooklet> pageReadBooklet = new Page<ReadBooklet>(page, rows);
		QueryWrapper<ReadBooklet> queryWrapperReadBooklet = new QueryWrapper<ReadBooklet>();
		queryWrapperReadBooklet.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperReadBooklet.lambda()
				.eq(readBookletQueryParam.getId() != null, ReadBooklet::getId, readBookletQueryParam.getId())
				.eq(readBookletQueryParam.getTenantId() != null, ReadBooklet::getTenantId, readBookletQueryParam.getTenantId())
				.eq(readBookletQueryParam.getBookletName() != null, ReadBooklet::getBookletName, readBookletQueryParam.getBookletName())
				.eq(readBookletQueryParam.getBookletType() != null, ReadBooklet::getBookletType, readBookletQueryParam.getBookletType())
				.eq(readBookletQueryParam.getReadEmpId() != null, ReadBooklet::getReadEmpId, readBookletQueryParam.getReadEmpId())
				.eq(readBookletQueryParam.getPayEmpId() != null, ReadBooklet::getPayEmpId, readBookletQueryParam.getPayEmpId())
				.eq(readBookletQueryParam.getCalcCycleInterval() != null, ReadBooklet::getCalcCycleInterval, readBookletQueryParam.getCalcCycleInterval())
				.eq(readBookletQueryParam.getCalcMonthLast() != null, ReadBooklet::getCalcMonthLast, readBookletQueryParam.getCalcMonthLast())
				.eq(readBookletQueryParam.getCalcMonthNext() != null, ReadBooklet::getCalcMonthNext, readBookletQueryParam.getCalcMonthNext())
				;

		IPage<ReadBooklet> readBookletPage = readBookletService.page(pageReadBooklet, queryWrapperReadBooklet);

		Page<ReadBookletVo> readBookletVoPage = new Page<ReadBookletVo>(page, rows);
		readBookletVoPage.setCurrent(readBookletPage.getCurrent());
		readBookletVoPage.setPages(readBookletPage.getPages());
		readBookletVoPage.setSize(readBookletPage.getSize());
		readBookletVoPage.setTotal(readBookletPage.getTotal());
		readBookletVoPage.setRecords(readBookletPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return readBookletVoPage;
	}

	@ApiOperation(value = "新增表册信息")
	@RequestMapping(value = "/read-booklets", method = RequestMethod.POST)
	public ReadBookletVo save(@RequestBody ReadBooklet readBooklet) {
		if (readBooklet.getId() == null || readBooklet.getId().compareTo(0L) <= 0) {
			readBooklet.setId(idService.selectId());
		}
		boolean success = readBookletService.save(readBooklet);
		if (success) {
			ReadBooklet readBookletDatabase = readBookletService.getById(readBooklet.getId());
			return entity2vo(readBookletDatabase);
		}
		log.info("save ReadBooklet fail，{}", ToStringBuilder.reflectionToString(readBooklet, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新表册信息全部信息")
	@RequestMapping(value = "/read-booklets/{id}", method = RequestMethod.PUT)
	public ReadBookletVo updateById(@PathVariable("id") Long id, @RequestBody ReadBooklet readBooklet) {
		readBooklet.setId(id);
		boolean success = readBookletService.updateById(readBooklet);
		if (success) {
			ReadBooklet readBookletDatabase = readBookletService.getById(id);
			return entity2vo(readBookletDatabase);
		}
		log.info("update ReadBooklet fail，{}", ToStringBuilder.reflectionToString(readBooklet, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新表册信息信息")
	@RequestMapping(value = "/read-booklets/{id}", method = RequestMethod.PATCH)
	public ReadBookletVo updatePatchById(@PathVariable("id") Long id, @RequestBody ReadBooklet readBooklet) {
		UpdateWrapper<ReadBooklet> updateWrapperReadBooklet = new UpdateWrapper<ReadBooklet>();
		updateWrapperReadBooklet.lambda()//
				.eq(ReadBooklet::getId, id)
				// .set(readBooklet.getId() != null, ReadBooklet::getId, readBooklet.getId())
				.set(readBooklet.getTenantId() != null, ReadBooklet::getTenantId, readBooklet.getTenantId())
				.set(readBooklet.getBookletName() != null, ReadBooklet::getBookletName, readBooklet.getBookletName())
				.set(readBooklet.getBookletType() != null, ReadBooklet::getBookletType, readBooklet.getBookletType())
				.set(readBooklet.getReadEmpId() != null, ReadBooklet::getReadEmpId, readBooklet.getReadEmpId())
				.set(readBooklet.getPayEmpId() != null, ReadBooklet::getPayEmpId, readBooklet.getPayEmpId())
				.set(readBooklet.getCalcCycleInterval() != null, ReadBooklet::getCalcCycleInterval, readBooklet.getCalcCycleInterval())
				.set(readBooklet.getCalcMonthLast() != null, ReadBooklet::getCalcMonthLast, readBooklet.getCalcMonthLast())
				.set(readBooklet.getCalcMonthNext() != null, ReadBooklet::getCalcMonthNext, readBooklet.getCalcMonthNext())
				;

		boolean success = readBookletService.update(updateWrapperReadBooklet);
		if (success) {
			ReadBooklet readBookletDatabase = readBookletService.getById(id);
			return entity2vo(readBookletDatabase);
		}
		log.info("partial update ReadBooklet fail，{}",
				ToStringBuilder.reflectionToString(readBooklet, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除表册信息")
	@RequestMapping(value = "/read-booklets/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = readBookletService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private ReadBookletVo entity2vo(ReadBooklet readBooklet) {
		String jsonString = JSON.toJSONString(readBooklet);
		ReadBookletVo readBookletVo = JSON.parseObject(jsonString, ReadBookletVo.class);
		return readBookletVo;
	}

}
