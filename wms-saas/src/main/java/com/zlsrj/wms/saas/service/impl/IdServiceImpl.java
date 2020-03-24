package com.zlsrj.wms.saas.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zlsrj.wms.saas.service.IIdService;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;

@Service
public class IdServiceImpl implements IIdService {
//	@Value("${id.config.workerId}")
//	private Long workerId;/* 终端ID */
//	@Value("${id.config.datacenterId}")
//	private Long datacenterId;/* 数据中心ID */

	@Override
	public String selectId() {
//		return IdUtil.createSnowflake(workerId, datacenterId).nextId();
		return IdUtil.fastSimpleUUID();
	}
	
	@Override
	public String newId(String... elements) {
		String text = StringUtils.join(elements);
		return SecureUtil.md5(text);
	}
}
