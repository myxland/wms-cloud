package com.zlsrj.wms.saas.rest;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zlsrj.wms.saas.mq.Producer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "RocketMQ", tags = { "RocketMQ操作接口" })
@RestController
@Slf4j
public class RocketMQRestController {
	
	@Value("${rocketmq.topic}")
	private String topic;
	
	private final static String tags = "TagA";
	
	@Autowired
	private Producer producer;

	@ApiOperation(value = "RocketMQ发送消息")
	@RequestMapping(value = "/rocketmqs/send/{msg}", method = RequestMethod.GET)
	public Object send(@PathVariable("msg") String msg) throws Exception{
		log.info("msg={}",msg);
		Message message = new Message(topic, tags, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
		// 发送
		SendResult sendResult = producer.getProducer().send(message);
		log.info("sendResult={}", sendResult);
		return sendResult.getMsgId();
	}
	

}
