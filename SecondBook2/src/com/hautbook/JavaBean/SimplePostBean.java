package com.hautbook.JavaBean;
/**
 * 
 * @author caiyao
 *
 * @function �����������Ϣ���������б���ʽ�µ���ʾ
 */
public class SimplePostBean {
	/**����*/
	private String name ;
	/**������*/
	private String desc ;
	/**�۸�*/
	private int price ;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
