# java-graalvm-start

- [Gitee](https://gitee.com/westinyang/java-graalvm-start)
- [Gtihub](https://github.com/westinyang/java-graalvm-start)

[English document](README.en.md)

## 介绍

GraalVM最佳实践，使用Java开发CLI、Desktop(JavaFX)、Web(SpringBoot)项目，并使用native-image技术把Java代码静态编译为独立可执行文件（本机映像）。

> GraalVM让Java再次变得强大，使用native-image把程序编译为目标平台的可执行文件，脱离jvm直接运行，启动速度飞快，内存负载也很低。

## 更新日志

> 时隔九个月，再次关注GraalVM，发现以前实验性的Maven插件大多都已经变更，更新记录一波~  
> 如果需要参考旧版代码，请手动切换到 `graalvm-21.0.0.2` 标签。  
> 另外最近刚换了台12代U `i7 12700KF` 的主机，实测比我之前用的 `i7 7700` 编译速度快了2倍还要多，比如 `cli-normal` 模块实测编译耗时10s左右。下面模块概览内的测试数据暂时就不改了，还是之前机器的测试数据。

- **2021-03-05** GraalVM-21.0.0.2 (Java 8 or Java 11) `首次提交`
    - org.graalvm.nativeimage/native-image-maven-plugin:21.0.0.2 `cli`
    - com.gluonhq/client-maven-plugin:0.1.38 `javafx 15.0.1`
    - org.springframework.experimental/spring-graalvm-native:0.8.5 `springboot 2.4.3`
- **2021-12-10** GraalVM-21.3.0 (Java 11 or Java 17)
    - [org.graalvm.buildtools/native-maven-plugin:0.9.8](https://graalvm.github.io/native-build-tools/latest/index.html) `cli`
    - [com.gluonhq/gluonfx-maven-plugin:1.0.10](https://docs.gluonhq.com/#_gluonfx_plugin_for_maven) `javafx 17.0.1`
    - [org.springframework.experimental/spring-native:0.11.0](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/index.html) `springboot 2.6.1`

## 模块概览

> 体验我已经编译好的可执行文件（Windows、Linux、Mac），[点此下载](https://gitee.com/westinyang/java-graalvm-start/releases/v1.0)。  
>
> 下面的性能测试结果是在我本机Windows上测试的，测试结果与机器配置有关，尤其是 `native-image` 编译耗时。

| ↓标签 \ 模块→ | [cli-normal](cli-normal) | [desktop-javafx](desktop-javafx) | [web-springboot](web-springboot) |
| ----- | ----- | ----- | ----- |
| 模块描述 | 命令行应用（无框架） | 桌面应用（JavaFx） | Web应用（SpringBoot） |
| JDK | 11 or 17 | 11 or 17 | 11 or 17 |
| GraalVM | CE-21.3.0 | CE-21.3.0 | CE-21.3.0 |
| Maven Plugin | native-maven-plugin | gluonfx-maven-plugin | spring-native | 
| 启动耗时（jvm） | 0.713s | 2.555s | 1.793s |
| **启动耗时（native-image）** | **0.047s** | **0.665s** | **0.216s** |
| 内存负载（jvm） | 38.8m | 309.3m | 440.5m |
| **内存负载（native-image）** | **3.1m** | **60.4m** | **70.2m** |
| 编译耗时（native-image） | 24.786s | 93.455s | 99.434s |
| 可执行文件大小（7z压缩） | 8.03m (7z : 1.68m) | 62.7m (7z : 13.1m) | 66.5m (7z : 13.9m) |

**新增模块**

> 新增模块简单介绍和基本测试数据

- [web-jlhttp](web-jlhttp)
    - 仅3000行Java代码实现的嵌入式HTTPServer，有些时候我们仅仅是想写一两个简单的接口打包发布，使用Spring等框架真的是觉得小题大做了。
    - jarfile：`52k`，可执行文件大小：`12.9m`， 7z：`2.9m`
- [web-nanohttpd](web-nanohttpd)
    - 另一种轻量级且设计良好的嵌入式HTTPServer实现，该库常用语Android应用开发，不过在这里配合GraalVM一样能正常使用。
    - jarfile：`54k`，可执行文件大小：`12.8m`， 7z：`2.9m`

## 开发环境

> 以下是我本机的开发环境，理论上windows、linux、mac都是支持的（注意不同平台的 GraalVM SDK 和 native-image的依赖是不一样的）。

- Windows 10 (CPU: i7-7700, RAM: 16G)
- IntelliJ IDEA 2020
- jdk-11.0.10 `其实也可以不用，因为GraalVM是自带OpenJDK的`
- graalvm-ce-java11-21.3.0
- Visual Sutdio 2019

## 环境配置（Windows）

> System、IDE、JDK 这三个就不用说了，直接跳过...

**Graal VM**
- [下载 Graal VM SDK](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-21.3.0)
- 设置GraalVM环境变量（注意JAVA_HOME也要指向GRAALVM_HOME）：
``` bat
GRAALVM_HOME = C:\path\to\graalvm-ce-java11-21.3.0
JAVA_HOME = %GRAALVM_HOME%
PATH += %GRAALVM_HOME%\bin
```
- 验证环境 `java -version`
```
C:\Users\Administrator>java -version
openjdk version "11.0.13" 2021-10-19
OpenJDK Runtime Environment GraalVM CE 21.3.0 (build 11.0.13+7-jvmci-21.3-b05)
OpenJDK 64-Bit Server VM GraalVM CE 21.3.0 (build 11.0.13+7-jvmci-21.3-b05, mixed mode, sharing)
```
- 安装native-image组件
``` bat
gu install native-image
```
- 验证组件
``` bat
gu list
native-image --version
```

**MSVC (Visual Studio 2019)**

> 除了GraalVM，还需要Microsoft Visual Studio 2019。社区版就足够了，可以从[这里下载](https://visualstudio.microsoft.com/downloads/)。

在安装过程中，请确保至少选择以下各个组件：

- 选择英语语言包
- C++/CLI v142 构建工具（14.25或更高版本）
- MSVC v142 - VS 2019 C++ x64/x86 构建工具（v14.25或更高版本）
- Windows 通用 CRT SDK
- Windows 10 SDK（10.0.19041.0或更高版本）

> 所有构建命令（无论是Maven还是Gradle）都必须在名为的Visual Studio 2019命令提示符中执行 `x64 Native Tools Command Prompt for VS 2019`。快捷方式可以在“开始菜单”中找到，也可以在搜索框中搜索应用程序。 阅读Microsoft文档以获取更多信息。
> 
> 另外，您可以执行 `cmd.exe /k "<path to VS2019>\VC\Auxiliary\Build\vcvars64.bat` 从任何其他终端运行，然后再开始使用构建命令。

> **推荐这个终极方案：[Visual Studio 2019 配置 MSVC 环境变量，使用命令行编译](https://www.jianshu.com/p/7fab25165f4b)，这样就可以在任何终端直接使用MSVC的编译工具。**
> 
> 一定要使用这个终极方案，否则在接下来的项目构建中，将会变得很麻烦，每次 `mvn package` 之前都要先执行 `cmd.exe /k "<path to VS2019>\VC\Auxiliary\Build\vcvars64.bat` 。

## 环境配置（Linux/Mac）

**Linux**

下载GraalVM，配置环境变量，除了GraalVM，还需要以下软件包：

- gcc 6 或更高版本
- ld 2.26 或更高版本
- Ubuntu 18 还需要安装以下这些库（我在Linux子系统中已经测试通过）：
```shell script
sudo apt install pkg-config libgl-dev libglib2.0-dev libgtk-3-dev libpango1.0-dev libx11-dev libxtst-dev libasound2-dev libavcodec-dev libavformat-dev libavutil-dev
```
- 其他Linux发行版，请在打包时查看错误都缺少哪些依赖库，自行安装。

**Mac**

- TODO

有关不同平台配置和依赖更详细的说明，请参考：
- [install-native-image](https://www.graalvm.org/reference-manual/native-image/#install-native-image) `构建Java应用时参考`
- [gluon documentation](https://docs.gluonhq.com/#_platforms) `构建JavaFx应用时参考`

> tips：Gluon公司是 `OpenJFX` 项目和 `GraalVM` 项目的贡献者，该公司提供了 `client-maven-plugin ` 封装了 `native-image` 的相关命令，简化了打包操作。

## 注意事项

- 通过上述步骤，你已经配置好了开发环境，还有一点需要注意的是，在IDEA开发工具中，你需要为项目设置JDK的时候应该直接指向GraalVM下的bin目录，而不是其他JDK的目录，否则在编译时可能会出现错误。

## 扩展阅读

- GraalVM应该是不支持交叉编译的，不过可以利用Windows提供的Linux子系统来编译源码。

## 后续规划

> 对于这三种应用的其他实现方式，后续可能会添加更多的模块

- cli-<支持解析args参数的库> `用于更快速的开发cli应用`
- desktop-&lt;Swing/AWT> `其他GUI实现方式`
- web-<轻量级无容器的http-server库> `其他Web实现方式`

## 技术交流

- Q群：[707416319](https://qm.qq.com/cgi-bin/qm/qr?k=uSAXH8sKqQnF_cvDSF4T8IN7tSqabAJ3&jump_from=webapi)

## 参考资料

- 官方资料
    - [GraalVM 入门](https://www.graalvm.org/docs/getting-started/)
    - [native-image 文档](https://www.graalvm.org/reference-manual/native-image/)
    - [JavaFX 中文官网](https://openjfx.cn/) `推荐阅读`
    - [Spring Native for GraalVM 文档](https://repo.spring.io/milestone/org/springframework/experimental/spring-graalvm-native-docs/0.8.5/spring-graalvm-native-docs-0.8.5.zip!/reference/index.html) `推荐阅读`
- 网络文章
    - [如何评价 GraalVM 这个项目？](https://www.zhihu.com/question/274042223) `推荐阅读`
    - [GraalVM：微服务时代的Java](https://www.zhihu.com/column/p/137836206) `推荐阅读` `很全面的讲解`
    - [为 JavaFX 应用生成 native image](https://zhuanlan.zhihu.com/p/103606559?utm_source=wechat_session)
    - [Windows 下使用 GraalVM 将 JavaFX 应用编译成 exe](https://www.cnblogs.com/dehai/p/14258391.html)
    - [Spring Boot as GraalVM Native Images](https://blog.codecentric.de/en/2020/05/spring-boot-graalvm/) `推荐阅读` `可能需要“出国留学”工具`
    - [Spring Boot GraalVM Native Image builds with the native-image-maven-plugin](https://blog.codecentric.de/en/2020/05/spring-boot-graalvm/) `推荐阅读` `可能需要“出国留学”工具`
