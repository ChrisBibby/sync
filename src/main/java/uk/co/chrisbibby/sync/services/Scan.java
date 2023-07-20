package uk.co.chrisbibby.sync.services;

import java.nio.file.Path;

public interface Scan {
  public void perform(Path path);
}
