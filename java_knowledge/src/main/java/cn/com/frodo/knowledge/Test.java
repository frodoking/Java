package cn.com.frodo.knowledge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import cn.com.frodo.knowledge.callback.CallMe;
import cn.com.frodo.knowledge.callback.EventNotifier;

public class Test {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		/*
		String head = "{\"billAddressInfo\":{\"uid";
		String spaceStr1 = "\":\"";
		String end = "\"}}";
		StringBuilder str = new StringBuilder();
		str.append(head);
		str.append(spaceStr1);
		str.append("123");
		str.append(end);

		Date d = new Date();
		str.append(d.getYear());
		str.append(d.getMonth() + 1);
		str.append(d.getDay());
		str.append(d.getHours());
		str.append(d.getMinutes());
		str.append(d.getSeconds());

		DateFormat dateFormat = new SimpleDateFormat("yyyyddMMkkmmss");
		System.out.println(dateFormat.format(d));
		System.out.println(str.toString());
		*/
		CallMe call = new CallMe();
		call.registerEvent();
		EventNotifier.getInstance().doWork();
		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("abc", String.valueOf(0));
		hash.keySet();
		hash.values();
		
		
		Set<Entry<String,Object>> entrys = hash.entrySet();
		Iterator<Entry<String,Object>> iter = entrys.iterator();
		while (iter.hasNext()) {
			Entry<String,Object> entry = iter.next();
			entry.getKey();
			entry.getValue();
		}
	}

}
