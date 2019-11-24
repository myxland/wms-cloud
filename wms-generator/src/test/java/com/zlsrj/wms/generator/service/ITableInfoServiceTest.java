package com.zlsrj.wms.generator.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.generator.entity.TableInfo;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITableInfoServiceTest {

	@Autowired
	private ITableInfoService tableInfoService;

	@Test
	public void selectTableInfoListTest() {
		String schema = "wop";
		List<TableInfo> tableInfoList = tableInfoService.selectTableInfoList(schema);
		tableInfoList.forEach(tableInfo -> {
			log.info(ToStringBuilder.reflectionToString(tableInfo, ToStringStyle.MULTI_LINE_STYLE));
		});

	}

	@Test
	public void selectTableInfoByTableName() {
		String schema = "wop";
		String name = "t_op_tenant_info";

		TableInfo tableInfo = tableInfoService.selectTableInfoByTableName(schema, name);

		log.info(ToStringBuilder.reflectionToString(tableInfo, ToStringStyle.MULTI_LINE_STYLE));

	}
}
