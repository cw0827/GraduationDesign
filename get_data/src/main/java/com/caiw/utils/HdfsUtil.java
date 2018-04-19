package com.caiw.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * hdfs的相关操作
 */
public class HdfsUtil {
    private FileSystem fs;
    private String fileName;
    public HdfsUtil(String fileName){
            this.fileName=fileName;
    }

    /**
     * 简单封装一个得到FileSystem的公共方法
     * @return
     */
    private FileSystem getFileSystem(){
        try {
            Configuration conf = new Configuration();
            conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
            conf .set("dfs.client.block.write.replace-datanode-on-failure.policy" ,"NEVER" );
            conf .set("dfs.client.block.write.replace-datanode-on-failure.enable" ,"true" );
            return FileSystem.get(new URI("hdfs://cw01:9000"),conf,"root");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断文件是否存在，如果不存在进行创建
     */
    public void existsFile(){
        Path filePath=new Path(fileName);
        try {
            if(!fs.exists(filePath)){
                FileSystem fs2=getFileSystem();
                fs2.create(filePath);
                fs2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向HDFS中写入一行数据，以追加的方式进行
     * @param message
     */
    public void writeData(String message){
        fs = getFileSystem();
        existsFile();
        FSDataOutputStream fos= null;
        try {
            fos = fs.append(new Path(fileName));
            //将得到的字符串转为输入流
            ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(message.getBytes());
            IOUtils.copyBytes(tInputStringStream,fos,4096);
            tInputStringStream.close();
            fos.close();
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
