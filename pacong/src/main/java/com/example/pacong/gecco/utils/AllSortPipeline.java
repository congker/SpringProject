package com.example.pacong.gecco.utils;

import com.example.pacong.gecco.bean.AllSort;
import com.example.pacong.gecco.bean.Category;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuwencong on 2019/4/9
 */
@PipelineName("allSortPipeline")
public class AllSortPipeline implements Pipeline<AllSort> {

    @Override
    public void process(AllSort allSort) {
        List<Category> categorys = new ArrayList<>();
        categorys.addAll(allSort.getInternet());
        categorys.addAll(allSort.getElectric());
        categorys.addAll(allSort.getMobileInternet());
        categorys.addAll(allSort.getNetGame());
        categorys.addAll(allSort.getNetMarket());
        for (Category category : categorys) {
            List<HrefBean> hrefs = category.getCategorys();
            for (HrefBean href : hrefs) {
                System.out.println("title: " + href.getTitle() + " url: " + href.getUrl());
                String url = href.getUrl();
                HttpRequest currRequest = allSort.getRequest();
                SchedulerContext.into(currRequest.subRequest(url));
            }
        }
    }
}
