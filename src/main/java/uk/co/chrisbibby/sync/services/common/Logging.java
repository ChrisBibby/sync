package uk.co.chrisbibby.sync.services.common;

import picocli.CommandLine.Model.CommandSpec;

import java.io.PrintWriter;

public class Logging {

  private final PrintWriter err;

  private final PrintWriter out;

  public Logging(final CommandSpec spec) {
    this.err = spec.commandLine().getErr();
    this.out = spec.commandLine().getOut();
  }

  public PrintWriter getErr() {
    return err;
  }

  public PrintWriter getOut() {
    return out;
  }
}
