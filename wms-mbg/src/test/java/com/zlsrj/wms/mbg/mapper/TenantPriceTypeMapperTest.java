package com.zlsrj.wms.mbg.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantPriceType;

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
		Long id = 1L;
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
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomInt(0,1000+1))// 租户编号
				.priceTypeName(TestCaseUtil.name())// 价格类别名称
				.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（启用/不启用）
				.bottomNum(RandomUtil.randomInt(0,1000+1))// 保底水量
				.topOn(RandomUtil.randomInt(0,1+1))// 启用封顶水量（启用/不启用）
				.topNum(RandomUtil.randomInt(0,1000+1))// 封顶水量
				.reduceOn(RandomUtil.randomInt(0,1+1))// 启用固定减免（启用/不启用）
				.reduceNum(RandomUtil.randomInt(0,1000+1))// 固定减免水量
				.reduceLowerLimit(RandomUtil.randomInt(0,1000+1))// 减免起始水量（当月多少吨以上才可以减免）
				.fixedOn(RandomUtil.randomInt(0,1+1))// 启用固定水量征收（启用/不启用）
				.fixedNum(RandomUtil.randomInt(0,1000+1))// 固定征收水量
				.build();
				
		int count = tenantPriceTypeMapper.insert(tenantPriceType);
		log.info("count={}",count);
		log.info("tenantPriceType={}",tenantPriceType);
	}
	
}
