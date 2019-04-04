package com.example.pacong.Controller.Excel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuwencong on 2019/4/4
 */
public class ReadCsv {
    /*
     * author:命运的信徒 date:2019-1-15 arm:java读取.csv文件里面的所有内容
     */
    public static void main(String[] args) {
        // 1. .csv文件的路径。注意只有一个\的要改成\\
        File csv = new File("E:\\Download\\programImage\\account_safety_status.csv"); // CSV文件路径
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        String everyLine;
        try {
            List<String> list = new ArrayList<>();

            while (br != null && (line = br.readLine()) != null) {
                String item[] = line.split(",");
                if(item.length>1){
                    everyLine = item[1];
                    System.out.println(everyLine);
                    list.add(everyLine);
                }
            }
            System.out.println("数量：" + list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
