package com.zlsrj.wms.tenant.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantInfo;

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
		for(int i=0;i<1;i++) {
			String companyShortName = TestCaseUtil.companyShortName();

			TenantInfo tenantInfo = TenantInfo.builder()//
					.id(TestCaseUtil.id())// 租户编号
					.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
					.displayName(companyShortName)// 显示名称
					.tenantProvince(TestCaseUtil.province())// 省
					.tenantCity(TestCaseUtil.city())// 市
					.tenantArea(TestCaseUtil.area())// 区县
					.tenantAddress(TestCaseUtil.address())// 联系地址
					.tenantContact(TestCaseUtil.name())// 联系人
					.tenantMobile(TestCaseUtil.mobile())// 手机号码
					.tenantTel(TestCaseUtil.tel())// 单位电话
					.tenantEmail(TestCaseUtil.email(null))// 邮箱
					.tenantQq(TestCaseUtil.qq())// QQ号码
					.tenantType(RandomUtil.randomInt(1,3+1))// 租户类型（1：使用单位；2：供应单位；3：内部运营）
					.tenantStatus(RandomUtil.randomInt(1,2+1))// 租户状态（1：正式；2：试用）
					.regTime(TestCaseUtil.dateBefore())// 注册时间
					.endDate(TestCaseUtil.dateAfter())// 到期日期
					.build();

			log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));

			boolean success = tenantInfoService.save(tenantInfo);

			log.info(Boolean.toString(success));
		}
		
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		String companyShortName = TestCaseUtil.companyShortName();

		TenantInfo tenantInfo = TenantInfo.builder()//
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				.displayName(companyShortName)// 显示名称
				.tenantProvince(TestCaseUtil.province())// 省
				.tenantCity(TestCaseUtil.city())// 市
				.tenantArea(TestCaseUtil.area())// 区县
				.tenantAddress(TestCaseUtil.address())// 联系地址
				.tenantContact(TestCaseUtil.name())// 联系人
				.tenantMobile(TestCaseUtil.mobile())// 手机号码
				.tenantTel(TestCaseUtil.tel())// 单位电话
				.tenantEmail(TestCaseUtil.email(null))// 邮箱
				.tenantQq(TestCaseUtil.qq())// QQ号码
				.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：供应单位；3：内部运营）
				.tenantStatus(RandomUtil.randomInt(0,1+1))// 租户状态（1：正式；2：试用）
				.regTime(new Date())// 注册时间
				.endDate(new Date())// 到期日期
				.build();
		tenantInfo.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantInfoService.updateById(tenantInfo);

		log.info(Boolean.toString(success));
	}
}
