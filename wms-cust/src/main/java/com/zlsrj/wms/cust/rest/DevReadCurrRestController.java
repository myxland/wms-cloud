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
import com.zlsrj.wms.api.dto.DevReadCurrQueryParam;
import com.zlsrj.wms.api.entity.DevReadCurr;
import com.zlsrj.wms.api.vo.DevReadCurrVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.cust.service.IIdService;
import com.zlsrj.wms.cust.service.IDevReadCurrService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "抄表信息", tags = { "抄表信息操作接口" })
@RestController
@Slf4j
public class DevReadCurrRestController {

	@Autowired
	private IDevReadCurrService devReadCurrService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询抄表信息")
	@RequestMapping(value = "/dev-read-currs/{id}", method = RequestMethod.GET)
	public DevReadCurrVo getById(@PathVariable("id") Long id) {
		DevReadCurr devReadCurr = devReadCurrService.getById(id);

		return entity2vo(devReadCurr);
	}

	@ApiOperation(value = "根据参数查询抄表信息列表")
	@RequestMapping(value = "/dev-read-currs", method = RequestMethod.GET)
	public Page<DevReadCurrVo> page(@RequestBody DevReadCurrQueryParam devReadCurrQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<DevReadCurr> pageDevReadCurr = new Page<DevReadCurr>(page, rows);
		QueryWrapper<DevReadCurr> queryWrapperDevReadCurr = new QueryWrapper<DevReadCurr>();
		queryWrapperDevReadCurr.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperDevReadCurr.lambda()
				.eq(devReadCurrQueryParam.getId() != null, DevReadCurr::getId, devReadCurrQueryParam.getId())
				.eq(devReadCurrQueryParam.getTenantId() != null, DevReadCurr::getTenantId, devReadCurrQueryParam.getTenantId())
				.eq(devReadCurrQueryParam.getReadMonth() != null, DevReadCurr::getReadMonth, devReadCurrQueryParam.getReadMonth())
				.eq(devReadCurrQueryParam.getDevId() != null, DevReadCurr::getDevId, devReadCurrQueryParam.getDevId())
				.eq(devReadCurrQueryParam.getParentDevId() != null, DevReadCurr::getParentDevId, devReadCurrQueryParam.getParentDevId())
				.eq(devReadCurrQueryParam.getYearUseNum() != null, DevReadCurr::getYearUseNum, devReadCurrQueryParam.getYearUseNum())
				.eq(devReadCurrQueryParam.getLastCalcDate() != null, DevReadCurr::getLastCalcDate, devReadCurrQueryParam.getLastCalcDate())
				.eq(devReadCurrQueryParam.getLastCalcCode() != null, DevReadCurr::getLastCalcCode, devReadCurrQueryParam.getLastCalcCode())
				.eq(devReadCurrQueryParam.getCurrReadEmpId() != null, DevReadCurr::getCurrReadEmpId, devReadCurrQueryParam.getCurrReadEmpId())
				.eq(devReadCurrQueryParam.getCurrReadDate() != null, DevReadCurr::getCurrReadDate, devReadCurrQueryParam.getCurrReadDate())
				.eq(devReadCurrQueryParam.getCurrReadCode() != null, DevReadCurr::getCurrReadCode, devReadCurrQueryParam.getCurrReadCode())
				.eq(devReadCurrQueryParam.getCurrDevStatus() != null, DevReadCurr::getCurrDevStatus, devReadCurrQueryParam.getCurrDevStatus())
				.eq(devReadCurrQueryParam.getCurrUseNum() != null, DevReadCurr::getCurrUseNum, devReadCurrQueryParam.getCurrUseNum())
				.eq(devReadCurrQueryParam.getCurrCalcUseNum() != null, DevReadCurr::getCurrCalcUseNum, devReadCurrQueryParam.getCurrCalcUseNum())
				.eq(devReadCurrQueryParam.getReadSource() != null, DevReadCurr::getReadSource, devReadCurrQueryParam.getReadSource())
				.eq(devReadCurrQueryParam.getReadStatus() != null, DevReadCurr::getReadStatus, devReadCurrQueryParam.getReadStatus())
				.eq(devReadCurrQueryParam.getCheckResult() != null, DevReadCurr::getCheckResult, devReadCurrQueryParam.getCheckResult())
				.eq(devReadCurrQueryParam.getProcReault() != null, DevReadCurr::getProcReault, devReadCurrQueryParam.getProcReault())
				.eq(devReadCurrQueryParam.getProcMan() != null, DevReadCurr::getProcMan, devReadCurrQueryParam.getProcMan())
				.eq(devReadCurrQueryParam.getProcTime() != null, DevReadCurr::getProcTime, devReadCurrQueryParam.getProcTime())
				;

		IPage<DevReadCurr> devReadCurrPage = devReadCurrService.page(pageDevReadCurr, queryWrapperDevReadCurr);

		Page<DevReadCurrVo> devReadCurrVoPage = new Page<DevReadCurrVo>(page, rows);
		devReadCurrVoPage.setCurrent(devReadCurrPage.getCurrent());
		devReadCurrVoPage.setPages(devReadCurrPage.getPages());
		devReadCurrVoPage.setSize(devReadCurrPage.getSize());
		devReadCurrVoPage.setTotal(devReadCurrPage.getTotal());
		devReadCurrVoPage.setRecords(devReadCurrPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return devReadCurrVoPage;
	}

	@ApiOperation(value = "新增抄表信息")
	@RequestMapping(value = "/dev-read-currs", method = RequestMethod.POST)
	public DevReadCurrVo save(@RequestBody DevReadCurr devReadCurr) {
		if (devReadCurr.getId() == null || devReadCurr.getId().compareTo(0L) <= 0) {
			devReadCurr.setId(idService.selectId());
		}
		boolean success = devReadCurrService.save(devReadCurr);
		if (success) {
			DevReadCurr devReadCurrDatabase = devReadCurrService.getById(devReadCurr.getId());
			return entity2vo(devReadCurrDatabase);
		}
		log.info("save DevReadCurr fail，{}", ToStringBuilder.reflectionToString(devReadCurr, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新抄表信息全部信息")
	@RequestMapping(value = "/dev-read-currs/{id}", method = RequestMethod.PUT)
	public DevReadCurrVo updateById(@PathVariable("id") Long id, @RequestBody DevReadCurr devReadCurr) {
		devReadCurr.setId(id);
		boolean success = devReadCurrService.updateById(devReadCurr);
		if (success) {
			DevReadCurr devReadCurrDatabase = devReadCurrService.getById(id);
			return entity2vo(devReadCurrDatabase);
		}
		log.info("update DevReadCurr fail，{}", ToStringBuilder.reflectionToString(devReadCurr, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新抄表信息信息")
	@RequestMapping(value = "/dev-read-currs/{id}", method = RequestMethod.PATCH)
	public DevReadCurrVo updatePatchById(@PathVariable("id") Long id, @RequestBody DevReadCurr devReadCurr) {
		UpdateWrapper<DevReadCurr> updateWrapperDevReadCurr = new UpdateWrapper<DevReadCurr>();
		updateWrapperDevReadCurr.lambda()//
				.eq(DevReadCurr::getId, id)
				// .set(devReadCurr.getId() != null, DevReadCurr::getId, devReadCurr.getId())
				.set(devReadCurr.getTenantId() != null, DevReadCurr::getTenantId, devReadCurr.getTenantId())
				.set(devReadCurr.getReadMonth() != null, DevReadCurr::getReadMonth, devReadCurr.getReadMonth())
				.set(devReadCurr.getDevId() != null, DevReadCurr::getDevId, devReadCurr.getDevId())
				.set(devReadCurr.getParentDevId() != null, DevReadCurr::getParentDevId, devReadCurr.getParentDevId())
				.set(devReadCurr.getYearUseNum() != null, DevReadCurr::getYearUseNum, devReadCurr.getYearUseNum())
				.set(devReadCurr.getLastCalcDate() != null, DevReadCurr::getLastCalcDate, devReadCurr.getLastCalcDate())
				.set(devReadCurr.getLastCalcCode() != null, DevReadCurr::getLastCalcCode, devReadCurr.getLastCalcCode())
				.set(devReadCurr.getCurrReadEmpId() != null, DevReadCurr::getCurrReadEmpId, devReadCurr.getCurrReadEmpId())
				.set(devReadCurr.getCurrReadDate() != null, DevReadCurr::getCurrReadDate, devReadCurr.getCurrReadDate())
				.set(devReadCurr.getCurrReadCode() != null, DevReadCurr::getCurrReadCode, devReadCurr.getCurrReadCode())
				.set(devReadCurr.getCurrDevStatus() != null, DevReadCurr::getCurrDevStatus, devReadCurr.getCurrDevStatus())
				.set(devReadCurr.getCurrUseNum() != null, DevReadCurr::getCurrUseNum, devReadCurr.getCurrUseNum())
				.set(devReadCurr.getCurrCalcUseNum() != null, DevReadCurr::getCurrCalcUseNum, devReadCurr.getCurrCalcUseNum())
				.set(devReadCurr.getReadSource() != null, DevReadCurr::getReadSource, devReadCurr.getReadSource())
				.set(devReadCurr.getReadStatus() != null, DevReadCurr::getReadStatus, devReadCurr.getReadStatus())
				.set(devReadCurr.getCheckResult() != null, DevReadCurr::getCheckResult, devReadCurr.getCheckResult())
				.set(devReadCurr.getProcReault() != null, DevReadCurr::getProcReault, devReadCurr.getProcReault())
				.set(devReadCurr.getProcMan() != null, DevReadCurr::getProcMan, devReadCurr.getProcMan())
				.set(devReadCurr.getProcTime() != null, DevReadCurr::getProcTime, devReadCurr.getProcTime())
				;

		boolean success = devReadCurrService.update(updateWrapperDevReadCurr);
		if (success) {
			DevReadCurr devReadCurrDatabase = devReadCurrService.getById(id);
			return entity2vo(devReadCurrDatabase);
		}
		log.info("partial update DevReadCurr fail，{}",
				ToStringBuilder.reflectionToString(devReadCurr, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除抄表信息")
	@RequestMapping(value = "/dev-read-currs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = devReadCurrService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private DevReadCurrVo entity2vo(DevReadCurr devReadCurr) {
		String jsonString = JSON.toJSONString(devReadCurr);
		DevReadCurrVo devReadCurrVo = JSON.parseObject(jsonString, DevReadCurrVo.class);
		return devReadCurrVo;
	}

}
