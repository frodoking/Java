package cn.com.frodo.refactor.model.generalization.step_4_observer_replace_notify;

import junit.framework.Test;

public interface TestListener {
    void addFailure(TestResult result, Test test, Throwable t);

    void addError(TestResult result, Test test, Throwable t);

    void startTest(TestResult result, Test test);

    void endTest(TestResult result, Test test);
}
