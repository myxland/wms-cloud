package com.zlsrj.wms.mq.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zlsrj.wms.mq.service.JmsConfig;
import com.zlsrj.wms.mq.service.Producer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MQController {
	@Autowired
	private Producer producer;

	private List<String> mesList;

	/**
	 * 初始化消息
	 */
	public MQController() {
		mesList = new ArrayList<>();
		mesList.add("小小");
		mesList.add("爸爸");
		mesList.add("妈妈");
		mesList.add("爷爷");
		mesList.add("奶奶");
	}

	@RequestMapping("/mq/send")
	public Object send() throws Exception {
		// 总共发送五次消息
		int i = 0;
		for (String s : mesList) {
			// 创建生产信息
			Message message = new Message(JmsConfig.TOPIC, "TagA", ("小小一家人的称谓:" + s).getBytes());
			// 发送
//			SendResult sendResult = producer.getProducer().send(message);
//			log.info("输出生产者信息={}", sendResult);

//			Message msg = new Message("TopicTest", //
//					"TagA", //
//					//"OrderID188", //
//					("ASyncProduer " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)//
//			);
			// 发送一个消息到broker
			// SendCallback接收异步返回结果回调
			final int index = i++;
			producer.getProducer().send(message, new SendCallback() {

				@Override
				public void onSuccess(SendResult sendResult) {
					log.info("onSuccess");
					log.info(String.format("%-10d OK %s %n", index, sendResult));

				}

				@Override
				public void onException(Throwable e) {
					log.info("onException");
					log.info(String.format("%-10d Exception %s %n", index, e));

				}
			});
		}
		return "成功";
	}
}
