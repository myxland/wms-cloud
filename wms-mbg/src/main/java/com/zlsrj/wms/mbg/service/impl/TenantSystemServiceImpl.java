package com.zlsrj.wms.mbg.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantSystem;
import com.zlsrj.wms.mbg.mapper.TenantSystemMapper;
import com.zlsrj.wms.mbg.service.ITenantSystemService;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;

@Service
public class TenantSystemServiceImpl extends ServiceImpl<TenantSystemMapper, TenantSystem>
		implements ITenantSystemService {

	/**
	 * 初始化系统
	 * 
	 * @param tenantId
	 * @return
	 */
	public boolean initByTenant(Long tenantId) {

		TenantSystem tenantSystem = TenantSystem.builder()//
				.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
				.tenantId(tenantId)// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysDispName(TestCaseUtil.name())// 模块显示名称
				.sysOrder(RandomUtil.randomInt(0, 1000 + 1))// 模块排序
				.sysEdition(RandomUtil.randomInt(0, 1000 + 1))// 开通版本（基础版/高级版/旗舰版）
				.sysStatus(RandomUtil.randomInt(0, 1 + 1))// 模块状态（1开通/0未开通）
				.sysPriceType(RandomUtil.randomInt(0, 1 + 1))// 价格体系（标准价格/指定价格）
				.sysOpenDate(new Date())// 开通时间
				.sysEndDate(new Date())// 到期时间
				.sysStartChargeDate(new Date())// 开始计费日期
				.sysArrears(new BigDecimal(0))// 欠费金额
				.sysOverdraft(new BigDecimal(0))// 透支额度
				.build();

		boolean success = retBool(baseMapper.insert(tenantSystem));

		return success;
	}
}
