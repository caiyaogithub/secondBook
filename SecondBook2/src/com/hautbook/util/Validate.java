package com.hautbook.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hautbook.JavaBean.PostBean;
import com.hautbook.exception.ValidateException;

/**
 * 
 * @author caiyao
 *
 * @function 执行对各种字段的校验 
 *
 */
public class Validate {

	/**
	 * 校验学院是否存在
	 * 
	 * @return
	 */
	public static boolean validateDept(String dept) {
		dept = dept.trim() ;
		String[] depts = { "粮油食品学院", "机电工程学院", "土木建筑学院", "信息科学与工程学院", "化学化工学院",
				"生物工程学院", "材料科学与工程学院", "电气工程学院", "管理学院", "经济贸易学院", "理学院",
				"外语学院", "法学院", "新闻与传播学院", "设计艺术学院", "国际教育学院", "中英国际学院",
				"职业技术学院", "体育学院", "思想政治教育学院", "继续教育学院" };
		if(dept != null){
			for (int i = 0; i < depts.length; i++) {
				if (dept.equals(depts[i])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 校验用户名（仅仅是昵称不包括学院）是否正确
	 * 
	 * @return
	 */
	public static boolean validateName(String name ) {
		/**
		 * 只能包含字母和数字，长度为1~10字符（包括中文和英文字符）
		 * 
		 */
		if(name != null){
			String regex = "([\u4e00-\u9fa5]|[a-z]|[A-Z]|[0-9]){1,10}";
			return name.matches(regex);
		}
		return false ;
	}
	/**
	 * 校验 Email格式是否正确
	 * @return
	 */
	public static boolean validateEmail(String email){
		/**
		 * email为可选字段
		 */
		/**
		 * TODO email 正则表达式
		 */
		return true ;
		}
	/**
	 * 校验密码
	 * @param password
	 * @return 如果密码校验成功返回true，否者返回false
	 */
	public static boolean validatePassword(String password){
		/**数字和字符，区分大小写，6~20个字符**/
		String regex = "([0-9]|[a-z]|[A-Z]){6,20}" ;
		return password.matches(regex) ;
	}
	/**
	 * 校验图片格式 ： 
	 * image/jpeg , image/jpg , image/png , image/gif
	 * @param fileType
	 * @return
	 */
	public static boolean validateImageType(String fileType){
		return     fileType.equals("image/jpeg") 
				|| fileType.equals("image/jpg") 
				|| fileType.equals("image/png") 
				|| fileType.equals("image/gif") ;
	}
	/**
	 * 校验一个post是否符合有效
	 * @param post PostBean 存放一个帖子的所有信息
	 * @return 校验是产生的异常，如果没有产生异常则为空
	 * @throws Throwable 所有异常都封装到这个超类当中，这是MyEclipse自动做的
	 */
	public static List<ValidateException> validatePost(PostBean post) throws Throwable {
		List<ValidateException> exception = new ArrayList<ValidateException>() ;
		Field[] fields = post.getClass().getFields() ;
		for(Field field : fields){
			String fieldName = field.getName() ;
				// 使用反射调用Validate类的方法，可以减少很长的判断语句
				Method method = 
						Validate.class.getMethod("validate" + 
						fieldName.substring(0 , 1).toUpperCase() + fieldName.substring(1) , field.getType()) ;
				try {
					if(!(boolean)method.invoke(post, field.get(post))){
						exception.add(new ValidateException(fieldName + " 格式不正确！")) ;
					} ;
				} catch (InvocationTargetException e) {
					throw  e.getTargetException() ;
				}
		}
		return exception ;
	}
	
	/**
	 * 校验userName（学院 + 昵称）
	 * @param userName （学院 + 昵称）
	 * @return
	 */
	public static boolean validateUserName(String userName){
		if(userName.indexOf("_") == -1){
			return false ;
		}
		String dept = userName.substring(0,userName.indexOf("_") ) ;
		String nickName = userName.substring(userName.indexOf("_") + 1) ;
		return validateDept(dept) && validateName(nickName) ;
	}
	/**
	 * 校验id（11位字符串，格式为  CyyyyMMddxxxx）
	 * @param id
	 * @return
	 */
	public  static boolean validateId(String id){
		if(id.length() != 11){
			return false ;
		}
		// 类型字段
		String typeField = id.substring(0,1) ;
		// 时间字段
		String dateField = id.substring(1,9) ;
		// 流水号
		String serialNum = id.substring(9) ;
		// 通过试图将日期字符串转换成日期对象检验日期字符串是否合格
		boolean dateLegality = false ;
		try {
			SimpleDateFormat dateformat= new SimpleDateFormat("yyyyMMdd");
			Date date;
			date = dateformat.parse(dateField);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			dateLegality = true ;
		} catch (ParseException e) {
			// dateLegality = false ; 该语句在上面已经写了，所以这里就不再进行赋值
		}
		
		return typeField.matches("[S,B,G]{1}") && dateLegality && serialNum.matches("[0-9]{4}") ;
	}
	/**
	 * 校验发布信息的类型 B： 买 S ： 卖   G； 赠送
	 * @param type
	 * @return
	 */
	public static boolean validateType(String type){
		
		return type.matches("[B,S,G]{1}") ;
	}
	/**
	 * 校验书名
	 * @param bookName
	 * @return
	 */
	public static boolean validateBookName(String bookName){
		/*
		 * 仅能包含中文、数字、括号，不能超过数据库存储长度
		 */
		int storageCapacity = 40 ;// 数据库中的存储容量，类型varchar
		
		return  bookName.matches("([\u4E00-\u9FA5]|[0-9]|[a-z]|[A-Z]|[(,),（,）]){1,}") 
				&& bookName.length() <= storageCapacity ;
	}
	/**
	 * 校验作者名 ： 仅限于中文和英文名
	 * @param author
	 * @return
	 */
	public static boolean validateAuthor(String author){
		/**
		 * 
		 * 作者名： 例如 "张三" , "张三，李四" ， "张三，deitel" , "乔治·布什" ,"乔治.布什"
		 * 包含： 中文字符、英文大小写字符、中英文句点（有可能某些用户用中文句点分隔英文名）、中英文逗号、以及英文名间隔符“·”
		 * 最大长度为20
		 */
		return author.matches("([\u4E00-\u9FA5]|[a-z]|[A-Z]|[,,，,·,.,。,(,),（,）,]){1,20}" ) ;
	}
	/**
	 * 校验出版社
	 * @param poster
	 * @return
	 */
	public static  boolean validatePoster(String poster){
		return poster.matches("([\u4E00-\u9FA5]|[a-z]|[A-Z]){1,40}");
	}
	/**
	 * 校验isbn :标准13为isbn
	 * @param isbn
	 * @return
	 */
	public  static boolean validateIsbn( String isbn){
		
		return isbn.matches("[0-9]{13}");
	}
	/**
	 * 校验图书分类
	 * @param category
	 * @return
	 */
	public  static boolean validateCategory(String category){
		
		return false ;
	}
	public static  boolean validateDesc(String desc){
		return false ;
	}
	public  static boolean validatePrice(float price){
		return false ;
	}
	public  static boolean validateLinkmanName(String linkmanName){
		return false ;
	}
	public static  boolean validateLinkmanTel(String linkmanTel){
		return false ;
	}
	public static  boolean validateStatus(String status){
		return false ;
	}
}
