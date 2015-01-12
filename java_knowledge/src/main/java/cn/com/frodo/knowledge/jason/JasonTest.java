package cn.com.frodo.knowledge.jason;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class JasonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		String json = "{\"name\":\"reiz\"}";
		JSONObject jsonObj = JSONObject.fromObject(json);
		String name = jsonObj.getString("name");

		jsonObj.put("initial", name.substring(0, 1).toUpperCase());

		String[] likes = new String[] { "JavaScript", "Skiing", "Apple Pie" };
		jsonObj.put("likes", likes);

		Map<String, String> ingredients = new HashMap<String, String>();
		ingredients.put("apples", "3kg");
		ingredients.put("sugar", "1kg");
		ingredients.put("pastry", "2.4kg");
		ingredients.put("bestEaten", "outdoors");
		jsonObj.put("ingredients", ingredients);

		System.out.println(jsonObj);
		*/
		method();
	}

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
}
