import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        //定义开始时间
        long start_time = System.currentTimeMillis();

        File fileIn = null;
        if(args.length>0){
            fileIn = new File(args[0]);
        }else {
            fileIn = new File("input.txt");
            System.out.println("未输入文件名,使用默认名:input.txt");
//            System.exit(0);
        }
        //输出到result.txt
        String out_address= "result.txt";
        FileOutputStream fileOut = new FileOutputStream(out_address);


        //实例话Count类
        Count count = new Count(fileIn);

        //获取字符数
        int charactersNum = count.charactersCount();
        //读取单词数
        int wordNum = count.wordCount();
        //获取有效行数
        int lineNum = count.lineCount();
        List<HashMap.Entry<String, Integer>> m = count.getWords();

        String result = "characters:" + charactersNum + "\r\n" +
                "words:" + wordNum      + "\r\n" +
                "lines:" + lineNum      + "\r\n" ;
        int j = 0;
        String t = new String();
        if(m != null){
            if(m.size()!=0){
                for(;((j<10)&&(j<m.size()));j++){
                    t = "<"+ m.get(j).getKey() + ">:" + m.get(j).getValue();
                    result += t + "\r\n";
                }
            }
        }
        fileOut.write(result.getBytes());
        fileOut.close();

        //定义结束时间
        long end_time = System.currentTimeMillis();
        System.out.println(end_time-start_time);

    }
}
