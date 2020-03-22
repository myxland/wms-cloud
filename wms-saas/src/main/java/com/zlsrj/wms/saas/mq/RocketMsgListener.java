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
import com.zlsrj.wms.saas.strategy.tenant.TenantInsertContext;
import com.zlsrj.wms.saas.strategy.tenant.TenantInsertStrategy;

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
        
		Map<String, TenantInsertStrategy> map = tenantInsertContext.getStrategyMap();
		//map.keySet().forEach(log::info);
		
//		tenantRoleStrategy
//		tenantDepartmentStrategy
//		tenantEmployeeStrategy
		
		
        
        if(messageExt.getTopic().equals(mqConfig.getTopic())){
            String tag = messageExt.getTags() ;
            
            try {
                String key = StringUtils.firstCharToLower(tag.replaceFirst("Tag", "Strategy")); 
                log.info("tag={},key={}",tag,key);
                if(map.containsKey(key)) {
                	 map.get(key).initData(tenantInfo);
                } else {
                	log.info("key={}",key);
                	map.keySet().forEach(log::info);
                	log.info("tag {} unkonw strategy",tag);
                }
        	}
            catch (Exception e) {
    			log.error(tag+"执行initData方法出错", e);
    		}
            
//            switch (tag){
//                case "TenantDepartmentTag":
//                	
//                    
////                    for(String k:map.keySet()) {
////                    	try {
////                    		TenantInsertStrategy v = map.get(k);
////                        	v.initData(tenantInfo);
////                        	log.info(k+"执行initData方法完成");
////                    	} catch (Exception e) {
////                			log.error(k+"执行initData方法出错", e);
////                		}
////                    	
////                    }
//                    break ;
//                default:
//                    log.info("未匹配到Tag == >>"+tag);
//                    break;
//            }
        }
        // 消息消费成功
        return ConsumeOrderlyStatus.SUCCESS;
    }
}
