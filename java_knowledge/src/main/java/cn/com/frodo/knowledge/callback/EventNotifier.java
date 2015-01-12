package cn.com.frodo.knowledge.callback;

/**
 * 希望接受事件通知的代码必须实现Interestingevent接口，并将自身引用传递给事件通知程序。
 * 
 * @author XuWei4
 * 
 */
public class EventNotifier {
	private static EventNotifier _instance;

	private EventNotifier() {
	}

	public static synchronized EventNotifier getInstance() {
		if (_instance == null) {
			_instance = new EventNotifier();
		}
		return _instance;
	}

	/** 事件对象 */
	private InterestingEvent ie;
	/** 事件发生状态 */
	private boolean somethingHappend = false;

	public void registerEvent(InterestingEvent ie) {
		this.ie = ie;
		somethingHappend = true;
	}

	public void doWork() {
		if (somethingHappend) {
			ie.interestingEvent();
		}
	}

}
