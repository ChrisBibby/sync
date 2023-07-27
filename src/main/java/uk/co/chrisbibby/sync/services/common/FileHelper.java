package uk.co.chrisbibby.sync.services.common;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

  private static FileHelper fileHelper;

  private FileHelper() {
    // NO-OP.
  }

  public static FileHelper getInstance() {
    if (null == fileHelper) {
      fileHelper = new FileHelper();
    }

    return fileHelper;
  }
  public boolean fileExists(final String fileset) {
    try {
      return Files.exists(Paths.get(fileset));
    } catch (final SecurityException exception) {
      System.err.printf("An error occurred attempting access fileset %s %n", fileset);
      return false;
    }
  }
}
