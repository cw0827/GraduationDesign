package com.caiw.utils;


import com.caiw.entity.Comment;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;

/**
 * Created by 蔡维 in 11:52 2018/4/10
 */
public class KafkaUtil {

    /**
     * 生产者胜生产消息
     * @param commentList 评论列表
     */
    public static void kafkaProduce(List<Comment> commentList){
        Producer<String,String> producer = new KafkaProducer<>(PropertiesUtil.getProduceProperties());
        for(Integer i=0;i<commentList.size();i++){
//            System.err.println(commentList.get(i).toString());
            producer.send(new ProducerRecord<>("cwTest002",i.toString(),commentList.get(i).toString()));
        }
        producer.flush();
        producer.close();
    }

}
