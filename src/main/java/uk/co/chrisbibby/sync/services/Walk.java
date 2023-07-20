package uk.co.chrisbibby.sync.services;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Walk extends SimpleFileVisitor<Path>  {

  @Override
  public FileVisitResult visitFile(
      final Path path,
      final BasicFileAttributes attributes) {

    if (attributes.isRegularFile()) {
      System.out.printf("%s %n", path.toFile().getAbsolutePath());
    }

    return CONTINUE;
  }
}
