import java.io.*;
import java.util.*;

public class Count {
    private static String content = new String();
    private static Map  ma= new HashMap();
    private static List<HashMap.Entry<String, Integer>> words = null;

    public static List<HashMap.Entry<String, Integer>> getWords() {
        return words;
    }

    public Count(File fileIn) {
        try{
            //读取文件
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fileIn),"utf8"));

            StringBuffer contents = new StringBuffer();
            int byte_char = -1;
            //开始依次读取字节码
            while ((byte_char = bf.read()) >= 0) {
                contents.append((char)byte_char);
            }
            content = contents.toString();
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public int charactersCount(){
        String clearcontent = content.replaceAll("\r\n","\n");
        int charactersnum = clearcontent.length();
        return charactersnum;
    }


    public int lineCount(){
        Boolean flag = false;
        int linenum = 0;
        int i = 0;
        for (;i<content.length();i++){
            if(content.charAt(i) != '\r' && content.charAt(i) != '\n' && content .charAt(i) != ' ' ){
                flag = true;
            }else if(content.charAt(i) == '\n'){
                if(flag){
                    linenum++;
                    flag = false;
                }
            }
        }
        if(flag){
            linenum++;
        }
        return linenum;
    }

    public int wordCount(){
        int wordNum = 0;
        String regex = "[^0-9A-Za-z]";
        String contentString  = content.toLowerCase().replaceAll(regex,"|");
        String[] contents = contentString.split("\\|");
        int i = 0;
        for (; i <contents.length ; i++ ) {
            if(contents[i].length()>=4){
                if(Character.isLetter(contents[i].charAt(0))){
                    if(Character.isLetter(contents[i].charAt(1))){
                        if(Character.isLetter(contents[i].charAt(2))){
                            if(Character.isLetter(contents[i].charAt(3))){
                                wordNum++;
                                Maps(ma,contents[i]);
                            }
                        }
                    }

                }
            }
        }
        if(!ma.isEmpty()){
            words = Sort(ma);
        }
        return wordNum;
    }

    //更新字典的函数
    public static Map Maps(Map m, String s){
        if(m.containsKey(s)){
            int n = (int)m.get(s);
            n++;
            m.put(s,n);
        }else{
            m.put(s,1);
        }
        return m;
    }

    //单词排序
    public static List<HashMap.Entry<String, Integer>> Sort(Map m){
        List<HashMap.Entry<String, Integer>> wordList = new ArrayList<HashMap.Entry<String, Integer>>(m.entrySet());

        Comparator<Map.Entry<String, Integer>> com = new Comparator<Map.Entry<String, Integer>>(){

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue()==o2.getValue())
                    return o1.getKey().compareTo(o2.getKey());//字典序
                return o2.getValue()-o1.getValue();//从大到小
            }
        };
        wordList.sort(com);
        return wordList;
    }


}
