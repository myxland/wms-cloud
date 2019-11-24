package com.zlsrj.wms.generator.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class TableInfoMapperTest {

	@Autowired
	private TableInfoMapper tableInfoMapper;

	@Test
	public void selectTableInfoListTest() {
		String schema = "wop";
		List<TableInfo> tableInfoList = tableInfoMapper.selectTableInfoList(schema);
		tableInfoList.forEach(tableInfo->{
			log.info(ToStringBuilder.reflectionToString(tableInfo, ToStringStyle.MULTI_LINE_STYLE));
		});
		
	}
	
	@Test
	public void selectTableInfoByTableName() {
		String schema = "wop";
		String name = "t_op_tenant_info";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("schema", schema);
		map.put("name", name);
		TableInfo tableInfo = tableInfoMapper.selectTableInfoByTableName(map);
		
		log.info(ToStringBuilder.reflectionToString(tableInfo, ToStringStyle.MULTI_LINE_STYLE));
	}
	
}
