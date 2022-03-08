package cn.com.frodo.knowledge.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuwei19 on 2017/5/18.
 * AES对称加密和解密
 */
public class UnifiedManager {

    private static final Logger log = LoggerFactory.getLogger(UnifiedManager.class);

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

    private static String encode(String encodeRules, String content) {
        content = content.substring(content.length() / 7, content.length() * 4 / 5);
        return merge(content, encodeRules);
    }

    public static String merge(String a, String b) {
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();

        int len = aChar.length + bChar.length;
        char[] chars = new char[len];
        char[] maxChar = aChar.length > bChar.length ? aChar : bChar;
        int i = 0;
        int position = 0;
        for (; i < aChar.length && i < bChar.length; i++) {
            chars[i * 2] = aChar[i];
            chars[i * 2 + 1] = bChar[i];
            position = (i + 1) * 2;
        }
        for (; i < maxChar.length; i++) {
            chars[position++] = maxChar[i];
        }
        return String.valueOf(chars);

    }

    public static String get(String content, int length, boolean isDigital, String encodeRules) {
        String base64 = Base64.getEncoder().encodeToString(content.getBytes());
        String encodeContent = encode(encodeRules, base64);
        String filterContent = encodeContent.replaceAll("[^0-9a-zA-Z]+", "");

        log.info("base64: {}", base64);
        log.info("encodeContent: {}", encodeContent);
        log.info("filterContent: {}", filterContent);


        if (isDigital) {
            String result = getSubstring(filterContent, length);
            result = result.toLowerCase();
            log.info("result: {}", result);
            String resultDigital = "";
            for (int i = 0; i < result.length(); i++) {
                char c = result.charAt(i);
                for (String key : T9.keySet()) {
                    if (key.indexOf(c) != -1) {
                        resultDigital += T9.get(key);
                    }
                }
            }
            log.info("resultDigital: {}", resultDigital);
            return resultDigital;
        } else {
            char sChar = SPECIAL_CHARACTER.charAt(base64.length() / length);
            char eChar = SPECIAL_CHARACTER.charAt(base64.length() % length);
            String result = getSubstring(filterContent, length - 2);
            result = sChar + result + eChar;
            log.info("result: {}", result);
            return result;
        }
    }

    public static void main(String[] args) {
        String content = "qq-460642557";
        String encodeRules = "frodo870915";
        UnifiedManager.get(content, 10, false, encodeRules);
        UnifiedManager.get(content, 10, true, encodeRules);
    }
}
