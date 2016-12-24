package config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;

/**
 * Created by iSimbarashe on 2016/12/14.
 */
@Configuration
@ComponentScan({"com.projects"})
public class DataConfiguration {
    @Autowired
    Environment environment;

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(){
        Resource configurationPath = new ClassPathResource(environment.getProperty("hibernate.class.path"));
        LocalSessionFactoryBean sessionFactoryBean =  new LocalSessionFactoryBean();
        sessionFactoryBean.setConfigLocation(configurationPath);
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("application.entity.flight.package");
        sessionFactoryBean.setPackagesToScan("application.entity.hotel.package");
        return sessionFactoryBean;
    }

    @Bean
    public DataSource dataSource(){

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("dataSource.driverclassname"));
        dataSource.setUrl(environment.getProperty("dataSource.url"));
        dataSource.setUsername(environment.getProperty("dataSource.username"));
        dataSource.setPassword(environment.getProperty("dataSource.password"));
        //dataSource.setConnectionProperties("");

        return dataSource;
    }
}
