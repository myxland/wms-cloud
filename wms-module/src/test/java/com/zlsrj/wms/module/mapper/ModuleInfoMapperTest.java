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
				.relyId(RandomUtil.randomLong())// 依赖模块编码
				.moduleName(TestCaseUtil.name())// 模块名称
				.openTenantType(RandomUtil.randomInt(0,1+1))// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				.runEnvType(RandomUtil.randomInt(0,1+1))// 运行环境（1：PC；2：移动端）
				.pricePolicyType(RandomUtil.randomInt(0,1+1))// 价格政策（0：免费；1：按量付费；2：固定价格）
				.billCycleType(RandomUtil.randomInt(0,1+1))// 计费周期（1：实时；2：按天；3：按月；4：按年）
				.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.moduleReleaseOn(RandomUtil.randomInt(0,1+1))// 功能发布（1：已发布；0：未发布）
				.build();
				
		int count = moduleInfoMapper.insert(moduleInfo);
		log.info("count={}",count);
		log.info("moduleInfo={}",moduleInfo);
	}
	
}
