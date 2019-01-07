package java8;

import bean.A;
import mapreduce.bean.MyString;
import org.junit.Test;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

/**
 * Created by lipenglin on 2019/01/04
 */
public class StreamTest {

  @Test public void testFindAny() {
    Stream<String> stringStream = Stream.of("A", "B", "C");
    Optional<String> any = stringStream.findAny();
    System.out.println(any.get());
  }

  @Test public void testFilter() {
    Stream<MyString> stringStream =
        Stream.of(null, null, new MyString("a"), new MyString("b"), new MyString("c"), new MyString("d"));
    Stream<MyString> a = stringStream.filter(s -> s != null && s.getString().equalsIgnoreCase("d"));
    a.forEach(System.out::println);
  }

  @Test public void testOptional() {
    Optional<String> s = Optional.ofNullable(null);
    Optional<String> s1 = Optional.ofNullable("s1");
    System.out.println(s.isPresent() + " / " + s1.isPresent());
    System.out.println(s.orElse("default.") + " / " + s1.orElse("default."));

    Integer i = Optional.ofNullable(getInteger()).orElse(3);
    System.out.println(1);

  }

  Integer getInteger() {
    return null;
  }

  @Test public void testFilter01() {
    Stream<MyString> stringStream =
        Stream.of(new MyString("a"), new MyString("b"), new MyString("c"), new MyString("d"));
    stringStream.filter(s -> s.getString().equals("a")).forEach(System.out::println);
  }
}
