package mapreduce;

import mapreduce.bean.MyString;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by lipenglin on 2018/12/03
 */
public class WordCountMR_A {

  public static class WordCountMapper extends Mapper<LongWritable, MyString, Text, IntWritable> {

    Text outKey = new Text();
    IntWritable outValue = new IntWritable(1);
    @Override protected void map(LongWritable key, MyString value, Context context)
        throws IOException, InterruptedException {
      String line = value.getString();
      String[] words = line.split("\\s+");
      for (String word : words) {
        outKey.set(word);
        context.write(outKey, outValue);
      }
    }
  }
}
