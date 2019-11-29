package com.zlsrj.wms.api.enums;

public enum EmployeeEnum {
	ADMIN(1, "admin"),

	;

	private Integer code;
	private String text;

	private EmployeeEnum(Integer code, String text) {
		this.code = code;
		this.text = text;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static EmployeeEnum getEnumByCode(Integer code) {
		for (EmployeeEnum value : EmployeeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
