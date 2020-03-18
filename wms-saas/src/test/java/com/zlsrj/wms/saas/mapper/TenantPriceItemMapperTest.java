package com.zlsrj.wms.saas.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceItemMapperTest {

	@Resource
	private TenantPriceItemMapper tenantPriceItemMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantPriceItem tenantPriceItem = tenantPriceItemMapper.selectById(id);
		log.info(tenantPriceItem.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantPriceItem> queryWrapper = new QueryWrapper<TenantPriceItem>();
		List<TenantPriceItem> tenantPriceItemList = tenantPriceItemMapper.selectList(queryWrapper);
		tenantPriceItemList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantPriceItem tenantPriceItem = TenantPriceItem.builder()//
					.id(TestCaseUtil.id())// 
					.tenantId(tenantInfo.getId())// 租户ID
					.priceItemCode(RandomUtil.randomInt(0,1000+1))// 费用项目编码
					.priceItemName(TestCaseUtil.name())// 费用项目名称
					.priceItemTaxRate(TestCaseUtil.rate())// 税率
					.priceItemTaxCode(RandomUtil.randomString(4))// 税收分类编码
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantPriceItemMapper.insert(tenantPriceItem);
			log.info("count={}",count);
			log.info("tenantPriceItem={}",tenantPriceItem);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantPriceItem> wrapper = new QueryWrapper<TenantPriceItem>();
		wrapper.lambda()//
				.eq(TenantPriceItem::getTenantId, 1L)//
		;
		TenantPriceItem tenantPriceItemAggregation = tenantPriceItemMapper.selectAggregation(wrapper);
		
		log.info("tenantPriceItemAggregation={}", tenantPriceItemAggregation);
	}
	
}
