package com.zlsrj.wms.mbg.mq;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.mbg.service.ITenantWaterTypeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantWaterTypeMqCosume {

	@Autowired
	private ITenantWaterTypeService tenantWaterTypeService;

	@JmsListener(destination = "${mq.mbg.tenant.waterType}")
	public void receive(TextMessage textMessage) throws JMSException {
		log.info("TenantWaterTypeMqCosume.receive" + "\t" + textMessage.getText());
		String tenantIdString = textMessage.getText();
		Long tenantId = Long.parseLong(tenantIdString);
		tenantWaterTypeService.initByTenant(tenantId);
	}
}
