package cn.com.frodo.knowledge.callback;

public class CallMe implements InterestingEvent {
	
	public void registerEvent(){
		EventNotifier.getInstance().registerEvent(this);
	}

	@Override
	public void interestingEvent() {
		System.out.println("oh,something interesting has happended!");
		System.out.println("do some action!");

	}
}
