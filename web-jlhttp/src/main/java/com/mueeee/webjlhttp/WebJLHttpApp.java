package com.mueeee.webjlhttp;

import net.freeutils.httpserver.HTTPServer;

import java.io.IOException;

/**
 * JLHTTP
 * Download: https://www.freeutils.net/source/jlhttp
 * FAQ: https://www.freeutils.net/source/jlhttp/faq
 */
public class WebJLHttpApp {

    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) throws IOException {
        HTTPServer httpServer = new HTTPServer(SERVER_PORT);
        HTTPServer.VirtualHost host = httpServer.getVirtualHost(null);  // default virtual host

        host.addContext("/", (req, resp) -> {
            String html = """
                    <!DOCTYPE html>
                        <html>
                        <head>
                            <meta charset="UTF-8">
                            <meta http-equiv="X-UA-Compatible" content="IE=edge">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>JLHTTP</title>
                        </head>
                        <body>
                            <h1>JLHTTP，嵌入式 Java HTTP 服务器</h1>
                            <ul>
                                <li><a href="/">/</a></li>
                                <li><a href="/text">/text</a></li>
                                <li><a href="/json">/json</a></li>
                            </ul>
                            <script>
                                var autoclose = getUrlParam("autoclose");
                                if (autoclose) {
                                    checkIfServerIsDown();
                                }
                                // 检查服务器是否关闭，如果关闭则自动关闭当前页签
                                function checkIfServerIsDown() {
                                    setInterval(function () {
                                        var xhr = new XMLHttpRequest();
                                        xhr.open('GET', "/", true);
                                        xhr.onreadystatechange = function () {
                                            if (xhr.readyState === 4 && xhr.status === 200 || xhr.status === 304) {
                                                var data = xhr.responseText;
                                            }
                                        };
                                        xhr.onerror = function (e) {
                                            open(location, '_self').close();
                                        };
                                        xhr.send();
                                    }, 1000);
                                }
                                // 获取网址参数
                                function getUrlParam (name) {
                                    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                                    var r = window.location.search.substr(1).match(reg);
                                    if (r != null) return unescape(r[2]); return null;
                                }
                            </script>
                        </body>
                    </html>
                    """;
            resp.getHeaders().add("Content-Type", "text/html; charset=utf-8");
            resp.send(200, html);
            return 1;
        });
        host.addContext("/text", (req, resp) -> {
            String text = "Hello JLHTTP";
            resp.getHeaders().add("Content-Type", "text/plain");
            resp.send(200, text);
            return 1;
        });
        host.addContext("/json", (req, resp) -> {
            String data = """
                    {
                        "code": 0,
                        "message": "success",
                        "data": {}
                    }
                    """;
            resp.getHeaders().add("Content-Type", "application/json");
            resp.send(200, data);
            return 1;
        });

        httpServer.start();
        System.out.println("HTTPServer is listening on port " + SERVER_PORT);

        // Test
        onApplicationRunning();
    }

    public static void onApplicationRunning() {
        // 自动打开浏览器访问项目地址
        String osName = System.getProperty("os.name", "");
        if (osName.contains("Windows")) {
            try {
                Runtime.getRuntime().exec("cmd /c start http://127.0.0.1:" + SERVER_PORT + "?autoclose=1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
