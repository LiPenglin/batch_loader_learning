package myservice;

/**
 * Created by lipenglin on 2018/12/24
 */
public class MyServer implements HelloService.Iface {

  @Override public String helloString(String who) {
    return "hello " + who + ".";
  }
}
