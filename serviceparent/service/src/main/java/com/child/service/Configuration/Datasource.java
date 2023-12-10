package com.child.service.Configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class Datasource {
    @Value("${spring.datasource.driver-class-name}")
    private String dataSourceClassName;

    @Value("${spring.datasource.jdbc-url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUserName;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${spring.datasource.hibernate.dialect}")
    private String hibernateDialect;



//    @Bean(name="entityManagerFactory")
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        try {
            LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            localContainerEntityManagerFactoryBean.setDataSource(dataSource());
            localContainerEntityManagerFactoryBean.setPackagesToScan("com.child.service.entity");
            localContainerEntityManagerFactoryBean.setPersistenceUnitName("myntra");
            HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

            jpaVendorAdapter.setDatabasePlatform(hibernateDialect);
            localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
            localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
            return localContainerEntityManagerFactoryBean;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception is :");
        }
        return new LocalContainerEntityManagerFactoryBean();
    }


    @Bean
    public DataSource dataSource() {

        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(20);
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl(dataSourceUrl);
        ds.addDataSourceProperty("user",dataSourceUserName);
        ds.addDataSourceProperty("password",dataSourcePassword);
        ds.setLeakDetectionThreshold(5000);
        return ds;
    }

    private Properties hibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.dialect", hibernateDialect);
        prop.setProperty("hibernate.hbm2ddl.auto", "update");
        prop.put("hibernate.show_sql", "true");
        return prop;
    }
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager platformTransactionManager(
            EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }


    @PostConstruct
    public void printValue(){
        System.out.println(dataSourceClassName);
        System.out.println(dataSourceUrl);
        System.out.println(dataSourceUserName);
        System.out.println(dataSourcePassword);
        System.out.println(hibernateDialect);
    }


}
