package com.eventide.wordCount.service;

import java.io.*;

/**
 * 单词计数器，包括计算文件内单词总数.
 * 单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写.
 *
 * @author xyy
 * @version 1.1 2018/9/19
 * @since 2018/9/11
 */
public class WordCounter {
    /**
     * 读取并计算文件单词数.
     *
     * @param fileName 文件名
     * @return 总单词数
     */
    public static long countWord(String fileName) {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        int in = 0;
        long wordNum = 0;
        char temp;
        int state = 0;

        //读入文件
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("WordCounter找不到此文件");
            e.printStackTrace();
        }
        if (inputStreamReader != null) {
            bufferedReader = new BufferedReader(inputStreamReader);
        }
        //计算单词数
        try {
            while ((in = bufferedReader.read()) != -1) {
                //根据分隔符分割
                temp = (char) in;
                if ((temp >= 65) && (temp <= 90)) {
                    temp += 32;
                }

                //自动机状态转移
                switch (state) {
                    case 0: {
                        if ((temp >= 97) && (temp <= 122)) {
                            state = 1;
                        }
                        break;
                    }
                    case 1: {
                        if ((temp >= 97) && (temp <= 122)) {
                            state = 2;
                        } else {
                            state = 0;
                        }
                        break;
                    }
                    case 2: {
                        if ((temp >= 97) && (temp <= 122)) {
                            state = 3;
                        } else {
                            state = 0;
                        }
                        break;
                    }
                    case 3: {
                        if ((temp >= 97) && (temp <= 122)) {
                            state = 4;
                        } else {
                            state = 0;
                        }
                        break;
                    }
                    case 4: {
                        if (!((temp >= 97) && (temp <= 122)) || ((temp >= '0') && (temp <= '9'))) {
                            wordNum++;
                            state = 0;
                        }
                        break;
                    }
                }
            }
            if (state == 4) {
                wordNum++;
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
        return wordNum;
    }
}
