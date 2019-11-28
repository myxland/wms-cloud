package com.zlsrj.wms.api.enums;

/**
 * 价格策略环境
 *
 */
public enum PricePolicyTypeEnum {

	FREE(0, "免费"),

	USAGE(1, "按量付费"),

	FIXED(2, "固定价格")

	;

	private Integer code;
	private String text;

	private PricePolicyTypeEnum(Integer code, String text) {
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

	public static PricePolicyTypeEnum getEnumByCode(Integer code) {
		for (PricePolicyTypeEnum value : PricePolicyTypeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
