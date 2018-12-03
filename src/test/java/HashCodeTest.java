import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

public class HashCodeTest {
  @Test
  public void should_show_hashcode() {
    int iCode = new Integer(Integer.MAX_VALUE).hashCode();
    System.out.println("i code: " + iCode);
    int strCode = new String().hashCode();
    System.out.println("str code: " + strCode);
    int prime = 31;
    long code = 1;
    code = prime * code + iCode;
    code = prime * code + strCode;
    System.out.println("code: " + code);

  }
}
