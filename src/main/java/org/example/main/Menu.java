package org.example.main;

import org.example.items.Account;
import org.example.repozutory.AccountDao;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private AccountDao accountDao;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.accountDao = new AccountDao();
    }

    public void start() {
        while (true) {
            System.out.println("0 выход");
            System.out.println("1 Войти");
            System.out.println("2 Регистрация");
            System.out.println("3 Восстановить аккаунт");
            System.out.println("4 удалить аккаунт");
            System.out.println("5 список все аккаунтов");
            switch (scanner.nextLine()) {
                case "1" -> {
                }
                case "2" -> addAcc();
                case "3" -> {
                accountRecovery();
                }
                case "0" -> {
                    break;
                }
                case "4" -> {
                    deleteAccount();
                }
                case "5" -> {
                    printAcc();
                }
            }
        }
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
            String email = accountDao.getEmail();
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
        accountDao.getListAcc();
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
