package com.zlsrj.wms.mq.service.base.sync;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SyncProduer {

	@Test
	public void produerTest() throws Exception {
		// 实例化消息生产者producer
		DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
		// 设置NameServer的地址
		producer.setNamesrvAddr("192.168.80.133:9876");
		// 启动producer实例
		producer.start();

		for (int i = 0; i < 100; i++) {
			Message msg = new Message("TopicTest", //
					"TagA", //
					("SyncProducer Test " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)//
			);
			// 发送一个消息到broker
			SendResult sendResult = producer.send(msg);

			// 通过sendResult返回消息是否成功送达
			log.info(String.format("%s%n", sendResult));
			
		}

		// 如果不再发送消息，关闭Producer实例
		producer.shutdown();
	}
}
