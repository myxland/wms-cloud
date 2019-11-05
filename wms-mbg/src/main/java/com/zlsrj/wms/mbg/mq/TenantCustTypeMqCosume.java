package com.zlsrj.wms.mbg.mq;

import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class TenantCustTypeMqCosume {

//	@Autowired
//	private ITenantCustTypeService tenantCustTypeService;
//
//	@JmsListener(destination = "${mq.mbg.tenant.custType}")
//	public void receive(TextMessage textMessage) throws JMSException {
//		log.info("TenantCustTypeMqCosume.receive" + "\t" + textMessage.getText());
//		String tenantIdString = textMessage.getText();
//		Long tenantId = Long.parseLong(tenantIdString);
//		tenantCustTypeService.initByTenant(tenantId);
//	}
}
