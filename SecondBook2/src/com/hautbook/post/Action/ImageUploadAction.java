package com.hautbook.post.Action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;
import lombok.Setter;

import org.apache.struts2.interceptor.SessionAware;

import com.hautbook.util.FileUtils;
import com.hautbook.util.StrutsUtils;
import com.hautbook.util.Validate;

/**
 * 
 * @author caiyao 
 *
 * @function ���ϴ�ͼƬ�ļ���д�뵽session��
 *  
 */
public class ImageUploadAction implements SessionAware{  
	private @Getter @Setter  File file ;
	private @Getter @Setter String fileFileName ;
	private @Getter @Setter String fileContentType ;
	// ��Ŀ��Ŀ¼���� struts.xml ������
	private @Getter @Setter String rootPath ;
	private Map<String , Object> session ;
	private @Getter InputStream result = new ByteArrayInputStream("success".getBytes());
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session ;
	}
	public String execute(){
			// У���ϴ��ļ��Ƿ���������ͼƬ��ʽ
		if(!Validate.validateImageType(fileContentType)){
			result = new ByteArrayInputStream("notallow".getBytes());
			return "result" ;
		}
			/**
			 * 
			 * ��ע�� 
			 * 1. ʹ��plupload�ϴ������һ���ϴ�һ��ͼƬ������plupload���� www.plupload.com
			 * 2. ���ϴ���ͼƬд����Ŀ��Ŀ¼�µ�tempĿ¼���Ե�ǰsessionidΪ�ļ��������ļ����У�������Ӧ���ļ�������session��
			 * 3. һ��session����"image"��ͷ�Ķ������ϴ�ͼƬ��·����Ϣ
			 * 4. session��ʽΪ   <"image" + x , image-path> xΪͼƬ���У�x=1 ��ͼƬ�Ƿ���
			 * 5. ��(�����ύ)��tempĿ¼�е���ʱ�ļ�д�����������ϴ��ļ��У���Ŀ��Ŀ¼�µ�imageupload��ʱ��
			 *    ɾ��session����Ӧ����Ŀ��������ʱ�ļ�ɾ��
			 * 6. ��session����ʱ��ɾ��session������ͼƬ��Ŀ�Լ���Ӧ����ʱͼƬ�ļ�
			 * 7. ���û��뿪�ύ����ҳ��ʱ��ɾ��session������ͼƬ��Ϣ���Լ���Ӧ��ͼƬ��ʱ�ļ�
			 * 8. �ϴ�ͼƬ�������Ϊ15��
			 * 
			 */
		// ����session��ȷ��ͼƬ���
		Iterator<Entry<String, Object>> iter = session.entrySet().iterator() ;
		int imageNumInSession = 0 ;
		while(iter.hasNext()){
		
			imageNumInSession ++ ;
			iter.next() ;
		}
		if(imageNumInSession >= 15){
			result = new ByteArrayInputStream("max".getBytes()) ;
			return "result" ;
		}
		String tempImagePath = rootPath + "/temp/" + StrutsUtils.getSession().getId() + "/" +
				(imageNumInSession + 1) + fileFileName.substring(fileFileName.indexOf(".")) ;
		try{
			if(FileUtils.writeToFile(file , tempImagePath)){
				session.put("image" + (imageNumInSession + 1), tempImagePath) ;
			} 
			return "result" ;
		}catch(IOException e){
			e.printStackTrace(); 
			 result = new ByteArrayInputStream("error".getBytes());
			 return "result" ;
		}
	}
}