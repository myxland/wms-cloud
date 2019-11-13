package com.zlsrj.wms.mbg.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zlsrj.wms.mbg.service.IIdService;

import cn.hutool.core.util.IdUtil;

@Service
public class IdServiceImpl implements IIdService {
	@Value("${id.config.workerId}")
	private Long workerId;/* 终端ID */
	@Value("${id.config.datacenterId}")
	private Long datacenterId;/* 数据中心ID */

	public Long selectId() {
		return IdUtil.createSnowflake(workerId, datacenterId).nextId();
	}
}
