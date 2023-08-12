package uk.co.chrisbibby.sync.services.common;

public class SQLHelper {

  final static String CREATE_FILE_TABLE = """
        CREATE TABLE IF NOT EXISTS files (
          filename TEXT NOT NULL,
          path TEXT NOT NULL UNIQUE,
          checksum INTEGER NOT NULL,
          size INTEGER NOT NULL
        );
      """;

  final static String UPSERT_FILE = """
        INSERT INTO files(filename, path, checksum, size)
        VALUES(?,?,?,?)
        ON CONFLICT(path) DO UPDATE SET filename=?, path=?, checksum=?, size=?;
      """;
}
