# java-graalvm-start

- [Gitee](https://gitee.com/westinyang/java-graalvm-start)
- [Gtihub](https://github.com/westinyang/java-graalvm-start)

[中文文档](README.md)

## Description

Graal VM best practice, use Java to develop CLI, Desktop (Java FX), Web (Spring Boot) projects, and use native-image technology to statically compile Java code into independent executable files (native images).

> Graal VM makes Java powerful again, using native-image to compile the program into an executable file of the target platform, and run directly without jvm, the startup speed is very fast, and the memory load is also very low.

## Update log

> After nine months, I paid attention to Graal VM again and found that most of the previous experimental Maven plugins have been changed, and there is a wave of update records~  
> If you need to refer to the old version of the code, please manually switch to the `graalvm-21.0.0.2` tag.  
> In addition, I just changed the 12th generation U `i7 12700KF` host. The actual test is more than 2 times faster than the `i7 7700` I used before. For example, the measured compilation time of the `cli-normal` module takes about 10s. The test data in the module overview below will not be changed for the time being, and it is still the test data of the previous machine.

- **2021-03-05** GraalVM-21.0.0.2 (Java 8 or Java 11) `First submission`
    - org.graalvm.nativeimage/native-image-maven-plugin:21.0.0.2 `cli`
    - com.gluonhq/client-maven-plugin:0.1.38 `javafx 15.0.1`
    - org.springframework.experimental/spring-graalvm-native:0.8.5 `springboot 2.4.3`
- **2021-12-10** GraalVM-21.3.0 (Java 11 or Java 17)
    - [org.graalvm.buildtools/native-maven-plugin:0.9.8](https://graalvm.github.io/native-build-tools/latest/index.html) `cli`
    - [com.gluonhq/gluonfx-maven-plugin:1.0.10](https://docs.gluonhq.com/#_gluonfx_plugin_for_maven) `javafx 17.0.1`
    - [org.springframework.experimental/spring-native:0.11.0](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/index.html) `springboot 2.6.1`

## Module overview

> To experience the executable file I have compiled (Windows, Linux, Mac), [click here to download](https://gitee.com/westinyang/java-graalvm-start/releases/v1.0).
> 
> The following performance test results are tested on my native Windows. The test results are related to the machine configuration, especially the time-consuming compilation of `native-image`.

| ↓Tag \ Module→ | [cli-normal](cli-normal) | [desktop-javafx](desktop-javafx) | [web-springboot](web-springboot) |
| ----- | ----- | ----- | ----- |
| Module desc | Command line app (no framework) | Desktop app (JavaFx) | Web app (SpringBoot) |
| JDK | 11 or 17 | 11 or 17 | 11 or 17 |
| GraalVM | CE-21.3.0 | CE-21.3.0 | CE-21.3.0 |
| Maven Plugin | native-maven-plugin | gluonfx-maven-plugin | spring-native |
| Time-consuming to start (jvm) | 0.713s | 2.555s | 1.793s |
| **Time-consuming to start (native-image)** | **0.047s** | **0.665s** | **0.216s** |
| Memory load（jvm） | 38.8m | 309.3m | 440.5m |
| **Memory load（native-image）** | **3.1m** | **60.4m** | **70.2m** |
| Time-consuming to start (native-image) | 24.786s | 93.455s | 99.434s |
| Executable file size (7z compression) | 8.03m (7z : 1.68m) | 62.7m (7z : 13.1m) | 66.5m (7z : 13.9m) | 

**New module**

> Brief introduction of new modules and basic test data

- [web-jlhttp](web-jlhttp)
    - Embedded HTTP Server implemented with only 3000 lines of Java code, sometimes we just want to write one or two simple interfaces for packaging and publishing. Using frameworks such as Spring is really a fuss.
    - jarfile：`52k`，Executable file size：`12.9m`， 7z：`2.9m`
- [web-nanohttpd](web-nanohttpd)
    - Another lightweight and well-designed embedded HTTP Server implementation. The library is commonly used for Android application development, but it can be used normally with Graal VM here.
    - jarfile：`54k`，Executable file size：`12.8m`， 7z：`2.9m`

## Development environment

> The following is the development environment of my machine. In theory, windows, linux and mac are all supported (note that the dependencies of GraalVM SDK and native-image of different platforms are different).

- Windows 10 (CPU: i7-7700, RAM: 16G)
- IntelliJ IDEA 2020
- jdk-11.0.10 `In fact, you don’t need to, because GraalVM comes with OpenJDK`
- graalvm-ce-java11-21.3.0
- Visual Sutdio 2019

## Environment configuration (Windows)

> Needless to say, System, IDE, and JDK, just skip...

**Graal VM**
- [Download Graal VM SDK](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-21.3.0)
- Set GraalVM environment variables (note that JAVA_HOME also points to GRAALVM_HOME):
``` bat
GRAALVM_HOME = C:\path\to\graalvm-ce-java11-21.3.0
JAVA_HOME = %GRAALVM_HOME%
PATH += %GRAALVM_HOME%\bin
```
- Verify the environment `java -version`
```
C:\Users\Administrator>java -version
openjdk version "11.0.13" 2021-10-19
OpenJDK Runtime Environment GraalVM CE 21.3.0 (build 11.0.13+7-jvmci-21.3-b05)
OpenJDK 64-Bit Server VM GraalVM CE 21.3.0 (build 11.0.13+7-jvmci-21.3-b05, mixed mode, sharing)
```
- Install native-image components
``` bat
gu install native-image
```
- Verification component
``` bat
gu list
native-image --version
```

**MSVC (Visual Studio 2019)**

> In addition to GraalVM, Microsoft Visual Studio 2019 is also required. The community version is enough, You can  [download it from here](https://visualstudio.microsoft.com/downloads/)

During the installation process, make sure to select at least the following individual components:

- Choose the English Language Pack
- C++/CLI support for v142 build tools (14.25 or later)
- MSVC v142 - VS 2019 C++ x64/x86 build tools (v14.25 or later)
- Windows Universal CRT SDK
- Windows 10 SDK (10.0.19041.0 or later)

> All build commands, be it with Maven or Gradle, must be executed in a Visual Studio 2019 Command Prompt called x64 Native Tools Command Prompt for VS 2019. A shortcut can be found in the "Start Menu", or you can search the application in the search box. Read the Microsoft documentation for more information.  
> 
> Alternatively, you can run `cmd.exe /k "<path to VS2019>\VC\Auxiliary\Build\vcvars64.bat` from any other terminal before you can start using the build commands.

> **Recommend this ultimate solution: [Visual Studio 2019 configure MSVC environment variables, use the command line to compile](https://www.jianshu.com/p/7fab25165f4b)，In this way, MSVC compilation tools can be used directly on any terminal.**
> 
> Be sure to use this ultimate solution, otherwise it will become very troublesome in the build project. Every time before `mvn package`, you must execute `cmd.exe k "<path to VS2019>\VC\Auxiliary\ Build\vcvars64.bat`.

## Environment configuration（Linux/Mac）

**Linux**

Download Graal VM and configure environment variables. In addition to Graal VM, the following software packages are also required:

- gcc version 6 or higher
- ld version 2.26 or higher
- Ubuntu 18 also needs to install the following libraries (I have tested it in the Linux subsystem):
```shell script
sudo apt install pkg-config libgl-dev libglib2.0-dev libgtk-3-dev libpango1.0-dev libx11-dev libxtst-dev libasound2-dev libavcodec-dev libavformat-dev libavutil-dev
```
- For other Linux distributions, please check which dependent libraries are missing for errors when packaging, and install them yourself.

**Mac**

- TODO

For a more detailed description of different platform configurations and dependencies, please refer to:
- [install-native-image](https://www.graalvm.org/reference-manual/native-image/#install-native-image) `Reference when building Java applications`
- [gluon documentation](https://docs.gluonhq.com/#_platforms) `Reference when building JavaFx applications`

> tips：Gluon is a contributor to the `OpenJFX` project and the `GraalVM` project. The company provides `client-maven-plugin` to encapsulate the related commands of `native-image`, which simplifies the packaging operation.

## Precautions

- Through the above steps, you have configured the development environment. Another thing to note is that in IDEA development tools, when you need to set the JDK for the project, you should directly point to the bin directory under GraalVM, not other JDK directories, Otherwise an error may occur during compilation.

## Extended reading

- GraalVM should not support cross-compilation, but you can use the Linux subsystem provided by Windows to compile the source code.

## Follow-up planning

> For other implementations of these three applications, more modules may be added later.

- cli-&lt;A library that supports parsing args parameters> `For faster development of cli applications`
- desktop-&lt;Swing/AWT&gt; `Other GUI implementations`
- web-&lt;Lightweight, containerless http-server library> `Other web implementations`

## Technology Exchange

- QQ Group: [707416319](https://qm.qq.com/cgi-bin/qm/qr?k=uSAXH8sKqQnF_cvDSF4T8IN7tSqabAJ3&jump_from=webapi)

## Reference

- Official information
    - [Get Started with GraalVM](https://www.graalvm.org/docs/getting-started/)
    - [native-image documentation](https://www.graalvm.org/reference-manual/native-image/)
    - [JavaFX website](https://openjfx.io/) `Recommended reading`
    - [Spring Native for GraalVM documentation](https://repo.spring.io/milestone/org/springframework/experimental/spring-graalvm-native-docs/0.8.5/spring-graalvm-native-docs-0.8.5.zip!/reference/index.html) `Recommended reading`
- Web articles
    - [How to evaluate the GraalVM project?](https://www.zhihu.com/question/274042223) `Recommended reading`
    - [Graal VM: Java in the age of microservices](https://www.zhihu.com/column/p/137836206) `Recommended reading` `Very comprehensive explanation`
    - [Generate native image for JavaFX application](https://zhuanlan.zhihu.com/p/103606559?utm_source=wechat_session)
    - [Use GraalVM under Windows to compile JavaFX applications into exe](https://www.cnblogs.com/dehai/p/14258391.html)
    - [Spring Boot as GraalVM Native Images](https://blog.codecentric.de/en/2020/05/spring-boot-graalvm/) `Recommended reading`
    - [Spring Boot GraalVM Native Image builds with the native-image-maven-plugin](https://blog.codecentric.de/en/2020/05/spring-boot-graalvm/) `Recommended reading`
