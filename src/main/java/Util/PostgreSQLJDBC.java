/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLJDBC {

    private static final String USERNAME = "ayjuiqmyejccal";
    private static final String DATABASE = "d9e2e4mkn730n";
    private static final String PASSWORD = "86439d947ed45061eba4ad99fc8ec6b67e3377749674ad553ddbd7eaae9645d1";
    private static final String DATABASE_URL = "jdbc:postgresql://ec2-54-225-200-15.compute-1.amazonaws.com:5432/"+DATABASE+"?sslmode=require";
    
    //localhost
    /*private static final String USERNAME = "postgres";
    private static final String DATABASE = "financas";
    private static final String PASSWORD = "root";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
    */
    
    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");

            c = DriverManager
                    .getConnection(DATABASE_URL,
                            USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");
        return c;
    }
    
    public static void main(String[] args) {
        System.out.println(getConnection() != null);
    }
}
