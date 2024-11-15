package com.jackmouse.basicinfra.controller;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @ClassName GenerateCodeController
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/11/14 17:09
 * @Version 1.0
 **/
@RestController("/basic-infra/code")
@AllArgsConstructor
public class GenerateCodeController {

    private final GlobalConfig globalConfig;



    @GetMapping("/generate")
    public void generateCode(@RequestParam String tableName, @RequestParam String tablePrefix, @RequestParam String module) {
        HikariDataSource dataSource = new HikariDataSource();
        globalConfig.setGenerateTable(tableName);
        globalConfig.setTablePrefix(tablePrefix);
        globalConfig.setBasePackage(globalConfig.getBasePackage() + "." + module);
        //注意：url 需添加上 useInformationSchema=true 才能正常获取表的注释
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jackmouse_blog?useInformationSchema=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);
        //生成代码
        generator.generate();
    }
}
