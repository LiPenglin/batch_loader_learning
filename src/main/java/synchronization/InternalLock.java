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
