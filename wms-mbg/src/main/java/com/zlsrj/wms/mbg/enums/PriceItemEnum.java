package com.zlsrj.wms.mbg.enums;

/**
 * 默认费用项目分类
 *
 */
public enum PriceItemEnum {

	WATER(1, "水费", 0.03f),

	RESOURCE(2, "水资源费", 0f),

	SEWAGE(3, "污水处理费", 0f)

	;

	private Integer code;
	private String text;
	private float rate;/* 默认税率 */

	private PriceItemEnum(Integer code, String text, float rate) {
		this.code = code;
		this.text = text;
		this.rate = rate;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
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

	public static PriceItemEnum getEnumByCode(Integer code) {
		for (PriceItemEnum value : PriceItemEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}

}
