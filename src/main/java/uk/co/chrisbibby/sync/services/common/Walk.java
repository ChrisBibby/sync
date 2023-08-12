package uk.co.chrisbibby.sync.services.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.CRC32C;
import java.util.zip.CheckedInputStream;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Walk extends SimpleFileVisitor<Path>  {

  private final DatabaseHelper db;
  private final Logging logging;

  public Walk(final DatabaseHelper db, final Logging logging) {
    this.db = db;
    this.logging = logging;
  }

  @Override
  public FileVisitResult visitFile(
      final Path path,
      final BasicFileAttributes attributes) {

    if (attributes.isRegularFile()) {
      logging.getOut().printf("Calculating file signature: %s %n", path.toFile().getAbsolutePath());
      final Long checksum = calculateChecksum(path);
      final Long fileSize = FileHelper.getFileSize(path.toFile().getAbsolutePath());


      db.upsertFile(
          path.toFile().getName(),
          path.toFile().getAbsolutePath(),
          checksum,
          fileSize
      );
      logging.getOut().printf("Successfully saved to fileset %s %n", path.toFile().getAbsolutePath());
    }

    return CONTINUE;
  }


  private Long calculateChecksum(final Path path) {
    try {
      final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path.toString()));
      CheckedInputStream checkedInputStream = new CheckedInputStream(bis, new CRC32C());
      byte[] buffer = new byte[64 * 1024];
      while (true) {
        if (checkedInputStream.read(buffer, 0, buffer.length) < 0) break;
      }

      return checkedInputStream.getChecksum().getValue();
    } catch (final Exception exception) {
      System.out.println("errors");
      return -1L;
    }
  }
}
