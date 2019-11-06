package com.zlsrj.wms.mbg.mapper;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.SystemMenuDesign;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemMenuDesignMapperTest {

	@Resource
	private SystemMenuDesignMapper systemMenuDesignMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		SystemMenuDesign systemMenuDesign = systemMenuDesignMapper.selectById(id);
		log.info(systemMenuDesign.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<SystemMenuDesign> queryWrapper = new QueryWrapper<SystemMenuDesign>();
		List<SystemMenuDesign> systemMenuDesignList = systemMenuDesignMapper.selectList(queryWrapper);
		systemMenuDesignList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		SystemMenuDesign systemMenuDesign = SystemMenuDesign.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.parentMenuId(RandomUtil.randomLong())// 父菜单编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.menuName(TestCaseUtil.name())// 菜单名称
				.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
				.menuIcon(RandomUtil.randomString(4))// 菜单图标
				.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1开放0不开放）
				.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1开放0不开放）
				.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1开放0不开放）
				.basicUrl(RandomUtil.randomString(4))// 基础版链接地址
				.advanceUrl(RandomUtil.randomString(4))// 高级版链接地址
				.ultimateUrl(RandomUtil.randomString(4))// 旗舰版链接地址
				.build();
				
		int count = systemMenuDesignMapper.insert(systemMenuDesign);
		log.info("count={}",count);
		log.info("systemMenuDesign={}",systemMenuDesign);
	}
	
	@Test
	public void insertBatch() {
		
		Arrays.asList(new String[] {"用户管理","角色管理","控制中心"}).forEach(s->{
			SystemMenuDesign systemMenuDesign = SystemMenuDesign.builder()//
					.id(TestCaseUtil.id())// 系统ID
					.parentMenuId(null)// 父菜单编号
					.sysId(1191180168606126080L)// 模块编号
					.menuName(s)// 菜单名称
					.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
					.menuIcon("edit.ico")// 菜单图标
					.basicOn(1)// 开放基础版（1开放0不开放）
					.advanceOn(1)// 开放高级版（1开放0不开放）
					.ultimateOn(1)// 开放旗舰版（1开放0不开放）
					.basicUrl("http://www.baidu.com")// 基础版链接地址
					.advanceUrl("http://www.360.com")// 高级版链接地址
					.ultimateUrl("http://www.so.com")// 旗舰版链接地址
					.build();
					
			int count = systemMenuDesignMapper.insert(systemMenuDesign);
			log.info("count={}",count);
			log.info("systemMenuDesign={}",systemMenuDesign);
		});
		
		
	}
	
}
