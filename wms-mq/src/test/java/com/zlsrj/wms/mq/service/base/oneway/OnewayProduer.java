package com.zlsrj.wms.mq.service.base.oneway;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OnewayProduer {

	@Test
	public void produerTest() throws Exception {
		// 实例化消息生产者producer
		DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
		// 设置NameServer的地址
		producer.setNamesrvAddr("192.168.80.133:9876");
		// 启动producer实例
		producer.start();
		log.info("start");

		for (int i = 0; i < 100; i++) {
			Message msg = new Message("TopicTest", //
					"TagA", //
					("Oneway Test " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)//
			);
			// 发送一个消息到broker
			// 发送单向消息，没有任何返回结果
			producer.sendOneway(msg);
			
		}

		// 如果不再发送消息，关闭Producer实例
		producer.shutdown();
		log.info("shutdown");
	}
}
