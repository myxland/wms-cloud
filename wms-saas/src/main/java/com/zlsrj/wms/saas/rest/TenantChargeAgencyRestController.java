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
import com.zlsrj.wms.api.dto.TenantChargeAgencyAddParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyQueryParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantChargeAgency;
import com.zlsrj.wms.api.vo.TenantChargeAgencyVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantChargeAgencyService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "代收机构", tags = { "代收机构操作接口" })
@RestController
@Slf4j
public class TenantChargeAgencyRestController {

	@Autowired
	private ITenantChargeAgencyService tenantChargeAgencyService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询代收机构")
	@RequestMapping(value = "/tenant-charge-agencys/{id}", method = RequestMethod.GET)
	public TenantChargeAgencyVo getById(@PathVariable("id") String id) {
		TenantChargeAgency tenantChargeAgency = tenantChargeAgencyService.getById(id);

		return entity2vo(tenantChargeAgency);
	}

	@ApiOperation(value = "根据参数查询代收机构列表")
	@RequestMapping(value = "/tenant-charge-agencys/list", method = RequestMethod.GET)
	public List<TenantChargeAgencyVo> list(@RequestBody TenantChargeAgencyQueryParam tenantChargeAgencyQueryParam) {
		QueryWrapper<TenantChargeAgency> queryWrapperTenantChargeAgency = new QueryWrapper<TenantChargeAgency>();
		queryWrapperTenantChargeAgency.lambda()
				.eq(tenantChargeAgencyQueryParam.getId() != null, TenantChargeAgency::getId, tenantChargeAgencyQueryParam.getId())
				.eq(tenantChargeAgencyQueryParam.getTenantId() != null, TenantChargeAgency::getTenantId, tenantChargeAgencyQueryParam.getTenantId())
				.eq(tenantChargeAgencyQueryParam.getChargeAgencyName() != null, TenantChargeAgency::getChargeAgencyName, tenantChargeAgencyQueryParam.getChargeAgencyName())
				.eq(tenantChargeAgencyQueryParam.getChargeAgencyData() != null, TenantChargeAgency::getChargeAgencyData, tenantChargeAgencyQueryParam.getChargeAgencyData())
				.eq(tenantChargeAgencyQueryParam.getCreateType() != null, TenantChargeAgency::getCreateType, tenantChargeAgencyQueryParam.getCreateType())
				.eq(tenantChargeAgencyQueryParam.getApiOn() != null, TenantChargeAgency::getApiOn, tenantChargeAgencyQueryParam.getApiOn())
				.eq(tenantChargeAgencyQueryParam.getAddTime() != null, TenantChargeAgency::getAddTime, tenantChargeAgencyQueryParam.getAddTime())
				.ge(tenantChargeAgencyQueryParam.getAddTimeStart() != null, TenantChargeAgency::getAddTime,tenantChargeAgencyQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantChargeAgencyQueryParam.getAddTimeStart()))
				.le(tenantChargeAgencyQueryParam.getAddTimeEnd() != null, TenantChargeAgency::getAddTime,tenantChargeAgencyQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantChargeAgencyQueryParam.getAddTimeEnd()))
				.eq(tenantChargeAgencyQueryParam.getUpdateTime() != null, TenantChargeAgency::getUpdateTime, tenantChargeAgencyQueryParam.getUpdateTime())
				.ge(tenantChargeAgencyQueryParam.getUpdateTimeStart() != null, TenantChargeAgency::getUpdateTime,tenantChargeAgencyQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantChargeAgencyQueryParam.getUpdateTimeStart()))
				.le(tenantChargeAgencyQueryParam.getUpdateTimeEnd() != null, TenantChargeAgency::getUpdateTime,tenantChargeAgencyQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantChargeAgencyQueryParam.getUpdateTimeEnd()))
				;

		List<TenantChargeAgency> tenantChargeAgencyList = tenantChargeAgencyService.list(queryWrapperTenantChargeAgency);

		List<TenantChargeAgencyVo> tenantChargeAgencyVoList = TranslateUtil.translateList(tenantChargeAgencyList, TenantChargeAgencyVo.class);

		return tenantChargeAgencyVoList;
	}
	
	@ApiOperation(value = "根据参数查询代收机构列表")
	@RequestMapping(value = "/tenant-charge-agencys", method = RequestMethod.GET)
	public Page<TenantChargeAgencyVo> page(@RequestBody TenantChargeAgencyQueryParam tenantChargeAgencyQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantChargeAgency> pageTenantChargeAgency = new Page<TenantChargeAgency>(page, rows);
		QueryWrapper<TenantChargeAgency> queryWrapperTenantChargeAgency = new QueryWrapper<TenantChargeAgency>();
		queryWrapperTenantChargeAgency.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantChargeAgency.lambda()
				.eq(tenantChargeAgencyQueryParam.getId() != null, TenantChargeAgency::getId, tenantChargeAgencyQueryParam.getId())
				.eq(tenantChargeAgencyQueryParam.getTenantId() != null, TenantChargeAgency::getTenantId, tenantChargeAgencyQueryParam.getTenantId())
				.eq(tenantChargeAgencyQueryParam.getChargeAgencyName() != null, TenantChargeAgency::getChargeAgencyName, tenantChargeAgencyQueryParam.getChargeAgencyName())
				.eq(tenantChargeAgencyQueryParam.getChargeAgencyData() != null, TenantChargeAgency::getChargeAgencyData, tenantChargeAgencyQueryParam.getChargeAgencyData())
				.eq(tenantChargeAgencyQueryParam.getCreateType() != null, TenantChargeAgency::getCreateType, tenantChargeAgencyQueryParam.getCreateType())
				.eq(tenantChargeAgencyQueryParam.getApiOn() != null, TenantChargeAgency::getApiOn, tenantChargeAgencyQueryParam.getApiOn())
				.eq(tenantChargeAgencyQueryParam.getAddTime() != null, TenantChargeAgency::getAddTime, tenantChargeAgencyQueryParam.getAddTime())
				.ge(tenantChargeAgencyQueryParam.getAddTimeStart() != null, TenantChargeAgency::getAddTime,tenantChargeAgencyQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantChargeAgencyQueryParam.getAddTimeStart()))
				.le(tenantChargeAgencyQueryParam.getAddTimeEnd() != null, TenantChargeAgency::getAddTime,tenantChargeAgencyQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantChargeAgencyQueryParam.getAddTimeEnd()))
				.eq(tenantChargeAgencyQueryParam.getUpdateTime() != null, TenantChargeAgency::getUpdateTime, tenantChargeAgencyQueryParam.getUpdateTime())
				.ge(tenantChargeAgencyQueryParam.getUpdateTimeStart() != null, TenantChargeAgency::getUpdateTime,tenantChargeAgencyQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantChargeAgencyQueryParam.getUpdateTimeStart()))
				.le(tenantChargeAgencyQueryParam.getUpdateTimeEnd() != null, TenantChargeAgency::getUpdateTime,tenantChargeAgencyQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantChargeAgencyQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantChargeAgency> tenantChargeAgencyPage = tenantChargeAgencyService.page(pageTenantChargeAgency, queryWrapperTenantChargeAgency);

		Page<TenantChargeAgencyVo> tenantChargeAgencyVoPage = new Page<TenantChargeAgencyVo>(page, rows);
		tenantChargeAgencyVoPage.setCurrent(tenantChargeAgencyPage.getCurrent());
		tenantChargeAgencyVoPage.setPages(tenantChargeAgencyPage.getPages());
		tenantChargeAgencyVoPage.setSize(tenantChargeAgencyPage.getSize());
		tenantChargeAgencyVoPage.setTotal(tenantChargeAgencyPage.getTotal());
		tenantChargeAgencyVoPage.setRecords(tenantChargeAgencyPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantChargeAgencyVoPage;
	}
	
	@ApiOperation(value = "新增代收机构")
	@RequestMapping(value = "/tenant-charge-agencys", method = RequestMethod.POST)
	public String save(@RequestBody TenantChargeAgencyAddParam tenantChargeAgencyAddParam) {
		return tenantChargeAgencyService.save(tenantChargeAgencyAddParam);
	}

	@ApiOperation(value = "更新代收机构全部信息")
	@RequestMapping(value = "/tenant-charge-agencys/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantChargeAgencyUpdateParam tenantChargeAgencyUpdateParam) {
		tenantChargeAgencyUpdateParam.setId(id);
		return tenantChargeAgencyService.updateById(tenantChargeAgencyUpdateParam);
	}

	@ApiOperation(value = "根据参数更新代收机构信息")
	@RequestMapping(value = "/tenant-charge-agencys/{id}", method = RequestMethod.PATCH)
	public TenantChargeAgencyVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantChargeAgency tenantChargeAgency) {
        TenantChargeAgency tenantChargeAgencyWhere = TenantChargeAgency.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantChargeAgency> updateWrapperTenantChargeAgency = new UpdateWrapper<TenantChargeAgency>();
		updateWrapperTenantChargeAgency.setEntity(tenantChargeAgencyWhere);
		updateWrapperTenantChargeAgency.lambda()//
				//.eq(TenantChargeAgency::getId, id)
				// .set(tenantChargeAgency.getId() != null, TenantChargeAgency::getId, tenantChargeAgency.getId())
				.set(tenantChargeAgency.getTenantId() != null, TenantChargeAgency::getTenantId, tenantChargeAgency.getTenantId())
				.set(tenantChargeAgency.getChargeAgencyName() != null, TenantChargeAgency::getChargeAgencyName, tenantChargeAgency.getChargeAgencyName())
				.set(tenantChargeAgency.getChargeAgencyData() != null, TenantChargeAgency::getChargeAgencyData, tenantChargeAgency.getChargeAgencyData())
				.set(tenantChargeAgency.getCreateType() != null, TenantChargeAgency::getCreateType, tenantChargeAgency.getCreateType())
				.set(tenantChargeAgency.getApiOn() != null, TenantChargeAgency::getApiOn, tenantChargeAgency.getApiOn())
				.set(tenantChargeAgency.getAddTime() != null, TenantChargeAgency::getAddTime, tenantChargeAgency.getAddTime())
				.set(tenantChargeAgency.getUpdateTime() != null, TenantChargeAgency::getUpdateTime, tenantChargeAgency.getUpdateTime())
				;

		boolean success = tenantChargeAgencyService.update(updateWrapperTenantChargeAgency);
		if (success) {
			TenantChargeAgency tenantChargeAgencyDatabase = tenantChargeAgencyService.getById(id);
			return entity2vo(tenantChargeAgencyDatabase);
		}
		log.info("partial update TenantChargeAgency fail，{}",
				ToStringBuilder.reflectionToString(tenantChargeAgency, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除代收机构")
	@RequestMapping(value = "/tenant-charge-agencys/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantChargeAgencyService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantChargeAgencyVo entity2vo(TenantChargeAgency tenantChargeAgency) {
		if (tenantChargeAgency == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantChargeAgency);
		TenantChargeAgencyVo tenantChargeAgencyVo = JSON.parseObject(jsonString, TenantChargeAgencyVo.class);
		if (StringUtils.isEmpty(tenantChargeAgencyVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantChargeAgency.getTenantId());
			if (tenantInfo != null) {
				tenantChargeAgencyVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantChargeAgencyVo;
	}

}
