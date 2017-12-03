package cn.com.frodo;

public final class Log {
    public static void i(String regx, String... args) {
        System.out.println(String.format(regx, args));
    }
}
