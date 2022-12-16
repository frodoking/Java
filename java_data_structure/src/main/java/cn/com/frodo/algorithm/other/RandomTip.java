package cn.com.frodo.algorithm.other;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Random;

/**
 * 随机红包功能
 *
 * @author frodoking
 * @ClassName: RandomTip
 * @date 2022/3/20
 */
public class RandomTip implements IAlgorithm {

    private final Random random = new Random();

    @Override
    public void exec() {
        int[] arr = randomNums(1, 20, 100, 10);
        Arrays.show(arr, "RandomTip");
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        System.out.println(total);

        int[] arr2 = {724, 502, 500, 726, 2761, 2740, 500, 502, 522, 523};
        total = 0;
        for (int i = 0; i < arr2.length; i++) {
            total += arr2[i];
        }
        System.out.println(total);
    }

    private int[] randomNums(int min, int max, int sum, int num) {
        int[] arr = new int[num];
        java.util.Arrays.fill(arr, min);

        int leftTotal = sum - min * num;
        for (int i = 0; i < num; i++) {
            int rand = random.nextInt(leftTotal * 2 / (num - i + 1));
            if (rand > leftTotal - min) {
                rand = leftTotal - min;
            }
            if (rand > max - min) {
                rand = max - min;
            }
            arr[i] += rand;
            leftTotal -= arr[i];
            if (leftTotal < 0) {
                break;
            }
        }

//        shuffle(arr);

        return arr;
    }

    private void shuffle(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int nextIdx = random.nextInt(i);
            Arrays.swap(array, i, nextIdx);
        }
    }
}
