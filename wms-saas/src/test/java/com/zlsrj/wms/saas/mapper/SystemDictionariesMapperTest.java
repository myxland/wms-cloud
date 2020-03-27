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
import com.zlsrj.wms.api.entity.SystemDictionaries;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemDictionariesMapperTest {

	@Resource
	private SystemDictionariesMapper systemDictionariesMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		SystemDictionaries systemDictionaries = systemDictionariesMapper.selectById(id);
		log.info(systemDictionaries.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<SystemDictionaries> queryWrapper = new QueryWrapper<SystemDictionaries>();
		List<SystemDictionaries> systemDictionariesList = systemDictionariesMapper.selectList(queryWrapper);
		systemDictionariesList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			SystemDictionaries systemDictionaries = SystemDictionaries.builder()//
					.id(TestCaseUtil.id())// 字典ID
					.dictCode(RandomUtil.randomString(4))// 字典编码
					.dictType(RandomUtil.randomString(4))// 字典类型
					.dictName(TestCaseUtil.name())// 名称
					.dictData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = systemDictionariesMapper.insert(systemDictionaries);
			log.info("count={}",count);
			log.info("systemDictionaries={}",systemDictionaries);
		}
		
	}
	
}
