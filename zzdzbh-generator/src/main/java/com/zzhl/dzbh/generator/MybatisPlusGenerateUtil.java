package com.zzhl.dzbh.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 *【描述】：Mybatis 代码生成公工具
 *
 * @author tanggw
 * @date 2020-9-11 15:29
 */
public class MybatisPlusGenerateUtil {

    private static final String databaseURL = "jdbc:mysql://localhost:3306/zzdzbh_bak?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String password = "root";
    private static final String generateUserName = "tanggewei";
    private static String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {
        generate(ConfigEnum.COMMON);
    }

    public enum ConfigEnum {
        /**
         * 生成
         * F:\IDEAspace\wechatpa-backend\medusa-core
         */
        COMMON(generateUserName, "\\zzdzbh-system",
                "common",
                "com.zzhl",
                "t_b_area_dict"
        ),
        ;

        private final String author;
        /**
         * 项目路径
         */
        private final String projectPath;
        /**
         * 项目包名
         */
        private final String moduleName;
        /**
         * 项目包名
         */
        private final String packageName;
        /**
         * 数据库表明,多个用逗号分隔
         */
        private String tables;

        ConfigEnum(String author, String projectPath, String moduleName, String packageName, String tables) {
            this.author = author;
            this.projectPath = projectPath;
            this.moduleName = moduleName;
            this.packageName = packageName;
            this.tables = tables;
        }
    }

    /**
     * 根据配置生成Mapper等文件
     *
     * @param generateConfig 初始化配置
     */
    private static void generate(ConfigEnum generateConfig) {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig config = config(generateConfig);
        mpg.setGlobalConfig(config);
        mpg.setDataSource(datasource());
        PackageConfig packageInfo = packageConfig(generateConfig);
        mpg.setPackageInfo(packageInfo);
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = projectPath + generateConfig.projectPath + "/src/main/resources/mapper/" + packageInfo.getModuleName() + "/";
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        InjectionConfig injectionConfig = injectionConfig();
        injectionConfig.setFileOutConfigList(focList);
        mpg.setCfg(injectionConfig);


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(generateConfig.tables.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageInfo.getModuleName() + "_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    private static GlobalConfig config(ConfigEnum generateConfig) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + generateConfig.projectPath + "/src/main/java");
        globalConfig.setAuthor(generateConfig.author);
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setFileOverride(false);
        globalConfig.setDateType(DateType.TIME_PACK);
        globalConfig.setOpen(false);
        return globalConfig;
    }

    private static DataSourceConfig datasource() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(databaseURL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(userName);
        dataSourceConfig.setPassword(password);
        return dataSourceConfig;
    }

    private static PackageConfig packageConfig(ConfigEnum generateConfig) {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(generateConfig.moduleName);
        packageConfig.setParent(generateConfig.packageName);
        return packageConfig;
    }

    private static InjectionConfig injectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
    }
}
