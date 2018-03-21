package com.caiw;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.*;
import java.util.Collection;

/**
 * Created by 蔡维 in 17:00 2018/3/7
 */
public class Dame2 {

    public static void main(String[] args) throws IOException {
        KeyWordComputer kwc = new KeyWordComputer(5);

        FileReader fr = null;
        try {
            fr = new FileReader(new File("E:\\成都大学\\毕业设计\\data\\3月6日传闻即将涨停个股.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        StringBuilder buffer = new StringBuilder();
        while(line != null){
            buffer.append(line);
            buffer.append("\n");
            line = br.readLine();
        }
        String[] strings = buffer.toString().split("\n");
//        String title = strings[0];
        String content = strings[2];
//        Collection<Keyword> result = kwc.computeArticleTfidf(title, content);
        System.out.println(ToAnalysis.parse(content));
    }

}
