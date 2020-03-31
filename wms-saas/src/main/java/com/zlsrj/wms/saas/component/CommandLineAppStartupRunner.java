package com.zlsrj.wms.saas.component;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.config.CodeConfig;
import com.zlsrj.wms.saas.service.ITenantBookService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandLineAppStartupRunner implements CommandLineRunner {
	@Resource
	private RedisService<String,Object> redisService;
	@Resource
	private ITenantInfoService tenantInfoService;
	@Resource
	private ITenantBookService tenantBookService;
	@Resource
	private CodeConfig codeConfig;
	
	@Override
	public void run(String... args) throws Exception {
//		boolean hasKey = redisService.hasKey(codeConfig.getTenantCode());
//		if (hasKey == false) {
//			// 不存在则取数据库最大值
//			int max = tenantInfoService.getMaxTenantCode();
//			redisService.setValue(codeConfig.getTenantCode(), Integer.toString(max));
//			log.info("Set max tenant no {} from db to redis", max);
//		} else {
//			log.info("Redis exist tenant code seq");
//		}
		int max = tenantInfoService.getMaxTenantCode();
		boolean success = redisService.setIfAbsent(codeConfig.getTenantCode(), Integer.toString(max));
		log.info(success?"tenant code init from db,max="+Integer.toString(max):"tenant code init from redis");
		
		//初始化表册编号
		List<TenantBook> tenantBookList = tenantBookService.getMaxBookCode();
		List<TenantInfo> teantInfoList = tenantInfoService.list();
		if (teantInfoList != null && teantInfoList.size() > 0) {
			for (TenantInfo tenantInfo : teantInfoList) {
				String id = tenantInfo.getId();
				Integer tenantCode = tenantInfo.getTenantCode();
				
				Optional<TenantBook> tenantBookOptional=tenantBookList//
						.stream()//
						.filter(b->id.equals(b.getTenantId()))//
						.findFirst()//
						;
				
				Long initBookCode = Long.parseLong(Integer.toString(tenantInfo.getTenantCode())+StringUtils.leftPad("1", 4, '0'));
				
				if(tenantBookOptional.isPresent()) {
					TenantBook tenantBook = tenantBookOptional.get();
					if(StringUtils.isNumeric(tenantBook.getBookCode())) {
						try {
							initBookCode = Long.parseLong(tenantBook.getBookCode());
						} catch(Exception e) {
							log.info("数据库表册编号转数字失败，bookCode={}",tenantBook.getBookCode());
						}
						
					}
				}
				
				redisService.setIfAbsent(codeConfig.getBookCode()+tenantCode, Long.toString(initBookCode));
				
			}
		}
	}
}
