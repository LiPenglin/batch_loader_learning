package mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by lipenglin on 2018/12/03
 */
public class WordCountMR {

  public static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text outKey = new Text();
    IntWritable outValue = new IntWritable(1);
    @Override protected void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
      String line = value.toString();
      String[] words = line.split("\\s+");
      for (String word : words) {
        outKey.set(word);
        context.write(outKey, outValue);
      }
    }
  }

  public static class WordCountReducer extends Reducer<Text, IntWritable, Text, LongWritable> {
    @Override protected void reduce(Text key, Iterable<IntWritable> values, Context context)
        throws IOException, InterruptedException {
      long counter = 0;
      for (IntWritable value : values) {
        counter += value.get();
      }
      context.write(key, new LongWritable(counter));
    }
  }
}
