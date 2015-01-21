package cn.com.frodo.concurrent.chapter_8;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 在解决器中找不到解答
 * {
 * 解决方案
 * 1、ValueLatch中的getValue超时控制；
 * 2、设置特定的结束条件，例如只搜索特定数量的位置；
 * 3、另外还可以提供一种取消机制，用于用户自己决定何时停止搜索
 * }
 * Created by frodo on 2015/1/21.
 */
public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {

    private final AtomicInteger taskCount = new AtomicInteger(0);

    public PuzzleSolver(Puzzle<P, M> puzzle) {
        super(puzzle);
    }

    @Override
    protected Runnable newTask(P p, M m, PuzzleNode<P, M> n) {
        return new CountingSolverTask(p, m, n);
    }

    protected class CountingSolverTask extends SolverTask {

        public CountingSolverTask(P pos, M move, PuzzleNode<P, M> prev) {
            super(pos, move, prev);
        }

        @Override
        public void run() {
            try {
                super.run();
            } finally {
                if (taskCount.decrementAndGet() == 0) {
                    solution.setValue(null);
                }
            }
        }
    }
}
