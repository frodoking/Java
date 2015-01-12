package cn.com.frodo.refactor.model.generalization.step_4_observer_replace_notify;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestFailure;

public class TestResult {

	protected Vector<TestFailure> fFailures;
	protected Vector<TestFailure> fErrors;
	protected int fRunTests;
	protected boolean fStop;

	private List<TestListener> observers = new ArrayList<TestListener>();

	public TestResult() {
		fFailures = new Vector<TestFailure>(10);
		fErrors = new Vector<TestFailure>(10);
		fRunTests = 0;
		fStop = false;
	}

	public void addObservers(TestListener listener) {
		observers.add(listener);
	}

	public synchronized void addFailure(Test test, Throwable t) {
		fFailures.addElement(new TestFailure(test, t));
		for (Iterator<TestListener> i = observers.iterator(); i.hasNext();) {
			TestListener observer = i.next();
			observer.addFailure(this, test, t);
		}
	}

	public synchronized void addError(Test test, Throwable t) {
		fErrors.addElement(new TestFailure(test, t));
		for (Iterator<TestListener> i = observers.iterator(); i.hasNext();) {
			TestListener observer = i.next();
			observer.addError(this, test, t);
		}
	}

	public synchronized void startTest(Test test) {
		for (Iterator<TestListener> i = observers.iterator(); i.hasNext();) {
			TestListener observer = i.next();
			observer.endTest(this, test);
		}
	}

	public synchronized void endTest(Test test) {
		for (Iterator<TestListener> i = observers.iterator(); i.hasNext();) {
			fRunTests++;
			TestListener observer = i.next();
			observer.startTest(this, test);
		}
	}

}
