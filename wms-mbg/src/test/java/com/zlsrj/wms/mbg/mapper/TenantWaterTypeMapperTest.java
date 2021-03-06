package com.zlsrj.wms.mbg.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantWaterType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantWaterTypeMapperTest {

	@Resource
	private TenantWaterTypeMapper tenantWaterTypeMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantWaterType tenantWaterType = tenantWaterTypeMapper.selectById(id);
		log.info(tenantWaterType.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantWaterType> queryWrapper = new QueryWrapper<TenantWaterType>();
		List<TenantWaterType> tenantWaterTypeList = tenantWaterTypeMapper.selectList(queryWrapper);
		tenantWaterTypeList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantWaterType tenantWaterType = TenantWaterType.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.waterTypeName(TestCaseUtil.name())// 用水类别名称
				.build();
				
		int count = tenantWaterTypeMapper.insert(tenantWaterType);
		log.info("count={}",count);
		log.info("tenantWaterType={}",tenantWaterType);
	}
	
}
