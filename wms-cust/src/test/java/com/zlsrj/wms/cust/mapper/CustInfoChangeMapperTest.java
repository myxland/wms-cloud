package com.zlsrj.wms.cust.mapper;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.CustInfoChange;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustInfoChangeMapperTest {

	@Resource
	private CustInfoChangeMapper custInfoChangeMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		CustInfoChange custInfoChange = custInfoChangeMapper.selectById(id);
		log.info(custInfoChange.toString());
	}

	@Test
	public void selectList() {
		QueryWrapper<CustInfoChange> queryWrapper = new QueryWrapper<CustInfoChange>();
		List<CustInfoChange> custInfoChangeList = custInfoChangeMapper.selectList(queryWrapper);
		custInfoChangeList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for (int i = 0; i < 100; i++) {
			CustInfoChange custInfoChange = CustInfoChange.builder()//
					.id(TestCaseUtil.id())// 系统编号
					.tenantId(TestCaseUtil.id())// 租户编号
					.custId(RandomUtil.randomString(4))// 用户编号
					.changeDate(TestCaseUtil.dateBefore())// 变更日期
					.changer(TestCaseUtil.id())// 变更人
					.baseChange(RandomUtil.randomInt(0, 1 + 1))// 用户信息变更（1：是；0：否）
					.billChange(RandomUtil.randomInt(0, 1 + 1))// 开票信息变更（1：是；0：否）
					.statusChange(RandomUtil.randomInt(0, 1 + 1))// 用户状态变更（1：是；0：否）
					.custName(TestCaseUtil.name())// 用户名称
					.custNameHis(TestCaseUtil.name())// 变更前用户名称
					.custAddress(TestCaseUtil.address())// 用户地址
					.custAddressHis(TestCaseUtil.address())// 变更前用户地址
					.custTypeId(TestCaseUtil.id())// 用户类别编号
					.custTypeIdHis(TestCaseUtil.id())// 变更前用户类别编号
					.custRegistDate(TestCaseUtil.dateBefore())// 立户日期
					.custRegistDateHis(TestCaseUtil.dateBefore())// 变更前立户日期
					.custStatus(RandomUtil.randomInt(1, 3 + 1))// 用户状态（1：正常；2：暂停；3：消户）
					.custStatusHis(RandomUtil.randomInt(1, 3 + 1))// 变更前用户状态（1：正常；2：暂停；3：消户）
					.payType(RandomUtil.randomInt(1, 4 + 1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
					.payTypeHis(RandomUtil.randomInt(1, 4 + 1))// 变更前收费方式（1：坐收；2：走收；3：代扣；4：托收）
					.billType(RandomUtil.randomInt(1, 3 + 1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
					.billTypeHis(RandomUtil.randomInt(1, 3 + 1))// 变更前开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
					.billName(TestCaseUtil.name())// 开票名称
					.billNameHis(TestCaseUtil.name())// 变更前开票名称
					.billTaxnum(RandomUtil.randomString(4))// 税号
					.billTaxnumHis(RandomUtil.randomString(4))// 变更前税号
					.billAddress(TestCaseUtil.address())// 开票地址
					.billAddressHis(TestCaseUtil.address())// 变更前开票地址
					.billTel(TestCaseUtil.tel())// 开票电话
					.billTelHis(TestCaseUtil.tel())// 变更前开票电话
					.billBank(TestCaseUtil.bank())// 银行名称
					.billBankHis(TestCaseUtil.bank())// 变更前银行名称
					.billBankId(TestCaseUtil.bankNo())// 开户行行号
					.billBankIdHis(TestCaseUtil.bankNo())// 变更前开户行行号
					.billBankName(TestCaseUtil.bankName())// 开户行名称
					.billBankNameHis(TestCaseUtil.bankName())// 变更前开户行名称
					.billBankAccount(TestCaseUtil.bankCardNo(null))// 开户行账号
					.billBankAccountHis(TestCaseUtil.bankCardNo(null))// 变更前开户行账号
					.build();

			int count = custInfoChangeMapper.insert(custInfoChange);
			log.info("count={}", count);
			log.info("custInfoChange={}", custInfoChange);
		}

	}

}
