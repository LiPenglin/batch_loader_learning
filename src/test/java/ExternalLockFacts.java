import org.junit.Before;
import org.junit.Test;
import synchronization.ExternalLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ExternalLockFacts {
  ReentrantLock lock;

  @Before
  public void setup() {
    lock = new ReentrantLock();
  }

  @Test
  public void should_throw_interrupted_exception_when_try_lock_timeout() throws InterruptedException {
    lock.lock();
    ExternalLock eLock = new ExternalLock(lock);
    CountDownLatch latch = new CountDownLatch(1);
    new Thread(() -> eLock.timingLock(latch)).start();
    latch.await();
  }

  @Test
  public void should_interrupt_when_lock_is_occupied_by_other_thread() throws InterruptedException {
    lock.lock();
    ExternalLock eLock = new ExternalLock(lock);
    CountDownLatch latch = new CountDownLatch(1);
    Thread currentThread = new Thread(() -> eLock.interruptLock(latch));
    currentThread.start();
    Thread.sleep(1000);
    currentThread.interrupt();
    latch.await();
  }

  @Test
  public void should_awake_when_condition_signal() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    // 创建condition
    Condition fullLatch = lock.newCondition();
    ExternalLock eLock = new ExternalLock(lock, fullLatch);
    // 阻塞该线程
    new Thread(() -> eLock.condition(latch)).start();
    Thread.sleep(2 * 1000);
    // 获取锁，唤醒被condition 阻塞的线程
    lock.lock();
    fullLatch.signal();
    lock.unlock();
    // 等待thread 0 执行完成
    latch.await();
  }
}
