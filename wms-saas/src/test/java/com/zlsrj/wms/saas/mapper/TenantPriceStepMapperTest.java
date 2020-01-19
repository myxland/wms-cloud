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
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.entity.TenantPriceItem;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceStepMapperTest {

	@Resource
	private TenantPriceStepMapper tenantPriceStepMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantPriceTypeMapper tenantPriceTypeMapper;
	@Resource
	private TenantPriceItemMapper tenantPriceItemMapper;
	
	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantPriceStep tenantPriceStep = tenantPriceStepMapper.selectById(id);
		log.info(tenantPriceStep.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantPriceStep> queryWrapper = new QueryWrapper<TenantPriceStep>();
		List<TenantPriceStep> tenantPriceStepList = tenantPriceStepMapper.selectList(queryWrapper);
		tenantPriceStepList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantPriceType tenantPriceType = null;
			List<TenantPriceType> tenantPriceTypeList = tenantPriceTypeMapper.selectList(new QueryWrapper<TenantPriceType>().lambda().eq(TenantPriceType::getTenantId, tenantInfo.getId()));
			if(tenantPriceTypeList!=null && tenantPriceTypeList.size()>0) {
				tenantPriceType = tenantPriceTypeList.get(RandomUtil.randomInt(tenantPriceTypeList.size()));
			}
			
			TenantPriceItem tenantPriceItem = null;
			List<TenantPriceItem> tenantPriceItemList = tenantPriceItemMapper.selectList(new QueryWrapper<TenantPriceItem>().lambda().eq(TenantPriceItem::getTenantId, tenantInfo.getId()));
			if(tenantPriceItemList!=null && tenantPriceItemList.size()>0) {
				tenantPriceItem = tenantPriceItemList.get(RandomUtil.randomInt(tenantPriceItemList.size()));
			}
			
			TenantPriceStep tenantPriceStep = TenantPriceStep.builder()//
					.id(TestCaseUtil.id())// 价格阶梯ID
					.tenantId(tenantInfo.getId())// 租户ID
					.priceTypeId(tenantPriceType!=null?tenantPriceType.getId():null)// 价格类别ID
					.priceItemId(tenantPriceItem!=null?tenantPriceItem.getId():null)// 费用项目ID
					.stepNo(RandomUtil.randomInt(0,1000+1))// 阶梯号
					.startWaters(TestCaseUtil.water())// 起始量
					.endWaters(TestCaseUtil.water())// 终止量
					.stepPrice(TestCaseUtil.money())// 价格
					.build();
				
			int count = tenantPriceStepMapper.insert(tenantPriceStep);
			log.info("count={}",count);
			log.info("tenantPriceStep={}",tenantPriceStep);
		}
		
	}
	
}
