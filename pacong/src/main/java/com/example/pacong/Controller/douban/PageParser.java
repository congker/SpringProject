package com.example.pacong.Controller.douban;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xuwencong on 2019/4/1
 *
 * 页面解析器
 */
class PageParser<T> {

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Data<T> {
        private List<String> links;
        private List<T> results;
    }

    Data parse(String url, String html) {
        Document doc = Jsoup.parse(html, url);

        // 获取链接列表
        List<String> links = doc.select("#paginator > a[href]").stream()
                .map(a -> a.attr("abs:href"))
                .collect(Collectors.toList());

        // 获取数据列表
        List<Map<String, String>> results = doc.select("#comments > div.comment-item")
                .stream()
                .map(div -> {
                    Map<String, String> data = new HashMap<>();

                    String author = div.selectFirst("h3 > span.comment-info > a").text();
                    String date = div.selectFirst("h3 > span.comment-info > span.comment-time").text();
                    Element rating = div.selectFirst("h3 > span.comment-info > span.rating");
                    String star = null;
                    if (rating != null) {
                        star = rating.attr("class");
                        star = star.substring(7, 9);
                    }
                    String vote = div.selectFirst("h3 > span.comment-vote > span.votes").text();
                    String comment = div.selectFirst("div.comment > p").text();
                    data.put("author", author);
                    data.put("date", date);
                    if (star != null) {
                        data.put("star", star);
                    }
                    data.put("vote", vote);
                    data.put("comment", comment);
                    return data;
                })
                .collect(Collectors.toList());

        return new Data(links, results);
    }
}
