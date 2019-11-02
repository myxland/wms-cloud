package com.zlsrj.wms.mbg.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GeneratorTest {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		log.info(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	@Test
	public void generator() {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir"); // D:\spaceWms\wms-cloud\wms-mbg
		log.info(projectPath);
		log.info(projectPath + "/src/main/java");
		gc.setOutputDir(projectPath + "/src/main/java"); // D:\spaceWms\wms-cloud\wms-mbg
		gc.setAuthor("wms"); // 开发人员
		gc.setOpen(false); // 是否打开输出目录
		gc.setSwagger2(true); //开启 swagger2 模式
		gc.setIdType(IdType.INPUT); //该类型可以通过自己注册自动填充插件进行填充
		
		//gc.setDateType(DateType.TIME_PACK);// 默认 使用 java.time 包下的 java8 新的时间类型
		gc.setDateType(DateType.ONLY_DATE);// 只使用 java.util.date 代替 TIME_PACK不兼容druid，所以放弃

		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(url);
		// dsc.setSchemaName("public");
		dsc.setDriverName(driverClassName);
		dsc.setUsername(username);
		dsc.setPassword(password);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		// pc.setModuleName(scanner("模块名"));
		pc.setModuleName("mbg");
		pc.setParent("com.zlsrj.wms");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});

		String MAPPER_TEST_JAVA_FTL = "/templates/mapperTest.java.ftl";
		String MAPPER_TEST_JAVA_OUTPUT_DIR = "/src/test/java/com/zlsrj/wms/mbg/";

		// 测试类
		focList.add(new FileOutConfig(MAPPER_TEST_JAVA_FTL) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + MAPPER_TEST_JAVA_OUTPUT_DIR + "mapper" + "/" + tableInfo.getEntityName() + "Mapper"
						+ "Test" + StringPool.DOT_JAVA;
			}
		});

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();

		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// strategy.setSuperEntityClass("com.zlsrj.wms.mbg.entity.BaseEntity");
		strategy.setEntityLombokModel(true); // lombok支持
		strategy.setEntitySerialVersionUID(true);
		strategy.setEntityBuilderModel(true);
		strategy.setEntityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解
		// strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
		//strategy.setInclude(scanner("表名"));
		strategy.setInclude("t_op_tenant_info");
		// strategy.setSuperEntityColumns("trans_id");
		strategy.setRestControllerStyle(true); //生成 @RestController控制器
		strategy.setControllerMappingHyphenStyle(true);//驼峰转连字符
		// strategy.setTablePrefix(pc.getModuleName() + "_");
		strategy.setTablePrefix("t_op_", "t_pm_", "t_dev_", "t_");
		mpg.setStrategy(strategy);

		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}
