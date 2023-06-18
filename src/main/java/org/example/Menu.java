package org.example;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private AccountService accountService;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.accountService = new AccountService();
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
                case "1" -> {}
                case "2" -> addAcc();
                case "3" -> {}
                case "0" -> {break;}
                case "4" -> {deleteAccount();}
                case "5"-> {printAcc();}
            }
        }
    }

    private void printAcc(){
        accountService.getListAcc();
    }

    private void deleteAccount(){
        System.out.println("введите login для удаления");
        String login = scanner.nextLine();

        accountService.deleteAcc(login);

    }

    private void addAcc() {
        System.out.println("Введите логин до 30 символов");
        String login = scanner.nextLine();

        System.out.println("Введите пароль до 30 символов");
        String password = scanner.nextLine();

        Account account = new Account(login,password);
        accountService.addAccDataBase(account);
    }
}
