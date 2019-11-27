package com.zlsrj.wms.tenant.service.impl;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantSms;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.tenant.mapper.TenantSmsMapper;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantSmsService;

@Service
public class TenantSmsServiceImpl extends ServiceImpl<TenantSmsMapper, TenantSms> implements ITenantSmsService {
	@Resource
	private IIdService idService;

	public boolean saveByTenantInfo(TenantInfo tenantInfo) {
		TenantSms tenantSms = TenantSms.builder()//
				.id(idService.selectId())// 编号ID
				.tenantId(tenantInfo.getId())// 租户编号
				.smsSignature(null)// 短信签名
				.smsSpService(null)// 短信SP服务商
				.smsReadSendOn(null)// 是否启用抄表账单通知短信（1：启用；0：不启用）
				.smsChargeSendOn(null)// 是否启用缴费成功通知短信（1：启用；0：不启用）
				.smsQfSendOn(null)// 是否启用欠费通知短信（1：启用；0：不启用）
				.smsQfSendAfterDays(null)// 欠费通知短信发送间隔天数
				.build();

		return super.save(tenantSms);
	}
	
}
