package com.caiw.utils;


import com.caiw.entity.Comment;
import com.caiw.job.GetData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Random;

/**
 * Created by 蔡维 in 11:52 2018/4/10
 */
public class KafkaUtil {

    /**
     * 生产者胜生产消息
     * @param comment 评论
     */
    public static void kafkaProduce(Comment comment){
        GetData.producer.send(new ProducerRecord<>("cwTest002",new Random(10).toString(),comment.toString()));
        GetData.producer.flush();
    }

}
