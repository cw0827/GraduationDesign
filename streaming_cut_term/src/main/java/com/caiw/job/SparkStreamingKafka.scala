package com.caiw.job

import java.sql.Date
import java.util
import java.util.{ArrayList, List, UUID}

import com.caiw.dao.impl.StockTermDaoImpl
import com.caiw.entity.StockTerm
import org.ansj.domain.{Result, Term}
import org.ansj.splitWord.analysis.ToAnalysis
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
  *
  * Created by 蔡维 in 14:54 2017/12/15
  */
object SparkStreamingKafka {
  def main(args: Array[String]): Unit = {

    val kafkaProperties=Map[String,Object](
//      "bootstrap.servers"-> "192.168.200.11:9092",
      "bootstrap.servers"-> "120.79.24.24:9092",
      "key.deserializer"->classOf[StringDeserializer],
      "value.deserializer"->classOf[StringDeserializer],
      "group.id"->"caiW",
      "auto.offset.reset"->"latest",
      "enable.auto.commit"->(true:java.lang.Boolean)
    )
    val conf = new SparkConf().setMaster("local[1]").setAppName("caiW")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val ssc = new StreamingContext(sc,Seconds(5))

    val topics = Array("cwTest003")
    val kafkaRDD = KafkaUtils.createDirectStream(ssc,PreferConsistent,Subscribe[String,String](topics,kafkaProperties))

    kafkaRDD.map(_.value().split("\t")).foreachRDD{
      rdd =>
        rdd.foreach{
          comment =>
            val stockTermList = new util.ArrayList[StockTerm]
            //句子id,最开始为1。
            var sentenceId = 1
            //对文章进行分句
            println(comment(2))
            val splitOne = comment(2).split(";")
            for (s <- splitOne) {
              val stringTwo = s.split(",")
              for (str <- stringTwo) {
                //对词进行分词
                val parse = ToAnalysis.parse(str)
                val words = parse.getTerms
                var i = 0
                while (i < words.size) {
                  val stockTerm = new StockTerm
                  stockTerm.setId(UUID.randomUUID.toString)
                  stockTerm.setStockCode(comment(1))
                  stockTerm.setCommentId(comment(0))
                  stockTerm.setSentenceId(sentenceId)
                  stockTerm.setStockTerm(words.get(i).getName)
                  stockTerm.setStockTermId(i + 1)
                  stockTerm.setNature(words.get(i).getNatureStr)
                  stockTerm.setCreateTime(new Date(System.currentTimeMillis))
                  stockTermList.add(stockTerm)
                  i += 1
                }
                sentenceId += 1
              }
            }
            val stockTermDao = new StockTermDaoImpl
            stockTermDao.saveStockTerm(stockTermList)
        }
    }





    ssc.start()
    ssc.awaitTermination()
  }
}
