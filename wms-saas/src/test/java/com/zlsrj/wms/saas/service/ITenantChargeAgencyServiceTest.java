package com.zlsrj.wms.saas.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantChargeAgency;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantChargeAgencyServiceTest {

	@Autowired
	private ITenantChargeAgencyService tenantChargeAgencyService;
	
	@Resource
	private ITenantInfoService tenantInfoService;

	@Test
	public void insertTest() {
		TenantChargeAgency tenantChargeAgency = TenantChargeAgency.builder()//
				.id(TestCaseUtil.id())// 代收机构ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.chargeAgencyName(TestCaseUtil.name())// 代收机构名称
				.chargeAgencyData(RandomUtil.randomString(4))// 结构化数据
				.createType(RandomUtil.randomInt(0,1+1))// 创建类型（1：平台默认创建；2：租户自建）
				.apiOn(RandomUtil.randomInt(0,1+1))// 是否开放API（1：开放；0：不开放）
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantChargeAgency, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantChargeAgencyService.save(tenantChargeAgency);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantChargeAgency tenantChargeAgency = TenantChargeAgency.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.chargeAgencyName(TestCaseUtil.name())// 代收机构名称
				.chargeAgencyData(RandomUtil.randomString(4))// 结构化数据
				.createType(RandomUtil.randomInt(0,1+1))// 创建类型（1：平台默认创建；2：租户自建）
				.apiOn(RandomUtil.randomInt(0,1+1))// 是否开放API（1：开放；0：不开放）
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantChargeAgency.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantChargeAgency, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantChargeAgencyService.updateById(tenantChargeAgency);

		log.info(Boolean.toString(success));
	}
	
	@Test
	public void saveBatchByTenantInfoTest() {
		String tenantId = "27f3f0eec0714620b5c3f5c34c19650d";
		TenantInfo tenantInfo = tenantInfoService.getById(tenantId);
		boolean success = tenantChargeAgencyService.saveBatchByTenantInfo(tenantInfo);
		
		log.info("tenantId={},success={}",tenantId,Boolean.toString(success));
	}
	
	@Test
	public void removeBatchByTenantInfoTest() {
		String tenantId = "dd119da46cb44455be061425da1dd9ed";
		TenantInfo tenantInfo = tenantInfoService.getById(tenantId);
		boolean success = tenantChargeAgencyService.removeBatchByTenantInfo(tenantInfo);
		
		log.info("tenantId={},success={}",tenantId,Boolean.toString(success));
	}
}
