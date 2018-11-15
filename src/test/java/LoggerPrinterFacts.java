import org.junit.Test;
import slf4j.LogPrinter;

public class LoggerPrinterFacts {
  @Test
  public void logger_should_print_hello() {
    new LogPrinter();
  }
}
