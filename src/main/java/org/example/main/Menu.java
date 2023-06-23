package org.example.main;

import org.example.items.Account;
import org.example.repozutory.AccountDao;

import java.util.Scanner;

public class Menu {
    private boolean authorizationAcc = false;
    private Scanner scanner;
    private AccountDao accountDao;
    private Account account;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.accountDao = new AccountDao();
    }

    public void start() {

        menu:
        while (true) {
            hello();
            System.out.println("0 exit");
            System.out.println("1 Log In");
            System.out.println("2 Registration");
            System.out.println("3 Recover account");
            System.out.println("4 Delete account");
            System.out.println("5 Delete all account");
            System.out.println("6 List account");
            switch (scanner.nextLine()) {
                case "0" -> {break menu;}
                case "1" -> {authorization();}
                case "2" -> addAcc();
                case "3" -> {accountRecovery();}
                case "4" -> {deleteAccount();}
                case "5" -> {deleteAllAccount();}
                case "6" -> {printListAcc();}
            }
        }
    }

    private void hello(){
        if (authorizationAcc) System.out.println("Hello " + account.getLogin());
    }

    private boolean authorization(){
        Account account = inputLoginAndPass();
        for (Account account1 : accountDao.getListAcc()) {
            if (account.getLogin().equals(account1.getLogin())){
                if (account.getPassword().equals(account1.getPassword())) {
                    authorizationAcc = true;
                    this.account =account;
                    System.out.println("You are logged in");
                    return true;
                }
            }
        }
        System.out.println("wrong login or password");
        return false;
    }

    private Account inputLoginAndPass(){
        System.out.println("Enter login");
        String login = scanner.nextLine();


        System.out.println("Enter password");
        String pass = scanner.nextLine();

        return new Account(login,pass);
    }


    private boolean accountRecovery() {
        System.out.println("Enter your account login");
        String login = scanner.nextLine();
        if (login.isEmpty()) {
            System.out.println("you entered an empty string (try again)");
            return false;
        }
        String question = accountDao.getQuestion(login);
        if (question == null) {
            System.out.println("Login not found");
            return false;
        }

        System.out.println("Question: " + question);
        System.out.print("Answer: ");
        String answer = scanner.nextLine();

        if (answer.equals(accountDao.getAnswer(login))) {
            String email = accountDao.getEmail(login);
            if (!email.isEmpty()) {
                System.out.println("You new password has been sent to you email " + email);
                return true;

            }else {
                System.out.println("Email not found");
            }
        }
        return false;


    }


    private void printListAcc() {
        for (Account account : accountDao.getListAcc()) {
            System.out.println("Login: " + account.getLogin() + ", pass:"+account.getPassword());
            System.out.println("question: "+ account.getQuestion() + " , answer "+ account.getAnswer());
            System.out.println("email:" + account.getEmail() );
            System.out.println("---------------------------------------------------------------------");
        }
    }

    private void deleteAccount() {
        System.out.println("Enter login to delete");
        String login = scanner.nextLine();

        accountDao.deleteAcc(login);

    }

    private void addAcc() {
        System.out.println("Enter login up to 30 characters");
        String login = scanner.nextLine();

        System.out.println("Enter password up to 30 characters");
        String password = scanner.nextLine();

        System.out.println("Enter email");
        String email = scanner.nextLine();

        System.out.println("Select question (for password recovery)");
        String question = scanner.nextLine();

        System.out.println("Enter the answer to the question");
        String answer = scanner.nextLine();

        Account account = new Account(login, password, email, question, answer);
        accountDao.addAccDataBase(account);
    }

    private boolean deleteAllAccount(){//проверить
       int numberDeletedAccounts = accountDao.deleteAll();
       if (numberDeletedAccounts > 0){
           System.out.println("accounts deleted ( "+numberDeletedAccounts + " count)");
           return true;
       }else {
           System.out.println("0 deleted accounts");
       }return false;

    }
}
