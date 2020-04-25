package cn.com.frodo.refactor.model.create.step_6_no_singleton;

import java.io.BufferedReader;

/**
 * 将不需要单例的类 实现内联化
 *
 * @author frodoking
 */
public class Blackjack {

    HitStayResponse hitStayResponse;

    public HitStayResponse obtainHitStayResponse(BufferedReader input) {
        hitStayResponse.readFrom(input);
        return hitStayResponse;
    }

    public void setPlayerResponse(HitStayResponse newHitStayResponse) {
        this.hitStayResponse = newHitStayResponse;
    }

    int[] deck;

    public Blackjack(int[] deck) {
        super();
        this.deck = deck;
    }

    public void play() {
        // TODO
        obtainHitStayResponse(null);
    }

    public boolean didDealerWin() {
        return false;
    }

    public Object getDealerTotal() {
        return null;
    }

}
