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
import com.zlsrj.wms.api.entity.TenantBusinessRulesDefault;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBusinessRulesDefaultMapperTest {

	@Resource
	private TenantBusinessRulesDefaultMapper tenantBusinessRulesDefaultMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "1";
		TenantBusinessRulesDefault tenantBusinessRulesDefault = tenantBusinessRulesDefaultMapper.selectById(id);
		log.info(tenantBusinessRulesDefault.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantBusinessRulesDefault> queryWrapper = new QueryWrapper<TenantBusinessRulesDefault>();
		List<TenantBusinessRulesDefault> tenantBusinessRulesDefaultList = tenantBusinessRulesDefaultMapper.selectList(queryWrapper);
		tenantBusinessRulesDefaultList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantBusinessRulesDefault tenantBusinessRulesDefault = TenantBusinessRulesDefault.builder()//
					.tenantType(RandomUtil.randomInt(1,5+1))// （1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
					.rulesType(RandomUtil.randomString(4))// 业务规则类型
					.rulesData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantBusinessRulesDefaultMapper.insert(tenantBusinessRulesDefault);
			log.info("count={}",count);
			log.info("tenantBusinessRulesDefault={}",tenantBusinessRulesDefault);
		}
		
	}
	
}
