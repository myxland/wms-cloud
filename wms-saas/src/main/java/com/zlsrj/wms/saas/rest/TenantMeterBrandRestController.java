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
import com.zlsrj.wms.api.dto.TenantMeterBrandAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBrandQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterBrandUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterBrand;
import com.zlsrj.wms.api.vo.TenantMeterBrandVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterBrandService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表品牌", tags = { "水表品牌操作接口" })
@RestController
@Slf4j
public class TenantMeterBrandRestController {

	@Autowired
	private ITenantMeterBrandService tenantMeterBrandService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询水表品牌")
	@RequestMapping(value = "/tenant-meter-brands/{id}", method = RequestMethod.GET)
	public TenantMeterBrandVo getById(@PathVariable("id") String id) {
		TenantMeterBrand tenantMeterBrand = tenantMeterBrandService.getById(id);

		return entity2vo(tenantMeterBrand);
	}

	@ApiOperation(value = "根据参数查询水表品牌列表")
	@RequestMapping(value = "/tenant-meter-brands/list", method = RequestMethod.GET)
	public List<TenantMeterBrandVo> list(@RequestBody TenantMeterBrandQueryParam tenantMeterBrandQueryParam) {
		QueryWrapper<TenantMeterBrand> queryWrapperTenantMeterBrand = new QueryWrapper<TenantMeterBrand>();
		queryWrapperTenantMeterBrand.lambda()
				.eq(tenantMeterBrandQueryParam.getId() != null, TenantMeterBrand::getId, tenantMeterBrandQueryParam.getId())
				.eq(tenantMeterBrandQueryParam.getTenantId() != null, TenantMeterBrand::getTenantId, tenantMeterBrandQueryParam.getTenantId())
				.eq(tenantMeterBrandQueryParam.getMeterBrandName() != null, TenantMeterBrand::getMeterBrandName, tenantMeterBrandQueryParam.getMeterBrandName())
				.eq(tenantMeterBrandQueryParam.getMeterBrandData() != null, TenantMeterBrand::getMeterBrandData, tenantMeterBrandQueryParam.getMeterBrandData())
				.eq(tenantMeterBrandQueryParam.getAddTime() != null, TenantMeterBrand::getAddTime, tenantMeterBrandQueryParam.getAddTime())
				.ge(tenantMeterBrandQueryParam.getAddTimeStart() != null, TenantMeterBrand::getAddTime,tenantMeterBrandQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterBrandQueryParam.getAddTimeStart()))
				.le(tenantMeterBrandQueryParam.getAddTimeEnd() != null, TenantMeterBrand::getAddTime,tenantMeterBrandQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterBrandQueryParam.getAddTimeEnd()))
				.eq(tenantMeterBrandQueryParam.getUpdateTime() != null, TenantMeterBrand::getUpdateTime, tenantMeterBrandQueryParam.getUpdateTime())
				.ge(tenantMeterBrandQueryParam.getUpdateTimeStart() != null, TenantMeterBrand::getUpdateTime,tenantMeterBrandQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterBrandQueryParam.getUpdateTimeStart()))
				.le(tenantMeterBrandQueryParam.getUpdateTimeEnd() != null, TenantMeterBrand::getUpdateTime,tenantMeterBrandQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterBrandQueryParam.getUpdateTimeEnd()))
				;

		List<TenantMeterBrand> tenantMeterBrandList = tenantMeterBrandService.list(queryWrapperTenantMeterBrand);

		List<TenantMeterBrandVo> tenantMeterBrandVoList = TranslateUtil.translateList(tenantMeterBrandList, TenantMeterBrandVo.class);

		return tenantMeterBrandVoList;
	}
	
	@ApiOperation(value = "根据参数查询水表品牌列表")
	@RequestMapping(value = "/tenant-meter-brands", method = RequestMethod.GET)
	public Page<TenantMeterBrandVo> page(@RequestBody TenantMeterBrandQueryParam tenantMeterBrandQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterBrand> pageTenantMeterBrand = new Page<TenantMeterBrand>(page, rows);
		QueryWrapper<TenantMeterBrand> queryWrapperTenantMeterBrand = new QueryWrapper<TenantMeterBrand>();
		queryWrapperTenantMeterBrand.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterBrand.lambda()
				.eq(tenantMeterBrandQueryParam.getId() != null, TenantMeterBrand::getId, tenantMeterBrandQueryParam.getId())
				.eq(tenantMeterBrandQueryParam.getTenantId() != null, TenantMeterBrand::getTenantId, tenantMeterBrandQueryParam.getTenantId())
				.eq(tenantMeterBrandQueryParam.getMeterBrandName() != null, TenantMeterBrand::getMeterBrandName, tenantMeterBrandQueryParam.getMeterBrandName())
				.eq(tenantMeterBrandQueryParam.getMeterBrandData() != null, TenantMeterBrand::getMeterBrandData, tenantMeterBrandQueryParam.getMeterBrandData())
				.eq(tenantMeterBrandQueryParam.getAddTime() != null, TenantMeterBrand::getAddTime, tenantMeterBrandQueryParam.getAddTime())
				.ge(tenantMeterBrandQueryParam.getAddTimeStart() != null, TenantMeterBrand::getAddTime,tenantMeterBrandQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterBrandQueryParam.getAddTimeStart()))
				.le(tenantMeterBrandQueryParam.getAddTimeEnd() != null, TenantMeterBrand::getAddTime,tenantMeterBrandQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterBrandQueryParam.getAddTimeEnd()))
				.eq(tenantMeterBrandQueryParam.getUpdateTime() != null, TenantMeterBrand::getUpdateTime, tenantMeterBrandQueryParam.getUpdateTime())
				.ge(tenantMeterBrandQueryParam.getUpdateTimeStart() != null, TenantMeterBrand::getUpdateTime,tenantMeterBrandQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterBrandQueryParam.getUpdateTimeStart()))
				.le(tenantMeterBrandQueryParam.getUpdateTimeEnd() != null, TenantMeterBrand::getUpdateTime,tenantMeterBrandQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterBrandQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantMeterBrand> tenantMeterBrandPage = tenantMeterBrandService.page(pageTenantMeterBrand, queryWrapperTenantMeterBrand);

		Page<TenantMeterBrandVo> tenantMeterBrandVoPage = new Page<TenantMeterBrandVo>(page, rows);
		tenantMeterBrandVoPage.setCurrent(tenantMeterBrandPage.getCurrent());
		tenantMeterBrandVoPage.setPages(tenantMeterBrandPage.getPages());
		tenantMeterBrandVoPage.setSize(tenantMeterBrandPage.getSize());
		tenantMeterBrandVoPage.setTotal(tenantMeterBrandPage.getTotal());
		tenantMeterBrandVoPage.setRecords(tenantMeterBrandPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterBrandVoPage;
	}
	
	@ApiOperation(value = "新增水表品牌")
	@RequestMapping(value = "/tenant-meter-brands", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterBrandAddParam tenantMeterBrandAddParam) {
		return tenantMeterBrandService.save(tenantMeterBrandAddParam);
	}

	@ApiOperation(value = "更新水表品牌全部信息")
	@RequestMapping(value = "/tenant-meter-brands/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterBrandUpdateParam tenantMeterBrandUpdateParam) {
		tenantMeterBrandUpdateParam.setId(id);
		return tenantMeterBrandService.updateById(tenantMeterBrandUpdateParam);
	}

	@ApiOperation(value = "根据参数更新水表品牌信息")
	@RequestMapping(value = "/tenant-meter-brands/{id}", method = RequestMethod.PATCH)
	public TenantMeterBrandVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterBrand tenantMeterBrand) {
        TenantMeterBrand tenantMeterBrandWhere = TenantMeterBrand.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterBrand> updateWrapperTenantMeterBrand = new UpdateWrapper<TenantMeterBrand>();
		updateWrapperTenantMeterBrand.setEntity(tenantMeterBrandWhere);
		updateWrapperTenantMeterBrand.lambda()//
				//.eq(TenantMeterBrand::getId, id)
				// .set(tenantMeterBrand.getId() != null, TenantMeterBrand::getId, tenantMeterBrand.getId())
				.set(tenantMeterBrand.getTenantId() != null, TenantMeterBrand::getTenantId, tenantMeterBrand.getTenantId())
				.set(tenantMeterBrand.getMeterBrandName() != null, TenantMeterBrand::getMeterBrandName, tenantMeterBrand.getMeterBrandName())
				.set(tenantMeterBrand.getMeterBrandData() != null, TenantMeterBrand::getMeterBrandData, tenantMeterBrand.getMeterBrandData())
				.set(tenantMeterBrand.getAddTime() != null, TenantMeterBrand::getAddTime, tenantMeterBrand.getAddTime())
				.set(tenantMeterBrand.getUpdateTime() != null, TenantMeterBrand::getUpdateTime, tenantMeterBrand.getUpdateTime())
				;

		boolean success = tenantMeterBrandService.update(updateWrapperTenantMeterBrand);
		if (success) {
			TenantMeterBrand tenantMeterBrandDatabase = tenantMeterBrandService.getById(id);
			return entity2vo(tenantMeterBrandDatabase);
		}
		log.info("partial update TenantMeterBrand fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterBrand, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水表品牌")
	@RequestMapping(value = "/tenant-meter-brands/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterBrandService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterBrandVo entity2vo(TenantMeterBrand tenantMeterBrand) {
		if (tenantMeterBrand == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterBrand);
		TenantMeterBrandVo tenantMeterBrandVo = JSON.parseObject(jsonString, TenantMeterBrandVo.class);
		if (StringUtils.isEmpty(tenantMeterBrandVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantMeterBrand.getTenantId());
			if (tenantInfo != null) {
				tenantMeterBrandVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterBrandVo;
	}

}
