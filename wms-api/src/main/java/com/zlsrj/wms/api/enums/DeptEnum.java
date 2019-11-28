package com.zlsrj.wms.api.enums;

public enum DeptEnum {
	SALE_OFFICE(1, "营业所"),

	;

	private Integer code;
	private String text;

	private DeptEnum(Integer code, String text) {
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

	public static DeptEnum getEnumByCode(Integer code) {
		for (DeptEnum value : DeptEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
