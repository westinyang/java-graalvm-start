# java-graalvm-start

## 介绍

GraalVM最佳实践，使用Java开发CLI、Desktop(JavaFX)、Web(StringBoot)项目，并使用native-image技术把Java代码静态编译为独立可执行文件（本机映像）。

## 模块概览

> 其中的性能测试结果与机器配置有关，尤其是 `native-image` 编译耗时。

| ↓标签 \ 模块→ | [cli-normal](cli-normal) | [desktop-javafx](desktop-javafx) | [web-springboot](web-springboot) |
| ----- | ----- | ----- | ----- |
| 模块描述 | 命令行应用（无框架） | 桌面应用（JavaFx） | Web应用（SpringBoot） |
| JDK | 8 or 11+ | 11+ | 8 or 11+ |
| GraalVM | CE-21.0.0.2+ | CE-21.0.0.2+ | CE-21.0.0.2+ |
| Maven Plugin | [native-image-maven-plugin](https://www.graalvm.org/reference-manual/native-image/NativeImageMavenPlugin/) | [client-maven-plugin](https://docs.gluonhq.com/#_the_gluon_client_plugin_for_maven) | [native-image-maven-plugin](https://www.graalvm.org/reference-manual/native-image/NativeImageMavenPlugin/) |
| 启动耗时（jvm） | 0.713s | 2.555s | 1.793s |
| 启动耗时（native-image） | 0.047s | 0.665s | 0.216s |
| 编译耗时（native-image） | 24.786s | 93.455s | 99.434s |
| 可执行文件大小（7z压缩） | 8.03m (7z : 1.68m) | 62.7m (7z : 13.1m) | 66.5m (7z : 13.9m) |

## 开发环境

> 以下是我本机的开发环境，理论上windows、linux、mac都是支持的（注意不同平台的 GraalVM SDK 和 native-image的依赖是不一样的）。

- Windows 10 (CPU: i7-7700, RAM: 16G)
- IntelliJ IDEA 2020
- jdk-11.0.10 `其实也可以不用，因为GraalVM是自带OpenJDK的`
- graalvm-ce-java11-21.0.0.2
- Visual Sutdio 2019

## 环境配置（Windows）

> System、IDE、JDK 这三个就不用说了，直接跳过...

**Graal VM**
- [下载 Graal VM SDK](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-21.0.0.2)
- 设置GraalVM环境变量（注意JAVA_HOME也要指向GRAALVM_HOME）：
``` bat
GRAALVM_HOME = C:\path\to\graalvm-ce-java11-21.0.0.2
JAVA_HOME = %GRAALVM_HOME%
PATH += %GRAALVM_HOME%\bin
```
- 验证环境 `java -version`
```
C:\Users\Administrator>java -version
openjdk version "11.0.10" 2021-01-19
OpenJDK Runtime Environment GraalVM CE 21.0.0.2 (build 11.0.10+8-jvmci-21.0-b06)
OpenJDK 64-Bit Server VM GraalVM CE 21.0.0.2 (build 11.0.10+8-jvmci-21.0-b06, mixed mode, sharing)
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

**Mac**

- TODO

有关不同平台配置和依赖更详细的说明，请参考：[Gluon Documentation](https://docs.gluonhq.com/#_platforms)

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
