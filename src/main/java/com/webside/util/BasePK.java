package com.webside.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * getPK,获得数据库使用的一个String型唯一主键 20位，同一微秒内3000个不会重复
 * 
 * @author shadow
 * 
 */
public class BasePK {
	private static String[] ls = new String[3000];
	private static int li = 0;

	public synchronized static String getPK() {
		String lo = getpk();
		for (int i = 0; i < 3000; i++) {
			String lt = ls[i];
			if (lt == lo) {
				lo = getPK();
				break;
			}
		}
		ls[li] = lo;
		li++;
		if (li == 3000) {
			li = 0;
		}
		return lo;
	}

	private static String getpk() {
		String a = (new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()))
				.trim();
		String d = (String.valueOf(Math.random())).substring(2, 8).trim();
		return new StringBuffer(a + d).toString();
	}

	public static String getpk10() {
		Random random = new Random();
		int ran_i = random.nextInt(100000000);
		while (ran_i < 100000000) {
			ran_i = random.nextInt(100000000);
			String ran_t = String.valueOf(System.currentTimeMillis());
			String ran_s = ran_t.substring(11, ran_t.length());
			String ret = String.valueOf(ran_i) + ran_s;
			if (ret != null && ret.length() == 10) {
				return ret;
			}
		}

		return null;
	}
	
	//lilang 获取指定长度的数字和字符串的随机码
	public static String getCharAndNumr(int length){     
	    String val = "";     
	    Random random = new Random();     
	    for(int i = 0; i < length; i++) {     
	        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字     
	        //字符串
	        if("char".equalsIgnoreCase(charOrNum)){     
	            //int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母     
	        	int choice =65;   //取得大写字母
	            val += (char) (choice + random.nextInt(26));     
	        }     
	        // 数字
	        if("num".equalsIgnoreCase(charOrNum)){     
	            val += String.valueOf(random.nextInt(10));     
	        }     
	    }          
	    return val;     
	}   
	
	/*public static void main(String[] args) {
		// for(int i=0; i<1000000;i++){
		String aa = BasePK.getpk10();
		// if(aa.length()!=10){
		System.out.println(aa);
		// }
		// }

	}*/
}
