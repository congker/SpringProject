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
public class CommonUtil {

    private static String USER_AGENT = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0";
    private static Integer TIME_OUT = 3000;

    public static File createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            boolean isDir = file.mkdirs();
            System.out.println("isDir:" + isDir);
        }
        return file;
    }

    //获取图片集
    public static Elements getElements(String url, int index) throws IOException {
        Document doc = Jsoup.connect(url + index).userAgent(USER_AGENT).timeout(TIME_OUT).data("pager_offset", index + 1 + "").post();
        return doc.select("img");
    }


    //下载图片
    public static void getAndDownloadImage(Element element, String picUrl) throws IOException {
        String src = element.absUrl("src");
        URL url = new URL(src);
        InputStream in = createInputStream(url);
        OutputStream out = createOutputStream(createFile(picUrl), getImageName(src));
        //写入文件到本地
        writeFile(in, out);
        //关闭输入流/输出流
        closeStream(in, out);
    }


    //获取图片名称
    public static String getImageName(String imageSrc) {
        int indexName = imageSrc.lastIndexOf("/");
        return imageSrc.substring(indexName, imageSrc.length());
    }

    //创建输入流
    public static InputStream createInputStream(URL url) throws IOException {
        return url.openStream();
    }

    //创建输出流
    public static OutputStream createOutputStream(File files, String name) throws IOException {
        return new BufferedOutputStream(new FileOutputStream(files + name));
    }

    //写入文件
    public static void writeFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        for (int index; (index = inputStream.read()) != -1; ) {
            outputStream.write(index);
        }
    }

    //关闭流
    public static void closeStream(InputStream in, OutputStream out) throws IOException {
        in.close();
        out.close();
    }

}
