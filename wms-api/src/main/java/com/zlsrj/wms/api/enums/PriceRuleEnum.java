package com.zlsrj.wms.api.enums;

/**
 * 计费规则
 */
public enum PriceRuleEnum {
	

	PRICE(1, "固定单价"),

	AMOUNT (2, "按次数固定金额"),

	MONTH(3, "按间隔月数固定金额"),

	STEP_MONTH(4, "阶梯月结"),
	
	STEP_YEAR(4, "阶梯年结")

	;

	private Integer code;
	private String text;

	private PriceRuleEnum(Integer code, String text) {
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

	public static PriceRuleEnum getEnumByCode(Integer code) {
		for (PriceRuleEnum value : PriceRuleEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
