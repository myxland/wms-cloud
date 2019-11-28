package com.zlsrj.wms.api.enums;

/**
 * 默认用户分类
 *
 */
public enum CustTypeEnum {

	DENIZEN(1, "居民"),

	ADMINISTRA(2, "行政事业"),

	INDUSTRY(3, "工业"),

	BUSINESS(4, "经营服务"),

	SPECIAL(5, "特种行业")

	;

	private Integer code;
	private String text;

	private CustTypeEnum(Integer code, String text) {
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

	public static CustTypeEnum getEnumByCode(Integer code) {
		for (CustTypeEnum value : CustTypeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}

}
