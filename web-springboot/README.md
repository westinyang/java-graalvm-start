# 早期实验

- Spring Boot 2.7.1 ~ Spring Native 0.12.1 之前长期使用
- Spring Boot 2.7.7 ~ Spring Native 0.12.2 最新匹配可用 `2023`

# 停止维护

- 2023-09-19
- 废弃的 spring-native，编译时提示不支持JDK21
- 不过经测试，使用JDK17编译项目打包jar，环境变量配置 GraalVM for JDK 21 进行 native-image 构建依然是可以的
