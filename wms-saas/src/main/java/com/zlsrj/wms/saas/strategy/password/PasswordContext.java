package com.zlsrj.wms.saas.strategy.password;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PasswordContext {

	private final Map<String, PasswordStrategy> strategyMap = new ConcurrentHashMap<>();
	

    @Autowired
    public void stragegyInteface(Map<String, PasswordStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach(this.strategyMap::put);
        //System.out.println(this.strategyMap);
    }


    public PasswordStrategy getInstance(String mode) {
        //Preconditions.checkArgument(!StringUtils.isEmpty(mode), "不允许输入空字符串");
    	//如果mode为空，我们则提供默认模式 defaultPasswordStrategy
    	if(StringUtils.isEmpty(mode)) {
    		mode = "defaultPasswordStrategy";
    		log.info("mode is null, use default password strategy");
    	}
    	
    	log.info("system config password strategy mode is {}", mode);
    	
        return this.strategyMap.get(mode);
    }
}
