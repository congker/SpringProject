package com.example.pacong.Controller.douban;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by xuwencong on 2019/4/1
 * <p>
 * 爬虫主程序
 */
@Slf4j
public class Main {


    @Resource
    private UrlService urlService;

    //队列管理
    private UrlManager manager;

    //下载器
    private Downloader downloader;

    //页面解析器
    private PageParser parser;

    //数据处理器
    private DataProcessor processor;

    private final static String BASE_URL = "https://movie.douban.com/subject/26752088/comments";
    private final static String ROOT_URL = BASE_URL + "?start=0&limit=20&sort=new_score&status=P";

    private Main(UrlManager manager, Downloader downloader, PageParser parser, DataProcessor processor) {
        this.manager = manager;
        this.downloader = downloader;
        this.parser = parser;
        this.processor = processor;
    }


    @Test
    public void startCrawler() {
        // 构建爬虫并启动爬虫，这里仅作最小化演示，程序健壮性、扩展性等暂不考虑
        Main crawler = new Main(new UrlManager(BASE_URL, ROOT_URL),
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
                //获取url
                String url = manager.getNewUrl();
                if (url == null) {
                    break;
                }
                // 以原子方式将当前值加 1，并返回加1后的值 等价于"++num"
                counter.incrementAndGet();

                String html = downloader.download(url);
                PageParser.Data data = parser.parse(url, html);

                if (data == null) {
                    continue;
                }
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
