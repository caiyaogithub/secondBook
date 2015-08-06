package com.hautbook.JavaBean;

import lombok.Data;

/**
 * 
 * @author caiyao 
 *
 * @function ��ŷ�����Ϣ
 */
@Data
public class PostBean {
	private String userName ;// �� ѧԺ  + �ǳƣ�   
	private String id ;
	private String type ;
	private String bookName ;
	private String author ;
	private String poster ;
	private String isbn ;
	private String category ;
	private String desc ;
	private float price ;
	private String linkmanName ;
	private String linkmanTel ;
	private String status ;
	public PostBean(String userName, String id, String type, String bookName,
			String author, String poster, String isbn, String category,
			String desc, float price, String linkmanName, String linkmanTel,
			String status) {
		super();
		this.userName = userName;
		this.id = id;
		this.type = type;
		this.bookName = bookName;
		this.author = author;
		this.poster = poster;
		this.isbn = isbn;
		this.category = category;
		this.desc = desc;
		this.price = price;
		this.linkmanName = linkmanName;
		this.linkmanTel = linkmanTel;
		this.status = status;
	}
}
