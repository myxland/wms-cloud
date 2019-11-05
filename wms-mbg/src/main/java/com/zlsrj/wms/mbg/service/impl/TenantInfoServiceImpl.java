package com.zlsrj.wms.mbg.service.impl;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.mbg.entity.TenantInfo;
import com.zlsrj.wms.mbg.mapper.TenantInfoMapper;
import com.zlsrj.wms.mbg.service.ITenantAccountService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantSmsService;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements ITenantInfoService {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

//	@Autowired
//	@Qualifier("queueCustType")
//	private Queue queueCustType;
//
//	@Autowired
//	@Qualifier("queueWaterType")
//	private Queue queueWaterType;

//	@Autowired
//	@Qualifier("queueSystem")
//	private Queue queueSystem;

//	@Autowired
//	@Qualifier("queueDept")
//	private Queue queueDept;
//
//	@Autowired
//	@Qualifier("queueEmployee")
//	private Queue queueEmployee;
//
//	@Autowired
//	@Qualifier("queueRole")
//	private Queue queueRole;

	@Autowired
	@Qualifier("queuePrice")
	private Queue queuePrice;

	@Autowired
	@Qualifier("queueRbac")
	private Queue queueRbac;

	@Autowired
	@Qualifier("queueBase")
	private Queue queueBase;

	@Autowired
	private ITenantAccountService tenantAccountService;
	@Autowired
	private ITenantSmsService tenantSmsService;

	/*
	 * 覆盖以前的save方法 增加逻辑 1，租户新增，初始化用户类型 2，租户新增，初始化用水类型
	 */
	@Override
	public boolean save(TenantInfo tenantInfo) {
		Long id = tenantInfo.getId();
		boolean success = retBool(baseMapper.insert(tenantInfo));

		// 1,初始化账户信息
		tenantAccountService.initByTenant(id);
		// 2,初始化短信配置信息
		tenantSmsService.initByTenant(id);

//		if (TenantTypeEnum.USER_COMPANY.getCode().equals(tenantInfo.getTenantType())) {
//			// 使用单位数据初始化逻辑
//			
//
//		}

		// 3,开通系统
//		try {
//			log.info("id=[{}]", id);
//			jmsMessagingTemplate.convertAndSend(queueSystem, JSONUtil.toJsonStr(tenantInfo));
//		} catch (Exception e) {
//			log.error("send message error", e);
//		}

//		// 发送消息
//		// 1,用户类型
//		try {
//			log.info("id=[{}]", id);
//			jmsMessagingTemplate.convertAndSend(queueCustType, Long.toString(id));
//		} catch (Exception e) {
//			log.error("send message error", e);
//		}
//
//		// 2,用水类型
//		try {
//			log.info("id=[{}]", id);
//			jmsMessagingTemplate.convertAndSend(queueWaterType, Long.toString(id));
//		} catch (Exception e) {
//			log.error("send message error", e);
//		}

//					// 4,开通部门
//					try {
//						log.info("id=[{}]", id);
//						jmsMessagingTemplate.convertAndSend(queueDept, JSONUtil.toJsonStr(tenantInfo));
//					} catch (Exception e) {
//						log.error("send message error", e);
//					}
		//
//					// 4,创建员工
//					try {
//						log.info("id=[{}]", id);
//						jmsMessagingTemplate.convertAndSend(queueEmployee, JSONUtil.toJsonStr(tenantInfo));
//					} catch (Exception e) {
//						log.error("send message error", e);
//					}
		//
//					// 5,创建角色
//					try {
//						log.info("id=[{}]", id);
//						jmsMessagingTemplate.convertAndSend(queueRole, JSONUtil.toJsonStr(tenantInfo));
//					} catch (Exception e) {
//						log.error("send message error", e);
//					}
		// 5,基础数据
		try {
			jmsMessagingTemplate.convertAndSend(queueBase, JSONUtil.toJsonStr(tenantInfo));
		} catch (Exception e) {
			log.error("send message error", e);
		}

		// 5,水费价格方案
		try {
			jmsMessagingTemplate.convertAndSend(queuePrice, JSONUtil.toJsonStr(tenantInfo));
		} catch (Exception e) {
			log.error("send message error", e);
		}

		// 6,RBAC 基于角色的权限访问控制（Role-Based Access Control）
		try {
			jmsMessagingTemplate.convertAndSend(queueRbac, JSONUtil.toJsonStr(tenantInfo));
		} catch (Exception e) {
			log.error("send message error", e);
		}

		return success;
	}
}
