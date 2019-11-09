package com.zlsrj.wms.entity.fastjson;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FastJSONTest {

	@Test
	public void bean2string() {
		TenantInfo tenantInfo = TenantInfo.builder()//
				.id(TestCaseUtil.id())//
				.displayName(TestCaseUtil.name())//
				.tenantStatus(RandomUtil.randomInt(0, 1 + 1))//
				.endDate(new java.util.Date())//
				.regTime(new java.util.Date())//
				.tenantMobile(TestCaseUtil.mobile())//
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));

		String jsonString = JSON.toJSONString(tenantInfo);
		log.info(jsonString);// {"displayName":"郭鸿涛","endDate":1573263941207,"id":"1192981551143587840","regTime":1573263941207,"tenantMobile":"14755182797","tenantStatus":0}

		jsonString = JSON.toJSONString(tenantInfo, SerializerFeature.WriteMapNullValue); // 输出空值
		log.info(jsonString);// {"accountNo":null,"bankName":null,"bankNo":null,"creditNumber":null,"displayName":"郭鸿涛","endDate":1573263941207,"id":"1192981551143587840","invoiceAddress":null,"overDuefineDay":null,"overDuefineOn":null,"overDuefineRatio":null,"overDuefineTopRatio":null,"partChargeOn":null,"regTime":1573263941207,"tenantAddress":null,"tenantArea":null,"tenantCity":null,"tenantEmail":null,"tenantLinkman":null,"tenantMobile":"14755182797","tenantName":null,"tenantProvince":null,"tenantQq":null,"tenantStatus":0,"tenantTel":null,"tenantType":null,"ycdkType":null}

		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";//全局修改日期格式
		jsonString = JSON.toJSONString(tenantInfo, SerializerFeature.WriteDateUseDateFormat);
		log.info(jsonString);//{"displayName":"廖伟祺","endDate":"2019-11-09 09:48:01.497","id":"1192982139507970048","regTime":"2019-11-09 09:48:01.497","tenantMobile":"17222347470","tenantStatus":1}
	}

	@Test
	public void string2bean() {
		String jsonString = "{\"displayName\":\"赖哲瀚\",\"endDate\":1573263673828,\"id\":\"1192980429431508992\",\"regTime\":1573263673828,\"tenantMobile\":\"13066302045\",\"tenantStatus\":1}";
		log.info(jsonString);
		TenantInfo tenantInfo = JSON.parseObject(jsonString, TenantInfo.class);
		log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));
	}
}
