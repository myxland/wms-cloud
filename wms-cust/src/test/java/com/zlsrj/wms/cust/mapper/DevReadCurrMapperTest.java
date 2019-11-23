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
import com.zlsrj.wms.api.entity.DevReadCurr;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DevReadCurrMapperTest {

	@Resource
	private DevReadCurrMapper devReadCurrMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		DevReadCurr devReadCurr = devReadCurrMapper.selectById(id);
		log.info(devReadCurr.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<DevReadCurr> queryWrapper = new QueryWrapper<DevReadCurr>();
		List<DevReadCurr> devReadCurrList = devReadCurrMapper.selectList(queryWrapper);
		devReadCurrList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for (int i = 0; i < 100; i++) {
			DevReadCurr devReadCurr = DevReadCurr.builder()//
					.id(TestCaseUtil.id())// 系统编号
					.tenantId(TestCaseUtil.id())// 租户编号
					.readMonth(TestCaseUtil.monthBefore())// 抄表月份
					.devId(TestCaseUtil.id())// 表具系统编号
					.parentDevId(TestCaseUtil.id())// 父表具编号
					.yearUseNum(RandomUtil.randomBigDecimal(BigDecimal.ZERO, new BigDecimal(1000)))// 本次计费前当年累计水量
					.lastCalcDate(TestCaseUtil.dateBefore())// 上次计费日期
					.lastCalcCode(RandomUtil.randomBigDecimal(BigDecimal.ZERO, new BigDecimal(1000)))// 上次计费止码
					.currReadEmpId(TestCaseUtil.id())// 抄表人编号
					.currReadDate(TestCaseUtil.dateBefore())// 本次抄表日期
					.currReadCode(RandomUtil.randomBigDecimal(BigDecimal.ZERO, new BigDecimal(1000)))// 本次抄表止码
					.currDevStatus(RandomUtil.randomInt(1,1+1))// 表次表具状况
					.currUseNum(RandomUtil.randomBigDecimal(BigDecimal.ZERO, new BigDecimal(1000)))// 本次抄表水量
					.currCalcUseNum(RandomUtil.randomBigDecimal(BigDecimal.ZERO, new BigDecimal(1000)))// 本次计费水量
					.readSource(RandomUtil.randomInt(1,3+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表）
					.readStatus(RandomUtil.randomInt(1,2+1))// 抄表状态（1：未抄；2：已抄）
					.checkResult(RandomUtil.randomInt(1,2+1))// 审核状态（1：正常；2：异常）
					.procReault(RandomUtil.randomInt(1,3+1))// 处理状态（1：已计费；2：待审核；3：已审核）
					.procMan(TestCaseUtil.id())// 审核人
					.procTime(TestCaseUtil.dateBefore())// 审核时间
					.build();
					
			int count = devReadCurrMapper.insert(devReadCurr);
			log.info("count={}",count);
			log.info("devReadCurr={}",devReadCurr);
		}
		
	}
	
}
