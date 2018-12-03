import static org.junit.Assert.assertEquals;

import mapreduce.bean.Shop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapOutputShuffler;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipenglin on 2018/12/03
 */
public class TestMRShuffle {
  MapOutputShuffler shuffler;

  @Before public void setUp() {
    shuffler = new MapOutputShuffler<Shop, IntWritable>(new Configuration(), Shop::compareTo, Shop::compareToByProvince);
  }

  @Test public void shuffle_shopShouldCompareByProfit() {
    List<Pair<Shop, Text>> mapOutput = new ArrayList<>();
    mapOutput.add(new Pair<>(new Shop('E', 7), new Text("g")));
    mapOutput.add(new Pair<>(new Shop('D', 1), new Text("a")));
    mapOutput.add(new Pair<>(new Shop('D', 2), new Text("b")));
    mapOutput.add(new Pair<>(new Shop('A', 9), new Text("i")));
    mapOutput.add(new Pair<>(new Shop('E', 8), new Text("h")));
    mapOutput.add(new Pair<>(new Shop('D', 5), new Text("e")));
    mapOutput.add(new Pair<>(new Shop('D', 3), new Text("c")));
    mapOutput.add(new Pair<>(new Shop('D', 4), new Text("d")));
    mapOutput.add(new Pair<>(new Shop('E', 6), new Text("f")));
    ArrayList<Pair<Shop, List<Text>>> expect = new ArrayList<>();
    expect.add(new Pair<>(new Shop('D', 1), new ArrayList<Text>() {
      {
        add(new Text("a"));
        add(new Text("b"));
        add(new Text("c"));
        add(new Text("d"));
        add(new Text("e"));
      }
    }));
    expect.add(new Pair<>(new Shop('E', 6), new ArrayList<Text>() {
      {
        add(new Text("f"));
        add(new Text("g"));
        add(new Text("h"));
      }
    }));
    expect.add(new Pair<>(new Shop('A', 9), new ArrayList<Text>() {
      {
        add(new Text("i"));
      }
    }));

    List<Pair<Shop, Text>> actual = shuffler.shuffle(mapOutput);

    assertEquals(expect, actual);
  }
}
