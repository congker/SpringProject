package com.example.pacong.Controller.Excel;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by xuwencong on 2019/4/4
 */
public class Readers {
    /**
     * author:命运的信徒
     * date:2019/1/15
     * arm:获取.csv文件的某一行某一列数据的内容
     */
    private void test(int row, int col) {
        try {
            BufferedReader reade = new BufferedReader(new FileReader("E:\\Download\\programImage\\account_safety_status.csv"));//换成你的文件名

            String line;
            int index = 0;
            while ((line = reade.readLine()) != null) {

                String item[] = line.split("TJN");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                if (index == row - 1) {
                    if (item.length >= col - 1) {
                        String urs = item[col - 1];//这就是你要的数据了
                        System.out.println(urs);
                    }
                }
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Readers test = new Readers();
        test.test(3, 3);
    }

}
