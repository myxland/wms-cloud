package com.zlsrj.wms.mbg.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class MqConfig {
//	@Value("${mq.mbg.tenant.custType}")
//	private String MQ_MBG_TENANT_CUSTTYPE;
//
//	@Value("${mq.mbg.tenant.waterType}")
//	private String MQ_MBG_TENANT_WATERTYPE;

//	@Value("${mq.mbg.tenant.system}")
//	private String MQ_MBG_TENANT_SYSTEM;

//	@Value("${mq.mbg.tenant.dept}")
//	private String MQ_MBG_TENANT_DEPT;
//
//	@Value("${mq.mbg.tenant.employee}")
//	private String MQ_MBG_TENANT_EMPLOYEE;
//
//	@Value("${mq.mbg.tenant.role}")
//	private String MQ_MBG_TENANT_ROLE;

	@Value("${mq.mbg.tenant.price}")
	private String MQ_MBG_TENANT_PRICE;

	@Value("${mq.mbg.tenant.rbac}")
	private String MQ_MBG_TENANT_RBAC;

	@Value("${mq.mbg.tenant.base}")
	private String MQ_MBG_TENANT_BASE;

//	@Bean("queueCustType")
//	public Queue getQueueCustType() {
//		return new ActiveMQQueue(MQ_MBG_TENANT_CUSTTYPE);
//	}
//
//	@Bean("queueWaterType")
//	public Queue getQueueWaterType() {
//		return new ActiveMQQueue(MQ_MBG_TENANT_WATERTYPE);
//	}

//	@Bean("queueSystem")
//	public Queue getQueueSystem() {
//		return new ActiveMQQueue(MQ_MBG_TENANT_SYSTEM);
//	}

//	@Bean("queueDept")
//	public Queue getQueueDept() {
//		return new ActiveMQQueue(MQ_MBG_TENANT_DEPT);
//	}
//
//	@Bean("queueEmployee")
//	public Queue getQueueEmployee() {
//		return new ActiveMQQueue(MQ_MBG_TENANT_EMPLOYEE);
//	}
//
//	@Bean("queueRole")
//	public Queue getQueueRole() {
//		return new ActiveMQQueue(MQ_MBG_TENANT_ROLE);
//	}

	@Bean("queuePrice")
	public Queue getQueuePrice() {
		return new ActiveMQQueue(MQ_MBG_TENANT_PRICE);
	}

	@Bean("queueRbac")
	public Queue getQueueRbac() {
		return new ActiveMQQueue(MQ_MBG_TENANT_RBAC);
	}

	@Bean("queueBase")
	public Queue getQueueBase() {
		return new ActiveMQQueue(MQ_MBG_TENANT_BASE);
	}
}
