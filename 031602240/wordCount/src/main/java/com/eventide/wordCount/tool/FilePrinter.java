package com.eventide.wordCount.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 输出至文件工具类，包括写入字符数、单词数、行数、词频.
 *
 * @author xyy
 * @version 1.0 2018/9/12
 * @since 2018/9/11
 */
public class FilePrinter {
    /**
     * 输出结果至文件.
     *
     * @param fileName 文件名
     * @param charNum  字符数
     * @param wordNum  单词数
     * @param lineNum  行数
     * @param wordList 单词词频列表
     */
    public static void printToFile(String fileName, long charNum, long wordNum, long lineNum,
                                   ArrayList<HashMap.Entry<String, Long>> wordList) {
        File file = new File(fileName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //输出字符数
            bufferedWriter.write("characters: " + charNum + "\n");
            //输出单词数
            bufferedWriter.write("words: " + wordNum + "\n");
            //输出行数
            bufferedWriter.write("lines: " + lineNum + "\n");

            //输出单词词频前10
            int size = wordList.size();
            if (size >= 10) {
                for (int i = 0; i < 10; i++) {
                    bufferedWriter.write("<" + wordList.get(i).getKey() + ">: "
                            + wordList.get(i).getValue() + "\n");
                }
            } else {
                for (HashMap.Entry<String, Long> map : wordList) {
                    bufferedWriter.write("<" + map.getKey() + ">: " + map.getValue() + "\n");
                }
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
