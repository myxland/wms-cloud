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
import com.zlsrj.wms.api.dto.DevReadCurrHisQueryParam;
import com.zlsrj.wms.api.entity.DevReadCurrHis;
import com.zlsrj.wms.api.vo.DevReadCurrHisVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.cust.service.IIdService;
import com.zlsrj.wms.cust.service.IDevReadCurrHisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "历史抄表信息", tags = { "历史抄表信息操作接口" })
@RestController
@Slf4j
public class DevReadCurrHisRestController {

	@Autowired
	private IDevReadCurrHisService devReadCurrHisService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询历史抄表信息")
	@RequestMapping(value = "/dev-read-curr-hiss/{id}", method = RequestMethod.GET)
	public DevReadCurrHisVo getById(@PathVariable("id") Long id) {
		DevReadCurrHis devReadCurrHis = devReadCurrHisService.getById(id);

		return entity2vo(devReadCurrHis);
	}

	@ApiOperation(value = "根据参数查询历史抄表信息列表")
	@RequestMapping(value = "/dev-read-curr-hiss", method = RequestMethod.GET)
	public Page<DevReadCurrHisVo> page(@RequestBody DevReadCurrHisQueryParam devReadCurrHisQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<DevReadCurrHis> pageDevReadCurrHis = new Page<DevReadCurrHis>(page, rows);
		QueryWrapper<DevReadCurrHis> queryWrapperDevReadCurrHis = new QueryWrapper<DevReadCurrHis>();
		queryWrapperDevReadCurrHis.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperDevReadCurrHis.lambda()
				.eq(devReadCurrHisQueryParam.getId() != null, DevReadCurrHis::getId, devReadCurrHisQueryParam.getId())
				.eq(devReadCurrHisQueryParam.getTenantId() != null, DevReadCurrHis::getTenantId, devReadCurrHisQueryParam.getTenantId())
				.eq(devReadCurrHisQueryParam.getReadMonth() != null, DevReadCurrHis::getReadMonth, devReadCurrHisQueryParam.getReadMonth())
				.eq(devReadCurrHisQueryParam.getDevId() != null, DevReadCurrHis::getDevId, devReadCurrHisQueryParam.getDevId())
				.eq(devReadCurrHisQueryParam.getParentDevId() != null, DevReadCurrHis::getParentDevId, devReadCurrHisQueryParam.getParentDevId())
				.eq(devReadCurrHisQueryParam.getYearUseNum() != null, DevReadCurrHis::getYearUseNum, devReadCurrHisQueryParam.getYearUseNum())
				.eq(devReadCurrHisQueryParam.getLastCalcDate() != null, DevReadCurrHis::getLastCalcDate, devReadCurrHisQueryParam.getLastCalcDate())
				.eq(devReadCurrHisQueryParam.getLastCalcCode() != null, DevReadCurrHis::getLastCalcCode, devReadCurrHisQueryParam.getLastCalcCode())
				.eq(devReadCurrHisQueryParam.getCurrReadEmpId() != null, DevReadCurrHis::getCurrReadEmpId, devReadCurrHisQueryParam.getCurrReadEmpId())
				.eq(devReadCurrHisQueryParam.getCurrReadDate() != null, DevReadCurrHis::getCurrReadDate, devReadCurrHisQueryParam.getCurrReadDate())
				.eq(devReadCurrHisQueryParam.getCurrReadCode() != null, DevReadCurrHis::getCurrReadCode, devReadCurrHisQueryParam.getCurrReadCode())
				.eq(devReadCurrHisQueryParam.getCurrDevStatus() != null, DevReadCurrHis::getCurrDevStatus, devReadCurrHisQueryParam.getCurrDevStatus())
				.eq(devReadCurrHisQueryParam.getCurrUseNum() != null, DevReadCurrHis::getCurrUseNum, devReadCurrHisQueryParam.getCurrUseNum())
				.eq(devReadCurrHisQueryParam.getCurrCalcUseNum() != null, DevReadCurrHis::getCurrCalcUseNum, devReadCurrHisQueryParam.getCurrCalcUseNum())
				.eq(devReadCurrHisQueryParam.getReadSource() != null, DevReadCurrHis::getReadSource, devReadCurrHisQueryParam.getReadSource())
				.eq(devReadCurrHisQueryParam.getReadStatus() != null, DevReadCurrHis::getReadStatus, devReadCurrHisQueryParam.getReadStatus())
				.eq(devReadCurrHisQueryParam.getCheckResult() != null, DevReadCurrHis::getCheckResult, devReadCurrHisQueryParam.getCheckResult())
				.eq(devReadCurrHisQueryParam.getProcReault() != null, DevReadCurrHis::getProcReault, devReadCurrHisQueryParam.getProcReault())
				.eq(devReadCurrHisQueryParam.getProcMan() != null, DevReadCurrHis::getProcMan, devReadCurrHisQueryParam.getProcMan())
				.eq(devReadCurrHisQueryParam.getProcTime() != null, DevReadCurrHis::getProcTime, devReadCurrHisQueryParam.getProcTime())
				;

		IPage<DevReadCurrHis> devReadCurrHisPage = devReadCurrHisService.page(pageDevReadCurrHis, queryWrapperDevReadCurrHis);

		Page<DevReadCurrHisVo> devReadCurrHisVoPage = new Page<DevReadCurrHisVo>(page, rows);
		devReadCurrHisVoPage.setCurrent(devReadCurrHisPage.getCurrent());
		devReadCurrHisVoPage.setPages(devReadCurrHisPage.getPages());
		devReadCurrHisVoPage.setSize(devReadCurrHisPage.getSize());
		devReadCurrHisVoPage.setTotal(devReadCurrHisPage.getTotal());
		devReadCurrHisVoPage.setRecords(devReadCurrHisPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return devReadCurrHisVoPage;
	}

	@ApiOperation(value = "新增历史抄表信息")
	@RequestMapping(value = "/dev-read-curr-hiss", method = RequestMethod.POST)
	public DevReadCurrHisVo save(@RequestBody DevReadCurrHis devReadCurrHis) {
		if (devReadCurrHis.getId() == null || devReadCurrHis.getId().compareTo(0L) <= 0) {
			devReadCurrHis.setId(idService.selectId());
		}
		boolean success = devReadCurrHisService.save(devReadCurrHis);
		if (success) {
			DevReadCurrHis devReadCurrHisDatabase = devReadCurrHisService.getById(devReadCurrHis.getId());
			return entity2vo(devReadCurrHisDatabase);
		}
		log.info("save DevReadCurrHis fail，{}", ToStringBuilder.reflectionToString(devReadCurrHis, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新历史抄表信息全部信息")
	@RequestMapping(value = "/dev-read-curr-hiss/{id}", method = RequestMethod.PUT)
	public DevReadCurrHisVo updateById(@PathVariable("id") Long id, @RequestBody DevReadCurrHis devReadCurrHis) {
		devReadCurrHis.setId(id);
		boolean success = devReadCurrHisService.updateById(devReadCurrHis);
		if (success) {
			DevReadCurrHis devReadCurrHisDatabase = devReadCurrHisService.getById(id);
			return entity2vo(devReadCurrHisDatabase);
		}
		log.info("update DevReadCurrHis fail，{}", ToStringBuilder.reflectionToString(devReadCurrHis, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新历史抄表信息信息")
	@RequestMapping(value = "/dev-read-curr-hiss/{id}", method = RequestMethod.PATCH)
	public DevReadCurrHisVo updatePatchById(@PathVariable("id") Long id, @RequestBody DevReadCurrHis devReadCurrHis) {
		UpdateWrapper<DevReadCurrHis> updateWrapperDevReadCurrHis = new UpdateWrapper<DevReadCurrHis>();
		updateWrapperDevReadCurrHis.lambda()//
				.eq(DevReadCurrHis::getId, id)
				// .set(devReadCurrHis.getId() != null, DevReadCurrHis::getId, devReadCurrHis.getId())
				.set(devReadCurrHis.getTenantId() != null, DevReadCurrHis::getTenantId, devReadCurrHis.getTenantId())
				.set(devReadCurrHis.getReadMonth() != null, DevReadCurrHis::getReadMonth, devReadCurrHis.getReadMonth())
				.set(devReadCurrHis.getDevId() != null, DevReadCurrHis::getDevId, devReadCurrHis.getDevId())
				.set(devReadCurrHis.getParentDevId() != null, DevReadCurrHis::getParentDevId, devReadCurrHis.getParentDevId())
				.set(devReadCurrHis.getYearUseNum() != null, DevReadCurrHis::getYearUseNum, devReadCurrHis.getYearUseNum())
				.set(devReadCurrHis.getLastCalcDate() != null, DevReadCurrHis::getLastCalcDate, devReadCurrHis.getLastCalcDate())
				.set(devReadCurrHis.getLastCalcCode() != null, DevReadCurrHis::getLastCalcCode, devReadCurrHis.getLastCalcCode())
				.set(devReadCurrHis.getCurrReadEmpId() != null, DevReadCurrHis::getCurrReadEmpId, devReadCurrHis.getCurrReadEmpId())
				.set(devReadCurrHis.getCurrReadDate() != null, DevReadCurrHis::getCurrReadDate, devReadCurrHis.getCurrReadDate())
				.set(devReadCurrHis.getCurrReadCode() != null, DevReadCurrHis::getCurrReadCode, devReadCurrHis.getCurrReadCode())
				.set(devReadCurrHis.getCurrDevStatus() != null, DevReadCurrHis::getCurrDevStatus, devReadCurrHis.getCurrDevStatus())
				.set(devReadCurrHis.getCurrUseNum() != null, DevReadCurrHis::getCurrUseNum, devReadCurrHis.getCurrUseNum())
				.set(devReadCurrHis.getCurrCalcUseNum() != null, DevReadCurrHis::getCurrCalcUseNum, devReadCurrHis.getCurrCalcUseNum())
				.set(devReadCurrHis.getReadSource() != null, DevReadCurrHis::getReadSource, devReadCurrHis.getReadSource())
				.set(devReadCurrHis.getReadStatus() != null, DevReadCurrHis::getReadStatus, devReadCurrHis.getReadStatus())
				.set(devReadCurrHis.getCheckResult() != null, DevReadCurrHis::getCheckResult, devReadCurrHis.getCheckResult())
				.set(devReadCurrHis.getProcReault() != null, DevReadCurrHis::getProcReault, devReadCurrHis.getProcReault())
				.set(devReadCurrHis.getProcMan() != null, DevReadCurrHis::getProcMan, devReadCurrHis.getProcMan())
				.set(devReadCurrHis.getProcTime() != null, DevReadCurrHis::getProcTime, devReadCurrHis.getProcTime())
				;

		boolean success = devReadCurrHisService.update(updateWrapperDevReadCurrHis);
		if (success) {
			DevReadCurrHis devReadCurrHisDatabase = devReadCurrHisService.getById(id);
			return entity2vo(devReadCurrHisDatabase);
		}
		log.info("partial update DevReadCurrHis fail，{}",
				ToStringBuilder.reflectionToString(devReadCurrHis, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除历史抄表信息")
	@RequestMapping(value = "/dev-read-curr-hiss/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = devReadCurrHisService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private DevReadCurrHisVo entity2vo(DevReadCurrHis devReadCurrHis) {
		String jsonString = JSON.toJSONString(devReadCurrHis);
		DevReadCurrHisVo devReadCurrHisVo = JSON.parseObject(jsonString, DevReadCurrHisVo.class);
		return devReadCurrHisVo;
	}

}
