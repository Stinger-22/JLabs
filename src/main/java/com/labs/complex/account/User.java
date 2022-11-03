package com.labs.complex.account;

import com.labs.complex.being.Action;
import com.labs.complex.being.Benefit;
import com.labs.complex.being.Person;
import com.labs.complex.being.Tax;

import java.util.List;
import java.util.Objects;

/**
 * Class which represents user account
 */
public class User implements IAccount {
    private String login;
    private Person person;
    private List<Tax> taxList;
    private List<Benefit> benefitList;
    private List<Action> actionList;

    /**
     * User account constructor
     * @param login login of user
     * @param person personal data of user
     * @param taxList user's taxes
     * @param benefitList user's benefits
     * @param actionList user's actions
     */
    public User(String login, Person person, List<Tax> taxList, List<Benefit> benefitList, List<Action> actionList) {
        this.login = login;
        this.person = person;
        this.taxList = taxList;
        this.benefitList = benefitList;
        this.actionList = actionList;
    }

    /**
     * Get login of user
     * @return login of user
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get user's person
     * @return user's person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Get user's taxes
     * @return user's taxes
     */
    public List<Tax> getTaxList() {
        return taxList;
    }

    /**
     * Get user's benefits
     * @return user's benefits
     */
    public List<Benefit> getBenefitList() {
        return benefitList;
    }

    /**
     * Get user's actions
     * @return user's actions
     */
    public List<Action> getActionList() {
        return actionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(person, user.person) && Objects.equals(taxList, user.taxList) && Objects.equals(benefitList, user.benefitList) && Objects.equals(actionList, user.actionList);
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
