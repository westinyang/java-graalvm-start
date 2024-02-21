- [Add Icon to Windows native binary](https://github.com/gluonhq/substrate/pull/1090#issuecomment-1479999989)
- [Maven 插件：尚不支持 Maven 运行时 3.9.0（在这种情况下，将引发错误，要求降级到 3.8.8 或更低版本）](https://docs.gluonhq.com/#_what_is_new) `1.0.17`
```log
Maven version 3.9.2 is not currently supported by the GluonFX Maven Plugin.
Please downgrade your Maven version to 3.8.8 and then try again.
```
- [Couldn't determine GraalVM version](https://github.com/gluonhq/gluonfx-maven-plugin/issues/489)
    - gluonfx-maven-plugin对于刚出的21支持有问题，其根本原因在于所使用的substrate 0.60存在的版本识别问题，在0.61中修复，gluonfx-maven-plugin在1.0.22版本中升级到substrate 0.61解决了这个问题
- Native-image building on Windows currently only supports target architecture: AMD64 (?? unsupported))
  - GluonFx + GraalVM 21，21之前的版本没有此问题，非GluonFx插件项目的没有此问题
  - 更改VS语言包删除中文 或 修改 系统语言设置等，暂不考虑此方案
  - 添加构建参数 `-H:-CheckToolchain`，跳过环境检查，使用此方案
