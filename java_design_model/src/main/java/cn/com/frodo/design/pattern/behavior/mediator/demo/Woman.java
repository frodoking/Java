package cn.com.frodo.design.pattern.behavior.mediator.demo;

public class Woman extends Person {

    public Woman(String name, int age, int requestAge, MarriageAgency agency) {
        super(name, age, Sex.FEMAIL, requestAge, agency);
    }
}
