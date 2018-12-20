import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

import bean.A;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

/**
 * Created by lipenglin on 2018/12/10
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({A.class})
public class Test {

  @org.junit.Test public void separator() {
    String x = "|" + Character.LINE_SEPARATOR + "|";
    Arrays.stream(x.split(String.valueOf(Character.LINE_SEPARATOR))).forEach(System.out::println);
  }

  @org.junit.Test public void test_instanceof() {
    Integer  n = null;
    Integer integer = new Integer(1);
    System.out.println(null instanceof Integer);
    System.out.println(n instanceof Integer);
    System.out.println(integer instanceof Integer);
    System.out.println(null == null);
  }

  @org.junit.Test public void test_mock_private_method() throws Exception {
    A a = spy(new A());
    when(a, "who").thenReturn("B");
    a.say();
  }
}
