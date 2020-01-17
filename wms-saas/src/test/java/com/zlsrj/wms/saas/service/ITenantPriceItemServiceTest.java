package com.zlsrj.wms.saas.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantPriceItem;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPriceItemServiceTest {

	@Autowired
	private ITenantPriceItemService tenantPriceItemService;

	@Test
	public void insertTest() {
		TenantPriceItem tenantPriceItem = TenantPriceItem.builder()//
				.id(TestCaseUtil.id())// 费用项目ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.priceItemName(TestCaseUtil.name())// 费用项目名称
				.priceItemTaxRate(new BigDecimal(0))// 税率
				.priceItemTaxId(RandomUtil.randomString(4))// 对应税控项目编号
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPriceItem, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceItemService.save(tenantPriceItem);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantPriceItem tenantPriceItem = TenantPriceItem.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.priceItemName(TestCaseUtil.name())// 费用项目名称
				.priceItemTaxRate(new BigDecimal(0))// 税率
				.priceItemTaxId(RandomUtil.randomString(4))// 对应税控项目编号
				.build();
		tenantPriceItem.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPriceItem, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceItemService.updateById(tenantPriceItem);

		log.info(Boolean.toString(success));
	}
}
