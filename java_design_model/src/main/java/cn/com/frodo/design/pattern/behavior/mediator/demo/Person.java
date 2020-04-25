package cn.com.frodo.design.pattern.behavior.mediator.demo;

public abstract class Person {
    String name;
    int age;
    Sex sex;
    int requestAge;
    MarriageAgency agency;

    public Person(String name, int age, Sex sex, int requestAge, MarriageAgency agency) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.requestAge = requestAge;
        this.agency = agency;

        agency.register(this);
    }

    public void findPartner() {
        agency.pair(this);
    }

    enum Sex {
        MALE, FEMAIL
    }
}
