package org.example.repository;

import org.example.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * класс для запросов к бд
 */
public class AccountDaoImpl implements AccountDao {
    private final String url;
    private final String userName;
    private final String password;

    public AccountDaoImpl() {
        this.url = "jdbc:postgresql://127.0.0.1:5432/loginAcc";
        this.userName = "postgres";
        this.password = "postgres";

    }

    //добавление account в бд
    public void addAccDataBase(Account account) {
        String insertQuery = "INSERT INTO account (login_user,password_user,email,question,answer) VALUES (?,?,?,?,?)";

        //PreparedStatement используется для выполнения запроса к бд
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            //Данный метод устанавливает значение параметра запроса, который указывается в виде индекса
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getQuestion());
            preparedStatement.setString(5, account.getAnswer());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    //вывести содержимое таблицы в бд
    public List<Account> getListAcc() {
        List<Account> accountList = new ArrayList<>();

        String insertQuery = "SELECT * FROM account order by login_user";
        //String insertQuery = "SELECT  FROM user_acc";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_user");
                String login = rs.getString("login_user");
                String password = rs.getString("password_user");
                String email = rs.getString("email");
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                accountList.add(new Account(login, password, email, question, answer));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accountList;
    }

    public void deleteAcc(final String login) {
        String insertQuery = "DELETE FROM account WHERE login_user = ?";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, login);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("аккаунт был удален (" + rowsAffected + "  - rowsAffected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getQuestion(final String loginAcc) {
        String insertQuery = "Select question from account where login_user = ?";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, loginAcc);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("question");
            }

        } catch (SQLException e) {
            System.out.println("sql exception");
        }

        return null;
    }

    public String getAnswer(final String loginAcc) {
        String insertQuery = "Select answer from account where login_user = ?";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, loginAcc);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("answer");
            }

        } catch (SQLException e) {
            System.out.println("sql exception");
        }

        return null;
    }

    public String getEmail(String login) {
        String insertQuery = "Select email from account where login_user = ?";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("email");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int deleteAll() {
        String deleteAllQuery = "delete from account";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteAllQuery)) {
             return preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


}
