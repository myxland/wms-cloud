package com.zlsrj.wms.saas.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.SystemDictionaries;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ISystemDictionariesServiceTest {

	@Autowired
	private ISystemDictionariesService systemDictionariesService;

	@Test
	public void insertTest() {
		SystemDictionaries systemDictionaries = SystemDictionaries.builder()//
				.id(TestCaseUtil.id())// 字典ID
				.dictCode(RandomUtil.randomString(4))// 字典编码
				.dictType(RandomUtil.randomString(4))// 字典类型
				.dictName(TestCaseUtil.name())// 名称
				.dictData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(systemDictionaries, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = systemDictionariesService.save(systemDictionaries);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		SystemDictionaries systemDictionaries = SystemDictionaries.builder()//
				.dictCode(RandomUtil.randomString(4))// 字典编码
				.dictType(RandomUtil.randomString(4))// 字典类型
				.dictName(TestCaseUtil.name())// 名称
				.dictData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		systemDictionaries.setId(id);

		log.info(ToStringBuilder.reflectionToString(systemDictionaries, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = systemDictionariesService.updateById(systemDictionaries);

		log.info(Boolean.toString(success));
	}
}
