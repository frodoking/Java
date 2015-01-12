package cn.com.frodo.design.pattern.behavior.mediator.demo;

import java.util.ArrayList;
import java.util.List;

import cn.com.frodo.design.pattern.behavior.mediator.demo.Person.Sex;

public class MarriageAgencyImpl implements MarriageAgency {
	List<Man> men = new ArrayList<Man>();
	List<Woman> women = new ArrayList<Woman>();

	@Override
	public void pair(Person person) {
		if (person.sex == Sex.MALE) {
			for (Man m : men) {
				if (m.age == person.requestAge) {
					System.out.println(person.name + "和" + m.name + "配对成功");
				}
			}
		} else if (person.sex == Sex.FEMAIL) {
			for (Woman w : women) {
				if (w.age == person.requestAge) {
					System.out.println(person.name + "和" + w.name + "配对成功");
				}
			}
		}
	}

	@Override
	public void register(Person person) {
		if (person.sex == Sex.MALE) {
			men.add((Man) person);
		} else if (person.sex == Sex.FEMAIL) {
			women.add((Woman) person);
		}
	}
}
