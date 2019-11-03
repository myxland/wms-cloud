package com.zlsrj.wms.mbg.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantCustType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustTypeMapperTest {

	@Resource
	private TenantCustTypeMapper tenantCustTypeMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantCustType tenantCustType = tenantCustTypeMapper.selectById(id);
		log.info(tenantCustType.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustType> queryWrapper = new QueryWrapper<TenantCustType>();
		List<TenantCustType> tenantCustTypeList = tenantCustTypeMapper.selectList(queryWrapper);
		tenantCustTypeList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantCustType tenantCustType = TenantCustType.builder()//
				.id(TestCaseUtil.id())// 用户类型
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custTypeName(TestCaseUtil.name())// 用户类别名称
				.build();
				
		int count = tenantCustTypeMapper.insert(tenantCustType);
		log.info("count={}",count);
		log.info("tenantCustType={}",tenantCustType);
	}
	
}
