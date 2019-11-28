package com.zlsrj.wms.api.enums;

/**
 * 默认用水分类
 *
 */
public enum WaterTypeEnum {

	DENIZEN(1, "居民"),

	WELFARE(2, "福利院"),

	SCHOOL(3, "学校"),

	PUBLIC(4, "公共用水"),

	ADMINISTRA(5, "行政事业"),

	ARMY(6, "部队"),

	MEDICAL(7, "医疗卫生"),

	ENERGY(8, "能源"),

	MINING(9, "采矿冶金"),

	MACHINE(10, "机械制造"),

	ELECTRONIC(11, "电子电气"),

	BUILDING(12, "建筑建材"),

	BIOCHEMICAL(13, "生化化工"),

	WEAVE(14, "纺织印染"),

	PAPER(15, "造纸印刷"),

	FOOD(16, "食品饮料"),

	BUSINESS(17, "商业"),

	SERVICE(18, "服务业"),

	FINANCIAL(19, "金融保险"),

	ENGINEERING(20, "工程施工"),

	AMUSEMENT(21, "娱乐业"),

	VEHICLE(22, "洗车"),

	BATH(23, "洗浴")

	;

	private Integer code;
	private String text;

	private WaterTypeEnum(Integer code, String text) {
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

	public static WaterTypeEnum getEnumByCode(Integer code) {
		for (WaterTypeEnum value : WaterTypeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}

}
