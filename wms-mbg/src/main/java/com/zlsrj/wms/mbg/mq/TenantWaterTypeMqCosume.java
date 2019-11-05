package com.zlsrj.wms.mbg.mq;

import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class TenantWaterTypeMqCosume {

//	@Autowired
//	private ITenantWaterTypeService tenantWaterTypeService;
//
//	@JmsListener(destination = "${mq.mbg.tenant.waterType}")
//	public void receive(TextMessage textMessage) throws JMSException {
//		log.info("TenantWaterTypeMqCosume.receive" + "\t" + textMessage.getText());
//		String tenantIdString = textMessage.getText();
//		Long tenantId = Long.parseLong(tenantIdString);
//		tenantWaterTypeService.initByTenant(tenantId);
//	}
}
