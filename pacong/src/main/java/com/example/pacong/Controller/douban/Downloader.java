package com.example.pacong.Controller.douban;


import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuwencong on 2019/4/1
 * 下载器
 */
@Slf4j
class Downloader {
    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .build();

    /**
     * 下载网页
     *
     * @param url
     * @return
     */
    String download(String url) throws Exception {
        try {
            // 使用Cookie消息头是为了简化登录问题(豆瓣电影评论不登录条件下获取不到全部数据)
            return getHttpResponse(url);
        } catch (IOException e) {
            log.error("下载网页[{}]失败!", url, e);
        }
        return null;
    }


    private String getHttpResponse(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", ReqContent.USER_AGENT)
                .addHeader("Cookie", ReqContent.COOKIE)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.code() + "," + response.message());
        }
        return response.body().string();
    }


}
