package com.akadatsky;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.tools.DeleteDbFiles;

public class Main {

    public static void main(String[] args) {

        // delete the database named 'test' in the user home directory
        DeleteDbFiles.execute("~", "test", true);

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
            Statement stat = conn.createStatement();

            stat.execute("CREATE TABLE test(id int primary key, name varchar(255))");
            stat.execute("INSERT INTO test VALUES(1, 'Hello')");
            ResultSet rs;
            rs = stat.executeQuery("SELECT * FROM test");

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
