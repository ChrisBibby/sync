package uk.co.chrisbibby.sync.services.initialise;

import uk.co.chrisbibby.sync.services.common.FileHelper;
import uk.co.chrisbibby.sync.services.common.DatabaseHelper;
import uk.co.chrisbibby.sync.services.common.Logging;

public class InitialiseService implements Initialise {

  private final Logging logging;

  public InitialiseService(final Logging logging) {
    this.logging = logging;
  }

  @Override
  public void perform(final String fileset) {
    if (FileHelper.fileExists(fileset)) {
      logging.getErr().printf("Unable to initialise %s - fileset already exists %n", fileset);
      return;
    }

    logging.getOut().printf("Initialising fileset: %s %n", fileset);
    final DatabaseHelper db = new DatabaseHelper(fileset, logging);
    if (db.createNewDb(logging, fileset)) {
      logging.getOut().printf("Successfully created fileset %s %n", fileset);
    }
  }
}
