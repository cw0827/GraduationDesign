package com.caiw;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.Analysis;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by 蔡维 in 17:00 2018/3/7
 */
public class Dame2 {

    public static void main(String[] args) throws IOException {
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
        String content = strings[2];
        Result parse = ToAnalysis.parse(content);
        List<Term> terms = parse.getTerms();
        for (Term term:terms) {
            System.out.println(term.getName()+'\t'+term.getNatureStr());
        }

    }

}
