import mapreduce.WordCountMR_A;
import mapreduce.bean.MyString;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by lipenglin on 2018/12/03
 */
public class TestWordCountMR_A {
  MapDriver<LongWritable, MyString, Text, IntWritable> mapDriver;

  @Before public void setUp() {
    WordCountMR_A.WordCountMapper mapper = new WordCountMR_A.WordCountMapper();
    mapDriver = MapDriver.newMapDriver(mapper);
  }

  @Test public void WordCountMapper_inputLineShouldSplitBySpace() throws IOException {
    MyString myString = new MyString("love is a");
    //    mapDriver.withInput(new LongWritable(), myString);
    mapDriver.withInput(new Pair<>(new LongWritable(), myString));

    mapDriver.withOutput(new Text("love"), new IntWritable(1));
    mapDriver.withOutput(new Text("is"), new IntWritable(1));
    mapDriver.withOutput(new Text("a."), new IntWritable(1));

    mapDriver.runTest();
  }

}
