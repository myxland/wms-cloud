package com.zlsrj.wms.mbg.service.impl;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.mbg.entity.TenantInfo;
import com.zlsrj.wms.mbg.mapper.TenantInfoMapper;
import com.zlsrj.wms.mbg.service.ITenantInfoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements ITenantInfoService {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	@Qualifier("queueCustType")
	private Queue queueCustType;

	@Autowired
	@Qualifier("queueWaterType")
	private Queue queueWaterType;

	@Autowired
	@Qualifier("queueSystem")
	private Queue queueSystem;

	/*
	 * 覆盖以前的save方法 增加逻辑 1，租户新增，初始化用户类型 2，租户新增，初始化用水类型
	 */
	@Override
	public boolean save(TenantInfo tenantInfo) {
		Long id = tenantInfo.getId();
		boolean success = retBool(baseMapper.insert(tenantInfo));

		// 发送消息
		// 1,用户类型
		try {
			log.info("id=[{}]", id);
			jmsMessagingTemplate.convertAndSend(queueCustType, Long.toString(id));
		} catch (Exception e) {
			log.error("send message error", e);
		}

		// 2,用水类型
		try {
			log.info("id=[{}]", id);
			jmsMessagingTemplate.convertAndSend(queueWaterType, Long.toString(id));
		} catch (Exception e) {
			log.error("send message error", e);
		}

		// 3,开通系统
		try {
			log.info("id=[{}]", id);
			jmsMessagingTemplate.convertAndSend(queueSystem, Long.toString(id));
		} catch (Exception e) {
			log.error("send message error", e);
		}

		return success;
	}
}
