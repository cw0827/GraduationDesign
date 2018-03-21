package com.caiw;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.*;
import java.util.*;

/**
 * Created by caiw on 16-12-9.
 * 分词初步
 */
public class AnsjTest {

    public static HashMap<String,Integer> test() throws IOException {
        //只关注这些词性的词
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};

        FileReader fr = null;
        try {
            fr = new FileReader(new File("E:\\成都大学\\毕业设计\\data\\124份年报揭示四大机构新动向 绩优成长股受青睐占比近九成.txt"));
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
        String s = buffer.toString().split("\n")[2];
        Result result = ToAnalysis.parse(s); //分词结果的一个封装，主要是一个List<Term>的terms
//        System.out.println(result.getTerms());

        List<Term> terms = result.getTerms(); //拿到terms
//        System.out.println(terms.size());
        HashMap<String,Integer> wordMap = new HashMap<>();
        for(int i=0; i<terms.size(); i++) {
            String word = terms.get(i).getName(); //拿到词
            String natureStr = terms.get(i).getNatureStr(); //拿到词性
            if(expectedNature.contains(natureStr)) {
                wordMap.merge(word, 1, (a, b) -> a + b);
            }
        }
        return wordMap;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> test = test();
        Set<String> strings = test.keySet();
        for (String s:strings) {
            System.out.println(s+"\t\t\t"+test.get(s));
        }
    }
}