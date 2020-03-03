package com.zlsrj.wms.mq.service;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Producer {
	private String producerGroup = "please_rename_unique_group_name";
	private DefaultMQProducer producer;

	public Producer() {
		// 示例生产者
		producer = new DefaultMQProducer(producerGroup);
		// 不开启vip通道 开通口端口会减2
		producer.setVipChannelEnabled(false);
		// 绑定name server
		producer.setNamesrvAddr(JmsConfig.NAME_SERVER);
		start();
	}

	/**
	 * 对象在使用之前必须要调用一次，只能初始化一次
	 */
	public void start() {
		try {
			this.producer.start();
			log.info("producer start success");
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}

	public DefaultMQProducer getProducer() {
		return this.producer;
	}

	/**
	 * 一般在应用上下文，使用上下文监听器，进行关闭
	 */
	public void shutdown() {
		this.producer.shutdown();
		log.info("producer shutdown success");
	}
}
