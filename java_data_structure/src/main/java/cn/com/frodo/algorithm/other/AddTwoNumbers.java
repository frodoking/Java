package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 第一步：异或先将不同位置的数相加
 * 第二步：与将重合位置的数据进一位表示异或掉的数据
 * 第三步：将第一步和第二部的数据继续进行相加，直到进位为0为止
 *
 * @author frodoking
 * @ClassName: AddTwoNumbers
 * @date 2020/10/11
 */
public class AddTwoNumbers implements IAlgorithm {

    @Override
    public void exec() {
        System.out.println(addTwoNumbers(156, 79));
    }

    private int addTwoNumbers(int num1, int num2) {
        int sum, carry;
        do {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;

            num1 = sum;
            num2 = carry;
        } while (carry != 0);

        return sum;
    }
}
