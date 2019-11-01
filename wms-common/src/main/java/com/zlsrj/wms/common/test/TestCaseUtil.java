package com.zlsrj.wms.common.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.javafaker.Faker;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;

public class TestCaseUtil {
	public final static String[] COMPANY_NAME_SHORT = new String[] { "春信", "贵丰", "东弘", "同富", "飞庆", "万康", "万鼎", "隆高",
			"久协", "德高", "正禄", "如通", "公盈", "春谦", "皇贵", "伟荣", "旺利", "辉圣", "广安", "合亨", "吉如", "利如", "公佳", "华飞", "元正", "瑞丰",
			"聚兴", "长福", "元优", "多乾", "巨久", "德祥", "洪仁", "乾富", "隆安", "鑫德", "乾广", "伟复", "久多", "耀顺", "同福", "东昌", "洪亚", "盛欣",
			"元优", "耀佳", "昌益", "欣丰", "乾美", "长隆", "如福", "圣耀", "洪升", "合寿", "宏禄", "中辉", "辉浩", "裕顺", "伟汇", "发富", "茂宏", "盈信",
			"宝佳", "东恒", "中久", "多德", "百耀", "欣茂", "凯源", "台盈", "祥升", "满昌", "康泰", "同富", "高生", "元晶", "优谦", "义康", "进长", "复优",
			"华成", "耀发", "贵义", "茂乾", "宝高", "优泰", "益瑞", "高福", "优旺", "谦源", "长富", "润恒", "吉乾", "仁义", "益聚", "泰贵", "鑫协", "协多",
			"隆康", "欣鼎", "源耀", "贵昌", "禄协", "圣本", "庆兴", "鑫协", "正浩", "仁益", "高晶", "如隆", "凯进", "泰公", "多成", "通发", "同满", "乾升",
			"禄宏", "伟裕", "光贵", "正飞", "聚全", "裕耀", "百亚", "乾福", "乾安", "伟捷", "春禄", "美厚", "富泰", "顺义", "益捷", "中益", "本洪", "泰润",
			"凯佳", "盈捷", "厚荣", "大福", "耀协", "润美", "鑫广", "如德", "进源", "满谦", "长公", "进正", "元康", "荣协", "久泰", "升顺", "鑫广", "如德",
			"进源", "满谦" };

	public final static String[] COMPANY_TYPE = new String[] { "半导体", "计算机", "软件", "通讯", "数字家电", "精细化工", "汽车零部件",
			"装备产业", "机床", "环保机械", "医疗仪器设备", "工程机械", "起重运输机械", "半导体设备", "轻纺机械", "生物技术", "制药", "新材料", "光电子", "纳米", "新能源",
			"环保", "食品零售", "百货零售", "汽配零售", "家电零售", "医药及医疗器械专门零售", "连锁业", "金融租赁", "典当", "投资管理", "拍卖", "融资代理", "快餐服务",
			"其他餐饮服务", "信息中介", "市场调查", "管理咨询" };

	public final static String[] COMPANY_POSTFIX = new String[] { "有限责任公司", "集团", "公司" };

	public final static String[] JOB = new String[] { "销售代表", "销售助理", "销售经理", "销售总监", "电话销售", "销售支持", "汽车销售", "医药代表",
			"医疗器械销售", "网络销售", "区域销售", "渠道专员", "渠道经理", "客户经理", "大客户经理", "团购业务员", "会籍顾问", "销售培训师", "销售数据分析", "客户代表",
			"销售业务跟单", "经销商", "客户总监", "渠道经理", "渠道总监", "区域销售总监", "区域销售经理", "业务拓展经理", "销售行政经理", "销售行政专员", "销售运营经理",
			"业务分析经理", "销售其他", "销售工程师", "分销专员", "大客户销售代表", "在线销售", "招商专员", "招商经理", "销售运营专员", "商务经理", "商务专员", "业务分析专员",
			"团购业务经理", "业务分析专员" };

	public final static String[] BANK = new String[] { "中国工商银行", "中国建设银行", "中国银行", "交通银行", "中国农业银行", "招商银行", "中国邮政储蓄银行",
			"中国光大银行", "中国民生银行", "平安银行", "浦发银行", "中信银行", "兴业银行", "华夏银行", "广发银行" };

