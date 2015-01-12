package cn.com.frodo.design.pattern.structual.facade.demo;

public class Secretary {
	private Airport airport = new Airport();
	private Chauffeur chauffeur = new Chauffeur();
	private Hotel hotel = new Hotel();
	private Restaurant restaurant = new Restaurant();

	/**
	 * 安排出差
	 * 
	 * @param to
	 * @param days
	 */
	public void trip(String to, int days) {
		airport.bookTicket("青岛", to);
		chauffeur.drive("机场");
		hotel.reserve(days);
	}

	/**
	 * 安排饭局
	 * 
	 * @param num
	 */
	public void repast(int num) {
		restaurant.reserve(num);
		chauffeur.drive("酒店");
	}
}
