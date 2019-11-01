package com.zlsrj.wms.mbg.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.mbg.entity.TenantInfo;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantInfoServiceTest {

	@Autowired
	private ITenantInfoService tenantInfoService;

	@Test
	public void insertTest() {
		TenantInfo tenantInfo = TenantInfo.builder().id(IdUtil.createSnowflake(1L, 1L).nextId())
				.tenantName(RandomUtil.randomString(4)).displayName(RandomUtil.randomString(4))
				.tenantLinkman(RandomUtil.randomString(4))
				.tenantMobile(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 11))
				.tenantTel(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 11))
				.tenantEmail(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 8) + "@qq.com")
				.tenantQq(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 8)).tenantType(1).tenantStatus(1)
				.regTime(new Date()).endDate(new Date()).build();

		log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantInfoService.save(tenantInfo);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1189808651897016320L;

		TenantInfo tenantInfo = TenantInfo.builder().tenantName(RandomUtil.randomString(4))
				.displayName(RandomUtil.randomString(4)).tenantLinkman(RandomUtil.randomString(4))
				.tenantMobile(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 11))
				.tenantTel(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 11))
				.tenantEmail(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 8) + "@qq.com")
				.tenantQq(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 8)).tenantType(1).tenantStatus(1)
				.regTime(new Date()).endDate(new Date()).build();

		tenantInfo.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantInfoService.updateById(tenantInfo);

		log.info(Boolean.toString(success));
	}
}
