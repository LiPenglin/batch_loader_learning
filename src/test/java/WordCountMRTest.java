
import static mapreduce.WordCountMR.WordCountMapper;
import static mapreduce.WordCountMR.WordCountReducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lipenglin on 2018/12/03
 */
public class WordCountMRTest {
  @Test public void WordCountMapper_inputLineShouldSplitBySpace() throws IOException {
    WordCountMapper mapper = new WordCountMapper();
    MapDriver<LongWritable, Text, Text, IntWritable> mapDriver = MapDriver.newMapDriver(mapper);
    Text inValue = new Text("love is a ...");
    mapDriver.withInput(new LongWritable(), inValue);
    mapDriver.withOutput(new Text("love"), new IntWritable(1));
    mapDriver.withOutput(new Text("is"), new IntWritable(1));
    mapDriver.withOutput(new Text("a"), new IntWritable(1));
    mapDriver.withOutput(new Text("..."), new IntWritable(1));
    mapDriver.runTest();
  }

  @Test public void WordCountReducer_shouldCountTheNumberOfWordTimes() throws IOException {
    WordCountReducer reducer = new WordCountReducer();
    ReduceDriver<Text, IntWritable, Text, LongWritable> reduceDriver = ReduceDriver.newReduceDriver(reducer);
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
    WordCountReducer reducer = new WordCountReducer();
    WordCountMapper mapper = new WordCountMapper();
    MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, LongWritable> MRDriver =
        MapReduceDriver.newMapReduceDriver(mapper, reducer);
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
