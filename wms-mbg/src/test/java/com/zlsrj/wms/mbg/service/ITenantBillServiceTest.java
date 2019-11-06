package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantBill;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantBillServiceTest {

	@Autowired
	private ITenantBillService tenantBillService;

	@Test
	public void insertTest() {
		TenantBill tenantBill = TenantBill.builder()//
				.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.billPrintType(RandomUtil.randomInt(0,1+1))// 用户发票开具方式（按实收开票/按应收开票）
				.billRemark(RandomUtil.randomString(4))// 发票备注定义
				.billService(RandomUtil.randomString(4))// 电子发票服务商（百望/航天信息）
				.billJrdm(RandomUtil.randomString(4))// 接入代码
				.billQmcs(RandomUtil.randomString(4))// 签名值参数
				.billSkpbh(RandomUtil.randomString(4))// 税控盘编号
				.billSkpkl(RandomUtil.randomString(4))// 税控盘口令
				.billKeypwd(RandomUtil.randomString(4))// 税务数字证书密码
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantBill, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBillService.save(tenantBill);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantBill tenantBill = TenantBill.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.billPrintType(RandomUtil.randomInt(0,1+1))// 用户发票开具方式（按实收开票/按应收开票）
				.billRemark(RandomUtil.randomString(4))// 发票备注定义
				.billService(RandomUtil.randomString(4))// 电子发票服务商（百望/航天信息）
				.billJrdm(RandomUtil.randomString(4))// 接入代码
				.billQmcs(RandomUtil.randomString(4))// 签名值参数
				.billSkpbh(RandomUtil.randomString(4))// 税控盘编号
				.billSkpkl(RandomUtil.randomString(4))// 税控盘口令
				.billKeypwd(RandomUtil.randomString(4))// 税务数字证书密码
				.build();
		tenantBill.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantBill, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBillService.updateById(tenantBill);

		log.info(Boolean.toString(success));
	}
}
