package config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@ComponentScan({"dao", "service"})
@Configuration
@Profile("test")
@EnableTransactionManagement
public class TestConfig extends Config {

    @Value("${hibernate.dialect}")
    protected String DB_DIALECT;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    protected String DB_DDL_AUTO;
    @Value("${spring.datasource.url}")
    protected String DB_URL;
    @Value("${spring.datasource.username}")
    protected String DB_LOGIN;
    @Value("${spring.datasource.password}")
    protected String DB_PWD;
    @Value("${spring.datasource.driverClassName}")
    protected String DB_DRIVER;

    @Bean
    @Qualifier("myDataSorce")
    public DataSource getDataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(DB_DRIVER)
                .url(DB_URL)
                .username(DB_LOGIN)
                .build();
    }

    @Autowired
    @Qualifier("myDataSorce")
    protected DataSource dataSource;

    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /*
    * Configure a JPA with Hibernate provider and set up shared JPA EntityManagerFactory in a Spring application context
    * @return LocalContainerEntityManagerFactoryBean - entity manager factory for JPA with provider Hibernate
    * */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(dataSource);
        managerFactoryBean.setPackagesToScan("dao");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        managerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        Properties addProps = new Properties();
        addProps.put("hibernate.dialect", DB_DIALECT);
        addProps.put("hibernate.show_sql", true);
        addProps.put("hibernate.hbm2ddl.auto", DB_DDL_AUTO);
        managerFactoryBean.setJpaProperties(addProps);
        return managerFactoryBean;
    }

    /*
    * JpaTransactionManager of JPA with Hibernate provider
    * Binds a JPA EntityManager from specified factory to the thread.
    * @PersistenceContext are aware of thread-bound entity
    * managers in such transaction automatically
    * @return JpaTransactionManager - transaction manager
    * */
    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }


}
