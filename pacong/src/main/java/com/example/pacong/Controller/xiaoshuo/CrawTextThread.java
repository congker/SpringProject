package com.example.pacong.Controller.xiaoshuo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * Created by xuwencong on 2019/4/4
 */
public class CrawTextThread extends Thread {
    List<String> UrlList;

    public CrawTextThread(List<String> urlList) {
        this.UrlList = urlList;
    }

    String rule = "";
    String rule_title = "h1";
    String rule_content = "content";

    private static String PATH = "D:\\JSOUP\\";

    /**
     * 创建文件
     *
     * @param fileName
     * @return
     */
    private static void createFile(File fileName) {
        try {
            if (!fileName.exists()) {
                fileName.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void writeTxtFile(String content, File fileName) {
        FileOutputStream o;
        try {
            o = new FileOutputStream(fileName);
            o.write(content.getBytes("UTF-8"));
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        currentThread().setName("一个都别跑:");

        String title;

        String content;

        for (String url : UrlList) {
            try {

                Document document = Jsoup.connect(url).timeout(6000).get();
                title = document.select("h1").toString();
                content = document.select("#content").html();

                System.out.println("线程:" + currentThread().getName() + "爬取URL—>" + url);
                File file = new File(PATH + title.replaceAll("<h1>", "").replaceAll("</h1>", "") + ".txt");
                createFile(file);
                System.out.println("创建文件:" + file.getPath());
                writeTxtFile(FileterHtml(content), file);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static String FileterHtml(String str) {
        return str.replaceAll(" ", "").replaceAll("<br>", "\r\n");

    }

    public static void main(String[] args) {
        try {
            CrawlText.getText("http://www.biquge.com.tw/0_66/");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
