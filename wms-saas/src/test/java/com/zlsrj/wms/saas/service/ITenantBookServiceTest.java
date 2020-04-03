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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookBatchUpdateParam;
import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.saas.mapper.TenantEmployeeMapper;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterMarketingAreaMapper;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantBookServiceTest {

	@Autowired
	private ITenantBookService tenantBookService;
	@Autowired
	private TenantInfoMapper tenantInfoMapper;
	@Autowired
	private TenantEmployeeMapper tenantEmployeeMapper;
	@Autowired
	private TenantMeterMarketingAreaMapper tenantMeterMarketingAreaMapper;

	@Test
	public void insertTest() {
		for(int i=0;i<5;i++) {
			QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
			queryWrapperTenantInfo.lambda()//
					.eq(TenantInfo::getTenantType, 1)// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
					.in(TenantInfo::getId,"933d88d4d23244079cc0b49f99aa2c0b",
							"e1ddb601b6cc48b79f989d710712f6d0");
			;
			
			List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(queryWrapperTenantInfo);
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			
			String tenantId = tenantInfo.getId();
			
			QueryWrapper<TenantMeterMarketingArea> queryWrapperTenantMeterMarketingArea = new QueryWrapper<TenantMeterMarketingArea>();
			queryWrapperTenantMeterMarketingArea.lambda()//
					.eq(TenantMeterMarketingArea::getTenantId, tenantId)// 
			;

			List<TenantMeterMarketingArea> tenantMeterMarketingAreaList = tenantMeterMarketingAreaMapper.selectList(queryWrapperTenantMeterMarketingArea);
			TenantMeterMarketingArea tenantMeterMarketingArea = tenantMeterMarketingAreaList.get(RandomUtil.randomInt(tenantMeterMarketingAreaList.size()));
			
			
			QueryWrapper<TenantEmployee> queryWrapperTenantEmployee = new QueryWrapper<TenantEmployee>();
			queryWrapperTenantEmployee.lambda()//
					.eq(TenantEmployee::getTenantId, tenantId)// 
			;

			List<TenantEmployee> tenantEmployeeList = tenantEmployeeMapper.selectList(queryWrapperTenantEmployee);
			TenantEmployee tenantEmployee = tenantEmployeeList.get(RandomUtil.randomInt(tenantEmployeeList.size()));
			
			
			
			TenantBookAddParam tenantBookAddParam = new TenantBookAddParam();
			tenantBookAddParam.setTenantId(tenantId);// 租户ID
//			tenantBookAddParam.setBookCode(RandomUtil.randomString(4));// 表册编号
			tenantBookAddParam.setBookName("表册名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 表册名称
			tenantBookAddParam.setBookReaderEmployeeId(tenantEmployee.getId());// 抄表员
			tenantBookAddParam.setBookChargeEmployeeId(tenantEmployee.getId());// 收费员
			tenantBookAddParam.setBookMarketingAreaId(tenantMeterMarketingArea.getId());// 营销区域
			tenantBookAddParam.setBookReadCycle(RandomUtil.randomInt(0,1000+1));// 抄表周期
			tenantBookAddParam.setBookLastMonth(RandomUtil.randomString(4));// 最后一次抄表月份
			tenantBookAddParam.setBookReadMonth(RandomUtil.randomString(4));// 下次抄表月份
			tenantBookAddParam.setBookSettleCycle(RandomUtil.randomInt(0,1000+1));// 结算周期
			tenantBookAddParam.setBookSettleLastMonth(RandomUtil.randomString(4));// 最后一次结算月份
			tenantBookAddParam.setBookSettleMonth(RandomUtil.randomString(4));// 下次结算月份
			tenantBookAddParam.setBookStatus(RandomUtil.randomInt(0,1+1));// 有效状态（1：可用；0：禁用）
			tenantBookAddParam.setBookReadStatus(RandomUtil.randomInt(1,2+1));// 表册状态（1：抄表进行中；2：抄表截止）
			tenantBookAddParam.setBookMemo(RandomUtil.randomString(4));// 备注

			log.info(ToStringBuilder.reflectionToString(tenantBookAddParam, ToStringStyle.MULTI_LINE_STYLE));

			String id = tenantBookService.save(tenantBookAddParam);

			log.info("id={}",id);
		}
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
				.bookMemo(RandomUtil.randomString(4))// 备注
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


