package org.example.repozutory;

import org.example.items.Account;

import java.sql.*;

/**
 * класс для запросов к бд
 */
public class AccountDao {
    private final String url;
    private final String userName;
    private final String password;

    public AccountDao() {
        this.url = "jdbc:postgresql://127.0.0.1:5432/loginAcc";
        this.userName = "postgres";
        this.password = "postgres";

    }

    //добавление account в бд
    public void addAccDataBase(Account account) {
        String insertQuery = "INSERT INTO user_acc (login_user,password_user) VALUES (?, ?)";

        //PreparedStatement используется для выполнения запроса к бд
        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            //Данный метод устанавливает значение параметра запроса, который указывается в виде индекса
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setString(2, account.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    //вывести содержимое таблицы в бд
    public void getListAcc() {
        String insertQuery = "SELECT * FROM user_acc";
        //String insertQuery = "SELECT  FROM user_acc";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_user");
                String login = rs.getString("login_user");
                String password = rs.getString("password_user");
                System.out.printf("id:%d  login:%s  pass:%s\n", id, login, password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAcc(String login) {
        String insertQuery = "DELETE FROM user_acc WHERE login_user = ?";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, login);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("аккаунт был удален (" + rowsAffected + "  - rowsAffected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
