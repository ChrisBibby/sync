package uk.co.chrisbibby.sync.services.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static uk.co.chrisbibby.sync.services.common.SQLStatements.CREATE_FILE_TABLE;
import static uk.co.chrisbibby.sync.services.common.SQLStatements.UPSERT_FILE;

public class DatabaseHelper {

  private static final String JDBC_DB_CONNECTION_STRING = "jdbc:sqlite:";
  private static final int SQL_PARAM_FILENAME = 1;
  private static final int SQL_PARAM_PATH = 2;
  private static final int SQL_PARAM_SIGNATURE = 3;
  private static Connection connection = null;

  private static DatabaseHelper databaseHelper = null;

  public DatabaseHelper() {
    // NO-OP.
  }

  public static DatabaseHelper getInstance() {
    if (null == databaseHelper) {
      databaseHelper = new DatabaseHelper();
    }

    return databaseHelper;
  }

  public Connection getConnection(final String location) {

    if (null != connection) {
      return connection;
    }

    final String url = DatabaseHelper.JDBC_DB_CONNECTION_STRING.concat(location);

    try {
      connection = DriverManager.getConnection(url);
    } catch (final SQLException exception) {
      System.out.println(exception.getMessage());
    }

    return connection;
  }

  public void closeConnection() {
    try {
      if (null != connection) {
        connection.close();
      }
    } catch (final SQLException exception) {
      System.out.println(exception.getMessage());
    }
  }

  public void createNewDb(final String location) {
    try {
      if (null == connection) {
        getConnection(location);
      }
      connection.createStatement().execute(CREATE_FILE_TABLE);
    } catch (final SQLException exception) {
      System.out.println(exception.getMessage());
    } finally {
      closeConnection();
    }
  }

  public void upsertFile(
      final Connection connection,
      final String file,
      final String path,
      final String signature) {
    try {
      if (connection != null) {
        final PreparedStatement preparedStatement = connection.prepareStatement(UPSERT_FILE);
        preparedStatement.setString(SQL_PARAM_FILENAME, file);
        preparedStatement.setString(SQL_PARAM_PATH, path);
        preparedStatement.setString(SQL_PARAM_SIGNATURE, signature);
        preparedStatement.setString(SQL_PARAM_FILENAME+3, file);
        preparedStatement.setString(SQL_PARAM_PATH+3, path);
        preparedStatement.setString(SQL_PARAM_SIGNATURE+3, signature);
        preparedStatement.executeUpdate();
      }
    } catch (final SQLException exception) {
      System.out.println(exception.getMessage());
    }
  }
}



