package com.zlsrj.wms.generator.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlsrj.wms.generator.entity.TableField;

public interface TableFieldMapper extends BaseMapper<TableField> {
	List<TableField> selectTableFieldList(Map<String, Object> map);
}
