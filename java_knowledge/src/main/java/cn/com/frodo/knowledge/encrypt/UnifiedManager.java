package cn.com.frodo.knowledge.encrypt;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuwei19 on 2017/5/18.
 * AES对称加密和解密
 */
@Slf4j
public class UnifiedManager {

    private static final String SPECIAL_CHARACTER = "!@#$%^&*";
    private static final Map<String, Integer> T9 = new HashMap<>();

    static {
        T9.put("abc", 2);
        T9.put("def", 3);
        T9.put("ghi", 4);
        T9.put("jkl", 5);
        T9.put("mno", 6);
        T9.put("pqrs", 7);
        T9.put("tuv", 8);
        T9.put("wxyz", 9);
    }

    private static String getSubstring(String string, int length) {
        String result = "";
        int index;
        int count = 0;
        while (result.length() < length) {
            int step = result.length() % 2 == 0 ? 1 : 2;
            if (count % 2 == 0) {
                index = result.length() + step;
            } else {
                index = string.length() - result.length() - step;
            }
            result += string.charAt(index);
            count++;
        }
        return result;
    }

    public static String get(String content, int length, boolean isDigital, String encodeRules) {
        String base64 = Base64.getEncoder().encodeToString(content.getBytes());
        String aesContent = AESUtil.AESEncode(encodeRules, content);
        String filterContent = aesContent.replaceAll("[^0-9a-zA-Z]+", "");

        log.info("base64: " + base64 + "\n aesContent: " + aesContent + "\n filterContent: " + filterContent);
        if (isDigital) {
            String result = getSubstring(filterContent, length);
            result = result.toLowerCase();
            log.info("result: " + result);
            String resultDigital = "";
            for (int i = 0; i < result.length(); i++) {
                char c = result.charAt(i);
                for (String key : T9.keySet()) {
                    if (key.indexOf(c) != -1) {
                        resultDigital += T9.get(key);
                    }
                }
            }
            log.info("resultDigital: " + resultDigital);
            return resultDigital;
        } else {
            char sChar = SPECIAL_CHARACTER.charAt(base64.length() / length);
            char eChar = SPECIAL_CHARACTER.charAt(base64.length() % length);
            String result = getSubstring(filterContent, length - 2);
            result = sChar + result + eChar;
            log.info("result: " + result);
            return result;
        }
    }

}
