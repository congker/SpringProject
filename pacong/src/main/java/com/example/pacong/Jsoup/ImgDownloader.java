package com.example.pacong.Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by xuwencong on 2019/4/18
 */
public class ImgDownloader {
    private static final String PIC_PATH = "E:/lofter/girl";
    //    private static final String PIC_URL = "http://www.lofter.com/tag/%E6%88%8F%E7%B2%BE%E4%B8%BB%E5%AD%90%E4%BA%89%E5%AE%A0%E8%AE%B0?act=qbdashboardside_20121217_01";
    private static final String PIC_URL = "http://www.lofter.com/tag/%E5%88%B6%E6%9C%8D?act=qbdashboardside_20121217_01";


    public static void main(String[] args) throws IOException {
        downLoadImage();
    }

    private static void downLoadImage() throws IOException {
        CommonUtil.createFile(PIC_PATH);
        Document doc = Jsoup.connect(PIC_URL).get();
        Elements links = doc.select("img[src]");

        for (Element element : links) {
            String src = element.attr("src");
            String imageName = src.substring(src.lastIndexOf("/") + 1, src.length());
            String fileName = imageName.split("[?]")[0];
            //建立网络连接
            URL url = new URL(src);
            try {
                //打开网络连接，获取输入流
                InputStream inputStream = url.openConnection().getInputStream();

                OutputStream outputStream = new FileOutputStream(new File(PIC_PATH, fileName));
                byte[] buf = new byte[1024];
                int len;

                while ((len = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
