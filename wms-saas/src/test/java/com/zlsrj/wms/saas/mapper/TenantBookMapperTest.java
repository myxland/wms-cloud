package com.zlsrj.wms.saas.mapper;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBookMapperTest {

	@Resource
	private TenantBookMapper tenantBookMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantBook tenantBook = tenantBookMapper.selectById(id);
		log.info(tenantBook.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantBook> queryWrapper = new QueryWrapper<TenantBook>();
		List<TenantBook> tenantBookList = tenantBookMapper.selectList(queryWrapper);
		tenantBookList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantBook tenantBook = TenantBook.builder()//
					.id(TestCaseUtil.id())// 表册ID
					.tenantId(tenantInfo.getId())// 租户ID
					.bookCode(RandomUtil.randomString(4))// 表册编号
					.bookName(TestCaseUtil.name())// 表册名称
					.bookReaderEmployeeId(TestCaseUtil.id())// 抄表员
					.bookChargeEmployeeId(TestCaseUtil.id())// 收费员
					.bookMarketingAreaId(TestCaseUtil.id())// 营销区域
					.bookReadCycle(RandomUtil.randomInt(0,1000+1))// 抄表周期
					.bookLastMonth(RandomUtil.randomString(4))// 最后一次抄表月份
					.bookReadMonth(RandomUtil.randomString(4))// 下次抄表月份
					.bookSettleCycle(RandomUtil.randomInt(0,1000+1))// 结算周期
					.bookSettleLastMonth(RandomUtil.randomString(4))// 最后一次结算月份
					.bookSettleMonth(RandomUtil.randomString(4))// 下次结算月份
					.bookStatus(RandomUtil.randomInt(1,0+1))// 有效状态（1：可用；0：禁用）
					.bookReadStatus(RandomUtil.randomInt(1,2+1))// 表册状态（1：抄表进行中；2：抄表截止）
					.bookMemo(RandomUtil.randomString(4))// 备注
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantBookMapper.insert(tenantBook);
			log.info("count={}",count);
			log.info("tenantBook={}",tenantBook);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantBook> wrapper = new QueryWrapper<TenantBook>();
		wrapper.lambda()//
				.eq(TenantBook::getTenantId, 1L)//
		;
		TenantBook tenantBookAggregation = tenantBookMapper.selectAggregation(wrapper);
		
		log.info("tenantBookAggregation={}", tenantBookAggregation);
	}
	
}
