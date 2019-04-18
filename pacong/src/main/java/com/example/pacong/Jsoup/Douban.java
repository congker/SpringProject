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
public class Douban {
    private static final String url = "http://www.dbmeinv.com/?p=";
    private static final String picPath = "E:/douban/girl";
    private static String USER_AGENT="Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0";

    public static void main(String[] args) {
        System.out.println("开始下载图片");
        for (int i = 0; i < 50; i++) {
            try {
                Document doc = Jsoup.connect(url+i).userAgent(USER_AGENT).timeout(3000).data("pager_offset", i+1+"").post();
                Elements img = doc.select("img");
                for (Element ele : img) {
                    String src = ele.absUrl("src");
                    //System.out.println(src);
                    getImage(src);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("图片下载完成！");
    }


    private static void getImage(String src) {
        int indexName = src.lastIndexOf("/");
        String name = src.substring(indexName, src.length());
        InputStream in = null;
        OutputStream out = null;
        try {
            URL url = new URL(src);
            in = url.openStream();

            //创建文件夹
            File files = new File(picPath);
            if(!files.exists())
                files.mkdirs();

            out = new BufferedOutputStream(new FileOutputStream(files+name));
            for(int b;(b=in.read())!=-1;)
                out.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                assert out != null;
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
