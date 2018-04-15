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