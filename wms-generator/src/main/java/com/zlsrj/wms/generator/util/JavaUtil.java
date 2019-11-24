package com.zlsrj.wms.generator.util;

import java.util.ArrayList;
import java.util.List;

import com.zlsrj.wms.generator.entity.PropertyOption;
import com.zlsrj.wms.generator.enums.MySQLDataTypeEnum;

import cn.hutool.crypto.SecureUtil;

public class JavaUtil {

	/**
	 * @desc trans_info --> TransInfo
	 */
	public static String tableName2JavaName(String tableName) {
		StringBuffer stringBuffer = new StringBuffer();
		String arr[] = tableName.split("_");
		for (String s : arr) {
			stringBuffer.append(upperFirst(s));
		}

		return stringBuffer.toString();
	}
	
	/**
	 * @desc pms_brand --> Brand
	 */
	public static String tableName2JavaShortName(String tableName) {
		StringBuffer stringBuffer = new StringBuffer();
		String arr[] = tableName.split("_");
		boolean first = true;
		for (String s : arr) {
			if (first) {
				first = false;
			} else {
				stringBuffer.append(upperFirst(s));
			}

		}

		return stringBuffer.toString();
	}
	
	/**
	 * @desc pms_brand --> pms
	 */
	public static String tableName2JavaModuleName(String tableName) {
		String arr[] = tableName.split("_");
		
		return arr[0];
	}

	/**
	 * @desc trans_id --> transId
	 */
	public static String columnName2FieldName(String columnName) {
		StringBuffer stringBuffer = new StringBuffer();
		String arr[] = columnName.split("_");
		for (String s : arr) {
			stringBuffer.append(upperFirst(s));
		}

		return lowerFirst(stringBuffer.toString()); // 第一个字母小写
	}
	
	/**
	 * @desc 状态：0->禁用；1->启用 --> 状态
	 */
	public static String columnComment2FieldComment(String columnComment) {
		String fieldComment = columnComment;
		String arr[] = columnComment.split(":|：");
		if(arr.length>1) {
			fieldComment = arr[0];
		}

		return fieldComment; 
	}
	
	public static String columnComment2FieldIsSelect(String columnComment) {
		String fieldIsSelect = "N";
		String arr[] = columnComment.split(":|：");
		if(arr.length>1) {
			fieldIsSelect = "Y";
		}

		return fieldIsSelect; 
	}
	
	public static boolean columnComment2CanSelect(String columnComment) {
		boolean isCanSelect = false;
		String arr[] = columnComment.split(":|：");
		if(arr.length>1) {
			isCanSelect = true;
		}

		return isCanSelect; 
	}
	
	/**
	 * @desc 状态：0->禁用；1->启用 --> 状态
	 */
	public static List<PropertyOption> columnComment2FieldOptionList(String columnComment) {
		List<PropertyOption> freemarkerOptionList = new ArrayList<PropertyOption>();
		String arr[] = columnComment.split(":|：");// 英文冒号
		if (arr.length > 1) {
			String optionsText = arr[1];
			String options[] = optionsText.split(";|；");// 英文分号
			if (options.length > 0) {
				for (String optionText : options) {
					String[] option = optionText.split("->");//英文横线，括号
					if(option.length == 2) {
						PropertyOption freemarkerOption = new PropertyOption();
						freemarkerOption.setValue(option[0]);
						freemarkerOption.setText(option[1]);
						freemarkerOptionList.add(freemarkerOption);
					}
					
				}
			}
		}

		return freemarkerOptionList;
	}

	/**
	 * @desc varchar --> String
	 */
	public static String columnType2fieldType(String columnType) {
		return MySQLDataTypeEnum.getEnumByCode(columnType).getJavaType();
	}
	
	public static String columnType2fieldJdbcType(String columnType) {
		return MySQLDataTypeEnum.getEnumByCode(columnType).getJdbcType();
	}

	public static String lowerFirst(String str) {
		char[] chars = str.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}

	public static String upperFirst(String str) {
		char[] chars = str.toCharArray();
		chars[0] -= 32;
		return String.valueOf(chars);
	}
	
	public static long createSerialVersionUID(String javaName) {
		StringBuilder stringBuilder = new StringBuilder();
		String hex = SecureUtil.md5(javaName); // MD5加密，生成16进制MD5字符串
		if (hex.startsWith("0")) {
			stringBuilder.append("1");// 兼容第一个数字为0的情况
		}
		if (hex.startsWith("9")) {
			stringBuilder.append("1");// 兼容第一个数字为9有可能超过long最大值9223372036854775807情况 
		}
		for (char c : hex.toCharArray()) {
			stringBuilder.append(Integer.valueOf(String.valueOf(c), 16));// 16进制字符串转10进制字符串
		}

		return Long.parseLong(stringBuilder.substring(0, 19));// 截取19位长度字符串;
	}
	
	public static long createSerialVersionUIDOld(String javaName) {
		
		String hex = byteArrToHex(javaName.getBytes());
		String serialVersionUID = hex.replaceAll("[A-F]", "");
		StringBuilder builder = new StringBuilder();
		for(int i=1;i<=9;i++) {
			builder.append(i);
			builder.append(serialVersionUID);
		}
		
		serialVersionUID = builder.toString();
		serialVersionUID = serialVersionUID.substring(0, 19);
		
		return Long.parseLong(serialVersionUID);
		
		//return (Long.parseLong("1"+serialVersionUID)*987654321987654321L)%(8671725352857400986L);
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String byteArrToHex(byte... bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	public static void main(String[] args) {
		
		String s = "状态:0->禁用；1->启用";
		
		System.out.println(columnComment2FieldComment(s));
		System.out.println(columnComment2FieldIsSelect(s));
		System.out.println(columnComment2FieldOptionList(s));
	}
	
}
