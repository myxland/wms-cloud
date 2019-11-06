package com.zlsrj.wms.mbg.mapper;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantPriceItem;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceItemMapperTest {

	@Resource
	private TenantPriceItemMapper tenantPriceItemMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
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
		TenantPriceItem tenantPriceItem = TenantPriceItem.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceItemName(TestCaseUtil.name())// 费用项目名称
				.taxRate(new BigDecimal(0))// 税率
				.taxId(RandomUtil.randomString(4))// 对应税控项目编号
				.build();
				
		int count = tenantPriceItemMapper.insert(tenantPriceItem);
		log.info("count={}",count);
		log.info("tenantPriceItem={}",tenantPriceItem);
	}
	
}
