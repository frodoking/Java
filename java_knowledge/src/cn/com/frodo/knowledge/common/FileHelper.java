package cn.com.frodo.knowledge.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileHelper {

	private static final String ENCODING_UTF_8 = "UTF-8";
	private static final String DEFAULT_DIR = "d:\\cache";
	private static final String NEXT_LINE = System.getProperty("line.separator");

	public static String getCacheDir() {
		File dir = new File(DEFAULT_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
			System.err.println(dir + "已创建！");
		} else {
			System.err.println(dir + "已经存在！");
		}
		return dir.getAbsolutePath() + File.separator;
	}

	/**
	 * 创建文本文件.
	 * 
	 * @throws IOException
	 * 
	 */
	public static File creatFile(String path) throws IOException {
		File filename = new File(path);
		if (!filename.exists()) {
			filename.createNewFile();
			System.err.println(filename + "已创建！");
		} else {
			System.err.println(filename + "已经存在！");
		}
		return filename;
	}

	/**
	 * 读取文本文件.
	 * 
	 * @throws Exception
	 * 
	 */
	public static String readTxtFile(String path) throws Exception {
		String read;
		FileReader fileread;
		File filename = creatFile(path);
		fileread = new FileReader(filename);
		@SuppressWarnings("resource")
		BufferedReader bufread = new BufferedReader(fileread);
		String readStr = "";
		while ((read = bufread.readLine()) != null) {
			readStr = readStr + read + "\r\n";
		}
		System.out.println("文件内容是:" + "\r\n" + readStr);
		return readStr;
	}

	/**
	 * @param filePath
	 * @return
	 */
	public static List<String> getFileToList(String filePath) {
		List<String> list = new ArrayList<String>();
		String lineTxt = "";
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), ENCODING_UTF_8);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.length() != 0 && !"".equals(lineTxt)) {
						list.add(lineTxt);
					}
				}
				read.close();
			} else {
				System.out.println("*** getFileToList >> 找不到指定的文件 ***");
			}
		} catch (Exception e) {
			System.out.println("*** getFileToList >> 读取文件内容出错" + "当前行 ：" + lineTxt + " ***");
			e.printStackTrace();
		}

		return list;
	}

	public static void writeToFile(String path, List<String> list) {
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append(str);
			sb.append(NEXT_LINE);
		}

		try {
			FileUtils.writeStringToFile(file, sb.toString(), ENCODING_UTF_8, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(String path, String content) {
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			FileUtils.writeStringToFile(file, content + NEXT_LINE, ENCODING_UTF_8, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 将文件中指定内容的第一行替换为其它内容.
	 * 
	 * @param oldStr
	 *            查找内容
	 * @param replaceStr
	 *            替换内容
	 */
	public static void replaceTxtByStr(String path, String oldStr, String replaceStr) {
		String temp = "";
		try {
			File filename = creatFile(path);
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			// 保存该行前面的内容
			for (; (temp = br.readLine()) != null && !temp.equals(oldStr);) {
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));
			}

			// 将内容插入
			buf = buf.append(replaceStr);

			// 保存该行后面的内容
			while ((temp = br.readLine()) != null) {
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(temp);
			}

			br.close();
			FileOutputStream fos = new FileOutputStream(filename);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
