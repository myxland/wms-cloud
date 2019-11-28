package com.zlsrj.wms.api.enums;

/**
 * 计费周期
 */
public enum BillCycleTypeEnum {

	HOUR(0, "实时"),

	DAY(1, "按天"),

	MONTH(2, "按月"),

	YEAR(2, "按年")

	;

	private Integer code;
	private String text;

	private BillCycleTypeEnum(Integer code, String text) {
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

	public static BillCycleTypeEnum getEnumByCode(Integer code) {
		for (BillCycleTypeEnum value : BillCycleTypeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
