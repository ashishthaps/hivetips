//Connecting to HDInsight from outside of the cluster via JDBC
package com.jdbcapp;
import java.sql.SQLException;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.sql.DriverManager;
/**
 * Hello world!
 *
 */
public class App 
{
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        Connection con = DriverManager.getConnection("jdbc:hive2://xxxx.azurehdinsight.net:443/default;transportMode=http;ssl=true;httpPath=/hive2", "xxxx", "xxx");
        Statement stmt = con.createStatement();
        String sql = "select count(*) from hivesampletable";
        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {

            System.out.println(res.getString(1));
        }

    }
    }
