package uk.co.chrisbibby.sync.services.scan;

import uk.co.chrisbibby.sync.services.common.DatabaseHelper;
import uk.co.chrisbibby.sync.services.common.FileHelper;
import uk.co.chrisbibby.sync.services.common.Logging;
import uk.co.chrisbibby.sync.services.common.Walk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ScanService implements Scan {

  private final Logging logging;

  public ScanService(final Logging logging) {
    this.logging = logging;
  }

  @Override
  public void perform(final Path path, final String fileset) {
    if (FileHelper.fileExists(fileset)) {
      final DatabaseHelper db = new DatabaseHelper(fileset, logging);
      final Walk walk = new Walk(db, logging);
      try {
        Files.walkFileTree(path, walk);
      } catch (final Exception e) {
        logging.getErr().printf("ERROR OCCURRED");
      } finally {
        db.closeConnection(logging);
      }
    }
  }
}
