package com.zlsrj.wms.saas.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix ="code-config")
@Data
public class CodeConfig {
	private String tenantCode;//租户编号，4位（不补0）
	private String bookCode;//表册编号，4位租户号（不补0）+4位序号号（补0）
	private String customerCode;//用户编号，4位租户号（不补0）+7位序号号（补0）
	private String meterCode;//水表编号，4位租户号（不补0）+7位序号号（补0）
}
