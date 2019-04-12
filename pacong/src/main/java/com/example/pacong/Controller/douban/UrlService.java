package com.example.pacong.Controller.douban;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xuwencong on 2019/4/4
 */
//@Service
public class UrlService {
    private String baseUrl;
    private Queue<String> newUrls = new LinkedList<>();
    private Set<String> oldUrls = new HashSet<>();

    UrlService(String baseUrl, String rootUrl) {
        this(baseUrl, Collections.singletonList(rootUrl));
    }

    private UrlService(String baseUrl, List<String> rootUrls) {
        if (baseUrl == null || rootUrls == null || rootUrls.isEmpty()) {
            return;
        }
        this.baseUrl = baseUrl;
        this.appendNewUrls(rootUrls); // 添加待抓取URL列表
    }

    /**
     * 追加待抓取URLs
     *
     * @param urls
     */
    void appendNewUrls(List<String> urls) {
        newUrls.addAll(urls.stream()      // 添加待抓取URL列表
                .filter(url -> url.startsWith(baseUrl)) // 过滤指定URL
                .map(url -> url.replace("&status=P", "")) // 处理URL中的多余参数(&status=P，有的链接有，有的没有，为避免重复，统一去除，去除后并不影响)
                .filter(url -> !newUrls.contains(url) && !oldUrls.contains(url)) // 过滤重复的URL
                .collect(Collectors.toList())); // 返回处理过后的URL列表
    }

    boolean hasNewUrl() {
        return !this.newUrls.isEmpty();
    }

    /**
     * 取出一个新URL，这里简化处理了新旧URL状态迁移过程，取出后即认为成功处理了(实际情况下需要考虑各种失败情况和边界条件)
     *
     * @return
     */
    String getNewUrl() {
        String url = this.newUrls.poll();
        this.oldUrls.add(url);
        return url;
    }
}
