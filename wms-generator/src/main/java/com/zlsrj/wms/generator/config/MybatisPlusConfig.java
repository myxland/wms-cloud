package com.zlsrj.wms.generator.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;

@Configuration
@MapperScan("com.zlsrj.wms.generator.mapper")
public class MybatisPlusConfig {
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		// 开启 count 的 join 优化,只针对 left join !!!
		return new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true));
	}
}
