package com.nico.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.nico")
public class WebConfig {

	// config vistas

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/vista/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}

	// config bbdd y pool de conexiones

	@Bean
	public ComboPooledDataSource dataSourcePool() throws PropertyVetoException {
		ComboPooledDataSource pool = new ComboPooledDataSource();
		pool.setDriverClass("com.mysql.cj.jdbc.Driver");
		pool.setJdbcUrl("jdbc:mysql://localhost:3306/gestion_pedidos");
		pool.setUser("root");
		pool.setPassword("manager1");
		pool.setMinPoolSize(5);
		pool.setMaxPoolSize(20);
		pool.setMaxIdleTime(30000);
		return pool;
	}

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() throws PropertyVetoException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSourcePool());
		sessionFactory.setPackagesToScan("com.nico.entidad");
		sessionFactory.setHibernateProperties(hibernateProps());
		return sessionFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws PropertyVetoException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(localSessionFactoryBean().getObject());
		return transactionManager;
	}

	private Properties hibernateProps() {
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.show_sql", "true");
		return props;
	}

}
