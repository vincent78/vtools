/*
 * author 		:vincent
 * DateTime	:2012-1-17  下午8:18:50
 * Description:
 * 
 */

package com.vtools.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StringUtil
{
	public static int PASSWORD_LEVEL_LOW = 1;

	public static int PASSWORD_LEVEL_MIDDLE = 2;

	public static int PASSWORD_LEVEL_HIGH = 3;

	/**
	 * 截取字符串方法，超出部分用...表示
	 * 
	 * @param s
	 *            字符串
	 * @param len
	 *            长度
	 * @return
	 */
	public static String substring(String s, int len)
	{
		if (s == null)
		{
			return "";
		}
		if (s.getBytes().length <= len)
		{
			return s;
		}
		return substring2(s, len) + "...";
	}

	/**
	 * 截取英文字符串方法，超出部分用...表示，单词不截断
	 * 
	 * @param s
	 *            字符串
	 * @param len
	 *            长度
	 * @return
	 */

	public static String substring4e(String s, int len)
	{
		if (s == null)
		{
			return "";
		}
		if (s.getBytes().length <= len)
		{
			return s;
		}
		while (!" ".equals(s.substring(len, len + 1)) && len > 0)
		{
			len--;
		}
		return substring2(s, len) + "...";

	}

	/**
	 * 拉丁语系截取字符串
	 * 
	 * @param s
	 * @param len
	 * @return
	 */
	public static String substring4e2(String s, int len)
	{
		if (s == null)
		{
			return "";
		}
		int length = s.length();
		int byteLen = s.getBytes().length;
		int lg = length > byteLen ? byteLen : length;
		if (lg <= len)
		{
			return s;
		}

		while (!" ".equals(s.substring(len, len + 1)) && len > 0)
		{
			len--;
		}
		return s.substring(0, len) + "...";
	}

	/**
	 * 亚洲语系(中文，日文，朝鲜语)截取字符串
	 * 
	 * @param s
	 * @param len
	 *            字节长度
	 * @return
	 */
	public static String multiSubstring(String s, int len)
	{
		if (s == null)
		{
			return "";
		}
		try
		{
			if (s.getBytes("Unicode").length <= len)
			{
				return s;
			}
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return multiSubstring2(s, len) + "...";
	}

	public static String multiSubstring2(String s, int length)
	{
		String s1 = s;
		try
		{
			byte[] bytes = s.getBytes("Unicode");
			int n = 0; // 表示当前的字节数
			int i = 2; // 要截取的字节数，从第3个字节开始

			for (; i < bytes.length && n < length; i++)
			{
				// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
				if (i % 2 == 1)
				{
					n++; // 在UCS2第二个字节时n加1
				} else
				{
					// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
					if (bytes[i] != 0)
					{
						n++;
					}
				}
			}

			// 如果i为奇数时，处理成偶数
			if (i % 2 == 1)
			{
				// 该UCS2字符是汉字时，去掉这个截一半的汉字
				if (bytes[i - 1] != 0)
				{
					i = i - 1;
				}
				// 该UCS2字符是字母或数字，则保留该字符
				else
				{
					i = i + 1;
				}
			}

			s1 = new String(bytes, 0, i, "Unicode");
		} catch (UnsupportedEncodingException u)
		{
			u.printStackTrace();
		}
		return s1;
	}

	public static String substring2(String s, int len)
	{
		if (s == null)
		{
			return "";
		}
		if (len <= 0)
		{
			return "";
		}
		// 从右往左找到第一个小于0的byte，然后往右推到len的位置，如果是一个奇数，那么加1
		byte[] bs = s.getBytes();

		// 1. 找到第一个非中文字节
		if (len > bs.length)
		{
			len = bs.length;
		}
		int n = len - 1;
		while (bs[n] < 0)
		{
			n = n - 1;
			if (n < 0)
			{
				break;
			}
		}
		// n 是第一个中文字节
		n = n + 1;

		// 2. 从中文字节算起到len，是奇数还是偶数
		int r = len - n;
		if (r % 2 == 1)
		{
			len = len - 1;
		}

		byte[] bs2 = new byte[len];
		for (int i = 0; i < bs2.length; i++)
		{
			bs2[i] = bs[i];
		}
		return new String(bs2);
	}

	/**
	 * 去除字符串空格
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static String trim(String s)
	{
		if (s == null)
		{
			return "";
		}

		s = s.replaceAll("　", " ");
		s = s.replaceAll("\\s+", " ");

		return s.trim();
	}

	/**
	 * 以\s+格式截取字符串返回list
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static List<String> split(String s)
	{
		List<String> list = new ArrayList<String>();

		s = trim(s);
		if (s == null)
		{
			return list;
		}
		String[] rs = s.split("\\s+");

		for (int i = 0; i < rs.length; i++)
		{
			if (rs[i].trim().length() > 0)
			{
				list.add(rs[i]);
			}
		}

		return list;
	}

	/**
	 * 截取字符串返回string数组
	 * 
	 * @param s
	 *            字符串
	 * @param delim
	 *            截取方式
	 * @return
	 */
	public static String[] split2Array(String s, String delim)
	{
		List<String> list = split(s, delim);
		return (String[]) list.toArray(new String[0]);
	}

	/**
	 * 截取字符串
	 * 
	 * @param s
	 *            字符串
	 * @param delim
	 *            截取方式
	 * @return
	 */
	public static List<String> split(String s, String delim)
	{
		List<String> list = new ArrayList<String>();

		s = trim(s);
		if (s == null)
		{
			return list;
		}
		String[] rs = s.split(delim);

		for (int i = 0; i < rs.length; i++)
		{
			if (rs[i].trim().length() > 0)
			{
				list.add(rs[i]);
			}
		}

		return list;
	}

	/**
	 * 字符在字符串中出现的次数
	 * 
	 * @param string
	 * @param a
	 * @return
	 */
	public static int occurTimes(String string, String a)
	{
		int pos = -2;
		int n = 0;

		while (pos != -1)
		{
			if (pos == -2)
			{
				pos = -1;
			}
			pos = string.indexOf(a, pos + 1);
			if (pos != -1)
			{
				n++;
			}
		}
		return n;
	}

	/**
	 * 是否为空 null 或 ""
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static boolean nullOrSpace(String s)
	{
		if (s == null)
		{
			return true;
		}
		if (s.trim().equals(""))
		{
			return true;
		}
		return false;
	}

	/**
	 * 是否为空
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static boolean isNull(String s)
	{
		return isBlank(s);
	}

	/**
	 * 返回字符串如果为null 则返回""
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static String nullValue(String s)
	{
		return s == null ? "" : s.trim();
	}

	/**
	 * 返回字符串 如果为null 返回defaultValue
	 * 
	 * @param s
	 *            字符串
	 * @param defaultValue
	 *            默认值
	 * @return
	 */

	public static String nullValue(Object s, String defaultValue)
	{
		return s == null ? defaultValue : s.toString();
	}

	public static String nullValue(String s, String defaultValue)
	{
		return (s == null || s.length() == 0) ? defaultValue : s;
	}

	/**
	 * 重载方法
	 * 
	 * @param s
	 *            对象
	 * @return
	 */
	public static String nullValue(Object s)
	{
		return s == null ? "" : s.toString();
	}

	/**
	 * 重载方法
	 * 
	 * @param s
	 * @return
	 */
	public static String nullValue(long s)
	{
		return s <= 0 ? "" : String.valueOf(s);
	}

	/**
	 * 重载方法
	 * 
	 * @param s
	 * @return
	 */
	public static String nullValue(int s)
	{
		return s <= 0 ? "" : "" + s;
	}

	public static String nullValue(float s)
	{
		return s <= 0 ? "" : "" + s;
	}

	public static boolean isBlank(Object s)
	{
		return s == null || s.toString().trim().length() == 0;
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static String upperCaseFirst(String s)
	{
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 字符串是否在字符串数组中 忽略大小写
	 * 
	 * @param url
	 *            字符串
	 * @param allUrl
	 *            字符串数组
	 * @return
	 */
	public static boolean in(String url, String[] allUrl)
	{
		for (int i = 0; i < allUrl.length; i++)
		{
			if (allUrl[i].equalsIgnoreCase(url))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串是否在字符串数组中 不忽略大小写
	 * 
	 * @param url
	 *            字符串
	 * @param allUrl
	 *            字符串数组
	 * @return
	 */
	public static boolean inWithCase(String url, String[] allUrl)
	{
		for (int i = 0; i < allUrl.length; i++)
		{
			if (allUrl[i].equals(url))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回字符
	 * 
	 * @param n
	 *            第几个
	 * @return
	 */
	public static String getChar(int n)
	{
		return String.valueOf((char) n);
	}

	/**
	 * 返回字符
	 * 
	 * @param n
	 *            第几个
	 * @return
	 */
	public static String getCol(int n)
	{
		return String.valueOf((char) (n + 65));
	}

	/**
	 * 字符串中是否含有中文
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static boolean includeChinese(String s)
	{
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if (c > 100)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 格式化sql
	 * 
	 * @param sql
	 *            字符串
	 * @return
	 */
	public static String escapeSql(String sql)
	{
		if (sql == null)
		{
			return null;
		}
		// TODO 非常重要，需要进一步丰富
		sql = sql.replaceAll("'", "");
		sql = sql.replaceAll("\\\\", "");
		sql = sql.replaceAll("_", "\\_");
		sql = sql.replaceAll("%", "\\%");
		sql = sql.replaceAll("\\(", "\\\\(");
		sql = sql.replaceAll("\\)", "\\\\)");
		return sql;
	}

	/**
	 * 
	 * @param password
	 * @param intensity
	 * @param pwdLength
	 * @return 密码强度 1 为低等强度 2为中等强度 3为高等强度
	 */
	public static int validPassword(String password, String intensity,
			int pwdLength)
	{
		String charGroup[][] =
		{
				{ "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
						"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
						"x", "y", "z" },
				{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
						"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
						"X", "Y", "Z" },
				{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" } };
		int level = 0;
		boolean flagGroup[] =
		{ false, false, false };
		for (int i = 0; i < charGroup.length; i++)
		{
			String charSmallGroup[] = charGroup[i];

			for (int j = 0; j < password.length(); j++)
			{
				if (flagGroup[i] == true)
				{
					continue;
				}

				String str = password.substring(j, j + 1);
				if (inWithCase(str, charSmallGroup))
				{
					level++;
					flagGroup[i] = true;
				}
				System.out.println(i + "*" + j);
			}
		}

		return level;
	}

	public static String getFormatedStr(int num)
	{
		return String.format("%04d", num);
	}

	public static String getFormatedStr2(int num)
	{
		return String.format("%02d", num);
	}

	public static String getFormatedStr(String formatStr, int num)
	{
		return String.format(formatStr, num);
	}

	public static String getFormatedStr(String formatStr, float num)
	{
		return String.format(formatStr, num);
	}

	/**
	 * 将字符串转换为ASCII 注：转换为MHT文件时使用
	 * 
	 * @param str
	 * @return
	 */
	public static String convertToAscii(String str)
	{
		StringBuffer retVal = new StringBuffer();
		char[] chars = str.toCharArray();
		for (char c : chars)
		{
			retVal.append("&#").append((int) c).append(";");
		}
		return retVal.toString();
	}

	public static String getStringInMht(Object obj)
	{
		return convertToAscii(nullValue(obj, ""));
	}
}
