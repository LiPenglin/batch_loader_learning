package mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by lipenglin on 2018/12/03
 */
public class WordCountMR {
  private static final Logger logger = LoggerFactory.getLogger(WordCountMR.class);

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
        logger.info("map task write to context. [word='{}']", word);
        Thread.sleep(1000*10);
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

  public static void main(String []args) throws Exception {
    Configuration conf = new Configuration();

    FileSystem fs = FileSystem.get(conf);

    Job job = Job.getInstance(conf);

    job.setJarByClass(WordCountMR.class);
    job.setMapperClass(WordCountMapper.class);
    job.setReducerClass(WordCountReducer.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);

    String root = "/Users/lipenglin/tmp/hdfs";
    String inputPath = Paths.get(root, "/wc/input").toString();
    String outputPath = Paths.get(root, "/wc/output").toString();
    fs.delete(new Path(outputPath), true);
    FileInputFormat.setInputPaths(job, new Path(inputPath));
    FileOutputFormat.setOutputPath(job, new Path(outputPath));

    //output jobId
//    job.submit();
//    System.out.println(job.getJobID());

    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
