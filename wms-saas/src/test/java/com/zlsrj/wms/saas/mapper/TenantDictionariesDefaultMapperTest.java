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
import com.zlsrj.wms.api.entity.TenantDictionariesDefault;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantDictionariesDefaultMapperTest {

	@Resource
	private TenantDictionariesDefaultMapper tenantDictionariesDefaultMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantDictionariesDefault tenantDictionariesDefault = tenantDictionariesDefaultMapper.selectById(id);
		log.info(tenantDictionariesDefault.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantDictionariesDefault> queryWrapper = new QueryWrapper<TenantDictionariesDefault>();
		List<TenantDictionariesDefault> tenantDictionariesDefaultList = tenantDictionariesDefaultMapper.selectList(queryWrapper);
		tenantDictionariesDefaultList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantDictionariesDefault tenantDictionariesDefault = TenantDictionariesDefault.builder()//
					.tenantType(RandomUtil.randomInt(0,1))// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
					.dictCode(RandomUtil.randomInt(0,1000+1))// 字典编码
					.dictType(RandomUtil.randomInt(0,1))// 字典类型
					.dictName(TestCaseUtil.name())// 名称
					.dictData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantDictionariesDefaultMapper.insert(tenantDictionariesDefault);
			log.info("count={}",count);
			log.info("tenantDictionariesDefault={}",tenantDictionariesDefault);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantDictionariesDefault> wrapper = new QueryWrapper<TenantDictionariesDefault>();
		wrapper.lambda()//
		;
		TenantDictionariesDefault tenantDictionariesDefaultAggregation = tenantDictionariesDefaultMapper.selectAggregation(wrapper);
		
		log.info("tenantDictionariesDefaultAggregation={}", tenantDictionariesDefaultAggregation);
	}
	
}
