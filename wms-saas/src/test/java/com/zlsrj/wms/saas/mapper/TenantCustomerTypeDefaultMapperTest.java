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
import com.zlsrj.wms.api.entity.TenantCustomerTypeDefault;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerTypeDefaultMapperTest {

	@Resource
	private TenantCustomerTypeDefaultMapper tenantCustomerTypeDefaultMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantCustomerTypeDefault tenantCustomerTypeDefault = tenantCustomerTypeDefaultMapper.selectById(id);
		log.info(tenantCustomerTypeDefault.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomerTypeDefault> queryWrapper = new QueryWrapper<TenantCustomerTypeDefault>();
		List<TenantCustomerTypeDefault> tenantCustomerTypeDefaultList = tenantCustomerTypeDefaultMapper.selectList(queryWrapper);
		tenantCustomerTypeDefaultList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCustomerTypeDefault tenantCustomerTypeDefault = TenantCustomerTypeDefault.builder()//
					.id(TestCaseUtil.id())// 
					.tenantId(tenantInfo.getId())// 租户ID
					.customerTypeName(TestCaseUtil.name())// 用户分类名称
					.customerTypeData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantCustomerTypeDefaultMapper.insert(tenantCustomerTypeDefault);
			log.info("count={}",count);
			log.info("tenantCustomerTypeDefault={}",tenantCustomerTypeDefault);
		}
		
	}
	
}
