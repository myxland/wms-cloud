package com.zlsrj.wms.api.enums;

/**
 * 租户类型
 *
 */
public enum TenantTypeEnum {
	USER_COMPANY(1, "使用单位"),

	SUPPLYR_COMPANY(2, "供应单位"),

	INTERNAL_OPERATION(3, "内部运营")

	;

	private Integer code;
	private String text;

	private TenantTypeEnum(Integer code, String text) {
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

	public static TenantTypeEnum getEnumByCode(Integer code) {
		for (TenantTypeEnum value : TenantTypeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
