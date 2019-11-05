package com.zlsrj.wms.mbg.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.mbg.entity.SystemDesign;
import com.zlsrj.wms.mbg.entity.TenantSystem;
import com.zlsrj.wms.mbg.enums.PricePolicyTypeEnum;
import com.zlsrj.wms.mbg.mapper.TenantSystemMapper;
import com.zlsrj.wms.mbg.service.ISystemDesignService;
import com.zlsrj.wms.mbg.service.ITenantSystemService;

import cn.hutool.core.util.IdUtil;

@Service
public class TenantSystemServiceImpl extends ServiceImpl<TenantSystemMapper, TenantSystem>
		implements ITenantSystemService {

	@Autowired
	private ISystemDesignService systemDesignService;

	/**
	 * 初始化系统
	 * 
	 * @param tenantId
	 * @return
	 */
	public boolean initByTenant(Long tenantId, Integer tenantType) {
		boolean success = false;

		QueryWrapper<SystemDesign> queryWrapperSystemDesign = new QueryWrapper<SystemDesign>();
		queryWrapperSystemDesign.lambda().eq(SystemDesign::getOpenTenantType, tenantType);

		List<SystemDesign> systemDesignList = systemDesignService.list(queryWrapperSystemDesign);

		if (systemDesignList != null && systemDesignList.size() > 0) {
			
			systemDesignList.forEach(s -> {
				TenantSystem tenantSystem = TenantSystem.builder()//
						.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
						.tenantId(tenantId)// 租户编号
						.sysId(s.getId())// 模块编号
						.sysDispName(s.getModuleName())// 模块显示名称
						.sysOrder(1)// 模块排序
						.sysEdition(s.getBasicOn()>0?1:null)// 开通版本（基础版/高级版/旗舰版）
						.sysStatus(s.getModuleReleaseOn())// 模块状态（1开通/0未开通）
						.sysPriceType(s.getPricePolicyType()==PricePolicyTypeEnum.FREE.getCode()?1:2)// 价格体系（标准价格/指定价格）
						.sysOpenDate(new Date())// 开通时间
						.sysEndDate(new Date())// 到期时间
						.sysStartChargeDate(new Date())// 开始计费日期
						.sysArrears(new BigDecimal(0))// 欠费金额
						.sysOverdraft(new BigDecimal(0))// 透支额度
						.build();

				baseMapper.insert(tenantSystem);
			});
		}

		return success;
	}
}
