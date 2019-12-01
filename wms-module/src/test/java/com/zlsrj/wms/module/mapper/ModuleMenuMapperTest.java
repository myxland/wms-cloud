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
				.parentMenuId(RandomUtil.randomLong())// 父菜单编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.menuName(TestCaseUtil.name())// 菜单名称
				.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
				.menuIcon(RandomUtil.randomString(4))// 菜单图标
				.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.basicUrl(RandomUtil.randomString(4))// 基础版链接地址
				.advanceUrl(RandomUtil.randomString(4))// 高级版链接地址
				.ultimateUrl(RandomUtil.randomString(4))// 旗舰版链接地址
				.perms(RandomUtil.randomString(4))// 授权
				.menuType(RandomUtil.randomInt(0,1+1))// 菜单类型（0：目录；1：菜单；2：按钮）
				.build();
				
		int count = moduleMenuMapper.insert(moduleMenu);
		log.info("count={}",count);
		log.info("moduleMenu={}",moduleMenu);
	}
	
}
