package org.example;

import java.sql.*;
/** класс для запросов к бд*/
public class AccountService {
    //созданный объект для получения у него метода который возвращает Connect для подключения к бд
private  DatabaseConnectionManager databaseConnectionManager;

    public AccountService() {
        this.databaseConnectionManager  = new DatabaseConnectionManager(
                "jdbc:postgresql://127.0.0.1:5432/loginAcc","postgres","postgres");
    }

    //добавление account в бд
    public void addAccDataBase(Account account){
        String insertQuery = "INSERT INTO user_acc (login_user,password_user) VALUES (?, ?)";
        try (Connection conn = databaseConnectionManager.getConnection();
             //PreparedStatement используется для выполнения запроса к бд
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)
        ) {
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
    public void getListAcc(){
        String insertQuery = "SELECT * FROM user_acc";
        //String insertQuery = "SELECT  FROM user_acc";
        try (Connection conn = databaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
             ResultSet rs = preparedStatement.executeQuery();
        ) {


            while (rs.next()){
                int id = rs.getInt("id_user");
                String login = rs.getString("login_user");
                String password = rs.getString("password_user");
                System.out.printf("id:%d  login:%s  pass:%s\n",id,login,password);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAcc(String login){
        String insertQuery  = "DELETE FROM user_acc WHERE login_user = ?";
       try (Connection connection = databaseConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){
           preparedStatement.setString(1,login);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("аккаунт был удален (" + rowsAffected+"  - rowsAffected");

       }catch (SQLException e){

       }

    }





}
