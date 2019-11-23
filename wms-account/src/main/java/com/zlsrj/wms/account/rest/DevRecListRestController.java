package com.zlsrj.wms.account.rest;

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
import com.zlsrj.wms.api.dto.DevRecListQueryParam;
import com.zlsrj.wms.api.entity.DevRecList;
import com.zlsrj.wms.api.vo.DevRecListVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.account.service.IIdService;
import com.zlsrj.wms.account.service.IDevRecListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "应收流水", tags = { "应收流水操作接口" })
@RestController
@Slf4j
public class DevRecListRestController {

	@Autowired
	private IDevRecListService devRecListService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询应收流水")
	@RequestMapping(value = "/dev-rec-lists/{id}", method = RequestMethod.GET)
	public DevRecListVo getById(@PathVariable("id") Long id) {
		DevRecList devRecList = devRecListService.getById(id);

		return entity2vo(devRecList);
	}

	@ApiOperation(value = "根据参数查询应收流水列表")
	@RequestMapping(value = "/dev-rec-lists", method = RequestMethod.GET)
	public Page<DevRecListVo> page(@RequestBody DevRecListQueryParam devRecListQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<DevRecList> pageDevRecList = new Page<DevRecList>(page, rows);
		QueryWrapper<DevRecList> queryWrapperDevRecList = new QueryWrapper<DevRecList>();
		queryWrapperDevRecList.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperDevRecList.lambda()
				.eq(devRecListQueryParam.getId() != null, DevRecList::getId, devRecListQueryParam.getId())
				.eq(devRecListQueryParam.getTenantId() != null, DevRecList::getTenantId, devRecListQueryParam.getTenantId())
				.eq(devRecListQueryParam.getRecFlag() != null, DevRecList::getRecFlag, devRecListQueryParam.getRecFlag())
				.eq(devRecListQueryParam.getRecType() != null, DevRecList::getRecType, devRecListQueryParam.getRecType())
				.eq(devRecListQueryParam.getDeptId() != null, DevRecList::getDeptId, devRecListQueryParam.getDeptId())
				.eq(devRecListQueryParam.getBookletId() != null, DevRecList::getBookletId, devRecListQueryParam.getBookletId())
				.eq(devRecListQueryParam.getCustId() != null, DevRecList::getCustId, devRecListQueryParam.getCustId())
				.eq(devRecListQueryParam.getCustName() != null, DevRecList::getCustName, devRecListQueryParam.getCustName())
				.eq(devRecListQueryParam.getCustAddress() != null, DevRecList::getCustAddress, devRecListQueryParam.getCustAddress())
				.eq(devRecListQueryParam.getDevId() != null, DevRecList::getDevId, devRecListQueryParam.getDevId())
				.eq(devRecListQueryParam.getDevAddress() != null, DevRecList::getDevAddress, devRecListQueryParam.getDevAddress())
				.eq(devRecListQueryParam.getReadMonth() != null, DevRecList::getReadMonth, devRecListQueryParam.getReadMonth())
				.eq(devRecListQueryParam.getRecMonth() != null, DevRecList::getRecMonth, devRecListQueryParam.getRecMonth())
				.eq(devRecListQueryParam.getBusinessId() != null, DevRecList::getBusinessId, devRecListQueryParam.getBusinessId())
				.eq(devRecListQueryParam.getReader() != null, DevRecList::getReader, devRecListQueryParam.getReader())
				.eq(devRecListQueryParam.getCalcDate() != null, DevRecList::getCalcDate, devRecListQueryParam.getCalcDate())
				.eq(devRecListQueryParam.getLastDate() != null, DevRecList::getLastDate, devRecListQueryParam.getLastDate())
				.eq(devRecListQueryParam.getLastCode() != null, DevRecList::getLastCode, devRecListQueryParam.getLastCode())
				.eq(devRecListQueryParam.getCurrDate() != null, DevRecList::getCurrDate, devRecListQueryParam.getCurrDate())
				.eq(devRecListQueryParam.getCurrCode() != null, DevRecList::getCurrCode, devRecListQueryParam.getCurrCode())
				.eq(devRecListQueryParam.getWaterScale() != null, DevRecList::getWaterScale, devRecListQueryParam.getWaterScale())
				.eq(devRecListQueryParam.getUseNum() != null, DevRecList::getUseNum, devRecListQueryParam.getUseNum())
				.eq(devRecListQueryParam.getPriceTypeId() != null, DevRecList::getPriceTypeId, devRecListQueryParam.getPriceTypeId())
				.eq(devRecListQueryParam.getRecMoney() != null, DevRecList::getRecMoney, devRecListQueryParam.getRecMoney())
				.eq(devRecListQueryParam.getDueMoney() != null, DevRecList::getDueMoney, devRecListQueryParam.getDueMoney())
				;

		IPage<DevRecList> devRecListPage = devRecListService.page(pageDevRecList, queryWrapperDevRecList);

		Page<DevRecListVo> devRecListVoPage = new Page<DevRecListVo>(page, rows);
		devRecListVoPage.setCurrent(devRecListPage.getCurrent());
		devRecListVoPage.setPages(devRecListPage.getPages());
		devRecListVoPage.setSize(devRecListPage.getSize());
		devRecListVoPage.setTotal(devRecListPage.getTotal());
		devRecListVoPage.setRecords(devRecListPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return devRecListVoPage;
	}

	@ApiOperation(value = "新增应收流水")
	@RequestMapping(value = "/dev-rec-lists", method = RequestMethod.POST)
	public DevRecListVo save(@RequestBody DevRecList devRecList) {
		if (devRecList.getId() == null || devRecList.getId().compareTo(0L) <= 0) {
			devRecList.setId(idService.selectId());
		}
		boolean success = devRecListService.save(devRecList);
		if (success) {
			DevRecList devRecListDatabase = devRecListService.getById(devRecList.getId());
			return entity2vo(devRecListDatabase);
		}
		log.info("save DevRecList fail，{}", ToStringBuilder.reflectionToString(devRecList, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新应收流水全部信息")
	@RequestMapping(value = "/dev-rec-lists/{id}", method = RequestMethod.PUT)
	public DevRecListVo updateById(@PathVariable("id") Long id, @RequestBody DevRecList devRecList) {
		devRecList.setId(id);
		boolean success = devRecListService.updateById(devRecList);
		if (success) {
			DevRecList devRecListDatabase = devRecListService.getById(id);
			return entity2vo(devRecListDatabase);
		}
		log.info("update DevRecList fail，{}", ToStringBuilder.reflectionToString(devRecList, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新应收流水信息")
	@RequestMapping(value = "/dev-rec-lists/{id}", method = RequestMethod.PATCH)
	public DevRecListVo updatePatchById(@PathVariable("id") Long id, @RequestBody DevRecList devRecList) {
		UpdateWrapper<DevRecList> updateWrapperDevRecList = new UpdateWrapper<DevRecList>();
		updateWrapperDevRecList.lambda()//
				.eq(DevRecList::getId, id)
				// .set(devRecList.getId() != null, DevRecList::getId, devRecList.getId())
				.set(devRecList.getTenantId() != null, DevRecList::getTenantId, devRecList.getTenantId())
				.set(devRecList.getRecFlag() != null, DevRecList::getRecFlag, devRecList.getRecFlag())
				.set(devRecList.getRecType() != null, DevRecList::getRecType, devRecList.getRecType())
				.set(devRecList.getDeptId() != null, DevRecList::getDeptId, devRecList.getDeptId())
				.set(devRecList.getBookletId() != null, DevRecList::getBookletId, devRecList.getBookletId())
				.set(devRecList.getCustId() != null, DevRecList::getCustId, devRecList.getCustId())
				.set(devRecList.getCustName() != null, DevRecList::getCustName, devRecList.getCustName())
				.set(devRecList.getCustAddress() != null, DevRecList::getCustAddress, devRecList.getCustAddress())
				.set(devRecList.getDevId() != null, DevRecList::getDevId, devRecList.getDevId())
				.set(devRecList.getDevAddress() != null, DevRecList::getDevAddress, devRecList.getDevAddress())
				.set(devRecList.getReadMonth() != null, DevRecList::getReadMonth, devRecList.getReadMonth())
				.set(devRecList.getRecMonth() != null, DevRecList::getRecMonth, devRecList.getRecMonth())
				.set(devRecList.getBusinessId() != null, DevRecList::getBusinessId, devRecList.getBusinessId())
				.set(devRecList.getReader() != null, DevRecList::getReader, devRecList.getReader())
				.set(devRecList.getCalcDate() != null, DevRecList::getCalcDate, devRecList.getCalcDate())
				.set(devRecList.getLastDate() != null, DevRecList::getLastDate, devRecList.getLastDate())
				.set(devRecList.getLastCode() != null, DevRecList::getLastCode, devRecList.getLastCode())
				.set(devRecList.getCurrDate() != null, DevRecList::getCurrDate, devRecList.getCurrDate())
				.set(devRecList.getCurrCode() != null, DevRecList::getCurrCode, devRecList.getCurrCode())
				.set(devRecList.getWaterScale() != null, DevRecList::getWaterScale, devRecList.getWaterScale())
				.set(devRecList.getUseNum() != null, DevRecList::getUseNum, devRecList.getUseNum())
				.set(devRecList.getPriceTypeId() != null, DevRecList::getPriceTypeId, devRecList.getPriceTypeId())
				.set(devRecList.getRecMoney() != null, DevRecList::getRecMoney, devRecList.getRecMoney())
				.set(devRecList.getDueMoney() != null, DevRecList::getDueMoney, devRecList.getDueMoney())
				;

		boolean success = devRecListService.update(updateWrapperDevRecList);
		if (success) {
			DevRecList devRecListDatabase = devRecListService.getById(id);
			return entity2vo(devRecListDatabase);
		}
		log.info("partial update DevRecList fail，{}",
				ToStringBuilder.reflectionToString(devRecList, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除应收流水")
	@RequestMapping(value = "/dev-rec-lists/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = devRecListService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private DevRecListVo entity2vo(DevRecList devRecList) {
		String jsonString = JSON.toJSONString(devRecList);
		DevRecListVo devRecListVo = JSON.parseObject(jsonString, DevRecListVo.class);
		return devRecListVo;
	}

}
