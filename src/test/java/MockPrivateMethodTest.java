import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import bean.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by lipenglin on 2018/12/17
 */
@RunWith(PowerMockRunner.class)
public class MockPrivateMethodTest {
  @Spy C c = new C();
  @PrepareForTest(C.class)
  @Test public void awesomeTest() throws Exception {
//    when(c, "hi", anyInt(), anyInt()).thenReturn(false);
    doReturn(false).when(c, "hi", anyInt(), anyInt());
//    when(c, "say01").then(o -> {System.out.println("yyy"); return null;});
    doAnswer(o -> {
      System.out.println("yyy");
      return null;
    }).when(c, "say01");

    c.say();
    c.say01();
  }
}
