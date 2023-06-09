package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.*;

/**
 * VLan 资源池
 * https://dream.blog.csdn.net/article/details/129191192
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.thread,
        company = AlgorithmPoint.Company.huawei)
public class VlanResourcePool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String vlan = scanner.nextLine();
        int num = scanner.nextInt();

        String[] vlanArray = vlan.split(",");
        List<String> res = new ArrayList<>();
        for (int i = 0; i < vlanArray.length; i++) {
            String cVLan = vlanArray[i];

            if (Objects.equals(cVLan, num + "")) {
                continue;
            }

            if (cVLan.contains("-")) {
                String[] array = cVLan.split("-");

                int start = Integer.parseInt(array[0]);
                int end = Integer.parseInt(array[1]);

                if (start > num || end < num) {
                    res.add(cVLan);
                    continue;
                }

                int aStart = start;
                int bStart = start;
                int aEnd = end;
                int bEnd = end;
                for (int j = start; j <= end; j++) {
                    if (num == j) {
                        aEnd = j - 1;
                        bStart = j + 1;
                    }
                }

                if (aStart <= aEnd) {
                    res.add(aStart == aEnd ? aStart + "" : aStart + "-" + aEnd);
                }
                if (bStart <= bEnd) {
                    res.add(bStart == bEnd ? bStart + "" : bStart + "-" + bEnd);
                }
            } else {
                res.add(cVLan);
            }
        }

        res.sort(Comparator.comparing(o -> (o.contains("-") ? o.split("-")[0] : o)));

        System.out.println(String.join(",", res));
    }
}
