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
import com.zlsrj.wms.api.entity.TenantMeterBrand;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterBrandMapperTest {

	@Resource
	private TenantMeterBrandMapper tenantMeterBrandMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterBrand tenantMeterBrand = tenantMeterBrandMapper.selectById(id);
		log.info(tenantMeterBrand.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterBrand> queryWrapper = new QueryWrapper<TenantMeterBrand>();
		List<TenantMeterBrand> tenantMeterBrandList = tenantMeterBrandMapper.selectList(queryWrapper);
		tenantMeterBrandList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantMeterBrand tenantMeterBrand = TenantMeterBrand.builder()//
					.id(TestCaseUtil.id())// 水表品牌ID
					.tenantId(tenantInfo.getId())// 租户ID
					.meterBrandName(TestCaseUtil.name())// 名称
					.meterBrandData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterBrandMapper.insert(tenantMeterBrand);
			log.info("count={}",count);
			log.info("tenantMeterBrand={}",tenantMeterBrand);
		}
		
	}
	
}
