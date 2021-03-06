package com.zwx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhaowenx on 2018/8/23.
 */
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.zwx.transmanage.mapper")
//@EnableFeignClients
//@EnableDiscoveryClient
//public class TransmanageApplication extends SpringBootServletInitializer{
public class TransmanageApplication{
    public static void main(String[] args) {
        SpringApplication.run(TransmanageApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(TransmanageApplication.class);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(TransmanageApplication.class, args);
//    }

}
