package uk.co.chrisbibby.sync.services.common;

import uk.co.chrisbibby.sync.services.common.tables.Files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static uk.co.chrisbibby.sync.services.common.SQLHelper.CREATE_FILE_TABLE;
import static uk.co.chrisbibby.sync.services.common.SQLHelper.UPSERT_FILE;

public class DatabaseHelper {

  private static final String JDBC_DB_CONNECTION_STRING = "jdbc:sqlite:";

  private static Connection connection = null;

  private static Logging logging = null;

  public DatabaseHelper(final String fileset, final Logging logging) {
    setLogging(logging);

    if (null == connection) {
      connection = getConnection(fileset, logging);
    }
  }

  public void setLogging(final Logging logging) {
    if (null != logging && DatabaseHelper.logging == null) {
      DatabaseHelper.logging = logging;
    }
  }

  public Connection getConnection(final String location, final Logging logging) {

    if (null != connection) {
      return connection;
    }

    final String url = DatabaseHelper.JDBC_DB_CONNECTION_STRING.concat(location);

    try {
      connection = DriverManager.getConnection(url);
    } catch (final SQLException exception) {
      System.out.println(exception.getMessage());
      logging.getErr().printf("An error occurred whilst opening fileset");
    }

    return connection;
  }

  public void closeConnection(final Logging logging) {
    try {
      if (null != connection) {
        connection.close();
      }
    } catch (final SQLException exception) {
      logging.getErr().printf("Error occurred whilst closing fileset");
    }
  }

  public boolean createNewDb(final Logging logging, final String location) {
    boolean isSuccessful = false;

    try {
      if (null == connection) {
        getConnection(location, logging);
      }
      connection.createStatement().execute(CREATE_FILE_TABLE);
      isSuccessful = true;
    } catch (final SQLException exception) {
      logging.getErr().printf("Error occurred whilst attempting to create fileset %s %n", location);
    } finally {
      closeConnection(logging);
    }

    return isSuccessful;
  }

  public void upsertFile(final String file, final String path, final Long checksum, final Long size) {
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(UPSERT_FILE);

      preparedStatement.setString(1,"x");
      preparedStatement.setString(Files.FILENAME.getValue(), file);
      preparedStatement.setString(Files.PATH.getValue(), path);
      preparedStatement.setLong(Files.CHECKSUM.getValue(), checksum);
      preparedStatement.setLong(Files.SIZE.getValue(), size);

      preparedStatement.setString(Files.FILENAME.getValue() + Files.values().length, file);
      preparedStatement.setString(Files.PATH.getValue() + Files.values().length, path);
      preparedStatement.setLong(Files.CHECKSUM.getValue() + Files.values().length, checksum);
      preparedStatement.setLong(Files.SIZE.getValue() + Files.values().length, size);

      preparedStatement.executeUpdate();
    } catch (final SQLException exception) {
      logging.getErr().printf("An error occurred whilst inserting data into fileset %s %n", exception);
    } catch (final Exception exception) {
      logging.getErr().printf("An unknown error occurred whilst inserting data into fileset %n");
    }
  }
}
