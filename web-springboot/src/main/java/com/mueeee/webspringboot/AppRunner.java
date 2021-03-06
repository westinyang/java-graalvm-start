package com.mueeee.webspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Execute after the application started
 */
@Component
public class AppRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    @Value("${server.port}")
    private int serverPort;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 打印日志
        log.info("Service running on 127.0.0.1:" + serverPort);

        // 自动打开浏览器访问项目地址
        String osName = System.getProperty("os.name", "");
        if (osName.contains("Windows")) {
            try {
                Runtime.getRuntime().exec("cmd /c start http://127.0.0.1:" + serverPort + "?autoclose=1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
