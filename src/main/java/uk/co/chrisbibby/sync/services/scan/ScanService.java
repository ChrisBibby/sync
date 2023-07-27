package uk.co.chrisbibby.sync.services.scan;

import uk.co.chrisbibby.sync.services.common.DatabaseHelper;
import uk.co.chrisbibby.sync.services.common.FileHelper;
import uk.co.chrisbibby.sync.services.common.Walk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ScanService implements Scan {

  private final FileHelper fileHelper;
  private final DatabaseHelper db;

  public ScanService() {
    db = DatabaseHelper.getInstance();
    fileHelper = FileHelper.getInstance();
  }

  @Override
  public void perform(final Path path, final String fileset) {
    if (fileHelper.fileExists(fileset)) {
      final Walk walk = new Walk(db);

      try {
        Files.walkFileTree(path, walk);
      } catch (final IOException e) {
        throw new RuntimeException(e);
      } finally {
        db.closeConnection();
      }
    }
  }

  @Override
  public void diff(Path path, String fileset) {
    // NO-OP.
  }
}
