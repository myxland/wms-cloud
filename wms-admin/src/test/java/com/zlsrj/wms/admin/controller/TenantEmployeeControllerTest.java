package com.zlsrj.wms.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantEmployeeBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantEmployeePasswordUpdateParam;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
import com.zlsrj.wms.api.dto.TenantRoleUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantEmployeeControllerTest {
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
		for(int i=0;i<4;i++) {
			TenantEmployeeAddParam tenantEmployeeAddParam = new TenantEmployeeAddParam();
			tenantEmployeeAddParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");
			tenantEmployeeAddParam.setEmployeeName("密码用例"+"-"+TestCaseUtil.name());
			tenantEmployeeAddParam.setEmployeeDepartmentId("EBC639F193694D868C7679F723E72E30");
			tenantEmployeeAddParam.setEmployeeLoginOn(1);
			tenantEmployeeAddParam.setEmployeeStatus(1);
			tenantEmployeeAddParam.setEmployeeMobile(TestCaseUtil.mobile());
			tenantEmployeeAddParam.setEmployeeEmail(TestCaseUtil.email(TestCaseUtil.name()));
			tenantEmployeeAddParam.setEmployeePersonalWx(TestCaseUtil.wx());
			tenantEmployeeAddParam.setEmployeeEnterpriceWx(TestCaseUtil.wx());
			tenantEmployeeAddParam.setEmployeeDingding(TestCaseUtil.wx());
			tenantEmployeeAddParam.setEmployeeCreateType(2);
			
			List<TenantRoleAddParam> tenantRoleAddParamList = new ArrayList<TenantRoleAddParam>();
			
			TenantRoleAddParam tenantRoleAddParam = new TenantRoleAddParam();
			tenantRoleAddParam.setId("84B95609CA9A4883928EAE040890376A");
			tenantRoleAddParamList.add(tenantRoleAddParam);
			
			tenantRoleAddParam = new TenantRoleAddParam();
			tenantRoleAddParam.setId("3ACD1ACEF49B409C8D85F425E7AA1906");
			tenantRoleAddParamList.add(tenantRoleAddParam);
			
			tenantEmployeeAddParam.setTenantRoleList(tenantRoleAddParamList);
			
			log.info(JSON.toJSONString(tenantEmployeeAddParam));
			
			String responseString = mockMvc.perform(//
					MockMvcRequestBuilders.post("/tenantEmployee/create")//
							.content(JSON.toJSONString(tenantEmployeeAddParam))//
							.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
							.accept(MediaType.APPLICATION_JSON_UTF8)//
			).andReturn().getResponse().getContentAsString();
			log.info(responseString);
		}
		
	}
	
	@Test
	public void createTestSwagger() throws Exception {
		TenantEmployeeAddParam tenantEmployeeAddParam = new TenantEmployeeAddParam();
		tenantEmployeeAddParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");
		tenantEmployeeAddParam.setEmployeeName(TestCaseUtil.name());
		tenantEmployeeAddParam.setEmployeeDepartmentId("EBC639F193694D868C7679F723E72E30");
		tenantEmployeeAddParam.setEmployeeLoginOn(1);
		tenantEmployeeAddParam.setEmployeeStatus(1);
		tenantEmployeeAddParam.setEmployeeMobile(TestCaseUtil.mobile());
		tenantEmployeeAddParam.setEmployeeEmail(TestCaseUtil.email(TestCaseUtil.name()));
		tenantEmployeeAddParam.setEmployeePersonalWx(TestCaseUtil.wx());
		tenantEmployeeAddParam.setEmployeeEnterpriceWx(TestCaseUtil.wx());
		tenantEmployeeAddParam.setEmployeeDingding(TestCaseUtil.wx());
		tenantEmployeeAddParam.setEmployeeCreateType(2);
		
		List<TenantRoleAddParam> tenantRoleAddParamList = new ArrayList<TenantRoleAddParam>();
		
		TenantRoleAddParam tenantRoleAddParam = new TenantRoleAddParam();
		tenantRoleAddParam.setId("84B95609CA9A4883928EAE040890376A");
		tenantRoleAddParamList.add(tenantRoleAddParam);
		
		tenantRoleAddParam = new TenantRoleAddParam();
		tenantRoleAddParam.setId("3ACD1ACEF49B409C8D85F425E7AA1906");
		tenantRoleAddParamList.add(tenantRoleAddParam);
		
		tenantEmployeeAddParam.setTenantRoleList(tenantRoleAddParamList);
		
		log.info(JSON.toJSONString(tenantEmployeeAddParam));
		
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "305ea801f61f4f168cf22bbe68a82c94";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantEmployee/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "1317e12e730e424f94622510db7a0b5e";
		log.info("id={}",id);
		
		TenantEmployeeUpdateParam tenantEmployeeUpdateParam = new TenantEmployeeUpdateParam();
		tenantEmployeeUpdateParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");
		//tenantEmployeeUpdateParam.setEmployeeName(TestCaseUtil.name());
		//tenantEmployeeUpdateParam.setEmployeeDepartmentId("EBC639F193694D868C7679F723E72E30");
		//tenantEmployeeUpdateParam.setEmployeeLoginOn(1);
		//tenantEmployeeUpdateParam.setEmployeeStatus(1);
		tenantEmployeeUpdateParam.setEmployeeMobile(TestCaseUtil.mobile());
		//tenantEmployeeUpdateParam.setEmployeeEmail(TestCaseUtil.email(TestCaseUtil.name()));
		//tenantEmployeeUpdateParam.setEmployeePersonalWx(TestCaseUtil.wx());
		//tenantEmployeeUpdateParam.setEmployeeEnterpriceWx(TestCaseUtil.wx());
		//tenantEmployeeUpdateParam.setEmployeeDingding(TestCaseUtil.wx());
		//tenantEmployeeUpdateParam.setEmployeeCreateType(2);
		
		List<TenantRoleUpdateParam> tenantRoleUpdateParamList = new ArrayList<TenantRoleUpdateParam>();
		
		TenantRoleUpdateParam tenantRoleUpdateParam = new TenantRoleUpdateParam();
		tenantRoleUpdateParam.setId("84B95609CA9A4883928EAE040890376A");
		tenantRoleUpdateParamList.add(tenantRoleUpdateParam);
		
		tenantRoleUpdateParam = new TenantRoleUpdateParam();
		tenantRoleUpdateParam.setId("3ACD1ACEF49B409C8D85F425E7AA1906");
		tenantRoleUpdateParamList.add(tenantRoleUpdateParam);
		
		tenantEmployeeUpdateParam.setTenantRoleList(tenantRoleUpdateParamList);
		
		log.info(JSON.toJSONString(tenantEmployeeUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantEmployee/update/"+id)//
						.content(JSON.toJSONString(tenantEmployeeUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "88c60e7a8b974cabac5c27ddcd6ccd62";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantEmployee/"+id)//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByDepartmentTest() throws Exception {
		String departmentId = "66BED12184674A59BC7319A536F18C2B";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantEmployee/list/department/"+departmentId)//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByDepartmentParentTest() throws Exception {
		String departmentParentId = "66BED12184674A59BC7319A536F18C2B";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantEmployee/list/department/parent/"+departmentParentId)//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteBatchTest() throws Exception {
		String[] ids = new String[] {"ca0392149d534246938957b2d8b42515","2f4703120ff54730bb506195ef6a1e1f"};
		log.info("ids={}",StringUtils.join(ids, ","));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantEmployee/delete/ids/"+StringUtils.join(ids, ","))//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	
	@Test
	public void updateBatchTest() throws Exception {
		String[] ids = new String[] {"749aa4bbc68c45098daabedeef380505","c6c83a7bee8c4f4f9247f2d2e8c73b3d"};
		log.info("ids={}",StringUtils.join(ids, ","));
		
		TenantEmployeeBatchUpdateParam tenantEmployeeBatchUpdateParam = new TenantEmployeeBatchUpdateParam();
		tenantEmployeeBatchUpdateParam.setEmployeeDepartmentId("1A435B888DFC4E66AD4D3B2AA7CA3250");
		
		log.info(JSON.toJSONString(tenantEmployeeBatchUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantEmployee/update/ids/"+StringUtils.join(ids, ","))//
						.content(JSON.toJSONString(tenantEmployeeBatchUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updatePassordTest() throws Exception {
		String id = "77f9c7f8a1304c83871e7f824818bd7c";
		log.info("id={}",id);
		
		TenantEmployeePasswordUpdateParam tenantEmployeePasswordUpdateParam = new TenantEmployeePasswordUpdateParam();
		tenantEmployeePasswordUpdateParam.setOldPassword("123456");
		tenantEmployeePasswordUpdateParam.setNewPassword("000000");
		tenantEmployeePasswordUpdateParam.setConfirmPassword("111111");
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantEmployee/update/password/"+id)//
						.content(JSON.toJSONString(tenantEmployeePasswordUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void resetPassordTest() throws Exception {
		String id = "77f9c7f8a1304c83871e7f824818bd7c";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantEmployee/reset/password/"+id)//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "23a60db88e184a3fa82d21dd4b0055c4";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantEmployee/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
