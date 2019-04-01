package com.example.pacong.Controller;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by xuwencong on 2019/4/1
 */
@Slf4j
public class Crawler {
    private UrlManager manager;
    private Downloader downloader;
    private PageParser parser;
    private DataProcessor processor;

    private Crawler(UrlManager manager, Downloader downloader, PageParser parser, DataProcessor processor) {
        this.manager = manager;
        this.downloader = downloader;
        this.parser = parser;
        this.processor = processor;
    }

    public static void main(String[] args) {

        // 豆瓣影评URL部分不变，变化的只有参数部分
        final String BASE_URL = "https://movie.douban.com/subject/26752088/comments";
        final String ROOT_URL = BASE_URL + "?start=0&limit=20&sort=new_score&status=P";

        // 构建爬虫并启动爬虫，这里仅作最小化演示，程序健壮性、扩展性等暂不考虑
        Crawler crawler = new Crawler(new UrlManager(BASE_URL, ROOT_URL),
                new Downloader(),
                new PageParser(),
                new DataProcessor("192.168.0.105"));
        long urls = crawler.start();
        log.info("任务执行完成，共爬取 {} 个URL", urls);

    }

    /**
     * 启动爬虫，任务执行完成后，返回处理URL数量
     *
     * @return
     */
    private long start() {
        final AtomicLong counter = new AtomicLong();
        while (manager.hasNewUrl()) {
            try {
                String url = manager.getNewUrl();
                if (url == null) break;
                counter.incrementAndGet();
                String html = downloader.download(url);
                PageParser.Data data = parser.parse(url, html);
                if (data == null) continue;
                if (data.getLinks() != null) {
                    manager.appendNewUrls(data.getLinks());
                }
                if (data.getResults() != null) {
                    processor.process(data.getResults());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return counter.get();
    }

}
