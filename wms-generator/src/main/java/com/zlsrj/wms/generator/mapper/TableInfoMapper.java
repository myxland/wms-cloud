package com.zlsrj.wms.generator.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlsrj.wms.generator.entity.TableInfo;

public interface TableInfoMapper extends BaseMapper<TableInfo> {
	List<TableInfo> selectTableInfoList(String schema);

	TableInfo selectTableInfoByTableName(Map<String, Object> map);
}
