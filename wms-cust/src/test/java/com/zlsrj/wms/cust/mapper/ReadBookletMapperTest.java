package com.zlsrj.wms.cust.mapper;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.ReadBooklet;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ReadBookletMapperTest {

	@Resource
	private ReadBookletMapper readBookletMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		ReadBooklet readBooklet = readBookletMapper.selectById(id);
		log.info(readBooklet.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<ReadBooklet> queryWrapper = new QueryWrapper<ReadBooklet>();
		List<ReadBooklet> readBookletList = readBookletMapper.selectList(queryWrapper);
		readBookletList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for(int i=0;i<100;i++) {
			ReadBooklet readBooklet = ReadBooklet.builder()//
					.id(TestCaseUtil.id())// 系统编号
					.tenantId(RandomUtil.randomLong())// 租户编号
					.bookletName(TestCaseUtil.name())// 表册名称
					.bookletType(RandomUtil.randomInt(1,2+1))// 表册类型（1：远传表；2：机械表）
					.readEmpId(RandomUtil.randomLong())// 抄表负责人编号
					.payEmpId(RandomUtil.randomLong())// 收费负责人编号
					.calcCycleInterval(RandomUtil.randomInt(0,1000+1))// 抄表间隔周期_月
					.calcMonthLast(new Date())// 最后一次抄表月份
					.calcMonthNext(new Date())// 下次抄表月份
					.build();
					
			int count = readBookletMapper.insert(readBooklet);
			log.info("count={}",count);
			log.info("readBooklet={}",readBooklet);
		}
		
	}
	
}
