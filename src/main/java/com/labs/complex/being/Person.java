package com.labs.complex.being;

import java.util.Objects;

public class Person {
    private String name;
    private String surname;
    private double salary;
    private int kids;
    private String mainWork;
    private String additionalWork;

    public Person(String name, String surname, double salary, int kids, String mainWork, String additionalWork) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.kids = kids;
        this.mainWork = mainWork;
        this.additionalWork = additionalWork;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getSalary() {
        return salary;
    }

    public int getKids() {
        return kids;
    }

    public String getMainWork() {
        return mainWork;
    }

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
