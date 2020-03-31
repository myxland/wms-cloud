package com.zlsrj.wms.saas.rest;

import java.util.Date;
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
import com.zlsrj.wms.api.dto.TenantCustomerArchivesAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomerArchives;
import com.zlsrj.wms.api.vo.TenantCustomerArchivesVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCustomerArchivesService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户档案", tags = { "用户档案操作接口" })
@RestController
@Slf4j
public class TenantCustomerArchivesRestController {

	@Autowired
	private ITenantCustomerArchivesService tenantCustomerArchivesService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询用户档案")
	@RequestMapping(value = "/tenant-customer-archivess/{id}", method = RequestMethod.GET)
	public TenantCustomerArchivesVo getById(@PathVariable("id") String id) {
		TenantCustomerArchives tenantCustomerArchives = tenantCustomerArchivesService.getById(id);

		return entity2vo(tenantCustomerArchives);
	}

	@ApiOperation(value = "根据参数查询用户档案列表")
	@RequestMapping(value = "/tenant-customer-archivess/list", method = RequestMethod.GET)
	public List<TenantCustomerArchivesVo> list(@RequestBody TenantCustomerArchivesQueryParam tenantCustomerArchivesQueryParam) {
		QueryWrapper<TenantCustomerArchives> queryWrapperTenantCustomerArchives = new QueryWrapper<TenantCustomerArchives>();
		queryWrapperTenantCustomerArchives.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getId()), TenantCustomerArchives::getId, tenantCustomerArchivesQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getTenantId()), TenantCustomerArchives::getTenantId, tenantCustomerArchivesQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getCustomerId()), TenantCustomerArchives::getCustomerId, tenantCustomerArchivesQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getCustomerCode()), TenantCustomerArchives::getCustomerCode, tenantCustomerArchivesQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesName()), TenantCustomerArchives::getArchivesName, tenantCustomerArchivesQueryParam.getArchivesName())
				.eq(tenantCustomerArchivesQueryParam.getArchivesCreateTime() != null, TenantCustomerArchives::getArchivesCreateTime, tenantCustomerArchivesQueryParam.getArchivesCreateTime())
				.ge(tenantCustomerArchivesQueryParam.getArchivesCreateTimeStart() != null, TenantCustomerArchives::getArchivesCreateTime,tenantCustomerArchivesQueryParam.getArchivesCreateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateTimeStart()))
				.le(tenantCustomerArchivesQueryParam.getArchivesCreateTimeEnd() != null, TenantCustomerArchives::getArchivesCreateTime,tenantCustomerArchivesQueryParam.getArchivesCreateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateTimeEnd()))
				.eq(tenantCustomerArchivesQueryParam.getArchivesCreateDate() != null, TenantCustomerArchives::getArchivesCreateDate, tenantCustomerArchivesQueryParam.getArchivesCreateDate())
				.ge(tenantCustomerArchivesQueryParam.getArchivesCreateDateStart() != null, TenantCustomerArchives::getArchivesCreateDate,tenantCustomerArchivesQueryParam.getArchivesCreateDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateDateStart()))
				.le(tenantCustomerArchivesQueryParam.getArchivesCreateDateEnd() != null, TenantCustomerArchives::getArchivesCreateDate,tenantCustomerArchivesQueryParam.getArchivesCreateDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateDateEnd()))
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesFilename()), TenantCustomerArchives::getArchivesFilename, tenantCustomerArchivesQueryParam.getArchivesFilename())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesInformation()), TenantCustomerArchives::getArchivesInformation, tenantCustomerArchivesQueryParam.getArchivesInformation())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesCode()), TenantCustomerArchives::getArchivesCode, tenantCustomerArchivesQueryParam.getArchivesCode())
				.eq(tenantCustomerArchivesQueryParam.getAddTime() != null, TenantCustomerArchives::getAddTime, tenantCustomerArchivesQueryParam.getAddTime())
				.ge(tenantCustomerArchivesQueryParam.getAddTimeStart() != null, TenantCustomerArchives::getAddTime,tenantCustomerArchivesQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getAddTimeStart()))
				.le(tenantCustomerArchivesQueryParam.getAddTimeEnd() != null, TenantCustomerArchives::getAddTime,tenantCustomerArchivesQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerArchivesQueryParam.getUpdateTime() != null, TenantCustomerArchives::getUpdateTime, tenantCustomerArchivesQueryParam.getUpdateTime())
				.ge(tenantCustomerArchivesQueryParam.getUpdateTimeStart() != null, TenantCustomerArchives::getUpdateTime,tenantCustomerArchivesQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerArchivesQueryParam.getUpdateTimeEnd() != null, TenantCustomerArchives::getUpdateTime,tenantCustomerArchivesQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getUpdateTimeEnd()))
				;

		List<TenantCustomerArchives> tenantCustomerArchivesList = tenantCustomerArchivesService.list(queryWrapperTenantCustomerArchives);

		List<TenantCustomerArchivesVo> tenantCustomerArchivesVoList = tenantCustomerArchivesList.stream()//
				 .map(e -> entity2vo(e))//
				 .collect(Collectors.toList());

		return tenantCustomerArchivesVoList;
	}
	
	@ApiOperation(value = "根据参数查询用户档案数量")
	@RequestMapping(value = "/tenant-customer-archivess/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerArchivesQueryParam tenantCustomerArchivesQueryParam) {
		QueryWrapper<TenantCustomerArchives> queryWrapperTenantCustomerArchives = new QueryWrapper<TenantCustomerArchives>();
		queryWrapperTenantCustomerArchives.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getId()), TenantCustomerArchives::getId, tenantCustomerArchivesQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getTenantId()), TenantCustomerArchives::getTenantId, tenantCustomerArchivesQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getCustomerId()), TenantCustomerArchives::getCustomerId, tenantCustomerArchivesQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getCustomerCode()), TenantCustomerArchives::getCustomerCode, tenantCustomerArchivesQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesName()), TenantCustomerArchives::getArchivesName, tenantCustomerArchivesQueryParam.getArchivesName())
				.eq(tenantCustomerArchivesQueryParam.getArchivesCreateTime() != null, TenantCustomerArchives::getArchivesCreateTime, tenantCustomerArchivesQueryParam.getArchivesCreateTime())
				.ge(tenantCustomerArchivesQueryParam.getArchivesCreateTimeStart() != null, TenantCustomerArchives::getArchivesCreateTime,tenantCustomerArchivesQueryParam.getArchivesCreateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateTimeStart()))
				.le(tenantCustomerArchivesQueryParam.getArchivesCreateTimeEnd() != null, TenantCustomerArchives::getArchivesCreateTime,tenantCustomerArchivesQueryParam.getArchivesCreateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateTimeEnd()))
				.eq(tenantCustomerArchivesQueryParam.getArchivesCreateDate() != null, TenantCustomerArchives::getArchivesCreateDate, tenantCustomerArchivesQueryParam.getArchivesCreateDate())
				.ge(tenantCustomerArchivesQueryParam.getArchivesCreateDateStart() != null, TenantCustomerArchives::getArchivesCreateDate,tenantCustomerArchivesQueryParam.getArchivesCreateDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateDateStart()))
				.le(tenantCustomerArchivesQueryParam.getArchivesCreateDateEnd() != null, TenantCustomerArchives::getArchivesCreateDate,tenantCustomerArchivesQueryParam.getArchivesCreateDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateDateEnd()))
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesFilename()), TenantCustomerArchives::getArchivesFilename, tenantCustomerArchivesQueryParam.getArchivesFilename())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesInformation()), TenantCustomerArchives::getArchivesInformation, tenantCustomerArchivesQueryParam.getArchivesInformation())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesCode()), TenantCustomerArchives::getArchivesCode, tenantCustomerArchivesQueryParam.getArchivesCode())
				.eq(tenantCustomerArchivesQueryParam.getAddTime() != null, TenantCustomerArchives::getAddTime, tenantCustomerArchivesQueryParam.getAddTime())
				.ge(tenantCustomerArchivesQueryParam.getAddTimeStart() != null, TenantCustomerArchives::getAddTime,tenantCustomerArchivesQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getAddTimeStart()))
				.le(tenantCustomerArchivesQueryParam.getAddTimeEnd() != null, TenantCustomerArchives::getAddTime,tenantCustomerArchivesQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerArchivesQueryParam.getUpdateTime() != null, TenantCustomerArchives::getUpdateTime, tenantCustomerArchivesQueryParam.getUpdateTime())
				.ge(tenantCustomerArchivesQueryParam.getUpdateTimeStart() != null, TenantCustomerArchives::getUpdateTime,tenantCustomerArchivesQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerArchivesQueryParam.getUpdateTimeEnd() != null, TenantCustomerArchives::getUpdateTime,tenantCustomerArchivesQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getUpdateTimeEnd()))
				;

		int count = tenantCustomerArchivesService.count(queryWrapperTenantCustomerArchives);

		return count;
	}
	
	@ApiOperation(value = "根据参数查询用户档案列表")
	@RequestMapping(value = "/tenant-customer-archivess", method = RequestMethod.GET)
	public Page<TenantCustomerArchivesVo> page(@RequestBody TenantCustomerArchivesQueryParam tenantCustomerArchivesQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustomerArchives> pageTenantCustomerArchives = new Page<TenantCustomerArchives>(page, rows);
		QueryWrapper<TenantCustomerArchives> queryWrapperTenantCustomerArchives = new QueryWrapper<TenantCustomerArchives>();
		queryWrapperTenantCustomerArchives.orderBy(StringUtils.isNotBlank(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomerArchives.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getId()), TenantCustomerArchives::getId, tenantCustomerArchivesQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getTenantId()), TenantCustomerArchives::getTenantId, tenantCustomerArchivesQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getCustomerId()), TenantCustomerArchives::getCustomerId, tenantCustomerArchivesQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getCustomerCode()), TenantCustomerArchives::getCustomerCode, tenantCustomerArchivesQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesName()), TenantCustomerArchives::getArchivesName, tenantCustomerArchivesQueryParam.getArchivesName())
				.eq(tenantCustomerArchivesQueryParam.getArchivesCreateTime() != null, TenantCustomerArchives::getArchivesCreateTime, tenantCustomerArchivesQueryParam.getArchivesCreateTime())
				.ge(tenantCustomerArchivesQueryParam.getArchivesCreateTimeStart() != null, TenantCustomerArchives::getArchivesCreateTime,tenantCustomerArchivesQueryParam.getArchivesCreateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateTimeStart()))
				.le(tenantCustomerArchivesQueryParam.getArchivesCreateTimeEnd() != null, TenantCustomerArchives::getArchivesCreateTime,tenantCustomerArchivesQueryParam.getArchivesCreateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateTimeEnd()))
				.eq(tenantCustomerArchivesQueryParam.getArchivesCreateDate() != null, TenantCustomerArchives::getArchivesCreateDate, tenantCustomerArchivesQueryParam.getArchivesCreateDate())
				.ge(tenantCustomerArchivesQueryParam.getArchivesCreateDateStart() != null, TenantCustomerArchives::getArchivesCreateDate,tenantCustomerArchivesQueryParam.getArchivesCreateDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateDateStart()))
				.le(tenantCustomerArchivesQueryParam.getArchivesCreateDateEnd() != null, TenantCustomerArchives::getArchivesCreateDate,tenantCustomerArchivesQueryParam.getArchivesCreateDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getArchivesCreateDateEnd()))
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesFilename()), TenantCustomerArchives::getArchivesFilename, tenantCustomerArchivesQueryParam.getArchivesFilename())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesInformation()), TenantCustomerArchives::getArchivesInformation, tenantCustomerArchivesQueryParam.getArchivesInformation())
				.eq(StringUtils.isNotEmpty(tenantCustomerArchivesQueryParam.getArchivesCode()), TenantCustomerArchives::getArchivesCode, tenantCustomerArchivesQueryParam.getArchivesCode())
				.eq(tenantCustomerArchivesQueryParam.getAddTime() != null, TenantCustomerArchives::getAddTime, tenantCustomerArchivesQueryParam.getAddTime())
				.ge(tenantCustomerArchivesQueryParam.getAddTimeStart() != null, TenantCustomerArchives::getAddTime,tenantCustomerArchivesQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getAddTimeStart()))
				.le(tenantCustomerArchivesQueryParam.getAddTimeEnd() != null, TenantCustomerArchives::getAddTime,tenantCustomerArchivesQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerArchivesQueryParam.getUpdateTime() != null, TenantCustomerArchives::getUpdateTime, tenantCustomerArchivesQueryParam.getUpdateTime())
				.ge(tenantCustomerArchivesQueryParam.getUpdateTimeStart() != null, TenantCustomerArchives::getUpdateTime,tenantCustomerArchivesQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerArchivesQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerArchivesQueryParam.getUpdateTimeEnd() != null, TenantCustomerArchives::getUpdateTime,tenantCustomerArchivesQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerArchivesQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantCustomerArchives> tenantCustomerArchivesPage = tenantCustomerArchivesService.page(pageTenantCustomerArchives, queryWrapperTenantCustomerArchives);

		Page<TenantCustomerArchivesVo> tenantCustomerArchivesVoPage = new Page<TenantCustomerArchivesVo>(page, rows);
		tenantCustomerArchivesVoPage.setCurrent(tenantCustomerArchivesPage.getCurrent());
		tenantCustomerArchivesVoPage.setPages(tenantCustomerArchivesPage.getPages());
		tenantCustomerArchivesVoPage.setSize(tenantCustomerArchivesPage.getSize());
		tenantCustomerArchivesVoPage.setTotal(tenantCustomerArchivesPage.getTotal());
		tenantCustomerArchivesVoPage.setRecords(tenantCustomerArchivesPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustomerArchivesVoPage;
	}
	
	@ApiOperation(value = "新增用户档案")
	@RequestMapping(value = "/tenant-customer-archivess", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerArchivesAddParam tenantCustomerArchivesAddParam) {
		return tenantCustomerArchivesService.save(tenantCustomerArchivesAddParam);
	}

	@ApiOperation(value = "更新用户档案全部信息")
	@RequestMapping(value = "/tenant-customer-archivess/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerArchivesUpdateParam tenantCustomerArchivesUpdateParam) {
		tenantCustomerArchivesUpdateParam.setId(id);
		return tenantCustomerArchivesService.updateById(tenantCustomerArchivesUpdateParam);
	}

	@ApiOperation(value = "根据ID删除用户档案")
	@RequestMapping(value = "/tenant-customer-archivess/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantCustomerArchivesService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerArchivesVo entity2vo(TenantCustomerArchives tenantCustomerArchives) {
		if (tenantCustomerArchives == null) {
			return null;
		}

		TenantCustomerArchivesVo tenantCustomerArchivesVo = TranslateUtil.translate(tenantCustomerArchives, TenantCustomerArchivesVo.class);
		if (StringUtils.isEmpty(tenantCustomerArchivesVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantCustomerArchives.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerArchivesVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerArchivesVo;
	}

}
