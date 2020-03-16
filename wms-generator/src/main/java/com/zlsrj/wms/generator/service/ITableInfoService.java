package com.zlsrj.wms.generator.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.generator.entity.TableInfo;

public interface ITableInfoService extends IService<TableInfo> {
	List<TableInfo> selectTableInfoList(String schema);

	TableInfo selectTableInfoByTableName(String schema, String name);
}
