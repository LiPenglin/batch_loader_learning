package writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by lipenglin on 2018/12/24
 */
public class MyWriter {
  public void writerTo(String path, String content) throws IOException {
    Path target = Paths.get(path);
    if (Files.exists(target)) {
      throw new IOException("file is already exists.");
    }
    Files.write(target, content.getBytes());
  }
}
