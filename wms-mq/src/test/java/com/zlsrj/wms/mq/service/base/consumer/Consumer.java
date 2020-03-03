package com.zlsrj.wms.mq.service.base.consumer;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer {
	@Test
	public void consumerTest() throws Exception {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
		consumer.setNamesrvAddr("192.168.80.133:9876");
		consumer.subscribe("TopicTest", "*");
		//consumer.setMessageModel(MessageModel.CLUSTERING); //默认模式
		consumer.setMessageModel(MessageModel.BROADCASTING);
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				log.info(String.format("%s Receive New Message: %s %n", Thread.currentThread().getName(), msgs));
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		
		consumer.start();
		log.info(String.format("Consumer Started. %n"));
		
	}
}
