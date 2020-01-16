package com.zlsrj.wms.saas.rest;

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
import com.zlsrj.wms.api.dto.TenantDepartmentQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantDepartment;
import com.zlsrj.wms.api.vo.TenantDepartmentVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantDepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户部门", tags = { "租户部门操作接口" })
@RestController
@Slf4j
public class TenantDepartmentRestController {

	@Autowired
	private ITenantDepartmentService tenantDepartmentService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户部门")
	@RequestMapping(value = "/tenant-departments/{id}", method = RequestMethod.GET)
	public TenantDepartmentVo getById(@PathVariable("id") Long id) {
		TenantDepartment tenantDepartment = tenantDepartmentService.getById(id);

		return entity2vo(tenantDepartment);
	}

	@ApiOperation(value = "根据参数查询租户部门列表")
	@RequestMapping(value = "/tenant-departments", method = RequestMethod.GET)
	public Page<TenantDepartmentVo> page(@RequestBody TenantDepartmentQueryParam tenantDepartmentQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantDepartment> pageTenantDepartment = new Page<TenantDepartment>(page, rows);
		QueryWrapper<TenantDepartment> queryWrapperTenantDepartment = new QueryWrapper<TenantDepartment>();
		queryWrapperTenantDepartment.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantDepartment.lambda()
				.eq(tenantDepartmentQueryParam.getId() != null, TenantDepartment::getId, tenantDepartmentQueryParam.getId())
				.eq(tenantDepartmentQueryParam.getTenantId() != null, TenantDepartment::getTenantId, tenantDepartmentQueryParam.getTenantId())
				.eq(tenantDepartmentQueryParam.getDepartmentName() != null, TenantDepartment::getDepartmentName, tenantDepartmentQueryParam.getDepartmentName())
				.eq(tenantDepartmentQueryParam.getDepartmentParentId() != null, TenantDepartment::getDepartmentParentId, tenantDepartmentQueryParam.getDepartmentParentId())
				.eq(tenantDepartmentQueryParam.getParentId()!=null,TenantDepartment::getDepartmentParentId, tenantDepartmentQueryParam.getParentId())
				.isNull(tenantDepartmentQueryParam.getParentId()==null, TenantDepartment::getDepartmentParentId)
				;

		IPage<TenantDepartment> tenantDepartmentPage = tenantDepartmentService.page(pageTenantDepartment, queryWrapperTenantDepartment);

		Page<TenantDepartmentVo> tenantDepartmentVoPage = new Page<TenantDepartmentVo>(page, rows);
		tenantDepartmentVoPage.setCurrent(tenantDepartmentPage.getCurrent());
		tenantDepartmentVoPage.setPages(tenantDepartmentPage.getPages());
		tenantDepartmentVoPage.setSize(tenantDepartmentPage.getSize());
		tenantDepartmentVoPage.setTotal(tenantDepartmentPage.getTotal());
		tenantDepartmentVoPage.setRecords(tenantDepartmentPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantDepartmentVoPage;
	}

	@ApiOperation(value = "新增租户部门")
	@RequestMapping(value = "/tenant-departments", method = RequestMethod.POST)
	public TenantDepartmentVo save(@RequestBody TenantDepartment tenantDepartment) {
		if (tenantDepartment.getId() == null || tenantDepartment.getId().compareTo(0L) <= 0) {
			tenantDepartment.setId(idService.selectId());
		}
		boolean success = tenantDepartmentService.save(tenantDepartment);
		if (success) {
			TenantDepartment tenantDepartmentDatabase = tenantDepartmentService.getById(tenantDepartment.getId());
			return entity2vo(tenantDepartmentDatabase);
		}
		log.info("save TenantDepartment fail，{}", ToStringBuilder.reflectionToString(tenantDepartment, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户部门全部信息")
	@RequestMapping(value = "/tenant-departments/{id}", method = RequestMethod.PUT)
	public TenantDepartmentVo updateById(@PathVariable("id") Long id, @RequestBody TenantDepartment tenantDepartment) {
		tenantDepartment.setId(id);
		boolean success = tenantDepartmentService.updateById(tenantDepartment);
		if (success) {
			TenantDepartment tenantDepartmentDatabase = tenantDepartmentService.getById(id);
			return entity2vo(tenantDepartmentDatabase);
		}
		log.info("update TenantDepartment fail，{}", ToStringBuilder.reflectionToString(tenantDepartment, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户部门信息")
	@RequestMapping(value = "/tenant-departments/{id}", method = RequestMethod.PATCH)
	public TenantDepartmentVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantDepartment tenantDepartment) {
        TenantDepartment tenantDepartmentWhere = TenantDepartment.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantDepartment> updateWrapperTenantDepartment = new UpdateWrapper<TenantDepartment>();
		updateWrapperTenantDepartment.setEntity(tenantDepartmentWhere);
		updateWrapperTenantDepartment.lambda()//
				//.eq(TenantDepartment::getId, id)
				// .set(tenantDepartment.getId() != null, TenantDepartment::getId, tenantDepartment.getId())
				.set(tenantDepartment.getTenantId() != null, TenantDepartment::getTenantId, tenantDepartment.getTenantId())
				.set(tenantDepartment.getDepartmentName() != null, TenantDepartment::getDepartmentName, tenantDepartment.getDepartmentName())
				.set(tenantDepartment.getDepartmentParentId() != null, TenantDepartment::getDepartmentParentId, tenantDepartment.getDepartmentParentId())
				;

		boolean success = tenantDepartmentService.update(updateWrapperTenantDepartment);
		if (success) {
			TenantDepartment tenantDepartmentDatabase = tenantDepartmentService.getById(id);
			return entity2vo(tenantDepartmentDatabase);
		}
		log.info("partial update TenantDepartment fail，{}",
				ToStringBuilder.reflectionToString(tenantDepartment, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户部门")
	@RequestMapping(value = "/tenant-departments/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantDepartmentService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantDepartmentVo entity2vo(TenantDepartment tenantDepartment) {
		if (tenantDepartment == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantDepartment);
		TenantDepartmentVo tenantDepartmentVo = JSON.parseObject(jsonString, TenantDepartmentVo.class);
		if (StringUtils.isEmpty(tenantDepartmentVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantDepartment.getTenantId());
			if (tenantInfo != null) {
				tenantDepartmentVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantDepartmentVo;
	}

}
