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
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterBrand;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterBrandServiceTest {

	@Autowired
	private ITenantMeterBrandService tenantMeterBrandService;
	
	@Resource
	private ITenantInfoService tenantInfoService;

	@Test
	public void insertTest() {
		TenantMeterBrand tenantMeterBrand = TenantMeterBrand.builder()//
				.id(TestCaseUtil.id())// 水表品牌ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.meterBrandName(TestCaseUtil.name())// 名称
				.meterBrandData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterBrand, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterBrandService.save(tenantMeterBrand);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeterBrand tenantMeterBrand = TenantMeterBrand.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.meterBrandName(TestCaseUtil.name())// 名称
				.meterBrandData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeterBrand.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterBrand, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterBrandService.updateById(tenantMeterBrand);

		log.info(Boolean.toString(success));
	}
	
	@Test
	public void saveBatchByTenantInfoTest() {
		String tenantId = "27f3f0eec0714620b5c3f5c34c19650d";
		TenantInfo tenantInfo = tenantInfoService.getById(tenantId);
		boolean success = tenantMeterBrandService.saveBatchByTenantInfo(tenantInfo);
		
		log.info("tenantId={},success={}",tenantId,Boolean.toString(success));
	}
}
