package cn.com.frodo;

public class Utils {

    public static boolean isPair(char a, char b) {
        return (a == '(' && b == ')')
                || (a == '{' && b == '}')
                || (a == '[' && b == ']');
    }
}
