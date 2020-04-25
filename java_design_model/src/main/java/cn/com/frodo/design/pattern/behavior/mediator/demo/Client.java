package cn.com.frodo.design.pattern.behavior.mediator.demo;

/**
 * 调停模式 ： 用一个中介对象封装一系列对象的交互，中介者使各对象不需要显式地相互作用，
 * 从而时期耦合松散，而且可以独立地改变他们之间的交互
 *
 * @author frodoking
 */
public class Client {
    public static void main(String args[]) {
        MarriageAgency marriageAgency = new MarriageAgencyImpl();
        Person m1 = new Man("John", 23, 20, marriageAgency);
        Person m2 = new Man("Mike", 27, 22, marriageAgency);
        Person w1 = new Man("Mary", 22, 27, marriageAgency);
        Person w2 = new Man("Jane", 20, 23, marriageAgency);

        m1.findPartner();
        m2.findPartner();
    }
}
