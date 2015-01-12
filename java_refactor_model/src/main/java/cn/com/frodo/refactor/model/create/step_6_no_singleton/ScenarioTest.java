package cn.com.frodo.refactor.model.create.step_6_no_singleton;

import junit.framework.TestCase;

public class ScenarioTest extends TestCase {
	public void testDealerStandsWhenPlayerBusts() {
		int[] deck = { 10, 9, 7, 2, 6 };
		Blackjack blackjack = new Blackjack(deck);
		blackjack.setPlayerResponse(new TestAlwaysHitResonse());
		blackjack.play();

		assertTrue("dealer Wins", blackjack.didDealerWin());
		assertTrue("player loses", !blackjack.didDealerWin());

		assertEquals("dealer total", 11, blackjack.getDealerTotal());
		assertEquals("dealer total", 23, blackjack.getDealerTotal());
	}

}
