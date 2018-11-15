import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnmodifiableListFacts {
  @Test
  public void should_throw_exception_when_modify_unmodifiable_list() {
    try {
      operateUnmodifiableList();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void operateUnmodifiableList() {
    ArrayList<Object> list = new ArrayList<>();
    List<Object> uList = Collections.unmodifiableList(list);
    try {
      uList.add("Melon");
    } catch (UnsupportedOperationException uoe) {
      throw new UnsupportedOperationException("unsupported_operation_exception.");
    }
  }
}
