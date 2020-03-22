package com.zlsrj.wms.saas.mq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ParamConfigService {
	@Value("${wms-saas.wms-saas-topic}")
	public String wmsSaasTopic;
	@Value("${wms-saas.tenant-info-tag}")
	public String tenantInfoTag;
}
