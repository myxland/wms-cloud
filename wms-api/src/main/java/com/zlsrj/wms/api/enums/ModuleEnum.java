package com.zlsrj.wms.api.enums;

/**
 * 默认用水分类
 *
 */
public enum ModuleEnum {

	MY_CONTROL_PANEL(1, "我的控制台"),

	;

	private Integer code;
	private String text;

	private ModuleEnum(Integer code, String text) {
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

	public static ModuleEnum getEnumByCode(Integer code) {
		for (ModuleEnum value : ModuleEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}

}
