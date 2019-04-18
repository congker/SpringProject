package com.example.pacong.Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;

/**
 * Created by xuwencong on 2019/4/18
 */
public class Lofter1 {
    private static final String URL = "http://www.lofter.com/?p=";
    private static final String PIC_PATH = "E:/lofter/image";


    public static void main(String[] args) {
        System.out.println("开始下载图片");
        for (int i = 0; i < 50; i++) {
            try {
                Elements img = CommonUtil.getElements(URL, i);
                for (Element element : img) {
                    CommonUtil.getAndDownloadImage(element, PIC_PATH);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("图片下载完成！");
    }


}
