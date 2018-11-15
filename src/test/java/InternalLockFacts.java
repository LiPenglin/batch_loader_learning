import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import synchronization.InternalLock;

import java.util.concurrent.CountDownLatch;

public class InternalLockFacts {

  private int threadCount = 3;
  private CountDownLatch wait4ChildThread;

  @Before
  public void setup() {
    wait4ChildThread = new CountDownLatch(threadCount);
  }

  /*
  同步：保证 print thread name 和 print id 的先后顺序。
   */

  @Test
  public void static_synchronized_method_should_lock_by_class() {
    for (int i = 0; i < threadCount; i++) {
      new Thread(() -> {
        for (int j = 0; j < 5; j++) {
          InternalLock.decrease();
        }
        wait4ChildThread.countDown();
      }).start();
    }
  }

  @Test
  public void synchronized_method_should_lock_by_this() {
    InternalLock lock = new InternalLock();
    for (int i = 0; i < threadCount; i++) {
      new Thread(() -> {
        for (int j = 0; j < 5; j++) {
          lock.increase();
        }
        wait4ChildThread.countDown();
      }).start();
    }
  }

  @Test
  public void synchronized_code_block_should_lock_by_obj() {
    Object lock = new Object();
    for (int i = 0; i < threadCount; i++) {
      new Thread(() -> {
        for (int j = 0; j < 5; j++) {
          InternalLock.printInvokeTimestamp(lock);
        }
        wait4ChildThread.countDown();
      }).start();
    }
  }

  @After
  public void teardown() throws InterruptedException {
    wait4ChildThread.await();
  }

}
