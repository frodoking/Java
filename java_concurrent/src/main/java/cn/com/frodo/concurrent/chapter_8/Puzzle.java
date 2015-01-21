package cn.com.frodo.concurrent.chapter_8;

import java.util.Set;

/**
 * 表示搬箱子之类谜题的抽象类
 * {
 * P：位置类
 * M：移动类
 * }
 * Created by frodo on 2015/1/21.
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P postion, M move);
}
