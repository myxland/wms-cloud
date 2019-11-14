package com.zlsrj.wms.system.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

// 	http://localhost:8080/swagger-ui.html

	@Bean
	public Docket api() {
//	return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
//			.paths(PathSelectors.any()).build();

		ParameterBuilder parameterBuilder = new ParameterBuilder();
		List<Parameter> parameterList = new ArrayList<Parameter>();
		parameterBuilder.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		parameterList.add(parameterBuilder.build());

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().globalOperationParameters(parameterList);

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().build();
	}
}
