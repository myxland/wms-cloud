package com.zlsrj.wms.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
import com.zlsrj.wms.api.dto.TenantRoleQueryParam;
import com.zlsrj.wms.api.dto.TenantRoleUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantRoleControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createTest() throws Exception {
		TenantRoleAddParam tenantRoleAddParam = new TenantRoleAddParam();
		tenantRoleAddParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");
		tenantRoleAddParam.setRoleName("测试角色"+"-"+RandomUtil.randomNumbers(4));
		tenantRoleAddParam.setRoleRemark(RandomUtil.randomString(4));
		tenantRoleAddParam.setCreateType(2);
		
		List<Map<String,String>> roleMenuList = new ArrayList<Map<String,String>>();
		Map<String,String> roleMenu = new HashMap<String,String>();
		roleMenu.put("id", "788B57296EC0465280EEB7121170CB13");//报表3
		roleMenuList.add(roleMenu);
		roleMenu = new HashMap<String,String>();
		roleMenu.put("id", "448E5AD61DAB4A228DE804EC7C9D1795");//报表4
		roleMenuList.add(roleMenu);
		
		List<Map<String,String>> employeeRoleList = new ArrayList<Map<String,String>>();
		Map<String,String> employeeRole = new HashMap<String,String>();
		employeeRole.put("id", "0b3d4d47604b47c093f21993c794d349");//彭烨霖
		employeeRoleList.add(employeeRole);
		employeeRole = new HashMap<String,String>();
		employeeRole.put("id", "1317e12e730e424f94622510db7a0b5e");//张健雄
		employeeRoleList.add(employeeRole);
		
		tenantRoleAddParam.setModuleMenuList(roleMenuList);
		tenantRoleAddParam.setTenantEmployeeList(employeeRoleList);
		
		log.info(JSON.toJSONString(tenantRoleAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantRole/create")//
						.content(JSON.toJSONString(tenantRoleAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "84B95609CA9A4883928EAE040890376A";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantRole/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "AE6492EB900A4CEAB9C6E2DB3E03C344";
		
		TenantRoleQueryParam tenantRoleQueryParam = new TenantRoleQueryParam();
		tenantRoleQueryParam.setTenantId(tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantRole/list")//
						.content(JSON.toJSONString(tenantRoleQueryParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void initTest() throws Exception {
		String tenantId = "AE6492EB900A4CEAB9C6E2DB3E03C344";
		
		TenantRoleQueryParam tenantRoleQueryParam = new TenantRoleQueryParam();
		tenantRoleQueryParam.setTenantId(tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantRole/init")//
						.content(JSON.toJSONString(tenantRoleQueryParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "9eeccb6362f543e0955a5aec14be34ff";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantRole/"+id)//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "2dac03560d404b7987c282ea8b01e6bc";//测试角色-6714
		log.info("id={}",id);
		
		TenantRoleUpdateParam tenantRoleUpdateParam = new TenantRoleUpdateParam();
		tenantRoleUpdateParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");
		tenantRoleUpdateParam.setRoleName("测试角色"+"-"+RandomUtil.randomNumbers(4));
		tenantRoleUpdateParam.setRoleRemark(RandomUtil.randomString(4));
		tenantRoleUpdateParam.setCreateType(2);
		
		List<Map<String,String>> roleMenuList = new ArrayList<Map<String,String>>();
		Map<String,String> roleMenu = new HashMap<String,String>();
		roleMenu.put("id", "788B57296EC0465280EEB7121170CB13");//报表3
		roleMenuList.add(roleMenu);
		roleMenu = new HashMap<String,String>();
		roleMenu.put("id", "448E5AD61DAB4A228DE804EC7C9D1795");//报表4
		roleMenuList.add(roleMenu);
		
		List<Map<String,String>> employeeRoleList = new ArrayList<Map<String,String>>();
		Map<String,String> employeeRole = new HashMap<String,String>();
		employeeRole.put("id", "0b3d4d47604b47c093f21993c794d349");//彭烨霖
		employeeRoleList.add(employeeRole);
		employeeRole = new HashMap<String,String>();
		employeeRole.put("id", "1317e12e730e424f94622510db7a0b5e");//张健雄
		employeeRoleList.add(employeeRole);
		
		tenantRoleUpdateParam.setModuleMenuList(roleMenuList);
		tenantRoleUpdateParam.setTenantEmployeeList(employeeRoleList);
		
		log.info(JSON.toJSONString(tenantRoleUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantRole/update/"+id)//
						.content(JSON.toJSONString(tenantRoleUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
