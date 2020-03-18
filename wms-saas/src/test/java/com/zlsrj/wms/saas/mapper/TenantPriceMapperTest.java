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
import com.zlsrj.wms.api.entity.TenantPrice;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceMapperTest {

	@Resource
	private TenantPriceMapper tenantPriceMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantPrice tenantPrice = tenantPriceMapper.selectById(id);
		log.info(tenantPrice.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantPrice> queryWrapper = new QueryWrapper<TenantPrice>();
		List<TenantPrice> tenantPriceList = tenantPriceMapper.selectList(queryWrapper);
		tenantPriceList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantPrice tenantPrice = TenantPrice.builder()//
					.id(TestCaseUtil.id())// 
					.tenantId(tenantInfo.getId())// 租户ID
					.priceOrder(RandomUtil.randomInt(0,1000+1))// 排序
					.priceName(TestCaseUtil.name())// 水价名称
					.priceParentId(TestCaseUtil.id())// 父级ID
					.priceVersion(RandomUtil.randomString(4))// 水价版本
					.priceVersionMemo(RandomUtil.randomString(4))// 版本说明
					.marketingAreaId(TestCaseUtil.id())// 营销区域
					.priceMemo(RandomUtil.randomString(4))// 备注
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantPriceMapper.insert(tenantPrice);
			log.info("count={}",count);
			log.info("tenantPrice={}",tenantPrice);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantPrice> wrapper = new QueryWrapper<TenantPrice>();
		wrapper.lambda()//
				.eq(TenantPrice::getTenantId, 1L)//
		;
		TenantPrice tenantPriceAggregation = tenantPriceMapper.selectAggregation(wrapper);
		
		log.info("tenantPriceAggregation={}", tenantPriceAggregation);
	}
	
}
