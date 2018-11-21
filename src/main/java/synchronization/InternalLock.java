package synchronization;

public class InternalLock {
  private static int id = 100;

  //  Lock->class
  public static synchronized void decrease() {
    printThreadName();
    id--;
    printId();
  }

  //  Lock->this
  public synchronized void increase() {
    printThreadName();
    id--;
    printId();
  }

  // lock->obj
  public static void printInvokeTimestamp(Object lock) {
    synchronized (lock) {
      printThreadName();
      sleepOneSecond();
      id--;
      printId();
    }
  }

  /*
    Volatile:
      1. 保证变量每次都从内存中读，而不是CPU 的cache，所以具有所见性
      2. 但是并不保证原子性，所以只适用于，一个线程读写，多个线程读的场景
      3. 禁止编译器指令重排序
    Atomic*:
      1. 轻量级，不同于其他同步工具，像自旋锁一样，线程一直占用资源，直到该方法执行完成，才由JVM 从等待队列选择另一个线程进行
      2. 采用CAS 对共享数据处理的CPU 级别指令。 原理：更改值与期望值做比较，两值相同才替换新值。
  */

  private static void printId() {
    System.out.println("id:" + id);
  }

  private static void printThreadName() {
    System.out.println(Thread.currentThread().getName() + " invoke this method.");
  }

  private static void sleepOneSecond() {
    try {
      Thread.sleep((long) (Math.random() * 1000));
    } catch (InterruptedException ignored) {
    }
  }

}
