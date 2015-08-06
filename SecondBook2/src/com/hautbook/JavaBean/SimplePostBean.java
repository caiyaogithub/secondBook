package com.hautbook.JavaBean;
/**
 * 
 * @author caiyao
 *
 * @function 精简的帖子信息，用于在列表形式下的显示
 */
public class SimplePostBean {
	/**书名*/
	private String name ;
	/**长描述*/
	private String desc ;
	/**价格*/
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
