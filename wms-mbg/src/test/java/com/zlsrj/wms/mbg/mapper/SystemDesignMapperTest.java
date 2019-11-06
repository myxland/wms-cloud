package com.zlsrj.wms.mbg.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemDesignMapperTest {

	@Resource
	private SystemDesignMapper systemDesignMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		SystemDesign systemDesign = systemDesignMapper.selectById(id);
		log.info(systemDesign.toString());
	}

	@Test
	public void selectList() {
		QueryWrapper<SystemDesign> queryWrapper = new QueryWrapper<SystemDesign>();
		List<SystemDesign> systemDesignList = systemDesignMapper.selectList(queryWrapper);
		systemDesignList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		SystemDesign systemDesign = SystemDesign.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.relyId(RandomUtil.randomLong())// 依赖模块编码
				.moduleName(TestCaseUtil.name())// 模块名称
				.openTenantType(RandomUtil.randomInt(0, 1 + 1))// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				.runEnvType(RandomUtil.randomInt(0, 1 + 1))// 运行环境（1：PC；2：移动端）
				.pricePolicyType(RandomUtil.randomInt(0, 1 + 1))// 价格政策（0：免费；1：按量付费；2：固定价格）
				.billCycleType(RandomUtil.randomInt(0, 1 + 1))// 计费周期（1：实时；2：按天；3：按月；4：按年）
				.basicOn(RandomUtil.randomInt(0, 1 + 1))// 开放基础版（1：开放；0：不开放）
				.advanceOn(RandomUtil.randomInt(0, 1 + 1))// 开放高级版（1：开放；0：不开放）
				.ultimateOn(RandomUtil.randomInt(0, 1 + 1))// 开放旗舰版（1：开放；0：不开放）
				.moduleReleaseOn(RandomUtil.randomInt(0, 1 + 1))// 功能发布（1：已发布；0：未发布）
				.build();

		int count = systemDesignMapper.insert(systemDesign);
		log.info("count={}", count);
		log.info("systemDesign={}", systemDesign);
	}

	@Test
	public void insertBatch() {
		SystemDesign systemDesign = SystemDesign.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.relyId(null)// 依赖模块编码
				.moduleName("云运营平台")// 模块名称
				.openTenantType(3)// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				.runEnvType(1)// 运行环境（1：PC；2：移动端）
				.pricePolicyType(2)// 价格政策（0：免费；1：按量付费；2：固定价格）
				.billCycleType(2)// 计费周期（1：实时；2：按天；3：按月；4：按年）
				.basicOn(1)// 开放基础版（1：开放；0：不开放）
				.advanceOn(1)// 开放高级版（1：开放；0：不开放）
				.ultimateOn(1)// 开放旗舰版（1：开放；0：不开放）
				.moduleReleaseOn(1)// 功能发布（1：已发布；0：未发布）
				.build();

		systemDesignMapper.insert(systemDesign);

		systemDesign = SystemDesign.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.relyId(null)// 依赖模块编码
				.moduleName("控制台")// 模块名称
				.openTenantType(2)// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				.runEnvType(1)// 运行环境（1：PC；2：移动端）
				.pricePolicyType(0)// 价格政策（0：免费；1：按量付费；2：固定价格）
				.billCycleType(1)// 计费周期（1：实时；2：按天；3：按月；4：按年）
				.basicOn(1)// 开放基础版（1：开放；0：不开放）
				.advanceOn(1)// 开放高级版（1：开放；0：不开放）
				.ultimateOn(1)// 开放旗舰版（1：开放；0：不开放）
				.moduleReleaseOn(1)// 功能发布（1：已发布；0：未发布）
				.build();

		systemDesignMapper.insert(systemDesign);

		systemDesign = SystemDesign.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.relyId(null)// 依赖模块编码
				.moduleName("设备管理")// 模块名称
				.openTenantType(2)// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				.runEnvType(1)// 运行环境（1：PC；2：移动端）
				.pricePolicyType(1)// 价格政策（0：免费；1：按量付费；2：固定价格）
				.billCycleType(3)// 计费周期（1：实时；2：按天；3：按月；4：按年）
				.basicOn(1)// 开放基础版（1：开放；0：不开放）
				.advanceOn(1)// 开放高级版（1：开放；0：不开放）
				.ultimateOn(1)// 开放旗舰版（1：开放；0：不开放）
				.moduleReleaseOn(1)// 功能发布（1：已发布；0：未发布）
				.build();

		systemDesignMapper.insert(systemDesign);
		
		systemDesign = SystemDesign.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.relyId(null)// 依赖模块编码
				.moduleName("控制台")// 模块名称
				.openTenantType(1)// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				.runEnvType(1)// 运行环境（1：PC；2：移动端）
				.pricePolicyType(0)// 价格政策（0：免费；1：按量付费；2：固定价格）
				.billCycleType(1)// 计费周期（1：实时；2：按天；3：按月；4：按年）
				.basicOn(1)// 开放基础版（1：开放；0：不开放）
				.advanceOn(1)// 开放高级版（1：开放；0：不开放）
				.ultimateOn(1)// 开放旗舰版（1：开放；0：不开放）
				.moduleReleaseOn(1)// 功能发布（1：已发布；0：未发布）
				.build();

		systemDesignMapper.insert(systemDesign);

		systemDesign = SystemDesign.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.relyId(null)// 依赖模块编码
				.moduleName("设备管理")// 模块名称
				.openTenantType(1)// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				.runEnvType(1)// 运行环境（1：PC；2：移动端）
				.pricePolicyType(1)// 价格政策（0：免费；1：按量付费；2：固定价格）
				.billCycleType(3)// 计费周期（1：实时；2：按天；3：按月；4：按年）
				.basicOn(1)// 开放基础版（1：开放；0：不开放）
				.advanceOn(1)// 开放高级版（1：开放；0：不开放）
				.ultimateOn(1)// 开放旗舰版（1：开放；0：不开放）
				.moduleReleaseOn(1)// 功能发布（1：已发布；0：未发布）
				.build();

		systemDesignMapper.insert(systemDesign);

	}

}
