package com.zlsrj.wms.saas.rest;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api(value = "RocketMQ", tags = { "RocketMQ操作接口" })
@RestController
@Slf4j
public class RocketMQRestController {
	
	
//	@Autowired
//	private DefaultMQProducer defaultMQProducer;
//	@Autowired
//	private ParamConfigService paramConfigService ;
//
//	@ApiOperation(value = "RocketMQ发送消息")
//	@RequestMapping(value = "/rocketmqs/send/{msg}", method = RequestMethod.GET)
//	public Object send(@PathVariable("msg") String msg) throws Exception{
//		log.info("msg={}",msg);
//		Message message = new Message(paramConfigService.wmsSaasTopic, paramConfigService.tenantInfoTag, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
//		// 发送
//		SendResult sendResult = defaultMQProducer.send(message);
//		log.info("sendResult={}", sendResult);
//		return sendResult.getMsgId();
//	}

}
