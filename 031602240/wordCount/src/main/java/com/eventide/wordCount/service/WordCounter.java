package com.eventide.wordCount.service;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 单词计数器，包括计算文件内单词总数.
 * 单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写.
 *
 * @author xyy
 * @version 1.0 2018/9/11
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
        String in = null;
        long wordNum = 0;
        String regex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
        String delim = " ,.!?-=*/()[]{}\\\"\\';:\\n\\r\\t“”‘’·——…（）【】｛｝\\0";

        //读入文件
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("找不到此文件");
            e.printStackTrace();
        }
        if (inputStreamReader != null) {
            bufferedReader = new BufferedReader(inputStreamReader);
        }
        //计算单词数
        try {
            while ((in = bufferedReader.readLine()) != null) {
                in = in.toLowerCase();
                //根据分隔符分割
                StringTokenizer tokenizer = new StringTokenizer(in, delim);
                while (tokenizer.hasMoreTokens()) {
                    if (tokenizer.nextToken().matches(regex)) {
                        wordNum++;
                    }
                }

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
