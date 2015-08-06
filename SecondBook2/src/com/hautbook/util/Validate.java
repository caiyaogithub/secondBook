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
 * @function ִ�жԸ����ֶε�У�� 
 *
 */
public class Validate {

	/**
	 * У��ѧԺ�Ƿ����
	 * 
	 * @return
	 */
	public static boolean validateDept(String dept) {
		dept = dept.trim() ;
		String[] depts = { "����ʳƷѧԺ", "���繤��ѧԺ", "��ľ����ѧԺ", "��Ϣ��ѧ�빤��ѧԺ", "��ѧ����ѧԺ",
				"���﹤��ѧԺ", "���Ͽ�ѧ�빤��ѧԺ", "��������ѧԺ", "����ѧԺ", "����ó��ѧԺ", "��ѧԺ",
				"����ѧԺ", "��ѧԺ", "�����봫��ѧԺ", "�������ѧԺ", "���ʽ���ѧԺ", "��Ӣ����ѧԺ",
				"ְҵ����ѧԺ", "����ѧԺ", "˼�����ν���ѧԺ", "��������ѧԺ" };
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
	 * У���û������������ǳƲ�����ѧԺ���Ƿ���ȷ
	 * 
	 * @return
	 */
	public static boolean validateName(String name ) {
		/**
		 * ֻ�ܰ�����ĸ�����֣�����Ϊ1~10�ַ����������ĺ�Ӣ���ַ���
		 * 
		 */
		if(name != null){
			String regex = "([\u4e00-\u9fa5]|[a-z]|[A-Z]|[0-9]){1,10}";
			return name.matches(regex);
		}
		return false ;
	}
	/**
	 * У�� Email��ʽ�Ƿ���ȷ
	 * @return
	 */
	public static boolean validateEmail(String email){
		/**
		 * emailΪ��ѡ�ֶ�
		 */
		/**
		 * TODO email ������ʽ
		 */
		return true ;
		}
	/**
	 * У������
	 * @param password
	 * @return �������У��ɹ�����true�����߷���false
	 */
	public static boolean validatePassword(String password){
		/**���ֺ��ַ������ִ�Сд��6~20���ַ�**/
		String regex = "([0-9]|[a-z]|[A-Z]){6,20}" ;
		return password.matches(regex) ;
	}
	/**
	 * У��ͼƬ��ʽ �� 
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
	 * У��һ��post�Ƿ������Ч
	 * @param post PostBean ���һ�����ӵ�������Ϣ
	 * @return У���ǲ������쳣�����û�в����쳣��Ϊ��
	 * @throws Throwable �����쳣����װ��������൱�У�����MyEclipse�Զ�����
	 */
	public static List<ValidateException> validatePost(PostBean post) throws Throwable {
		List<ValidateException> exception = new ArrayList<ValidateException>() ;
		Field[] fields = post.getClass().getFields() ;
		for(Field field : fields){
			String fieldName = field.getName() ;
				// ʹ�÷������Validate��ķ��������Լ��ٺܳ����ж����
				Method method = 
						Validate.class.getMethod("validate" + 
						fieldName.substring(0 , 1).toUpperCase() + fieldName.substring(1) , field.getType()) ;
				try {
					if(!(boolean)method.invoke(post, field.get(post))){
						exception.add(new ValidateException(fieldName + " ��ʽ����ȷ��")) ;
					} ;
				} catch (InvocationTargetException e) {
					throw  e.getTargetException() ;
				}
		}
		return exception ;
	}
	
	/**
	 * У��userName��ѧԺ + �ǳƣ�
	 * @param userName ��ѧԺ + �ǳƣ�
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
	 * У��id��11λ�ַ�������ʽΪ  CyyyyMMddxxxx��
	 * @param id
	 * @return
	 */
	public  static boolean validateId(String id){
		if(id.length() != 11){
			return false ;
		}
		// �����ֶ�
		String typeField = id.substring(0,1) ;
		// ʱ���ֶ�
		String dateField = id.substring(1,9) ;
		// ��ˮ��
		String serialNum = id.substring(9) ;
		// ͨ����ͼ�������ַ���ת�������ڶ�����������ַ����Ƿ�ϸ�
		boolean dateLegality = false ;
		try {
			SimpleDateFormat dateformat= new SimpleDateFormat("yyyyMMdd");
			Date date;
			date = dateformat.parse(dateField);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			dateLegality = true ;
		} catch (ParseException e) {
			// dateLegality = false ; ������������Ѿ�д�ˣ���������Ͳ��ٽ��и�ֵ
		}
		
		return typeField.matches("[S,B,G]{1}") && dateLegality && serialNum.matches("[0-9]{4}") ;
	}
	/**
	 * У�鷢����Ϣ������ B�� �� S �� ��   G�� ����
	 * @param type
	 * @return
	 */
	public static boolean validateType(String type){
		
		return type.matches("[B,S,G]{1}") ;
	}
	/**
	 * У������
	 * @param bookName
	 * @return
	 */
	public static boolean validateBookName(String bookName){
		/*
		 * ���ܰ������ġ����֡����ţ����ܳ������ݿ�洢����
		 */
		int storageCapacity = 40 ;// ���ݿ��еĴ洢����������varchar
		
		return  bookName.matches("([\u4E00-\u9FA5]|[0-9]|[a-z]|[A-Z]|[(,),��,��]){1,}") 
				&& bookName.length() <= storageCapacity ;
	}
	/**
	 * У�������� �� ���������ĺ�Ӣ����
	 * @param author
	 * @return
	 */
	public static boolean validateAuthor(String author){
		/**
		 * 
		 * �������� ���� "����" , "����������" �� "������deitel" , "���Ρ���ʲ" ,"����.��ʲ"
		 * ������ �����ַ���Ӣ�Ĵ�Сд�ַ�����Ӣ�ľ�㣨�п���ĳЩ�û������ľ��ָ�Ӣ����������Ӣ�Ķ��š��Լ�Ӣ���������������
		 * ��󳤶�Ϊ20
		 */
		return author.matches("([\u4E00-\u9FA5]|[a-z]|[A-Z]|[,,��,��,.,��,(,),��,��,]){1,20}" ) ;
	}
	/**
	 * У�������
	 * @param poster
	 * @return
	 */
	public static  boolean validatePoster(String poster){
		return poster.matches("([\u4E00-\u9FA5]|[a-z]|[A-Z]){1,40}");
	}
	/**
	 * У��isbn :��׼13Ϊisbn
	 * @param isbn
	 * @return
	 */
	public  static boolean validateIsbn( String isbn){
		
		return isbn.matches("[0-9]{13}");
	}
	/**
	 * У��ͼ�����
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
