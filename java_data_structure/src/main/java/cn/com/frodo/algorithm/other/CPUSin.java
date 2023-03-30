package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * cpu显示y=sin(x),x是时间，y是cpu占有率[0,100%]
 * Created by frodoking on 2019/3/17 上午11:32.
 * Description: https://www.jianshu.com/p/1d69c8ebf829
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.thread)
public class CPUSin implements IAlgorithm {

    static int coreNum = Runtime.getRuntime().availableProcessors();
    static CyclicBarrier k = new CyclicBarrier(coreNum);
    static int cycle = 60;
    static double PI = 3.1415926535;
    static int interval = 2000;

    public Runnable run() {
        return new Runnable() {
            int current = 0;

            @Override
            public void run() {
                // 每一份的长度
                double portion = 2 * PI / cycle;
                try {
                    // 使用CyclicBarrier让所有线程在此开始执行
                    k.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                while (true) {
                    long currentTimeMillis = System.currentTimeMillis();
                    while (System.currentTimeMillis() - currentTimeMillis <= interval) {
                        // 当前x的值
                        double value = portion * current;
                        // y=sin(x)当前y的值,因为y的范围是[-1,1], 所以(y+1) * 0.5 得到[0,1]
                        double percent = (Math.sin(value) + 1) * 0.5;
                        // 当前繁忙的时间百分比
                        double busy = percent * interval;
                        // 空闲百分比
                        double idle = (1 - percent) * interval;
                        // 比如一个统计窗口时间是1000ms，那么cpu 10%则应该是前100ms繁忙，后900ms空闲
                        // 当前繁忙
                        while (System.currentTimeMillis() - currentTimeMillis < busy);
                        // 后续释放cpu
                        try {
                            Thread.sleep((long) idle);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // 进入下一个统计间隔
                    current++;

                    if (current == cycle) {
                        current = current % cycle;
                    }
                }
            }
        };
    }

    @Override
    public void exec() {
        for (int i = 0; i < coreNum; i++) {
            new Thread(run()).start();
        }
    }
}
