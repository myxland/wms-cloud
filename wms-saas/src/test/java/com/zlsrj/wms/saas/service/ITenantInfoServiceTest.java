package com.zlsrj.wms.saas.service;

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
		for(int i=0;i<RandomUtil.randomInt(10,100+1);i++) {
			String companyShortName = TestCaseUtil.companyShortName();

			TenantInfo tenantInfo = TenantInfo.builder()//
					.id(TestCaseUtil.id())// 租户ID
					.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
					.tenantBalance(new BigDecimal(0))// 账户余额
					.tenantOverdraw(new BigDecimal(0))// 透支额度
					.tenantDisplayName(companyShortName)// 租户显示名称
					.tenantProvince(TestCaseUtil.province())// 省
					.tenantCity(TestCaseUtil.city())// 市
					.tenantCountyTown(RandomUtil.randomString(4))// 区/县
					.tenantLinkAddress(TestCaseUtil.address())// 联系地址
					.tenantLinkman(TestCaseUtil.name())// 联系人
					.tenantLinkmanMobile(TestCaseUtil.mobile())// 手机号码
					.tenantLinkmanTel(TestCaseUtil.tel())// 单位电话
					.tenantLinkmanEmail(TestCaseUtil.email(null))// 邮箱
					.tenantLinkmanQq(TestCaseUtil.qq())// QQ号码
					.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
					.tenantRegisterTime(new Date())// 注册时间
					.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
					.invoiceName(TestCaseUtil.name())// 开票名称
					.invoiceTaxNo(RandomUtil.randomString(4))// 税号
					.invoiceAddress(TestCaseUtil.address())// 开票地址
					.invoiceTel(TestCaseUtil.tel())// 开票电话
					.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
					.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
					.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
					.tenantAccesskey(RandomUtil.randomString(4))// 租户KEY
					.build();

			log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));

			boolean success = tenantInfoService.save(tenantInfo);

			log.info(Boolean.toString(success));
		}
	}

	@Test
	public void updateTest() {

		String id = "";

		String companyShortName = TestCaseUtil.companyShortName();

		TenantInfo tenantInfo = TenantInfo.builder()//
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				.tenantBalance(new BigDecimal(0))// 账户余额
				.tenantOverdraw(new BigDecimal(0))// 透支额度
				.tenantDisplayName(companyShortName)// 租户显示名称
				.tenantProvince(TestCaseUtil.province())// 省
				.tenantCity(TestCaseUtil.city())// 市
				.tenantCountyTown(RandomUtil.randomString(4))// 区/县
				.tenantLinkAddress(TestCaseUtil.address())// 联系地址
				.tenantLinkman(TestCaseUtil.name())// 联系人
				.tenantLinkmanMobile(TestCaseUtil.mobile())// 手机号码
				.tenantLinkmanTel(TestCaseUtil.tel())// 单位电话
				.tenantLinkmanEmail(TestCaseUtil.email(null))// 邮箱
				.tenantLinkmanQq(TestCaseUtil.qq())// QQ号码
				.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				.tenantRegisterTime(new Date())// 注册时间
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.tenantAccesskey(RandomUtil.randomString(4))// 租户KEY
				.build();
		tenantInfo.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantInfoService.updateById(tenantInfo);

		log.info(Boolean.toString(success));
	}
}
