import com.caiw.job.GetData;
import com.caiw.utils.KafkaUtil;
import com.caiw.utils.PropertiesUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

/**
 * Created by 蔡维 in 16:31 2018/4/13
 */
public class Test1 {


    public static void main(String[] args) {
        GetData.producer = new KafkaProducer<>(PropertiesUtil.getProduceProperties());
        KafkaUtil.kafkaProduce("test1");
        GetData.producer.close();
    }
}
