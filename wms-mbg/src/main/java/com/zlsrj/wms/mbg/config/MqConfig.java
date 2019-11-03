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
	@Value("${mq.mbg.tenant.custType}")
	private String MQ_MBG_TENANT_CUSTTYPE;

	@Value("${mq.mbg.tenant.waterType}")
	private String MQ_MBG_TENANT_WATERTYPE;

	@Value("${mq.mbg.tenant.system}")
	private String MQ_MBG_TENANT_SYSTEM;

	@Bean("queueCustType")
	public Queue getQueueCustType() {
		return new ActiveMQQueue(MQ_MBG_TENANT_CUSTTYPE);
	}

	@Bean("queueWaterType")
	public Queue getQueueWaterType() {
		return new ActiveMQQueue(MQ_MBG_TENANT_WATERTYPE);
	}

	@Bean("queueSystem")
	public Queue getQueueSystem() {
		return new ActiveMQQueue(MQ_MBG_TENANT_SYSTEM);
	}
}
