package cn.com.frodo.design.pattern.creational.builder.pattern;

public class T410 extends Computer {
	private String graphicCard;

	public T410() {
		this.setType("ThinkPad T410i");
	}

	public String getGraphicCard() {
		return graphicCard;
	}

	public void setGraphicCard(String graphicCard) {
		this.graphicCard = graphicCard;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
