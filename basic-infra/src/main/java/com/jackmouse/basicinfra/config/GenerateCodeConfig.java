package com.jackmouse.basicinfra.config;


import com.jackmouse.common.entity.BaseEntity;
import com.mybatisflex.codegen.config.GlobalConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName GenerateCodeConfig
 * @Description Mybatis flex 代码生成配置
 * @Author zhoujiaangyao
 * @Date 2024/11/14 16:25
 * @Version 1.0
 **/
@Configuration
public class GenerateCodeConfig {

    @Bean
    public GlobalConfig globalConfig() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.getPackageConfig().setSourceDir("/Users/zhoujiaangyao/Downloads/code");
        globalConfig.getPackageConfig().setMapperXmlPath("/Users/zhoujiaangyao/Downloads/code");
        //设置根包
        globalConfig.setBasePackage("com.jackmouse");

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);
        //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(17);

        //设置生成Controller
        globalConfig.enableController();
        //设置生成 service
        globalConfig.enableService();
        globalConfig.enableServiceImpl();
        //设置生成 mapper
        globalConfig.enableMapper();
        globalConfig.enableMapperXml();
        // 忽略表固定字段
        globalConfig.getStrategyConfig().setIgnoreColumns("create_time", "update_time", "update_by", "delete_flag", "create_by", "tenant_id");
        globalConfig.getEntityConfig().setSuperClass(BaseEntity.class);

        return globalConfig;
    }
}
