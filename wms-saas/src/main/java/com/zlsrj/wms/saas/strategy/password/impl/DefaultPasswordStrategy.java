package com.zlsrj.wms.saas.strategy.password.impl;

import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.saas.strategy.password.PasswordStrategy;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultPasswordStrategy implements PasswordStrategy {

	/* 
	 * 密码默认加密方式为手机号码后6位,MD5加密
	 */
	@Override
	public String getPassword(TenantEmployee tenantEmployee) {
		String plainText = "";
		if(tenantEmployee!=null && tenantEmployee.getEmployeeMobile().trim().length()>0) {
			int length = tenantEmployee.getEmployeeMobile().length();
			plainText = tenantEmployee.getEmployeeMobile().substring(length-6);
			
			log.info("mobile is {},last 6 is {}",tenantEmployee.getEmployeeMobile(),plainText);
		}else {
			//如果手机号码未填写，则随机6位数字
			plainText = RandomUtil.randomNumbers(6);
			log.info("unkown mobile,use random number {}",plainText);
		}
		
		return SecureUtil.md5(plainText);
	}

}
