package com.zlsrj.wms.saas.mapper;

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
import com.zlsrj.wms.api.entity.TenantInfo;

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
				.id(TestCaseUtil.id())// 租户ID
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				.tenantBalance(new BigDecimal(0))// 账户余额
				.teanantOverdraw(new BigDecimal(0))// 透支额度
				.tenantDisplayName(companyShortName)// 租户显示名称
				.tenantProvince(TestCaseUtil.province())// 省（下拉框）
				.tenantCity(TestCaseUtil.city())// 市（下拉框）
				.tenantCountyTown(RandomUtil.randomString(4))// 区县（下拉框）
				.tenantLinkAddress(TestCaseUtil.address())// 联系地址
				.tenantLinkman(TestCaseUtil.name())// 联系人
				.tenantLinkmanMobile(TestCaseUtil.mobile())// 手机号码
				.tenantLinkmanTel(TestCaseUtil.tel())// 单位电话
				.tenantLinkmanEmail(TestCaseUtil.email(null))// 邮箱
				.tenantLinkmanQq(TestCaseUtil.qq())// QQ号码
				.tenantType(RandomUtil.randomInt(1,5+1))// 租户类型（1使用单位/2水表厂商/3代收机构/4内部运营/5分销商）
				.tenantRegisterTime(new Date())// 注册时间
				.invoiceType(RandomUtil.randomInt(1,3+1))// 开票类别（1普通纸制发票/2普通电子发票/3专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.tenantAccesskey(RandomUtil.randomString(4))// 租户KEY
				.build();
				
		int count = tenantInfoMapper.insert(tenantInfo);
		log.info("count={}",count);
		log.info("tenantInfo={}",tenantInfo);
	}
	
}
