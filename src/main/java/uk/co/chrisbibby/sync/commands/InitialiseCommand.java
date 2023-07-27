package uk.co.chrisbibby.sync.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import uk.co.chrisbibby.sync.services.initialise.InitialiseService;

import java.io.PrintWriter;

import static uk.co.chrisbibby.sync.services.common.Constants.EMPTY_STRING;

@Command(name = "init", description = "Initialise New Fileset")
public class InitialiseCommand implements Runnable {

  private final static String FILESET_PARAM = "0";

  @Spec
  private CommandSpec spec;

  @Parameters(arity = "1", index = FILESET_PARAM, description = "Location of new fileset")
  private String fileset;

  @Override
  public void run() {

    final PrintWriter err = spec.commandLine().getErr();
    final PrintWriter out = spec.commandLine().getOut();

    if (null == fileset || EMPTY_STRING.equals(fileset)) {
      err.print("A fileset location must be supplied!");
      return;
    }

    new InitialiseService(err, out).perform(fileset);
  }
}
