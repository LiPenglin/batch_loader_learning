import mapreduce.WordCountMR;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lipenglin on 2018/12/03
 */
public class TestWordCountMR {
  MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
  ReduceDriver<Text, IntWritable, Text, LongWritable> reduceDriver;
  MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, LongWritable> MRDriver;

  @Before public void setUp() {
    mapreduce.WordCountMR.WordCountMapper mapper = new WordCountMR.WordCountMapper();
    mapDriver = MapDriver.newMapDriver(mapper);
    mapreduce.WordCountMR.WordCountReducer reducer = new WordCountMR.WordCountReducer();
    reduceDriver = ReduceDriver.newReduceDriver(reducer);
    MRDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
  }

  @Test public void WordCountMapper_inputLineShouldSplitBySpace() throws IOException {
    mapDriver.withInput(new LongWritable(), new Text("love is a touch and yet not a touch"));

    mapDriver.withOutput(new Text("love"), new IntWritable(1));
    mapDriver.withOutput(new Text("is"), new IntWritable(1));
    mapDriver.withOutput(new Text("a"), new IntWritable(1));
    mapDriver.withOutput(new Text("touch"), new IntWritable(1));
    mapDriver.withOutput(new Text("and"), new IntWritable(1));
    mapDriver.withOutput(new Text("yet"), new IntWritable(1));
    mapDriver.withOutput(new Text("not"), new IntWritable(1));
    mapDriver.withOutput(new Text("a"), new IntWritable(1));
    mapDriver.withOutput(new Text("touch"), new IntWritable(1));

    mapDriver.runTest();
  }

  @Test public void WordCountReducer_shouldCountTheNumberOfWordTimes() throws IOException {
    Text key1 = new Text("love");
    ArrayList<IntWritable> values1 = new ArrayList<IntWritable>() {
      {
        add(new IntWritable(1));
        add(new IntWritable(1));
        add(new IntWritable(1));
      }
    };
    reduceDriver.withInput(key1, values1);
    Text key2 = new Text("is");
    ArrayList<IntWritable> values2 = new ArrayList<IntWritable>() {
      {
        add(new IntWritable(1));
        add(new IntWritable(1));
      }
    };
    reduceDriver.withInput(key2, values2);
    Text key3 = new Text("a");
    ArrayList<IntWritable> values3 = new ArrayList<IntWritable>() {
      {
        add(new IntWritable(1));
      }
    };
    reduceDriver.withInput(key3, values3);

    reduceDriver.withOutput(key1, new LongWritable(3));
    reduceDriver.withOutput(key2, new LongWritable(2));
    reduceDriver.withOutput(key3, new LongWritable(1));

    reduceDriver.runTest();
  }

  @Test public void WordCountMR_shouldCountTheNumberOfWordTimesByInputLine() throws IOException {
    MRDriver.withInput(new LongWritable(), new Text("love is a touch and yet not a touch"));

    MRDriver.withOutput(new Text("a"), new LongWritable(2));
    MRDriver.withOutput(new Text("and"), new LongWritable(1));
    MRDriver.withOutput(new Text("is"), new LongWritable(1));
    MRDriver.withOutput(new Text("love"), new LongWritable(1));
    MRDriver.withOutput(new Text("not"), new LongWritable(1));
    MRDriver.withOutput(new Text("touch"), new LongWritable(2));
    MRDriver.withOutput(new Text("yet"), new LongWritable(1));

    MRDriver.runTest();
  }
}
