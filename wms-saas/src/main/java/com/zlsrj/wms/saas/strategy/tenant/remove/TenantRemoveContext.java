package com.zlsrj.wms.saas.strategy.tenant.remove;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantRemoveContext {

	private final Map<String, TenantRemoveStrategy> strategyMap = new ConcurrentHashMap<>();
	

    @Autowired
    public void stragegyInteface(Map<String, TenantRemoveStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach(this.strategyMap::put);
        //this.strategyMap.keySet().forEach(log::info);
    }


    public Map<String, TenantRemoveStrategy> getStrategyMap() {
        return strategyMap;
    }
}
