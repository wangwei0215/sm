package com.sm.business.function;

import org.apache.commons.lang.time.DateFormatUtils;
import org.iframework.commons.util.MD5Util;
import org.iframework.commons.util.fast.L;
import org.iframework.commons.util.fast.V;
import org.iframework.support.spring.function.BaseCommonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 业务工具方法
 * 
 * @author sunhao
 * 
 */
public class BaseBusinessUtil extends BaseCommonUtil {

	public static String getIdNumber() {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 7; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return new Date().getTime() + sb.toString();
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param source
	 *            过滤前字符串
	 * @return 解码后的字符串
	 */
	public static String replaceSpecialChar(String source) {
		if (V.isEmpty(source)) {
			return "";
		}
		source = source.trim();
		source = source.replace("\\", "");
		source = source.replaceAll("\r|\n|\t", "");
		// 如果有新的特殊字符需要替换可以直接加一个
		return source;
	}

	/**
	 * 解码特殊字符(ascii)
	 * 
	 * @param str
	 *            解码前字符串
	 * @return 解码后的字符串
	 */
	public static String unEscape(String str) {
		try {
			// 判断字符串是否为空,为空时直接返回原数据
			if (V.isEmpty(str)) {
				return str;
			}
			// 获取&的下标位置
			int firstAmp = str.indexOf('&');
			if (firstAmp < 0)
				return str;
			// 字符串转为 StringWriter
			StringWriter stringWriter = new StringWriter(
					(int) ((double) str.length() + (double) str.length() * 0.10000000000000001D));
			// 保存下标0 - firstAmp位置的字符串
			stringWriter.write(str, 0, firstAmp);
			// 获取字符串字符长度
			int len = str.length();
			// 从firstAmp 开始循环字符串
			for (int i = firstAmp; i < len; i++) {
				// 获取单个字符
				char c = str.charAt(i);
				// 判断单个字符是否是&,为 true 时，进行转码操作
				if (c == '&') {
					// 设置查找分号的开始位置：&下标位置+1
					int nextIdx = i + 1;
					// 查找分号是否存在
					int semiColonIdx = str.indexOf(';', nextIdx);
					// 分号不存在时直接输出字符
					if (semiColonIdx == -1) {
						stringWriter.write(c);
						continue;
					}
					// 判断字符串中是否存在&
					int amphersandIdx = str.indexOf('&', i + 1);
					if (amphersandIdx != -1 && amphersandIdx < semiColonIdx) {
						stringWriter.write(c);
						continue;
					}
					// 截取字符串： 从&开始到分号结束
					String entityContent = str.substring(nextIdx, semiColonIdx);
					int entityValue = -1;
					// 截取到的字符串的长度
					int entityContentLen = entityContent.length();
					// 判断字符串长度是否大于0
					if (entityContentLen > 0) {
						// 删除字符串中空格
						entityContent = entityContent.replaceAll(" ", "");
						// 判断字符串是否以‘#’开始
						if (entityContent.charAt(0) == '#') {
							if (entityContentLen > 1) {
								char isHexChar = entityContent.charAt(1);
								try {
									switch (isHexChar) {
									case 88: // 'X'
									case 120: // 'x'
										entityValue = Integer.parseInt(entityContent.substring(2), 16);
										break;
									default:
										// 获取10进制数字
										entityValue = Integer.parseInt(entityContent.substring(1), 10);
										break;
									}
									if (entityValue > 65535)
										entityValue = -1;
								} catch (NumberFormatException e) {
									entityValue = -1;
								}
							}
						}
					}
					// entityValue = -1 时，表示字符未做改变,直接输出
					if (entityValue == -1) {
						stringWriter.write(38);
						stringWriter.write(entityContent);
						stringWriter.write(59);
					} else {
						// 输出解码后的char
						stringWriter.write(entityValue);
					}
					i = semiColonIdx;
				} else {
					// 判断单个字符是否是&,为 false 时，直接输出&
					stringWriter.write(c);
				}
			}
			// 返回string类型数据
			return stringWriter.toString();
		} catch (Exception e) {
			return str;
		}
	}

	/**
	 * 【日期处理】通过字符串日期参数返回格式化日期字符串
	 * 
	 * @param time
	 *            日期字符串 （例：2012-10-29 11:46:23）
	 * @param format
	 *            日期格式字符串 （例：yyyy-MM-dd hh:mm:ss 默认）
	 * @return 返回的日期格式字符串 （例：yyyy-MM-dd hh:mm:ss 默认）
	 */
	public static String toDate(String time, String format) {
		if (time == null || "".equals(time)) {
			return "";
		}
		if (format == null || "".equals(format)) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			return "";
		}
		return DateFormatUtils.format(d, format);
	}

