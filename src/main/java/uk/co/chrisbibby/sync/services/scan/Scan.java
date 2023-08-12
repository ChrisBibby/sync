package uk.co.chrisbibby.sync.services.scan;

import java.nio.file.Path;

public interface Scan {
  void perform(Path path, String fileset);
}
