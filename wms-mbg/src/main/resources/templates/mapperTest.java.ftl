package ${package.Mapper};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.javafaker.Faker;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ${entity}MapperTest {

	@Resource
	private ${entity}Mapper ${entity?uncap_first}Mapper;

	@Test
	public void selectByIdTest() {
		${entity} ${entity?uncap_first} = ${entity?uncap_first}Mapper.selectById(10L);
		log.info(${entity?uncap_first}.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<${entity}> queryWrapper = new QueryWrapper<${entity}>();
		List<${entity}> ${entity?uncap_first}List = ${entity?uncap_first}Mapper.selectList(queryWrapper);
		${entity?uncap_first}List.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		${entity} ${entity?uncap_first} = ${entity}.builder()//
		<#list table.fields as field>
		<#if field.name == "id">
				.${field.capitalName?uncap_first}(IdUtil.createSnowflake(1L, 1L).nextId())//${field.comment}
		<#elseif field.columnType == "INTEGER">
				.${field.capitalName?uncap_first}(RandomUtil.randomInt(10))//${field.comment}
		<#elseif field.type == "datetime" || field.type == "date">
				.${field.capitalName?uncap_first}(new Date())//${field.comment}
		<#else>
				.${field.capitalName?uncap_first}(RandomUtil.randomString(4))//${field.comment} ${field.name} ${field.type} ${field.propertyName} ${field.columnType} ${field.comment} ${field.propertyType} ${field.capitalName}
        </#if>		
		</#list>
				.build();
		
		int count = ${entity?uncap_first}Mapper.insert(${entity?uncap_first});
		log.info("count={}",count);
		log.info("${entity?uncap_first}={}",${entity?uncap_first});
	}
	
}