	/**
	 * 【日期处理】通过字符串日期参数返回格式化日期字符串
	 *
	 * @param time
	 *            日期字符串 （例：2012-10-29 11:46:23） 日期格式字符串 （例：yyyy-MM-dd hh:mm:ss
	 *            默认）
	 * @return 返回的日期格式字符串 （例：yyyy-MM-dd hh:mm:ss 默认）
	 */
	public static String toDate(String time) {
		Long timeLong = Long.parseLong(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
		Date date;
		try {
			date = sdf.parse(sdf.format(timeLong));
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 【日期处理】通过日期参数返回格式化日期字符串
	 * 
	 * @param date
	 *            日期 （例：2012-10-29 11:46:23）
	 * @param format
	 *            日期格式字符串 （例：yyyy-MM-dd hh:mm:ss 默认）
	 * @return 返回的日期格式字符串 （例：yyyy-MM-dd hh:mm:ss 默认）
	 */
	public static String toDate(Date date, String format) {
		if (date != null) {
			if (format == null || "".equals(format)) {
				format = "yyyy-MM-dd";
			}
			return DateFormatUtils.format(date, format);
		} else {
			return "";
		}
	}

	/**
	 * 【日期处理】通过日期参数返回格式化日期字符串
	 * 
	 * @param date
	 *            日期 （例：2012-10-29 11:46:23）
	 * @return 返回的日期格式字符串 （例：yyyy-MM-dd默认）
	 */
	public static String toDate(Object date) {
		if (date != null && date instanceof Date) {
			Date d = (Date) date;
			return DateFormatUtils.format(d, "yyyy-MM-dd");
		} else {
			return "";
		}
	}

	/**
	 * 【日期处理】获取当前日期时间
	 * 
	 * @param format
	 *            日期格式字符串 （例：yyyy-MM-dd hh:mm:ss 默认）
	 * @return 返回的日期格式字符串 （例：yyyy-MM-dd hh:mm:ss 默认）
	 */
	public static String toNow(String format) {
		format = format == null || "".equals(format) ? "yyyy-MM-dd hh:mm:ss" : format;
		return DateFormatUtils.format(new Date(), format);
	}

	/**
	 * 【日期处理】获取当前日期时间戳
	 * 
	 * @return 返回的日期时间戳格式
	 */
	public static Long toTime() {
		return new Date().getTime();
	}

	public static boolean checkoutSign(String sign, String str) {
		L.i("sign:" + sign + " str:" + str);
		L.i(MD5Util.MD5Encode(str + "2D5A35CED55D41438C86BCCB33D186FD", 1));
		return V.isEquals(sign, MD5Util.MD5Encode(str + "2D5A35CED55D41438C86BCCB33D186FD", 1));
	}

	/**
	 * list转字符串
	 */
	public static String listToString(@SuppressWarnings("rawtypes") List list, char separator) {
		return org.apache.commons.lang.StringUtils.join(list.toArray(), separator);
	}

	public static String getFileString(InputStream is) {
		String result = "";
		byte b[] = new byte[1024];
		int len = 0;
		int temp = 0; // 所有读取的内容都使用temp接收
		try {
			while ((temp = is.read()) != -1) { // 当没有读取完时，继续读取
				b[len] = (byte) temp;
				len++;
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(new String(b, 0, len));
		result = new String(b, 0, len);

		return result;
	}

	/**
	 * 脱敏
	 * 
	 * @param str,a,b
	 * @return
	 */
	public static String replace(String str, int a, int b) {
		if (str == null || "".equals(str)) {
			return "";
		}
		StringBuilder sb = new StringBuilder(str);
		return sb.replace(a, sb.length() - b, "********").toString();
	}

	/**
	 * 获取N周前的时间
	 */
	public static Date getAweekAgo(Date date, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.WEEK_OF_MONTH, -num);
		Date m = c.getTime();
		return m;
	}
	
    public static Date getDate(String dateString){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
    	try {
			 date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return date;
    }

	/**
	 * 得到日期字符串
	 * */
	public static String getStringDate(Date date, String dateFmt) {
		if (V.isEmpty(dateFmt)) {
			dateFmt = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFmt);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		return sdf.format(c.getTime());
	}
    
	/**
	 * 获取今天之前30天日期的数组
	 * */
    public static String[] getDateArrByNow(){
    	List<String> listDate = new ArrayList<String>();
    	 Calendar calendar = Calendar.getInstance();
    	 calendar.setTime(new Date());
    	 for (int i = 0; i < 30; i++) {
    		 calendar.add(Calendar.DAY_OF_MONTH, -1);
    		 listDate.add( getStringDate(calendar.getTime(), "yyyy/MM/dd"));
		}
    	int size = listDate.size();  
    	Collections.reverse(listDate);
	    String[] arrDate = (String[])listDate.toArray(new String[size]);
    	return arrDate;
    }
    
	/**
	 * 获取今天之前30天日期的数组
	 * */
    public static List<String> getDateListByNow(){
    	List<String> listDate = new ArrayList<String>();
    	 Calendar calendar = Calendar.getInstance();
    	 calendar.setTime(new Date());
    	 for (int i = 0; i < 30; i++) {
    		 calendar.add(Calendar.DAY_OF_MONTH, -1);
    		 listDate.add( getStringDate(calendar.getTime(), "yyyy-MM-dd"));
		}
    	Collections.reverse(listDate);
    	return listDate;
    }
    
    
    /**
     * List返回带""的字符串
     * */
    public static String getStringByList(List<String> list){
    	if(V.isEmpty(list)){
    		return "";
    	}
    	StringBuffer sb = new StringBuffer();
    	for (String string : list) {
    		sb.append("\"");
			sb.append(string);
			sb.append("\",");
		}
    	String res = sb.toString();
    	res = res.substring(0, res.length()-1);
    	return res;
    }
    
    /**
     * 补全日期，没有的补全value 0
     * */
    public static String getAllString(List<Map<String, Object>> list){
    	List<String> dateListByNow = getDateListByNow();
    	//循环30天日期，有取值，没有补0，拼成字符串返回
    	StringBuffer sb = new StringBuffer();
    	for (String string : dateListByNow) {
    		String mapKey = "0";
			for (Map<String, Object> map : list) {
				if(V.isNotEmpty(map.get(string))){
					mapKey = map.get(string).toString();
				}
			}
			sb.append(mapKey);
			sb.append(",");
		}
    	String res = sb.toString();
    	res = res.substring(0, res.length()-1);
    	return res;
    }
    
    public static void main(String[] args) {
    	List<Map<String, Object>> list = new ArrayList<>();
    	Map<String, Object> map1 = new HashMap<>();
    	map1.put("2019-03-12", 100);
    	list.add(map1);
    	Map<String, Object> map2 = new HashMap<>();
    	map1.put("2019-03-13", 200);
    	list.add(map2);
    	Map<String, Object> map3 = new HashMap<>();
    	map1.put("2019-03-15", 300);
    	list.add(map3);
    	String allString = getAllString(list);
    	System.out.println(allString);
	}
}
