package com.example.pacong.geccoBlog;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

/**
 * Created by xuwencong on 2019/4/10
 */
@PipelineName(value = "blogPipelines")
public class BlogPipelines implements Pipeline<Blog> {
    @Override
    public void process(Blog blog) {
        System.out.println("content:" + blog.getContent());
    }
}
