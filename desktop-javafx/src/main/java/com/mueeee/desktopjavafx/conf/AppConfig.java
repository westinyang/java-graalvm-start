package com.mueeee.desktopjavafx.conf;

import com.mueeee.desktopjavafx.App;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 应用配置
 */
public class AppConfig {
    // 应用标题
    public static String title = "JavaFx Application";
    // 应用图标
    public static String icon = "icon/icon.png";
    // 窗口宽度
    public static int stageWidth = 640;
    // 窗口高度
    public static int stageHeight = 480;
    // 允许调整窗口尺寸
    public static boolean stageResizable = true;

    public static void init() {
        try {
            Properties properties = new Properties();
            InputStream in = App.class.getResourceAsStream("app.properties");
            properties.load(in);
            title = properties.getProperty("title");
            icon = properties.getProperty("icon");
            stageWidth = Integer.parseInt(properties.getProperty("stage.width"));
            stageHeight = Integer.parseInt(properties.getProperty("stage.height"));
            stageResizable = Boolean.parseBoolean(properties.getProperty("stage.resizable"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
