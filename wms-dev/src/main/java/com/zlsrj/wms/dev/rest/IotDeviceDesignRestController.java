package com.zlsrj.wms.dev.rest;

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
import com.zlsrj.wms.api.dto.IotDeviceDesignQueryParam;
import com.zlsrj.wms.api.entity.IotDeviceDesign;
import com.zlsrj.wms.api.vo.IotDeviceDesignVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.dev.service.IIdService;
import com.zlsrj.wms.dev.service.IIotDeviceDesignService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "设备信息", tags = { "设备信息操作接口" })
@RestController
@Slf4j
public class IotDeviceDesignRestController {

	@Autowired
	private IIotDeviceDesignService iotDeviceDesignService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询设备信息")
	@RequestMapping(value = "/iot-device-designs/{id}", method = RequestMethod.GET)
	public IotDeviceDesignVo getById(@PathVariable("id") Long id) {
		IotDeviceDesign iotDeviceDesign = iotDeviceDesignService.getById(id);

		return entity2vo(iotDeviceDesign);
	}

	@ApiOperation(value = "根据参数查询设备信息列表")
	@RequestMapping(value = "/iot-device-designs", method = RequestMethod.GET)
	public Page<IotDeviceDesignVo> page(@RequestBody IotDeviceDesignQueryParam iotDeviceDesignQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<IotDeviceDesign> pageIotDeviceDesign = new Page<IotDeviceDesign>(page, rows);
		QueryWrapper<IotDeviceDesign> queryWrapperIotDeviceDesign = new QueryWrapper<IotDeviceDesign>();
		queryWrapperIotDeviceDesign.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperIotDeviceDesign.lambda()
				.eq(iotDeviceDesignQueryParam.getId() != null, IotDeviceDesign::getId, iotDeviceDesignQueryParam.getId())
				.eq(iotDeviceDesignQueryParam.getParentId() != null, IotDeviceDesign::getParentId, iotDeviceDesignQueryParam.getParentId())
				.eq(iotDeviceDesignQueryParam.getDevType() != null, IotDeviceDesign::getDevType, iotDeviceDesignQueryParam.getDevType())
				.eq(iotDeviceDesignQueryParam.getDevVerId() != null, IotDeviceDesign::getDevVerId, iotDeviceDesignQueryParam.getDevVerId())
				.eq(iotDeviceDesignQueryParam.getDevMacId() != null, IotDeviceDesign::getDevMacId, iotDeviceDesignQueryParam.getDevMacId())
				.eq(iotDeviceDesignQueryParam.getImei() != null, IotDeviceDesign::getImei, iotDeviceDesignQueryParam.getImei())
				.eq(iotDeviceDesignQueryParam.getImsi() != null, IotDeviceDesign::getImsi, iotDeviceDesignQueryParam.getImsi())
				.eq(iotDeviceDesignQueryParam.getAreaId() != null, IotDeviceDesign::getAreaId, iotDeviceDesignQueryParam.getAreaId())
				.eq(iotDeviceDesignQueryParam.getCustId() != null, IotDeviceDesign::getCustId, iotDeviceDesignQueryParam.getCustId())
				.eq(iotDeviceDesignQueryParam.getInstAddress() != null, IotDeviceDesign::getInstAddress, iotDeviceDesignQueryParam.getInstAddress())
				.eq(iotDeviceDesignQueryParam.getMapX() != null, IotDeviceDesign::getMapX, iotDeviceDesignQueryParam.getMapX())
				.eq(iotDeviceDesignQueryParam.getMapY() != null, IotDeviceDesign::getMapY, iotDeviceDesignQueryParam.getMapY())
				.eq(iotDeviceDesignQueryParam.getMapIcon() != null, IotDeviceDesign::getMapIcon, iotDeviceDesignQueryParam.getMapIcon())
				.eq(iotDeviceDesignQueryParam.getFactId() != null, IotDeviceDesign::getFactId, iotDeviceDesignQueryParam.getFactId())
				.eq(iotDeviceDesignQueryParam.getUserId() != null, IotDeviceDesign::getUserId, iotDeviceDesignQueryParam.getUserId())
				.eq(iotDeviceDesignQueryParam.getDevStatus() != null, IotDeviceDesign::getDevStatus, iotDeviceDesignQueryParam.getDevStatus())
				.eq(iotDeviceDesignQueryParam.getDevRegTime() != null, IotDeviceDesign::getDevRegTime, iotDeviceDesignQueryParam.getDevRegTime())
				.eq(iotDeviceDesignQueryParam.getDevRegister() != null, IotDeviceDesign::getDevRegister, iotDeviceDesignQueryParam.getDevRegister())
				.eq(iotDeviceDesignQueryParam.getDevSegWay() != null, IotDeviceDesign::getDevSegWay, iotDeviceDesignQueryParam.getDevSegWay())
				.eq(iotDeviceDesignQueryParam.getDevSendoutTime() != null, IotDeviceDesign::getDevSendoutTime, iotDeviceDesignQueryParam.getDevSendoutTime())
				.eq(iotDeviceDesignQueryParam.getDevSendouter() != null, IotDeviceDesign::getDevSendouter, iotDeviceDesignQueryParam.getDevSendouter())
				.eq(iotDeviceDesignQueryParam.getDevSendoutBatch() != null, IotDeviceDesign::getDevSendoutBatch, iotDeviceDesignQueryParam.getDevSendoutBatch())
				.eq(iotDeviceDesignQueryParam.getDevSendoutWay() != null, IotDeviceDesign::getDevSendoutWay, iotDeviceDesignQueryParam.getDevSendoutWay())
				.eq(iotDeviceDesignQueryParam.getDevPutinTime() != null, IotDeviceDesign::getDevPutinTime, iotDeviceDesignQueryParam.getDevPutinTime())
				.eq(iotDeviceDesignQueryParam.getDevPutiner() != null, IotDeviceDesign::getDevPutiner, iotDeviceDesignQueryParam.getDevPutiner())
				.eq(iotDeviceDesignQueryParam.getDevPutinWay() != null, IotDeviceDesign::getDevPutinWay, iotDeviceDesignQueryParam.getDevPutinWay())
				.eq(iotDeviceDesignQueryParam.getDevInstTime() != null, IotDeviceDesign::getDevInstTime, iotDeviceDesignQueryParam.getDevInstTime())
				.eq(iotDeviceDesignQueryParam.getDevInster() != null, IotDeviceDesign::getDevInster, iotDeviceDesignQueryParam.getDevInster())
				.eq(iotDeviceDesignQueryParam.getDevInstWay() != null, IotDeviceDesign::getDevInstWay, iotDeviceDesignQueryParam.getDevInstWay())
				.eq(iotDeviceDesignQueryParam.getDevLastUpTime() != null, IotDeviceDesign::getDevLastUpTime, iotDeviceDesignQueryParam.getDevLastUpTime())
				.eq(iotDeviceDesignQueryParam.getDevLastUpId() != null, IotDeviceDesign::getDevLastUpId, iotDeviceDesignQueryParam.getDevLastUpId())
				.eq(iotDeviceDesignQueryParam.getBookletId() != null, IotDeviceDesign::getBookletId, iotDeviceDesignQueryParam.getBookletId())
				.eq(iotDeviceDesignQueryParam.getLastCalcDate() != null, IotDeviceDesign::getLastCalcDate, iotDeviceDesignQueryParam.getLastCalcDate())
				.eq(iotDeviceDesignQueryParam.getYearusenum() != null, IotDeviceDesign::getYearusenum, iotDeviceDesignQueryParam.getYearusenum())
				.eq(iotDeviceDesignQueryParam.getLastCalcCode() != null, IotDeviceDesign::getLastCalcCode, iotDeviceDesignQueryParam.getLastCalcCode())
				.eq(iotDeviceDesignQueryParam.getDevNo() != null, IotDeviceDesign::getDevNo, iotDeviceDesignQueryParam.getDevNo())
				.eq(iotDeviceDesignQueryParam.getChannelType() != null, IotDeviceDesign::getChannelType, iotDeviceDesignQueryParam.getChannelType())
				;

		IPage<IotDeviceDesign> iotDeviceDesignPage = iotDeviceDesignService.page(pageIotDeviceDesign, queryWrapperIotDeviceDesign);

		Page<IotDeviceDesignVo> iotDeviceDesignVoPage = new Page<IotDeviceDesignVo>(page, rows);
		iotDeviceDesignVoPage.setCurrent(iotDeviceDesignPage.getCurrent());
		iotDeviceDesignVoPage.setPages(iotDeviceDesignPage.getPages());
		iotDeviceDesignVoPage.setSize(iotDeviceDesignPage.getSize());
		iotDeviceDesignVoPage.setTotal(iotDeviceDesignPage.getTotal());
		iotDeviceDesignVoPage.setRecords(iotDeviceDesignPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return iotDeviceDesignVoPage;
	}

	@ApiOperation(value = "新增设备信息")
	@RequestMapping(value = "/iot-device-designs", method = RequestMethod.POST)
	public IotDeviceDesignVo save(@RequestBody IotDeviceDesign iotDeviceDesign) {
		if (iotDeviceDesign.getId() == null || iotDeviceDesign.getId().compareTo(0L) <= 0) {
			iotDeviceDesign.setId(idService.selectId());
		}
		boolean success = iotDeviceDesignService.save(iotDeviceDesign);
		if (success) {
			IotDeviceDesign iotDeviceDesignDatabase = iotDeviceDesignService.getById(iotDeviceDesign.getId());
			return entity2vo(iotDeviceDesignDatabase);
		}
		log.info("save IotDeviceDesign fail，{}", ToStringBuilder.reflectionToString(iotDeviceDesign, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新设备信息全部信息")
	@RequestMapping(value = "/iot-device-designs/{id}", method = RequestMethod.PUT)
	public IotDeviceDesignVo updateById(@PathVariable("id") Long id, @RequestBody IotDeviceDesign iotDeviceDesign) {
		iotDeviceDesign.setId(id);
		boolean success = iotDeviceDesignService.updateById(iotDeviceDesign);
		if (success) {
			IotDeviceDesign iotDeviceDesignDatabase = iotDeviceDesignService.getById(id);
			return entity2vo(iotDeviceDesignDatabase);
		}
		log.info("update IotDeviceDesign fail，{}", ToStringBuilder.reflectionToString(iotDeviceDesign, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新设备信息信息")
	@RequestMapping(value = "/iot-device-designs/{id}", method = RequestMethod.PATCH)
	public IotDeviceDesignVo updatePatchById(@PathVariable("id") Long id, @RequestBody IotDeviceDesign iotDeviceDesign) {
		UpdateWrapper<IotDeviceDesign> updateWrapperIotDeviceDesign = new UpdateWrapper<IotDeviceDesign>();
		updateWrapperIotDeviceDesign.lambda()//
				.eq(IotDeviceDesign::getId, id)
				// .set(iotDeviceDesign.getId() != null, IotDeviceDesign::getId, iotDeviceDesign.getId())
				.set(iotDeviceDesign.getParentId() != null, IotDeviceDesign::getParentId, iotDeviceDesign.getParentId())
				.set(iotDeviceDesign.getDevType() != null, IotDeviceDesign::getDevType, iotDeviceDesign.getDevType())
				.set(iotDeviceDesign.getDevVerId() != null, IotDeviceDesign::getDevVerId, iotDeviceDesign.getDevVerId())
				.set(iotDeviceDesign.getDevMacId() != null, IotDeviceDesign::getDevMacId, iotDeviceDesign.getDevMacId())
				.set(iotDeviceDesign.getImei() != null, IotDeviceDesign::getImei, iotDeviceDesign.getImei())
				.set(iotDeviceDesign.getImsi() != null, IotDeviceDesign::getImsi, iotDeviceDesign.getImsi())
				.set(iotDeviceDesign.getAreaId() != null, IotDeviceDesign::getAreaId, iotDeviceDesign.getAreaId())
				.set(iotDeviceDesign.getCustId() != null, IotDeviceDesign::getCustId, iotDeviceDesign.getCustId())
				.set(iotDeviceDesign.getInstAddress() != null, IotDeviceDesign::getInstAddress, iotDeviceDesign.getInstAddress())
				.set(iotDeviceDesign.getMapX() != null, IotDeviceDesign::getMapX, iotDeviceDesign.getMapX())
				.set(iotDeviceDesign.getMapY() != null, IotDeviceDesign::getMapY, iotDeviceDesign.getMapY())
				.set(iotDeviceDesign.getMapIcon() != null, IotDeviceDesign::getMapIcon, iotDeviceDesign.getMapIcon())
				.set(iotDeviceDesign.getFactId() != null, IotDeviceDesign::getFactId, iotDeviceDesign.getFactId())
				.set(iotDeviceDesign.getUserId() != null, IotDeviceDesign::getUserId, iotDeviceDesign.getUserId())
				.set(iotDeviceDesign.getDevStatus() != null, IotDeviceDesign::getDevStatus, iotDeviceDesign.getDevStatus())
				.set(iotDeviceDesign.getDevRegTime() != null, IotDeviceDesign::getDevRegTime, iotDeviceDesign.getDevRegTime())
				.set(iotDeviceDesign.getDevRegister() != null, IotDeviceDesign::getDevRegister, iotDeviceDesign.getDevRegister())
				.set(iotDeviceDesign.getDevSegWay() != null, IotDeviceDesign::getDevSegWay, iotDeviceDesign.getDevSegWay())
				.set(iotDeviceDesign.getDevSendoutTime() != null, IotDeviceDesign::getDevSendoutTime, iotDeviceDesign.getDevSendoutTime())
				.set(iotDeviceDesign.getDevSendouter() != null, IotDeviceDesign::getDevSendouter, iotDeviceDesign.getDevSendouter())
				.set(iotDeviceDesign.getDevSendoutBatch() != null, IotDeviceDesign::getDevSendoutBatch, iotDeviceDesign.getDevSendoutBatch())
				.set(iotDeviceDesign.getDevSendoutWay() != null, IotDeviceDesign::getDevSendoutWay, iotDeviceDesign.getDevSendoutWay())
				.set(iotDeviceDesign.getDevPutinTime() != null, IotDeviceDesign::getDevPutinTime, iotDeviceDesign.getDevPutinTime())
				.set(iotDeviceDesign.getDevPutiner() != null, IotDeviceDesign::getDevPutiner, iotDeviceDesign.getDevPutiner())
				.set(iotDeviceDesign.getDevPutinWay() != null, IotDeviceDesign::getDevPutinWay, iotDeviceDesign.getDevPutinWay())
				.set(iotDeviceDesign.getDevInstTime() != null, IotDeviceDesign::getDevInstTime, iotDeviceDesign.getDevInstTime())
				.set(iotDeviceDesign.getDevInster() != null, IotDeviceDesign::getDevInster, iotDeviceDesign.getDevInster())
				.set(iotDeviceDesign.getDevInstWay() != null, IotDeviceDesign::getDevInstWay, iotDeviceDesign.getDevInstWay())
				.set(iotDeviceDesign.getDevLastUpTime() != null, IotDeviceDesign::getDevLastUpTime, iotDeviceDesign.getDevLastUpTime())
				.set(iotDeviceDesign.getDevLastUpId() != null, IotDeviceDesign::getDevLastUpId, iotDeviceDesign.getDevLastUpId())
				.set(iotDeviceDesign.getBookletId() != null, IotDeviceDesign::getBookletId, iotDeviceDesign.getBookletId())
				.set(iotDeviceDesign.getLastCalcDate() != null, IotDeviceDesign::getLastCalcDate, iotDeviceDesign.getLastCalcDate())
				.set(iotDeviceDesign.getYearusenum() != null, IotDeviceDesign::getYearusenum, iotDeviceDesign.getYearusenum())
				.set(iotDeviceDesign.getLastCalcCode() != null, IotDeviceDesign::getLastCalcCode, iotDeviceDesign.getLastCalcCode())
				.set(iotDeviceDesign.getDevNo() != null, IotDeviceDesign::getDevNo, iotDeviceDesign.getDevNo())
				.set(iotDeviceDesign.getChannelType() != null, IotDeviceDesign::getChannelType, iotDeviceDesign.getChannelType())
				;

		boolean success = iotDeviceDesignService.update(updateWrapperIotDeviceDesign);
		if (success) {
			IotDeviceDesign iotDeviceDesignDatabase = iotDeviceDesignService.getById(id);
			return entity2vo(iotDeviceDesignDatabase);
		}
		log.info("partial update IotDeviceDesign fail，{}",
				ToStringBuilder.reflectionToString(iotDeviceDesign, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除设备信息")
	@RequestMapping(value = "/iot-device-designs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = iotDeviceDesignService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private IotDeviceDesignVo entity2vo(IotDeviceDesign iotDeviceDesign) {
		String jsonString = JSON.toJSONString(iotDeviceDesign);
		IotDeviceDesignVo iotDeviceDesignVo = JSON.parseObject(jsonString, IotDeviceDesignVo.class);
		return iotDeviceDesignVo;
	}

}
