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
				"module_price",//
				"module_menu",//
				"tenant_role",//
				"tenant_department",//
				"tenant_employee",//
				"tenant_water_area",//
				"tenant_price_item",//
				"tenant_price_type",//
				"tenant_water_type",//
				"tenant_manufactor",//
				"tenant_caliber",//
				"tenant_meter_status",//
				"tenant_customer_meter_install",//
				"tenant_booklet",//
				"tenant_customer",//
				"tenant_meter",//
				"tenant_customer_type",//
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
