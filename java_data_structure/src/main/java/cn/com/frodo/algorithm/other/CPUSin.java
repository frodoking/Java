package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * Created by frodoking on 2019/3/17 上午11:32.
 * Description:
 */
public class CPUSin  implements IAlgorithm {

    public void sin() throws InterruptedException {
        final int SAMPLING_COUNT = 200;
        final double PI = 3.1415926535;
        final int TOTAL_AMPLITUDE = 300;

        int[] busySpan = new int[SAMPLING_COUNT];
        int amplitude = TOTAL_AMPLITUDE / 2;
        double radian = 0.0;
        double radianIncrement = 2.0 / (double) SAMPLING_COUNT;
        for (int i = 0; i < SAMPLING_COUNT; i++) {
            busySpan[i] = (int) (amplitude + Math.sin(PI * radian) * amplitude);
            radian += radianIncrement;
        }

        long startTime = 0;
        for (int j = 0; ; j = (j + 1) % SAMPLING_COUNT) {
            startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) <= busySpan[j])
                ;
            Thread.sleep(TOTAL_AMPLITUDE - busySpan[j]);
        }
    }

    @Override
    public void exec() {
        try {
            sin();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
