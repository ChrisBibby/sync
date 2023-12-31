package uk.co.chrisbibby.sync.commands;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;
import uk.co.chrisbibby.sync.services.common.Logging;
import uk.co.chrisbibby.sync.services.scan.Scan;
import uk.co.chrisbibby.sync.services.scan.ScanService;

import java.nio.file.Paths;

import static uk.co.chrisbibby.sync.services.common.Constants.EMPTY_STRING;

@Command(name = "scan", description = "Scan Location")
public class ScanCommand implements Runnable {

  @Spec
  private CommandSpec spec;
  private final static String LOCATION_PARAM = "0";
  private final static String FILESET_PARAM = "1";

  @CommandLine.Parameters(arity = "1", index = LOCATION_PARAM, description = "Location to scan")
  private String location;

  @CommandLine.Parameters(arity = "1", index = FILESET_PARAM, description = "Fileset location")
  private String fileset;

  @Override
  public void run() {
    if (null == location || EMPTY_STRING.equals(location)) {
      System.err.print("A source location must be supplied! %n");
    }

    if (null == fileset || EMPTY_STRING.equals(fileset)) {
      System.err.print("A fileset must be supplied! %n");
    }

    final Logging logging = new Logging(spec);

    final Scan scan = new ScanService(logging);
    scan.perform(Paths.get(location), fileset);
  }
}
