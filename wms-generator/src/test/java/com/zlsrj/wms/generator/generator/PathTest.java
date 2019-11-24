package com.zlsrj.wms.generator.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.zlsrj.wms.generator.entity.TableInfo;
import com.zlsrj.wms.generator.service.ITableInfoService;
import com.zlsrj.wms.generator.util.JavaUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.file.FileReader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PathTest {

	@Value("${avatar.schema}")
	private String schema;

	@Value("${avatar.domainName}")
	private String domainName;

	@Value("${avatar.projectName}")
	private String projectName;

	@Value("${avatar.projectNameApi}")
	private String projectNameApi;

	@Value("${avatar.projectNameWeb}")
	private String projectNameWeb;

	@Value("${avatar.projectNameVue}")
	private String projectNameVue;

	@Value("${avatar.artifactId}")
	private String artifactId;

	@Value("${avatar.artifactIdApi}")
	private String artifactIdApi;

	@Value("${avatar.artifactIdWeb}")
	private String artifactIdWeb;

	@Value("${avatar.author}")
	private String author;

	@Value("${avatar.target.dir}")
	private String targetDir;

	@Value("${avatar.target.dirVue}")
	private String targetDirVue;

	@Value("${avatar.target.dirBak}")
	private String targetDirBak;

	@Value("${avatar.template.dir}")
	private String templateDir;

	@Value("${avatar.encode}")
	private String encode;

	@Value("${avatar.tomcat.port}")
	private String tomcatPort;

	@Value("${avatar.database.ip}")
	private String databaseIp;

	@Value("${avatar.database.port}")
	private String databasePort;

	@Value("${avatar.database.schema}")
	private String databaseSchema;

	@Value("${avatar.database.username}")
	private String databaseUsername;

	@Value("${avatar.database.password}")
	private String databasePassword;

	@Autowired
	private ITableInfoService tableInfoService;

	@Test
	public void generator() {
		String tableName = TableNameFactory.build();
		String tablePrefix = "t_op_";
		tablePrefix = "t_";

		log.info(schema);
		log.info(domainName);
		log.info(projectName);
		log.info(artifactId);
		log.info(targetDir);
		log.info(targetDirVue);
		log.info(targetDirBak);
		log.info(templateDir);
		log.info(encode);
		log.info(tomcatPort);
		log.info(databaseIp);
		log.info(databasePort);
		log.info(databaseSchema);
		log.info(databaseUsername);
		log.info(databasePassword);

		log.info(tableName);

		TableInfo tableInfo = tableInfoService.selectTableInfoByTableName(schema, tableName);
		tableInfo.setTablePrefix(tablePrefix);

		log.info(ToStringBuilder.reflectionToString(tableInfo, ToStringStyle.MULTI_LINE_STYLE));

		// 载入本地配置
		try {
			String bakFileDir = targetDirBak;
			String bakFileName = tableInfo.getEntityName();
			String POSTFIX = ".json";// 生成备份文件后缀名
			String bakFilePath = bakFileDir + "/" + bakFileName + POSTFIX;
			if (new File(bakFilePath).exists()) {
				FileReader fileReader = new FileReader(bakFilePath);
				String freemarkerTableJSONString = fileReader.readString();
				TableInfo tableInfoBak = JSONObject.parseObject(freemarkerTableJSONString, tableInfo.getClass());

				String[] IGNORE_PROPERTIES = new String[] { "tableName", "tableComment", "tablePrefix", "tableShortName"//
						, "primaryKeyList", "selectColumnList"//
						, "includeBigDecimal", "includeDate", "includeStatus", "includeCompanyShortName"//
				};

				BeanUtil.copyProperties(tableInfoBak, tableInfo, IGNORE_PROPERTIES); // 忽略属性
				// columnList
				log.info("载入备份成功，文件路径={}", new File(bakFilePath).getAbsolutePath());
				// 载入本地配置
				log.info(ToStringBuilder.reflectionToString(tableInfo, ToStringStyle.MULTI_LINE_STYLE));
			}

		} catch (Exception e) {
			log.error("载入本地备份出错", e);
		}

		Map<String, Object> dataModel = new HashMap<>();
		dataModel.put("table", tableInfo);
		dataModel.put("serialVersionUID", Long.toString(JavaUtil.createSerialVersionUID(tableInfo.getEntityName())));
		dataModel.put("serialVersionUIDQueryParam",
				Long.toString(JavaUtil.createSerialVersionUID("QP" + tableInfo.getEntityName())));// 为了生成不同的serialVersionUID
		dataModel.put("serialVersionUIDVO",
				Long.toString(JavaUtil.createSerialVersionUID("VO" + tableInfo.getEntityName())));// 为了生成不同的serialVersionUID
		dataModel.put("targetDir", targetDir);
		dataModel.put("targetDirVue", targetDirVue);
		dataModel.put("targetDirBak", targetDirBak);
		dataModel.put("artifactId", artifactId);
		dataModel.put("domainName", domainName);
		dataModel.put("domainNameDir", domainName.replaceAll("\\.", "/"));
		dataModel.put("projectName", projectName);

		dataModel.put("artifactIdApi", artifactIdApi);
		dataModel.put("artifactIdWeb", artifactIdWeb);
		dataModel.put("projectNameApi", projectNameApi);
		dataModel.put("projectNameWeb", projectNameWeb);
		dataModel.put("projectNameVue", projectNameVue);

		// freemarker
		try {

			// 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
			Configuration configuration = new Configuration(Configuration.getVersion());
			// 第二步：设置模板文件所在的路径。
			configuration.setDirectoryForTemplateLoading(new File(templateDir));
			// 第三步：设置模板文件使用的字符集。一般就是utf-8.
			configuration.setDefaultEncoding(encode);

			Properties prop = new Properties();
			Template template = configuration.getTemplate("path.ftl", encode);
			StringWriter stringWriter = new StringWriter();
			BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
			template.process(dataModel, bufferedWriter);
			StringReader reader = new StringReader(stringWriter.toString());

			prop.load(reader);

			Set<String> names = prop.stringPropertyNames();
			for (String key : names) {
				String targetPath = prop.getProperty(key);
				log.info(new File(targetPath).getAbsolutePath());
			}

			log.info(
					"======================================================================================================================================");
//			String WINDOWS_SEPARATOR = "\\"; // Windows文件夹分隔符
			String WINDOWS_SEPARATOR = "\\\\"; // Windows文件夹分隔符
			String LINUX_SEPARATOR = "/"; // Linux文件夹分隔符

			for (String key : names) {
				String targetPath = prop.getProperty(key);
				log.info(new File(targetPath).getAbsolutePath().replaceAll(WINDOWS_SEPARATOR, LINUX_SEPARATOR)
						.replaceFirst("D:", "/d"));
			}

			log.info(
					"======================================================================================================================================");

			log.info(
					"======================================================================================================================================");

			for (String key : names) {
				String targetPath = prop.getProperty(key);
//				log.info("rm -rf " + new File(targetPath).getAbsolutePath()
//						.replaceAll(WINDOWS_SEPARATOR, LINUX_SEPARATOR).replaceFirst("D:", "/d"));
				System.out.println("rm -rf " + new File(targetPath).getAbsolutePath()
						.replaceAll(WINDOWS_SEPARATOR, LINUX_SEPARATOR).replaceFirst("D:", "/d"));
			}

			log.info(
					"======================================================================================================================================");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// freemarker

	}

}
