package com.it.ynzl.main.configer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 应用配置
 * @author: Mr.Muxl
 * @create: 2021-12-09 15:26
 **/
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.it.ynzl.main"})
@MapperScan(basePackages = {"com.it.ynzl.main.mapper"})
public class ApplicationConfig {

}
