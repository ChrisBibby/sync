package uk.co.chrisbibby.sync.services.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

  public static boolean fileExists(final String file) {
    try {
      return Files.exists(Paths.get(file));
    } catch (final SecurityException exception) {
      System.err.printf("Accessing file %s was not allowed %n", file);
    } catch (final Exception exception) {
      System.err.printf("An unknown error occurred attempting access file %s %n", file);
    }

    return false;
  }

  public static long getFileSize(final String file) {
    try {
      return Files.size(Paths.get(file));
    } catch (final IOException exception ) {
      System.err.printf("An error occurred whilst Accessing file %s %n", file);
    } catch (final Exception exception) {
      System.err.printf("An unknown error occurred attempting access file %s %n", file);
    }

    return -1L;
  }
}
