package com.example.pacong.gecco.utils;

import com.example.pacong.gecco.bean.ProductDetail;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by xuwencong on 2019/4/9
 */
@PipelineName("productDetailPipeline")
public class ProductDetailPipeline implements Pipeline<ProductDetail> {
    @Override
    public void process(ProductDetail productDetail) {
        File file = new File("result.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter;
            fileWriter = new FileWriter("result.txt", true);
            Objects.requireNonNull(fileWriter).write(RegexUtils.getZHText(productDetail.getContent()));
            Objects.requireNonNull(fileWriter).flush();
            Objects.requireNonNull(fileWriter).close();
        } catch (IOException e) {
            System.out.println("fileWriter.write failed: " + e);
        }
    }


}
