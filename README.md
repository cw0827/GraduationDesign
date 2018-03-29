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
        
        
### 股票相关术语
* 压力线：当股价上涨到某价位附近时，股价就会停止上涨，甚至回落。压力线起阻止股价继续上涨的作用。
* 支撑线：股价下跌时的关卡。
* 关卡：一般将整数位或黄金分割位或股民习惯上的心理价位称之为关卡。
* 突破：股价冲过关卡，一般指向上突破。跌破：股价冲过关卡向下突破。
* 反转：股价朝原来趋势的相反方向移动，分为向上反转和向下反转。
* 回档：在股市上，股价呈不断上涨趋势，终因股价上涨速度过快而反转回跌到某一价位这一调整现象称为回档。一般来说，股票的回档幅度要比上涨幅度小，通常是反转回跌到前一次上涨幅度的三分之一左右时又恢复原来的上涨趋势。
* 探底：寻找股价最低点过程，探底成功后股价由最低点开始翻升。