package com.zwx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhaowenx on 2018/8/23.
 */
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.zwx.transmanage.mapper")
//@EnableFeignClients
//@EnableDiscoveryClient
public class TransmanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransmanageApplication.class, args);
    }

}
