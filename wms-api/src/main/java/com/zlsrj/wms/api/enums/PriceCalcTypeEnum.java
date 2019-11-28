package com.zlsrj.wms.api.enums;

/**
 * 默认费用计算方法分类
 *
 */
public enum PriceCalcTypeEnum {
	FIXED_RATE(1, "固定单价"),

	FIXED_MONEY(2, "固定金额"),

	STEP(3, "阶梯价格")

	;

	private Integer code;
	private String text;

	private PriceCalcTypeEnum(Integer code, String text) {
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

	public static PriceCalcTypeEnum getEnumByCode(Integer code) {
		for (PriceCalcTypeEnum value : PriceCalcTypeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
