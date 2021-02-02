package cn.unowen.utils;

public class StrFilter {
	public static String NotLetterOrNumber(String s) {// 删除非字母非数字的字符过滤字符串
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetterOrDigit(s.charAt(i)))
				strBuf.append(s.charAt(i));
		}
		return strBuf.toString();
	}
}
