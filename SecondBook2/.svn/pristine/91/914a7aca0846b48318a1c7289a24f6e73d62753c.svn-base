package com.hautbook.util;

import java.util.Random;

/**
 * 
 * @author caiyao
 *
 * @function 根据验证码字符串生成类
 * 
 */
public class CaptchaStringGenerator {
	// 去掉了容易混淆的0、o , l（小写l）换成了大写L
	private static String[] chars = {"1","2","3","4","5","6","7","8","9",
									 "a","b","c","d","e","f","g","h","i","j","k",
									 "L","m","n","p","q","r","s","t","u","v","w",
									 "x","y","z"} ; 
	private static Random random = new Random();
	/**
	 * 获取随机验证码字符串
	 * @param num : 随机字符串的个数
	 * @return ： 验证码字符串
	 */
	synchronized public static String getCaptchaString(int num){
		String randomString = "" ;
		for(int i = 0 ; i < num ; i ++){
			randomString += chars[random.nextInt(chars.length)]; 
		}
		return randomString ;
	}
}
