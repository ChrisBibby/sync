package uk.co.chrisbibby.sync.services.common;

public class SQLStatements {

  final static String CREATE_FILE_TABLE = """
        CREATE TABLE IF NOT EXISTS files (
          filename TEXT NOT NULL,
          path TEXT NOT NULL UNIQUE,
          signature TEXT NOT NULL
        );
      """;

  final static String UPSERT_FILE = """
        INSERT INTO files(filename, path, signature)
        VALUES(?,?,?)
        ON CONFLICT(path) DO UPDATE SET filename=?, path=?, signature=?;
      """;
}
