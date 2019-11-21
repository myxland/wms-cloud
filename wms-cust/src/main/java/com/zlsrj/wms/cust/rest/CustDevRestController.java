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
import com.zlsrj.wms.api.dto.CustDevQueryParam;
import com.zlsrj.wms.api.entity.CustDev;
import com.zlsrj.wms.api.vo.CustDevVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.cust.service.ICustDevService;
import com.zlsrj.wms.cust.service.IIdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户表具", tags = { "用户表具操作接口" })
@RestController
@Slf4j
public class CustDevRestController {

	@Autowired
	private ICustDevService custDevService;
//	@Autowired
//	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用户表具")
	@RequestMapping(value = "/cust-devs/{id}", method = RequestMethod.GET)
	public CustDevVo getById(@PathVariable("id") Long id) {
		CustDev custDev = custDevService.getById(id);

		return entity2vo(custDev);
	}

	@ApiOperation(value = "根据参数查询用户表具列表")
	@RequestMapping(value = "/cust-devs", method = RequestMethod.GET)
	public Page<CustDevVo> page(@RequestBody CustDevQueryParam custDevQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<CustDev> pageCustDev = new Page<CustDev>(page, rows);
		QueryWrapper<CustDev> queryWrapperCustDev = new QueryWrapper<CustDev>();
		queryWrapperCustDev.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperCustDev.lambda()
				.eq(custDevQueryParam.getId() != null, CustDev::getId, custDevQueryParam.getId())
				.eq(custDevQueryParam.getTenantId() != null, CustDev::getTenantId, custDevQueryParam.getTenantId())
				.eq(custDevQueryParam.getDevId() != null, CustDev::getDevId, custDevQueryParam.getDevId())
				.eq(custDevQueryParam.getPriceTypeId() != null, CustDev::getPriceTypeId, custDevQueryParam.getPriceTypeId())
				.eq(custDevQueryParam.getWaterTypeId() != null, CustDev::getWaterTypeId, custDevQueryParam.getWaterTypeId())
				.eq(custDevQueryParam.getDevOrder() != null, CustDev::getDevOrder, custDevQueryParam.getDevOrder())
				.eq(custDevQueryParam.getWaterMixType() != null, CustDev::getWaterMixType, custDevQueryParam.getWaterMixType())
				.eq(custDevQueryParam.getWaterScale() != null, CustDev::getWaterScale, custDevQueryParam.getWaterScale())
				.eq(custDevQueryParam.getWaterCalcType() != null, CustDev::getWaterCalcType, custDevQueryParam.getWaterCalcType())
				;

		IPage<CustDev> custDevPage = custDevService.page(pageCustDev, queryWrapperCustDev);

		Page<CustDevVo> custDevVoPage = new Page<CustDevVo>(page, rows);
		custDevVoPage.setCurrent(custDevPage.getCurrent());
		custDevVoPage.setPages(custDevPage.getPages());
		custDevVoPage.setSize(custDevPage.getSize());
		custDevVoPage.setTotal(custDevPage.getTotal());
		custDevVoPage.setRecords(custDevPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return custDevVoPage;
	}

	@ApiOperation(value = "新增用户表具")
	@RequestMapping(value = "/cust-devs", method = RequestMethod.POST)
	public CustDevVo save(@RequestBody CustDev custDev) {
		if (custDev.getId() == null || custDev.getId().compareTo(0L) <= 0) {
			custDev.setId(idService.selectId());
		}
		boolean success = custDevService.save(custDev);
		if (success) {
			CustDev custDevDatabase = custDevService.getById(custDev.getId());
			return entity2vo(custDevDatabase);
		}
		log.info("save CustDev fail，{}", ToStringBuilder.reflectionToString(custDev, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新用户表具全部信息")
	@RequestMapping(value = "/cust-devs/{id}", method = RequestMethod.PUT)
	public CustDevVo updateById(@PathVariable("id") Long id, @RequestBody CustDev custDev) {
		custDev.setId(id);
		boolean success = custDevService.updateById(custDev);
		if (success) {
			CustDev custDevDatabase = custDevService.getById(id);
			return entity2vo(custDevDatabase);
		}
		log.info("update CustDev fail，{}", ToStringBuilder.reflectionToString(custDev, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用户表具信息")
	@RequestMapping(value = "/cust-devs/{id}", method = RequestMethod.PATCH)
	public CustDevVo updatePatchById(@PathVariable("id") Long id, @RequestBody CustDev custDev) {
		UpdateWrapper<CustDev> updateWrapperCustDev = new UpdateWrapper<CustDev>();
		updateWrapperCustDev.lambda()//
				.eq(CustDev::getId, id)
				// .set(custDev.getId() != null, CustDev::getId, custDev.getId())
				.set(custDev.getTenantId() != null, CustDev::getTenantId, custDev.getTenantId())
				.set(custDev.getDevId() != null, CustDev::getDevId, custDev.getDevId())
				.set(custDev.getPriceTypeId() != null, CustDev::getPriceTypeId, custDev.getPriceTypeId())
				.set(custDev.getWaterTypeId() != null, CustDev::getWaterTypeId, custDev.getWaterTypeId())
				.set(custDev.getDevOrder() != null, CustDev::getDevOrder, custDev.getDevOrder())
				.set(custDev.getWaterMixType() != null, CustDev::getWaterMixType, custDev.getWaterMixType())
				.set(custDev.getWaterScale() != null, CustDev::getWaterScale, custDev.getWaterScale())
				.set(custDev.getWaterCalcType() != null, CustDev::getWaterCalcType, custDev.getWaterCalcType())
				;

		boolean success = custDevService.update(updateWrapperCustDev);
		if (success) {
			CustDev custDevDatabase = custDevService.getById(id);
			return entity2vo(custDevDatabase);
		}
		log.info("partial update CustDev fail，{}",
				ToStringBuilder.reflectionToString(custDev, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除用户表具")
	@RequestMapping(value = "/cust-devs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = custDevService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private CustDevVo entity2vo(CustDev custDev) {
		String jsonString = JSON.toJSONString(custDev);
		CustDevVo custDevVo = JSON.parseObject(jsonString, CustDevVo.class);
//		if (StringUtils.isEmpty(custDevVo.getTenantName())) {
//			TenantInfo tenantInfo = tenantInfoService.getById(custDev.getTenantId());
//			if (tenantInfo != null) {
//				custDevVo.setTenantName(tenantInfo.getTenantName());
//			}
//		}
		return custDevVo;
	}

}
