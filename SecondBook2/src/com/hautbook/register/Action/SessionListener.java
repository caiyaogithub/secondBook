package com.hautbook.register.Action;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.hautbook.util.FileUtils;





/**
 * 
 * @author caiyao
 *
 * @function session������
 *
 */
public class SessionListener implements HttpSessionAttributeListener , HttpSessionListener{
	@Override
	public void attributeAdded(HttpSessionBindingEvent addEvent) {
		/**
		 * �û���¼ģ��session��application����Ϣ
		 * session �еĸ�ʽΪ�� "userName" : userName
		 * application �еĸ�ʽΪ�� userName : "login" 
		 * 
		 */
		if(addEvent.getName().equals("userName")){
			ServletContext application= addEvent.getSession().getServletContext() ;
			application.setAttribute(addEvent.getValue().toString(),"login") ;
			Logger.getLogger(this.getClass().getName()).info("�ɹ���" + addEvent.getValue() + "��Ϣ���ӵ�application�У�") ;
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent removeEvent) {
		if(removeEvent.getName().equals("userName")){
			ServletContext application= removeEvent.getSession().getServletContext() ;
			application.removeAttribute(removeEvent.getValue().toString()) ;
			(Logger.getLogger(this.getClass().getName())).info("�ɹ�����" + removeEvent.getValue() + "��Ϣ�û���application���Ƴ���");
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent replaceEvent) {
		if(replaceEvent.getName().equals("userName")){
			ServletContext application= replaceEvent.getSession().getServletContext() ;
			application.setAttribute(replaceEvent.getValue().toString(),"login") ;
			(Logger.getLogger(this.getClass().getName())).info("�滻application��" + replaceEvent.getValue() + "��ֵ��");
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent createEvent) {
		/***
		 * session ����ʱ�����κβ���
		 */
		(Logger.getLogger(this.getClass().getName())).info("session �ɹ�������");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent destroyEvent) {
		ServletContext application= destroyEvent.getSession().getServletContext() ;	
		HttpSession session = destroyEvent.getSession() ;
		Object userName = session.getAttribute("userName") ;
		/**
		 * �ж�session���Ƿ�����userNameΪ����ֵ��
		 * ����б�ʾ�û���session�������ڵ���֮ǰû��ִ�����߲���
		 * ���û�б�ʾ��session�������ڵ���֮ǰ�û�ִ�������߲���
		 * 
		 * */
		if(userName != null){
			Logger.getLogger(this.getClass().getName()).info(userName + " session����ǿ�����ߣ�");
			application.removeAttribute(userName.toString()) ;
		}else{
			Logger.getLogger(this.getClass().getName()).info("session���ڣ����û��Ѿ����ߣ�");
		}
		
		/**
		 * ����session���ϴ�����ʱͼƬ��Ϣ��ɾ��
		 * session��ʽ�� "image" + ͼƬ��� : ��ʱͼƬ�ļ��ľ���·��
		 * 1. ɾ��session�е���Ŀ
		 * 2. ɾ��session��Ӧ����ʱͼƬ�ļ���
		 */
		Enumeration<String> sessionAttrs = session.getAttributeNames() ;
		String tempFolder = "" ;// �����ʱͼƬ�ļ����ļ���
		while(sessionAttrs.hasMoreElements()){
			String sessionItem = sessionAttrs.nextElement() ;
			// ������δ�����ʵֻ��Ҫִ��һ�飬����û�뵽ʲô�ð취ʵ��ִֻ��һ��
			String value = session.getAttribute(sessionItem).toString() ;
			tempFolder = value.substring(0,value.lastIndexOf("/"))  ;
			if(sessionItem.startsWith("image")){
				session.removeAttribute(sessionItem) ;
				Logger.getLogger(this.getClass().getName()).info(sessionItem + " ע���ɹ���");
			}
		}
		FileUtils.deleteFolder( tempFolder );
		Logger.getLogger(this.getClass().getName()).info(
				"�ļ��У� " + tempFolder + "ɾ���ɹ���");
	}
}