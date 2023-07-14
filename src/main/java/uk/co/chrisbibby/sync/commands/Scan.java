package uk.co.chrisbibby.sync.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "scan", description = "Scan Location")
public class Scan implements Runnable {

  @Option(names = {"-l",
      "--location"}, description = "Location")
  private String location;

  @Override
  public void run() {
    System.out.printf("Location to scan %s %n", location);
  }
}
