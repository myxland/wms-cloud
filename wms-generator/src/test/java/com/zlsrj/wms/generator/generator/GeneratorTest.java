package com.zlsrj.wms.generator.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zlsrj.wms.generator.entity.TableField;
import com.zlsrj.wms.generator.entity.TableInfo;
import com.zlsrj.wms.generator.service.ITableInfoService;
import com.zlsrj.wms.generator.util.JavaUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GeneratorTest {

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
		tablePrefix = "t_op_";

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

		// 根据规格初始化部分配置
		List<TableField> columnList = tableInfo.getColumnList();
		if (columnList != null && columnList.size() > 0) {
			for (int i = 0; i < columnList.size(); i++) {
				TableField column = columnList.get(i);
				column.setQueryable(true);
				column.setSelectable(column.isPropertySelect());// 是否是下拉列表
				if ("id".equals(column.getColumnName())) {
					column.setGridWidth(180);
				}

				if ("tenant_id".equals(column.getColumnName())) {
					column.setGridWidth(280);
				}

				// 手机号码默认宽度120
				if (column.getColumnName().endsWith("_mobile")) {
					column.setGridWidth(120);
				}

				if ("BigDecimal".equals(column.getPropertyType())) {
					column.setGridAlign("right");
				}
			}

			for (int i = 0; i < columnList.size(); i++) {
				TableField column = columnList.get(i);
				if (column.getColumnName().equals("id") == false// 非id字段
						&& column.getColumnName().endsWith("tenant_id") == false// 非tenant_id字段
						&& column.getColumnName().endsWith("parent_id") == false// 非父级字段
						&& column.isPropertySelect() == false// 非下拉列表字段
						&& column.isViewable() == false// 非Grid显示字段
				) {

					column.setGridWidthAuto(true);
					break;
				}
			}
		}

		// 根据规格初始化部分配置

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
						, "includeBigDecimal","includeLong", "includeDate", "includeStatus", "includeCompanyShortName", //
						"includeTenantId", "includeTenantOne2One", "includeTenantOne2Many", "includeSysId",
						"includeSysOne2One", "includeSysOne2Many", "includeModuleId", "includeModuleOne2One",
						"includeModuleOne2Many", "includeSingleUpdatable", "singleUpdatableColumnList",
						"includeBatchUpdatable", "batchUpdatableColumnList", "includeNotNullabe",
						"notNullabeColumnList", "includeParentId", "includeAggregation", "aggregationColumnList" };

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
		dataModel.put("serialVersionUIDAddParam",
				Long.toString(JavaUtil.createSerialVersionUID("AP" + tableInfo.getEntityName())));// 为了生成不同的serialVersionUID
		dataModel.put("serialVersionUIDUpdateParam",
				Long.toString(JavaUtil.createSerialVersionUID("UP" + tableInfo.getEntityName())));// 为了生成不同的serialVersionUID
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
				String ftl = key + ".ftl";
				// log.info(ftl);
				String targetPath = prop.getProperty(key);

				// 增加判断，如果文件已经存在，则不再生成（防止覆盖已经写好的逻辑）
				if (new File(targetPath).exists()) {
					log.info(new File(targetPath).getAbsolutePath() + " 已经存在，不再重新生成");
					continue;
				}

				try {
					targetPath = create(configuration, dataModel, ftl, targetPath);
					log.info(new File(targetPath).getAbsolutePath());
					// infoJSONObject.put(key, targetPath);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// freemarker

		// 写入本地备份
		try {
			String bakFileDir = targetDirBak;
			String bakFileName = tableInfo.getEntityName();
			String POSTFIX = ".json";// 生成备份文件后缀名
			String bakFilePath = bakFileDir + "/" + bakFileName + POSTFIX;

			if (new File(bakFilePath).exists() == false) {
				// 备份文件不存在
				String jsonString = JSON.toJSONString(tableInfo//
						, SerializerFeature.PrettyFormat// 结果是否格式化,默认为false
						, SerializerFeature.WriteMapNullValue// 是否输出值为null的字段,默认为false
				);
				FileWriter writer = new FileWriter(bakFilePath);
				writer.write(jsonString);

				log.info(new File(bakFilePath).getAbsolutePath() + " 备份文件生成成功");
			} else {
				log.info(new File(bakFilePath).getAbsolutePath() + " 备份文件已经存在，不必重新生成");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 写入本地备份

	}

	private String create(Configuration configuration, Map<String, Object> dataModel, String ftl, String targetPath)
			throws Exception {

		Template template = configuration.getTemplate(ftl);
		File targetFile = new File(targetPath);
		File targetDir = targetFile.getParentFile();
		if (targetDir.exists() == false) {
			targetDir.mkdirs();
			log.info(targetDir.getAbsolutePath());
		}

		// 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
//		Writer out = new java.io.FileWriter(targetFile);
		Writer out = new java.io.BufferedWriter(
				new java.io.OutputStreamWriter(new java.io.FileOutputStream(targetFile), encode));

		// 第七步：调用模板对象的process方法输出文件。
		template.process(dataModel, out);
		// 第八步：关闭流。
		out.close();

		return new File(targetPath).getAbsolutePath();
	}
}
