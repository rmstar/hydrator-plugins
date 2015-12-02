/*
 * Copyright © 2015 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.hydrator.plugin.sink;

import co.cask.cdap.api.annotation.Description;
import co.cask.cdap.api.annotation.Name;
import co.cask.cdap.etl.batch.sink.TPFSSinkConfig;

import javax.annotation.Nullable;

/**
 * Configuration for Vertica Plugin
 */
public class VerticaBulkLoadConfig extends TPFSSinkConfig {

  @Name(Vertica.DB_CONNECTION_URL)
  @Description("The connection URL to vertica host through JDBC with the database to connect to. " +
    "Example: jdbc:vertica://VerticaHost:5433/ExampleDB")
  public String dbConnectionURL;

  @Name(Vertica.USER)
  @Description("The user, which must be the database superuser to perform the bulk load.")
  public String user;

  @Name(Vertica.PASSWORD)
  @Description("The password for the given superuser. Leave this field black if the user does not have a password.")
  @Nullable
  public String password;

  @Name(Vertica.TABLE_NAME)
  @Description("The name of the table to which the data will be loaded in bulk")
  public String tableName;

  @Name(Vertica.DELIMITER)
  @Description("The delimiter to use while converting the StructuredRecord to Text as a delimiter. This delimiter " +
    "will later be used while bulk loading data to the table for splitting the fields. The delimiter must not be " +
    "present in your data else it will lead to faliure while bulk loading to Vertica.")
  public String delimiter;

  @Name(Vertica.DIRECT_MODE)
  @Description("Set this 'true' if you want to use Direct mode. You should use this option when you are loading large" +
    " files (over 100MB) into the database. Without this option, your load would fill the WOS and overflow into ROS, " +
    "requiring the Tuple Mover to perform a Moveout on the data in the WOS. It is more efficient to directly load " +
    "into ROS and avoid forcing a moveout. Defaults to 'false'.")
  @Nullable
  public Boolean directMode;

  @Name(Vertica.HDFS_NAMENODE)
  @Description("Namenode of the HDFS. Required while running the plugin in distributed mode. In local mode this field" +
    " should be left empty.")
  @Nullable
  public String hdfsNamenode;

  @Name(Vertica.WEB_HDFS_PORT)
  @Description("Port number of the web HDFS. Required while running the plugin in distributed mode. In local mode " +
    "this field should be left empty.")
  @Nullable
  public String webhdfsPort;

  @Name(Vertica.HDFS_USER)
  @Description("Required while running the plugin in distributed mode. In local mode this field can be left empty." +
    "This HDFS user must have permission to access the TPFS file generated.")
  @Nullable
  public String hdfsUser;

  public VerticaBulkLoadConfig(String name, @Nullable String basePath, @Nullable String filePathFormat,
                               @Nullable String timeZone) {
    super(name, basePath, filePathFormat, timeZone);
  }

  public VerticaBulkLoadConfig(String name, @Nullable String basePath, @Nullable String filePathFormat,
                               @Nullable String timeZone, String dbConnectionURL,
                               String user, String password, String tableName,
                               String delimiter, @Nullable String directMode) {
    super(name, basePath, filePathFormat, timeZone);
  }


  // getters for members variables in TPFSSinkConfig which has protected access
  public String getName() {
    return name;
  }

  public String getBasePath() {
    return basePath;
  }

  /**
   * Vertica config variables
   */
  public static class Vertica {
    public static final String DB_CONNECTION_URL = "dbConnectionURL";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String TABLE_NAME = "tableName";
    public static final String DELIMITER = "delimiter";
    public static final String DIRECT_MODE = "directMode";
    public static final String HDFS_NAMENODE = "hdfsNamenode";
    public static final String WEB_HDFS_PORT = "webhdfsPort";
    public static final String HDFS_USER = "hdfsUser";
  }
}