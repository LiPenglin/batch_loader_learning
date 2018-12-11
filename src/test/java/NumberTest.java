import org.junit.Test;

/**
 * Created by lipenglin on 2018/12/13
 */
public class NumberTest {

  @Test public void conversion() {
    System.out.println((int)1.4);
    System.out.println((int)1.6);
    System.out.println((int)-1.4);
    System.out.println((int)-1.6);
    System.out.println(Math.floor(1.3));
    System.out.println(Math.floor(1.6));
    System.out.println(Math.floor(-1.3));
    System.out.println(Math.floor(-1.6));
    System.out.println(Math.ceil(1.3));
    System.out.println(Math.ceil(1.6));
    System.out.println(Math.ceil(-1.3));
    System.out.println(Math.ceil(-1.6));
    System.out.println(Math.rint(1.3));
    System.out.println(Math.rint(1.6));
    System.out.println(Math.rint(-1.3));
    System.out.println(Math.rint(-1.6));
    System.out.println(Math.round(1.3));
    System.out.println(Math.round(1.6));
    System.out.println(Math.round(-1.3));
    System.out.println(Math.round(-1.6));
  }

  @Test public void logicalOperator() {
    boolean a1 = false || false && true;
    boolean a2 = false || true && false || true;
    boolean a3 = false || false && false || true;
    boolean a4 = is(true) || is(false);
    System.out.println(a1 + " / " + a2 + " / " + a3);
  }

  private boolean is(boolean a) {
    System.out.println("is method :" + a);
    return a;
  }

  private static class MyString {
    private String s;

    public MyString(String s) {
      this.s = s;
    }

    @Override public String toString() {
      return s;
    }
  }
  @Test public void testString() {
    String s1 = "original.";
    MyString myS = new MyString(s1);
    //    String s2 = "hhh";

    str1(s1);
    myStr(myS);
//    str2(s2);
    System.out.println(s1);
    System.out.println(myS);
//    System.out.println(s2);
  }

  private void myStr(MyString myS) {
    MyString myS1 = new MyString("change.");
    myS = myS1;
    System.out.println("myStr method:" + myS);
  }

  private void str1(String s) {
    s = "change.";
  }
//  private void str2(final String s) {
//    s = "change";
//  }
}