	public final static String[] BANK_PREFIX = new String[] { "620200", "620060", "621660", "601428", "623206",
			"622580", "622150", "622660", "622620", "622298", "622516", "621771", "622909", "621222", "622568" };

	public final static int[] BANK_LENGTH = new int[] { 18, 19, 19, 17, 19, 16, 19, 16, 16, 16, 16, 16, 18, 16, 19 };

//	620200开头的银行卡是工商银行借记卡
//
//	620200开头的银行卡类型是工商银行牡丹灵通卡借记卡
//
//	620200开头的银行卡号数字长度为18位 如：620200xxxxxxxxxxxx

//	620060开头的银行卡是建设银行借记卡
//
//	620060开头的银行卡类型是建设银行银联标准卡借记卡
//
//	620060开头的银行卡号数字长度为19位 如：620060xxxxxxxxxxxxx

//	621660开头的银行卡是中国银行借记卡
//
//	621660开头的银行卡类型是中国银行联名卡借记卡
//
//	621660开头的银行卡号数字长度为19位 如：621660xxxxxxxxxxxxx

//	601428开头的银行卡是交通银行借记卡
//
//	601428开头的银行卡类型是交通银行太平洋万事顺卡借记卡
//
//	601428开头的银行卡号数字长度为17位 如：601428xxxxxxxxxxx

//	623206开头的银行卡是农业银行借记卡
//
//	623206开头的银行卡类型是农业银行中国旅游卡借记卡
//
//	623206开头的银行卡号数字长度为19位 如：623206xxxxxxxxxxxxx

//	622580开头的银行卡是招商银行借记卡
//
//	622580开头的银行卡类型是招商银行一卡通(银联卡)借记卡
//
//	622580开头的银行卡号数字长度为16位 如：622580xxxxxxxxxx

//	622150开头的银行卡是邮储银行借记卡
//
//	622150开头的银行卡类型是邮储银行绿卡银联标准卡借记卡
//
//	622150开头的银行卡号数字长度为19位 如：622150xxxxxxxxxxxxx

//	622660开头的银行卡是光大银行借记卡
//
//	622660开头的银行卡类型是光大银行阳光卡(银联卡)借记卡
//
//	622660开头的银行卡号数字长度为16位 如：622660xxxxxxxxxx

//	622620开头的银行卡是民生银行借记卡
//
//	622620开头的银行卡类型是民生银行薪资理财卡借记卡
//
//	622620开头的银行卡号数字长度为16位 如：622620xxxxxxxxxx

//	622298开头的银行卡是平安银行借记卡
//
//	622298开头的银行卡类型是平安银行万事顺借记卡借记卡
//
//	622298开头的银行卡号数字长度为16位 如：622298xxxxxxxxxx

//	622516开头的银行卡是浦东发展银行借记卡
//
//	622516开头的银行卡类型是浦东发展银行东方轻松理财卡借记卡
//
//	622516开头的银行卡号数字长度为16位 如：622516xxxxxxxxxx

//	621771开头的银行卡是中信银行借记卡
//
//	621771开头的银行卡类型是中信银行借记卡借记卡
//
//	621771开头的银行卡号数字长度为16位 如：621771xxxxxxxxxx

//	622909开头的银行卡是兴业银行借记卡
//
//	622909开头的银行卡类型是兴业银行兴业卡(银联标准卡)借记卡
//
//	622909开头的银行卡号数字长度为18位 如：622909xxxxxxxxxxxx

//	621222开头的银行卡是华夏银行借记卡
//
//	621222开头的银行卡类型是华夏银行华夏白金卡借记卡
//
//	621222开头的银行卡号数字长度为16位 如：621222xxxxxxxxxx

//	622568开头的银行卡是广发银行股份有限公司借记卡
//
//	622568开头的银行卡类型是广发银行股份有限公司广发理财通卡借记卡
//
//	622568开头的银行卡号数字长度为19位 如：622568xxxxxxxxxxxxx

	public final static String[] EMAIL_POSTFIX = new String[] { "163.com", "126.com", "yeah.net", "sina.com",
			"sohu.com", "139.com", "qq.com", "gmail.com", "hotmail.com", "21cn.com", "189.cn" };

	public final static Map<String, String> AREA = new HashMap<>();

