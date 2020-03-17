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
import com.zlsrj.wms.api.entity.TenantCustomerType;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerTypeMapperTest {

	@Resource
	private TenantCustomerTypeMapper tenantCustomerTypeMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantCustomerType tenantCustomerType = tenantCustomerTypeMapper.selectById(id);
		log.info(tenantCustomerType.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomerType> queryWrapper = new QueryWrapper<TenantCustomerType>();
		List<TenantCustomerType> tenantCustomerTypeList = tenantCustomerTypeMapper.selectList(queryWrapper);
		tenantCustomerTypeList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCustomerType tenantCustomerType = TenantCustomerType.builder()//
					.id(TestCaseUtil.id())// 
					.tenantId(tenantInfo.getId())// 租户ID
					.customerTypeName(TestCaseUtil.name())// 用户分类名称
					.customerTypeData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantCustomerTypeMapper.insert(tenantCustomerType);
			log.info("count={}",count);
			log.info("tenantCustomerType={}",tenantCustomerType);
		}
		
	}
	
}
