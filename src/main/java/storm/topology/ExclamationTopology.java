package storm.topology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

import storm.bolt.ExclamationBolt;
import storm.spout.TestWordSpout;

public class ExclamationTopology
{
    
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException
    {
        final TopologyBuilder builder = new TopologyBuilder();
        
        builder.setSpout("word", new TestWordSpout(), 3);
        builder.setBolt("exclam1", new ExclamationBolt(), 3).shuffleGrouping("word");
        builder.setBolt("exclam2", new ExclamationBolt(), 3).shuffleGrouping("exclam1");
        
        final Config conf = new Config();
        conf.setDebug(true);
        
        if(args != null && args.length > 0) {
            conf.setNumWorkers(3);
            StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
        } else {
            final LocalCluster cluster = new LocalCluster();
            final String topoName = "test";
            cluster.submitTopology(topoName, conf, builder.createTopology());
            Utils.sleep(100000);
            cluster.killTopology(topoName);
            cluster.shutdown();
        }
    }
    
}
