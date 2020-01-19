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
import com.zlsrj.wms.api.entity.TenantReceivableDetail;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantReceivable;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.entity.TenantPriceItem;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantReceivableDetailMapperTest {

	@Resource
	private TenantReceivableDetailMapper tenantReceivableDetailMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantReceivableMapper tenantReceivableMapper;
	@Resource
	private TenantPriceStepMapper tenantPriceStepMapper;
	@Resource
	private TenantPriceItemMapper tenantPriceItemMapper;
	
	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantReceivableDetail tenantReceivableDetail = tenantReceivableDetailMapper.selectById(id);
		log.info(tenantReceivableDetail.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantReceivableDetail> queryWrapper = new QueryWrapper<TenantReceivableDetail>();
		List<TenantReceivableDetail> tenantReceivableDetailList = tenantReceivableDetailMapper.selectList(queryWrapper);
		tenantReceivableDetailList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantReceivable tenantReceivable = null;
			List<TenantReceivable> tenantReceivableList = tenantReceivableMapper.selectList(new QueryWrapper<TenantReceivable>().lambda().eq(TenantReceivable::getTenantId, tenantInfo.getId()));
			if(tenantReceivableList!=null && tenantReceivableList.size()>0) {
				tenantReceivable = tenantReceivableList.get(RandomUtil.randomInt(tenantReceivableList.size()));
			}
			
			TenantPriceStep tenantPriceStep = null;
			List<TenantPriceStep> tenantPriceStepList = tenantPriceStepMapper.selectList(new QueryWrapper<TenantPriceStep>().lambda().eq(TenantPriceStep::getTenantId, tenantInfo.getId()));
			if(tenantPriceStepList!=null && tenantPriceStepList.size()>0) {
				tenantPriceStep = tenantPriceStepList.get(RandomUtil.randomInt(tenantPriceStepList.size()));
			}
			
			TenantPriceItem tenantPriceItem = null;
			List<TenantPriceItem> tenantPriceItemList = tenantPriceItemMapper.selectList(new QueryWrapper<TenantPriceItem>().lambda().eq(TenantPriceItem::getTenantId, tenantInfo.getId()));
			if(tenantPriceItemList!=null && tenantPriceItemList.size()>0) {
				tenantPriceItem = tenantPriceItemList.get(RandomUtil.randomInt(tenantPriceItemList.size()));
			}
			
			TenantReceivableDetail tenantReceivableDetail = TenantReceivableDetail.builder()//
					.id(TestCaseUtil.id())// 应收明细账ID
					.tenantId(tenantInfo.getId())// 租户ID
					.receivableId(tenantReceivable!=null?tenantReceivable.getId():null)// 应收总账ID
					.priceStepId(tenantPriceStep!=null?tenantPriceStep.getId():null)// 价格阶梯ID
					.receivableWaters(TestCaseUtil.water())// 应收水量
					.arrearsWaters(TestCaseUtil.water())// 欠费水量
					.priceItemId(tenantPriceItem!=null?tenantPriceItem.getId():null)// 费用项目ID
					.detailPrice(TestCaseUtil.money())// 价格
					.receivableMoney(TestCaseUtil.money())// 应收金额
					.arrearsMoney(TestCaseUtil.money())// 欠费金额
					.build();
				
			int count = tenantReceivableDetailMapper.insert(tenantReceivableDetail);
			log.info("count={}",count);
			log.info("tenantReceivableDetail={}",tenantReceivableDetail);
		}
		
	}
	
}
