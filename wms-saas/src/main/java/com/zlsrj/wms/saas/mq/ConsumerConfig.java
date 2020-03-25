package com.zlsrj.wms.saas.mq;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ 消费者配置
 */
@Configuration
public class ConsumerConfig {
	@Value("${rocketmq.consumer.namesrvAddr}")
	private String namesrvAddr;
	@Value("${rocketmq.consumer.groupName}")
	private String groupName;
	@Value("${rocketmq.consumer.consumeThreadMin}")
	private int consumeThreadMin;
	@Value("${rocketmq.consumer.consumeThreadMax}")
	private int consumeThreadMax;
//	@Value("${rocketmq.consumer.topics}")
//	private String topics;
	@Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
	private int consumeMessageBatchMaxSize;
	
	
	@Resource
	private RocketMsgListener msgListener;
	
	@Resource
	private MqConfig mqConfig;
	
	@Bean
    public DefaultMQPushConsumer getRocketMQConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(msgListener);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        try {
//            String[] topicTagsArr = topics.split(";");
//            for (String topicTags : topicTagsArr) {
//                String[] topicTag = topicTags.split("~");
//                consumer.subscribe(topicTag[0],topicTag[1]);
//            }
        	
        	// 顺序消息
        	List<String> tags = new ArrayList<String>();
        	if(mqConfig!=null && mqConfig.getInsertTags()!=null && mqConfig.getInsertTags().size()>0)
        	{
        		tags.addAll(mqConfig.getInsertTags());
        	}
        	if(mqConfig!=null && mqConfig.getRemoveTags()!=null && mqConfig.getRemoveTags().size()>0)
        	{
        		tags.addAll(mqConfig.getRemoveTags());
        	}
        	
			consumer.subscribe(mqConfig.getTopic(),StringUtils.join(tags, " || "));
        	
            consumer.start();
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }
}
