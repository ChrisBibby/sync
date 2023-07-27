package uk.co.chrisbibby.sync.services.scan;

import java.nio.file.Path;

public interface Scan {
  public void perform(Path path, String fileset);

  public void diff(Path path, String fileset);
}
