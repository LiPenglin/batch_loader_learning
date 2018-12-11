package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by lipenglin on 2018/12/11
 */
public class Calculator implements Callable<Integer> {
  private Integer num;

  public Calculator(Integer num) {
    this.num = num;
  }

  @Override public Integer call() throws Exception {
    int result = 1;
    for (int i = 0; i < num; i++) {
      result += i;
      TimeUnit.MILLISECONDS.sleep(500);
    }
    System.out.println(num + " ------------- " + result);
    return result;
  }
}
