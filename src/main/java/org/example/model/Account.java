package org.example.model;

public class Account {
    private String login;
    private String password;
    private String email;
    private String question;
    private String answer;


    public Account(String login, String password, String email, String question, String answer) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.question = question;
        this.answer = answer;
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
