package com.zlsrj.wms.mq.service.base.async;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
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
public class ASyncProduer {

	@Test
	public void produerTest() throws Exception {
		// 实例化消息生产者producer
		DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
		// 设置NameServer的地址
		producer.setNamesrvAddr("192.168.80.133:9876");
		// 启动producer实例
		producer.start();
		producer.setRetryTimesWhenSendAsyncFailed(0);
		log.info("start");

		for (int i = 0; i < 100; i++) {
			final int index = i;
			Message msg = new Message("TopicTest", //
					"TagA", //
					//"OrderID188", //
					("ASyncProduer " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)//
			);
			// 发送一个消息到broker
			// SendCallback接收异步返回结果回调
			producer.send(msg, new SendCallback() {

				@Override
				public void onSuccess(SendResult sendResult) {
					log.info("onSuccess");
					log.info(String.format("%-10d OK %s %n", index, sendResult.getMsgId()));

				}

				@Override
				public void onException(Throwable e) {
					log.info("onException");
					log.info(String.format("%-10d Exception %s %n", index, e));

				}
			});

		}

		// 如果不再发送消息，关闭Producer实例
		producer.shutdown();
		log.info("shutdown");
	}
}
