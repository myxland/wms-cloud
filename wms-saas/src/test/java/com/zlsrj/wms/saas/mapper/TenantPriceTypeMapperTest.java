package com.zlsrj.wms.saas.mapper;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantPriceType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceTypeMapperTest {

	@Resource
	private TenantPriceTypeMapper tenantPriceTypeMapper;

	@Test
	public void selectByIdTest() {
		String id = "";
		TenantPriceType tenantPriceType = tenantPriceTypeMapper.selectById(id);
		log.info(tenantPriceType.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantPriceType> queryWrapper = new QueryWrapper<TenantPriceType>();
		List<TenantPriceType> tenantPriceTypeList = tenantPriceTypeMapper.selectList(queryWrapper);
		tenantPriceTypeList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantPriceType tenantPriceType = TenantPriceType.builder()//
				.id(TestCaseUtil.id())// 价格类别ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.priceTypeName(TestCaseUtil.name())// 价格类别名称
				.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（1：启用；0：不启用）
				.bottomWaters(new BigDecimal(0))// 保底水量
				.topOn(RandomUtil.randomInt(0,1+1))// 启用封顶水量（1：启用；0：不启用）
				.topWaters(new BigDecimal(0))// 封顶水量
				.reduceOn(RandomUtil.randomInt(0,1+1))// 启用固定减免（1：启用；0：不启用）
				.reduceWaters(new BigDecimal(0))// 固定减免水量
				.reduceLowerlimit(new BigDecimal(0))// 固定减免水量下限
				.fixedOn(RandomUtil.randomInt(0,1+1))// 启用固定水量征收（1：启用；0：不启用）
				.fixedWaters(new BigDecimal(0))// 固定征收水量
				.build();
				
		int count = tenantPriceTypeMapper.insert(tenantPriceType);
		log.info("count={}",count);
		log.info("tenantPriceType={}",tenantPriceType);
	}
	
}
