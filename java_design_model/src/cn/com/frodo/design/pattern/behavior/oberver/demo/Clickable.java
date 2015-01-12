package cn.com.frodo.design.pattern.behavior.oberver.demo;

public interface Clickable {
	void click();
	void addClickableObserver(ClickableObserver observer);
	void removeClickableObserver(ClickableObserver observer);
}
