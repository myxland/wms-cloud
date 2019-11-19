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
import com.zlsrj.wms.api.dto.TenantDeptQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantDept;
import com.zlsrj.wms.api.vo.TenantDeptVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantDeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户部门", tags = { "租户部门操作接口" })
@RestController
@Slf4j
public class TenantDeptRestController {

	@Autowired
	private ITenantDeptService tenantDeptService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户部门")
	@RequestMapping(value = "/tenant-depts/{id}", method = RequestMethod.GET)
	public TenantDeptVo getById(@PathVariable("id") Long id) {
		TenantDept tenantDept = tenantDeptService.getById(id);

		return entity2vo(tenantDept);
	}

	@ApiOperation(value = "根据参数查询租户部门列表")
	@RequestMapping(value = "/tenant-depts", method = RequestMethod.GET)
	public Page<TenantDeptVo> page(@RequestBody TenantDeptQueryParam tenantDeptQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantDept> pageTenantDept = new Page<TenantDept>(page, rows);
		QueryWrapper<TenantDept> queryWrapperTenantDept = new QueryWrapper<TenantDept>();
		queryWrapperTenantDept.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantDept.lambda()
						.eq(tenantDeptQueryParam.getId() != null, TenantDept::getId, tenantDeptQueryParam.getId())
						.eq(tenantDeptQueryParam.getParentDeptId() != null, TenantDept::getParentDeptId, tenantDeptQueryParam.getParentDeptId())
						.eq(tenantDeptQueryParam.getTenantId() != null, TenantDept::getTenantId, tenantDeptQueryParam.getTenantId())
						.eq(tenantDeptQueryParam.getDeptName() != null, TenantDept::getDeptName, tenantDeptQueryParam.getDeptName())
				;

		IPage<TenantDept> tenantDeptPage = tenantDeptService.page(pageTenantDept, queryWrapperTenantDept);

		Page<TenantDeptVo> tenantDeptVoPage = new Page<TenantDeptVo>(page, rows);
		tenantDeptVoPage.setCurrent(tenantDeptPage.getCurrent());
		tenantDeptVoPage.setPages(tenantDeptPage.getPages());
		tenantDeptVoPage.setSize(tenantDeptPage.getSize());
		tenantDeptVoPage.setTotal(tenantDeptPage.getTotal());
		tenantDeptVoPage.setRecords(tenantDeptPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantDeptVoPage;
	}
	
	@ApiOperation(value = "根据参数查询租户部门列表")
	@RequestMapping(value = "/tenant-depts/root", method = RequestMethod.GET)
	public Page<TenantDeptVo> pageRoot(@RequestBody TenantDeptQueryParam tenantDeptQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantDept> pageTenantDept = new Page<TenantDept>(page, rows);
		QueryWrapper<TenantDept> queryWrapperTenantDept = new QueryWrapper<TenantDept>();
		queryWrapperTenantDept.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantDept.lambda()
						.eq(tenantDeptQueryParam.getId() != null, TenantDept::getId, tenantDeptQueryParam.getId())
						.eq(tenantDeptQueryParam.getParentDeptId() != null, TenantDept::getParentDeptId, tenantDeptQueryParam.getParentDeptId())
						.eq(tenantDeptQueryParam.getTenantId() != null, TenantDept::getTenantId, tenantDeptQueryParam.getTenantId())
						.eq(tenantDeptQueryParam.getDeptName() != null, TenantDept::getDeptName, tenantDeptQueryParam.getDeptName())
						.isNull(TenantDept::getParentDeptId)
				;

		IPage<TenantDept> tenantDeptPage = tenantDeptService.page(pageTenantDept, queryWrapperTenantDept);

		Page<TenantDeptVo> tenantDeptVoPage = new Page<TenantDeptVo>(page, rows);
		tenantDeptVoPage.setCurrent(tenantDeptPage.getCurrent());
		tenantDeptVoPage.setPages(tenantDeptPage.getPages());
		tenantDeptVoPage.setSize(tenantDeptPage.getSize());
		tenantDeptVoPage.setTotal(tenantDeptPage.getTotal());
		tenantDeptVoPage.setRecords(tenantDeptPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantDeptVoPage;
	}

	@ApiOperation(value = "新增租户部门")
	@RequestMapping(value = "/tenant-depts", method = RequestMethod.POST)
	public TenantDeptVo save(@RequestBody TenantDept tenantDept) {
		if (tenantDept.getId() == null || tenantDept.getId().compareTo(0L) <= 0) {
			tenantDept.setId(idService.selectId());
		}
		boolean success = tenantDeptService.save(tenantDept);
		if (success) {
			TenantDept tenantDeptDatabase = tenantDeptService.getById(tenantDept.getId());
			return entity2vo(tenantDeptDatabase);
		}
		log.info("save TenantDept fail，{}", ToStringBuilder.reflectionToString(tenantDept, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户部门全部信息")
	@RequestMapping(value = "/tenant-depts/{id}", method = RequestMethod.PUT)
	public TenantDeptVo updateById(@PathVariable("id") Long id, @RequestBody TenantDept tenantDept) {
		tenantDept.setId(id);
		boolean success = tenantDeptService.updateById(tenantDept);
		if (success) {
			TenantDept tenantDeptDatabase = tenantDeptService.getById(id);
			return entity2vo(tenantDeptDatabase);
		}
		log.info("update TenantDept fail，{}", ToStringBuilder.reflectionToString(tenantDept, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户部门信息")
	@RequestMapping(value = "/tenant-depts/{id}", method = RequestMethod.PATCH)
	public TenantDeptVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantDept tenantDept) {
		UpdateWrapper<TenantDept> updateWrapperTenantDept = new UpdateWrapper<TenantDept>();
		updateWrapperTenantDept.lambda()//
				.eq(TenantDept::getId, id)
				// .set(tenantDept.getId() != null, TenantDept::getId, tenantDept.getId())
				.set(tenantDept.getParentDeptId() != null, TenantDept::getParentDeptId, tenantDept.getParentDeptId())
				.set(tenantDept.getTenantId() != null, TenantDept::getTenantId, tenantDept.getTenantId())
				.set(tenantDept.getDeptName() != null, TenantDept::getDeptName, tenantDept.getDeptName())
				;

		boolean success = tenantDeptService.update(updateWrapperTenantDept);
		if (success) {
			TenantDept tenantDeptDatabase = tenantDeptService.getById(id);
			return entity2vo(tenantDeptDatabase);
		}
		log.info("partial update TenantDept fail，{}",
				ToStringBuilder.reflectionToString(tenantDept, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户部门")
	@RequestMapping(value = "/tenant-depts/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantDeptService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantDeptVo entity2vo(TenantDept tenantDept) {
		String jsonString = JSON.toJSONString(tenantDept);
		TenantDeptVo tenantDeptVo = JSON.parseObject(jsonString, TenantDeptVo.class);
		if (StringUtils.isEmpty(tenantDeptVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantDept.getTenantId());
			if (tenantInfo != null) {
				tenantDeptVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantDeptVo;
	}

}
