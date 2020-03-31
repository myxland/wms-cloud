package com.zlsrj.wms.saas.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookBatchUpdateParam;
import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.common.test.TestCaseUtil;

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
		TenantBookAddParam tenantBookAddParam = new TenantBookAddParam();
		tenantBookAddParam.setTenantId("23a60db88e184a3fa82d21dd4b0055c4");// 租户ID
//		tenantBookAddParam.setBookCode(RandomUtil.randomString(4));// 表册编号
		tenantBookAddParam.setBookName("表册名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 表册名称
		tenantBookAddParam.setBookReaderEmployeeId("62a6017cb94f4279867035dd57727362");// 抄表员
		tenantBookAddParam.setBookChargeEmployeeId("62a6017cb94f4279867035dd57727362");// 收费员
		tenantBookAddParam.setBookMarketingAreaId("220126d5e3a14cca93f5d87131c85cc6");// 营销区域
		tenantBookAddParam.setBookReadCycle(RandomUtil.randomInt(0,1000+1));// 抄表周期
		tenantBookAddParam.setBookLastMonth(RandomUtil.randomString(4));// 最后一次抄表月份
		tenantBookAddParam.setBookReadMonth(RandomUtil.randomString(4));// 下次抄表月份
		tenantBookAddParam.setBookSettleCycle(RandomUtil.randomInt(0,1000+1));// 结算周期
		tenantBookAddParam.setBookSettleLastMonth(RandomUtil.randomString(4));// 最后一次结算月份
		tenantBookAddParam.setBookSettleMonth(RandomUtil.randomString(4));// 下次结算月份
		tenantBookAddParam.setBookStatus(RandomUtil.randomInt(0,1+1));// 有效状态（1：可用；0：禁用）
		tenantBookAddParam.setBookReadStatus(RandomUtil.randomInt(1,2+1));// 表册状态（1：抄表进行中；2：抄表截止）
		tenantBookAddParam.setPriceClass(RandomUtil.randomInt(0,1000+1));// 级次
		tenantBookAddParam.setPriceMemo(RandomUtil.randomString(4));// 备注

		log.info(ToStringBuilder.reflectionToString(tenantBookAddParam, ToStringStyle.MULTI_LINE_STYLE));

		String id = tenantBookService.save(tenantBookAddParam);

		log.info("id={}",id);
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
				.priceClass(RandomUtil.randomInt(0,1000+1))// 级次
				.priceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantBook.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantBook, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBookService.updateById(tenantBook);

		log.info(Boolean.toString(success));
	}
	
	@Test
	public void updateBatchTest() {

		String[] ids = new String[] {"0b31d1c0a89a4a5eb0e481f417b0e9a9","c47de065d6464467972fe0f33ba119fd"};

		TenantBookBatchUpdateParam tenantBookBatchUpdateParam  = new TenantBookBatchUpdateParam();
		tenantBookBatchUpdateParam.setIds(StringUtils.join(ids, ","));
		tenantBookBatchUpdateParam.setBookMarketingAreaId("MAI"+"-"+RandomUtil.randomString(4));
		tenantBookBatchUpdateParam.setOwn(RandomUtil.randomInt(0, 1+1));
		tenantBookBatchUpdateParam.setMeterread(RandomUtil.randomInt(0, 1+1));
		tenantBookBatchUpdateParam.setReceivable(RandomUtil.randomInt(0, 1+1));
		tenantBookBatchUpdateParam.setPay(RandomUtil.randomInt(0, 1+1));

		log.info(ToStringBuilder.reflectionToString(tenantBookBatchUpdateParam, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBookService.updateByIds(tenantBookBatchUpdateParam);

		log.info(Boolean.toString(success));
	}
	
	@Test
	public void getMaxBookCodeTest() {
		List<TenantBook> tenantBookList = tenantBookService.getMaxBookCode();
		tenantBookList.forEach(e->log.info(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
	}
	
}


