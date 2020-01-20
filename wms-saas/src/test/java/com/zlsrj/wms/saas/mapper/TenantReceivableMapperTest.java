package com.zlsrj.wms.saas.mapper;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.TenantBooklet;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantDepartment;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.entity.TenantReceivable;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantReceivableMapperTest {

	@Resource
	private TenantReceivableMapper tenantReceivableMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantDepartmentMapper tenantDepartmentMapper;
	@Resource
	private TenantBookletMapper tenantBookletMapper;
	@Resource
	private TenantCustomerMapper tenantCustomerMapper;
	@Resource
	private TenantMeterMapper tenantMeterMapper;
	@Resource
	private TenantPriceTypeMapper tenantPriceTypeMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantReceivable tenantReceivable = tenantReceivableMapper.selectById(id);
		log.info(tenantReceivable.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantReceivable> queryWrapper = new QueryWrapper<TenantReceivable>();
		List<TenantReceivable> tenantReceivableList = tenantReceivableMapper.selectList(queryWrapper);
		tenantReceivableList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantDepartment tenantDepartment = null;
			List<TenantDepartment> tenantDepartmentList = tenantDepartmentMapper.selectList(new QueryWrapper<TenantDepartment>().lambda().eq(TenantDepartment::getTenantId, tenantInfo.getId()));
			if(tenantDepartmentList!=null && tenantDepartmentList.size()>0) {
				tenantDepartment = tenantDepartmentList.get(RandomUtil.randomInt(tenantDepartmentList.size()));
			}
			
			TenantBooklet tenantBooklet = null;
			List<TenantBooklet> tenantBookletList = tenantBookletMapper.selectList(new QueryWrapper<TenantBooklet>().lambda().eq(TenantBooklet::getTenantId, tenantInfo.getId()));
			if(tenantBookletList!=null && tenantBookletList.size()>0) {
				tenantBooklet = tenantBookletList.get(RandomUtil.randomInt(tenantBookletList.size()));
			}
			
			TenantCustomer tenantCustomer = null;
			List<TenantCustomer> tenantCustomerList = tenantCustomerMapper.selectList(new QueryWrapper<TenantCustomer>().lambda().eq(TenantCustomer::getTenantId, tenantInfo.getId()));
			if(tenantCustomerList!=null && tenantCustomerList.size()>0) {
				tenantCustomer = tenantCustomerList.get(RandomUtil.randomInt(tenantCustomerList.size()));
			}
			
			TenantMeter tenantMeter = null;
			if(tenantCustomer!=null) {
				List<TenantMeter> tenantMeterList = tenantMeterMapper.selectList(new QueryWrapper<TenantMeter>().lambda().eq(TenantMeter::getTenantId, tenantInfo.getId()).eq(TenantMeter::getCustomerId, tenantCustomer.getId()));
				if(tenantMeterList!=null && tenantMeterList.size()>0) {
					tenantMeter = tenantMeterList.get(RandomUtil.randomInt(tenantMeterList.size()));
				}
			}
			
			TenantPriceType tenantPriceType = null;
			List<TenantPriceType> tenantPriceTypeList = tenantPriceTypeMapper.selectList(new QueryWrapper<TenantPriceType>().lambda().eq(TenantPriceType::getTenantId, tenantInfo.getId()));
			if(tenantPriceTypeList!=null && tenantPriceTypeList.size()>0) {
				tenantPriceType = tenantPriceTypeList.get(RandomUtil.randomInt(tenantPriceTypeList.size()));
			}
			
			TenantReceivable tenantReceivable = TenantReceivable.builder()//
					.id(TestCaseUtil.id())// 应收账ID
					.tenantId(tenantInfo.getId())// 租户ID
					.receivableStatus(RandomUtil.randomInt(1,3+1))// 应收账状态（1：正常；2：被冲正；3：冲正负记录）
					.receivableType(RandomUtil.randomInt(1,3+1))// 应收类型（1：抄表；2：换表；3：追补）
					.departmentId(tenantDepartment!=null?tenantDepartment.getId():null)// 部门ID
					.bookletId(tenantBooklet!=null?tenantBooklet.getId():null)// 表册ID
					.bookletCode(tenantBooklet!=null?tenantBooklet.getBookletCode():null)// 表册代码
					.customerId(tenantCustomer!=null?tenantCustomer.getId():null)// 用户ID
					.customerCode(tenantCustomer!=null?tenantCustomer.getCustomerCode():null)// 用户代码
					.customerName(tenantCustomer!=null?tenantCustomer.getCustomerName():null)// 用户名称
					.customerAddress(tenantCustomer!=null?tenantCustomer.getCustomerAddress():null)// 用户地址
					.meterId(tenantMeter!=null?tenantMeter.getId():null)// 水表ID
					.meterCode(tenantMeter!=null?tenantMeter.getMeterCode():null)// 水表代码
					.meterAddress(tenantMeter!=null?tenantMeter.getMeterAddress():null)// 表具地址
					.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
					.receivableTime(TestCaseUtil.dateBefore())// 应收账时间
					.settleStartTime(TestCaseUtil.dateBefore())// 结算开始时间
					.settleStartPointer(new BigDecimal(RandomUtil.randomInt(1000)))// 结算开始指针
					.settleEndTime(TestCaseUtil.dateBefore())// 结算截止时间
					.settleEndPointer(new BigDecimal(RandomUtil.randomInt(1000)))// 结算截止指针
					.settleWaters(new BigDecimal(RandomUtil.randomInt(1000)))// 应结算水量
					.priceTypeId(tenantPriceType.getId())// 价格类别ID
					.receivableWaters(new BigDecimal(RandomUtil.randomInt(1000)))// 应收水量
					.receivableMoney(new BigDecimal(RandomUtil.randomInt(1000)))// 应收金额
					.arrearsMoney(new BigDecimal(RandomUtil.randomInt(1000)))// 欠费金额
					.build();
			try {
				int count = tenantReceivableMapper.insert(tenantReceivable);
				log.info("count={}", count);
				log.info("tenantReceivable={}", tenantReceivable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantReceivable> queryWrapper = new QueryWrapper<TenantReceivable>();
		queryWrapper.lambda()//
				.eq(TenantReceivable::getTenantId, 1L)//
		;
		TenantReceivable tenantReceivableAggregation = tenantReceivableMapper.selectAggregation(queryWrapper);
		
		log.info("tenantReceivableAggregation={}", tenantReceivableAggregation);
	}
	
}
