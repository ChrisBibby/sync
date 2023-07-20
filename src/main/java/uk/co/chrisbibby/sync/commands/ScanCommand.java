package uk.co.chrisbibby.sync.commands;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import uk.co.chrisbibby.sync.services.Scan;
import uk.co.chrisbibby.sync.services.ScanService;

import java.nio.file.Paths;

@Command(name = "scan", description = "Scan Location")
public class ScanCommand implements Runnable {

  private final static String LOCATION_PARAM = "0";
  public final static String EMPTY_STRING = "";

  @CommandLine.Parameters(arity = "1", index = LOCATION_PARAM, description = "Location of new fileset")
  private String location;

  @Override
  public void run() {
    if (null == location || EMPTY_STRING.equals(location)) {
      System.err.print("A location must be supplied! %n");
    }

    final Scan scan = new ScanService();
    scan.perform(Paths.get(location));
    System.out.printf("Location to scan :%s %n", location);
  }
}
