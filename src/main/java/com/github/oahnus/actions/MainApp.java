package com.github.oahnus.actions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.oahnus.actions.dto.BingImgResp;
import com.github.oahnus.actions.dto.DingRet;
import io.itit.itf.okhttp.FastHttpClientBuilder;
import io.itit.itf.okhttp.Response;
import okhttp3.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by oahnus on 2020-10-30
 */
public class MainApp {
    private static final String DING_BOT = "https://oapi.dingtalk.com/robot/send?access_token=ad9a22b93a162e83a52e2fedf2d511f2947bdc1a8702f8d8fb2590d943c39748";

    private static final String BING_IMG = "https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN";
    private static final String BING_BASE = "https://www.bing.com";

    private static boolean dingNotify(String title, String mdContent) {
        // refresh
        try {
            String body = String.format("{\"msgtype\": \"markdown\",\"markdown\": {\"title\": \"%s\",\"text\": \"%s\"}}", "Action-" + title, mdContent);
            Response response = new FastHttpClientBuilder().build()
                    .post()
                    .addHeader("Content-Type", "application/json")
                    .url(DING_BOT)
                    .body(body)
                    .build()
                    .execute();
            String ret = response.getResponse().body().string();
            DingRet dingRet = JSON.parseObject(ret, DingRet.class);
            return dingRet.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static BingImgResp fetchBingImg() throws Exception {
        Response response = new FastHttpClientBuilder().build()
                .get()
                .addHeader("Content-Type", "application/json")
                .url(BING_IMG)
                .build()
                .execute();

        if (response.code() != 200) {
            return null;
        }

        ResponseBody body = response.getResponse().body();
        if (body == null) return null;

        return JSON.parseObject(body.string(), BingImgResp.class);
    }

    public static void BingwpMode(){
        try {
            BingImgResp bingImgResp = fetchBingImg();
            if (bingImgResp == null) {
                return;
            }
            List<BingImgResp.BImage> images = bingImgResp.getImages();
            String title = "每日Bing壁纸";
            String con = String.format("### %s", title);
            if (images != null && !images.isEmpty()) {
                con += images.get(0).getCopyright() + "\n";
                for (BingImgResp.BImage image : images) {
                    con += String.format("![](%s)\n", BING_BASE + image.getUrl());
                }
                boolean ret = dingNotify(title, con);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        RunMode mode = RunMode.BLANK;
        for (String arg : args) {
            if (arg.startsWith("mode=")) {
                String m = arg.replace("mode=", "");
                if (m.equals("")) {
                    continue;
                }
                if ("bingwp".equals(m)) {
                    mode = RunMode.BING_WP;
                }
            }
        }

        switch (mode) {
            case BING_WP:
                BingwpMode();
                break;
            default:
                break;
        }
    }
}
