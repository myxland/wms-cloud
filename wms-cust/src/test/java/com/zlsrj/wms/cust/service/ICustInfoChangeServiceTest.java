package com.zlsrj.wms.cust.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.CustInfoChange;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ICustInfoChangeServiceTest {

	@Autowired
	private ICustInfoChangeService custInfoChangeService;

	@Test
	public void insertTest() {
		CustInfoChange custInfoChange = CustInfoChange.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custId(RandomUtil.randomString(4))// 用户编号
				.changeDate(new Date())// 变更日期
				.changer(RandomUtil.randomLong())// 变更人
				.baseChange(RandomUtil.randomInt(0,1000+1))// 用户信息变更（1：是；0：否）
				.billChange(RandomUtil.randomInt(0,1000+1))// 开票信息变更（1：是；0：否）
				.statusChange(RandomUtil.randomInt(0,1000+1))// 用户状态变更（1：是；0：否）
				.custName(TestCaseUtil.name())// 用户名称
				.custNameHis(RandomUtil.randomString(4))// 变更前用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.custAddressHis(RandomUtil.randomString(4))// 变更前用户地址
				.custTypeId(RandomUtil.randomLong())// 用户类别编号
				.custTypeIdHis(RandomUtil.randomLong())// 变更前用户类别编号
				.custRegistDate(new Date())// 立户日期
				.custRegistDateHis(new Date())// 变更前立户日期
				.custStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.custStatusHis(RandomUtil.randomInt(0,1000+1))// 变更前用户状态（1：正常；2：暂停；3：消户）
				.payType(RandomUtil.randomInt(0,1+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.payTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.billType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billName(TestCaseUtil.name())// 开票名称
				.billNameHis(RandomUtil.randomString(4))// 变更前开票名称
				.billTaxnum(RandomUtil.randomString(4))// 税号
				.billTaxnumHis(RandomUtil.randomString(4))// 变更前税号
				.billAddress(TestCaseUtil.address())// 开票地址
				.billAddressHis(RandomUtil.randomString(4))// 变更前开票地址
				.billTel(TestCaseUtil.tel())// 开票电话
				.billTelHis(RandomUtil.randomString(4))// 变更前开票电话
				.billBank(RandomUtil.randomString(4))// 银行名称
				.billBankHis(RandomUtil.randomString(4))// 变更前银行名称
				.billBankId(RandomUtil.randomString(4))// 开户行行号
				.billBankIdHis(RandomUtil.randomString(4))// 变更前开户行行号
				.billBankName(TestCaseUtil.bankName())// 开户行名称
				.billBankNameHis(RandomUtil.randomString(4))// 变更前开户行名称
				.billBankAccount(RandomUtil.randomString(4))// 开户行账号
				.billBankAccountHis(RandomUtil.randomString(4))// 变更前开户行账号
				.build();

		log.info(ToStringBuilder.reflectionToString(custInfoChange, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = custInfoChangeService.save(custInfoChange);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		CustInfoChange custInfoChange = CustInfoChange.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custId(RandomUtil.randomString(4))// 用户编号
				.changeDate(new Date())// 变更日期
				.changer(RandomUtil.randomLong())// 变更人
				.baseChange(RandomUtil.randomInt(0,1000+1))// 用户信息变更（1：是；0：否）
				.billChange(RandomUtil.randomInt(0,1000+1))// 开票信息变更（1：是；0：否）
				.statusChange(RandomUtil.randomInt(0,1000+1))// 用户状态变更（1：是；0：否）
				.custName(TestCaseUtil.name())// 用户名称
				.custNameHis(RandomUtil.randomString(4))// 变更前用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.custAddressHis(RandomUtil.randomString(4))// 变更前用户地址
				.custTypeId(RandomUtil.randomLong())// 用户类别编号
				.custTypeIdHis(RandomUtil.randomLong())// 变更前用户类别编号
				.custRegistDate(new Date())// 立户日期
				.custRegistDateHis(new Date())// 变更前立户日期
				.custStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.custStatusHis(RandomUtil.randomInt(0,1000+1))// 变更前用户状态（1：正常；2：暂停；3：消户）
				.payType(RandomUtil.randomInt(0,1+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.payTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.billType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billName(TestCaseUtil.name())// 开票名称
				.billNameHis(RandomUtil.randomString(4))// 变更前开票名称
				.billTaxnum(RandomUtil.randomString(4))// 税号
				.billTaxnumHis(RandomUtil.randomString(4))// 变更前税号
				.billAddress(TestCaseUtil.address())// 开票地址
				.billAddressHis(RandomUtil.randomString(4))// 变更前开票地址
				.billTel(TestCaseUtil.tel())// 开票电话
				.billTelHis(RandomUtil.randomString(4))// 变更前开票电话
				.billBank(RandomUtil.randomString(4))// 银行名称
				.billBankHis(RandomUtil.randomString(4))// 变更前银行名称
				.billBankId(RandomUtil.randomString(4))// 开户行行号
				.billBankIdHis(RandomUtil.randomString(4))// 变更前开户行行号
				.billBankName(TestCaseUtil.bankName())// 开户行名称
				.billBankNameHis(RandomUtil.randomString(4))// 变更前开户行名称
				.billBankAccount(RandomUtil.randomString(4))// 开户行账号
				.billBankAccountHis(RandomUtil.randomString(4))// 变更前开户行账号
				.build();
		custInfoChange.setId(id);

		log.info(ToStringBuilder.reflectionToString(custInfoChange, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = custInfoChangeService.updateById(custInfoChange);

		log.info(Boolean.toString(success));
	}
}
