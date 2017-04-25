package storm.spout;

import java.util.Map;
import java.util.Random;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

public class TestWordSpout extends BaseRichSpout
{
    private SpoutOutputCollector _collector;
    private Fields fields = new Fields("word");
    @Override
    public void open(Map conf, TopologyContext context,
            SpoutOutputCollector collector)
    {
        this._collector = collector;
    }
    
    @Override
    public void nextTuple()
    {
        Utils.sleep(100);
        
        final String[] words = new String[] {
                "nathan", "mike", "jackson", "golda", "bertels"
        };
        final Random random = new Random();
        final String word = words[random.nextInt(words.length)];
        System.out.println("基础发射>>>>>>>>>>>"+word);
        _collector.emit(new Values(word));
    }
    
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
        declarer.declare(fields);
    }
    
}
