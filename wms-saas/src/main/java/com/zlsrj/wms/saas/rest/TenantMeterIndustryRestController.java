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
import com.zlsrj.wms.api.dto.TenantMeterIndustryAddParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterIndustry;
import com.zlsrj.wms.api.vo.TenantMeterIndustryVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterIndustryService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "行业分类", tags = { "行业分类操作接口" })
@RestController
@Slf4j
public class TenantMeterIndustryRestController {

	@Autowired
	private ITenantMeterIndustryService tenantMeterIndustryService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询行业分类")
	@RequestMapping(value = "/tenant-meter-industrys/{id}", method = RequestMethod.GET)
	public TenantMeterIndustryVo getById(@PathVariable("id") String id) {
		TenantMeterIndustry tenantMeterIndustry = tenantMeterIndustryService.getById(id);

		return entity2vo(tenantMeterIndustry);
	}

	@ApiOperation(value = "根据参数查询行业分类列表")
	@RequestMapping(value = "/tenant-meter-industrys/list", method = RequestMethod.GET)
	public List<TenantMeterIndustryVo> list(@RequestBody TenantMeterIndustryQueryParam tenantMeterIndustryQueryParam) {
		QueryWrapper<TenantMeterIndustry> queryWrapperTenantMeterIndustry = new QueryWrapper<TenantMeterIndustry>();
		queryWrapperTenantMeterIndustry.lambda()
				.eq(tenantMeterIndustryQueryParam.getId() != null, TenantMeterIndustry::getId, tenantMeterIndustryQueryParam.getId())
				.eq(tenantMeterIndustryQueryParam.getTenantId() != null, TenantMeterIndustry::getTenantId, tenantMeterIndustryQueryParam.getTenantId())
				.eq(tenantMeterIndustryQueryParam.getMeterIndustryName() != null, TenantMeterIndustry::getMeterIndustryName, tenantMeterIndustryQueryParam.getMeterIndustryName())
				.eq(tenantMeterIndustryQueryParam.getMeterIndustryParentId() != null, TenantMeterIndustry::getMeterIndustryParentId, tenantMeterIndustryQueryParam.getMeterIndustryParentId())
				.eq(tenantMeterIndustryQueryParam.getMeterIndustryData() != null, TenantMeterIndustry::getMeterIndustryData, tenantMeterIndustryQueryParam.getMeterIndustryData())
				.eq(tenantMeterIndustryQueryParam.getAddTime() != null, TenantMeterIndustry::getAddTime, tenantMeterIndustryQueryParam.getAddTime())
				.ge(tenantMeterIndustryQueryParam.getAddTimeStart() != null, TenantMeterIndustry::getAddTime,tenantMeterIndustryQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterIndustryQueryParam.getAddTimeStart()))
				.le(tenantMeterIndustryQueryParam.getAddTimeEnd() != null, TenantMeterIndustry::getAddTime,tenantMeterIndustryQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterIndustryQueryParam.getAddTimeEnd()))
				.eq(tenantMeterIndustryQueryParam.getUpdateTime() != null, TenantMeterIndustry::getUpdateTime, tenantMeterIndustryQueryParam.getUpdateTime())
				.ge(tenantMeterIndustryQueryParam.getUpdateTimeStart() != null, TenantMeterIndustry::getUpdateTime,tenantMeterIndustryQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterIndustryQueryParam.getUpdateTimeStart()))
				.le(tenantMeterIndustryQueryParam.getUpdateTimeEnd() != null, TenantMeterIndustry::getUpdateTime,tenantMeterIndustryQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterIndustryQueryParam.getUpdateTimeEnd()))
				.eq(tenantMeterIndustryQueryParam.getParentId()!=null,TenantMeterIndustry::getMeterIndustryParentId, tenantMeterIndustryQueryParam.getParentId())
				;

		List<TenantMeterIndustry> tenantMeterIndustryList = tenantMeterIndustryService.list(queryWrapperTenantMeterIndustry);

		List<TenantMeterIndustryVo> tenantMeterIndustryVoList = TranslateUtil.translateList(tenantMeterIndustryList, TenantMeterIndustryVo.class);

		return tenantMeterIndustryVoList;
	}
	
	@ApiOperation(value = "根据参数查询行业分类列表")
	@RequestMapping(value = "/tenant-meter-industrys", method = RequestMethod.GET)
	public Page<TenantMeterIndustryVo> page(@RequestBody TenantMeterIndustryQueryParam tenantMeterIndustryQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterIndustry> pageTenantMeterIndustry = new Page<TenantMeterIndustry>(page, rows);
		QueryWrapper<TenantMeterIndustry> queryWrapperTenantMeterIndustry = new QueryWrapper<TenantMeterIndustry>();
		queryWrapperTenantMeterIndustry.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterIndustry.lambda()
				.eq(tenantMeterIndustryQueryParam.getId() != null, TenantMeterIndustry::getId, tenantMeterIndustryQueryParam.getId())
				.eq(tenantMeterIndustryQueryParam.getTenantId() != null, TenantMeterIndustry::getTenantId, tenantMeterIndustryQueryParam.getTenantId())
				.eq(tenantMeterIndustryQueryParam.getMeterIndustryName() != null, TenantMeterIndustry::getMeterIndustryName, tenantMeterIndustryQueryParam.getMeterIndustryName())
				.eq(tenantMeterIndustryQueryParam.getMeterIndustryParentId() != null, TenantMeterIndustry::getMeterIndustryParentId, tenantMeterIndustryQueryParam.getMeterIndustryParentId())
				.eq(tenantMeterIndustryQueryParam.getMeterIndustryData() != null, TenantMeterIndustry::getMeterIndustryData, tenantMeterIndustryQueryParam.getMeterIndustryData())
				.eq(tenantMeterIndustryQueryParam.getAddTime() != null, TenantMeterIndustry::getAddTime, tenantMeterIndustryQueryParam.getAddTime())
				.ge(tenantMeterIndustryQueryParam.getAddTimeStart() != null, TenantMeterIndustry::getAddTime,tenantMeterIndustryQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterIndustryQueryParam.getAddTimeStart()))
				.le(tenantMeterIndustryQueryParam.getAddTimeEnd() != null, TenantMeterIndustry::getAddTime,tenantMeterIndustryQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterIndustryQueryParam.getAddTimeEnd()))
				.eq(tenantMeterIndustryQueryParam.getUpdateTime() != null, TenantMeterIndustry::getUpdateTime, tenantMeterIndustryQueryParam.getUpdateTime())
				.ge(tenantMeterIndustryQueryParam.getUpdateTimeStart() != null, TenantMeterIndustry::getUpdateTime,tenantMeterIndustryQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterIndustryQueryParam.getUpdateTimeStart()))
				.le(tenantMeterIndustryQueryParam.getUpdateTimeEnd() != null, TenantMeterIndustry::getUpdateTime,tenantMeterIndustryQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterIndustryQueryParam.getUpdateTimeEnd()))
				.eq(tenantMeterIndustryQueryParam.getParentId()!=null,TenantMeterIndustry::getMeterIndustryParentId, tenantMeterIndustryQueryParam.getParentId())
				.isNull(tenantMeterIndustryQueryParam.getParentId()==null, TenantMeterIndustry::getMeterIndustryParentId)
				;

		IPage<TenantMeterIndustry> tenantMeterIndustryPage = tenantMeterIndustryService.page(pageTenantMeterIndustry, queryWrapperTenantMeterIndustry);

		Page<TenantMeterIndustryVo> tenantMeterIndustryVoPage = new Page<TenantMeterIndustryVo>(page, rows);
		tenantMeterIndustryVoPage.setCurrent(tenantMeterIndustryPage.getCurrent());
		tenantMeterIndustryVoPage.setPages(tenantMeterIndustryPage.getPages());
		tenantMeterIndustryVoPage.setSize(tenantMeterIndustryPage.getSize());
		tenantMeterIndustryVoPage.setTotal(tenantMeterIndustryPage.getTotal());
		tenantMeterIndustryVoPage.setRecords(tenantMeterIndustryPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterIndustryVoPage;
	}
	
	@ApiOperation(value = "新增行业分类")
	@RequestMapping(value = "/tenant-meter-industrys", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterIndustryAddParam tenantMeterIndustryAddParam) {
		return tenantMeterIndustryService.save(tenantMeterIndustryAddParam);
	}

	@ApiOperation(value = "更新行业分类全部信息")
	@RequestMapping(value = "/tenant-meter-industrys/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterIndustryUpdateParam tenantMeterIndustryUpdateParam) {
		tenantMeterIndustryUpdateParam.setId(id);
		return tenantMeterIndustryService.updateById(tenantMeterIndustryUpdateParam);
	}

	@ApiOperation(value = "根据参数更新行业分类信息")
	@RequestMapping(value = "/tenant-meter-industrys/{id}", method = RequestMethod.PATCH)
	public TenantMeterIndustryVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterIndustry tenantMeterIndustry) {
        TenantMeterIndustry tenantMeterIndustryWhere = TenantMeterIndustry.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterIndustry> updateWrapperTenantMeterIndustry = new UpdateWrapper<TenantMeterIndustry>();
		updateWrapperTenantMeterIndustry.setEntity(tenantMeterIndustryWhere);
		updateWrapperTenantMeterIndustry.lambda()//
				//.eq(TenantMeterIndustry::getId, id)
				// .set(tenantMeterIndustry.getId() != null, TenantMeterIndustry::getId, tenantMeterIndustry.getId())
				.set(tenantMeterIndustry.getTenantId() != null, TenantMeterIndustry::getTenantId, tenantMeterIndustry.getTenantId())
				.set(tenantMeterIndustry.getMeterIndustryName() != null, TenantMeterIndustry::getMeterIndustryName, tenantMeterIndustry.getMeterIndustryName())
				.set(tenantMeterIndustry.getMeterIndustryParentId() != null, TenantMeterIndustry::getMeterIndustryParentId, tenantMeterIndustry.getMeterIndustryParentId())
				.set(tenantMeterIndustry.getMeterIndustryData() != null, TenantMeterIndustry::getMeterIndustryData, tenantMeterIndustry.getMeterIndustryData())
				.set(tenantMeterIndustry.getAddTime() != null, TenantMeterIndustry::getAddTime, tenantMeterIndustry.getAddTime())
				.set(tenantMeterIndustry.getUpdateTime() != null, TenantMeterIndustry::getUpdateTime, tenantMeterIndustry.getUpdateTime())
				;

		boolean success = tenantMeterIndustryService.update(updateWrapperTenantMeterIndustry);
		if (success) {
			TenantMeterIndustry tenantMeterIndustryDatabase = tenantMeterIndustryService.getById(id);
			return entity2vo(tenantMeterIndustryDatabase);
		}
		log.info("partial update TenantMeterIndustry fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterIndustry, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除行业分类")
	@RequestMapping(value = "/tenant-meter-industrys/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterIndustryService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterIndustryVo entity2vo(TenantMeterIndustry tenantMeterIndustry) {
		if (tenantMeterIndustry == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterIndustry);
		TenantMeterIndustryVo tenantMeterIndustryVo = JSON.parseObject(jsonString, TenantMeterIndustryVo.class);
		if (StringUtils.isEmpty(tenantMeterIndustryVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantMeterIndustry.getTenantId());
			if (tenantInfo != null) {
				tenantMeterIndustryVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterIndustryVo;
	}

}
