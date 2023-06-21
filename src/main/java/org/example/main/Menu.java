package org.example.main;

import org.example.items.Account;
import org.example.repozutory.AccountDao;

import java.util.List;
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
            System.out.println("0 выход");
            System.out.println("1 Войти");
            System.out.println("2 Регистрация");
            System.out.println("3 Восстановить аккаунт");
            System.out.println("4 удалить аккаунт");
            System.out.println("5 список все аккаунтов");
            switch (scanner.nextLine()) {
                case "0" -> {break menu;}
                case "1" -> {authorization();}
                case "2" -> addAcc();
                case "3" -> {accountRecovery();}
                case "4" -> {deleteAccount();}
                case "5" -> {printAcc();}
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
                    System.out.println("вы авторизировались");
                    return true;
                }
            }
        }
        System.out.println("не верный логин ил пароль");
        return false;
    }

    private Account inputLoginAndPass(){
        System.out.println("введите логин");
        String login = scanner.nextLine();


        System.out.println("введите пароль");
        String pass = scanner.nextLine();

        return new Account(login,pass);
    }

    private boolean accountRecovery() {
        System.out.println("введите логин от аккаунта");
        String login = scanner.nextLine();
        if (login.isEmpty()) {
            System.out.println("вы ввели пустую строку (попробуйте снова)");
            return false;
        }
        String question = accountDao.getQuestion(login);
        if (question == null) {
            System.out.println("Логин не найден ");
            return false;
        }

        System.out.println("Вопрос: " + question);
        System.out.print("Ответ: ");
        String answer = scanner.nextLine();

        if (answer.equals(accountDao.getAnswer(login))) {
            String email = accountDao.getEmail(login);
            if (!email.isEmpty()) {
                System.out.println("Ваш новый пароль отправлен на почту " + email);
                return true;

            }else {
                System.out.println("почта не найдена");
            }
        }
        return false;


    }


    private void printAcc() {
        for (Account account : accountDao.getListAcc()) {
            System.out.println("Login: " + account.getLogin() + ", pass:"+account.getPassword());
            System.out.println("question: "+ account.getQuestion() + " , answer "+ account.getAnswer());
            System.out.println("email:" + account.getEmail() );
            System.out.println("---------------------------------------------------------------------");
        }
    }

    private void deleteAccount() {
        System.out.println("введите login для удаления");
        String login = scanner.nextLine();

        accountDao.deleteAcc(login);

    }

    private void addAcc() {
        System.out.println("Введите логин до 30 символов");
        String login = scanner.nextLine();

        System.out.println("Введите пароль до 30 символов");
        String password = scanner.nextLine();

        System.out.println("Введите email");
        String email = scanner.nextLine();

        System.out.println("Выберите вопрос (для восстановления пароля)");
        String question = scanner.nextLine();

        System.out.println("Введите ответ на вопрос до 30 символов");
        String answer = scanner.nextLine();

        Account account = new Account(login, password, email, question, answer);
        accountDao.addAccDataBase(account);
    }
}
