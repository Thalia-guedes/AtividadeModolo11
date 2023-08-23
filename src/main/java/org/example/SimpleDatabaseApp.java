package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class SimpleDatabaseApp {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:meubanco.db"; // Nome do banco de dados

        try {

            Connection connection = DriverManager.getConnection(url);

            Statement createTableStatement = connection.createStatement();
            createTableStatement.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)");
            createTableStatement.close();


            Statement insertStatement = connection.createStatement();
            insertStatement.execute("INSERT INTO users (name) VALUES ('Alice')");
            insertStatement.execute("INSERT INTO users (name) VALUES ('Bob')");
            insertStatement.close();


            Statement queryStatement = connection.createStatement();
            ResultSet resultSet = queryStatement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

            resultSet.close();
            queryStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
