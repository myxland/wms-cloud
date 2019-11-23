package com.zlsrj.wms.dev.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.IotDeviceDesign;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IIotDeviceDesignServiceTest {

	@Autowired
	private IIotDeviceDesignService iotDeviceDesignService;

	@Test
	public void insertTest() {
		IotDeviceDesign iotDeviceDesign = IotDeviceDesign.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.parentId(RandomUtil.randomLong())// 父级系统编号
				.devType(RandomUtil.randomInt(0,1+1))// 表具用途（1：贸易结算；2：数据监测）
				.devVerId(RandomUtil.randomInt(0,1000+1))// 产品型号编号
				.devMacId(RandomUtil.randomString(4))// 设备出厂编号
				.imei(RandomUtil.randomString(4))// 模组编号
				.imsi(RandomUtil.randomString(4))// 通讯卡号
				.areaId(RandomUtil.randomInt(0,1000+1))// 设备所属区域编号
				.custId(RandomUtil.randomLong())// 用户编号
				.instAddress(TestCaseUtil.address())// 安装地址
				.mapX(null)// 地图X坐标
				.mapY(null)// 地图Y坐标
				.mapIcon(RandomUtil.randomString(4))// 地图显示图标
				.factId(RandomUtil.randomLong())// 供应商编号（租户编号）
				.userId(RandomUtil.randomLong())// 使用方编号（租户编号）
				.devStatus(RandomUtil.randomInt(0,1+1))// 设备当前状态
				.devRegTime(new Date())// 注册时间
				.devRegister(RandomUtil.randomLong())// 注册人
				.devSegWay(RandomUtil.randomInt(0,1000+1))// 注册数据来源
				.devSendoutTime(new Date())// 发货时间
				.devSendouter(RandomUtil.randomLong())// 发货人
				.devSendoutBatch(RandomUtil.randomLong())// 发货批次
				.devSendoutWay(RandomUtil.randomInt(0,1000+1))// 发货数据来源
				.devPutinTime(new Date())// 入库时间
				.devPutiner(RandomUtil.randomLong())// 入库人
				.devPutinWay(RandomUtil.randomInt(0,1000+1))// 入库数据来源
				.devInstTime(new Date())// 安装时间
				.devInster(RandomUtil.randomLong())// 安装人
				.devInstWay(RandomUtil.randomInt(0,1000+1))// 安装数据来源
				.devLastUpTime(new Date())// 最后一次上传时间
				.devLastUpId(RandomUtil.randomLong())// 最后一次上传ID
				.bookletId(RandomUtil.randomLong())// 表册编号
				.lastCalcDate(new Date())// 最后一次计费日期
				.yearusenum(new BigDecimal(0))// 年累计用水量
				.lastCalcCode(new BigDecimal(0))// 最后一次计费止码
				.devNo(RandomUtil.randomString(4))// 设备编号
				.channelType(RandomUtil.randomInt(0,1+1))// 上传渠道类型（1：移动物联平台）
				.build();

		log.info(ToStringBuilder.reflectionToString(iotDeviceDesign, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = iotDeviceDesignService.save(iotDeviceDesign);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		IotDeviceDesign iotDeviceDesign = IotDeviceDesign.builder()//
				.parentId(RandomUtil.randomLong())// 父级系统编号
				.devType(RandomUtil.randomInt(0,1+1))// 表具用途（1：贸易结算；2：数据监测）
				.devVerId(RandomUtil.randomInt(0,1000+1))// 产品型号编号
				.devMacId(RandomUtil.randomString(4))// 设备出厂编号
				.imei(RandomUtil.randomString(4))// 模组编号
				.imsi(RandomUtil.randomString(4))// 通讯卡号
				.areaId(RandomUtil.randomInt(0,1000+1))// 设备所属区域编号
				.custId(RandomUtil.randomLong())// 用户编号
				.instAddress(TestCaseUtil.address())// 安装地址
				.mapX(null)// 地图X坐标
				.mapY(null)// 地图Y坐标
				.mapIcon(RandomUtil.randomString(4))// 地图显示图标
				.factId(RandomUtil.randomLong())// 供应商编号（租户编号）
				.userId(RandomUtil.randomLong())// 使用方编号（租户编号）
				.devStatus(RandomUtil.randomInt(0,1+1))// 设备当前状态
				.devRegTime(new Date())// 注册时间
				.devRegister(RandomUtil.randomLong())// 注册人
				.devSegWay(RandomUtil.randomInt(0,1000+1))// 注册数据来源
				.devSendoutTime(new Date())// 发货时间
				.devSendouter(RandomUtil.randomLong())// 发货人
				.devSendoutBatch(RandomUtil.randomLong())// 发货批次
				.devSendoutWay(RandomUtil.randomInt(0,1000+1))// 发货数据来源
				.devPutinTime(new Date())// 入库时间
				.devPutiner(RandomUtil.randomLong())// 入库人
				.devPutinWay(RandomUtil.randomInt(0,1000+1))// 入库数据来源
				.devInstTime(new Date())// 安装时间
				.devInster(RandomUtil.randomLong())// 安装人
				.devInstWay(RandomUtil.randomInt(0,1000+1))// 安装数据来源
				.devLastUpTime(new Date())// 最后一次上传时间
				.devLastUpId(RandomUtil.randomLong())// 最后一次上传ID
				.bookletId(RandomUtil.randomLong())// 表册编号
				.lastCalcDate(new Date())// 最后一次计费日期
				.yearusenum(new BigDecimal(0))// 年累计用水量
				.lastCalcCode(new BigDecimal(0))// 最后一次计费止码
				.devNo(RandomUtil.randomString(4))// 设备编号
				.channelType(RandomUtil.randomInt(0,1+1))// 上传渠道类型（1：移动物联平台）
				.build();
		iotDeviceDesign.setId(id);

		log.info(ToStringBuilder.reflectionToString(iotDeviceDesign, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = iotDeviceDesignService.updateById(iotDeviceDesign);

		log.info(Boolean.toString(success));
	}
}
