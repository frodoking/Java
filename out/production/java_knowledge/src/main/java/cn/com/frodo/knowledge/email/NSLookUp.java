package cn.com.frodo.knowledge.email;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import cn.com.frodo.knowledge.common.FileHelper;
import cn.com.frodo.knowledge.mysql.MySqlClient;

public class NSLookUp {
	/**
	 * 查询邮件交换记录
	 * 
	 * @param domain
	 * @return
	 */
	public boolean checkExchange(String domain) {
		return checkBy(domain, Type.MX);
	}

	/**
	 * 查询域名对应的IP地址
	 * 
	 * @param domain
	 * @return
	 */
	public boolean checkIp(String domain) {
		return checkBy(domain, Type.A);
	}

	private boolean checkBy(String domain, int Type) {
		Lookup lookup = null;
		try {
			lookup = new Lookup(domain, Type);
			lookup.run();
		} catch (TextParseException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (lookup.getResult() != Lookup.SUCCESSFUL) {
			System.out.println("ERROR: " + lookup.getErrorString());
			return false;
		} else {
			Record[] answers = lookup.getAnswers();
			for (Record rec : answers) {
				System.out.println(rec.toString());
			}
			return true;
		}
	}

	public static void checkServer() {
		MySqlClient client = new MySqlClient();
		client.connect();

		String sql = "select * from domain;";
		ResultSet set = client.select(sql);

		NSLookUp nslookUp = new NSLookUp();
		Map<String, String> map = new HashMap<String, String>();
		try {
			while (set.next()) {
				boolean canPing = nslookUp.checkExchange(set.getString(2));
				if (!canPing) {
					map.put(set.getString(1), set.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		client.close();

		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public static void checkLocal() {
		String localPath = FileHelper.getCacheDir() + "failDomain4.txt";
		List<String> list = FileHelper.getFileToList(localPath);
		NSLookUp nslookUp = new NSLookUp();
		List<String> errorList = new ArrayList<String>();
		for (String str : list) {
			boolean canPing = nslookUp.checkExchange(str.split(":")[1]);
			if (!canPing) {
				errorList.add(str);
			}
		}

		localPath = FileHelper.getCacheDir() + "failDomain5.txt";
		FileHelper.writeToFile(localPath, errorList);
	}

	public static void checkIpLocal() {
		String localPath = FileHelper.getCacheDir() + "failDomain12.txt";
		List<String> list = FileHelper.getFileToList(localPath);
		NSLookUp nslookUp = new NSLookUp();
		List<String> errorList = new ArrayList<String>();
		for (String str : list) {
			boolean canPing = nslookUp.checkIp(str.split(":")[1]);
			if (!canPing) {
				errorList.add(str);
			}
		}

		localPath = FileHelper.getCacheDir() + "failDomain13.txt";
		FileHelper.writeToFile(localPath, errorList);
	}

	public static void main(String[] args) {
		// checkServer();
		checkIpLocal();
		// NSLookUp nslookUp = new NSLookUp();
		// nslookUp.checkIp("sensor1.com.sg");
	}
}
