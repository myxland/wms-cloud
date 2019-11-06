package com.zlsrj.wms.mbg.mq;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.mbg.service.ITenantRbacService;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantRbacMqCosume {

	@Autowired
	private ITenantRbacService tenantRbacService;

	@JmsListener(destination = "${mq.mbg.tenant.rbac}")
	public void receive(TextMessage textMessage) throws JMSException {
		if (textMessage != null) {
			String text = textMessage.getText();
			log.info(text);
			if (JSONUtil.isJson(text)) {
				TenantInfo tenantInfo = JSONUtil.toBean(text, TenantInfo.class);

				log.info("TenantRbacMqCosume.receive" + "\t" + tenantInfo.toString());
				tenantRbacService.initByTenant(tenantInfo);
			}
		}

	}
}
