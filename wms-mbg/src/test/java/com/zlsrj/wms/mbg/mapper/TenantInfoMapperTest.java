package com.zlsrj.wms.mbg.mapper;

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
import com.zlsrj.wms.mbg.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantInfoMapperTest {

	@Resource
	private TenantInfoMapper tenantInfoMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantInfo tenantInfo = tenantInfoMapper.selectById(id);
		log.info(tenantInfo.toString());
	}

	@Test
	public void selectList() {
		QueryWrapper<TenantInfo> queryWrapper = new QueryWrapper<TenantInfo>();
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(queryWrapper);
		tenantInfoList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		String companyShortName = TestCaseUtil.companyShortName();

		TenantInfo tenantInfo = TenantInfo.builder()//
				.id(TestCaseUtil.id())// 租户编号
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				.displayName(companyShortName)// 显示名称
				.tenantProvince(TestCaseUtil.province())// 省
				.tenantCity(TestCaseUtil.city())// 市
				.tenantArea(TestCaseUtil.area())// 区县
				.tenantAddress(TestCaseUtil.address())// 联系地址
				.tenantLinkman(TestCaseUtil.name())// 联系人
				.tenantMobile(TestCaseUtil.mobile())// 手机号码
				.tenantTel(TestCaseUtil.tel())// 单位电话
				.tenantEmail(TestCaseUtil.email(null))// 邮箱
				.tenantQq(TestCaseUtil.qq())// QQ号码
				.tenantType(RandomUtil.randomInt(0, 1 + 1))// 租户类型（1：使用单位；2：供应单位；3：内部运营）
				.tenantStatus(RandomUtil.randomInt(0, 1 + 1))// 租户状态（正式/试用）
				.regTime(new Date())// 注册时间
				.endDate(new Date())// 到期日期
				.creditNumber(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.bankNo(TestCaseUtil.bankNo())// 开户行行号
				.bankName(TestCaseUtil.bankName())// 开户行名称
				.accountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.partChargeOn(RandomUtil.randomInt(0, 1 + 1))// 是否启用部分缴费（启用/不启用）
				.overDuefineOn(RandomUtil.randomInt(0, 1 + 1))// 是否启用违约金（启用/不启用）
				.overDuefineDay(RandomUtil.randomInt(0, 1000 + 1))// 违约金宽限天数（从计费之日开始）
				.overDuefineRatio(new BigDecimal(0))// 违约金每天收取比例
				.overDuefineTopRatio(new BigDecimal(0))// 违约金封顶比例（与欠费金额相比）
				.ycdkType(RandomUtil.randomInt(0, 1 + 1))// 预存抵扣方式（抄表后即时抵扣/人工发起抵扣）
				.build();

		int count = tenantInfoMapper.insert(tenantInfo);
		log.info("count={}", count);
		log.info("tenantInfo={}", tenantInfo);
	}

}
