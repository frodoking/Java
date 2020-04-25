package cn.com.frodo.algorithm.sort;

public abstract class Sort {
    public abstract void sort(int[] data);

    public void swap(int[] data, int i, int j) {
        if (data[i] == data[j]) {
            return;
        }
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }

    public void show(int[] data, String flag) {
        System.out.println("=======" + flag + "======");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "\t");
        }
        System.out.println();
    }
}
