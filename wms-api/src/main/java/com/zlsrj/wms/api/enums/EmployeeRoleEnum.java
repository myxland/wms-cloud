package com.zlsrj.wms.api.enums;

public enum EmployeeRoleEnum {
	EMPLOYEE_ROLE_DEFAULT(1, "系统默认管理员"),

	;

	private Integer code;
	private String text;

	private EmployeeRoleEnum(Integer code, String text) {
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

	public static EmployeeRoleEnum getEnumByCode(Integer code) {
		for (EmployeeRoleEnum value : EmployeeRoleEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
