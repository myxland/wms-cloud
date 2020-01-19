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
import com.zlsrj.wms.api.entity.TenantPaymentDetail;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPayment;
import com.zlsrj.wms.api.entity.TenantReceivable;
import com.zlsrj.wms.api.entity.TenantReceivableDetail;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.entity.TenantPriceItem;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPaymentDetailMapperTest {

	@Resource
	private TenantPaymentDetailMapper tenantPaymentDetailMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantPaymentMapper tenantPaymentMapper;
	@Resource
	private TenantReceivableMapper tenantReceivableMapper;
	@Resource
	private TenantReceivableDetailMapper tenantReceivableDetailMapper;
	@Resource
	private TenantPriceStepMapper tenantPriceStepMapper;
	@Resource
	private TenantPriceTypeMapper tenantPriceTypeMapper;
	@Resource
	private TenantPriceItemMapper tenantPriceItemMapper;
	
	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantPaymentDetail tenantPaymentDetail = tenantPaymentDetailMapper.selectById(id);
		log.info(tenantPaymentDetail.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantPaymentDetail> queryWrapper = new QueryWrapper<TenantPaymentDetail>();
		List<TenantPaymentDetail> tenantPaymentDetailList = tenantPaymentDetailMapper.selectList(queryWrapper);
		tenantPaymentDetailList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantPayment tenantPayment = null;
			List<TenantPayment> tenantPaymentList = tenantPaymentMapper.selectList(new QueryWrapper<TenantPayment>().lambda().eq(TenantPayment::getTenantId, tenantInfo.getId()));
			if(tenantPaymentList!=null && tenantPaymentList.size()>0) {
				tenantPayment = tenantPaymentList.get(RandomUtil.randomInt(tenantPaymentList.size()));
			}
			
			TenantReceivable tenantReceivable = null;
			List<TenantReceivable> tenantReceivableList = tenantReceivableMapper.selectList(new QueryWrapper<TenantReceivable>().lambda().eq(TenantReceivable::getTenantId, tenantInfo.getId()));
			if(tenantReceivableList!=null && tenantReceivableList.size()>0) {
				tenantReceivable = tenantReceivableList.get(RandomUtil.randomInt(tenantReceivableList.size()));
			}
			
			TenantReceivableDetail tenantReceivableDetail = null;
			List<TenantReceivableDetail> tenantReceivableDetailList = tenantReceivableDetailMapper.selectList(new QueryWrapper<TenantReceivableDetail>().lambda().eq(TenantReceivableDetail::getTenantId, tenantInfo.getId()));
			if(tenantReceivableDetailList!=null && tenantReceivableDetailList.size()>0) {
				tenantReceivableDetail = tenantReceivableDetailList.get(RandomUtil.randomInt(tenantReceivableDetailList.size()));
			}
			
			TenantPriceStep tenantPriceStep = null;
			List<TenantPriceStep> tenantPriceStepList = tenantPriceStepMapper.selectList(new QueryWrapper<TenantPriceStep>().lambda().eq(TenantPriceStep::getTenantId, tenantInfo.getId()));
			if(tenantPriceStepList!=null && tenantPriceStepList.size()>0) {
				tenantPriceStep = tenantPriceStepList.get(RandomUtil.randomInt(tenantPriceStepList.size()));
			}
			
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
			
			TenantPaymentDetail tenantPaymentDetail = TenantPaymentDetail.builder()//
					.id(TestCaseUtil.id())// 实收明细账ID
					.tenantId(tenantInfo.getId())// 租户ID
					.paymentId(tenantPayment!=null?tenantPayment.getId():null)// 实收总账ID
					.receivableTime(TestCaseUtil.dateBefore())// 对应的应收账时间
					.receivableId(tenantReceivable!=null?tenantReceivable.getId():null)// 应收总账ID
					.receivableDetailId(tenantReceivableDetail!=null?tenantReceivableDetail.getId():null)// 应收明细账ID
					.stepNo(RandomUtil.randomInt(0,1000+1))// 阶梯号
					.payWaters(TestCaseUtil.water())// 收费水量
					.priceTypeId(tenantPriceType!=null?tenantPriceType.getId():null)// 价格分类ID
					.priceItemId(tenantPriceItem!=null?tenantPriceItem.getId():null)// 费用项目ID
					.payPrice(TestCaseUtil.money())// 价格
					.payMoney(TestCaseUtil.money())// 收费金额
					.build();
				
			int count = tenantPaymentDetailMapper.insert(tenantPaymentDetail);
			log.info("count={}",count);
			log.info("tenantPaymentDetail={}",tenantPaymentDetail);
		}
		
	}
	
}
