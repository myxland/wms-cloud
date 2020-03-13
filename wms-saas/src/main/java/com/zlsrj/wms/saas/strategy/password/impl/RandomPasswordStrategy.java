package com.zlsrj.wms.saas.strategy.password.impl;

import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.saas.strategy.password.PasswordStrategy;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RandomPasswordStrategy implements PasswordStrategy {

	/* 
	 * 密码随机6位数字,MD5加密
	 */
	@Override
	public String getPassword(TenantEmployee tenantEmployee) {
		String plainText = "";
		plainText = RandomUtil.randomNumbers(6);
		log.info("use random number {}",plainText);
		return SecureUtil.md5(plainText);
	}

}
