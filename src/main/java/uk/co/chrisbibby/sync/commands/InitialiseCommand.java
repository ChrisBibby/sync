package uk.co.chrisbibby.sync.commands;

import picocli.CommandLine;
import uk.co.chrisbibby.sync.repository.DatabaseHelper;

@CommandLine.Command(name = "initialise", description = "Initialise New Fileset")
public class InitialiseCommand implements Runnable {

  private final static String LOCATION_PARAM = "0";

  @CommandLine.Parameters(arity = "1", index = LOCATION_PARAM, description = "Location of new fileset")
  private String location;

  @Override
  public void run() {
    DatabaseHelper.createNew(location);
  }
}
