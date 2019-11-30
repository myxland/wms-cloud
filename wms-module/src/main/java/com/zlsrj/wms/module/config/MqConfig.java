package com.zlsrj.wms.module.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class MqConfig {

	@Value("${mq.mbg.tenant.module}")
	private String MQ_MBG_TENANT_MODULE;

	@Bean("queueModule")
	public Queue getQueueRbac() {
		return new ActiveMQQueue(MQ_MBG_TENANT_MODULE);
	}
}
