package com.mueeee.desktopjavafx;

import com.mueeee.desktopjavafx.conf.AppConfig;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFx Application
 *
 * 开发时如果直接启动这个类，会报找不到组件或其他错误信息，需要在IDEA启动项中配置如下jvm参数
 * --module-path "D:\dev\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
 * 关于这个问题，还有另一种解决方案，另外创建一个主类，然后类调用默认的启动类。请运行：DesktopJavaFxApp
 *
 * 官方文档：https://openjfx.io/openjfx-docs/
 * 错误原因：https://my.oschina.net/tridays/blog/2222909
 */
public class App extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        AppConfig.init();
        launch();
    }

    @Override
    public void init() throws Exception {
        System.out.println("init");
        super.init();
        // 在非JavaFX应用程序主线程上运行指定的Runnable
        /*Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Content Text");
            alert.show();
        });*/
    }

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("start");
        // 加载并创建主场景
        Parent root = loadFXML("fxml/Main");
        scene = new Scene(root, AppConfig.stageWidth, AppConfig.stageHeight);
        // 设置窗口信息
        stage.setTitle(AppConfig.title);
        stage.setResizable(AppConfig.stageResizable);
        stage.getIcons().add(new Image(App.class.getResourceAsStream(AppConfig.icon)));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
        super.stop();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
