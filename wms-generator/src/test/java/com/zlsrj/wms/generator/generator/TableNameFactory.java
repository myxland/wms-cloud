package com.zlsrj.wms.generator.generator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class TableNameFactory {
	public static String build() {
		List<String> list = Arrays.asList(//
				"tenant_info", //
				"tenant_consumption_bill", //
				"module_info", //
				"module_price", //
				"module_menu", //
				"tenant_role", //
				"tenant_department", //
				"tenant_employee", //
				"tenant_water_area", //
				"tenant_price_item", //
				"tenant_price_type", //
				"tenant_price_step", //
				"tenant_water_type", //
				"tenant_manufactor", //
				"tenant_caliber", //
				"tenant_meter_status", //
				"tenant_customer_meter_install", //
				"tenant_booklet", //
				"tenant_meter", //
				"tenant_customer_type", //
				"tenant_meter_read_log_current", //
				"tenant_config_sms", //
				"tenant_customer_meter_change_log", //
				"tenant_receivable", //
				"tenant_receivable_detail", //
				"tenant_payment", //
				"tenant_payment_detail", //
				"tenant_customer_linkman", //
				"tenant_customer", //
				"tenant_receivable", //
				"tenant_meter", //
				"tenant_customer_meter_change_log", //
				"tenant_payment", //
				"tenant_meter_read_log_current", //
				"", //
				"tenant_config_sms", //
				"tenant_config_wx", //
				"", //
				"tenant_customer_type", //
				"tenant_meter_industry", //
				"tenant_meter_supply_area", //
				"tenant_meter_marketing_area", //
				"tenant_meter_read_situation", //
				"tenant_meter_model", //
				"tenant_meter_type", //
				"tenant_meter_caliber", //
				"tenant_meter_brand", //
				"tenant_price_item", //
				"tenant_price_rule", //
				"tenant_price", //
				"tenant_price_detail", //
				"tenant_price_step", //
				"", //
				"", //
				"tenant_business_rules", //
				"tenant_charge_agency", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"", //
				"");
		
		List<String> filterList = list.stream().filter(s->StringUtils.isNotEmpty(s)).collect(Collectors.toList());
		
		
		return filterList.get(filterList.size()-1);
	}
	
//	public static void main(String[] args) {
//		System.out.println(build() );
//	}
}
