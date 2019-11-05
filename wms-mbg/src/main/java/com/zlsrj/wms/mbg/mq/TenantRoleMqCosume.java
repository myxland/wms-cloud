package com.zlsrj.wms.mbg.mq;

import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class TenantRoleMqCosume {

//	@Autowired
//	private ITenantRoleService tenantRoleService;
//
//	@JmsListener(destination = "${mq.mbg.tenant.role}")
//	public void receive(TextMessage textMessage) throws JMSException {
//		if (textMessage != null) {
//			String text = textMessage.getText();
//			log.info(text);
//			if (JSONUtil.isJson(text)) {
//				TenantInfo tenantInfo = JSONUtil.toBean(text, TenantInfo.class);
//
//				log.info("TenantRoleMqCosume.receive" + "\t" + tenantInfo.toString());
//				tenantRoleService.initByTenant(tenantInfo);
//			}
//		}
//
//	}
}
