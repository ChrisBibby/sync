package uk.co.chrisbibby.sync.services.common;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Walk extends SimpleFileVisitor<Path>  {

//  private final Connection connection;
  private final DatabaseHelper db;

  public Walk(final DatabaseHelper db) {
    this.db = db;
  }

  @Override
  public FileVisitResult visitFile(
      final Path path,
      final BasicFileAttributes attributes) {

    if (attributes.isRegularFile()) {
      System.out.printf("%s %n", path.toFile().getAbsolutePath());
      db.upsertFile(
          db.getConnection(""),
          path.toFile().getName(),
          path.toFile().getAbsolutePath(),
          "not calculated"
      );
    }

    return CONTINUE;
  }
}
