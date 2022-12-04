> 2022年11月25日，Spring Boot 3.0 正式发布

# 主要特性

- Java 17 baseline 和 Java 19 支持
- 支持 GraalVM native images，取代实验性的 Spring Native 项目
- 支持具有 EE 9 baseline 的 Jakarta EE 10
- Spring Boot 需要 Graal 22.3 或更高版本和 Native Build Tools Plugin 0.9.17 或更高版本

# 参考资料

- [Spring Boot 3.0 正式 GA](https://www.oschina.net/news/219180/spring-boot-3-0-0-ga)
- [Spring Boot 3.0 Release Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Release-Notes)
- [GraalVM Native Image Support](https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html#native-image)

# 构建

> 非Docker方式，使用本机 GraalVM 构建 native-image

```shell
# profile native 使用了 pluginManagement，不会直接显示native插件，暂时只能通过命令调用
mvn -Pnative native:compile -DskipTests
```
