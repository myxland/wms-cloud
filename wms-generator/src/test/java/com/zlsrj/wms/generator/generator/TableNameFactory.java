package com.zlsrj.wms.generator.generator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class TableNameFactory {
	public static String build() {
		List<String> list = Arrays.asList(
				"t_op_tenant_info",//
				"t_op_tenant_employee",//
				"t_op_tenant_cust_type",//
				"t_op_tenant_water_type",//
				"t_op_tenant_dept",//
				"t_op_tenant_role",//
				"t_op_tenant_system",//
				"t_op_tenant_role_system",//
				"t_op_tenant_role_menu",//
				"t_op_tenant_employee_role",//
				"t_op_tenant_price_type",//
				"t_op_tenant_price_item",//
				"t_op_tenant_price_detail",//
				"t_op_tenant_price_step",//
				"t_op_tenant_account",//
				"t_op_tenant_sms",//
				"t_op_system_design",//
				"t_op_system_menu_design",//
				"t_op_tenant_bill",//
				"t_op_tenant_info",//
				"t_op_tenant_sms",//
				"t_op_tenant_account",//
				"t_op_tenant_bill",//
				"t_op_system_design",//
				"t_op_system_menu_design",//
				"t_op_system_price",//
				"t_op_tenant_system",//
				"t_op_tenant_system_price",//
				"t_op_tenant_dept",//
				"t_op_tenant_cust_type",//
				
				"t_op_tenant_water_type",//
				"t_op_tenant_price_type",//
				"t_op_tenant_price_item",//
				"t_op_tenant_price_step",//
				"t_op_tenant_price_detail",//
				"t_op_tenant_employee",//
				"t_op_tenant_role",//
				"t_op_tenant_employee_role",//
				"t_op_tenant_role_system",//
				"t_op_tenant_role_menu",//
				"t_cust_dev",//
				"t_cust_info",//
				"t_cust_contact",//
				"t_read_booklet",//
				"t_cust_info_change",//
				"t_dev_read_curr",//
				"t_dev_read_curr_his",//
				"t_dev_rec_list",//
				"t_iot_device_design",//
				"t_op_tenant_info",//
				"t_op_tenant_account",//
				"t_op_tenant_info",//
				"t_op_tenant_config",//
				"t_op_tenant_sms",//
				"t_op_tenant_invoice",//
				"t_op_tenant_bill",//
				"t_op_tenant_cust_type",//
				"t_op_tenant_water_type",//
				"t_op_tenant_dept",//
				"t_op_tenant_employee",//
				"t_op_tenant_role",//
				"t_op_tenant_employee_role",//
				"t_op_tenant_module",//
				"t_op_module_info",//
				"t_op_module_menu",//
				"t_op_tenant_module",//
				"t_op_tenant_role_module",//
				"t_op_tenant_info",//
				"t_op_tenant_role_menu",//
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
				"tenant_info",//
				"tenant_consumption_bill",//
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
