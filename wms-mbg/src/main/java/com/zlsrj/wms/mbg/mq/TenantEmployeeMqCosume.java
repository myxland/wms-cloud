package com.zlsrj.wms.mbg.mq;

import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class TenantEmployeeMqCosume {

//	@Autowired
//	private ITenantEmployeeService tenantEmployeeService;
//
//	@JmsListener(destination = "${mq.mbg.tenant.employee}")
//	public void receive(TextMessage textMessage) throws JMSException {
//		if (textMessage != null) {
//			String text = textMessage.getText();
//			log.info(text);
//			if (JSONUtil.isJson(text)) {
//				TenantInfo tenantInfo = JSONUtil.toBean(text, TenantInfo.class);
//
//				log.info("TenantEmployeeMqCosume.receive" + "\t" + tenantInfo.toString());
//				tenantEmployeeService.initByTenant(tenantInfo);
//			}
//		}
//
//	}
}
