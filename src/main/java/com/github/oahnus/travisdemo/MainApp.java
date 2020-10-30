package com.github.oahnus.travisdemo;

import io.itit.itf.okhttp.FastHttpClientBuilder;
import io.itit.itf.okhttp.Response;

/**
 * Created by oahnus on 2020-10-30
 */
public class MainApp {
    public static void main(String... args) throws Exception {
        String body = String.format("{\"msgtype\": \"markdown\",\"markdown\": {\"title\": \"%s\",\"text\": \"%s\"}}", "ROBOT测试", "测试123");
        String url = "https://oapi.dingtalk.com/robot/send?access_token=0003dfe75e0a39f007d53d4b793fad65a897f86812f7073d721b4d592cc38d18";
        Response response = new FastHttpClientBuilder().build()
                .post()
                .addHeader("Content-Type", "application/json")
                .url(url)
                .body(body)
                .build()
                .execute();
        System.out.println(response.getResponse().body().string());
    }
}
