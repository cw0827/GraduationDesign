-- 每个层次分数按从高到低排序
SELECT ss.stock_code,ss.mar_score,s.stock_name,s.industry 
from stock_score ss 
LEFT JOIN stock s 
on ss.stock_code=s.stock_code 
ORDER BY ss.mar_score DESC;

SELECT ss.stock_code,ss.mac_score,s.stock_name,s.industry 
from stock_score ss 
LEFT JOIN stock s 
on ss.stock_code=s.stock_code 
ORDER BY ss.mac_score DESC;

SELECT ss.stock_code,ss.mic_score,s.stock_name,s.industry 
from stock_score ss 
LEFT JOIN stock s 
on ss.stock_code=s.stock_code 
ORDER BY ss.mic_score DESC;



-- 算出各个股票的得分平均数  然后按分数排名
SELECT ss.id,ss.stock_code,(mac_score+mic_score+mar_score)/3 as avg_score,s.stock_name,s.industry
from stock_score ss
LEFT JOIN stock s on ss.stock_code=s.stock_code
ORDER BY avg_score desc;


-- 三个评分都较高的股票
SELECT * 
from (select stock_code,mar_score from stock_score ORDER BY mac_score desc limit 20) mac 
LEFT JOIN (select stock_code,mic_score from stock_score ORDER BY mic_score desc limit 20) mic on mac.stock_code=mic.stock_code
LEFT JOIN (select stock_code,mar_score from stock_score ORDER BY mar_score desc limit 20) mar on mac.stock_code=mar.stock_code;

SELECT * 
from (select stock_code,mic_score from stock_score ORDER BY mic_score desc limit 20) mac 
LEFT JOIN (select stock_code,mac_score from stock_score ORDER BY mac_score desc limit 20) mic on mac.stock_code=mic.stock_code
LEFT JOIN (select stock_code,mar_score from stock_score ORDER BY mar_score desc limit 20) mar on mac.stock_code=mar.stock_code;


SELECT * 
from (select stock_code,mar_score from stock_score ORDER BY mac_score desc limit 20) mac 
LEFT JOIN (select stock_code,mic_score from stock_score ORDER BY mic_score desc limit 20) mic on mac.stock_code=mic.stock_code
LEFT JOIN (select stock_code,mar_score from stock_score ORDER BY mar_score desc limit 20) mar on mac.stock_code=mar.stock_code;