package com.eventide.wordCount.service;

import java.io.*;

/**
 * 行数计数器，包括计算文件总行数.
 *
 * @author xyy
 * @version 1.0 2018/9/11
 * @since 2018/9/11
 */
public class LineCounter {
    /**
     * 读取并计算文件行数.
     *
     * @param fileName 文件名
     * @return 总行数
     */
    public static long countLine(String fileName) {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String in = null;
        long lineNum = 0;

        //读入文件
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("LineCounter找不到此文件");
            e.printStackTrace();
        }
        if (inputStreamReader != null) {
            bufferedReader = new BufferedReader(inputStreamReader);
        }
        //计算行数
        try {
            while ((in = bufferedReader.readLine()) != null) {
                if (!in.equals("")) lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineNum;
    }
}
