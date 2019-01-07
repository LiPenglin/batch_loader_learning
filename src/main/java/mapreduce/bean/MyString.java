package mapreduce.bean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by lipenglin on 2018/12/05
 */
public class MyString implements Writable {
  private String str;
  MyString() {

  }
  public MyString(String str) {
    this.str = str;
  }

  public String getString() {
    return str;
  }

  @Override public void write(DataOutput dataOutput) throws IOException {
    dataOutput.writeUTF(str);
  }

  @Override public void readFields(DataInput dataInput) throws IOException {
    str = dataInput.readUTF();
  }

  @Override public String toString() {
    return str;
  }
}
