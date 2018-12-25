import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import writer.MyWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by lipenglin on 2018/12/24
 */
public class FileWriterTest {
  private final MyWriter myWriter = new MyWriter();

  @Rule public TemporaryFolder tmp = new TemporaryFolder();

  @Test public void testWriteContentToFolder() throws IOException {
    File file = tmp.newFolder("folder").toPath().resolve("output.txt").toFile();
    myWriter.writerTo(file.getPath(), "test.");
    System.out.println(file.getName() + " / " + new String(Files.readAllBytes(Paths.get(file.getPath()))));
    System.out.println(file.getPath());
  }

  @Test public void testWriterContentToFile() throws IOException {
    File file = tmp.newFile("/folder/output.txt");
    myWriter.writerTo(file.getPath(), "test.");
    System.out.println(file.getName() + " / " + new String(Files.readAllBytes(Paths.get(file.getPath()))));
  }
}
