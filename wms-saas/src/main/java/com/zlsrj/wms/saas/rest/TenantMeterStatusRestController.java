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
import com.zlsrj.wms.api.dto.TenantMeterStatusQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterStatus;
import com.zlsrj.wms.api.vo.TenantMeterStatusVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterStatusService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表状况", tags = { "水表状况操作接口" })
@RestController
@Slf4j
public class TenantMeterStatusRestController {

	@Autowired
	private ITenantMeterStatusService tenantMeterStatusService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询水表状况")
	@RequestMapping(value = "/tenant-meter-statuss/{id}", method = RequestMethod.GET)
	public TenantMeterStatusVo getById(@PathVariable("id") Long id) {
		TenantMeterStatus tenantMeterStatus = tenantMeterStatusService.getById(id);

		return entity2vo(tenantMeterStatus);
	}

	@ApiOperation(value = "根据参数查询水表状况列表")
	@RequestMapping(value = "/tenant-meter-statuss", method = RequestMethod.GET)
	public Page<TenantMeterStatusVo> page(@RequestBody TenantMeterStatusQueryParam tenantMeterStatusQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterStatus> pageTenantMeterStatus = new Page<TenantMeterStatus>(page, rows);
		QueryWrapper<TenantMeterStatus> queryWrapperTenantMeterStatus = new QueryWrapper<TenantMeterStatus>();
		queryWrapperTenantMeterStatus.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterStatus.lambda()
				.eq(tenantMeterStatusQueryParam.getId() != null, TenantMeterStatus::getId, tenantMeterStatusQueryParam.getId())
				.eq(tenantMeterStatusQueryParam.getTenantId() != null, TenantMeterStatus::getTenantId, tenantMeterStatusQueryParam.getTenantId())
				.eq(tenantMeterStatusQueryParam.getMeterStatusName() != null, TenantMeterStatus::getMeterStatusName, tenantMeterStatusQueryParam.getMeterStatusName())
				.eq(tenantMeterStatusQueryParam.getUsenumCalcType() != null, TenantMeterStatus::getUsenumCalcType, tenantMeterStatusQueryParam.getUsenumCalcType())
				.eq(tenantMeterStatusQueryParam.getWorkBillType() != null, TenantMeterStatus::getWorkBillType, tenantMeterStatusQueryParam.getWorkBillType())
				.eq(tenantMeterStatusQueryParam.getCreateType() != null, TenantMeterStatus::getCreateType, tenantMeterStatusQueryParam.getCreateType())
				;

		IPage<TenantMeterStatus> tenantMeterStatusPage = tenantMeterStatusService.page(pageTenantMeterStatus, queryWrapperTenantMeterStatus);

		Page<TenantMeterStatusVo> tenantMeterStatusVoPage = new Page<TenantMeterStatusVo>(page, rows);
		tenantMeterStatusVoPage.setCurrent(tenantMeterStatusPage.getCurrent());
		tenantMeterStatusVoPage.setPages(tenantMeterStatusPage.getPages());
		tenantMeterStatusVoPage.setSize(tenantMeterStatusPage.getSize());
		tenantMeterStatusVoPage.setTotal(tenantMeterStatusPage.getTotal());
		tenantMeterStatusVoPage.setRecords(tenantMeterStatusPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterStatusVoPage;
	}

	@ApiOperation(value = "新增水表状况")
	@RequestMapping(value = "/tenant-meter-statuss", method = RequestMethod.POST)
	public TenantMeterStatusVo save(@RequestBody TenantMeterStatus tenantMeterStatus) {
		if (tenantMeterStatus.getId() == null || tenantMeterStatus.getId().compareTo(0L) <= 0) {
			tenantMeterStatus.setId(idService.selectId());
		}
		boolean success = tenantMeterStatusService.save(tenantMeterStatus);
		if (success) {
			TenantMeterStatus tenantMeterStatusDatabase = tenantMeterStatusService.getById(tenantMeterStatus.getId());
			return entity2vo(tenantMeterStatusDatabase);
		}
		log.info("save TenantMeterStatus fail，{}", ToStringBuilder.reflectionToString(tenantMeterStatus, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新水表状况全部信息")
	@RequestMapping(value = "/tenant-meter-statuss/{id}", method = RequestMethod.PUT)
	public TenantMeterStatusVo updateById(@PathVariable("id") Long id, @RequestBody TenantMeterStatus tenantMeterStatus) {
		tenantMeterStatus.setId(id);
		boolean success = tenantMeterStatusService.updateById(tenantMeterStatus);
		if (success) {
			TenantMeterStatus tenantMeterStatusDatabase = tenantMeterStatusService.getById(id);
			return entity2vo(tenantMeterStatusDatabase);
		}
		log.info("update TenantMeterStatus fail，{}", ToStringBuilder.reflectionToString(tenantMeterStatus, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新水表状况信息")
	@RequestMapping(value = "/tenant-meter-statuss/{id}", method = RequestMethod.PATCH)
	public TenantMeterStatusVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantMeterStatus tenantMeterStatus) {
        TenantMeterStatus tenantMeterStatusWhere = TenantMeterStatus.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterStatus> updateWrapperTenantMeterStatus = new UpdateWrapper<TenantMeterStatus>();
		updateWrapperTenantMeterStatus.setEntity(tenantMeterStatusWhere);
		updateWrapperTenantMeterStatus.lambda()//
				//.eq(TenantMeterStatus::getId, id)
				// .set(tenantMeterStatus.getId() != null, TenantMeterStatus::getId, tenantMeterStatus.getId())
				.set(tenantMeterStatus.getTenantId() != null, TenantMeterStatus::getTenantId, tenantMeterStatus.getTenantId())
				.set(tenantMeterStatus.getMeterStatusName() != null, TenantMeterStatus::getMeterStatusName, tenantMeterStatus.getMeterStatusName())
				.set(tenantMeterStatus.getUsenumCalcType() != null, TenantMeterStatus::getUsenumCalcType, tenantMeterStatus.getUsenumCalcType())
				.set(tenantMeterStatus.getWorkBillType() != null, TenantMeterStatus::getWorkBillType, tenantMeterStatus.getWorkBillType())
				.set(tenantMeterStatus.getCreateType() != null, TenantMeterStatus::getCreateType, tenantMeterStatus.getCreateType())
				;

		boolean success = tenantMeterStatusService.update(updateWrapperTenantMeterStatus);
		if (success) {
			TenantMeterStatus tenantMeterStatusDatabase = tenantMeterStatusService.getById(id);
			return entity2vo(tenantMeterStatusDatabase);
		}
		log.info("partial update TenantMeterStatus fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterStatus, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水表状况")
	@RequestMapping(value = "/tenant-meter-statuss/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantMeterStatusService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterStatusVo entity2vo(TenantMeterStatus tenantMeterStatus) {
		if (tenantMeterStatus == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterStatus);
		TenantMeterStatusVo tenantMeterStatusVo = JSON.parseObject(jsonString, TenantMeterStatusVo.class);
		if (StringUtils.isEmpty(tenantMeterStatusVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantMeterStatus.getTenantId());
			if (tenantInfo != null) {
				tenantMeterStatusVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterStatusVo;
	}

}
