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
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterMarketingAreaMapperTest {

	@Resource
	private TenantMeterMarketingAreaMapper tenantMeterMarketingAreaMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterMarketingArea tenantMeterMarketingArea = tenantMeterMarketingAreaMapper.selectById(id);
		log.info(tenantMeterMarketingArea.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterMarketingArea> queryWrapper = new QueryWrapper<TenantMeterMarketingArea>();
		List<TenantMeterMarketingArea> tenantMeterMarketingAreaList = tenantMeterMarketingAreaMapper.selectList(queryWrapper);
		tenantMeterMarketingAreaList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantMeterMarketingArea tenantMeterMarketingArea = TenantMeterMarketingArea.builder()//
					.id(TestCaseUtil.id())// 营销机构ID
					.tenantId(tenantInfo.getId())// 租户ID
					.marketingAreaType(RandomUtil.randomInt(0,1+1))// 区域类型（0：内部机构；1：代收机构）
					.marketingAreaName(TestCaseUtil.name())// 名称
					.marketingAreaParentId(TestCaseUtil.id())// 父级ID
					.marketingAreaData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterMarketingAreaMapper.insert(tenantMeterMarketingArea);
			log.info("count={}",count);
			log.info("tenantMeterMarketingArea={}",tenantMeterMarketingArea);
		}
		
	}
	
}
