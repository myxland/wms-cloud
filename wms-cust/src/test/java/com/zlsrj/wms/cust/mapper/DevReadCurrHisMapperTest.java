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
import com.zlsrj.wms.api.entity.DevReadCurrHis;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DevReadCurrHisMapperTest {

	@Resource
	private DevReadCurrHisMapper devReadCurrHisMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		DevReadCurrHis devReadCurrHis = devReadCurrHisMapper.selectById(id);
		log.info(devReadCurrHis.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<DevReadCurrHis> queryWrapper = new QueryWrapper<DevReadCurrHis>();
		List<DevReadCurrHis> devReadCurrHisList = devReadCurrHisMapper.selectList(queryWrapper);
		devReadCurrHisList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		DevReadCurrHis devReadCurrHis = DevReadCurrHis.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.readMonth(new Date())// 抄表月份
				.devId(RandomUtil.randomLong())// 表具系统编号
				.parentDevId(RandomUtil.randomLong())// 父表具编号
				.yearUseNum(new BigDecimal(0))// 本次计费前当年累计水量
				.lastCalcDate(new Date())// 上次计费日期
				.lastCalcCode(new BigDecimal(0))// 上次计费止码
				.currReadEmpId(RandomUtil.randomLong())// 抄表人编号
				.currReadDate(new Date())// 本次抄表日期
				.currReadCode(new BigDecimal(0))// 本次抄表止码
				.currDevStatus(RandomUtil.randomInt(0,1+1))// 表次表具状况
				.currUseNum(new BigDecimal(0))// 本次抄表水量
				.currCalcUseNum(new BigDecimal(0))// 本次计费水量
				.readSource(RandomUtil.randomInt(0,1000+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表）
				.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（1：未抄；2：已抄）
				.checkResult(RandomUtil.randomInt(0,1000+1))// 审核状态（1：正常；2：异常）
				.procReault(RandomUtil.randomInt(0,1000+1))// 处理状态（1：已计费；2：待审核；3：已审核）
				.procMan(RandomUtil.randomLong())// 审核人
				.procTime(new Date())// 审核时间
				.build();
				
		int count = devReadCurrHisMapper.insert(devReadCurrHis);
		log.info("count={}",count);
		log.info("devReadCurrHis={}",devReadCurrHis);
	}
	
}
