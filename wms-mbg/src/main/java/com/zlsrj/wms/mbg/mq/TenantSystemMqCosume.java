package com.zlsrj.wms.mbg.mq;

import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class TenantSystemMqCosume {

//	@Autowired
//	private ITenantSystemService tenantSystemService;
//
//	@JmsListener(destination = "${mq.mbg.tenant.system}")
//	public void receive(TextMessage textMessage) throws JMSException {
////		log.info("TenantSystemMqCosume.receive" + "\t" + textMessage.getText());
////		String tenantIdString = textMessage.getText();
////		Long tenantId = Long.parseLong(tenantIdString);
////		tenantSystemService.initByTenant(tenantId);
//
//		if (textMessage != null) {
//			String text = textMessage.getText();
//			log.info(text);
//			if (JSONUtil.isJson(text)) {
//				TenantInfo tenantInfo = JSONUtil.toBean(text, TenantInfo.class);
//				tenantSystemService.initByTenant(tenantInfo.getId(), tenantInfo.getTenantType());
//			}
//		}
//	}
}
