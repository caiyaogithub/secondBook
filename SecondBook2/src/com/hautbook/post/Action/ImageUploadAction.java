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
 * @function 将上传图片文件流写入到session中
 *  
 */
public class ImageUploadAction implements SessionAware{  
	private @Getter @Setter  File file ;
	private @Getter @Setter String fileFileName ;
	private @Getter @Setter String fileContentType ;
	// 项目根目录，在 struts.xml 中配置
	private @Getter @Setter String rootPath ;
	private Map<String , Object> session ;
	private @Getter InputStream result = new ByteArrayInputStream("success".getBytes());
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session ;
	}
	public String execute(){
			// 校验上传文件是否是允许的图片格式
		if(!Validate.validateImageType(fileContentType)){
			result = new ByteArrayInputStream("notallow".getBytes());
			return "result" ;
		}
			/**
			 * 
			 * 备注： 
			 * 1. 使用plupload上传组件，一次上传一张图片，关于plupload访问 www.plupload.com
			 * 2. 将上传的图片写入项目根目录下的temp目录中以当前sessionid为文件夹名的文件夹中，并将相应的文件名放入session中
			 * 3. 一个session中以"image"开头的都是已上传图片的路径信息
			 * 4. session格式为   <"image" + x , image-path> x为图片序列，x=1 的图片是封面
			 * 5. 当(表单提交)将temp目录中的临时文件写出到真正的上传文件夹（项目根目录下的imageupload）时，
			 *    删除session中相应的条目，并将临时文件删除
			 * 6. 当session消亡时，删除session中所有图片条目以及对应的临时图片文件
			 * 7. 当用户离开提交表单页面时，删除session中所有图片信息，以及对应的图片临时文件
			 * 8. 上传图片数量最大为15张
			 * 
			 */
		// 遍历session，确定图片编号
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
