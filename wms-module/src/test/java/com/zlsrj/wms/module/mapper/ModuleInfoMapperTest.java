package com.zlsrj.wms.module.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.ModuleInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ModuleInfoMapperTest {

	@Resource
	private ModuleInfoMapper moduleInfoMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		ModuleInfo moduleInfo = moduleInfoMapper.selectById(id);
		log.info(moduleInfo.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<ModuleInfo> queryWrapper = new QueryWrapper<ModuleInfo>();
		List<ModuleInfo> moduleInfoList = moduleInfoMapper.selectList(queryWrapper);
		moduleInfoList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		ModuleInfo moduleInfo = ModuleInfo.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.build();
				
		int count = moduleInfoMapper.insert(moduleInfo);
		log.info("count={}",count);
		log.info("moduleInfo={}",moduleInfo);
	}
	
}
