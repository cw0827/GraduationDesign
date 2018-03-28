## GraduationDesign

描述：蔡维毕业设计

### 题目： 基于大数据平台的股评信息文本挖掘研究



### 主要功能介绍
* 爬取数据[地址](http://stock.quote.stockstar.com/stockinfo_info/comment.aspx?code=900905&pageid=3)
* 地址解析：http://stock.quote.stockstar.com/stockinfo_info/comment.aspx?code="+股票代码+"&pageid="+页数
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