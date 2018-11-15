package synchronization;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ExternalLock {
  ReentrantLock lock;
  Condition fullLatch;

  public ExternalLock(ReentrantLock lock) {
    this.lock = lock;
  }

  public ExternalLock(ReentrantLock lock, Condition fullLatch) {
    this.lock = lock;
    this.fullLatch = fullLatch;
  }

  // 可定时的锁请求
  public void timingLock(CountDownLatch latch) {
    try {
      System.out.println("try locking " + getCurrentSecond());
      /*
      IllegalMonitorStateException:
      没有拥有监视器(monitor)的所有权
       */
      int timeout = 5;
      if (lock.tryLock(timeout, TimeUnit.SECONDS)) {
        try {
          System.out.println(Thread.currentThread().getName() + "/" + System.currentTimeMillis());
        } finally {
          lock.unlock();
        }
      }
    } catch (InterruptedException ignored) {
    } finally {
      latch.countDown();
      System.out.println("end " + getCurrentSecond());
    }
  }

  // 可中断的锁请求
  public void interruptLock(CountDownLatch latch) {
    try {
      // 该线程在等待锁的过程中，不允许被中断
//      lock.lock();
      // 该线程在等待锁的过程中，允许被中断
      lock.lockInterruptibly();
      System.out.println("try lock " + getCurrentSecond());
      System.out.println(Thread.currentThread().getName() + "/" + System.currentTimeMillis());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      latch.countDown();
      System.out.println("end " + getCurrentSecond());
    }
  }

  // Condition
  public void condition(CountDownLatch latch) {
    lock.lock();
    try {
      fullLatch.await();
      System.out.println(Thread.currentThread().getName());
    } catch (InterruptedException ignored) {
    } finally {
      lock.unlock();
      latch.countDown();
    }
  }
  private long getCurrentSecond() {
    return System.currentTimeMillis()%100000/1000;
  }
}
