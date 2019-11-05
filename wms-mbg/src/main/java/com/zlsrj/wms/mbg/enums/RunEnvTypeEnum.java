package com.zlsrj.wms.mbg.enums;

/**
 * 运行环境
 *
 */
public enum RunEnvTypeEnum {
	PC(1, "PC"),

	APP(2, "移动端")

	;

	private Integer code;
	private String text;

	private RunEnvTypeEnum(Integer code, String text) {
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

	public static RunEnvTypeEnum getEnumByCode(Integer code) {
		for (RunEnvTypeEnum value : RunEnvTypeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}
}
