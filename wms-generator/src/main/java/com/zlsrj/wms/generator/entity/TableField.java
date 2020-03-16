package com.zlsrj.wms.generator.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TableField implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3038540581842993804L;
	private String columnName;
	private String isNullable;/* NO YES */
	private String dataType;/*
							 * varchar bigint longtext datetime int tinyint decimal double char timestamp
							 * set enum float longblob mediumtext mediumblob smallint text blob time date
							 * varbinary
							 */
	private int characterMaximumLength;/* 以字符为单位的最大长度 */
	private int characterOctetLength;/* 以字节为单位的最大长度 */
	private int numericPrecision;
	private int numericScale;
	private String columnKey;/* PRI UNI MUL */
	private String extra;/* auto_increment */
	private String columnComment;

	private String propertyComment;/* 属性备注 */
	private boolean propertySelect;/* 是否是下拉列表属性值 */
	private List<PropertyOption> propertyOptionList = new ArrayList<PropertyOption>();

	private boolean queryable = false;/* 是否可作为查询字段 */
	private boolean likeable = false;/* 是否可作为模糊查询字段 */
	private boolean addable = true;/* 是否可作为增加字段 */
	private boolean updatable = true;/* 是否可作为更新字段 */
	private boolean selectable = propertySelect;/* 是否可作为下拉列表字段 */
	private boolean addableOrUpdatable = true;/* 是否可作为增加或者编辑字段 */

	private String defaultAddValue = null;/* 新增时默认值 */
	private String defaultUpdateValue = null;/* 更新时默认值 */

	private boolean singleUpdatable = false;/* 是否能单独更新，比如上架、下架 */
	private boolean batchUpdatable = false;/* 是否能批量更新，比如状态 */

	private boolean viewable = true;/* 是否Grid显示字段 */
	private boolean detailable = true;/* 是否明细显示字段 */

	private boolean gridWidthAuto = false;/* Grid宽度自由调整 */
	private int gridWidth = 100;/* Grid宽度值 */
	private String gridAlign = "left";/* Grid对齐方式，left,center,right */

	private String refEntityName;/*id字段引用表 TenantDepartment*/
	//private String refEntityId;/*id字段引用表ID id*/
	//private String refEntityText;/*id字段引用表名称 departmentName*/
	/**
	 * @return aaa_bbb_cc -> aaaBbbCc 数据库列名转java驼峰属性值
	 */
	public String getPropertyName() {
		StringBuilder builder = new StringBuilder();
		Arrays.asList(columnName.split("_")).forEach(c -> {
			builder.append(c.substring(0, 1).toUpperCase()); // 首字母大写
			if (c.length() > 1) {
				builder.append(c.substring(1).toLowerCase());// 其余字母小写
			}
		});

		String tmp = builder.toString();
		String result = tmp.substring(0, 1).toLowerCase();
		if (tmp.length() > 1) {
			result = tmp.substring(0, 1).toLowerCase() + tmp.substring(1);
		}

		return result;
	}

	public String getPropertyType() {
		String propertyType = "String";
		/*
		 * varchar longtext char set enum mediumtext text
		 * 
		 */
		if (dataType.equals("int") || dataType.equals("tinyint") || dataType.equals("smallint")) {
			propertyType = "Integer";
		} else if (dataType.equals("bigint")) {
			propertyType = "Long";
		} else if (dataType.equals("decimal")) {
			propertyType = "BigDecimal";
		} else if (dataType.equals("double")) {
			propertyType = "Double";
		} else if (dataType.equals("float")) {
			propertyType = "Float";
		} else if (dataType.equals("datetime") || dataType.equals("timestamp") || dataType.equals("time")
				|| dataType.equals("date")) {
			propertyType = "Date";
		} else if (dataType.equals("longblob") || dataType.equals("mediumblob") || dataType.equals("blob")
				|| dataType.equals("varbinary")) {
			propertyType = "byte []";
		}

		return propertyType;
	}

	public String getPropertyComment() {
		if (StringUtils.isEmpty(propertyComment)) {
			propertyComment = columnComment;
			String regEx = "（.*）";
			Pattern pat = Pattern.compile(regEx);
			Matcher mat = pat.matcher(columnComment);
			if (mat.find()) {
				propertyComment = columnComment.replaceAll(regEx, "");
			}
		}

		return propertyComment;
	}

	public boolean isPropertySelect() {
		if (propertySelect == false) {
			// 数据类型为int且注释中包含中文的括号（）
			if (dataType.equals("int")) {
				String regEx = "（.*）";
				Pattern pat = Pattern.compile(regEx);
				Matcher mat = pat.matcher(columnComment);
				if (mat.find()) {
					propertySelect = true;
				}
			}
		}

		return propertySelect;
	}

	public List<PropertyOption> getPropertyOptionList() {
		// propertyOptionList = new ArrayList<PropertyOption>();
		if (propertyOptionList == null || propertyOptionList.size() == 0) {
			if (isPropertySelect()) {// 确认是可以下拉选项列表
				String regEx = "（.*）";
				Pattern pat = Pattern.compile(regEx);
				Matcher mat = pat.matcher(columnComment);
				if (mat.find()) {
					String listString = mat.group(0);
					listString = StrUtil.removePrefix(listString, "（");
					listString = StrUtil.removeSuffix(listString, "）");

					if (listString.indexOf("；") > 0) {// 以中文；分隔情况
						String[] arr = listString.split("；");
						if (listString.indexOf("：") > 0) {// 键值以中文：分隔情况
							Arrays.asList(arr).forEach(
									s -> propertyOptionList.add(new PropertyOption(s.split("：")[0], s.split("：")[1])));
						} else {
							for (int i = 1; i <= arr.length; i++) {// 键值对只有值，则以数字为键，从1开始
								propertyOptionList.add(new PropertyOption(Integer.toString(i), arr[i - 1]));
							}
						}

					} else if (listString.indexOf("/") > 0) {// 以中文/分隔情况
						String[] arr = listString.split("/");
						if (listString.indexOf("：") > 0) {// 键值以中文：分隔情况
							Arrays.asList(arr).forEach(
									s -> propertyOptionList.add(new PropertyOption(s.split("：")[0], s.split("：")[1])));
						} else {
							for (int i = 1; i <= arr.length; i++) {// 键值对只有值，则以数字为键，从1开始
								propertyOptionList.add(new PropertyOption(Integer.toString(i), arr[i - 1]));
							}
						}
					}

				}
			}
		}

		return propertyOptionList;
	}

	public boolean isYnSelectable() {
		if (isPropertySelect() && getPropertyOptionList() != null) {
			if (propertyOptionList != null && propertyOptionList.size() > 0) {
				if (propertyOptionList.size() == 2) {
					if ("1".equals(propertyOptionList.get(0).getValue())
							&& "0".equals(propertyOptionList.get(1).getValue())) {
						return true;
					}
				}
			}
		}
		return false;
	}

}