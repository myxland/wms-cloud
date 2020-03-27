package com.zlsrj.wms.saas.mapper;

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
		String id = "";
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
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			ModuleInfo moduleInfo = ModuleInfo.builder()//
					.id(TestCaseUtil.id())// 
					.moduleName(TestCaseUtil.name())// 服务模块名称
					.openTarget(RandomUtil.randomInt(0,1000+1))// 开放对象（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
					.runEnv(RandomUtil.randomInt(0,1000+1))// 运行环境（1：PC端；2：移动端；3：微信端；4：支付宝端；5：API接口；6：自助终端）
					.relyModuleId(TestCaseUtil.id())// 所依赖的模块ID列表
					.billingMode(RandomUtil.randomInt(0,1000+1))// 计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）
					.billingCycle(RandomUtil.randomInt(0,1000+1))// 计费周期（0：不计费；1：实时；2：按天；3：按月；4：按年）
					.basicEditionOn(RandomUtil.randomInt(1,0+1))// 开放基础版（1：开放；0：不开放）
					.advanceEditionOn(RandomUtil.randomInt(1,0+1))// 开放高级版（1：开放；0：不开放）
					.ultimateEditionOn(RandomUtil.randomInt(1,0+1))// 开放旗舰版（1：开放；0：不开放）
					.moduleOn(RandomUtil.randomInt(1,0+1))// 服务发布状态（1：发布 ；0：未发布）
					.build();
				
			int count = moduleInfoMapper.insert(moduleInfo);
			log.info("count={}",count);
			log.info("moduleInfo={}",moduleInfo);
		}
		
	}
	
}
