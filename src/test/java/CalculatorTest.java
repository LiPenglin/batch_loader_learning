import org.junit.Test;
import thread.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by lipenglin on 2018/12/11
 */
public class CalculatorTest {
  @Test public void should_return_sum() throws ExecutionException, InterruptedException {
    ExecutorService pool = Executors.newFixedThreadPool(3);
    List<Future<Integer>> list = new ArrayList<>();
    Random random = new Random();

    for (int i = 0; i < 5; i++) {
      int num = random.nextInt(20);
      System.out.println("----" + num + "-------");
      Calculator calculator = new Calculator(num);
      Future<Integer> result = pool.submit(calculator);
      list.add(result);
    }
      /*
        ----8-------
        ----13-------
        ----8-------
        ----14-------
        ----17-------
        8 ------------- 29
        8 ------------- 29
        29 / true
        13 ------------- 79
        79 / true
        29 / true
        14 ------------- 92
        92 / true
        17 ------------- 137
        137 / true

        1. 顺序创建 callable，add 到线程池中
        2. 线程运行完之后，返回 futrue
        3. list 会按顺序打印，遇到没有返回的futrue 会阻塞
        4. 知道所有futrue 打印完成
     */
    for (Future<Integer> future: list) {
      System.out.println(future.get() + " / " + future.isDone());
    }

  }
}
