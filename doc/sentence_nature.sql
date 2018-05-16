SELECT count(1) as `总数` from stock_term;
select count(1) as `名词` from stock_term where nature = '名词';
select count(1) as `动词` from stock_term where nature = '动词';



-- 词性模板统计，并取出频数前八的模板
select 
b.comment_id,b.sentence_id,count(1) as `num`,b.sentence_nature,GROUP_CONCAT("-",b.sentence) as terms
from 
(select 
GROUP_CONCAT(a.nature) as `sentence_nature` ,GROUP_CONCAT(a.stock_term) as `sentence`,a.comment_id,a.sentence_id
from 
(SELECT 
comment_id,sentence_id,nature,stock_term,stock_code 
from stock_term s 
where s.stock_code = '股票代码'
ORDER BY s.comment_id,s.sentence_id,s.stock_term_id ) as `a`
GROUP BY a.comment_id,a.sentence_id) as `b` 
GROUP BY b.sentence_nature ORDER BY num desc limit 0,8;


-- select * from stock_term GROUP BY comment_id,sentence_id

select COUNT(1)
FROM
(select COUNT(1) as c from stock_term GROUP BY comment_id,sentence_id) as cc;



-- 出现最多的前100个词		均线 回调 突破	回档 压力线	支撑线
select COUNT(1) as `times`,stock_term from stock_term GROUP BY stock_term.stock_term ORDER BY times desc;


select * from stock_term WHERE stock_term = '介入';

DELETE from screen_term;

INSERT into screen_term select * from stock_term WHERE stock_term = '介入';


SELECT * from stock_term where nature = '副词';

-- 900905   可能一个评论出现多次，所以根据commend_id进行聚合
select comment_id from stock_score where stock_code = '900905' and nature = '名词' and stock_term = '' GROUP BY comment_id;

select * from screen_term where stock_code = '900905' GROUP BY stock_term HAVING nature = '名词';

-- 900908
select * from screen_term where stock_code = '900908' GROUP BY stock_term HAVING nature = 'n';



