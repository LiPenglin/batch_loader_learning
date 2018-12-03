package mapreduce.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by lipenglin on 2018/12/03
 */
public class Shop implements WritableComparable<Shop> {
  private char province;
  private int profit;

  public Shop(char province, int profit) {
    this.province = province;
    this.profit = profit;
  }

  public char getProvince() {
    return province;
  }

  public int getProfit() {
    return profit;
  }

  public int compareToByProvince(Shop o) {
    return Character.compare(province, o.getProvince());
  }

  @Override public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    Shop o = (Shop) obj;
    return province == o.getProvince() && profit == o.getProfit();
  }

  @Override public String toString() {
    return this.province + ", " + this.profit;
  }

  @Override public int compareTo(Shop o) {
    return Integer.compare(profit, o.getProfit());
  }

  @Override public void write(DataOutput dataOutput) throws IOException {
    dataOutput.writeChar(profit);
    dataOutput.writeInt(profit);
  }

  @Override public void readFields(DataInput dataInput) throws IOException {
    profit = dataInput.readChar();
    profit = dataInput.readInt();
  }
}
