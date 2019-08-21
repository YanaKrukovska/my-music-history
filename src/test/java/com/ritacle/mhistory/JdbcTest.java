package com.ritacle.mhistory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTest {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-170-5.eu-west-1.compute.amazonaws.com:5432/d1aac9s76vgasj",
                    "ugzdqifhzmadad", "cfb7126cea034067418a48ebe68681392a83214d1e2151092293c3d2fd63d541");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
