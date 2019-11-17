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
import com.zlsrj.wms.api.dto.TenantSystemPriceQueryParam;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantSystemPrice;
import com.zlsrj.wms.api.vo.TenantSystemPriceVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ISystemDesignService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantSystemPriceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户模块价格", tags = { "租户模块价格操作接口" })
@RestController
@Slf4j
public class TenantSystemPriceRestController {

	@Autowired
	private ITenantSystemPriceService tenantSystemPriceService;
	@Autowired
	private ISystemDesignService systemDesignService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户模块价格")
	@RequestMapping(value = "/tenant-system-prices/{id}", method = RequestMethod.GET)
	public TenantSystemPriceVo getById(@PathVariable("id") Long id) {
		TenantSystemPrice tenantSystemPrice = tenantSystemPriceService.getById(id);

		return entity2vo(tenantSystemPrice);
	}

	@ApiOperation(value = "根据参数查询租户模块价格列表")
	@RequestMapping(value = "/tenant-system-prices", method = RequestMethod.GET)
	public Page<TenantSystemPriceVo> page(@RequestBody TenantSystemPriceQueryParam tenantSystemPriceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantSystemPrice> pageTenantSystemPrice = new Page<TenantSystemPrice>(page, rows);
		QueryWrapper<TenantSystemPrice> queryWrapperTenantSystemPrice = new QueryWrapper<TenantSystemPrice>();
		queryWrapperTenantSystemPrice.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantSystemPrice.lambda()
						.eq(tenantSystemPriceQueryParam.getId() != null, TenantSystemPrice::getId, tenantSystemPriceQueryParam.getId())
						.eq(tenantSystemPriceQueryParam.getTenantId() != null, TenantSystemPrice::getTenantId, tenantSystemPriceQueryParam.getTenantId())
						.eq(tenantSystemPriceQueryParam.getSysId() != null, TenantSystemPrice::getSysId, tenantSystemPriceQueryParam.getSysId())
						.eq(tenantSystemPriceQueryParam.getSysEdition() != null, TenantSystemPrice::getSysEdition, tenantSystemPriceQueryParam.getSysEdition())
						.eq(tenantSystemPriceQueryParam.getStartNum() != null, TenantSystemPrice::getStartNum, tenantSystemPriceQueryParam.getStartNum())
						.eq(tenantSystemPriceQueryParam.getEndNum() != null, TenantSystemPrice::getEndNum, tenantSystemPriceQueryParam.getEndNum())
						.eq(tenantSystemPriceQueryParam.getPrice() != null, TenantSystemPrice::getPrice, tenantSystemPriceQueryParam.getPrice())
				;

		IPage<TenantSystemPrice> tenantSystemPricePage = tenantSystemPriceService.page(pageTenantSystemPrice, queryWrapperTenantSystemPrice);

		Page<TenantSystemPriceVo> tenantSystemPriceVoPage = new Page<TenantSystemPriceVo>(page, rows);
		tenantSystemPriceVoPage.setCurrent(tenantSystemPricePage.getCurrent());
		tenantSystemPriceVoPage.setPages(tenantSystemPricePage.getPages());
		tenantSystemPriceVoPage.setSize(tenantSystemPricePage.getSize());
		tenantSystemPriceVoPage.setTotal(tenantSystemPricePage.getTotal());
		tenantSystemPriceVoPage.setRecords(tenantSystemPricePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantSystemPriceVoPage;
	}

	@ApiOperation(value = "新增租户模块价格")
	@RequestMapping(value = "/tenant-system-prices", method = RequestMethod.POST)
	public TenantSystemPriceVo save(@RequestBody TenantSystemPrice tenantSystemPrice) {
		if (tenantSystemPrice.getId() == null || tenantSystemPrice.getId().compareTo(0L) <= 0) {
			tenantSystemPrice.setId(idService.selectId());
		}
		boolean success = tenantSystemPriceService.save(tenantSystemPrice);
		if (success) {
			TenantSystemPrice tenantSystemPriceDatabase = tenantSystemPriceService.getById(tenantSystemPrice.getId());
			return entity2vo(tenantSystemPriceDatabase);
		}
		log.info("save TenantSystemPrice fail，{}", ToStringBuilder.reflectionToString(tenantSystemPrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户模块价格全部信息")
	@RequestMapping(value = "/tenant-system-prices/{id}", method = RequestMethod.PUT)
	public TenantSystemPriceVo updateById(@PathVariable("id") Long id, @RequestBody TenantSystemPrice tenantSystemPrice) {
		tenantSystemPrice.setId(id);
		boolean success = tenantSystemPriceService.updateById(tenantSystemPrice);
		if (success) {
			TenantSystemPrice tenantSystemPriceDatabase = tenantSystemPriceService.getById(id);
			return entity2vo(tenantSystemPriceDatabase);
		}
		log.info("update TenantSystemPrice fail，{}", ToStringBuilder.reflectionToString(tenantSystemPrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户模块价格信息")
	@RequestMapping(value = "/tenant-system-prices/{id}", method = RequestMethod.PATCH)
	public TenantSystemPriceVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantSystemPrice tenantSystemPrice) {
		UpdateWrapper<TenantSystemPrice> updateWrapperTenantSystemPrice = new UpdateWrapper<TenantSystemPrice>();
		updateWrapperTenantSystemPrice.lambda()//
				.eq(TenantSystemPrice::getId, id)
				// .set(tenantSystemPrice.getId() != null, TenantSystemPrice::getId, tenantSystemPrice.getId())
				.set(tenantSystemPrice.getTenantId() != null, TenantSystemPrice::getTenantId, tenantSystemPrice.getTenantId())
				.set(tenantSystemPrice.getSysId() != null, TenantSystemPrice::getSysId, tenantSystemPrice.getSysId())
				.set(tenantSystemPrice.getSysEdition() != null, TenantSystemPrice::getSysEdition, tenantSystemPrice.getSysEdition())
				.set(tenantSystemPrice.getStartNum() != null, TenantSystemPrice::getStartNum, tenantSystemPrice.getStartNum())
				.set(tenantSystemPrice.getEndNum() != null, TenantSystemPrice::getEndNum, tenantSystemPrice.getEndNum())
				.set(tenantSystemPrice.getPrice() != null, TenantSystemPrice::getPrice, tenantSystemPrice.getPrice())
				;

		boolean success = tenantSystemPriceService.update(updateWrapperTenantSystemPrice);
		if (success) {
			TenantSystemPrice tenantSystemPriceDatabase = tenantSystemPriceService.getById(id);
			return entity2vo(tenantSystemPriceDatabase);
		}
		log.info("partial update TenantSystemPrice fail，{}",
				ToStringBuilder.reflectionToString(tenantSystemPrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户模块价格")
	@RequestMapping(value = "/tenant-system-prices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantSystemPriceService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantSystemPriceVo entity2vo(TenantSystemPrice tenantSystemPrice) {
		String jsonString = JSON.toJSONString(tenantSystemPrice);
		TenantSystemPriceVo tenantSystemPriceVo = JSON.parseObject(jsonString, TenantSystemPriceVo.class);
		if (StringUtils.isEmpty(tenantSystemPriceVo.getModuleName())) {
			SystemDesign systemDesign = systemDesignService.getById(tenantSystemPrice.getSysId());
			if (systemDesign != null) {
				tenantSystemPriceVo.setModuleName(systemDesign.getModuleName());
			}
		}
		if (StringUtils.isEmpty(tenantSystemPriceVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantSystemPrice.getTenantId());
			if (tenantInfo != null) {
				tenantSystemPriceVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantSystemPriceVo;
	}

}