	static {
		AREA.put("11", "北京市");
		AREA.put("12", "天津市");
		AREA.put("13", "河北省");
		AREA.put("14", "山西省");
		AREA.put("15", "内蒙古自治区");
		AREA.put("21", "辽宁省");
		AREA.put("22", "吉林省");
		AREA.put("23", "黑龙江省");
		AREA.put("31", "上海市");
		AREA.put("32", "江苏省");
		AREA.put("33", "浙江省");
		AREA.put("34", "安徽省");
		AREA.put("35", "福建省");
		AREA.put("36", "江西省");
		AREA.put("37", "山东省");
		AREA.put("41", "河南省");
		AREA.put("42", "湖北省");
		AREA.put("43", "湖南省");
		AREA.put("44", "广东省");
		AREA.put("45", "广西壮族自治区");
		AREA.put("46", "海南省");
		AREA.put("50", "重庆市");
		AREA.put("51", "四川省");
		AREA.put("52", "贵州省");
		AREA.put("53", "云南省");
		AREA.put("54", "西藏自治区");
		AREA.put("61", "陕西省");
		AREA.put("62", "甘肃省");
		AREA.put("63", "青海省");
		AREA.put("64", "宁夏回族自治区");
		AREA.put("65", "新疆维吾尔自治区");
	}

	public static String companyName() {

		String state = new Faker(Locale.CHINA).address().state();

		String city = new Faker(Locale.CHINA).address().city();
		String companyName = COMPANY_NAME_SHORT[RandomUtil.randomInt(COMPANY_NAME_SHORT.length)];
		String companyType = COMPANY_TYPE[RandomUtil.randomInt(COMPANY_TYPE.length)];
		String companyPostfix = COMPANY_POSTFIX[RandomUtil.randomInt(COMPANY_POSTFIX.length)];

		StringBuilder stringBuilder = new StringBuilder();

		switch (RandomUtil.randomInt(3)) {
		case 0:
			stringBuilder.append(state);
			stringBuilder.append(city);
			break;
		case 1:
			stringBuilder.append(state);
			break;
		case 2:
			stringBuilder.append(city);
			break;
		}

		stringBuilder.append(companyName);

		stringBuilder.append(companyType);
		stringBuilder.append(companyPostfix);

		return stringBuilder.toString();
	}

	public static String mobile() {
		return "1" + RandomUtil.randomChar("3456789") + RandomUtil.randomString(RandomUtil.BASE_NUMBER, 9);/// ^1[3456789]\d{9}$/;
	};

	public static Long id() {
		return IdUtil.createSnowflake(1L, 1L).nextId();
	}

	// 企业营业执照
	public static String licence() {
		return RandomUtil.randomString(RandomUtil.BASE_NUMBER, 15);
	}

	public static String dateBefore() {
		return DateUtil.format(DateUtil.offsetDay(DateUtil.date(), -RandomUtil.randomInt(0, 365 * 50)), "yyyy-MM-dd");
	}

	public static String dateAfter() {
		return DateUtil.format(DateUtil.offsetDay(DateUtil.date(), RandomUtil.randomInt(0, 365 * 50)), "yyyy-MM-dd");
	}

	public static String idcard() {
		int random = RandomUtil.randomInt(AREA.size());
		String area = AREA.keySet().toArray()[random].toString() + "01" + "01";
		String birthday = DateUtil.format(DateUtil.offsetDay(DateUtil.date(), -RandomUtil.randomInt(0, 365 * 50)),
				"yyyyMMdd");
		String seq = RandomUtil.randomString(RandomUtil.BASE_NUMBER, 3);
		// String valid = RandomUtil.randomString(RandomUtil.BASE_NUMBER+"X", 1);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(area);
		stringBuilder.append(birthday);
		stringBuilder.append(seq);
		stringBuilder.append(valid(stringBuilder.toString()));

		return stringBuilder.toString();
	}

	private static String valid(String front) {
		String v;

		int[] IDnums = new int[17];
		int[] coe = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 }; // 1-17位对应的系数

		String inputID = front;

		for (int i = 0; i < inputID.length(); i++) {
			IDnums[i] = inputID.charAt(i) - '0';
			// System.out.print(IDnums[i]);
		}

