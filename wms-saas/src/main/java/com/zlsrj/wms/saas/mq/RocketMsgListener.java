package com.zlsrj.wms.saas.mq;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.strategy.tenant.insert.TenantInsertContext;
import com.zlsrj.wms.saas.strategy.tenant.insert.TenantInsertStrategy;
import com.zlsrj.wms.saas.strategy.tenant.remove.TenantRemoveContext;
import com.zlsrj.wms.saas.strategy.tenant.remove.TenantRemoveStrategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息消费监听
 */
@Component
@Slf4j
public class RocketMsgListener implements MessageListenerOrderly {
    
    @Resource
    private TenantInsertContext tenantInsertContext;
    @Resource
    private TenantRemoveContext tenantRemoveContext;
    
    @Resource
    private MqConfig mqConfig;
	
	@Override
	public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
		if (CollectionUtils.isEmpty(list)) {
			return ConsumeOrderlyStatus.SUCCESS;
		}
        MessageExt messageExt = list.get(0);
        //log.info("接受到的消息为："+new String(messageExt.getBody(),RemotingHelper.DEFAULT_CHARSET));
        TenantInfo tenantInfo = null;
		try {
			log.info("接受到的消息为：" + new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET));
			tenantInfo = JSON.parseObject(new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET), TenantInfo.class);
		} catch (UnsupportedEncodingException e) {
			log.error(RemotingHelper.DEFAULT_CHARSET + "字体不支持", e);
		}
        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次，如果不需要再次消费，则返回成功
        if(reConsume ==3){
            return ConsumeOrderlyStatus.SUCCESS;
        }
        
		Map<String, TenantInsertStrategy> mapTenantInsertStrategy = tenantInsertContext.getStrategyMap();
		Map<String, TenantRemoveStrategy> mapTenantRemoveStrategy = tenantRemoveContext.getStrategyMap();
		
		boolean success = true;
		
        
        if(messageExt.getTopic().equals(mqConfig.getTopic())){
            String tag = messageExt.getTags() ;
            
			try {
				if (tag.endsWith("InsertTag")) {
					String key = StringUtils.firstCharToLower(tag.replaceFirst("InsertTag", "Strategy"));
					if (mapTenantInsertStrategy.containsKey(key)) {
						success = mapTenantInsertStrategy.get(key).initData(tenantInfo);
					} else {
						log.info("key={}", key);
						mapTenantRemoveStrategy.keySet().forEach(log::info);
						log.info("tag {} unkonwn strategy", tag);
					}
				} else if (tag.endsWith("RemoveTag")) {
					String key = StringUtils.firstCharToLower(tag.replaceFirst("RemoveTag", "RemoveStrategy"));
					if (mapTenantRemoveStrategy.containsKey(key)) {
						success = mapTenantRemoveStrategy.get(key).removeData(tenantInfo);
					} else {
						log.info("key={}", key);
						mapTenantRemoveStrategy.keySet().forEach(log::info);
						log.info("tag {} unkonwn strategy", tag);
					}
				} else {
					log.info("tag {} unkonwn strategy", tag);
				}

			} catch (Exception e) {
				log.error(tag + "执行方法出错", e);
			}
            
        }
        
        return success?ConsumeOrderlyStatus.SUCCESS:ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
    }
}
