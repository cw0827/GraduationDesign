package com.caiw;

import com.huaban.analysis.jieba.JiebaSegmenter;
import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.Analysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.PartOfSpeech;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.tagging.PartOfSpeechTagging;
import org.junit.Test;

import java.io.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

/**
 * Created by 蔡维 in 17:00 2018/3/7
 */
public class Dame2 {

    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader(new File("E:\\成都大学\\毕业设计\\data\\A股高现金分红公司“画像” 具备这些特征.txt"));
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
//        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
//        List<String> strings1 = jiebaSegmenter.sentenceProcess(content);
//        for (String str:strings1) {
//            System.out.println(str);
//            System.out.println();
//        }
//        SegmentationAlgorithm segmentationAlgorithm = new SegmentationAlgorithm();
        //word   Text word.getPartOfSpeech().getDes()
//        List<Word> seg = WordSegmenter.seg(content);
//        PartOfSpeechTagging.process(seg);
//        for (Word word:seg){
//            System.out.println(word.getAcronymPinYin()+"1\t"+word.getFullPinYin()+"2\t"+word.getText()+"3\t"+word.getAntonym()+"4\t"+word.getFrequency()
//                    +"5\t"+word.getPartOfSpeech().getDes()+"\t"+word.getPartOfSpeech().getPos()+"6\t"+word.getSynonym()+"7\t"+word.getWeight());
//        }
    }


    @Test
    public  void Test1() {
//        System.out.println(new Date(System.currentTimeMillis()));
//        List<Word> words = WordSegmenter.seg("小米手机好");
//
//        System.out.println(words);
//
//        PartOfSpeechTagging.process(words);
//
//        System.out.println(words);

//                Result parse = ToAnalysis.parse("好东西");
//        List<Term> terms = parse.getTerms();
//        for (Term term:terms) {
//            System.out.println(term.getName()+'\t'+term.getNatureStr());
//        }
    }

}
