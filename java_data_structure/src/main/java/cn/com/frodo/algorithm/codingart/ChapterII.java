package cn.com.frodo.algorithm.codingart;

public class ChapterII {

    public static void main(String[] args) {
        ChapterII chapterII = new ChapterII();
        int[] data = {12, 5, 78, 46, 51, 34, 22, 32, 20, 2, 8, 17, 42};
        chapterII.findKMaxFromN(data, 5);
        System.out.println(chapterII.findCommonDivisor(42, 30));
    }

    /**
     * 2.5从data中寻找k个大的数,先寻找第k大的数，再遍历寻找这k个数
     *
     * @param data
     * @param K
     */
    public void findKMaxFromN(int[] data, int K) {
        int max = data[0];
        int min = data[0];
        for (int i : data) {
            max = max < i ? i : max;
            min = min > i ? i : min;
        }

        int vmax = max;
        int vmin = min;
        int vmid = 0;
        while (vmax - vmin > 1.0) {
            vmid = (int) (vmin + (vmax - vmin) * 0.5);
            if (find(data, vmid) > K) {
                vmin = vmid;
            } else {
                vmax = vmid;
            }
        }
        System.out.println(String.format("[%d,%d]", vmin, vmax));
    }

    public int find(int[] array, int vmid) {
        int num = 0;
        for (int i : array) {
            num = i >= vmid ? ++num : num;
        }
        return num;
    }

    /**
     * 2.7 寻找a b 的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public int findCommonDivisor(int x, int y) {
        if (x < y)
            return findCommonDivisor(y, x);
        if (y == 0)
            return x;
        else {
            if (isEven(x)) {
                if (isEven(y)) {
                    return findCommonDivisor(x >> 1, y >> 1) << 1;
                } else {
                    return findCommonDivisor(x >> 1, y);
                }
            } else {
                if (isEven(y))
                    return findCommonDivisor(x, y >> 1);
                else
                    return findCommonDivisor(y, x - y);
            }
        }
    }

    public boolean isEven(int data) {
        return (data & 1) == 0;
    }
}
