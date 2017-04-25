package storm.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class ExclamationBolt extends BaseRichBolt
{
    private OutputCollector _collector;
    private Fields fields = new Fields("words");
    
    @Override
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector)
    {
        this._collector = collector;
    }
    
    @Override
    public void execute(Tuple input)
    {
        System.out.println("我处理了>>>>>>>>>>>"+input.getString(0));
        _collector.emit(input, new Values(input.getString(0) + " !!!!!!!!"));
        _collector.ack(input);
    }
    
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
        declarer.declare(fields);
    }
    
}
