package cn.com.frodo.knowledge.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对文本进行处理的类
 */
public class TextHelper {

    /**
     * 判断对象为空字符串或者为null，如果满足其中一个条件，则返回true
     *
     * @param o
     * @return
     */
    public static boolean isNullOrEmpty(Object object) {
        return object == null || "".equals(object);
    }

    /**
     * 获取富文本内容中的图片链接地址
     *
     * @return
     */
    public static List<String> getTextImageSrc(String text) {
        if (TextHelper.isNullOrEmpty(text))
            return null;
        String regex = "<\\s*[I|i][m|M][g|G]\\s+([^>]*)\\s*>";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(text);
        List<String> list = new ArrayList<String>();
        while (ma.find()) {// 首先判断话题内容中是否有图片
            list.add(ma.group());
        }
        if (list.size() != 0) {// 有图片文件
            List<String> imgSrcList = null;
            String a = null;
            for (String s : list) {
                ma = Pattern.compile("[s|S][R|r][c|C]=[\"|'](.*?)[\"|']").matcher(s);
                if (ma.find()) {
                    a = ma.group();
                    if (imgSrcList == null)
                        imgSrcList = new ArrayList<String>();
                } else {
                    a = null;
                }
                if (a != null) {
                    a = a.replaceAll("[s|S][R|r][c|C]=[\"|']", "").replaceAll("[\"|']", "");
                    imgSrcList.add(a);
                }
            }
            if (imgSrcList != null && imgSrcList.size() != 0)
                return imgSrcList;
            else
                return null;
        } else {
            return null;
        }
    }
}
