package cn.com.frodo.knowledge.encodedecode;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.frodo.MockInterface;
import net.sf.json.JSONObject;

public class JasonTest implements MockInterface {

	public static void method() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "json");
		map.put("bool", Boolean.TRUE);
		map.put("int", new Integer(1));
		map.put("arr", new String[] { "a", "b" });
		map.put("func", "function(i){ return this.arr[i]; }");
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json);
	}

	public static void method2() {
		String deviceName = "vivo_X5Max+";
			String regEx="[#%&+=?\\u0020\\\\]";
			Pattern p   =   Pattern.compile(regEx);
			Matcher m   =   p.matcher(deviceName);
			String s =   m.replaceAll("").trim();
		System.out.println(s);
	}

	@Override
	public void doTest() {
		method2();
	}
}
