package com.zlsrj.wms.generator.generator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class TableNameFactory {
	public static String build() {
		List<String> list = Arrays.asList(
				"tenant_info",//
				"tenant_consumption_bill",//
				"module_info",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				"",//
				""
				);
		
		List<String> filterList = list.stream().filter(s->StringUtils.isNotEmpty(s)).collect(Collectors.toList());
		
		
		return filterList.get(filterList.size()-1);
	}
	
//	public static void main(String[] args) {
//		System.out.println(build() );
//	}
}