		int sum = getSum(IDnums, coe);
		int last = getLastNum(sum);
		if (last == 10) {
			v = "X";
		} else {
			// System.out.println(last);
			v = Integer.toString(last);
		}

		return v;
	}

	private static int getSum(int IDnums[], int coe[]) {
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			sum += IDnums[i] * coe[i];
		}
		return sum;
	}

	private static int getLastNum(int num) {
		num = num % 11;
		switch (num) {
		case 0:
			num = 1;
			break;
		case 1:
			num = 0;
			break;
		case 2:
			num = 10;
			break;
		case 3:
			num = 9;
			break;
		case 4:
			num = 8;
			break;
		case 5:
			num = 7;
			break;
		case 6:
			num = 6;
			break;
		case 7:
			num = 5;
			break;
		case 8:
			num = 4;
			break;
		case 9:
			num = 3;
			break;
		case 10:
			num = 2;
			break;
		}
		return num;
	}

	public static Object select(Object... objects) {
		if (null == objects || objects.length == 0) {
			return null;
		}

		int length = objects.length;

		return objects[RandomUtil.randomInt(length)];
	}

	public static String status(String... status) {
		if (null == status || status.length == 0) {
			return null;
		}

		int length = status.length;

		return status[RandomUtil.randomInt(length)];
	}

	public static int age() {
		int weight = RandomUtil.randomInt(100);
		if (weight < 80) {
			return RandomUtil.randomInt(18, 40);// 80% 18~40岁
		} else if (weight < 85) {
			return RandomUtil.randomInt(0, 18);// 5% 0~17岁
		} else {
			return RandomUtil.randomInt(40, 100);// 15% 41~99岁
		}
	}

	public static String job() {
		return select((Object[]) JOB).toString();
	}

	public static String bank() {
		return select((Object[]) BANK).toString();
	}

	public static String bankCardNo(String bank) {
		if (StringUtils.isEmpty(bank)) {
			bank = bank();
		}

		int index = Arrays.asList(BANK).indexOf(bank);

		String bank_prefix = BANK_PREFIX[index];
		int bank_length = BANK_LENGTH[index];

		String cardNo = StringUtils.join(bank_prefix,
				RandomUtil.randomString(RandomUtil.BASE_NUMBER, bank_length - bank_prefix.length()));

		return cardNo;
	}

	public static String name() {
		return new Faker(Locale.CHINA).name().fullName();
	}

	public static String email(String name) {
		if (StringUtils.isEmpty(name)) {
			name = name();
		}
		String pinyin = PinYinUtil.getPinYin(name);
		String postfix = select((Object[]) EMAIL_POSTFIX).toString();

		return StringUtils.join(pinyin, "@", postfix);

	}

	public static String qq() {
		String first = Integer.toString(RandomUtil.randomInt(1, 10));
		int length = RandomUtil.randomInt(6, 13);

		return StringUtils.join(first, RandomUtil.randomString(RandomUtil.BASE_NUMBER, length - 1));
	}
	
	public static String address() {
		return new Faker(Locale.CHINA).address().streetAddress();
	}


	public static void main(String[] args) {
		System.out.println("公司名：" + TestCaseUtil.companyName());
		System.out.println("手机：" + TestCaseUtil.mobile());
		System.out.println("ID：" + TestCaseUtil.id());
		System.out.println("营业执照：" + TestCaseUtil.licence());
		System.out.println("身份证：" + TestCaseUtil.idcard());

		System.out.println("之前日期：" + TestCaseUtil.dateBefore());
		System.out.println("之后日期：" + TestCaseUtil.dateAfter());

		System.out.println("状态：" + TestCaseUtil.select(1, 2, 3, 4));
		System.out.println("状态：" + TestCaseUtil.select("C", "P", "S", "F"));

		System.out.println("年龄：" + TestCaseUtil.age());
		System.out.println("职位：" + TestCaseUtil.job());
		System.out.println("银行：" + TestCaseUtil.bank());
		System.out.println("卡号：" + TestCaseUtil.bankCardNo(TestCaseUtil.bank()));
		System.out.println("姓名：" + TestCaseUtil.name());
		System.out.println("邮箱：" + TestCaseUtil.email(TestCaseUtil.name()));
		System.out.println("QQ：" + TestCaseUtil.qq());
		System.out.println("地址：" + TestCaseUtil.address());
	}
}
