## GraduationDesign

描述：蔡维毕业设计

### 题目： 基于大数据平台的股评信息文本挖掘研究



### 主要功能介绍
* 爬取数据[地址](http://stock.quote.stockstar.com/stockinfo_info/comment.aspx?code=900905&pageid=3)
* 地址解析：`http://stock.quote.stockstar.com/stockinfo_info/comment.aspx?code="+股票代码+"&pageid="+页数`
* 爬取工具: `Java,Jsoup`
* 爬取数据去向
    * `txt`文件
    * `MySQL`
* 对数据进行分词
    * 分词工具 `Word`
   
   
   
### 数据库 MySQL:120.79.24.24  
* 数据库：`graduation_design `
    * 表：
        * `comment`:id,stock_code,comment,create_time
        * `stock_term` : id,stock_code,article_id,sentence_id,stockTerm_id,stock_term,nature,create_time
        
### 分词工具
   * `Word`分词
        * 版本1.3
        
        
### 股票相关术语
* 压力线：当股价上涨到某价位附近时，股价就会停止上涨，甚至回落。压力线起阻止股价继续上涨的作用。
* 支撑线：股价下跌时的关卡。
* 关卡：一般将整数位或黄金分割位或股民习惯上的心理价位称之为关卡。
* 突破：股价冲过关卡，一般指向上突破。跌破：股价冲过关卡向下突破。
* 反转：股价朝原来趋势的相反方向移动，分为向上反转和向下反转。
* 回档：在股市上，股价呈不断上涨趋势，终因股价上涨速度过快而反转回跌到某一价位这一调整现象称为回档。一般来说，股票的回档幅度要比上涨幅度小，通常是反转回跌到前一次上涨幅度的三分之一左右时又恢复原来的上涨趋势。
* 探底：寻找股价最低点过程，探底成功后股价由最低点开始翻升。


### 数据库相关操作
* 分析名词、动词的频率
    * SQL：
    ```SQL
    select count(1) from stock_term;
    select count(1) from stock_term where nature = '名词';
    select count(1) from stock_term where nature = '动词';
    ```
    > 频率=单独的次数/总数
    
    ![](images/1.png)
    ![](images/2.png)
    ![](images/3.png)
* 词性路径模板统计
    * SQL：
    ```SQL
    //得出词性路径模板数
    select 
    b.comment_id,b.sentence_id,count(1) as `num`,b.sentence_nature,GROUP_CONCAT("-",b.sentence)
    from 
    (select 
    GROUP_CONCAT(a.nature) as `sentence_nature` ,GROUP_CONCAT(a.stock_term) as `sentence`,a.comment_id,a.sentence_id
    from 
    (SELECT 
    comment_id,sentence_id,nature,stock_term,stock_code 
    from stock_term s 
    where s.stock_code = "+股票代码+"
    ORDER BY s.comment_id,s.sentence_id,s.stock_term_id ) as `a`
    GROUP BY a.comment_id,a.sentence_id) as `b` 
    GROUP BY b.sentence_nature HAVING `num`>70 ORDER BY num desc;
  
  //得出总句子数
  select COUNT(1)
  FROM
  (select COUNT(1) as c from stock_term GROUP BY comment_id,sentence_id) as cc
    ```
    > 频率=词性模板对应的数/总句子数，筛选了一下，将频数小于70的去掉。
    
    ![](images/4.png)
    ![](images/5.png)
    
* 拿出筛选过后的词，进行下一步的筛选


### 特征词的过滤
* 在从训练语料中出现次数最多的前100个特征词中，手工挑选出股票6个典型属性。并加入产品类别名称“股票”。将其组成领域性特征词的种子集合Seeds，PMI-IR计算公式如下：

![](images/PMI-IR.png)


