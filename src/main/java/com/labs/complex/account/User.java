package com.labs.complex.account;

import com.labs.complex.being.Action;
import com.labs.complex.being.Benefit;
import com.labs.complex.being.Person;
import com.labs.complex.being.Tax;

import java.util.List;

public class User implements IAccount {
    private String login;
    private Person person;
    private List<Tax> taxList;
    private List<Benefit> benefitList;
    private List<Action> actionList;

    public User(String login, Person person, List<Tax> taxList, List<Benefit> benefitList, List<Action> actionList) {
        this.login = login;
        this.person = person;
        this.taxList = taxList;
        this.benefitList = benefitList;
        this.actionList = actionList;
    }

    public String getLogin() {
        return login;
    }

    public Person getPerson() {
        return person;
    }

    public List<Tax> getTaxList() {
        return taxList;
    }

    public List<Benefit> getBenefitList() {
        return benefitList;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", person=" + person +
                ", taxList=" + taxList +
                ", benefitList=" + benefitList +
                ", actionList=" + actionList +
                '}';
    }
}
