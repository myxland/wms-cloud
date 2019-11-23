package com.zlsrj.wms.cust.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.CustInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustInfoMapperTest {

	@Resource
	private CustInfoMapper custInfoMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		CustInfo custInfo = custInfoMapper.selectById(id);
		log.info(custInfo.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<CustInfo> queryWrapper = new QueryWrapper<CustInfo>();
		List<CustInfo> custInfoList = custInfoMapper.selectList(queryWrapper);
		custInfoList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		CustInfo custInfo = CustInfo.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custNo(RandomUtil.randomString(4))// 用户编号
				.custName(TestCaseUtil.name())// 用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.custTypeId(RandomUtil.randomLong())// 用户类别编号
				.custRegistDate(new Date())// 立户日期
				.custStatus(RandomUtil.randomInt(1,3+1))// 用户状态（1：正常；2：暂停；3：消户）
				.payType(RandomUtil.randomInt(1,4+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.billType(RandomUtil.randomInt(1,3+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billName(TestCaseUtil.name())// 开票名称
				.billTaxnum(RandomUtil.randomString(4))// 税号
				.billAddress(TestCaseUtil.address())// 开票地址
				.billTel(TestCaseUtil.tel())// 开票电话
				.billBank(TestCaseUtil.bank())// 银行名称
				.billBankName(TestCaseUtil.bankName())// 开户行名称
				.billBankAccount(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				.billBankId(TestCaseUtil.bankNo())// 开户行号
				.saveMoney(new BigDecimal(0))// 预存余额
				.dueMoney(new BigDecimal(0))// 欠费金额
				.build();
				
		int count = custInfoMapper.insert(custInfo);
		log.info("count={}",count);
		log.info("custInfo={}",custInfo);
	}
	
}
