package com.zlsrj.wms.api.enums;

/**
 * 默认价格类别分类
 *
 */
public enum PriceTypeEnum {

	DENIZEN(1, "居民用水"),

	NO_DENIZEN(2, "非居民用水"),

	SPECIAL(3, "特种行业用水")

	;

	private Integer code;
	private String text;

	private PriceTypeEnum(Integer code, String text) {
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

	public static PriceTypeEnum getEnumByCode(Integer code) {
		for (PriceTypeEnum value : PriceTypeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}

}
