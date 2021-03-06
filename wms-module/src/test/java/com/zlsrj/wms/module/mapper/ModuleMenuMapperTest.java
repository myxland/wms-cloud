package com.zlsrj.wms.module.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.ModuleMenu;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ModuleMenuMapperTest {

	@Resource
	private ModuleMenuMapper moduleMenuMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		ModuleMenu moduleMenu = moduleMenuMapper.selectById(id);
		log.info(moduleMenu.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<ModuleMenu> queryWrapper = new QueryWrapper<ModuleMenu>();
		List<ModuleMenu> moduleMenuList = moduleMenuMapper.selectList(queryWrapper);
		moduleMenuList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		ModuleMenu moduleMenu = ModuleMenu.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.build();
				
		int count = moduleMenuMapper.insert(moduleMenu);
		log.info("count={}",count);
		log.info("moduleMenu={}",moduleMenu);
	}
	
}
