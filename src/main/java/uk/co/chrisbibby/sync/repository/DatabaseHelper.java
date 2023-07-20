package uk.co.chrisbibby.sync.repository;

import java.sql.*;

public class DatabaseHelper {

  private static final String JDBC_DB_CONNECTION_STRING = "jdbc:sqlite:";

  final static String SQL_CREATE_FILE_TABLE = """
      CREATE TABLE IF NOT EXISTS files (
        id integer PRIMARY KEY,
        filename TEXT NOT NULL,
        path TEXT NOT NULL,
        signature TEXT NOT NULL
      );
    """;

  public static void createNew(final String location) {
    final String url = DatabaseHelper.JDBC_DB_CONNECTION_STRING.concat(location);

    try (final Connection connection = DriverManager.getConnection(url)) {
      if (connection != null) {
        connection.createStatement().execute(SQL_CREATE_FILE_TABLE);
        System.out.printf("Now using fileset %s %n", location);
      }

    } catch (final SQLException exception) {
      System.out.println(exception.getMessage());
    }
  }
}



