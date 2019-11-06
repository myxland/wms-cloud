package com.zlsrj.wms.mbg.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantBill;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBillMapperTest {

	@Resource
	private TenantBillMapper tenantBillMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantBill tenantBill = tenantBillMapper.selectById(id);
		log.info(tenantBill.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantBill> queryWrapper = new QueryWrapper<TenantBill>();
		List<TenantBill> tenantBillList = tenantBillMapper.selectList(queryWrapper);
		tenantBillList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantBill tenantBill = TenantBill.builder()//
				.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.billPrintType(RandomUtil.randomInt(0,1+1))// 用户发票开具方式（按实收开票/按应收开票）
				.billRemark(RandomUtil.randomString(4))// 发票备注定义
				.billService(RandomUtil.randomString(4))// 电子发票服务商（百望/航天信息）
				.billJrdm(RandomUtil.randomString(4))// 接入代码
				.billQmcs(RandomUtil.randomString(4))// 签名值参数
				.billSkpbh(RandomUtil.randomString(4))// 税控盘编号
				.billSkpkl(RandomUtil.randomString(4))// 税控盘口令
				.billKeypwd(RandomUtil.randomString(4))// 税务数字证书密码
				.build();
				
		int count = tenantBillMapper.insert(tenantBill);
		log.info("count={}",count);
		log.info("tenantBill={}",tenantBill);
	}
	
}
