package uk.co.chrisbibby.sync.services.initialise;

import uk.co.chrisbibby.sync.services.common.FileHelper;
import uk.co.chrisbibby.sync.services.common.DatabaseHelper;

import java.io.PrintWriter;

public class InitialiseService implements Initialise {

  private final DatabaseHelper db;
  private final FileHelper fileHelper;
  private final PrintWriter err;
  private final PrintWriter out;



  public InitialiseService(final PrintWriter err, final PrintWriter out) {
    this.db = DatabaseHelper.getInstance();
    this.fileHelper = FileHelper.getInstance();
    this.err = err;
    this.out = out;

  }

  @Override
  public void perform(final String fileset) {
    if (fileHelper.fileExists(fileset)) {
      err.printf("Unable to initialise %s - fileset already exists %n", fileset);
      return;
    }

    out.printf("Initialising fileset: %s %n", fileset);
    db.createNewDb(fileset);
  }
}
