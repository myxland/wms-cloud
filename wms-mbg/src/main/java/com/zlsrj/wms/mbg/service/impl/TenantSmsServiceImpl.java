package com.zlsrj.wms.mbg.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantSms;
import com.zlsrj.wms.mbg.mapper.TenantSmsMapper;
import com.zlsrj.wms.mbg.service.ITenantSmsService;

import cn.hutool.core.util.IdUtil;

@Service
public class TenantSmsServiceImpl extends ServiceImpl<TenantSmsMapper, TenantSms> implements ITenantSmsService {

	/**
	 * 初始化租户短信配置
	 * 
	 * @param tenantId
	 * @return
	 */
	public boolean initByTenant(Long tenantId) {
		TenantSms tenantSms = TenantSms.builder()//
				.id(IdUtil.createSnowflake(1L, 1L).nextId())// 编号ID
				.tenantId(tenantId)// 租户编号
				.smsSignature(null)// 短信签名
				.smsSpService(null)// 短信SP服务商
				.smsReadSendOn(0)// 是否启用抄表账单通知短信（启用/不启用）
				.smsChargeSendOn(0)// 是否启用缴费成功通知短信（启用/不启用）
				.smsQfSendOn(0)// 是否启用欠费通知短信（启用/不启用）
				.smsQfSendAfterDays(0)// 欠费通知短信发送间隔天数（欠费多少天后，催费多少天后仍然未缴）
				.build();
		boolean success = retBool(baseMapper.insert(tenantSms));

		return success;
	}
}
