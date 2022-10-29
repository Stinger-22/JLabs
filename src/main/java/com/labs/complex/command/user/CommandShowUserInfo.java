package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.being.Person;
import com.labs.complex.command.Command;
import com.labs.complex.command.exception.AccessDeniedException;

public class CommandShowUserInfo implements Command {
    private User account;

    public CommandShowUserInfo(IAccount account) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
    }

    @Override
    public void execute() {
        System.out.println("Login: " + account.getLogin());
        Person person = account.getPerson();
        System.out.println("Name: " + person.getName());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("Salary: " + person.getSalary());
        System.out.println("Kids: " + person.getKids());
        System.out.println("Main work: " + person.getMainWork());
        System.out.println("Additional work: " + person.getAdditionalWork());
    }
}