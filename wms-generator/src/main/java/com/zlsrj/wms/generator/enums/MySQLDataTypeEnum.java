package com.zlsrj.wms.generator.enums;

public enum MySQLDataTypeEnum {

	TINYINT("tinyint", "Byte", "INTEGER"),

	SMALLINT("smallint", "Short  ", "INTEGER"),

	INT("int", "Integer", "INTEGER"),

	BIGINT("bigint", "Long", "BIGINT"),

	FLOAT("float", "Float", "FLOAT"),

	DOUBLE("double", "Double", "DOUBLE"),

	DECIMAL("decimal", "BigDecimal", "DECIMAL"),

	CHAR("char", "String", "CHAR"),

	VARCHAR("varchar", "String", "VARCHAR"),

	TINYBLOB("tinyblob", "byte []", "TINYBLOB"),

	TINYTEXT("tinytext", "String", "TINYTEXT"),

	BLOB("blob", "byte []", "BLOB"),

	TEXT("text", "String", "LONGVARCHAR"),

	MEDIUMBLOB("mediumblob", "byte []", "BLOB"),

	MEDIUMTEXT("mediumtext", "String", "LONGVARCHAR"),

	LONGBLOB("longblob", "byte []", "BLOB"),

	LONGTEXT("longtext", "String", "LONGVARCHAR"),

	VARBINARY("varbinary", "byte []", "VARBINARY"),

	DATE("date", "Date", "DATE"),

	TIME("time", "Time", "TIMESTAMP"),

	YEAR("year", "Date", "YEAR"),

	DATETIME("datetime", "Date", "TIMESTAMP"),

	TIMESTAMP("timestamp", "Date", "TIMESTAMP"),

	;
	private String dataType;
	private String javaType;
	private String jdbcType;

	private MySQLDataTypeEnum(String dataType, String javaType, String jdbcType) {
		this.dataType = dataType;
		this.javaType = javaType;
		this.jdbcType = jdbcType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public static MySQLDataTypeEnum getEnumByCode(String code) {
		for (MySQLDataTypeEnum value : MySQLDataTypeEnum.values()) {
			if (value.getDataType().equals(code)) {
				return value;
			}
		}
		return null;
	}
}
