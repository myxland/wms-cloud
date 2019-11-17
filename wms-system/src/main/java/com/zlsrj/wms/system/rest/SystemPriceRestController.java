package com.zlsrj.wms.system.rest;

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
import com.zlsrj.wms.api.dto.SystemPriceQueryParam;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.api.entity.SystemPrice;
import com.zlsrj.wms.api.vo.SystemPriceVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.system.service.IIdService;
import com.zlsrj.wms.system.service.ISystemDesignService;
import com.zlsrj.wms.system.service.ISystemPriceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块各版本价格定义表", tags = { "模块各版本价格定义表操作接口" })
@RestController
@Slf4j
public class SystemPriceRestController {

	@Autowired
	private ISystemPriceService systemPriceService;
	@Autowired
	private ISystemDesignService systemDesignService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询模块各版本价格定义表")
	@RequestMapping(value = "/system-prices/{id}", method = RequestMethod.GET)
	public SystemPriceVo getById(@PathVariable("id") Long id) {
		SystemPrice systemPrice = systemPriceService.getById(id);

		return entity2vo(systemPrice);
	}

	@ApiOperation(value = "根据参数查询模块各版本价格定义表列表")
	@RequestMapping(value = "/system-prices", method = RequestMethod.GET)
	public Page<SystemPriceVo> page(@RequestBody SystemPriceQueryParam systemPriceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<SystemPrice> pageSystemPrice = new Page<SystemPrice>(page, rows);
		QueryWrapper<SystemPrice> queryWrapperSystemPrice = new QueryWrapper<SystemPrice>();
		queryWrapperSystemPrice.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperSystemPrice.lambda()
				.eq(systemPriceQueryParam.getId() != null, SystemPrice::getId, systemPriceQueryParam.getId())
				.eq(systemPriceQueryParam.getSysId() != null, SystemPrice::getSysId, systemPriceQueryParam.getSysId())
				.eq(systemPriceQueryParam.getSysEdition() != null, SystemPrice::getSysEdition, systemPriceQueryParam.getSysEdition())
				.eq(systemPriceQueryParam.getStartNum() != null, SystemPrice::getStartNum, systemPriceQueryParam.getStartNum())
				.eq(systemPriceQueryParam.getEndNum() != null, SystemPrice::getEndNum, systemPriceQueryParam.getEndNum())
				.eq(systemPriceQueryParam.getPrice() != null, SystemPrice::getPrice, systemPriceQueryParam.getPrice())
				;

		IPage<SystemPrice> systemPricePage = systemPriceService.page(pageSystemPrice, queryWrapperSystemPrice);

		Page<SystemPriceVo> systemPriceVoPage = new Page<SystemPriceVo>(page, rows);
		systemPriceVoPage.setCurrent(systemPricePage.getCurrent());
		systemPriceVoPage.setPages(systemPricePage.getPages());
		systemPriceVoPage.setSize(systemPricePage.getSize());
		systemPriceVoPage.setTotal(systemPricePage.getTotal());
		systemPriceVoPage.setRecords(systemPricePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return systemPriceVoPage;
	}

	@ApiOperation(value = "新增模块各版本价格定义表")
	@RequestMapping(value = "/system-prices", method = RequestMethod.POST)
	public SystemPriceVo save(@RequestBody SystemPrice systemPrice) {
		if (systemPrice.getId() == null || systemPrice.getId().compareTo(0L) <= 0) {
			systemPrice.setId(idService.selectId());
		}
		boolean success = systemPriceService.save(systemPrice);
		if (success) {
			SystemPrice systemPriceDatabase = systemPriceService.getById(systemPrice.getId());
			return entity2vo(systemPriceDatabase);
		}
		log.info("save SystemPrice fail，{}", ToStringBuilder.reflectionToString(systemPrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新模块各版本价格定义表全部信息")
	@RequestMapping(value = "/system-prices/{id}", method = RequestMethod.PUT)
	public SystemPriceVo updateById(@PathVariable("id") Long id, @RequestBody SystemPrice systemPrice) {
		systemPrice.setId(id);
		boolean success = systemPriceService.updateById(systemPrice);
		if (success) {
			SystemPrice systemPriceDatabase = systemPriceService.getById(id);
			return entity2vo(systemPriceDatabase);
		}
		log.info("update SystemPrice fail，{}", ToStringBuilder.reflectionToString(systemPrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新模块各版本价格定义表信息")
	@RequestMapping(value = "/system-prices/{id}", method = RequestMethod.PATCH)
	public SystemPriceVo updatePatchById(@PathVariable("id") Long id, @RequestBody SystemPrice systemPrice) {
		UpdateWrapper<SystemPrice> updateWrapperSystemPrice = new UpdateWrapper<SystemPrice>();
		updateWrapperSystemPrice.lambda()//
				.eq(SystemPrice::getId, id)
				// .set(systemPrice.getId() != null, SystemPrice::getId, systemPrice.getId())
				.set(systemPrice.getSysId() != null, SystemPrice::getSysId, systemPrice.getSysId())
				.set(systemPrice.getSysEdition() != null, SystemPrice::getSysEdition, systemPrice.getSysEdition())
				.set(systemPrice.getStartNum() != null, SystemPrice::getStartNum, systemPrice.getStartNum())
				.set(systemPrice.getEndNum() != null, SystemPrice::getEndNum, systemPrice.getEndNum())
				.set(systemPrice.getPrice() != null, SystemPrice::getPrice, systemPrice.getPrice())
				;

		boolean success = systemPriceService.update(updateWrapperSystemPrice);
		if (success) {
			SystemPrice systemPriceDatabase = systemPriceService.getById(id);
			return entity2vo(systemPriceDatabase);
		}
		log.info("partial update SystemPrice fail，{}",
				ToStringBuilder.reflectionToString(systemPrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除模块各版本价格定义表")
	@RequestMapping(value = "/system-prices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = systemPriceService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private SystemPriceVo entity2vo(SystemPrice systemPrice) {
		String jsonString = JSON.toJSONString(systemPrice);
		SystemPriceVo systemPriceVo = JSON.parseObject(jsonString, SystemPriceVo.class);
		if (StringUtils.isEmpty(systemPriceVo.getModuleName())) {
			SystemDesign systemDesign = systemDesignService.getById(systemPrice.getSysId());
			if (systemDesign != null) {
				systemPriceVo.setModuleName(systemDesign.getModuleName());
			}
		}
		return systemPriceVo;
	}

}
