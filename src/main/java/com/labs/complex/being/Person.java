package com.labs.complex.being;

import java.util.Objects;

/**
 * Class which unites personal data of user
 */
public class Person {
    private String name;
    private String surname;
    private double salary;
    private int kids;
    private String mainWork;
    private String additionalWork;

    /**
     * Constructor of person
     * @param name name of person
     * @param surname surname of person
     * @param salary salary of person
     * @param kids number of kids person has
     * @param mainWork main work of person (can be null)
     * @param additionalWork additional work of person (can be null)
     */
    public Person(String name, String surname, double salary, int kids, String mainWork, String additionalWork) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.kids = kids;
        this.mainWork = mainWork;
        this.additionalWork = additionalWork;
    }

    /**
     * Get name of person
     * @return name of person
     */
    public String getName() {
        return name;
    }

    /**
     * Get surname of person
     * @return surname of person
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Get salary of person
     * @return salary of person
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Get number of kids person has
     * @return number of kids person has
     */
    public int getKids() {
        return kids;
    }

    /**
     * Get main work of person
     * @return main work of person
     */
    public String getMainWork() {
        return mainWork;
    }

    /**
     * Get additional work of person
     * @return additional work of person
     */
    public String getAdditionalWork() {
        return additionalWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.salary, salary) == 0 && kids == person.kids && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(mainWork, person.mainWork) && Objects.equals(additionalWork, person.additionalWork);
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', surname='" + surname + "', salary=" + salary +
                ", kids=" + kids + ", mainWork='" + mainWork + "', additionalWork='" + additionalWork + "'}";
    }
}
