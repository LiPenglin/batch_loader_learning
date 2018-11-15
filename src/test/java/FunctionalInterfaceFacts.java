import functionalinterface.Melon;
import functionalinterface.Runner;
import org.junit.Test;

public class FunctionalInterfaceFacts {

  @Test
  public void melon_should_introduce_itself_when_runner_invoke_run_method() {
    Melon melon = new Melon("top 1.");
    new Runner().run(melon::introduce);
  }
}
