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
            String html =
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>JLHTTP</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <h1>JLHTTP，嵌入式 Java HTTP 服务器</h1>\n" +
                    "    <ul>\n" +
                    "        <li><a href=\"/\">/</a></li>\n" +
                    "        <li><a href=\"/text\">/text</a></li>\n" +
                    "        <li><a href=\"/json\">/json</a></li>\n" +
                    "    </ul>\n" +
                    "   <script>\n" +
                    "        var autoclose = getUrlParam(\"autoclose\");\n" +
                    "        if (autoclose) {\n" +
                    "            checkIfServerIsDown();\n" +
                    "        }\n" +
                    "        // 检查服务器是否关闭，如果关闭则自动关闭当前页签\n" +
                    "        function checkIfServerIsDown() {\n" +
                    "            setInterval(function () {\n" +
                    "                var xhr = new XMLHttpRequest();\n" +
                    "                xhr.open('GET', \"/\", true);\n" +
                    "                xhr.onreadystatechange = function () {\n" +
                    "                    if (xhr.readyState === 4 && xhr.status === 200 || xhr.status === 304) {\n" +
                    "                        var data = xhr.responseText;\n" +
                    "                    }\n" +
                    "                };\n" +
                    "                xhr.onerror = function (e) {\n" +
                    "                    open(location, '_self').close();\n" +
                    "                };\n" +
                    "                xhr.send();\n" +
                    "            }, 1000);\n" +
                    "        }\n" +
                    "        // 获取网址参数\n" +
                    "        function getUrlParam (name) {\n" +
                    "            var reg = new RegExp(\"(^|&)\" + name + \"=([^&]*)(&|$)\");\n" +
                    "            var r = window.location.search.substr(1).match(reg);\n" +
                    "            if (r != null) return unescape(r[2]); return null;\n" +
                    "        }\n" +
                    "    </script>" +
                    "</body>\n" +
                    "</html>";
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
            String data =
                    "{\n" +
                    "    \"code\": 0,\n" +
                    "    \"message\": \"success\",\n" +
                    "    \"data\": {}\n" +
                    "}";
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
