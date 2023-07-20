package uk.co.chrisbibby.sync.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScanService implements Scan {

  @Override
  public void perform(final Path path) {
    final Walk walk = new Walk();
    try {
      Files.walkFileTree(path, walk);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }
}
