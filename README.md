## 学习码匠社区

## 资料
[Spring 文档](https://spring.io/guides)
[Spring web文档](https://spring.io/guides/gs/serving-web-content/)
[ES 社区](https://elasticsearch.cn/explore/)
[Bootstrap v3 组件文档](https://getbootstrap.com/docs/3.3/components/#navbar)
[GitHub 部署密钥](https://docs.github.com/en/developers/overview/managing-deploy-keys#deploy-keys)
[GitHub 部署密钥教程](https://www.cnblogs.com/luhouxiang/p/5668118.html)
[GitHub 授权App文档](https://docs.github.com/en/developers/apps/building-oauth-apps)
[OkHttp 官网](https://square.github.io/okhttp/)
[Springboot 文档](https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/htmlsingle/#boot-features-embedded-database-support)
[Mybatis 文档](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
[H2 Database](https://www.h2database.com/html/quickstart.html)
[Maven 中央仓库](https://mvnrepository.com/)
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
[Spring 文档](https://docs.spring.io/spring/docs/5.2.8.RELEASE/spring-framework-reference/web.html#mvc-config-interceptors)


## 工具
[Vim 官网](https://www.vim.org/download.php#pc)
[Git 官网](https://git-scm.com/downloads)
[Visual paradigm 官网](https://www.visual-paradigm.com/cn/)
作图软件
[Flyway 官网](https://flywaydb.org/getstarted/firststeps/maven)
多人同步数据库
[Lombok 官网](https://projectlombok.org/setup/maven)
@Data 自动生成getter&setter toString()

## 脚本
```sql
CREATE CACHED TABLE "PUBLIC"."USER"(
    "ID" INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    "ACCOUNT_ID" VARCHAR(100),
    "NAME" VARCHAR(50),
    "TOKEN" CHAR(36),
    "GMT_CREAT" BIGINT,
    "GMT_MODIFIED" BIGINT
);

CREATE USER IF NOT EXISTS sa PASSWORD '123';
ALTER USER sa admin true ;
```

```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
---
test commit --amend --no-edit
