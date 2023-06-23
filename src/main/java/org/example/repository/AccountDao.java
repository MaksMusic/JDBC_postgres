package org.example.repository;

import org.example.items.Account;

import java.util.List;

public interface AccountDao {
    void addAccDataBase(Account account);

    List<Account> getListAcc();

    void deleteAcc(final String login);

    String getQuestion(final String loginAcc);

    String getAnswer(final String loginAcc);

    public String getEmail(String login);

    public int deleteAll();


}