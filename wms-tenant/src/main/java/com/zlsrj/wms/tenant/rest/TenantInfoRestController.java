package com.zlsrj.wms.tenant.rest;

import java.util.Date;
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
import com.zlsrj.wms.api.dto.TenantInfoQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantInfoService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户", tags = { "租户操作接口" })
@RestController
@Slf4j
public class TenantInfoRestController {

	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户")
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.GET)
	public TenantInfoVo getById(@PathVariable("id") Long id) {
		TenantInfo tenantInfo = tenantInfoService.getById(id);

		return entity2vo(tenantInfo);
	}

	@ApiOperation(value = "根据参数查询租户列表")
	@RequestMapping(value = "/tenant-infos", method = RequestMethod.GET)
	public Page<TenantInfoVo> page(@RequestBody TenantInfoQueryParam tenantInfoQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantInfo> pageTenantInfo = new Page<TenantInfo>(page, rows);
		QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
		queryWrapperTenantInfo.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantInfo.lambda()
				.eq(tenantInfoQueryParam.getId() != null, TenantInfo::getId, tenantInfoQueryParam.getId())
				.eq(tenantInfoQueryParam.getTenantName() != null, TenantInfo::getTenantName, tenantInfoQueryParam.getTenantName())
				
				;

		IPage<TenantInfo> tenantInfoPage = tenantInfoService.page(pageTenantInfo, queryWrapperTenantInfo);

		Page<TenantInfoVo> tenantInfoVoPage = new Page<TenantInfoVo>(page, rows);
		tenantInfoVoPage.setCurrent(tenantInfoPage.getCurrent());
		tenantInfoVoPage.setPages(tenantInfoPage.getPages());
		tenantInfoVoPage.setSize(tenantInfoPage.getSize());
		tenantInfoVoPage.setTotal(tenantInfoPage.getTotal());
		tenantInfoVoPage.setRecords(tenantInfoPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantInfoVoPage;
	}

	@ApiOperation(value = "新增租户")
	@RequestMapping(value = "/tenant-infos", method = RequestMethod.POST)
	public TenantInfoVo save(@RequestBody TenantInfo tenantInfo) {
		if (tenantInfo.getId() == null || tenantInfo.getId().compareTo(0L) <= 0) {
			tenantInfo.setId(idService.selectId());
		}
		boolean success = tenantInfoService.save(tenantInfo);
		if (success) {
			TenantInfo tenantInfoDatabase = tenantInfoService.getById(tenantInfo.getId());
			return entity2vo(tenantInfoDatabase);
		}
		log.info("save TenantInfo fail，{}", ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户全部信息")
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.PUT)
	public TenantInfoVo updateById(@PathVariable("id") Long id, @RequestBody TenantInfo tenantInfo) {
		tenantInfo.setId(id);
		boolean success = tenantInfoService.updateById(tenantInfo);
		if (success) {
			TenantInfo tenantInfoDatabase = tenantInfoService.getById(id);
			return entity2vo(tenantInfoDatabase);
		}
		log.info("update TenantInfo fail，{}", ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户信息")
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.PATCH)
	public TenantInfoVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantInfo tenantInfo) {
        TenantInfo tenantInfoWhere = TenantInfo.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantInfo> updateWrapperTenantInfo = new UpdateWrapper<TenantInfo>();
		updateWrapperTenantInfo.setEntity(tenantInfoWhere);
		updateWrapperTenantInfo.lambda()//
				//.eq(TenantInfo::getId, id)
				// .set(tenantInfo.getId() != null, TenantInfo::getId, tenantInfo.getId())
				.set(tenantInfo.getTenantName() != null, TenantInfo::getTenantName, tenantInfo.getTenantName())
				;

		boolean success = tenantInfoService.update(updateWrapperTenantInfo);
		if (success) {
			TenantInfo tenantInfoDatabase = tenantInfoService.getById(id);
			return entity2vo(tenantInfoDatabase);
		}
		log.info("partial update TenantInfo fail，{}",
				ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户")
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantInfoService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantInfoVo entity2vo(TenantInfo tenantInfo) {
		String jsonString = JSON.toJSONString(tenantInfo);
		TenantInfoVo tenantInfoVo = JSON.parseObject(jsonString, TenantInfoVo.class);
		return tenantInfoVo;
	}

}
