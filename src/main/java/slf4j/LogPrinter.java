package slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogPrinter {
  public LogPrinter() {
    //  facade design pattern
    Logger logger = LoggerFactory.getLogger(LogPrinter.class);
    //  只有info和更高级的信息会被打印出来
    logger.info("info.");
    System.out.println("log printer constructor.");
  }
}
