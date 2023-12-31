package uk.co.chrisbibby.sync;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import uk.co.chrisbibby.sync.commands.InitialiseCommand;
import uk.co.chrisbibby.sync.commands.ScanCommand;

import java.util.concurrent.Callable;

@Command(
    name = "sync",
    mixinStandardHelpOptions = true,
    version = "sync 0.0.1",
    description = "File Synchronisation",
    subcommands = {
        InitialiseCommand.class,
        ScanCommand.class
    })
public class Main implements Callable<Integer> {

  public static void main(final String[] args) {
    System.exit(new CommandLine(new Main())
        .execute(args));
  }

  @Override
  public Integer call() {
    CommandLine.usage(this, System.out);
    return 0;
  }
}