package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lipenglin on 2018/12/26
 */
public class IOStreamTest {

  static void testSystemIn() throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in);
    BufferedReader bReader = new BufferedReader(reader);
    String line = bReader.readLine();
    System.out.println(line);
  }

  public static void main(String[] args) throws IOException {
    IOStreamTest.testSystemIn();
  }
}
