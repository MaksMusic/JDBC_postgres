package org.example.model;

import org.example.repository.AccountDao;
import org.example.repository.AccountDaoImpl;

import javax.script.ScriptContext;
import java.util.Scanner;

public class AuthService {
    private boolean state;
    private Account account;
    private AccountDao accountDao = new AccountDaoImpl();
    private Scanner scanner = new Scanner(System.in);

    public boolean connect(Account account){
        for (Account account1 : accountDao.getListAcc()) {
            if (account.getLogin().equals(account1.getLogin())){
                if (account.getPassword().equals(account1.getPassword())) {
                    this.account =  account1;
                    state = true;
                    System.out.println("You are logged in");
                    return true;
                }
            }
        }
        System.out.println("wrong login or password");
        return false;
    }

    public boolean isState() {
        return state;
    }

    public Account getAccount() {
        return account;
    }

    public void exit() {
        account = null;
        state = false;
    }

    public void logInMenu() {
        System.out.println("------------------------------");
        System.out.println("Логин:" + account.getLogin());
        System.out.println("1 изменить пароль");
        System.out.println("2 изменить почту");
        System.out.println("3 exit");
        System.out.println("------------------------------");
        String answer= scanner.nextLine();

    }
}
