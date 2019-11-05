package com.zlsrj.wms.mbg.mq;

import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class TenantDeptMqCosume {

//	@Autowired
//	private ITenantDeptService tenantDeptService;
//
//	@JmsListener(destination = "${mq.mbg.tenant.dept}")
//	public void receive(TextMessage textMessage) throws JMSException {
//		if (textMessage != null) {
//			String text = textMessage.getText();
//			log.info(text);
//			if (JSONUtil.isJson(text)) {
//				TenantInfo tenantInfo = JSONUtil.toBean(text, TenantInfo.class);
//
//				log.info("TenantDeptMqCosume.receive" + "\t" + tenantInfo.toString());
//				Long tenantId = tenantInfo.getId();
//				tenantDeptService.initByTenant(tenantId);
//			}
//		}
//
//	}
}
