package com.zlsrj.wms.generator.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.generator.entity.TableField;
import com.zlsrj.wms.generator.entity.TableInfo;
import com.zlsrj.wms.generator.mapper.TableFieldMapper;
import com.zlsrj.wms.generator.mapper.TableInfoMapper;
import com.zlsrj.wms.generator.service.ITableInfoService;

@Service
public class TableInfoServiceImpl extends ServiceImpl<TableInfoMapper, TableInfo> implements ITableInfoService {

	@Autowired
	private TableFieldMapper tableFieldMapper;

	@Override
	public List<TableInfo> selectTableInfoList(String schema) {
		return baseMapper.selectTableInfoList(schema);
	}

	@Override
	public TableInfo selectTableInfoByTableName(String schema, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("schema", schema);
		map.put("name", name);

		TableInfo tableInfo = baseMapper.selectTableInfoByTableName(map);

		if (tableInfo != null) {
			List<TableField> tableFieldList = tableFieldMapper.selectTableFieldList(map);
			tableInfo.setColumnList(tableFieldList);
		}

		return tableInfo;
	}

}
