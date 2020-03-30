package com.zlsrj.wms.saas.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantBook;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantBookServiceTest {

	@Autowired
	private ITenantBookService tenantBookService;

	@Test
	public void insertTest() {
		TenantBook tenantBook = TenantBook.builder()//
				.id(TestCaseUtil.id())// 表册ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.bookCode(RandomUtil.randomString(4))// 表册编号
				.bookName(TestCaseUtil.name())// 表册名称
				.bookReaderEmployeeId(RandomUtil.randomString(4))// 抄表员
				.bookChargeEmployeeId(RandomUtil.randomString(4))// 收费员
				.bookMarketingAreaId(RandomUtil.randomString(4))// 营销区域
				.bookReadCycle(RandomUtil.randomInt(0,1000+1))// 抄表周期
				.bookLastMonth(RandomUtil.randomString(4))// 最后一次抄表月份
				.bookReadMonth(RandomUtil.randomString(4))// 下次抄表月份
				.bookSettleCycle(RandomUtil.randomInt(0,1000+1))// 结算周期
				.bookSettleLastMonth(RandomUtil.randomString(4))// 最后一次结算月份
				.bookSettleMonth(RandomUtil.randomString(4))// 下次结算月份
				.bookStatus(RandomUtil.randomInt(0,1+1))// 有效状态（1：可用；0：禁用）
				.bookReadStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				.priceCalss(RandomUtil.randomInt(0,1000+1))// 级次
				.priceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantBook, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBookService.save(tenantBook);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantBook tenantBook = TenantBook.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.bookCode(RandomUtil.randomString(4))// 表册编号
				.bookName(TestCaseUtil.name())// 表册名称
				.bookReaderEmployeeId(RandomUtil.randomString(4))// 抄表员
				.bookChargeEmployeeId(RandomUtil.randomString(4))// 收费员
				.bookMarketingAreaId(RandomUtil.randomString(4))// 营销区域
				.bookReadCycle(RandomUtil.randomInt(0,1000+1))// 抄表周期
				.bookLastMonth(RandomUtil.randomString(4))// 最后一次抄表月份
				.bookReadMonth(RandomUtil.randomString(4))// 下次抄表月份
				.bookSettleCycle(RandomUtil.randomInt(0,1000+1))// 结算周期
				.bookSettleLastMonth(RandomUtil.randomString(4))// 最后一次结算月份
				.bookSettleMonth(RandomUtil.randomString(4))// 下次结算月份
				.bookStatus(RandomUtil.randomInt(0,1+1))// 有效状态（1：可用；0：禁用）
				.bookReadStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				.priceCalss(RandomUtil.randomInt(0,1000+1))// 级次
				.priceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantBook.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantBook, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBookService.updateById(tenantBook);

		log.info(Boolean.toString(success));
	}
}
