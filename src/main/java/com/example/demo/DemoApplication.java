package com.example.demo;

import com.example.demo.service.ExcelService;
import com.example.demo.tools.SpringUtil;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class
})
@ComponentScan(basePackages = {"com.example.demo"})
@SpringBootApplication
public class DemoApplication
{
    // 日志
    private static Logger log = getLogger(DemoApplication.class);

    @Bean
    public SpringUtil getSpringUtil()
    {
        return new SpringUtil();
    }

    private static ExcelService excelService;

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("hello world");

        excelService = (ExcelService) SpringUtil.getBean("excelService");

        excelService.generateExcel();
    }

}
