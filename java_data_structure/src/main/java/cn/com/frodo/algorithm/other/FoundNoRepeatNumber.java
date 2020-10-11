package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * @author frodoking
 * @ClassName: FoundNoRepeatNumber
 * @date 2020/10/11
 */
public class FoundNoRepeatNumber implements IAlgorithm {

    @Override
    public void exec() {
        int[] array = {1, 1, 0, 2, 3, 4, 4, 5, 5, 7};
        foundNoRepeatNumber(array);
    }

    private void foundNoRepeatNumber(int[] array) {
        int i = 0;
        for (int j = 1; j < array.length; j++) {
            if (array[i] != array[j]) {
                System.out.println(">>" + array[i]);
                if (j == array.length - 1) {
                    System.out.println(">>>>>" + array[j]);
                }
                i = j;
            } else {
                i = j + 1;
                if (i == array.length - 1) {
                    System.out.println(">>>" + array[i]);
                } else {
                    j = j + 1;
                }
            }
        }
    }
}
