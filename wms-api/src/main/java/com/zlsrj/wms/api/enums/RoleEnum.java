package com.zlsrj.wms.api.enums;

public enum RoleEnum {
	SYSTEM_ADMIN(1, "系统管理员"),

	;

	private Integer code;
	private String text;

	private RoleEnum(Integer code, String text) {
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

	public static RoleEnum getEnumByCode(Integer code) {
		for (RoleEnum value : RoleEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
